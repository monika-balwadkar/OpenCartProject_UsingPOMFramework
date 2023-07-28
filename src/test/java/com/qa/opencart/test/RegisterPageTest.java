package com.qa.opencart.test;

import java.util.Random;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constant;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void regPageSetup() 
	{
		registerPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmail() {
		Random random = new Random();
		//String email = "automation" + random.nextInt(1000) + "@gmail.com";
		
		String email = "automation" + System.currentTimeMillis() + "@gmail.com";
		//automation12121212121@gmail.com
		//automation232323232323@gmail.com
		
		return email;
	}

	@DataProvider
	public Object[][] getRegTestData() throws InvalidFormatException 
	{
		Object regData[][] = ExcelUtil.getTestData(Constant.REGISTER_SHEET_NAME);
		return regData;
	}

	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String firstName, String lastName, String telephone, String password, String subscribe)
	{
		Assert.assertTrue(registerPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe));
	}

}
