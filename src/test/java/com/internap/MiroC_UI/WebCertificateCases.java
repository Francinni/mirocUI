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
import com.internap.MiroC_UI.Pages.MyProfilePage;

public class WebCertificateCases extends MyProjTestCaseUtils{

	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	MyProfilePage myProfilePage = PageFactory.initElements(
			uiInstance.getDriver(), MyProfilePage.class);
	ConfigurationPage configurationPage;
	Home home;
	

	public WebCertificateCases(String browserType) {
		super(BrowserType.FIREFOX);		
		
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		loginPage.login(Common.adminUserName, Common.passWord);
		home = PageFactory.initElements(uiInstance.getDriver(), Home.class);
	}
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-742 :  Web certificate page is not shown for non admin users
	 */ 
	@Test(groups = { "Negative", "verifyWebCertificateTabIsNotShownForNonAdmins" },priority = 0) 
	public void verifyWebCertificateTabIsNotShownForNonAdmins() { 
			
		 using(loginPage = home  
					.goLogOut(uiInstance.getDriver())
					.login(Common.userUserName, Common.userPassWord))
					.check(loginPage.successfulMessageMustBePresent());
		 andUsing(configurationPage = home
				 .goConfigurationTab(uiInstance.getDriver()))
			.check(configurationPage.webCertificateNotShownForNonAdmins(uiInstance.getDriver()));
	} 
	
}
