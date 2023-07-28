package com.qa.opencart.test;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constant;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup()
	{
		accPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());	
	}
	
	@Test
	public void accPageTitleTest()
	{
		String accTitle=accPage.getAccPageTitle();
		Assert.assertEquals(accTitle, Constant.ACCOUNT_PAGE_TITLE_VALUE);
	}
	
	@Test
	public void accPageUrlTest()
	{
		String accUrl=accPage.getAccPageURL();
		Assert.assertTrue(accUrl.contains(Constant.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	
	@Test
	public void isLogoutLinkExistTest()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void accHeadersCountTest()
	{
		List<String> actualAccPageHeadersList=accPage.getAccPageHeadersList();
		System.out.println("Account page header List:"+actualAccPageHeadersList);
		Assert.assertEquals(actualAccPageHeadersList.size(),Constant.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accHeadersValueTest()
	{
		List<String> actualAccPageHeadersList=accPage.getAccPageHeadersList();
		System.out.println("Account page header List:"+actualAccPageHeadersList);
		System.out.println("Expected Account page headers list:"+Constant.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
		Assert.assertEquals(actualAccPageHeadersList,Constant.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook"},
			{"Samsung"},
			{"Apple"},
			{"iMac"}
		};
	}
	
	@Test(dataProvider="getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage=accPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductCount()>0);
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"Macbook","MacBook Air"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Apple","Apple Cinema 30\""},
			{"iMac","iMac"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider="getProductTestData")
	public void searchProductTest(String searchKey,String productName) {
		searchPage=accPage.performSearch(searchKey);
		if(searchPage.getSearchProductCount()>0) {
			productInfoPage=searchPage.selectProduct(productName);
			String actProductHeader=productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actProductHeader, productName);
		}
	}
	
	
	
}
