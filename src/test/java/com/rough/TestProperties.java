package com.rough;

import java.io.FileInputStream;
import java.util.Properties;




public class TestProperties {

	public static void main(String[] args) throws Exception {
		
		Properties config = new Properties();
		Properties OR = new Properties();
		
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(inputStream);
		
		
		inputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(inputStream);
			
		System.out.println(config.getProperty("browser"));
		System.out.println(OR.getProperty("BMLoginBtn"));

	}

}
