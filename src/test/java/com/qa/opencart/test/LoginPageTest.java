package com.qa.opencart.test;

import static org.testng.Assert.assertEquals;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actual_title= loginPage.getLoginPageTitle();
		Assert.assertEquals(actual_title, "Account Login");		
	}
	
	@Test(priority=2)
	public void loginPageUrlTest()
	{
		String actual_url=loginPage.getLoginPageURL();
		Assert.assertTrue(actual_url.contains("route=account/login"));
	}
	
	@Test(priority=3)
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority=4)
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
