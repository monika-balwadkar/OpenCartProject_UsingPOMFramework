package com.qa.opencart.pages;



import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constant;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productInfoMap;
	
	private By productHeader=By.tagName("h1");
	private By productImages=By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity=By.id("input-quantity");
	private By addToCartBtn=By.id("button-cart");
	private By cartSuccessMsg=By.cssSelector("div.alert.alert-success");
	
	public ProductInfoPage(WebDriver driver1) {
		this.driver=driver1;
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
	
	public void enterQuantity(int qty) {
		System.out.println("Product Quantity:"+qty);
		eleUtil.doSendKeys(quantity,String.valueOf(qty));
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMsg=eleUtil.waitForElementVisible(cartSuccessMsg, Constant.DEFAULT_SHORT_TIME_OUT).getText();
		StringBuilder sb=new StringBuilder(successMsg);
		String msg1=sb.substring(0,successMsg.length()-1 ).replace("\n", "").toString();
		System.out.println("Cart successs message:"+msg1);
		return msg1; //this substring is for removing x from button msg
	}
	
	public Map<String, String> getProductInfo() {
		
		//productInfoMap=new HashMap<String,String>();
		productInfoMap=new LinkedHashMap<String,String>(); //Linkedmap:sequence order
		//productInfoMap=new TreeMap<String,String>();//Treemap: sorted order
		//For Header Value:
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();		
		return productInfoMap;
	}
	
	//Fetching metadata
	private void getProductMetaData() {
		//For Meta Data:
		/*Brand: Apple
		Product Code: Product 16
		Reward Points: 600
		Availability: In Stock */
		
		List<WebElement> metaList=eleUtil.getElements(productMetaData);
		for(WebElement e:metaList) {
			String meta=e.getText();
			String metaInfo[]=meta.split(":");
			String key=metaInfo[0].trim();
			String value=metaInfo[1].trim();
			productInfoMap.put(key, value);
		}
	}
	
	//Fetching price data
	private void getProductPriceData() {
		//For Price Data:
		/* $602.00
		Ex Tax: $500.00 */
		
		List<WebElement> priceList=eleUtil.getElements(productPriceData);
		String price=priceList.get(0).getText();
		String exTax=priceList.get(1).getText();	
		String exTaxVal=exTax.split(":")[1];
		productInfoMap.put("productprice",price); //"productprice" is custom key
		productInfoMap.put("exTax",exTaxVal); //"exTax" is custom key
	}
	
	
}
