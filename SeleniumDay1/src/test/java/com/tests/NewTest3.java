package com.tests;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class NewTest3 {
  @Test
  public void keyPress() throws InterruptedException {
	  
	  System.setProperty("webdriver.chrome.driver",".\\chromedriver\\chromedriver.exe");
	  WebDriver driver = new ChromeDriver();
	  driver.get("https://www.cleartrip.com/");
	  driver.manage().window().maximize();
	 
	  WebElement from = driver.findElement(By.id("FromTag"));
	  Actions acc = new Actions(driver);
	  acc.keyDown(from,Keys.SHIFT).perform();
	  acc.sendKeys("h").perform();
	  Thread.sleep(3000);
	  acc.keyDown(Keys.SHIFT).release();
	  acc.sendKeys("y").perform();
	  Thread.sleep(3000);
	  acc.sendKeys("d").perform();
	  Thread.sleep(5000);
	  acc.sendKeys(Keys.ENTER).perform();
	  
	  WebElement to = driver.findElement(By.id("ToTag"));
	  to.sendKeys("b");
	  to.sendKeys("a");
	  to.sendKeys("n");
	  WebDriverWait wait = new WebDriverWait(driver,10);
	  wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.id("ui-id-2"), By.tagName("li")));
	  WebElement ul = driver.findElement(By.id("ui-id-2"));
	  List<WebElement> list = ul.findElements(By.tagName("li"));
	  for (WebElement e : list) {
		 if (e.getText().contains("BKK")) {
			 e.click();
			 break;
		 }
	  }  
	  //driver.close();
  }
}
