package com.internap.MiroC_UI;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;
import com.internap.MiroC_UI.Home.Home;
import com.internap.MiroC_UI.Home.LoginPage;
import com.internap.MiroC_UI.Pages.AuthenticationPage;

public class AuthenticationTestCases extends MyProjTestCaseUtils{
	
	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	Home home;
	AuthenticationPage authenticationPage;

	public AuthenticationTestCases(String browserType) {
		super(BrowserType.FIREFOX);
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		loginPage.login(Common.adminUserName, Common.passWord);
		home = PageFactory.initElements(uiInstance.getDriver(), Home.class);
	}

	/**
	 * This test case is the equivalent to the Testlink: MBOX-311:User can add blocked ports
	 */
	@Test(groups = { "Positive",  "addRadiusAuthentication_validateEmptyFields_checkPassIsMasked" },priority = 0)
	public void addRadiusAuthentication_validateEmptyFields_checkPassIsMasked() {

		using(authenticationPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goAuthentication(uiInstance.getDriver())
				.addRadius(uiInstance.getDriver(), "123.45.34.12", "pass", "30"))
				.check(authenticationPage.isPasswordValidator(uiInstance.getDriver()),
					   authenticationPage.successfulMessageShouldBePresented(uiInstance.getDriver()))
					  .andUsing(authenticationPage
								.addRadius(uiInstance.getDriver(), "", "", "")
								).check(authenticationPage.emptyValuesAuthentication(uiInstance.getDriver(),"1"));
	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-319:System validates if the user enter an existent trusted network or blocked port
    */
	@Test(groups = { "Positive", "addTacacsAuthentication_validateEmptyFields_checkPassIsMasked" }, dependsOnMethods = "addRadiusAuthentication_validateEmptyFields_checkPassIsMasked")
	public void addTacacsAuthentication_validateEmptyFields_checkPassIsMasked() {
		using(authenticationPage
				.addTACACS(uiInstance.getDriver(), "123.45.34.12", "pass")
		).check(authenticationPage.successfulMessageShouldBePresented(uiInstance.getDriver()),authenticationPage.isPasswordValidator(uiInstance.getDriver()))
		.andUsing(authenticationPage
				.addTACACS(uiInstance.getDriver(), "", "")
		).check(authenticationPage.emptyValuesAuthentication(uiInstance.getDriver(),"2"));

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-319:System validates if the user enter an existent trusted network or blocked port
    */
	@Test(groups = { "Positive", "addLDAPAuthentication_validateEmptyFields_checkPassIsMasked" }, dependsOnMethods = "addTacacsAuthentication_validateEmptyFields_checkPassIsMasked")
	public void addLDAPAuthentication_validateEmptyFields_checkPassIsMasked() {
		using(authenticationPage
				.addLDAP(uiInstance.getDriver(), "dc=cdnqa,dc=internap,dc=com",  "ldap://deploymaster.cdnqa.internap.com","3", "cn=admin,dc=cdnqa,dc=internap,dc=com", "test123")
		).check(authenticationPage.successfulMessageShouldBePresented(uiInstance.getDriver()),authenticationPage.isPasswordValidator(uiInstance.getDriver()))
		.andUsing(authenticationPage
				.addLDAP(uiInstance.getDriver(), "", "", "", "", "")
		).check(authenticationPage.emptyValuesAuthentication(uiInstance.getDriver(),"3"))
		.andUsing(authenticationPage
				.addExtraLdapParameter("abc", "123")
		).check(authenticationPage.extraLdapParameterIsPresented(uiInstance.getDriver(), "abc"))
			.andUsing(authenticationPage
				.deleteExtraLdapParameter(uiInstance.getDriver(),"123")
		).check(authenticationPage.extraLdapParameterIsNotPresented(uiInstance.getDriver(),"123"))
		
		;

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-346:System validates if the user not administrator is authorized for save/update trusted networks
    */
	@Test(groups = { "Positive", "ldapUserCanLogin" }, dependsOnMethods = "addLDAPAuthentication_validateEmptyFields_checkPassIsMasked")
	public void ldapUserCanLogin() {

		using(
				loginPage = home  
				.goLogOut(uiInstance.getDriver()).login("fran", "mipass"))
				.check(loginPage.successfulMessageMustBePresent());

	}
	

}
