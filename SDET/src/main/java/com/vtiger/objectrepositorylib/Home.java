package com.vtiger.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	
	 public WebDriver driver;
		
	 @FindBy(linkText = "Organizations")
	 private WebElement organizationLink;
	 
	 @FindBy(xpath = "//img[@alt='Create Contact...']")
	 private WebElement password;
	 
	 @FindBy(id = "submitButton")
	 private WebElement loginBtn;
	
//	 public WebElement getUserName() {
//		return userName;
//	}
//
//	 public WebElement getPassword() {
//		return password;
//	}


	 public Home(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void clickOrgLink()
	 {
		 organizationLink.click();
	 }
//	 public void loginApp() throws Throwable
//	 {
//		 System.out.println("In loginApp method");
//		 userName.sendKeys(FileLib.readPropertyFile("username"));
//		 password.sendKeys(FileLib.readPropertyFile("password"));
//		 loginBtn.click();
//	 }

}
