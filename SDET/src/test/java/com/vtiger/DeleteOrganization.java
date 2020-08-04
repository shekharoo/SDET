package com.vtiger;

import java.io.IOException;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.FileLib;
import com.vtiger.genericUtils.WebDriverUtils;

public class DeleteOrganization {
	
	
	public WebDriver driver;
	String pwh;
	
	WebDriverUtils wdu = new WebDriverUtils();
	
	/**
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws SQLException 
	 */
	@Test
	public void test_001() throws Throwable
	{
		//Step 1:
		//Open browser and apply implicit wait
		driver = wdu.openBrowser("chrome");
		wdu.openUrl(FileLib.readPropertyFile("url"));
		wdu.testLoad();
		
		//Step 2:
		//Enter username and password.
		driver.findElement(By.name("user_name")).sendKeys(FileLib.readPropertyFile("username"));
		driver.findElement(By.name("user_password")).sendKeys(FileLib.readPropertyFile("password"));
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3:
		//click on Organization tab
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		String orgName = FileLib.readPropertyFile("orgName")+WebDriverUtils.randomFunc();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Select Industry and Type
		WebElement industry = driver.findElement(By.name("industry"));
		WebElement accType = driver.findElement(By.name("accounttype"));
		wdu.dropDownSelect(industry,FileLib.readPropertyFile("industry") );
		wdu.dropDownSelect(accType, FileLib.readPropertyFile("acc_Type"));
		
		//Click on Save
		driver.findElement(By.name("button")).click();
		
		//Step 4:
		//verfiy if organization name is same
		String actOrgName = driver.findElement(By.className("dvHeaderText")).getText();
		
		Assert.assertTrue(actOrgName.contains(orgName));
		
		//Step 5:
		//search for organization created
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		WebElement orgDrpDownSrch = driver.findElement(By.id("bas_searchfield"));
		wdu.dropDownSelect(orgDrpDownSrch, FileLib.readPropertyFile("orgDrpDownText"));
		
		//click on search button
		driver.findElement(By.name("submit")).click();
		
		//Step 6:
		//select checkbox to delete
		WebElement orgEle = driver.findElement(By.linkText(orgName));
		WebElement chkbox = driver.findElement(By.xpath("//input[@name='selected_id']"));
		wdu.waitAndClickElement(driver, orgEle, chkbox);
		//chkbox.click();
		
		//click on delete button
		driver.findElement(By.linkText("del")).click();
		
		//accept alert pop up
		wdu.driverWaitAlert(driver);
		wdu.popUpAccept(driver);
		
		//Step 7:
		//verify organization is deleted
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		WebElement orgDrpDownSrch1 = driver.findElement(By.id("bas_searchfield"));
		wdu.dropDownSelect(orgDrpDownSrch1, FileLib.readPropertyFile("orgDrpDownText"));
				
		//click on search button
		driver.findElement(By.name("submit")).click();
		
		//Step 8:
		//capture no oraganization text
		String actOrgMsg = driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
		Assert.assertTrue(actOrgMsg.contains(FileLib.readPropertyFile("expOrgMsg")));
		System.out.println("Message is: "+actOrgMsg + " with given org search: "+ orgName);
		
		//Step 9:
		//Mouse hover for logout
		WebElement mouseHovEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wdu.mouseHover(driver, mouseHovEle);
		driver.findElement(By.linkText("Sign Out")).click();
	}


}
