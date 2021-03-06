package com.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.CaptureScreen;
import com.utilities.ExcelReader;
import com.utilities.ExtentManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream inputStream;
	public static Logger logger = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports extentReports = ExtentManager.getInstance();
	public static ExtentTest test;
	private String browser;
	
	
	@BeforeSuite
	public void setUp() {

		if (driver == null) {

			System.setProperty("current.date", new Date().toString().replace(" ", "_").replace(":", "_"));
			PropertyConfigurator
					.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

			System.setProperty("org.uncommons.reportng.escape-output", "false");

			try {
				inputStream = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(inputStream);
				logger.debug("Config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				inputStream = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(inputStream);
				logger.debug("OR file loaded");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
		
			if(System.getenv("browser") !=null && !System.getenv("browser").isEmpty())
			{
				browser=System.getenv("browser");
				System.out.println("From get Env: "+System.getenv("browser"));
				config.setProperty("browser", browser);
			}
		
			if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				logger.debug("Chrome Launched");
			}

			else if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				logger.debug("Firefox Launched");
			}

			else if (config.getProperty("browser").equals("opera")) {

				System.setProperty("webdriver.opera.driver", ".\\src\\test\\resources\\executables\\operadriver.exe");
				driver = new OperaDriver();
			}

			else if (config.getProperty("browser").equals("edge")) {

				System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\executables\\msedgedriver.exe");
				driver = new EdgeDriver();
			}

			else if (config.getProperty("browser").equals("iexplorer")) {

				System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			driver.get(config.getProperty("testsiteurl"));
			logger.debug("Navigating to " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit_wait"))));

		}

	}

	@AfterSuite
	public void tearDown() {

		try {
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (driver != null) {
			CaptureScreen.CaptureScreenShot();
			 driver.quit();
			logger.debug("Execution completed");
		}

	}

	public void click(String locator) {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "Clicked on " + locator);
		Reporter.log("Clicked on " + locator + " <br>");
		logger.debug("Clicked on " + locator + " <br>");
	}

	public void selectFromDropDown(String locator, String value) {

		WebElement dropdown = null;

		if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_CLASSNAME")) {
			dropdown = driver.findElement(By.className(OR.getProperty(locator)));
		} else if (locator.endsWith("_TAGNAME")) {
			dropdown = driver.findElement(By.tagName(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(LogStatus.INFO, "Selecting from Dropdown- " + locator + " & selected value is " + value);
		Reporter.log("Selecting from Dropdown- " + locator + " & selected value is " + value + "<br>");
		logger.debug("Selecting from Dropdown- " + locator + " & selected value is " + value + " <br>");
	}

	public void clearTextBox(String locator) {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).clear();
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).clear();
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).clear();
		}

		test.log(LogStatus.INFO, "Clearing field from " + locator);
		Reporter.log("Clearing field from " + locator + " <br>");
		logger.debug("Clearing field from " + locator + " <br>");
	}

	public void typeIn(String locator, String value) {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(LogStatus.INFO, "Typed in " + locator + " & entered value is " + value);
		Reporter.log("Typed in " + locator + " & entered value is " + value + " <br>");
		logger.debug("Typed in " + locator + " & entered value is " + value + " <br>");
	}

	public Boolean IsElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void readFromTable(String locatorForHeadRow, String LocatorForTableRow, String LocatorforCol) {

		int rows, cols;
		String sheetName = "testDeleteCustomer";

		if (excel.isSheetExist(sheetName)) {
			excel.removeSheet(sheetName);
		}
		excel.addSheet(sheetName);

		rows = driver.findElements(By.xpath(LocatorForTableRow)).size();
		cols = driver.findElements(By.xpath(LocatorforCol)).size();

		boolean flag = true;

		String colName = "";
		for (int row = 1; row <= rows; row++) {

			if (flag) {
				for (int col = 1; col <= cols; col++) {
					String Headdata = (driver.findElement(By.xpath((locatorForHeadRow) + "/td[" + col + "]"))
							.getText());
					excel.addColumn(sheetName, Headdata);

				}
				flag = false;
			}

			for (int col = 1; col <= cols; col++) {

				if (col < cols) {
					String cellData = driver
							.findElement(By.xpath((LocatorForTableRow) + "[" + row + "]/td[" + col + "]")).getText();

					if (col == 1)
						colName = "First Name";
					else if (col == 2)
						colName = "Last Name";
					else if (col == 3)
						colName = "Post Code";
					else if (col == 4)
						colName = "Account Number";

					System.out.println(cellData);
					excel.setCellData(sheetName, colName, row + 1, cellData);
				}

				else if (col == cols) {

					int num = (int) (Math.random() * 10);
					System.out.println(num);

					if (num <= 5)
						excel.setCellData(sheetName, "Delete Customer", row + 1, "Y");
					else
						excel.setCellData(sheetName, "Delete Customer", row + 1, "N");

				}

			}

		}

	}

	public void deleteCusFromTable(String LocatorForTableRow, String firstName, String lastName) {

		int rows;

		rows = driver.findElements(By.xpath(LocatorForTableRow)).size();

		for (int row = 1; row <= rows; row++) {

			String fName = driver.findElement(By.xpath((LocatorForTableRow) + "[" + row + "]/td[1]")).getText();
			String lName = driver.findElement(By.xpath((LocatorForTableRow) + "[" + row + "]/td[2]")).getText();
			if (fName.equals(firstName) || lName.equals(lastName)) {
				WebElement deleteBtn = driver
						.findElement(By.xpath((LocatorForTableRow) + "[" + row + "]/td[5]/button"));
				
				Actions actions = new Actions(driver);
				actions.moveToElement(deleteBtn).click().perform();
				
				test.log(LogStatus.INFO, "Deleted customer " + firstName +" " +lastName);
				Reporter.log("Deleted customer " + firstName +" " +lastName + " <br>");
				logger.debug("Deleted customer " + firstName +" " +lastName + " <br>");
				
				break;
			}
			
		}

	}

}
