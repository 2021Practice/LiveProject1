package com.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.DataProviders;

public class TestOpenAccount extends TestBase {
	
		
		@Test(dataProviderClass=DataProviders.class,dataProvider="dp")
		public void testOpenAccount(String cusName, String currency, String runmode) {
			
			if (!runmode.equalsIgnoreCase("y")) {
				throw new SkipException("Skipping test case from TestOpenAccount for customer " + cusName + " as run mode is \"No\"");
			}
			
			
			click("OpenAccBtn_XPATH");
			
			selectFromDropDown("customer_XPATH", cusName);
			selectFromDropDown("currency_XPATH", currency);
			click("processBtn_XPATH");
				
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			Assert.assertTrue(alert.getText().contains("Account created successfully"),"Account creation is failed");
			logger.debug(alert.getText());
			alert.accept();
							
		}
}
