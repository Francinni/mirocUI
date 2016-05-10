package com.internap.MiroC_UI.Common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;

import com.internap.MiroC_UI.Home.*;
import com.ts.commons.ChromeDriver;
import com.ts.commons.FirefoxDriver;
import com.ts.commons.InternetExplorerDriver;

public class InternapUI {
	
	private static WebDriver driver;

	public InternapUI(String browserType) {

		switch (browserType) {
		case BrowserType.CHROME:
			driver = ChromeDriver.createInstance();
			break;
		case BrowserType.FIREFOX:
			driver = new FirefoxDriver();
			break;
		case BrowserType.IE:
			driver = InternetExplorerDriver.createInstance();
			break;

		default:
			driver = null;
			break;
		}
	}
	
	public static LoginPage goToPortalPage_withFirefox() {
		driver.get(Common.urlMiro);
		driver.manage().window().maximize();
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public WebDriver getDriver() {
		return driver;
	}

}
