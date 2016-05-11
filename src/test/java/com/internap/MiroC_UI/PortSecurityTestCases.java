package com.internap.MiroC_UI;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
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
	 * This test case is the equivalent to the Testlink id: MIRO'
	 */
	@Test(groups = { "Positive",  "addPortSecurity" },priority = 0)
	public void addPortSecurity() {

		using(portSecurityPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goPortSecurity(uiInstance.getDriver())
				.addPortSecurity(uiInstance.getDriver(),"720"))
				.check(portSecurityPage.assignmentListMustBePresent(uiInstance.getDriver()));
	}	

	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
 */ 
	@Test(groups = { "Positive", "editPortSecurity" },  dependsOnMethods = "addPortSecurity")
	public void editPortSecurity() {

		using(portSecurityPage
				.editPortSecurity(uiInstance.getDriver(),"720","723")
		).check(portSecurityPage.assignmentListMustBePresent(uiInstance.getDriver()));

	}	   
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
    */
	@Test(groups = { "Positive", "deletePortSecurity" }, dependsOnMethods = "editPortSecurity")
	public void deletePortSecurity() {

		using(portSecurityPage
				.deletePortSecurity(uiInstance.getDriver(),"723")
		).check(portSecurityPage.assignmentListMustBePresent(uiInstance.getDriver()));

	}	 
	
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
    */
	@Test(groups = { "Positive", "addAllowedNetwork" }, dependsOnMethods = "deletePortSecurity")
	public void addAllowedNetwork() {

		using(portSecurityPage
				.addAllowedNetwork(uiInstance.getDriver(),"12.45.67.78/45")
		).check(portSecurityPage.assignmentListMustBePresent(uiInstance.getDriver()));

	}
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
    */
	@Test(groups = { "Positive", "editAllowedNetwork" }, dependsOnMethods = "addAllowedNetwork")
	public void editAllowedNetwork() {

		using(portSecurityPage
				.editAllowedNetwork(uiInstance.getDriver(),"12.45.67.78/45", "55.77.88.99/45")
		).check(portSecurityPage.assignmentListMustBePresent(uiInstance.getDriver()));

	}
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
    */
	@Test(groups = { "Positive", "deleteAllowedNetwork" }, dependsOnMethods = "editAllowedNetwork")
	public void deleteAllowedNetwork() {

		using(portSecurityPage
				.deleteAllowedNetwork(uiInstance.getDriver(), "55.77.88.99/45")
		).check(portSecurityPage.assignmentListMustBePresent(uiInstance.getDriver()));

	}

}