package com.qa.opencart.test;





import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC - 100: design login for open cart app")
@Story("US-Login: 101: design login page features for open cart")
public class LoginPageTest extends BaseTest {
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("....checking the title of the page.... tester: Monika")
	@Test(priority=1)
	public void loginPageTitleTest()
	{
		String actual_title= loginPage.getLoginPageTitle();
		Assert.assertEquals(actual_title, "Account Login");		
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("....checking the url of the page.... tester: Monika")
	@Test(priority=2)
	public void loginPageUrlTest()
	{
		String actual_url=loginPage.getLoginPageURL();
		Assert.assertTrue(actual_url.contains("route=account/login"));
	}
	

	@Severity(SeverityLevel.CRITICAL)
	@Description("....checking forgot pwd link exist.... tester: Monika")
	@Test(priority=3)
	public void forgotPwdLinkTest()
	{
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	

	@Severity(SeverityLevel.BLOCKER)
	@Description("....checking user is able to login to app with correct username and password....")
	@Test(priority=4)
	public void loginTest()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
