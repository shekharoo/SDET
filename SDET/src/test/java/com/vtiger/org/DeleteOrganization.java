package com.vtiger.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.genericUtils.FileLib;
import com.vtiger.genericUtils.WebDriverUtils;

public class DeleteOrganization extends BaseClass{
	
	String pwh;
	
    /**
     * @author SHEKHAR
     * @throws Throwable
     */
	@Test
	public void test_001() throws Throwable
	{
		/* read test script specific data*/
		 String orgName = excelLib.getExcelData("Org", 1, 2)+"_"+WebDriverUtils.randomFunc();
		 String org_Type = excelLib.getExcelData("Org", 1, 3);
		 String org_industry = excelLib.getExcelData("Org", 1, 4);
		 
		 //Step 3:
		//click on Organization Tab
		
		//Step 3:
		//click on Organization tab
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		//Select Industry and Type
		WebElement industry = driver.findElement(By.name("industry"));
		WebElement accType = driver.findElement(By.name("accounttype"));
		wdu.dropDownSelect(industry, org_industry);
		wdu.dropDownSelect(accType, org_Type);
		
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

	}


}
