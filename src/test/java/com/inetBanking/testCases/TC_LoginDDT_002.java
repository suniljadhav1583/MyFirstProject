package com.inetBanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.baseClass.BaseClass;
import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pwd) {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided ");
		lp.setPassword(pwd);
		logger.info("password provided");
		lp.clickSubmit();

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login Failed");
		} else {

			Assert.assertTrue(true);
			logger.info("Login Passed");

			lp.clickLogout();

			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String xpath = System.getProperty("user.dir") + "/src/main/java/com/inetBanking/testData/inetBanking.xlsx";

		int rownum = XLUtils.getRowCount(xpath, "data");
		int colcount = XLUtils.getCellCount(xpath, "data", 1);

		String logindata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				logindata.clone()[i - 1][j] = XLUtils.getCellData(xpath, "data", i, j);
			}

		}
		return logindata;
	}
}
