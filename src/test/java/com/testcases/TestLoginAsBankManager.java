package com.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.TestBase;

public class TestLoginAsBankManager extends TestBase {

	@Test
	public void testLoginAsBankManager() {
			
		
		click("BMLoginBtn_XPATH");
//		driver.findElement(By.xpath(OR.getProperty())).click();
		Assert.assertTrue(IsElementPresent(By.xpath(OR.getProperty("AddCusBtn_XPATH"))));
	
	}
	
}
