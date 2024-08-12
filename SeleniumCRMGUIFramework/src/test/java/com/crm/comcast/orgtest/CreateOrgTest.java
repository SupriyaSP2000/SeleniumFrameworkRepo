package com.crm.comcast.orgtest;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListenerImpleClass;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.crm.generic.baseutility.BaseTest;

@Listeners(com.comcast.crm.listenerutility.ListenerImpleClass.class)
public class CreateOrgTest extends BaseTest {

	@Test(groups = "ST", priority=1)
	public void createOrgTest() throws Throwable {

		// generate randomNum using javaUtility
		// read data from excelUtility
		UtilityClassObject.getTest().log(Status.INFO, "read data from excel");
		String orgName = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNumber();

		// step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// step 3: click on "create organization" button
		UtilityClassObject.getTest().log(Status.INFO, "navigate to create org page");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		// step 4: enter all details & create new organization
		UtilityClassObject.getTest().log(Status.INFO, "create a new org");
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createNewOrganization(orgName);
		
		UtilityClassObject.getTest().log(Status.INFO, orgName + "===>created new org");

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
	}

	@Test(groups = "RT", priority=2)
	public void createOrgWithIndustry() throws Throwable {

		// generate the random number using javaUtility
		// read data from excelUtility
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// step 3: click on "create organization" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		// step 4: enter all details & create new organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);

		WebElement wbele1 = cop.getIndustrySelect();
		wLib.handleDropdown(industry, wbele1);

		WebElement wbele2 = cop.getTypeSelect();
		wLib.handleDropdown(type, wbele2);

		cop.createNewOrganizationWithIndustry(orgName);

		// verify the industries and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustry = oip.getIndustryInfo().getText();
		if (actIndustry.equals(industry)) {
			System.out.println(industry + " information is verified==PASS");
		} else {
			System.out.println(industry + " information is not verified==FAIL");
		}

		String actType = oip.getTypeInfo().getText();
		if (actType.equals(type)) {
			System.out.println(type + " information is verified==PASS");
		} else {
			System.out.println(type + " information is not verified==FAIL");
		}
	}

	@Test(groups = "RT", priority=3)
	public void createOrgWithPhoneNumber() throws Throwable {

		// generate the random number using javaUtility
		// read data from excelUtility
		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);

		// step 2: navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationLink().click();

		// step 3: click on "create organization" button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateOrgBtn().click();

		// step 4: enter all details & create new organization
		CreatingNewOrganizationPage cop = new CreatingNewOrganizationPage(driver);
		cop.createNewOrganizationWithPhoneNum(orgName, phoneNumber);

		// verify phoneNumber info expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNumber = oip.getPhoneNumInfo().getText();
		if (actPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " information is created==PASS");
		} else {
			System.out.println(phoneNumber + " information is not created==FAIL");
		}
	}

}
