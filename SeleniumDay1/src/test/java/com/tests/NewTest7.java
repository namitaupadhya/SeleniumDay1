package com.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.MarkUnsupportedException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class NewTest7 {
	
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest logger;
	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-ms");
		
		htmlreporter = new ExtentHtmlReporter(System.getProperty("user.dir")+ "/extent_reports/reports_" 
		 + sdf.format(new Date())+".html");
		reports = new ExtentReports();
		reports.attachReporter(htmlreporter);
		reports.setSystemInfo("host", "localHost Training");
		reports.setSystemInfo("username", "namita.koni");
		
		htmlreporter.config().setTheme(Theme.STANDARD);
		htmlreporter.config().setReportName("Report for UBS");
		
	}
	
	@Test
	public void testPass() {
		logger = reports.createTest("testpass");
		logger.log(Status.PASS,MarkupHelper.createLabel("Test pass", ExtentColor.GREEN));
	}
	
	@Test
	public void testFail() {
		logger = reports.createTest("testfail");
		 System.setProperty("webdriver.chrome.driver",".\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demowebshop.tricentis.com/login");
		driver.findElement(By.id("Email")).sendKeys("nami@sf.com");
		driver.findElement(By.id("Pass")).sendKeys("nami1234");
		//Assert.assertTrue(false);
	}
	@Test
	public void testSkip() {
		logger = reports.createTest("testskip");
		throw new SkipException("test is skipped");
	}
	
  @AfterMethod
  public void afterMethod(ITestResult result) {
	  if (result.getStatus()== ITestResult.FAILURE) {
		  
		  logger.log(Status.FAIL,MarkupHelper.createLabel("This test -> "+ result.getMethod().getMethodName(), ExtentColor.RED));
		  TakesScreenshot capture = (TakesScreenshot) driver;
		  File src = capture.getScreenshotAs(OutputType.FILE);
		  //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-ms");
		  String capturePath = System.getProperty("user.dir")+"/extend_reports/capture/"+result.getMethod().getMethodName()+".png";
		  try {
			  FileUtils.copyFile(src, new File(capturePath));
			  logger.addScreenCaptureFromPath(capturePath,"Demo Web Shop Screen");
			  logger.log(Status.FAIL, result.getThrowable().getMessage());
		  }
		  catch (IOException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  else if (result.getStatus()==ITestResult.SKIP){
		  logger.log(Status.SKIP,MarkupHelper.createLabel("This test-> "+ result.getMethod().getMethodName(), ExtentColor.AMBER));
	  } 
   }
  
  @AfterTest
  public void afterTest() {
	  reports.flush(); // writes the updated information to the html file
  }
  
}
