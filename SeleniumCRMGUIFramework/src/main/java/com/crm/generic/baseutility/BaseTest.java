package com.crm.generic.baseutility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseTest {

	
	//create object
		public FileUtility fLib = new FileUtility();
		public ExcelUtility eLib = new ExcelUtility();
		public JavaUtility jLib = new JavaUtility();
		public WebDriverUtility wLib = new WebDriverUtility();
		public DataBaseUtility dbLib = new DataBaseUtility();

		public WebDriver driver; 
		public static WebDriver sdriver;//if static --> it wont participate in parallel execution
	
		
	@BeforeSuite(groups = {"ST", "RT"})
	public void configBs() throws SQLException{
		System.out.println("====connect to db, report config====");
		dbLib.getDbConnection();
		
	}
	
/*	@Parameters("BROWSER")
	@BeforeClass(groups = {"ST", "RT"})
	public void configBc(String browser) throws Throwable {
		System.out.println("===Launch the browser===");
		String BROWSER = browser;
				//fLib.getDataFromPropertiesFile("browser");
		 if(BROWSER.equals("chrome")) { 
			 driver = new ChromeDriver(); 
		 }else if(BROWSER.equals("firefox")) { 
			 driver = new FirefoxDriver(); 
		}else if(BROWSER.equals("edge")) { 
			driver = new EdgeDriver(); 
		}else {
				driver = new ChromeDriver(); 
		} 
	}
*/
	@BeforeClass(groups = {"ST", "RT"})
	public void configBc() throws Throwable {
		System.out.println("===Launch the browser===");
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		
		 if(BROWSER.equals("chrome")) { 
			 driver = new ChromeDriver(); 
		 }else if(BROWSER.equals("firefox")) { 
			 driver = new FirefoxDriver(); 
		}else if(BROWSER.equals("edge")) { 
			driver = new EdgeDriver(); 
		}else {
				driver = new ChromeDriver(); 
		} 
		 sdriver = driver;
		 UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups = {"ST", "RT"})
	public void configBm() throws Throwable {
		System.out.println("===login===");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	}
	
	
	@AfterMethod(groups = {"ST", "RT"})
	public void configAm() throws Throwable {
		System.out.println("===logout===");
		HomePage hp = new HomePage(driver);
		hp.logoutApp();
	}
	
	@AfterClass(groups = {"ST", "RT"})
	public void configAc() {
		System.out.println("===close the browser===");
		driver.quit();
	}
	
	@AfterSuite(groups = {"ST", "RT"})
	public void configAs() throws SQLException {
		System.out.println("=====close db, report backup=====");
		dbLib.closeDbConnection();
		
	}
}
