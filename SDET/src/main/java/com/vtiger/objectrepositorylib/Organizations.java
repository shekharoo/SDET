package com.vtiger.objectrepositorylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericUtils.WebDriverUtils;

public class Organizations extends WebDriverUtils{
	 
	public WebDriver driver;
	public Organizations(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createOrgBtn;

	@FindBy(name = "search_text")
	private WebElement searchTf;

	@FindBy(name = "search_field")
	private WebElement orgSearchdropDown;

	@FindBy(name = "search")
	private WebElement searchBtninOrgContact;

	@FindBy(name = "submit")
	private WebElement searchNowOrg;
	
	@FindBy(name = "selected_id")
	private WebElement selCheckBox;
	
	@FindBy(id = "bas_searchfield")
	private WebElement noOrgText;
	
	@FindBy(linkText = "del")
	private WebElement delButton;
	
	public WebElement getDelButton() {
		return delButton;
	}

	public WebElement getSelCheckBox() {
		return selCheckBox;
	}

	public WebElement getNoOrgText() {
		return noOrgText;
	}

	public WebElement getSearchNowOrg() 
	{
		return searchNowOrg;
	}

	public WebElement getCreateOrgBtn() {
		return createOrgBtn;
	}

	public WebElement getSearchTf() {
		return searchTf;
	}

	public WebElement getOrgSearchdropDown() {
		return orgSearchdropDown;
	}

	public WebElement getSearchBtn() {
		return searchBtninOrgContact;
	}
	public void deleteOrg(String orgName,String srchType) throws Throwable
	{
		searchTf.sendKeys(orgName);
		orgSearchdropDown.sendKeys(srchType);
		searchNowOrg.click();
		//String xpathOrgLink = "//a[text()='"+orgName+"']";
		WebElement orgEle = driver.findElement(By.linkText(orgName));
		waitAndClickElement(driver, orgEle, selCheckBox);
		delButton.click();
		popUpAccept(driver);
	}
	public void verifyDeleteOrg(String orgName,String srchType) throws Throwable
	{
		searchTf.sendKeys(orgName);
		orgSearchdropDown.sendKeys(srchType);
		searchNowOrg.click();		
		driverWait(driver, noOrgText);
	}



}
