package com.internap.MiroC_UI.Pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.ts.commons.Validator;
import com.ts.commons.RaceConditions.WaitTool;

public class AuthenticationPage extends MyProjPage {

	// General

	@FindBy(xpath = ".//span[Contains(.,'ON')]")
	private WebElement onAuthentication;

	@FindBy(xpath = ".//span[@class='bootstrap-switch-handle-off bootstrap-switch-default'][Contains(.,'OFF')]")
	private WebElement offAuthentication;

	@FindBy(xpath = ".//*[@id='Radius']")
	private WebElement radiusTab;

	@FindBy(xpath = ".//*[@id='Tacacs']")
	private WebElement tacacsTab;

	@FindBy(xpath = ".//*[@id='ldap']")
	private WebElement ldapTab;

	// Radius

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[1]/div[1]/div/input[@name='radiusServer']")
	private WebElement radiusServerIPTexfield;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[1]/div[2]/div/input[@name='radiusPassword']")
	private WebElement radiusPasswordTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[1]/div[3]/div/input[@name='radiusTimeout']")
	private WebElement radiusTimeOutTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[2]/button[Contains(.,'Cancel')]")
	private WebElement radiusCancelButton;

	//.//*[@id='tab_15_1']/div/form/div[2]/button[Contains(.,'Save')]
	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[2]/button[2]")
	private WebElement radiusSveButton;

	// TACACS

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/form/div[1]/div[1]/div/input[@name='tacacsServer']")
	private WebElement tacacsServerTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/form/div[1]/div[2]/div/input[@name='tacacsSecret']")
	private WebElement tacacsPasswordTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/form/div[2]/button[Contains(.,'Cancel')]")
	private WebElement tacacsCancelButton;

	//.//*[@id='tab_15_2']/div/form/div[2]/button[Contains(.,'Save')]
	@FindBy(xpath = ".//*[@id='tab_15_2']/div/form/div[2]/button[2]")
	private WebElement tacacsSveButton;

	// LDAP

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[1]/div/input[@name='ldapBase']")
	private WebElement ldapBaseTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[2]/div/input[@name='ldapURI']")
	private WebElement ldapURITextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[3]/div/input[@name='ldapVersion']")
	private WebElement ldapVersionTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[4]/div/input[@name='ldapRootBinddn']")
	private WebElement ldapRootBinddnTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[5]/div/input[@name='ldapPassword']")
	private WebElement ldapPasswordTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[2]/button[Contains(.,'Cancel')]")
	private WebElement ldapCancelButton;
//.//*[@id='tab_15_3']/div/form/div[2]/button[Contains(.,'Save')]
	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[2]/button[2]")
	private WebElement ldapSveButton;

	// Extra Parameters

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[6]/div/input[@name='key']")
	private WebElement ldapKeyTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[7]/div[1]/input[@name='val']")
	private WebElement ldapValTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[7]/div[2]/button[Contains(.,'Add')]")
	private WebElement ldapAddButton;

	// Method to enable the external authentication
	public AuthenticationPage enableExternalAuthentication(WebDriver driver) {
		this.offAuthentication.click();
		this.radiusSveButton.click();
		return this;
	}

	// Method to disable the external authentication
	public AuthenticationPage disableExternalAuthentication(WebDriver driver) {
		this.onAuthentication.click();
		this.radiusSveButton.click();
		return this;
	}

	// Method to know if authentication is enabled
	public AuthenticationPage clear() {
		this.radiusServerIPTexfield.clear();
		this.radiusPasswordTextfield.clear();
		this.radiusTimeOutTextfield.clear();
		return this;
	}
	
	// Method to know if authentication is enabled
	public AuthenticationPage clearTacacs() {
		this.tacacsServerTextfield.clear();
		this.tacacsPasswordTextfield.clear();
		return this;
	}

	// Method to save RADIUS authentication
	public AuthenticationPage addRaius(WebDriver driver, String server,
			String pass, String timeout) {
		boolean value = radiusServerIPTexfield.isEnabled();
		System.out.print("atributo" + value);

		if (value == true) {
			WaitTool.waitForElementPresentAndVisible(driver, radiusTab);
			this.radiusTab.click();
			clear();
			this.radiusServerIPTexfield.sendKeys(server);
			this.radiusPasswordTextfield.sendKeys(pass);
			this.radiusTimeOutTextfield.sendKeys(timeout);
			this.radiusSveButton.click();
		} else {
			this.offAuthentication.click();
			WaitTool.waitForElementPresentAndVisible(driver, radiusTab);
			this.radiusTab.click();
			clear();
			this.radiusServerIPTexfield.sendKeys(server);
			this.radiusPasswordTextfield.sendKeys(pass);
			this.radiusTimeOutTextfield.sendKeys(timeout);
			this.radiusSveButton.click();
		}
		return this;
	}

	// Method to save TACACS authentication
	public AuthenticationPage addTACACS(WebDriver driver, String server,
			String pass) {
		boolean value = radiusServerIPTexfield.isEnabled();

		if (value = true) {
			WaitTool.waitForElementPresentAndVisible(driver, tacacsTab);
			this.tacacsTab.click();
			clearTacacs();
			this.tacacsServerTextfield.sendKeys(server);
			this.tacacsPasswordTextfield.sendKeys(pass);
			this.tacacsSveButton.click();
		} else {
			this.offAuthentication.click();
			this.tacacsTab.click();
			clearTacacs();
			this.tacacsServerTextfield.sendKeys(server);
			this.tacacsPasswordTextfield.sendKeys(pass);
			this.tacacsSveButton.click();
		}
		return this;
	}

	// Method to save LDAP authentication
	public AuthenticationPage addLDAP(WebDriver driver, String base,
			String uri, String version, String rootbinddn, String pass) {
		boolean value = radiusServerIPTexfield.isEnabled();

		if (value = true) {
			WaitTool.waitForElementPresentAndVisible(driver, ldapTab);
			this.ldapTab.click();
			this.ldapBaseTextfield.sendKeys(base);
			this.ldapURITextfield.sendKeys(uri);
			this.ldapVersionTextfield.sendKeys(version);
			this.ldapRootBinddnTextfield.sendKeys(rootbinddn);
			this.ldapPasswordTextfield.sendKeys(pass);
			this.ldapSveButton.click();
		} else {
			this.offAuthentication.click();
			WaitTool.waitForElementPresentAndVisible(driver, ldapTab);
			this.ldapTab.click();
			this.ldapBaseTextfield.sendKeys(base);
			this.ldapURITextfield.sendKeys(uri);
			this.ldapVersionTextfield.sendKeys(version);
			this.ldapRootBinddnTextfield.sendKeys(rootbinddn);
			this.ldapPasswordTextfield.sendKeys(pass);
			this.ldapSveButton.click();
		}
		return this;
	}

	// Validator for successful message on Authentication
	public Validator successfulMessageShouldBePresented(final WebDriver driver) {
		return new Validator() {

			@Override
			public void Validate() {

				boolean thereIsAnSuccessfulMessage = PageUtils
						.isElementPresent(
								driver,
								//.//p[Contains(.,'Authentication settings saved successfully.')]
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[1]/div[2]/p"));
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}
}
