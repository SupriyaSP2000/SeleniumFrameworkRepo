package com.comcast.crm.contacttest;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDataTest {

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

			 //step 1: login to app
				
				 wLib.waitForPageToLoad(driver);
				driver.get(URL);

				LoginPage lp = new LoginPage(driver);
				lp.loginToApp(USERNAME, PASSWORD);
				
				//step 2: navigate to contact module
				HomePage hp = new HomePage(driver);
				hp.getContactLink().click();
				
				//step 3: click on "create organization" button
				ContactPage cp = new ContactPage(driver);
				cp.getCreateContactBtn().click();
				
				//step 4: enter all details & create new contact
				String startDate = jLib.getSystemDateYYYYDDMM();
				String endDate = jLib.getRequiredDateYYYYDDMM(30);
					
				CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
				ccp.createNewContactWithDate(lastName, startDate, endDate);
				
				//verify startdate & enddate info expected result
				ContactInfoPage cip = new ContactInfoPage(driver);
				String actStartDate = cip.getStartDateInfo().getText();
				if(actStartDate.equals(startDate)) {
					System.out.println(startDate + " information is verified==PASS");
				}else {
					System.out.println(startDate + " information is not verified==FAIL");
				}
				
				String actEndDate = cip.getEndDateInfo().getText();
				if(actEndDate.equals(endDate)) {
					System.out.println(endDate + " information is verified==PASS");
				}else {
					System.out.println(endDate + " information is not verified==FAIL");
				}
				
				//step 5: logout
				hp.logoutApp();
			
	}
	
}
