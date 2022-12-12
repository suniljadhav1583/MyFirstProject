package com.inetBanking.baseClass;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	ReadConfig readConfig = new ReadConfig();

	public String baseUrl = readConfig.getApplicationURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		logger = Logger.getLogger(BaseClass.class);
		PropertyConfigurator.configure("log4j.properties");

		if (br.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (br.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot is taken");
	}
	public static String randomString() {
		String genratedString = RandomStringUtils.randomAlphabetic(8);
		return (genratedString);
	}

	public static String randomNum() {
		String genratedString2 = RandomStringUtils.randomNumeric(4);
		return (genratedString2);
	}


}
