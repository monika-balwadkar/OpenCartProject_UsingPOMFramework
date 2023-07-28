package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
//Private By Locators:
	private By emailId= By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
//Page Constructor:
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
//Page Actions & Methods:
	
	@Step("....getting the login page title.....")
	public String getLoginPageTitle()
	{
		String title=eleUtil.waitForTitleIsAndFetch(Constant.DEFAULT_SHORT_TIME_OUT,Constant.LOGIN_PAGE_TITLE_VALUE);
		//String title=driver.getTitle();
		System.out.println("Login Page Title is:"+title);
		return title;
	}
	
	@Step("....getting the login page url.....")
	public String getLoginPageURL()
	{
		String url=eleUtil.waitForURLContainsAndFetch(Constant.DEFAULT_SHORT_TIME_OUT,Constant.LOGIN_PAGE_URL_FRACTION_VALUE);
		//String url=driver.getCurrentUrl();
		System.out.println("Login Page Title is:"+url);
		return url;
	}
	
	@Step("....getting the forgot pwd link.....")
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.waitForElementVisible(forgotPwdLink, Constant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		//return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	//Positive Use-case
	@Step("login with username : {0} and password: {1}")
	public AccountsPage doLogin(String un, String pw)
	{
		System.out.println("App credentials are:"+un+":"+pw);
		eleUtil.waitForElementVisible(emailId,Constant.DEFAULT_SHORT_TIME_OUT).sendKeys(un);
		//driver.findElement(emailId).sendKeys(un);
		eleUtil.doSendKeys(password, pw);
		//driver.findElement(password).sendKeys(pw);
		eleUtil.doClick(loginBtn);
		//driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	//navigating to register page
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

	
}
