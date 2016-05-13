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
	
	
	 ////////////////////////////////////////////////////////-----ACCOUNT INFO-------/////////////////////////////////////////////////////////////////////////
	
	/**
	 * This test case is the equivalent to the Testlink id:
	 * MBOX-275- Change User Admin Info && 
	 * MBOX-335- New user info is shown after some changes were performed
	 */ 
	@Test(groups = { "Positive", "editAccountInfo" },priority = 0) 
	public void editAccountInfo() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver()) 
				.editMyProfile(firstname,lastname,email))
				
		.check(myProfilePage.userIsEdited(uiInstance.getDriver(), firstname, lastname, email),
				myProfilePage.verifyUserInfoOnHeader(uiInstance.getDriver(), firstname, lastname))
		
		.andUsing(loginPage = home  
				.goLogOut(uiInstance.getDriver()).login(Common.adminUserName, Common.passWord))
				.check(myProfilePage.verifyUserInfoOnHeader(uiInstance.getDriver(), firstname, lastname))
		
		.andUsing(myProfilePage.editMyProfile("admin", "user", ""))
		.check(myProfilePage.userIsEdited(uiInstance.getDriver(), "admin", "user", ""));
				
	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-590- Account info validates empty values
	 */ 
	@Test(groups = { "Negative", "verifyEmptyValuesOnAccountInfo"},dependsOnMethods = "editAccountInfo") 
	public void verifyEmptyValuesOnAccountInfo() { 
		using(myProfilePage 
				.editMyProfile("","",""))
		.check(myProfilePage.validateEmptyValues(uiInstance.getDriver()));

	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-588- User cannot change the username
	 */
	@Test(groups = { "Positive", "checkUsernameFieldIsDisabled" },dependsOnMethods = "verifyEmptyValuesOnAccountInfo") 
	public void checkUsernameFieldIsDisabled() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver()))
		.check(myProfilePage.usernameFieldIsDisabled(uiInstance.getDriver()));		
	} 
		
	
	 ////////////////////////////////////////////////////////-----CHANGE PASSWORD-------/////////////////////////////////////////////////////////////////////////

	/**
	 * This test case is the equivalent to the Testlink id: 
	 * MBOX-334- User can login with the new password after it was changed
	 * MBOX-272- Change user password
	 */ 
	@Test(groups = { "Positive", "verifyChangePassword" },dependsOnMethods = "checkUsernameFieldIsDisabled") 
	public void verifyChangePassword() { 
		using(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver())
				.goChangePassword(uiInstance.getDriver())
				.changePassword(uiInstance.getDriver(),Common.passWord, Common.newPassWord, Common.newPassWord))
		.check(myProfilePage.passwordIsChanged(uiInstance.getDriver()))
		
		.andUsing(loginPage = home  
				.goLogOut(uiInstance.getDriver())
				.login(Common.adminUserName, Common.newPassWord))
				.check(loginPage.successfulMessageMustBePresent())
				
		.andUsing(myProfilePage = home  
				.goMyProfilePage(uiInstance.getDriver())
				.goChangePassword(uiInstance.getDriver())
				.changePassword(uiInstance.getDriver(),Common.newPassWord, Common.passWord, Common.passWord))
		.check(myProfilePage.passwordIsChanged(uiInstance.getDriver()));
		
	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: 
	 * MBOX-345- System validates that the correct old password was entered
	 */ 
	@Test(groups = { "Negative", "inCorrectOldPasswordWasEnteredValidation" },dependsOnMethods = "verifyChangePassword") 
	public void inCorrectOldPasswordWasEnteredValidation() { 
		using(myProfilePage 
				.goChangePassword(uiInstance.getDriver())
				.changePassword(uiInstance.getDriver(),"wrongOldPassword", Common.newPassWord, Common.newPassWord))
		.check(myProfilePage.validateOldPasswordMatch(uiInstance.getDriver()));				
		
	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: 
	 * MBOX-576- Old and new password cannot be the same
	 */ 
	@Test(groups = { "Negative", "oldAndNewPasswordShouldNotBeSameValidation" },dependsOnMethods = "inCorrectOldPasswordWasEnteredValidation") 
	public void oldAndNewPasswordShouldNotBeSameValidation() { 
		PageUtils.refreshPage(uiInstance.getDriver());
		using(myProfilePage
				.goChangePassword(uiInstance.getDriver())
				.clearPassword(uiInstance.getDriver()) 
				.changePassword(uiInstance.getDriver(),Common.passWord, Common.passWord, Common.passWord))
		.check(myProfilePage.oldAndNewPasswordShouldNotMatch(uiInstance.getDriver()));				
		
	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: 
	 * MBOX-577- User cannot change password if the re-type data does not match
	 */ 
	@Test(groups = { "Negative", "retypedPasswordAndNewPasswordShouldNotBeSameValidation" },dependsOnMethods = "oldAndNewPasswordShouldNotBeSameValidation") 
	public void retypedPasswordAndNewPasswordShouldNotBeSameValidation() { 
		PageUtils.refreshPage(uiInstance.getDriver());
		using(myProfilePage
				.goChangePassword(uiInstance.getDriver())
				.clearPassword(uiInstance.getDriver()) 
				.changePassword(uiInstance.getDriver(),Common.passWord, Common.newPassWord, "RetypedWrongPassword"))
		.check(myProfilePage.retypePasswordAndPasswordShouldMatch(uiInstance.getDriver()));				
		
	}

	/**
	 * This test case is the equivalent to the Testlink id: MBOX-578- User cannot leave empty values
	 */ 
	@Test(groups = { "Negative", "verifyEmptyValuesOnChangePassword"},dependsOnMethods = "retypedPasswordAndNewPasswordShouldNotBeSameValidation") 
	public void verifyEmptyValuesOnChangePassword() { 
		using(myProfilePage
				.goChangePassword(uiInstance.getDriver())
				.clearPassword(uiInstance.getDriver()) 
				.changePassword(uiInstance.getDriver(), "", "", ""))
		.check(myProfilePage.validateEmptyValuesOnChangePassword(uiInstance.getDriver()));

	} 
	
	 ////////////////////////////////////////////////////////-----CHANGE AVATAR-------/////////////////////////////////////////////////////////////////////////
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-579- User can change image profile
	 */ 
	@Test(groups = { "Positive", "updateImage"} ,dependsOnMethods = "verifyEmptyValuesOnChangePassword") 
	public void updateImage() { 
		using(myProfilePage = home
				.goMyProfilePage(uiInstance.getDriver())
				.goChangeAvatar(uiInstance.getDriver())
				.uploadImage(uiInstance.getDriver(), "src/main/resources/newAvatar.jpg")) 				
		.check(myProfilePage.avatarIsUploaded(uiInstance.getDriver()))

		.andUsing(myProfilePage
				.uploadImage(uiInstance.getDriver(), "src/main/resources/originalAvatar.jpg")) 
		.check(myProfilePage.avatarIsUploaded(uiInstance.getDriver()));

	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-584- Remove button deletes the selected image
	 */ 
	@Test(groups = { "Positive", "cancelUpdateImage"} ,dependsOnMethods = "updateImage") 
	public void cancelUpdateImage() { 
		using(myProfilePage = home
				.goMyProfilePage(uiInstance.getDriver())
				.goChangeAvatar(uiInstance.getDriver())
				.cancelUploadImage(uiInstance.getDriver())) 				
		.check(myProfilePage.avatarUploadIsCancelled(uiInstance.getDriver()));

	} 
} 
