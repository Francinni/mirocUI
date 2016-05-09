package com.internap.MiroC_UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ts.commons.generator.ElementType;
import com.ts.commons.generator.Page;

public class ProfilePage extends Page {

	public ProfilePage(WebDriver driver, ElementType[] selectorsToFind) {
		super(driver, selectorsToFind);
		// TODO Auto-generated constructor stub
	}

		// Webdriver to use
		private WebDriver driver;

		@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[1]/div[1]/div[2]/div/label")
		private WebElement addNetworkButton;
		
		@Override
		public Page and() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Page then() {
			// TODO Auto-generated method stub
			return null;
		}
	

}
