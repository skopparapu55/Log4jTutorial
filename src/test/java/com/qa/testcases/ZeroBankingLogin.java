package com.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ZeroBankingLogin {
	
	public WebDriver driver;
	public Logger log=Logger.getLogger(ZeroBankingLogin.class);
	
	@BeforeMethod
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "D:\\work\\chromedriver_win32\\chromedriver.exe");
		 driver=new ChromeDriver();
		driver.get("http://zero.webappsecurity.com/login.html");
		log.info("hit the url");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void verifyLogin()
	{
		driver.findElement(By.id("user_login")).sendKeys("username");
		log.debug("username is enterted");
		driver.findElement(By.id("user_password")).sendKeys("password");
		log.debug("Password is enterted");
		driver.findElement(By.name("submit")).click();
		log.info("hit the submitt btn");
		String actual=driver.getTitle();
		String expected="Zero - Account Summary";
		
		Assert.assertEquals(actual, expected,"Title does not match");
		log.error("This is error log");
		}
	@Test
	public void verifyLogo()
	{
		WebElement e=driver.findElement(By.linkText("Zero Bank"));
		Assert.assertTrue(e.isDisplayed());
	}
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}

}
