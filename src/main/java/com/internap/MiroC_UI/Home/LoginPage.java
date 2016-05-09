package com.internap.MiroC_UI.Home;

import junit.framework.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.MyProjPage;
import com.ts.commons.Validator;

public class LoginPage extends MyProjPage{

	@FindBy(xpath=".//*[@id='username']")
	private WebElement userName;
	
	@FindBy(xpath="html/body/div[3]/form/div[4]/div/input")
	private WebElement password;
	
	@FindBy(xpath="//button[contains(.,'Login')]")
	private WebElement logInButton;
	
	public LoginPage login (String userName, String password){
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
		this.logInButton.click();
		return this;
	}
	
	public LoginPage and(InternapUI uiInstance) {
		this.uiInstance = uiInstance;
		return this;
	}
	
	@Override
	public LoginPage and() {
		return this;
	}

	@Override
	public LoginPage then() {
		return this;
	}

	@FindBy(xpath=".//*[@id='sidebar']/ul/li[3]/a/span[1]")
	private WebElement val;
	
	public Validator successfulMessageMustBePresent() {
		return new Validator() {
			@Override
			public void Validate() {
				WebElement successfulMessage = val;

				boolean thereIsAnSuccessfulMessage = successfulMessage != null;
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}

	
}
