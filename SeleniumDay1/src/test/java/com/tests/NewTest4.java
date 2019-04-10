package com.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class NewTest4 {
  @Test
  public void javascr() throws InterruptedException {
	  
	  System.setProperty("webdriver.chrome.driver",".\\chromedriver\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	  driver.get("https://www.hdfcbank.com/");
	  driver.manage().window().maximize();
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  js.executeScript("document.getElementById('netsafe').click()");
	  Thread.sleep(2000);
	  js.executeScript("window.scrollBy(0,10000)");
	  Thread.sleep(3000);
	  js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	  String title = (String) js.executeScript("return document.title"); 
	  System.out.println(title);
	  js.executeScript("alert('welcome to selenium')");
	driver.get("http://demowebshop.tricentis.com/login");
	js.executeScript("document.getElementById('Email').value = 'askmail@email.com'");
  }
}
