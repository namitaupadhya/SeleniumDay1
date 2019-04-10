package com.tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class NewTest8 {
	
	WebDriver driver;
	
	@BeforeTest
	@Parameters ("node")
	public void beforeTest(String value) throws MalformedURLException {
		
		DesiredCapabilities ds = DesiredCapabilities.chrome();
		ds.setPlatform(Platform.ANY);
		driver = new RemoteWebDriver (new URL(value),ds);
		 driver.manage().window().maximize();
		 driver.get("http://demowebshop.tricentis.com/login");
	}
	
	@AfterTest
	public void afterTest() {
		//driver.close();
	}
	
	@Test(dataProvider = "dp1")
	public void testValidData(String username, String pwd) {
	  
		driver.findElement(By.id("Email")).sendKeys(username);
		driver.findElement(By.id("Password")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
		//driver.findElement(By.linkText("Log out")).click();
		driver.findElement(By.linkText("Log in")).click();
	}

  @DataProvider(name= "dp1")
  
  public Object[][] provideData() throws IOException {
	  return NewTest5.testDataExcelExtract();
	
  }
  

}
