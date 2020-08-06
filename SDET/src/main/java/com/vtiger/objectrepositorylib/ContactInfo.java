package com.vtiger.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfo {
	public WebDriver driver;
	
	@FindBy(xpath = "//img[@alt='Create Contact...']")
	 private WebElement createContactLink;
	 
	 @FindBy(className = "dvHeaderText")
	 private WebElement verifyContact;

	 public ContactInfo(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public String verifyOrg()
	 {
		return verifyContact.getText();
	 }

}
