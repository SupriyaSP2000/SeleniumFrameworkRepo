package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {

	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Products")
	private WebElement productLink;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;

	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement administratorIcon;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signOutLink;
	
	
	public WebElement getProductLink() {
		return productLink;
	}
	
	public WebElement getOrganizationLink() {
		return organizationLink;
	}
	
	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getAdministratorIcon() {
		return administratorIcon;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	
	//business logic 
	WebDriverUtility wLib = new WebDriverUtility();
	public void logoutApp() throws Throwable {
		administratorIcon.click();
		wLib.mouseHover(driver, signOutLink);
		wLib.clickElement(driver, signOutLink);
	}
}
