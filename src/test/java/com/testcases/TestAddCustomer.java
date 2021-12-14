package com.testcases;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.DataProviders;

public class TestAddCustomer extends TestBase {

	@Test(dataProviderClass = DataProviders.class, dataProvider = "dp")
	public void testAddCustomer(String firstName, String lastName, String postalCode, String runmode) {

		if (!runmode.equalsIgnoreCase("y")) {
			throw new SkipException("Skipping test case from TestAddCustomer for customer " + firstName + " " + lastName
					+ " as run mode is \"No\"");
		}

		try {

			click("AddCusBtn_XPATH");

			clearTextBox("FNameTB_XPATH");
			typeIn("FNameTB_XPATH", firstName);
			clearTextBox("LNameTB_XPATH");
			typeIn("LNameTB_XPATH", lastName);
			clearTextBox("PCodeTB_XPATH");
			typeIn("PCodeTB_XPATH", postalCode);

			click("AddCusSubmit_XPATH");

			wait = new WebDriverWait(driver, Duration.ofSeconds(5));

			Alert alert = wait.until(ExpectedConditions.alertIsPresent());

			Assert.assertTrue(alert.getText().contains("added successfully"), "New Customer Addition failed");

			logger.debug(alert.getText());
			alert.accept();

		} catch (Throwable t) {

			firstName = null;
			lastName = null;
			postalCode = null;
			click("AddCusBtn_XPATH");

		}

	}
}
