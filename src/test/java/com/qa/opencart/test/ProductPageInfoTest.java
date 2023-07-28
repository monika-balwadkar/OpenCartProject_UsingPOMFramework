package com.qa.opencart.test;

import java.util.Map;

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
	
	@Test
	public void productInfoPage() {
		searchPage=accPage.performSearch("MacBook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInfo();
		System.out.println(actProductInfoMap);		
		//if 1st assertion is failed,it will not continue with further assertion,so we have use soft assert
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();

	}
	
	@Test
	public void addToCartTest() {
		searchPage=accPage.performSearch("MacBook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actCartMsg=productInfoPage.addProductToCart();
		softAssert.assertTrue(actCartMsg.indexOf("Success")>=0);
		softAssert.assertTrue(actCartMsg.indexOf("MacBook Pro")>=0);
		softAssert.assertEquals(actCartMsg, "Success: You have added MacBook Pro to your shopping cart!");
	//	Success: You have added "+productName+" to your shopping cart!
		softAssert.assertAll();
	}
}
