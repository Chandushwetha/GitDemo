package com.GSD.mainTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.GSD.base.BaseTestClass;
import com.GSD.tests.GSDPageTest;
import com.GSD.tests.HomePageTest;

public class MainGSDTest extends BaseTestClass {
		
		static WebDriver driver;
		HomePageTest homePage = new HomePageTest();
		GSDPageTest gsdPage = new  GSDPageTest();

		@Test(priority=1)
		public void HomePage() throws Exception {
			driver = invokeBrowser();
			gsdPage = homePage.homePageTest();
		
		}
		
		@Test(priority=2)
		public void GSDPage() throws Exception {
			GSDPageTest.gsdPageTest();
			closeBrowser();
		}
}
