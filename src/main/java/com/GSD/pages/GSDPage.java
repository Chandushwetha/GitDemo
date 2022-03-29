package com.GSD.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.GSD.base.BaseTestClass;
import com.aventstack.extentreports.Status;

public class GSDPage extends BaseTestClass {
	
	/************************************Handling multiple window***************************/
	public static void windowHandle() {
		Set<String> windowIDs = driver.getWindowHandles();
	     Iterator<String> itr = windowIDs.iterator();
	     String HomePageID = itr.next();
	     String GSDPageID = itr.next();
	     
	     driver.switchTo().window(GSDPageID);
	}
	
	/*********** validate title ***************/
	public static void validatePage() {
		
		String actual ="OneCognizant";
		String excpected = driver.getTitle();
		Assert.assertEquals(actual,excpected);
		extentReportManager();
		exttest = report.createTest("GSD Page Validation");
		exttest.log(Status.PASS,"One Cognizant's Live Support (GSD) Page is validated");
		
	}
	
	/**********************************Switching frame*********************************/
	public static void iFrame() {
		driver.switchTo().frame(driver.findElement(By.id("appFrame")));
	}
	
	/*********************Selecting random country from the drop down list********************************/
	public static void selectCountry()throws Exception
	{
		exttest = report.createTest("Selecting random country from the country drop down list");
		windowHandle();
		iFrame();
		exttest.log(Status.INFO,"Control switched to 'appFrame' frame");
		WebElement countryDropDown = driver.findElement(By.id("menu4"));
		countryDropDown.click();
		exttest.log(Status.INFO,"Country drop down clicked");
		List<WebElement> options = driver.findElements(By.xpath("//*[@id='hamBarCollapseContainer']/div/div[2]/ul/li"));
		for(int i=0;i<options.size();i++) 
		{
			String str=(options.get(i).getText()).toString();
			if(str.equalsIgnoreCase("India"))
			{
				options.remove(i);
				break;
			}
		}
		Random rand=new Random();
		int list=rand.nextInt(options.size());
		WebElement country = options.get(list);
		String countryName = country.getText();
		country.click();
		String actualCountry = countryDropDown.getText().trim();
		Assert.assertEquals(actualCountry, countryName);
		exttest.log(Status.PASS,"The Country displayed is same as the country selected");
		System.out.println("The country selected is "+countryName);
		exttest.log(Status.INFO, "The country selected is "+countryName);
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].setAttribute('style','background:yellow; border:3px solid red;');", countryDropDown); 
		
		String path = getScreenShot("GSDPage");
		exttest.addScreenCaptureFromPath(path);
	}
	
	/******************************Extracting and displaying elements based on user input****************************/
	public static void display() throws Exception {
		windowHandle();
		validatePage();
		iFrame();
		selectCountry();
		exttest = report.createTest("Extracting and displaying the contents displayed on the screen");
		int num=1;
		
		By ITInfra = By.xpath("//*[@id='ui-view']/div/div[4]/div/div/div[1]/div/div[1]");
		By HR = By.xpath("//*[@id='ui-view']/div/div[4]/div/div/div[2]/div/div[1]");
		By ITApp = By.xpath("//*[@id='ui-view']/div/div[4]/div/div/div[3]/div/div[1]");
		By Policy = By.xpath("//*[@id='ui-view']/div/div[4]/div/div/div[4]/div/div[1]");	
		
		String ITInfra1 = driver.findElement(ITInfra).getText();
		String HR1 = driver.findElement(HR).getText();
		String ITApp1 = driver.findElement(ITApp).getText();
		String Policy1 = driver.findElement(Policy).getText();
		
			System.out.println("\n1. "+ITInfra1);
			System.out.println("2. "+HR1);
			System.out.println("3. "+ITApp1);
			System.out.println("4. "+Policy1);
			System.out.println("5. All");
			System.out.println("Enter your choice:");
			String choice = prop.getProperty("Choice");
			System.out.println("You chose : " +choice);
			
			exttest.log(Status.INFO,"User menu is displayed and user input is asked");
			
		 
			switch(choice) { 
				case "1": {
						exttest.log(Status.INFO,"User entered 1: ("+ITInfra1+")");
						List<WebElement> tot = driver.findElements(By.xpath("//div[@id='ui-view']/div/div[4]/div/div/div[1]/div/div/div"));
						System.out.println("\n"+ ITInfra1);
						System.out.println("-------------------------");
						exttest.log(Status.INFO,"The contents displayed under "+ITInfra1+" are:");
						for(int i=0;i<tot.size();i++) {
							String str1=(tot.get(i).getText()).toString();
							System.out.println(str1);
							exttest.log(Status.INFO,str1);
						}
						break;
				}
				
				case "2": {
						exttest.log(Status.INFO,"User entered 2: ("+HR1+")");
						List<WebElement> tot = driver.findElements(By.xpath("//div[@id='ui-view']/div/div[4]/div/div/div[2]/div/div/div"));
						System.out.println("\n" +HR1);
						System.out.println("-------------------------");
						exttest.log(Status.INFO,"The contents displayed under "+HR1+" are:");
						for(int i=0;i<tot.size();i++) {
							String str1=(tot.get(i).getText()).toString();
							System.out.println(str1);
							exttest.log(Status.INFO,str1);
						}
						break;
				}
				
				case "3": {
						exttest.log(Status.INFO,"User entered 3: ("+ITApp1+")");
						List<WebElement> tot = driver.findElements(By.xpath("//div[@id='ui-view']/div/div[4]/div/div/div[3]/div/div/div"));
						System.out.println("\n" +ITApp1);
						System.out.println("-------------------------");
						exttest.log(Status.INFO,"The contents displayed under "+ITApp1+" are:");
						for(int i=0;i<tot.size();i++) {
							String str1=(tot.get(i).getText()).toString();
							System.out.println(str1);
							exttest.log(Status.INFO,str1);
						}
						break;
				}
				
				case "4": {
						exttest.log(Status.INFO,"User entered 4: ("+Policy1+")");
						List<WebElement> tot = driver.findElements(By.xpath("//div[@id='ui-view']/div/div[4]/div/div/div[4]/div/div/div"));
						System.out.println("\n" +Policy1);
						System.out.println("-------------------------");
						exttest.log(Status.INFO,"The contents displayed under "+Policy1+" are:");
						for(int i=0;i<tot.size();i++) {
							String str1=(tot.get(i).getText()).toString();
							System.out.println(str1);
							exttest.log(Status.INFO,str1);
						}
						break;
				}
				
				case "5": {
					exttest.log(Status.INFO,"User entered 5: All");
					while(num<5) {
						String title = driver.findElement(By.xpath("//div[@id='ui-view']/div/div[4]/div/div/div["+num+"]/div/div")).getText();
						List<WebElement> tot = driver.findElements(By.xpath("//div[@id='ui-view']/div/div[4]/div/div/div["+num+"]/div/div/div"));
						System.out.println("\n"+title);
						System.out.println("-------------------------");
						exttest.log(Status.INFO,"The contents displayed under "+title+" are:");
						for(int i=0;i<tot.size();i++) {
							String str1=(tot.get(i).getText()).toString();
							System.out.println(str1);
							exttest.log(Status.INFO,str1);
						}
						num++;
					}

				}
				
				default: System.out.println("Please enter a valid number!!!");
			} 
			
		exttest.log(Status.PASS,"Contents from the website extracted and displayed successfully");
		
	}

}

