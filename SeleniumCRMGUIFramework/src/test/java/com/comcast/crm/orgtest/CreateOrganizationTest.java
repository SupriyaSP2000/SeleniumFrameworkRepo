package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationTest {

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
				
			//generate randomNum using javaUtility	
			//read data from excelUtility
				String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
				
			//polymorphism program
				 WebDriver driver; 
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
				
	
				//step 5: logout
				hp.logoutApp();
	
	}

}
