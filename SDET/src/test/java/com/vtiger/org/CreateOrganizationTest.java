package com.vtiger.org;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.genericUtils.FileLib;
import com.vtiger.objectrepositorylib.CreateOrganization;
import com.vtiger.objectrepositorylib.Home;

public class CreateOrganizationTest extends BaseClass {
	
	String pwh;
/**
 * @author SHEKHAR
 * @throws Throwable
 */
	@Test
	public void test_01() throws Throwable
	{	
		Home h = new Home(driver);
		CreateOrganization co = new CreateOrganization(driver);
		 
		 //Step 3:
		//click on Organization Tab
		h.clickOrgLink();
		co.createOrg();
		String orgName = co.orgName(FileLib.readPropertyFile("orgName")+"_"+wdu.randomFunc());
		
		//Enter org name Select Industry and Type
		co.selectInd(FileLib.readPropertyFile("industry"));
		co.selectAcc(FileLib.readPropertyFile("acc_Type"));
		
		//Step 4:
		//Click on Save
		co.clickSave();
		
		//Step 5:
		//verfication if organization name is same
		String actOrgName = co.verifyOrg();
		
		Assert.assertTrue(actOrgName.contains(orgName));

	}

}
