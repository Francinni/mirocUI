package com.internap.MiroC_UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;

public class AuthenticationPage extends MyProjPage {

	// General
	
	@FindBy(xpath = ".//span[Contains(.,'OFF')]")
	private WebElement onAuthentication;
	
	@FindBy(xpath = ".//span[Contains(.,'ON')]")
	private WebElement offAuthentication;
	
	// Radius

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[1]/div[1]/div/input[@name='radiusServer']")
	private WebElement radiusServerIPTexfield;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[1]/div[2]/div/input[@name='radiusPassword']")
	private WebElement radiusPasswordTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[1]/div[3]/div/input[@name='radiusTimeout']")
	private WebElement radiusTimeOutTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[2]/button[Contains(.,'Cancel')]")
	private WebElement radiusCancelButton;

	@FindBy(xpath = ".//*[@id='tab_15_1']/div/form/div[2]/button[Contains(.,'Save')]")
	private WebElement radiusSveButton;

	// TACACS

	@FindBy(xpath = ".//label[@ng-click='!auth.admin||addPrefix(prefixForm)']")
	private WebElement tacacsServerTextfield;

	@FindBy(xpath = ".//label[@ng-click='!auth.admin||addPrefix(prefixForm)']")
	private WebElement tacacsPasswordTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/form/div[2]/button[Contains(.,'Cancel')]")
	private WebElement tacacsCancelButton;

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/form/div[2]/button[Contains(.,'Save')]")
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

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[2]/button[Contains(.,'Save')]")
	private WebElement ldapSveButton;

	// Extra Parameters
	
	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[6]/div/input[@name='key']")
	private WebElement ldapKeyTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[7]/div[1]/input[@name='val']")
	private WebElement ldapValTextfield;

	@FindBy(xpath = ".//*[@id='tab_15_3']/div/form/div[1]/div[7]/div[2]/button[Contains(.,'Add')]")
	private WebElement ldapAddButton;
	
	
	
	

}
