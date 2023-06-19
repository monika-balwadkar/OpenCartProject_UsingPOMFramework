package com.qa.opencart.pages;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constant;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;


	private By logoutLink=By.linkText("Logout");
	private By accsHeaders=By.cssSelector("div#content h2");
	private By search=By.name("search");
	private By searchIcon=By.cssSelector("#search button");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccPageTitle()
	{
		String title=eleUtil.waitForTitleIsAndFetch(Constant.DEFAULT_SHORT_TIME_OUT,Constant.ACCOUNT_PAGE_TITLE_VALUE);
		//String title=driver.getTitle();
		System.out.println("Accounts Page Title is:"+title);
		return title;
	}
	
	public String getAccPageURL()
	{
		String url=eleUtil.waitForURLContainsAndFetch(Constant.DEFAULT_SHORT_TIME_OUT,Constant.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		//String url=driver.getCurrentUrl();
		System.out.println("Accounts Page Title is:"+url);
		return url;
	}
	
	public boolean isLogoutLinkExist()
	{
		return eleUtil.waitForElementVisible(logoutLink,Constant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		//return driver.findElement(logoutLink).isDisplayed();
	}
	
	public boolean isSearchExist()
	{
		return eleUtil.waitForElementVisible(search,Constant.DEFAULT_SHORT_TIME_OUT).isDisplayed();
		//return driver.findElement(search).isDisplayed();
	}
	
	public List<String> getAccPageHeadersList()
	{
		List<WebElement> accHeadersList=eleUtil.waitForElementsVisible(accsHeaders,Constant.DEFAULT_SHORT_TIME_OUT);
		//List<WebElement> accHeadersList=driver.findElements(accsHeaders);
		List<String> accHeadersValList=new ArrayList<String>();
		for(WebElement e:accHeadersList)
		{
			String text=e.getText();
			accHeadersValList.add(text);
		}
		return accHeadersValList;
	}
	
	public SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
			
		}else {
			System.out.println("Search field in not present on the page...");
			return null;
		}
	}

}

