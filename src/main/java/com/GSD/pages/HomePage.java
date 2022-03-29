package com.GSD.pages;

import java.io.File;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.GSD.base.BaseTestClass;
import com.aventstack.extentreports.Status;

public class HomePage extends BaseTestClass {


	/*********** validate title ***************/
	public static void validatePage() {
		
		String actual ="Be.Cognizant";
		String excpected = driver.getTitle();
		Assert.assertEquals(actual,excpected);
		extentReportManager();
		exttest = report.createTest("Home Page Validation");
		exttest.log(Status.PASS,"Be.Cognizant Home Page is validated");
		
	}
	
	public static void userVerification() throws Exception {
		
		// Locate the excel sheet to be read
		File src = new File(System.getProperty("user.dir")+"\\Data\\TestData.xlsx");
		//FileInputStream stream = new FileInputStream(src);
		OPCPackage pkg;
		pkg = OPCPackage.open(src);
		XSSFWorkbook book = new XSSFWorkbook(pkg);
		XSSFSheet sheet = book.getSheet("UserDetails");
		String expectedUserName = sheet.getRow(1).getCell(0).getStringCellValue();
		
		exttest = report.createTest("Verifying the User");
		WebElement userName = driver.findElement(By.id("user-name"));
		String actualUserName = userName.getText();
		//String expectedName = prop.getProperty("userName");
		exttest.log(Status.INFO,"User Verfication in Progress");
		Assert.assertTrue(actualUserName.contains(expectedUserName), "User Verified");
		exttest.log(Status.PASS,"User Verfied Successfully");
		System.out.println("User Name : "+actualUserName);
		exttest.log(Status.INFO, "User Name is "+actualUserName);
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].setAttribute('style','background:yellow; border:3px solid red;');", userName);     

		
		String path = getScreenShot("HomePage");
		exttest.addScreenCaptureFromPath(path);
	}
	
	public static void searchGSD() throws Exception {
		
		validatePage();
		userVerification();
		exttest = report.createTest("Searching Live Support (GSD)");
		driver.findElement(By.id("searchbox")).sendKeys("GSD");
		driver.findElement(By.className("icomoon-search2")).click();
		exttest.log(Status.INFO, "Live Support (GSD) search is in progress ");
		driver.findElement(By.xpath("//span[text()='GSD']")).click();
		exttest.log(Status.PASS, "Search is successfull");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
