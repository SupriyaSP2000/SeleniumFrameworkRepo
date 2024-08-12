package com.comcast.crm.listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImple implements IRetryAnalyzer{

	int count=0;
	int limitCount=5; //retry the failed TS 5 times
	@Override
	public boolean retry(ITestResult result) {
		
		if(count<limitCount)
		{
			count++;
			return true;
		}
		return false;
	}

	
}
