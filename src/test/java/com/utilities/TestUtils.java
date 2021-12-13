package com.utilities;

import com.base.TestBase;

public class TestUtils extends TestBase {

	public static boolean isRunnable(String testName, ExcelReader excel) {

		String sheetName = "testSuite";
		int rows = excel.getRowCount(sheetName);
	
		for (int row=1; row <rows; row++) {
			String testCase = excel.getCellData(sheetName, "TC ID", row);
			if (testName.equalsIgnoreCase(testCase)) {
				String runMode = excel.getCellData(sheetName, "RunMode", row);
				if (runMode.equalsIgnoreCase("Y")) {
					return true;
				}
				else {
					return false;
					}
			}
		}
		return false;
	}
}
