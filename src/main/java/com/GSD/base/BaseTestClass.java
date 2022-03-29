package com.GSD.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestClass {
	protected static WebDriver driver;
	public static Properties prop;
	public static ExtentHtmlReporter exthtml;
	public static ExtentTest exttest;
	public static ExtentReports report;
		
	/****************** Invoke Browser ***********************/
	public static WebDriver invokeBrowser() {

		try {
			prop = new Properties();
			try {
				prop.load(new FileInputStream("src\\main\\java\\com\\GSD\\utitlities\\config.properties")); // Loading the properties
			} catch (Exception e) {
				e.printStackTrace();
			}

			if(prop.getProperty("browserName").matches("chrome")) {
				//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if(prop.getProperty("browserName").matches("firefox"))  {
				//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if(prop.getProperty("browserName").matches("opera"))  {
				//System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\Drivers\\operadriver.exe");
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
			} else if(prop.getProperty("browserName").matches("edge"))  {
				//System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe");
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else {
				driver = new SafariDriver();
			}
		} catch (Exception e) {
			// reportFail(e.getMessage());
			System.out.println(e.getMessage());
		}
		

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		/*************** Navigating to URL ***********/
		String URL=(prop.getProperty("URL"));
		
		driver.get(URL);
		WebDriverWait wait=new WebDriverWait(driver,180);
		wait.until(ExpectedConditions.urlToBe(URL));
		return driver;
		
	}
	

	/******************** Generating Extent Report ***********/
	public static void extentReportManager() {
		if(report==null) {		
			exthtml = new ExtentHtmlReporter(System.getProperty("user.dir")
					+ "\\Reports\\ExtentReport.html");
			report = new ExtentReports();
			report.attachReporter(exthtml);
			
			report.setSystemInfo("OS","Windows 10");
			report.setSystemInfo("Environment","UAT");
			report.setSystemInfo("Browser",prop.getProperty("browserName"));
			
			exthtml.config().setDocumentTitle("UAT UI Automation Results");
			exthtml.config().setReportName("All HeadLines UI Test Report");
			exthtml.config().setTestViewChartLocation(ChartLocation.TOP);
			exthtml.config().setTimeStampFormat("MM ddd, yyyy HH:mm:ss");
			}
	}
	
	/************ Takes screenshot and store it in the specified path ***********/
	public static String getScreenShot(String fileName) throws IOException{
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir")+"\\Screenshots\\"+fileName+".jpeg";
		 try
		 {
			 FileUtils.copyFile(screenshot, new File(path));
		 }
		 catch(IOException e)
		 {
			 System.out.println(e.getMessage());
		 }
        return path;
    }
	
	/***************Close the Browser *******************/
	public static void closeBrowser() {
		report.flush();
		driver.quit();
	}

}
