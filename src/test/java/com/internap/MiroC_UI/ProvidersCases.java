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
import com.internap.MiroC_UI.Pages.ProvidersPage;
import com.internap.MiroC_UI.Common.PageUtils;

public class ProvidersCases extends MyProjTestCaseUtils{

	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	ProvidersPage myProvidersPage = PageFactory.initElements(
			uiInstance.getDriver(), ProvidersPage.class);
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
	 */ 
	@Test(groups = { "Positive", "addNewProvider" },priority = 0) 
	public void addNewProvider() { 
		using(myProvidersPage = home 
				.goConfigurationTab(uiInstance.getDriver())
				.goProviders(uiInstance.getDriver())
				.addProvider(uiInstance.getDriver(), asn , 300, 5500,color))				
		.check(
				myProvidersPage.providerIsAdded(uiInstance.getDriver()), 
				myProvidersPage.providerIsAddedToList(uiInstance.getDriver(), String.valueOf(asn), color),  // Verifies that new asn is in asns table after it was inserted and refreshes the page
				myProvidersPage.providerIsAddedToList(uiInstance.getDriver(), String.valueOf(asn), color)   //Verifies again once the page is refreshed
				);
				
	} 
}


