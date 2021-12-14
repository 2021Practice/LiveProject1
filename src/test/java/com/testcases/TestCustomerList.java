package com.testcases;

import org.testng.annotations.Test;

import com.base.TestBase;

public class TestCustomerList extends TestBase{

	@Test
	public void  testCustomerList() {
		
		click("CustomersBtn_XPATH");
		
		readFromTable(OR.getProperty("tbHeadRow_XPATH"), OR.getProperty("tbRow_XPATH"), OR.getProperty("tbCol_XPATH"));
		
		
	}
	
	
	
	
	
}
