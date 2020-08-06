package com.vtiger.objectrepositorylib;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericUtils.WebDriverUtils;

public class CreateContact extends WebDriverUtils{
	public WebDriver driver;
	 
	@FindBy(name = "firstname")
	 private WebElement firstnameTf;
	 
	 @FindBy(name = "lastname")
	 private WebElement lastnameTf;
	 
	 @FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	 private WebElement orgNameLookUp;
	 
	 @FindBy(name = "button")
	 private WebElement saveBtn;
	 	 
	public WebElement getOrgNameLookUp() 
	{
		return orgNameLookUp;
	}

	 public CreateContact(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void createContact(String contactfName,String contactlname)
	 {
		 firstnameTf.sendKeys(contactfName);
		 lastnameTf.sendKeys(contactlname);
		 saveBtn.click();
	 }
	 public void createContact(String contactfName,String contactlname,String orgName,String expTitle,String homeTitle,String srchType)
	 {
		 firstnameTf.sendKeys(contactfName);
		 lastnameTf.sendKeys(contactlname);
		 orgNameLookUp.click();
		 switchWindow(expTitle);
		 Organizations o = new Organizations(driver);
		 o.getSearchTf().sendKeys(orgName);
		 o.getOrgSearchdropDown().sendKeys(srchType);
		 o.getSearchBtn().click();
		 String xpathOrgLink = "//a[text()='"+orgName+"']";
		 driver.findElement(By.xpath(xpathOrgLink)).click();
		 switchWindow(homeTitle);
		 saveBtn.click();
	 }
	 
	

}
