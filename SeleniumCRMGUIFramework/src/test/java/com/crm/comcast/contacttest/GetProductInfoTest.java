package com.crm.comcast.contacttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {

	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("iphone",Keys.ENTER);
		//capture product info
		String x = driver.findElement(By.xpath("//span[text()='"+productName+"']/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span/span[2]/span[2]")).getText();
		System.out.println(x);
	}
		
		@DataProvider
		public Object[][] getData() throws Throwable {
			ExcelUtility eLib=new ExcelUtility();
			int rowCount = eLib.getRowCount("product");
			
			Object[][] objArr = new Object[rowCount][2];
			
			for(int i=0; i<rowCount; i++) {
				objArr[i][0] = eLib.getDataFromExcel("product", i+1, 0);
				objArr[i][1] = eLib.getDataFromExcel("product", i+1, 1);
				
			}
			
			return objArr;
		}
}
