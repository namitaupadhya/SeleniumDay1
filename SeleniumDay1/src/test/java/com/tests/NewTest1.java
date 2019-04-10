package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTest1 {
  @Test
  public void test1() throws InterruptedException {
	  
	  System.setProperty("webdriver.chrome.driver",".\\chromedriver\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	  driver.get("https://www.hdfcbank.com/");
	  driver.manage().window().maximize();
	  Thread.sleep(2000);
	  //driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
	  
	  Actions acc = new Actions(driver);
	  acc.moveToElement(driver.findElement(By.xpath("//a[text()='Products']"))).perform();
	  acc.moveToElement(driver.findElement(By.xpath("//a[text()='Cards']"))).perform();
	  acc.moveToElement(driver.findElement(By.xpath("//a[text() = 'Credit Cards']"))).click().perform(); 
	  Assert.assertTrue(driver.getCurrentUrl().contains("Credit Cards"));
  }
}
