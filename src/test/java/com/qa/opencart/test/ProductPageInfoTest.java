package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;



public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());	
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][] {
			//third column is image count for checking assertion
			{"Macbook","MacBook Pro",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Apple","Apple Cinema 30\"",6},
			{"iMac","iMac",3}
		};
	}
	
	@Test(dataProvider="getProductImagesTestData")
	public void productImagesCountTest(String searchKey,String productName,int expectedImagesCount) {
		searchPage=accPage.performSearch(searchKey);
		productInfoPage=searchPage.selectProduct(productName);
		int actImagesCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, expectedImagesCount);
	}
}
