package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.generic.baseutility.BaseTest;

public class CreateContactTest extends BaseTest {

	@Test
	public void createContactTest() throws IOException, Throwable {

		// generate the random number using javaUtility
		// read data from excelUtility
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create organization" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step 4: enter all details & create new contact name
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createNewContact(lastName);

		// verify header contact info expected result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actLastName = cip.getContactInfoLastName().getText();
		if (actLastName.contains(lastName)) {
			System.out.println(lastName + " is created==PASS");
		} else {
			System.out.println(lastName + " is not created==FAIL");
		}

	}
	
	
	@Test
	public void createContactWithSupportDateTest() {
		
	}
	
	@Test
	public void createContactWithOrgTest() {
		
	}
}
