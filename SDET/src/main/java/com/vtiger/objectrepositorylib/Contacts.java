package com.vtiger.objectrepositorylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericUtils.WebDriverUtils;

public class Contacts extends WebDriverUtils{

	public WebDriver driver;

	public Contacts(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@alt='Create Contact...']")
	private WebElement createContactLink;

	@FindBy(name = "search_text")
	private WebElement searchTf;

	@FindBy(name = "search_field")
	private WebElement orgSearchdropDown;

	@FindBy(name = "submit")
	private WebElement searchNowOrg;

	@FindBy(name = "selected_id")
	private WebElement selCheckBox;

	@FindBy(xpath = "//span[@class='genHeaderSmall']")
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

	public WebElement getSearchTf() {
		return searchTf;
	}

	public WebElement getOrgSearchdropDown() {
		return orgSearchdropDown;
	}


	public WebElement getCreateContactLink() {
		return createContactLink;
	}

	public void deleteContact(String orgName,String srchType) throws Throwable
	{
		Home h = new Home(driver);
		h.getContactsLink().click();
		searchTf.sendKeys(orgName);
		orgSearchdropDown.sendKeys(srchType);
		searchNowOrg.click();
		//String xpathOrgLink = "//a[text()='"+orgName+"']";
		WebElement orgEle = driver.findElement(By.linkText(orgName));
		waitAndClickElement(driver, orgEle, selCheckBox);
		delButton.click();
		popUpAccept(driver);
	}
	public String verifyDeleteContact(String orgName,String srchType) throws Throwable
	{
		Home h = new Home(driver);
		h.getContactsLink().click();
		searchTf.sendKeys(orgName);
		orgSearchdropDown.sendKeys(srchType);
		searchNowOrg.click();		
		return waitAndCaptureText(driver, noOrgText);

	}
}
