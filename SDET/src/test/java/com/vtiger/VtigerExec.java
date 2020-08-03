package com.vtiger;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.FileLib;
import com.vtiger.genericUtils.WebDriverUtils;

public class VtigerExec {
	
	public WebDriver driver;
	String pwh;
	WebDriverUtils wdu = new WebDriverUtils();
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws SQLException 
	 */
	@Test
	public void test_001() throws IOException, InterruptedException, SQLException
	{
		driver = wdu.openBrowser("chrome");
		wdu.openUrl(FileLib.readPropertyFile("url"));
		wdu.testLoad();
		driver.findElement(By.name("user_name")).sendKeys(FileLib.readPropertyFile("username"));
		driver.findElement(By.name("user_password")).sendKeys(FileLib.readPropertyFile("password"));
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(FileLib.readPropertyFile("orgName")+WebDriverUtils.randomFunc());
		
		//Select Industry and Type
		WebElement industry = driver.findElement(By.name("industry"));
		WebElement accType = driver.findElement(By.name("accounttype"));
		wdu.dropDownSelect(industry,FileLib.readPropertyFile("industry") );
		wdu.dropDownSelect(accType, FileLib.readPropertyFile("acc_Type"));
		
		//Click on Save
		driver.findElement(By.name("button")).click();
		
		//verfication if organization name is same
		WebElement orgEle = driver.findElement(By.className("dvHeaderText"));
		wdu.verifyTitle(orgEle, FileLib.readPropertyFile("expOrgName"));
	
		//Click on Contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//Click on new Contact button
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Select Title
		WebElement salutation = driver.findElement(By.name("salutationtype"));
		wdu.dropDownSelect(salutation, FileLib.readPropertyFile("salutation"));
		
		//Enter name
		driver.findElement(By.name("firstname")).sendKeys(FileLib.readPropertyFile("firstName"));
		
		//Enter last name
		driver.findElement(By.name("lastname")).sendKeys(FileLib.readPropertyFile("lastName"));
		
		//Get Parent Window Handle
		pwh = driver.getWindowHandle();
		
		//Click on organization name lookup
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();
		
		//Switch driver control to Organization look up pop-up/window.
		wdu.switchWindow(FileLib.readPropertyFile("searchVal"));
		
		//Click on search button
		driver.findElement(By.name("search")).click();
		
		Thread.sleep(5000);
		//Click on 1st search link
	    WebElement srchLink = driver.findElement(By.xpath("//form[@name='selectall']/table[1]/tbody/tr[2]/td/div/table/tbody/tr[2]/td[1]/a"));
		srchLink.click();
	    
		//Return to parent window
		wdu.switchWindowParent(pwh);
		
		//Thread.sleep(5000);
		
		//Select Lead Source
		WebElement leadsrc = driver.findElement(By.name("leadsource"));
		wdu.dropDownSelect(leadsrc, FileLib.readPropertyFile("leadsrc"));
		
		//Click on Save button, to save Contact
		driver.findElement(By.name("button")).click();
		
		//Verify if contact name is same
		WebElement contactEle = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		wdu.verifyTitle(contactEle, FileLib.readPropertyFile("expContactName"));
		
		//Mouse hover for logout
		WebElement mouseHovEle = driver.findElement(By.xpath("(//td[@class='small'])[2]/img"));
		wdu.mouseHover(driver, mouseHovEle);
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
