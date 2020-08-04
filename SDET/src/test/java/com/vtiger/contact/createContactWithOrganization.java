package com.vtiger.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.genericUtils.FileLib;
import com.vtiger.genericUtils.WebDriverUtils;

public class createContactWithOrganization extends BaseClass{
	
	String pwh;
	
	//WebDriverUtils wdu = new WebDriverUtils();
	
/**
 * @author SHEKHAR
 * @throws Throwable
 */
	@Test
	public void test_01() throws Throwable
	{
		/* read test script specific data*/
		 String orgName = excelLib.getExcelData("Org", 1, 2)+"_"+WebDriverUtils.randomFunc();
		 String org_Type = excelLib.getExcelData("Org", 1, 3);
		 String org_industry = excelLib.getExcelData("Org", 1, 4);
		 
		 //Step 3:
		//click on Organization Tab
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Select Industry and Type
		WebElement industry = driver.findElement(By.name("industry"));
		WebElement accType = driver.findElement(By.name("accounttype"));
		wdu.dropDownSelect(industry,org_industry );
		wdu.dropDownSelect(accType, org_Type);
		
		//Click on Save
		driver.findElement(By.name("button")).click();
		
		//Step 4:
		
		//verfication if organization name is same
		WebElement orgEle = driver.findElement(By.className("dvHeaderText"));
		wdu.verifyTitle(orgEle, orgName);
	
		//Step 5:
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
		//To remove indexing
		//Click on organization name lookup
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//Switch driver control to Organization look up pop-up/window.
		wdu.switchWindow(orgName);
		
		//Click on search button
		driver.findElement(By.name("search")).click();
		
		//Click on search link
	    //WebElement srchLink = driver.findElement(By.xpath("//form[@name='selectall']/table[1]/tbody/tr[2]/td/div/table/tbody/tr[2]/td[1]/a"));
		driver.findElement(By.linkText(orgName)).click();
	    
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
		

	}

}
