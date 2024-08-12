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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationWithPhoneNumberTest {

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
				String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
				String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
				
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

				//step 1: login to app
				wLib.waitForPageToLoad(driver);
				driver.get(URL);
				
				LoginPage lp = new LoginPage(driver); //Using loginpage constructor -> this will take care of initialization
				lp.loginToApp(USERNAME, PASSWORD);
				
				//step 2: navigate to organization module
				HomePage hp=new HomePage(driver);
				hp.getOrganizationLink().click();
				
				//step 3: click on "create organization" button
				OrganizationPage op = new OrganizationPage(driver);
				op.getCreateOrgBtn().click();
				
				//step 4: enter all details & create new organization
				CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
				cop.createNewOrganizationWithPhoneNum(orgName, phoneNumber);
				
				//verify phoneNumber info expected result
				OrganizationInfoPage oip = new OrganizationInfoPage(driver);
				String actPhoneNumber = oip.getPhoneNumInfo().getText();
				if(actPhoneNumber.equals(phoneNumber)) {
					System.out.println(phoneNumber + " information is created==PASS");
				}else {
					System.out.println(phoneNumber + " information is not created==FAIL");
				}
				
				//sept 5:logout
				hp.logoutApp();
	}

}
