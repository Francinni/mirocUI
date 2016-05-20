package com.internap.MiroC_UI;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;
import com.internap.MiroC_UI.Home.ConfigurationPage;
import com.internap.MiroC_UI.Home.Home;
import com.internap.MiroC_UI.Home.LoginPage;
import com.internap.MiroC_UI.Pages.OverridesPage;
import com.internap.MiroC_UI.Pages.ProvidersPage;
import com.internap.MiroC_UI.Common.PageUtils;

public class ProvidersCases extends MyProjTestCaseUtils{

	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	ProvidersPage myProvidersPage = PageFactory.initElements(
			uiInstance.getDriver(), ProvidersPage.class);
	OverridesPage myOverridesPage = PageFactory.initElements(
			uiInstance.getDriver(), OverridesPage.class);
	ConfigurationPage configurationPage;
	Home home;
	
	int  asn = PageUtils.randomNumber();
	String color = PageUtils.hexColor();
	
	public ProvidersCases(String browserType) {
		super(BrowserType.FIREFOX);		
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		loginPage.login(Common.adminUserName, Common.passWord);
		home = PageFactory.initElements(uiInstance.getDriver(), Home.class);
	}
	
	/**
	 * This test case is the equivalent to the Testlink id:
	 * MBOX-277  : Assigned Target Traffic an Price per Mbps for each Provider
	 * MBOX-286 : User can add a new provider and the values are saved
	 * MBOX-622 : User can assign a color graph to the provider  
	 * MBOX-646 : AS+ASN number is added as Provider name when the ASN is unknown 
	 * MBOX-368 : Validate that the refresh the page preserved all the ASN on "Providers" 
	 * MBOX-655 : Provider name is added to Providers Table and sorted by name  
	 * MBOX-663 : Providers created are displayed in other processes
	 * MBOX-666 : Validation message do not displayed when the user create a second provider  
	 */ 
	@Test(groups = { "Positive", "addNewProvider" },priority = 0) 
	public void addNewProvider() { 
		using(myProvidersPage = home 
				.goConfigurationTab(uiInstance.getDriver())
				.goProviders(uiInstance.getDriver())
				.addProvider(uiInstance.getDriver(), asn , PageUtils.randomNumber(), PageUtils.randomNumber(), color))				
		.check(
				myProvidersPage.providerIsSaved(uiInstance.getDriver()), 			                         // Verifies that succesful message is displayed
				myProvidersPage.providerIsAddedToList(uiInstance.getDriver(), String.valueOf(asn), color),  //  Verifies that new Asn was added to the table after it was Saved and refreshes the page
				myProvidersPage.providerIsAddedToList(uiInstance.getDriver(), String.valueOf(asn), color), //   Verifies again once the page is refreshed
				myProvidersPage.providersAreSortedByName(uiInstance.getDriver()),                         //    Verifies if elements on table are sorted by name
				myProvidersPage.emptyValidationIsNotShown(uiInstance.getDriver())						 //     Verifies that empty validations are not displayed if fields are empty but Save has not been clicked.
				);
		
		andUsing(myOverridesPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goOverrides(uiInstance.getDriver()))
				.check(myOverridesPage.asnIsInSelectOptions(uiInstance.getDriver(), String.valueOf(asn))); // Verifies if the ASN added is in Overrides Selects.
				
	} 
	
 
	
}


