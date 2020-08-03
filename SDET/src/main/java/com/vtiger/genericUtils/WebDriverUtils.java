package com.vtiger.genericUtils;

import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * @author SHEKHAR
 *
 */
public class WebDriverUtils {
	
	/**
	 * 
	 */
	public static WebDriver driver;
	/**
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
		if(browserName.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		return driver;
	}
	/**
	 * @author SHEKHAR
	 * @param url
	 */
	public void openUrl(String url)
	{
		driver.manage().window().maximize();
		driver.get(url);
	}

	public void testLoad()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static int randomFunc()
	{
		Random r = new Random();
		int no = r.nextInt(1000);
		return no;
	}
	/**
	 * @author SHEKHAR
	 * @param element
	 * @param text
	 */
	public void dropDownSelect(WebElement element,String text)
	{
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	//handle drop down
	//handle pop-up
	//select data from drop down
	//Action class
	
	//wait for test load() to use implicitlywait and explicitly wait
	/**
	 * @author SHEKHAR
	 * @param element
	 * @param sec
	 */
//	public WebElement driverWait(WebDriver driver,WebElement element,long sec,String expLink)
//	{
//		WebDriverWait wait = new WebDriverWait(driver, sec);
//		//wait.until(ExpectedConditions.visibilityOf(element));
//		String actLink = element.getText();
//		System.out.println("Actual Link Text is: "+actLink);
//		wait.until(ExpectedConditions.refreshed(element));
//		return element;
//	}
	/**
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
	/**
	 * @author SHEKHAR
	 * @param pwh
	 */
	public void switchWindowParent(String pwh)
	{
		driver.switchTo().window(pwh);
		
	}
	/**This is used to switch window
	 * @author SHEKHAR
	 * @param expTitle
	 * @throws IOException
	 */
	public void switchWindow(String expTitle) throws IOException
	{
		String pwh = driver.getWindowHandle();
		Set<String> whs = driver.getWindowHandles();
		whs.remove(pwh);
		for(String w:whs)
		{
			driver.switchTo().window(w);
		    driver.findElement(By.name("search_text")).sendKeys(FileLib.readPropertyFile("searchVal"));
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
}
