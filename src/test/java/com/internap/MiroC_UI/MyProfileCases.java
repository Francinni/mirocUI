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
import com.internap.MiroC_UI.Pages.MyProfilePage;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;

public class MyProfileCases extends MyProjTestCaseUtils{
	
	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	MyProfilePage myProfilePage = PageFactory.initElements(
			uiInstance.getDriver(), MyProfilePage.class);
	ConfigurationPage configurationPage;
	Home home;
	
	String firstname = "testFirstName";
	String lastname = "testLastName";  
	String email = "test@testing.com";


	public MyProfileCases(String browserType) {
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
	@Test(groups = { "Positive" },priority = 0) 
	public void editMyProfile() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver()) 
				.editMyProfile(firstname,lastname,email))
		.check(myProfilePage.userIsEdited(uiInstance.getDriver(), firstname, lastname, email))
		
		.andUsing(myProfilePage.editMyProfile("admin", "user", "")) 
		.check(myProfilePage.userIsEdited(uiInstance.getDriver(), "admin", "user", ""));
		
	} 

} 
