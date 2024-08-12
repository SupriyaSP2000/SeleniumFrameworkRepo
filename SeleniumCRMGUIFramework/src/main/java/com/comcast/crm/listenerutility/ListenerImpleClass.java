package com.comcast.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.generic.baseutility.BaseTest;

public class ListenerImpleClass implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter spark;
	public static ExtentReports report; //want to use this variable in testcases so declaring it globally
				 				//making this var as public, so that we can inherit that var and use it in all classes
	public static ExtentTest test;
	String time = new Date().toString().replace(" ", "_").replace(":", "_");

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
	//spark report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
	//add env info and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "Chrome-100");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		//taking backup or to save the report
				report.flush(); 
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("=======>"+result.getMethod().getMethodName()+">====START=====<");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+">====STARTED====<");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("=======>"+result.getMethod().getMethodName()+"<====END=====");
		test.log(Status.PASS, result.getMethod().getMethodName()+">====COMPLETED SUCCESSFULLY====<");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
	
		TakesScreenshot edriver = (TakesScreenshot) BaseTest.sdriver;
		String filePath = edriver.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+">====FAILED====<");
		test.log(Status.FAIL, result.getThrowable()); //to get the exception details
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName()+">====SKIPPED====<");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	
}
