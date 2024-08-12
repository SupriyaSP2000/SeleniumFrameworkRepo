package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	//implicit wait
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	//explicit wait
	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	//maximize window 
	public void toMaximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	//minimize window
	public void toMinimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	
	//switch to another window
	public void switchToTabOnURL(WebDriver driver, String partilURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowID = it.next(); //to 1st capture data from set -> next()
			driver.switchTo().window(windowID); //switching to that window
			
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains(partilURL))
			{
				break;
			}
		}
	}
	
	public void switchToTabOnTitle(WebDriver driver, String partilTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String windowID = it.next(); //to 1st capture data from set -> next()
			driver.switchTo().window(windowID); //switching to that window
			
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains(partilTitle))
			{
				break;
			}
		}
	}
	
	//switch to another frame --> overloaded method - args are diff
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	
	//switch to alert 
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	//select dropdown
	public Select dropdown(WebElement element) {
		Select sel = new Select (element);
		return sel;
	}
	public void handleDropdown(WebElement element, String value) {
		dropdown(element).selectByValue(value);
	}
	public void handleDropdown(WebElement element, int index) {
		dropdown(element).selectByIndex(index);
	}
	public void handleDropdown(String text, WebElement element) {
		dropdown(element).selectByVisibleText(text);
	}
	
	//mouse actions
	public Actions action(WebDriver driver) {
		Actions act = new Actions(driver);
		return act;
	}
	public void mouseHover(WebDriver driver, WebElement element) {
		action(driver).moveToElement(element).perform();
	}
	public void doubleClick(WebDriver driver, WebElement element) {
		action(driver).doubleClick(element).perform();
	}
	public void dragAndDrop(WebDriver driver, WebElement src, WebElement dest) {
		action(driver).dragAndDrop(src, dest).perform();
	}
	public void rightClick(WebDriver driver, WebElement element) {
		action(driver).contextClick(element).perform();
	}
	public void clickAndHold(WebDriver driver, WebElement element) {
		action(driver).clickAndHold(element).perform();
	}
	public void release(WebDriver driver, WebElement element) {
		action(driver).release(element).perform();
	}
	public void scrollToElement(WebDriver driver, WebElement element) {
		action(driver).scrollToElement(element).perform();
	}
	public void scrollByAmount(WebDriver driver, int x, int y) {
		action(driver).scrollByAmount(x, y).perform();
	}
	public void clickEle(WebDriver driver, WebElement element) {
		action(driver).click(element).perform();
	}
	
	//screenshot
	public void takesScreenshot(WebDriver driver, String fileName) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshots"+fileName+".png");
		FileUtils.copyFile(src, dest);
	}
	
	//javascript executor
	public void scrollUntilEleToBeVisible(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")");
	}
	public void scrollUntilElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	public void clickElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", element);
	}
}




















