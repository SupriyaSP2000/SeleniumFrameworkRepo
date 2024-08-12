package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="dtlview_Last Name")
	private WebElement contactInfoLastName;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startDateInfo;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDateInfo;
	
	@FindBy(xpath="//span[@class=\"dvHeaderText\"]")
	private WebElement headerInfoLastName; 
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement headerInfoOrgName;
	
	public WebElement getContactInfoLastName() {
		return contactInfoLastName;
	}

	public WebElement getStartDateInfo() {
		return startDateInfo;
	}

	public WebElement getEndDateInfo() {
		return endDateInfo;
	}

	public WebElement getHeaderInfoLastName() {
		return headerInfoLastName;
	}

	public WebElement getHeaderInfoOrgName() {
		return headerInfoOrgName;
	}
	
	
}
