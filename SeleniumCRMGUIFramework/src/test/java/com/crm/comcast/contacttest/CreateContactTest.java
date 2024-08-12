package com.crm.comcast.contacttest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.crm.generic.baseutility.BaseTest;

public class CreateContactTest extends BaseTest {

	@Test(groups = "ST")
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
		
		String actHeader = cip.getHeaderInfoLastName().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);
		System.out.println("======validated header========");
		
		String actLastName = cip.getContactInfoLastName().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();
		System.out.println("======validated lastname========");
 
	}

	@Test(groups = "RT")
	public void createContactWithSupportDateTest() throws Throwable {

		// generate the random number using javaUtility
		// read data from excelUtility
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3: click on "create organization" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step 4: enter all details & create new contact
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequiredDateYYYYDDMM(30);

		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createNewContactWithDate(lastName, startDate, endDate);

		// verify startdate & enddate info expected result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actStartDate = cip.getStartDateInfo().getText();
		if (actStartDate.equals(startDate)) {
			System.out.println(startDate + " information is verified==PASS");
		} else {
			System.out.println(startDate + " information is not verified==FAIL");
		}

		String actEndDate = cip.getEndDateInfo().getText();
		if (actEndDate.equals(endDate)) {
			System.out.println(endDate + " information is verified==PASS");
		} else {
			System.out.println(endDate + " information is not verified==FAIL");
		}

	}

	@Test(groups = "RT")
	public void createContactWithOrgTest() throws Throwable {

		// generate the random number using javaUtility
		// read data from excelUtility
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();
		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// step 3: click on "create organization" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		// step 4: enter all details & create new organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createNewOrganization(orgName);

		// verify header msg expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headerInfo = oip.getHeaderInfo().getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is created==PASS");
		} else {
			System.out.println(orgName + " is not created==FAIL");
		}

		// verify header ordName info expected result
		String actOrgName = oip.getHeaderInfoOrgName().getText();
		if (actOrgName.equals(orgName)) {
			System.out.println(orgName + " information is created==PASS");
		} else {
			System.out.println(orgName + " information is not created==FAIL");
		}

		// navigate to contact module
		hp.getContactLink().click();

		// step 3: click on "create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContactBtn().click();

		// step 4: enter all details & create new organization
		CreatingNewContactPage ccp = new CreatingNewContactPage(driver);
		ccp.createNewContactWithOrg(lastName, orgName);

		// verify header contact info expected result

		// verify header lastname expected result & organame result
		ContactInfoPage cip = new ContactInfoPage(driver);
		String actHeaderLastName = cip.getHeaderInfoLastName().getText();
		if (actHeaderLastName.contains(lastName)) {
			System.out.println(lastName + " header verified==PASS");
		} else {
			System.out.println(lastName + " header verified==PASS");
		}

		String actInfoOrgName = cip.getHeaderInfoOrgName().getText();
		System.out.println(actInfoOrgName);
		if (actInfoOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "information is created==PASS");
		} else {
			System.out.println(orgName + "information is not created==FAIL");
		}
	}

}
