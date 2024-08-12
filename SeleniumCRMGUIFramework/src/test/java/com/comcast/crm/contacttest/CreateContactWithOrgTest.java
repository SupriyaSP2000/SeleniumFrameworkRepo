package com.comcast.crm.contacttest;

import java.io.FileNotFoundException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws FileNotFoundException, Throwable {
	
		//using file utility & excel utility
		
		//create object
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//read data from fileUtility
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
	
	//generate the random number using javaUtility	
	//read data from excelUtility
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();	
			
	//polymorphism program
		 WebDriver driver = null; 
		 if(BROWSER.equals("chrome")) { 
			 driver = new ChromeDriver(); 
		 }else if(BROWSER.equals("firefox")) { 
			 driver = new FirefoxDriver(); 
		}else if(BROWSER.equals("edge")) { 
			driver = new EdgeDriver(); 
		}else {
				driver = new ChromeDriver(); 
		}
		
		 wLib.toMaximizeWindow(driver);
	//creating an organization
		 //step 1: login to app
		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		//lazy initialization
		LoginPage lp = new LoginPage(driver); //Using loginpage constructor -> this will take care of initialization
		lp.loginToApp(USERNAME, PASSWORD);
		
		//step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
		//step 3: click on "create organization" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();
		
		//step 4: enter all details & create new organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createNewOrganization(orgName);
		
		//verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderInfo().getText();
		if(headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		}else {
			System.out.println(orgName + " is not created==FAIL");
		}
		
		//verify header ordName info expected result
		String actOrgName = oip.getHeaderInfoOrgName().getText();
		if(actOrgName.equals(orgName)) {
			System.out.println(orgName + " information is created==PASS");
		}else {
			System.out.println(orgName + " information is not created==FAIL");
		}
	
	//navigate to contact module
		hp.getContactLink().click();
				
		//step 3: click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();
				
		//step 4: enter all details & create new organization
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createNewContactWithOrg(lastName, orgName);
		
		//verify header contact info expected result
				
		//verify header lastname expected result & organame result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actHeaderLastName = cip.getHeaderInfoLastName().getText();
		if(actHeaderLastName.contains(lastName)) {
			System.out.println(lastName + " header verified==PASS");
		}else {
			System.out.println(lastName + " header verified==PASS");
		}
			
				
		String actInfoOrgName = cip.getHeaderInfoOrgName().getText();
		System.out.println(actInfoOrgName);
		if(actInfoOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "information is created==PASS");
		}else {
			System.out.println(orgName + "information is not created==FAIL");
		}	
		
		//step5: logout
		hp.logoutApp();
	}
}
