package com.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.TestBase;

public class CaptureScreen extends TestBase {

	private static int counter=0;
	public static String CaptureScreenShot() {

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		counter++;
		try {
			String path = System.getProperty("user.dir") + "\\screenshots\\"
					+ System.getProperty("current.date")+"_"+counter+".jpg";
			FileUtils.copyFile(screenshot, new File(path));

			return path;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
//	public static String elementScreenshot() {
//		
//		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//		
//	}

}
