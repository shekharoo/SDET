package com.vtiger.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.genericUtils.FileLib;

public class Login {
	
	 public WebDriver driver;
	
	 @FindBy(name = "user_name")
	 private WebElement userName;
	 
	 @FindBy(name = "user_password")
	 private WebElement password;
	 
	 @FindBy(id = "submitButton")
	 private WebElement loginBtn;
	
	 public WebElement getUserName() {
		return userName;
	}

	 public WebElement getPassword() {
		return password;
	}

	 public WebElement getLoginBtn() {
		return loginBtn;
	}
 
	 public Login(WebDriver driver)
	 {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void loginApp(String username, String psswd)
	 {
		 userName.sendKeys(username);
		 password.sendKeys(psswd);
		 loginBtn.click();
	 }
	 public void loginApp() throws Throwable
	 {
		 userName.sendKeys(FileLib.readPropertyFile("username"));
		 password.sendKeys(FileLib.readPropertyFile("password"));
		 loginBtn.click();
	 }

}
