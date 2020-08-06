package com.vtiger.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contacts {
     
     public WebDriver driver;

     public Contacts(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }

	@FindBy(xpath = "//img[@alt='Create Contact...']")
	 private WebElement createContactLink;
	
	 public WebElement getCreateContactLink() {
		return createContactLink;
	}
}
