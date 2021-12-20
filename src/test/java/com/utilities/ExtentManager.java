package com.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	
	private static ExtentReports extentReports;
	
	public static ExtentReports getInstance() {
		
		if(extentReports==null) {
//		extentReports = new ExtentReports(System.getProperty("user.dir") + "\\extentReports\\"
//				+ "Report_"+new Date().toString().replace(" ", "_").replace(":", "_")+".html", false , DisplayOrder.OLDEST_FIRST);
		
			extentReports = new ExtentReports(System.getProperty("user.dir") + "\\extentReports\\"
					+ "extentReport.html", true , DisplayOrder.OLDEST_FIRST);
			
		
		extentReports.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
		
	}
		return extentReports;
	
}
	
	
}
