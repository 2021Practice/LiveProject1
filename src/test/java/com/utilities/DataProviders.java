package com.utilities;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.base.TestBase;

public class DataProviders extends TestBase{


	@DataProvider(name="dp")
	public Object[][] getData(Method m) {
		
		String sheetName = m.getName();
		
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int i=1; i<rows;i++) {
			for (int j=0; j<cols;j++) {
				data[i-1][j] = excel.getCellData(sheetName, j, i);
			}
		}
				
		return data;
		
	}
	
	
}
