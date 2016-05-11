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
import com.internap.MiroC_UI.Common.PageUtils;

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
	 * This test case is the equivalent to the Testlink id: MBOX-275- Change User Admin Info
	 */ 
	@Test(groups = { "Positive" },priority = 0) 
	public void editMyProfile() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver()) 
				.editMyProfile(firstname,lastname,email))
		.check(myProfilePage.userIsEdited(uiInstance.getDriver(), firstname, lastname, email));
		
		//.andUsing(myProfilePage.editMyProfile("admin", "user", ""))
		//.check(myProfilePage.userIsEdited(uiInstance.getDriver(), "admin", "user", ""));
		
	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-588- User cannot change the username
	 */
	@Test(groups = { "Positive" }) 
	public void checkUsernameFieldIsDisabled() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver()))
		.check(myProfilePage.usernameFieldIsDisabled(uiInstance.getDriver()));		
	} 
	
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-335- New user info is shown after some changes were performed
	 */ 
	@Test(groups = { "Positive" }) 
	public void checkHeaderInfo() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver()) 
				.editMyProfile(firstname,lastname,email))
				
		.check(myProfilePage.userIsEdited(uiInstance.getDriver(), firstname, lastname, email),
				myProfilePage.verifyUserInfoOnHeader(uiInstance.getDriver(), firstname, lastname))
		
		.andUsing(loginPage = home  
				.goLogOut(uiInstance.getDriver()).login(Common.adminUserName, Common.passWord))
				.check(myProfilePage.verifyUserInfoOnHeader(uiInstance.getDriver(), firstname, lastname));
				
	} 
	
	
	

} 
