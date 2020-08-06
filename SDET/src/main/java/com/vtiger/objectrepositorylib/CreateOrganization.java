package com.vtiger.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganization {
	
	 public WebDriver driver;
	 
	 @FindBy(name = "accountname")
	 private WebElement orgNameTf;
	 
	 @FindBy(name = "industry")
	 private WebElement indDrpDn;
	 
	 @FindBy(name = "accounttype")
	 private WebElement accTyprDrpDn;
	 
	 @FindBy(name = "button")
	 private WebElement saveBtn;
	 
	 @FindBy(className = "dvHeaderText")
	 private WebElement verifyOrg;

	 public WebElement getAccountNameTf() {
		return orgNameTf;
	}

	public WebElement getIndDrpDn() {
		return indDrpDn;
	}

	public WebElement getAccTyprDrpDn() {
		return accTyprDrpDn;
	}

	 public CreateOrganization(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public String orgName(String accName)
	 {
		orgNameTf.sendKeys(accName);
		return accName;
	 }
	 public void selectInd(String industry)
	 {
		 indDrpDn.sendKeys(industry);
	 }

	 public void selectAcc(String accType)
	 {
		 accTyprDrpDn.sendKeys(accType);
	 }
	 public void clickSave()
	 {
		 saveBtn.click();
	 }
	 
	 public String verifyOrg()
	 {
		return verifyOrg.getText();
	 }


}
