package com.vtiger.genericUtils;

import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.vtiger.objectrepositorylib.Login;

public class BaseClass {
	   public DataBaseLib dbLib = new DataBaseLib();
	   public ExcelLib excelLib = new ExcelLib();
	   public FileLib fLib = new FileLib();
	   public WebDriverUtils wdu = new WebDriverUtils();
	   public Login lg;
	   public WebDriver driver = null;

	
	@BeforeSuite
	public void connectToDB()
	{
		//dbLib.establishConn(url, username, psswd);
	}
	
	/****For cross parallel test execution******/
//	@Parameters
//	@BeforeTest
//	public void parallelLaunch()
//	{
//		
//	}
	
	@BeforeClass
	public void launchBrowser() throws Throwable
	{
		/*Step 1: */
		//Open browser
		driver = wdu.openBrowser("chrome");
		wdu.openUrl(FileLib.readPropertyFile("url"));
		wdu.testLoad();
	}
	
	@BeforeMethod
	public void login() throws Throwable
	{
		/* Step 2: */
		//Find username textbox and password text box
		lg = new Login(driver);
		lg.loginApp();
	}
	
	@AfterMethod
	public void logOut()
	{
		//Mouse hover for logout
		WebElement mouseHovEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wdu.mouseHover(driver, mouseHovEle);
		driver.findElement(By.linkText("Sign Out")).click();
	}
	
	@AfterClass
	public void closeBrowser()
	{
		/*close the browser*/
		driver.close();
	}
	
	@AfterSuite
	public void disconnectToDb() throws SQLException
	{
		/*disconnect to db*/
		//dbLib.closeConn();
	}

}
