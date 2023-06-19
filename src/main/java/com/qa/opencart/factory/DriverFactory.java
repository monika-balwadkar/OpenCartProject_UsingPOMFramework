package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static String highlight;
	
	/**
	 * this method is initializing the driver on the basis of given browser name
	 * @param browserName
	 * @return this return the driver
	 */
	public WebDriver initDriver(Properties prop)
	{
		optionsManager=new OptionsManager(prop);
		highlight=prop.getProperty("highlight").trim();
		String browserName=prop.getProperty("browser").toLowerCase().trim();  
		//if there is spaces after value in properties file,then use trim
		System.out.println("Browser name is:"+browserName);
		if(browserName.equalsIgnoreCase("chrome")){			
			driver=new ChromeDriver(optionsManager.getChromeOptions());
		}else if(browserName.trim().equalsIgnoreCase("firefox")){
			driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
		}else if(browserName.trim().equalsIgnoreCase("safari")){
			driver=new SafariDriver();
		}else if(browserName.trim().equalsIgnoreCase("edge")){
			driver=new EdgeDriver(optionsManager.getEdgeOptions());
		}else{
			System.out.println("Please pass the right browser"+browserName);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	/**
	 * this method is reading the properties from the .properties file
	 * @return
	 */
	public Properties initProp() {
		prop=new Properties();
		try {
			FileInputStream ip=new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
