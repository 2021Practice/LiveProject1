package com.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;


import com.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.CaptureScreen;
import com.utilities.TestUtils;

public class CustomListeners extends TestBase implements ITestListener {

	public void onTestFailure(ITestResult result) {

		System.out.println("Listener on failure");
		//Capturing test case failure screenshot and receiving path to file saved
		String path = CaptureScreen.CaptureScreenShot();
	//	System.out.println(path);

		//entering logs in ReportNG 
		Reporter.log(result.getName()+" -- Test is failed..."+ "<a href=" + path + " target=\"_blank\" >ScreenShot</a>");
		Reporter.log("<a href=" + path + " target=\"_blank\" ><img src=" + path
				+ " target=\"_blank\" height=50 width = 50></img></a> <br>");
		
		// entering logs in extent report
		test.log(LogStatus.FAIL, "Failed test --- "+result.getName().toUpperCase()+ "<a href=" + path + " target=\"_blank\" ><img src=" + path
				+ " target=\"_blank\" height=50 width = 50></img></a> <br>" + result.getThrowable());
		
		extentReports.endTest(test);
		extentReports.flush();
		

	}

	public void onTestSuccess(ITestResult result) {

		Reporter.log(result.getName() + " --Test is successfully performed"+"<br>");
		
		test.log(LogStatus.PASS, "Passed test --- "+result.getName().toUpperCase());
		extentReports.endTest(test);
		extentReports.flush();
	}

	public void onTestSkipped(ITestResult result) {

		Reporter.log(result.getName() + " --Test is Skipped as "+result.getThrowable()+"<br>");
		
		test.log(LogStatus.SKIP, "Skipped test --- "+result.getName().toUpperCase() +" as "+result.getThrowable());
		extentReports.endTest(test);
		extentReports.flush();}
	
	public void onTestStart(ITestResult result) {
		
		logger.debug("Starting test --- " + result.getName());
		test = extentReports.startTest(result.getName());
		
	//	System.out.println("Test on start is called for  "+result.getName());
		//Checking run modes
		if(!TestUtils.isRunnable(result.getName(), excel)) {
			throw new SkipException("Skipping test case "+result.getName()+" as run mode is \"No\"");
		}
		
	
	}
}
