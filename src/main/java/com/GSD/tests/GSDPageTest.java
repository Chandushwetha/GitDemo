package com.GSD.tests;

import org.testng.annotations.Test;

import com.GSD.base.BaseTestClass;
import com.GSD.pages.GSDPage;

public class GSDPageTest extends BaseTestClass {
	
	@Test
	public static void gsdPageTest() throws Exception {
		GSDPage.display();
	}
}
