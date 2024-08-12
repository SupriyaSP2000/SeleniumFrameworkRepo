package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * 
 * @author SUPRIYA PERIYASAMY
 * 
 *         Contains Login Page elements & business lib like login()
 */

public class LoginPage extends WebDriverUtility {
	// rule 1 -> create separate java class

	WebDriver driver; // providing driver obj as global

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// this Pagefactory will load all the elements with
												// current address and will return the obj of pom class
												// this -> refers the current class object
	}

	// rule 2 -> Object Creation
	@FindBy(name = "user_name")
	private WebElement usernameEdit;

	@FindBy(name = "user_password")
	private WebElement passwordEdit;

	@FindBy(id = "submitButton")
	private WebElement loginBtn;

	// rule 3 -> object initialization

	// rule 4 -> object encapsulation
	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	// rule 5 -> object utilization - 2 ways - using getters() -in testscripts,
	// via Actions - by providing business method in pom page & calling it in
	// testscripts
	/**
	 * Login to application based on username, password args
	 * 
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username, String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}

}
