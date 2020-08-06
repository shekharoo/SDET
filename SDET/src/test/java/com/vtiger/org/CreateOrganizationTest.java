package com.vtiger.org;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.objectrepositorylib.CreateOrganization;
import com.vtiger.objectrepositorylib.Home;
import com.vtiger.objectrepositorylib.Organizations;

public class CreateOrganizationTest extends BaseClass {
	
	
/**
 * @author SHEKHAR
 * @throws Throwable
 */
	Home h = null;
	Organizations o = null;
	CreateOrganization co = null;	
		@Test
	public void createOrgWithTypeTest() throws Throwable
	{	

		//test script specific data
		String orgName = excelLib.getExcelData("org", 1, 2)+ "_"+ wdu.randomFunc();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);
		
		h = new Home(driver);
		co = new CreateOrganization(driver);
		o = new Organizations(driver);
		 
		 //Step 3:
		//click on Organization Tab
		h.getOrgLink().click();
		o.getCreateOrgBtn().click();
		co.orgName(orgName);
		
		//Enter org name Select Industry and Type
		co.selectInd(org_industry);
		co.selectAcc(org_Type);
		
		//Step 4:
		//Click on Save
		co.clickSave();
		
		//Step 5:
		//verify if organization name is same
		String actOrgName = co.verifyOrg();
		
		Assert.assertTrue(actOrgName.contains(orgName),"Organization name does not Verified");
	}
		@Test
		public void createOrgTest() throws Throwable
		{
			//test script specific data
			String orgName = excelLib.getExcelData("org", 1, 2)+ "_"+ wdu.randomFunc();
			h = new Home(driver);
			co = new CreateOrganization(driver);
			o = new Organizations(driver);
			//Step 3:
			//click on Organization Tab
			h.getOrgLink().click();
			o.getCreateOrgBtn().click();
			co.orgName(orgName);
			//Step 4:
			//Click on Save
			co.clickSave();
			
			//Step 5:
			//verify if organization name is same
			String actOrgName = co.verifyOrg();
			
			Assert.assertTrue(actOrgName.contains(orgName),"Organization name does not Verified");
		}


}

