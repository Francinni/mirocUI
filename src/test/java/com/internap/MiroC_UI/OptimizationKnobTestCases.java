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
import com.internap.MiroC_UI.Pages.OptimizationKnobPage;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;


public class OptimizationKnobTestCases extends MyProjTestCaseUtils {	
	
	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	OptimizationKnobPage optimizatioKnobPage = PageFactory.initElements(
			uiInstance.getDriver(), OptimizationKnobPage.class);
	ConfigurationPage configurationPage;
	Home home;

	public OptimizationKnobTestCases(String browserType) {
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
	@Test(groups = { "Positive",  "updateKnobs" },priority = 0)
	public void updateKnobs() {

		using(optimizatioKnobPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goOptimizationKnob(uiInstance.getDriver())
				.updateKnobs(uiInstance.getDriver(),70, 70, 35, 7))
				.check(optimizatioKnobPage.assignmentListMustBePresent(uiInstance.getDriver()));
	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	*/
	@Test(groups = { "Negative",  "negativeKnobs" },dependsOnMethods = "updateKnobs")
	public void negativeKnobs() {
		using(optimizatioKnobPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goOptimizationKnob(uiInstance.getDriver())
				.updateInvalidKnobs(uiInstance.getDriver(),-5, -5, -5, -5))
				.check(optimizatioKnobPage.negativeValuesValidator(uiInstance.getDriver()));
	}
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	*/
	@Test(groups = { "Negative",  "overKnobs" },dependsOnMethods = "negativeKnobs")
	public void overKnobs() {
		using(optimizatioKnobPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goOptimizationKnob(uiInstance.getDriver())
				.updateInvalidKnobs(uiInstance.getDriver(),101, 101, 101, 101))
				.check(optimizatioKnobPage.overValuesValidator(uiInstance.getDriver()));
	}
	
	
	//SIMULATION
	
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	*/
	@Test(groups = { "Positive", "updateSimulationKnobs" },  dependsOnMethods = "negativeKnobs")
	public void updateSimulationKnobs() {

		using(optimizatioKnobPage 
				.updateSimulationKnobs(uiInstance.getDriver(),50, 50, 50, 50))
				.check(optimizatioKnobPage.assignmentListMustBePresent(uiInstance.getDriver()));
	}
	
	
	//negative test cases	
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	*/
	@Test(groups = { "Positive", "negativeSimulationKnobs" },  dependsOnMethods = "updateSimulationKnobs")
	public void negativeSimulationKnobs() {

		using(optimizatioKnobPage
				.updateSimulationKnobs(uiInstance.getDriver(),-6, -6, -6, -6))
				.check(optimizatioKnobPage.negativeValuesValidator(uiInstance.getDriver()));
	}
	
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	*/
	@Test(groups = { "Positive", "overSimulationKnobs" },  dependsOnMethods = "updateSimulationKnobs")
	public void overSimulationKnobs() {

		using(optimizatioKnobPage
				.updateSimulationKnobs(uiInstance.getDriver(),101, 101, 101, 101))
				.check(optimizatioKnobPage.overValuesValidator(uiInstance.getDriver()));
	}
	
	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	*/
	@Test(groups = { "Positive", "miroUserUpdateKnobs" },  dependsOnMethods = "negativeSimulationKnobs")
	public void miroUserUpdateKnobs() {

		using(
				loginPage = home  
				.goLogOut(uiInstance.getDriver()).login("user", "password"))
				.check(loginPage.successfulMessageMustBePresent())
		
		.andUsing(
				optimizatioKnobPage = home
						.goConfigurationTab(uiInstance.getDriver())
						.goOptimizationKnob(uiInstance.getDriver())
		).check(optimizatioKnobPage.miroUserValidator(uiInstance.getDriver()));				
	}	

}//end
