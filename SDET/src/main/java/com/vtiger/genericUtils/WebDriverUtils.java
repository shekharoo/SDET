package com.vtiger.genericUtils;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
	public static WebDriver driver;
	/**
	 * It is used to open browser
	 * @author SHEKHAR
	 * @param browserName
	 * @return WebDriver
	 */
	public WebDriver openBrowser(String browserName)
	{
		if(browserName.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		return driver;
	}
	/**
	 * It is used to open url and maximize window
	 * @author SHEKHAR
	 * @param url
	 */
	public void openUrl(String url)
	{
		driver.manage().window().maximize();
		driver.get(url);
	}
    /**
     * It is used to apply implicit wait to DOM page. 
     * @author SHEKHAR
     */
	public void testLoad()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	/**
	 * It is used to generate random numbers. 
	 * @author SHEKHAR
	 * @return int
	 */
	public int randomFunc()
	{
		Random r = new Random();
		int no = r.nextInt(1000);
		return no;
	}
	/**
	 * It is sued to select drop down based on Text
	 * @author SHEKHAR
	 * @param element
	 * @param text
	 */
	public void dropDownSelect(WebElement element,String text)
	{
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	/**
	 * It is sued to select drop down based on Value
	 * @author SHEKHAR
	 * @param element
	 * @param value
	 */
	public void dropDownSelectByValue(WebElement element,String value)
	{
		Select s = new Select(element);
		s.selectByValue(value);
	}

	
	/**
	 * It is sued to apply explicit wait on a particular element.
	 * @author SHEKHAR
	 * @param driver
	 * @param element
	 */
	public void driverWait(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * It is used to apply wait explicit wait based on element's text.
	 * @author SHEKHAR
	 * @param driver
	 * @param element
	 */
	public void driverWaitCustom(WebDriver driver,WebElement element,String expText)
	{
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBePresentInElement(element, expText));
	}
	
	/**
	 * It is used to apply wait on an element and perform click operation on another element.
	 * @author SHEKHAR
	 * @param driver
	 * @param element
	 * @throws InterruptedException 
	 */
	public void waitAndClickElement(WebDriver driver,WebElement element,WebElement chkbox) throws InterruptedException
	{
		//boolean flag = false;
		int count=0;
		while(count<30)
		{
		try {
			if(element.isDisplayed())
			{
				chkbox.click();
			}
				
			//flag=true;
			break;
		} catch (Exception e) {
			count++;
			Thread.sleep(1000);
		 }
		}

	}
	
	/**
	 * It is used to apply wait on an element and perform click operation on same element.
	 * @author SHEKHAR
	 * @param driver
	 * @param element
	 * @throws InterruptedException 
	 */
	public void waitAndClickElement(WebDriver driver,WebElement element) throws InterruptedException
	{
		//boolean flag = false;
		int count=0;
		while(count<30)
		{
		try {
			if(element.isDisplayed())
			{
				element.click();
			}
				
			//flag=true;
			break;
		} catch (Exception e) {
			count++;
			Thread.sleep(1000);
		 }
		}

	}

	
	/**
	 * It is used to apply explicit wait on Alert
	 * @author SHEKHAR
	 * @param element
	 */
	public void driverWaitAlert(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	/**
	 * it is used to verify title based on expected title.
	 * @author SHEKHAR
	 * @param element
	 * @param expTitle
	 */
	public void verifyTitle(WebElement element, String expTitle)
	{
		String actTitle = element.getText();
		if(actTitle.contains(expTitle))
		{
			System.out.println("Expected title contains: "+ expTitle + " in actTitle" );
		}
		else
		{
			System.out.println("Actual title is: "+ actTitle);
		}
	}
		/**This is used to switch window
	 * @author SHEKHAR
	 * @param expPageTitle
	 */
	public void switchWindow(String expPageTitle) 
	{
		Set<String> whs = driver.getWindowHandles();
		for(String w:whs)
		{
			driver.switchTo().window(w);
			String currentPageTitle = driver.getTitle();
		    if(currentPageTitle.contains(expPageTitle))
		    {
		    	break;
		    }
	    }
		
	}
	/**
	 * This is used to perform Mouse Hover action
	 * @param driver
	 * @param element
	 * @author SHEKHAR
	 */
	public void mouseHover(WebDriver driver,WebElement element)
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * Used to accept Pop up
	 * @param driver
	 *@author SHEKHAR
	 */
	public void popUpAccept(WebDriver driver)
	{
		Alert a = driver.switchTo().alert();
		a.accept();
	}
	
	/**
	 * used to dismiss Pop up
	 * @param driver
	 * @author SHEKHAR
	 */
	public void popUpDismiss(WebDriver driver)
	{
		Alert a = driver.switchTo().alert();
		a.dismiss();
	}
}
