package com.vtiger.org;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.genericUtils.WebDriverUtils;

public class createOrganizationTest extends BaseClass {
	
	String pwh;
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
		wdu.dropDownSelect(industry,org_industry);
		wdu.dropDownSelect(accType,org_Type);
		
		//Step 4:
		//Click on Save
		driver.findElement(By.name("button")).click();
		
		//Step 5:
		//verfication if organization name is same
		String actOrgName = driver.findElement(By.className("dvHeaderText")).getText();
		
		Assert.assertTrue(actOrgName.contains(orgName));

	}

}
