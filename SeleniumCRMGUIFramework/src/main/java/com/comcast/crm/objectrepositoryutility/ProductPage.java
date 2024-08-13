package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage {

	@FindBy(xpath="alt=\"Create Product...\"")
	private WebElement createProductBtn;
	
	@FindBy(name="searchBtn")
	private WebElement searchButton;
}
