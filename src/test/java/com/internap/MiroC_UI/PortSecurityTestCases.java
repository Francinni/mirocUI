package com.internap.MiroC_UI;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.PageUtils;
import com.internap.MiroC_UI.Home.ConfigurationPage;
import com.internap.MiroC_UI.Home.Home;
import com.internap.MiroC_UI.Home.LoginPage;
import com.internap.MiroC_UI.Pages.PortSecurityPage;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;

public class PortSecurityTestCases extends MyProjTestCaseUtils {

	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	PortSecurityPage portSecurityPage = PageFactory.initElements(
			uiInstance.getDriver(), PortSecurityPage.class);
	ConfigurationPage configurationPage;
	Home home;

	public PortSecurityTestCases(String browserType) {
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
	@Test(groups = { "Positive",  "addPortSecurity" },priority = 0)
	public void addPortSecurity() {

		using(portSecurityPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goPortSecurity(uiInstance.getDriver())
				.addPortSecurity(uiInstance.getDriver(),"720"))
				.check(portSecurityPage.successfulMessageShouldBePresented(uiInstance.getDriver()));
	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-319:System validates if the user enter an existent trusted network or blocked port
    */
	@Test(groups = { "Negative", "cannotAddExistentPortSecurity" }, dependsOnMethods = "addPortSecurity")
	public void cannotAddExistentPortSecurity() {
		PageUtils.refreshPage(uiInstance.getDriver());
		using(portSecurityPage
				.addPortSecurity(uiInstance.getDriver(),"720")
		).check(portSecurityPage.existentPortSecurity(uiInstance.getDriver(),"720"));

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-369:System validates if the user enter an invalid trusted network or blocked port
    */
	@Test(groups = { "Negative", "cannotAddEmptyOrInvalidValuePortSecurity" }, dependsOnMethods = "cannotAddExistentPortSecurity")
	public void cannotAddEmptyOrInvalidValuePortSecurity() {

		using(portSecurityPage
				.addPortSecurity(uiInstance.getDriver(),"")
		).check(portSecurityPage.emptyValuesPortSecurity(uiInstance.getDriver(),""))
		.andUsing(portSecurityPage
				.addAllowedNetwork(uiInstance.getDriver(), "")
		).check(portSecurityPage.emptyValuesPortSecurity(uiInstance.getDriver(),""))
			.andUsing(portSecurityPage
				.addAllowedNetwork(uiInstance.getDriver(), "test")
		).check(portSecurityPage.emptyValuesPortSecurity(uiInstance.getDriver(),"test"))
		.andUsing(portSecurityPage
				.addPortSecurity(uiInstance.getDriver(),"-5")
		).check(portSecurityPage.emptyValuesPortSecurity(uiInstance.getDriver(),"-5"));

	}

	/**
	 * This test case is the equivalent to the Testlink: MBOX-318:User can update a blocked port
 */ 
	@Test(groups = { "Positive", "editPortSecurity" },  dependsOnMethods = "cannotAddEmptyOrInvalidValuePortSecurity")
	public void editPortSecurity() {

		using(portSecurityPage
				.editPortSecurity(uiInstance.getDriver(),"720","723")
		).check(portSecurityPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}	   
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-315:User can delete a blocked port
    */
	@Test(groups = { "Positive", "deletePortSecurity" }, dependsOnMethods = "editPortSecurity")
	public void deletePortSecurity() {

		using(portSecurityPage
				.deletePortSecurity(uiInstance.getDriver(),"723")
		).check(portSecurityPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}	 
	
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-306:User can add a trusted network'
    */
	@Test(groups = { "Positive", "addAllowedNetwork" }, dependsOnMethods = "deletePortSecurity")
	public void addAllowedNetwork() {

		using(portSecurityPage
				.addAllowedNetwork(uiInstance.getDriver(),"12.45.67.78/45")
		).check(portSecurityPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}
	
	/**
	 * This test case is the equivalent to the Testlink:  MBOX-319:System validates if the user enter an existent trusted network or blocked port
    */
	@Test(groups = { "Negative", "cannotAddExistentAllowedNetwork" }, dependsOnMethods = "addAllowedNetwork")
	public void cannotAddExistentAllowedNetwork() {
		PageUtils.refreshPage(uiInstance.getDriver());
		using(portSecurityPage
				.addAllowedNetwork(uiInstance.getDriver(),"12.45.67.78/45")
		).check(portSecurityPage.existentPortSecurity(uiInstance.getDriver(),"12.45.67.78/45"));

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-316:User can update a trusted networks
    */
	@Test(groups = { "Positive", "editAllowedNetwork" }, dependsOnMethods = "addAllowedNetwork")
	public void editAllowedNetwork() {

		using(portSecurityPage
				.editAllowedNetwork(uiInstance.getDriver(),"12.45.67.78/45", "55.77.88.99/45")
		).check(portSecurityPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-313:User can delete a trusted network
    */
	@Test(groups = { "Positive", "deleteAllowedNetwork" }, dependsOnMethods = "editAllowedNetwork")
	public void deleteAllowedNetwork() {

		using(portSecurityPage
				.deleteAllowedNetwork(uiInstance.getDriver(), "55.77.88.99/45")
		).check(portSecurityPage.successfulMessageShouldBePresented(uiInstance.getDriver()));

	}
	
	/**
	 * This test case is the equivalent to the Testlink: MBOX-346:System validates if the user not administrator is authorized for save/update trusted networks
    */
	@Test(groups = { "Positive", "userCannotAddUpdatePortSecurity" }, dependsOnMethods = "deleteAllowedNetwork")
	public void userCannotAddUpdatePortSecurity() {

		using(
				loginPage = home  
				.goLogOut(uiInstance.getDriver()).login(Common.userUserName, Common.passWord))
				.check(loginPage.successfulMessageMustBePresent())
		
		.andUsing(
				portSecurityPage = home
						.goConfigurationTab(uiInstance.getDriver())
						.goPortSecurity(uiInstance.getDriver())
		).check(portSecurityPage.addPortIsDisabled(uiInstance.getDriver()));

	}
	
	
	
	
}