package com.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHost {
	
	public static void main(String[] args) throws UnknownHostException {
		
		String m = "http://"+InetAddress.getLocalHost().getHostAddress()+":8080/job/DataDrivenProject1/HTML_20Report/";
	
	System.out.println(m);
	}

}
