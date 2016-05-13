package com.internap.MiroC_UI;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;
import com.internap.MiroC_UI.Common.PageUtils;
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
	@Test(groups = { "Positive",  "addRadiusAuthentication" },priority = 0)
	public void addRadiusAuthentication() {

		using(authenticationPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goAuthentication(uiInstance.getDriver())
				.addRaius(uiInstance.getDriver(), "123.45.34.12", "pass", "30"))
				.check(authenticationPage.successfulMessageShouldBePresented(uiInstance.getDriver()));
	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-319:System validates if the user enter an existent trusted network or blocked port
    */
	@Test(groups = { "Positive", "addTacacsAuthentication" }, dependsOnMethods = "addRadiusAuthentication")
	public void addTacacsAuthentication() {
		using(authenticationPage
				.addTACACS(uiInstance.getDriver(), "123.45.34.12", "pass")
		).check(authenticationPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-319:System validates if the user enter an existent trusted network or blocked port
    */
	@Test(groups = { "Positive", "addLDAPAuthentication" }, dependsOnMethods = "addTacacsAuthentication")
	public void addLDAPAuthentication() {
		using(authenticationPage
				.addLDAP(uiInstance.getDriver(), "dc=cdnqa,dc=internap,dc=com", "3", "ldap://deploymaster.cdnqa.internap.com", "cn=admin,dc=cdnqa,dc=internap,dc=com", "test123")
		).check(authenticationPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}

}
