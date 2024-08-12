package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewOrganizationPage {

	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(name="phone")
	private WebElement phoneNumEdit;
	
	@FindBy(name="industry")
	private WebElement industrySelect;
	
	@FindBy(name="accounttype")
	private WebElement typeSelect;
	
	@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveBtn;
	
	//getters method
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}
	
	public WebElement getPhoneNumEdit() {
		return phoneNumEdit;
	}

	public WebElement getIndustrySelect() {
		return industrySelect;
	}

	public WebElement getTypeSelect() {
		return typeSelect;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	//business logics
	public void createNewOrganization(String orgName) {
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createNewOrganizationWithPhoneNum(String orgName, String phoneNumber) {
		orgNameEdit.sendKeys(orgName);
		phoneNumEdit.sendKeys(phoneNumber);
		saveBtn.click();
	}
	
	public void createNewOrganizationWithIndustry(String orgName) {
		orgNameEdit.sendKeys(orgName);
		industrySelect.click();
		typeSelect.click();
		saveBtn.click();
	}
}
