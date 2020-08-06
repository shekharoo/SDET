//
//
//package com.vtiger.contact;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import com.vtiger.genericUtils.BaseClass;
//import com.vtiger.genericUtils.FileLib;
//import com.vtiger.genericUtils.WebDriverUtils;
//
//public class DeleteContactwithOrganizationTest extends BaseClass{
//	
//	String pwh;
//	
//	/**
//	 * @author SHEKHAR
//	 * @throws Throwable
//	 */
//	@Test
//	public void test_001() throws Throwable
//	{
//		/* read test script specific data*/
//		 //String orgName = excelLib.getExcelData("Org", 1, 2)+"_"+WebDriverUtils.randomFunc();
//		 String org_Type = excelLib.getExcelData("Org", 1, 3);
//		 String org_industry = excelLib.getExcelData("Org", 1, 4);
//		//Step 3:
//		//click on Organization tab
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
//		//driver.findElement(By.name("accountname")).sendKeys(orgName);
//		
//		//Select Industry and Type
//		WebElement industry = driver.findElement(By.name("industry"));
//		WebElement accType = driver.findElement(By.name("accounttype"));
//		wdu.dropDownSelect(industry,org_industry);
//		wdu.dropDownSelect(accType, org_Type);
//		
//		//Click on Save
//		driver.findElement(By.name("button")).click();
//		
//		//Step 4:
//		//verfication if organization name is same
//		String actOrgName = driver.findElement(By.className("dvHeaderText")).getText();
//		
//		Assert.assertTrue(actOrgName.contains(orgName));
//		
//		//Step 5:
//		//Click on Contacts link to create new Contact
//		driver.findElement(By.linkText("Contacts")).click();
//				
//		//Click on new Contact button
//		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
//		
//		//Step 6:
//		//Select Title
//		WebElement salutation = driver.findElement(By.name("salutationtype"));
//		wdu.dropDownSelect(salutation, FileLib.readPropertyFile("salutation"));
//				
//		//Enter name
//		driver.findElement(By.name("firstname")).sendKeys(FileLib.readPropertyFile("firstName"));
//				
//		//Enter last name
//		driver.findElement(By.name("lastname")).sendKeys(FileLib.readPropertyFile("lastName"));
//				
//		//Get Parent Window Handle
//		pwh = driver.getWindowHandle();
//		
//		//Click on organization name lookup
//		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
//				
//		//Switch driver control to Organization look up pop-up/window.
//		wdu.switchWindow(orgName);
//				
//		//Click on search button
//		driver.findElement(By.name("search")).click();
//				
//		//Click on search link
//		//WebElement srchLink = driver.findElement(By.xpath("//form[@name='selectall']/table[1]/tbody/tr[2]/td/div/table/tbody/tr[2]/td[1]/a"));
//		driver.findElement(By.linkText(orgName)).click();
//			    
//		//Return to parent window
//		wdu.switchWindowParent(pwh);
//				
//		//Select Lead Source
//		WebElement leadsrc = driver.findElement(By.name("leadsource"));
//		wdu.dropDownSelect(leadsrc, FileLib.readPropertyFile("leadsrc"));
//				
//		//Click on Save button, to save Contact
//		driver.findElement(By.name("button")).click();
//		
//		//Step 7:
//		//Verify if contact name is same
//		WebElement contactEle = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
//		wdu.verifyTitle(contactEle, FileLib.readPropertyFile("expContactName"));
//		
//		//Step 8:
//		//delete contact
//		driver.findElement(By.linkText("Contacts")).click();
//		driver.findElement(By.name("search_text")).sendKeys(orgName);
//		WebElement orgDrpDownSrch = driver.findElement(By.id("bas_searchfield"));
//		wdu.dropDownSelect(orgDrpDownSrch, FileLib.readPropertyFile("orgDrpDownText"));
//		
//		//Step 9:
//		//click on search button
//		driver.findElement(By.name("submit")).click();
//		
//		//select checkbox to delete using organization
//		WebElement orgContactEle = driver.findElement(By.linkText(orgName));
//		WebElement chkbox = driver.findElement(By.xpath("//input[@name='selected_id']"));
//		wdu.waitAndClickElement(driver, orgContactEle, chkbox);
//		//chkbox.click();
//		
//		//click on delete button
//		driver.findElement(By.linkText("del")).click();
//		
//		//accept alert pop up
//		wdu.driverWaitAlert(driver);
//		wdu.popUpAccept(driver);
//		
//		//Step 10:
//		//verify contact is deleted
//		driver.findElement(By.linkText("Contacts")).click();
//		driver.findElement(By.name("search_text")).sendKeys(orgName);
//		WebElement orgDrpDownSrch1 = driver.findElement(By.id("bas_searchfield"));
//		wdu.dropDownSelect(orgDrpDownSrch1, FileLib.readPropertyFile("orgDrpDownText"));
//				
//		//click on search button
//		driver.findElement(By.name("submit")).click();
//		
//		//Step 11:
//		//capture no contact text
//		String actContactMsg = driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
//		Assert.assertTrue(actContactMsg.contains(FileLib.readPropertyFile("expContactMsg")));
//		System.out.println("Message is: "+ actContactMsg + " with given contact search: "+ FileLib.readPropertyFile("firstName"));
//		
//		//Step 12:
//		//search for organization created
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.name("search_text")).sendKeys(orgName);
//		WebElement orgDrpDownSrch2 = driver.findElement(By.id("bas_searchfield"));
//		wdu.dropDownSelect(orgDrpDownSrch2, FileLib.readPropertyFile("orgDrpDownText"));
//		
//		//click on search button
//		driver.findElement(By.name("submit")).click();
//		
//		//select checkbox to delete
//		WebElement orgEle = driver.findElement(By.linkText(orgName));
//		WebElement chkbox1 = driver.findElement(By.xpath("//input[@name='selected_id']"));
//		wdu.waitAndClickElement(driver, orgEle, chkbox1);
//		//chkbox.click();
//		
//		//click on delete button
//		driver.findElement(By.linkText("del")).click();
//		
//		//accept alert pop up
//		wdu.driverWaitAlert(driver);
//		wdu.popUpAccept(driver);
//		
//		//Step 13:
//		//verify organization is deleted
//		driver.findElement(By.linkText("Organizations")).click();
//		driver.findElement(By.name("search_text")).sendKeys(orgName);
//		WebElement orgDrpDownSrch3 = driver.findElement(By.id("bas_searchfield"));
//		wdu.dropDownSelect(orgDrpDownSrch3, FileLib.readPropertyFile("orgDrpDownText"));
//				
//		//click on search button
//		driver.findElement(By.name("submit")).click();
//		
//		//Step 14:
//		//capture no oraganization text
//		String actOrgMsg = driver.findElement(By.xpath("//span[@class='genHeaderSmall']")).getText();
//		Assert.assertTrue(actOrgMsg.contains(FileLib.readPropertyFile("expOrgMsg")));
//		System.out.println("Message is: "+actOrgMsg + " with given org search: "+ orgName);
//
//	}
//
//
//}
