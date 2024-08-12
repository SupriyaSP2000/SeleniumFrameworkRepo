package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewContactPage {

	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	WebElement lastNameEdit;
	
	@FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveBtn;
	
	@FindBy(name="support_start_date")
	private WebElement startDateSelect;
	
	@FindBy(name="support_end_date")
	private WebElement endDateSelect;
	
	@FindBy(xpath="//input[@name=\"account_name\"]/following-sibling::img")
	private WebElement selectOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchTextOrg;
	
	@FindBy(name="search")
	private  WebElement searchIconOrg;
	
	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}
	
	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getStartDateSelect() {
		return startDateSelect;
	}

	public WebElement getEndDateSelect() {
		return endDateSelect;
	}

	public WebElement getSelectOrgBtn() {
		return selectOrgBtn;
	}

	public WebElement getSearchTextOrg() {
		return searchTextOrg;
	}

	public WebElement getSearchIconOrg() {
		return searchIconOrg;
	}

	//business logic
	public void createNewContact(String lastName) {
		lastNameEdit.sendKeys(lastName);
		saveBtn.click();
	}
	
	public void createNewContactWithDate(String lastName, String startDate, String endDate) {
		lastNameEdit.sendKeys(lastName);
		startDateSelect.clear();
		startDateSelect.sendKeys(startDate);
		endDateSelect.clear();
		endDateSelect.sendKeys(endDate);
		saveBtn.click();
	}
	
	WebDriverUtility wLib = new WebDriverUtility();
	public void createNewContactWithOrg(String lastName, String orgName) {
		lastNameEdit.sendKeys(lastName);
		selectOrgBtn.click();
		wLib.switchToTabOnURL(driver, "module=Accounts");
		searchTextOrg.sendKeys(orgName);
		searchIconOrg.click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		wLib.switchToTabOnURL(driver, "module=Contacts");
		saveBtn.click();
	}
}
