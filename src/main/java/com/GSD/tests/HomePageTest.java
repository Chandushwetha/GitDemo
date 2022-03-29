package com.GSD.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.GSD.base.BaseTestClass;
import com.GSD.pages.HomePage;

public class HomePageTest extends BaseTestClass{

	@Test
	public GSDPageTest homePageTest() throws Exception {

		HomePage.searchGSD();
		return PageFactory.initElements(driver,GSDPageTest.class);
	}
}


