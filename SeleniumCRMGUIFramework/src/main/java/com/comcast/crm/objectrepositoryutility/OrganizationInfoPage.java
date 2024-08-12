package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class=\"dvHeaderText\"]")
	WebElement headerInfo;
	
	@FindBy(id="dtlview_Organization Name")
	WebElement headerInfoOrgName;
	
	@FindBy(id="dtlview_Phone")
	WebElement phoneNumInfo;
	
	@FindBy(id="dtlview_Industry")
	WebElement industryInfo;
	
	@FindBy(id="dtlview_Type")
	WebElement typeInfo;
	
	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getHeaderInfoOrgName() {
		return headerInfoOrgName;
	}

	public WebElement getPhoneNumInfo() {
		return phoneNumInfo;
	}

	public WebElement getIndustryInfo() {
		return industryInfo;
	}

	public WebElement getTypeInfo() {
		return typeInfo;
	}
	
	
}
