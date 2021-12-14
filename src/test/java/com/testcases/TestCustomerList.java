package com.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.base.TestBase;
import com.utilities.DataProviders;

public class TestCustomerList extends TestBase{

	@Test
	public void  testCustomerList() {
		
		click("CustomersBtn_XPATH");
		
		readFromTable(OR.getProperty("tbHeadRow_XPATH"), OR.getProperty("tbRow_XPATH"), OR.getProperty("tbCol_XPATH"));
		
		System.out.println("Competed writing in excel");
	}
	
	@Test(dataProviderClass=DataProviders.class, dataProvider="dp")
	public void testDeleteCustomer(String firstName, String lastName, String postCode, String accNumber, String deleteCus) {
		
		System.out.println("passed arg from delete "+deleteCus +" for "+ firstName+ " "+lastName );
		if(deleteCus.equalsIgnoreCase("N")) {
			
			throw new SkipException("Skipping deletion of customer "+firstName+" "+lastName+ " as Delete Customer is set to \"NO\"");
		}
		
		deleteCusFromTable(OR.getProperty("tbRow_XPATH"), firstName, lastName);
		
		
		
	}
	
	
	
	
	
}
