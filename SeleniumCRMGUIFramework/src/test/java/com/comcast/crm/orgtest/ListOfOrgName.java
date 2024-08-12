package com.comcast.crm.orgtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class ListOfOrgName {

	public static void main(String[] args) throws Throwable {
		
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		
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

		wLib.waitForPageToLoad(driver);
		driver.get(URL);

		LoginPage lp = new LoginPage(driver); 
		lp.loginToApp(USERNAME, PASSWORD);
		
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();
		
		OrganizationPage op = new OrganizationPage(driver);
		op.listOfAllOrg();
		
	}
}
