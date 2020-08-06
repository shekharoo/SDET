package com.vtiger.org;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.objectrepositorylib.CreateOrganization;
import com.vtiger.objectrepositorylib.Home;
import com.vtiger.objectrepositorylib.Organizations;

public class DeleteOrganizationTest extends BaseClass{
	
	String pwh;
	
    /**
     * @author SHEKHAR
     * @throws Throwable
     */
	Home h = null;
	Organizations o = null;
	CreateOrganization co = null;	
	@Test
	public void DeleteOrg() throws Throwable
	{
		//test script specific data
		String orgName = excelLib.getExcelData("org", 1, 2)+ "_"+ wdu.randomFunc();
		String org_Type = excelLib.getExcelData("org", 1, 3);
	    String org_industry = excelLib.getExcelData("org", 1, 4);
	    String srchType = excelLib.getExcelData("Contact",1 ,9);
	    String noOrgFound = excelLib.getExcelData("Contact",1 ,10);
	    
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

		
		//Step 6:
		//search for organization created
	    h.getOrgLink();
	    o.deleteOrg(orgName, srchType);
	    
		//Step 7:
		//verify organization is deleted
	    h.getOrgLink();
	    o.verifyDeleteOrg(orgName, srchType);
	    String actNoOrghText = o.getNoOrgText().getText();
	    Assert.assertTrue(actNoOrghText.contains(noOrgFound),"No Organization text does not found");
	}


}
