package com.vtiger.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericUtils.WebDriverUtils;

public class Home extends WebDriverUtils{

	public WebDriver driver;

	public Home(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;

	public WebElement getOrgLink() throws Throwable 
	{
		waitAndClickElement(driver,orgLink);
		return orgLink;
	}

	public WebElement getContactsLink() 
	{
		return contactsLink;
	}

	public void getLink1()
	{
		orgLink.click();
	}

}
