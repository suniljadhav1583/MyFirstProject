package com.inetBanking.testCases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetBanking.baseClass.BaseClass;
import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginPage_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Entered username");

		lp.setPassword(password);
		logger.info("Entered password");

		lp.clickSubmit();
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login Test Passed");

		} else {
			captureScreen(driver,  "Login Test");
			Assert.assertTrue(false);
			logger.info("Login Test Failed");
			
		}
	}

}
