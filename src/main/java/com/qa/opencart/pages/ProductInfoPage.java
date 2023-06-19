package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader=By.tagName("h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getProductHeaderValue() {
		String productHeaderValue=eleUtil.doElementGetText(productHeader);
		System.out.println("Product header:"+productHeaderValue);
		return productHeaderValue;
	}
	
	public int getProductImagesCount() {
		int imagesCount=eleUtil.waitForElementsVisible(productImages,Constant.DEFAULT_LONG_TIME_OUT).size();
		System.out.println("Product images count:"+imagesCount);
		return imagesCount;
		
	}
}
