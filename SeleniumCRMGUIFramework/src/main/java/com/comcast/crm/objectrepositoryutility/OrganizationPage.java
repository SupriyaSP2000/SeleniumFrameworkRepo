package com.comcast.crm.objectrepositoryutility;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {

	WebDriver driver;
	public OrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	@FindBy(xpath="//img[@title=\"Create Organization...\"]")
	private WebElement createOrgBtn;
	
	@FindBy(xpath="//table[@class=\"lvt small\"]/tbody/tr/td[3]")
	private List<WebElement> orgList;
	
	@FindBy(name="search_text")
	private WebElement searchEdt;
	
	@FindAll({@FindBy(id="bas_searchfield"), @FindBy(name="search_field")})
	private WebElement searchDropDown;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	
	
	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}
	
	public List<WebElement> getOrgList() {
		return orgList;
	}

	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchDropDown() {
		return searchDropDown;
	}
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}

	
	
	public void listOfAllOrg() {
		for(WebElement org : orgList)
		{
			String orgName = org.getText();
			System.out.println(orgName);
		}
	}
}
