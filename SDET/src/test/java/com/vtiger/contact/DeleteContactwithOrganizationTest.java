package com.vtiger.contact;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.vtiger.genericUtils.BaseClass;
import com.vtiger.objectrepositorylib.ContactInfo;
import com.vtiger.objectrepositorylib.Contacts;
import com.vtiger.objectrepositorylib.CreateContact;
import com.vtiger.objectrepositorylib.CreateOrganization;
import com.vtiger.objectrepositorylib.Home;
import com.vtiger.objectrepositorylib.Organizations;

public class DeleteContactwithOrganizationTest extends BaseClass{

	/**
	 * @author SHEKHAR
	 * @throws Throwable
	 */
	Home h = null;
	Organizations o = null;
	CreateOrganization co = null;	
	Contacts c = null;
	CreateContact cc = null;
	ContactInfo ci = null;

	@Test
	public void test_001() throws Throwable
	{
		//test script specific data
		String orgName = excelLib.getExcelData("org", 1, 2)+ "_"+ wdu.randomFunc();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);
		String contactfName = excelLib.getExcelData("Contact",1 ,5);
		String contactlName = excelLib.getExcelData("Contact",1 ,6);
		String expPageTitle = excelLib.getExcelData("Contact",1 ,7);
		String homePageTitle = excelLib.getExcelData("Contact",1 ,8);
		String srchType = excelLib.getExcelData("Contact",1 ,9);
		String noOrgFound = excelLib.getExcelData("Contact",1 ,10);
		String noContactFound = excelLib.getExcelData("Contact",1 ,11);

		h = new Home(driver);
		co = new CreateOrganization(driver);
		o = new Organizations(driver);
		cc= new CreateContact(driver);
		c = new Contacts(driver);
		ci=new ContactInfo(driver);

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

		//Step 5:
		//Click on Contacts link
		h.getContactsLink().click();

		//Step 6:
		//Click on new Contact button
		c.getCreateContactLink().click();

		//Step 6:
		//Create Contact
		cc.createContact(contactfName, contactlName, orgName, expPageTitle, homePageTitle, srchType);

		//Step 7:
		//Verify if contact name is same
		String actContact = ci.verifyOrg();
		Assert.assertTrue(actContact.contains(contactfName), "Contact does not contains "+contactfName);

		//Step 8:
		//delete contact
		c.deleteContact(orgName, srchType);

		//Step 9:
		//verify delete contact
		h.getContactsLink();
		String actNoContactMsgc = c.verifyDeleteContact(orgName, srchType);
		System.out.println(actNoContactMsgc);
		Assert.assertTrue(actNoContactMsgc.contains(noContactFound),"No Contact Found!! text does not found");

		//Step 10:
		//delete organization created
		h.getOrgLink();
		o.deleteOrg(orgName, srchType);

		//Step 11:
		//verify organization is deleted
		h.getOrgLink();
		String actNoOrghText = o.verifyDeleteOrg(orgName, srchType);
		System.out.println(actNoOrghText);
		Assert.assertTrue(actNoOrghText.contains(noOrgFound),"No Organization found!! text does not found");

	}


}
