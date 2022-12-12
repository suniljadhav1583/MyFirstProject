package com.inetBanking.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetBanking.baseClass.BaseClass;
import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Provide username ");
		lp.setPassword(password);
		logger.info("Provide password");
		lp.clickSubmit();

		Thread.sleep(3000);

		AddCustomerPage addcust = new AddCustomerPage(driver);
		addcust.clickAddNewCustomer();

		addcust.custName("Sunil");
		addcust.custGender("male");
		addcust.custdob("10", "12", "1995");
		Thread.sleep(3000);
		addcust.custAdress("India");
		addcust.custCity("Pune");
		addcust.custstate("Punjab");
		addcust.custpinno("500467");
		addcust.custelephoneno("7009726161");
		String email = randomString() + "@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		logger.info("Provided customer detailes");
		Thread.sleep(5000);
		addcust.custSubmit();
		Thread.sleep(5000);

		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		Thread.sleep(5000);
		if (res == true) {
			Assert.assertTrue(true);logger.info("Test case pass");
		} else {
			captureScreen(driver, "addNewCustomer");
			Assert.assertTrue(false);logger.info("Test case failed");
		}
	}

	
}
