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

public class createOrganizationTest {
	
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
		//Step 1:
		//Open browser
		driver = wdu.openBrowser("chrome");
		wdu.openUrl(FileLib.readPropertyFile("url"));
		wdu.testLoad();
		
		//Step 2:
		//Find username textbox and password text box
		driver.findElement(By.name("user_name")).sendKeys(FileLib.readPropertyFile("username"));
		driver.findElement(By.name("user_password")).sendKeys(FileLib.readPropertyFile("password"));
		driver.findElement(By.id("submitButton")).click();
		
		//Step 3:
		//click on Organization Tab
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		String orgName = FileLib.readPropertyFile("orgName")+WebDriverUtils.randomFunc();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Select Industry and Type
		WebElement industry = driver.findElement(By.name("industry"));
		WebElement accType = driver.findElement(By.name("accounttype"));
		wdu.dropDownSelect(industry,FileLib.readPropertyFile("industry") );
		wdu.dropDownSelect(accType, FileLib.readPropertyFile("acc_Type"));
		
		//Step 4:
		//Click on Save
		driver.findElement(By.name("button")).click();
		
		//Step 5:
		//verfication if organization name is same
		String actOrgName = driver.findElement(By.className("dvHeaderText")).getText();
		
		Assert.assertTrue(actOrgName.contains(orgName));
	
		//Step 6:
		//Mouse hover for logout
		WebElement mouseHovEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wdu.mouseHover(driver, mouseHovEle);
		driver.findElement(By.linkText("Sign Out")).click();
	}

}
