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
import com.internap.MiroC_UI.Pages.EditUserPage;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;

public class EditUserTestCases extends MyProjTestCaseUtils {

	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	EditUserPage editUserPage = PageFactory.initElements(
			uiInstance.getDriver(), EditUserPage.class);
	ConfigurationPage configurationPage;
	Home home;

	public EditUserTestCases(String browserType) {
		super(BrowserType.FIREFOX);
	}

	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		loginPage.login(Common.adminUserName, Common.passWord);
		home = PageFactory.initElements(uiInstance.getDriver(), Home.class);
	}

	/**
	 * This test case is the equivalent to the Testlink id: MIRO-266'
	 */
	@Test(groups = { "Positive", "addUser" }, priority = 0)
	public void addUser() {
		using(
				editUserPage = home.goConfigurationTab(uiInstance.getDriver())
						.goEditUser(uiInstance.getDriver())
						.addUser("testAuto", "test", "test", true))
				.check(editUserPage.assignmentListMustBePresent(uiInstance
						.getDriver()));
	}

	// this method validates the user last login
	/**
	 * This test case is the equivalent to the Testlink id: MIRO-282'
	 */
	@Test(groups = { "Negative", "userLastLogin" }, dependsOnMethods = "addUser")
	public void userLastLogin() {
		using(editUserPage
				).
		check(editUserPage.validationLastLogin(uiInstance.getDriver()));
	}

	// this method validates the user last login
	/**
	 * This test case is the equivalent to the Testlink id: MIRO-786'
	 */
	@Test(groups = { "Negative", "duplicatedUser" }, dependsOnMethods = "userLastLogin")
	public void duplicatedUser() {
		using(
				editUserPage.duplicatedUser(uiInstance.getDriver(), "testAuto", "test", "test", true)).check(
				editUserPage.validationDuplicateUser(uiInstance.getDriver()));
	}

	/**
	 * This test case is the equivalent to the Testlink id: MIRO-269'
	 */
	@Test(groups = { "Positive", "resetUser" }, dependsOnMethods = "duplicatedUser")
	public void resetUserPassword() {
		using(editUserPage.resetPassword(uiInstance.getDriver()))
				.check(editUserPage.assignmentListMustBePresent(uiInstance
						.getDriver()));
	}

	@Test(groups = { "Positive", "manualResetPassword" }, dependsOnMethods = "resetUserPassword")
	public void manualResetUserPassword() {
		using(
				editUserPage.manualResetPassword(uiInstance.getDriver(),
						"123456", "123456"))
				.check(editUserPage.assignmentListMustBePresent(uiInstance
						.getDriver()));
	}

	/**
	 * This test case is the equivalent to the Testlink id: MIRO-333'
	 
	@Test(groups = { "Positive", "logginChangePassword" }, dependsOnMethods = "manualResetUserPassword")
	public void logginChangePassword() {
		
		
		
		using(
				loginPage = home  
				.goLogOut(uiInstance.getDriver()).login(Common.adminUserName, Common.passWord))
				.check(loginPage.successfulMessageMustBePresent());
		
		
		.andUsing(
				editUserPage = home
						.goConfigurationTab(uiInstance.getDriver())
						.goEditUser(uiInstance.getDriver())
		).check(editUserPage.);
		
		
		
		
		
		
		using(
				loginPage = home.goLogOut(uiInstance.getDriver()).login(
						"testAuto", "123456"))
				.check(loginPage.successfulMessageMustBePresent())

				.andUsing(
						home.goLogOut(uiInstance.getDriver()).login(
								Common.adminUserName, Common.userUserName))
				.check(loginPage.successfulMessageMustBePresent())

				.andUsing(
						editUserPage = home.goConfigurationTab(
								uiInstance.getDriver()).goEditUser(
								uiInstance.getDriver()));
	}*/

	/**
	 * This test case is the equivalent to the Testlink id: MIRO-268'
	 */
	@Test(groups = { "Positive", "editUser" }, dependsOnMethods = "manualResetUserPassword")
	public void editUser() {
		using(
				editUserPage.editUser(uiInstance.getDriver(), "testNew",
						"testNew", false))
				.check(editUserPage.assignmentListMustBePresent(uiInstance
						.getDriver()));

	}

	/**
	 * This test case is the equivalent to the Testlink id: MIRO-267'
	 */
	@Test(groups = { "Positive", "deleteUser" }, dependsOnMethods = "shortUserName")
	public void deleteUser() {
		using(editUserPage.deleteUser())
				.check(editUserPage.assignmentListMustBePresent(uiInstance
						.getDriver()));

	}

	/**
	 * This test case is the equivalent to the Testlink id: MIRO-273'
	 */
	@Test(groups = { "Positive", "addMiroUser" }, dependsOnMethods = "deleteUser")
	public void addMiroUser() {
		using(
				editUserPage.addMiroUser(uiInstance.getDriver(),
						"TestMiroUser", "miroUser", "miroUser", false))
				.check(editUserPage.assignmentListMustBePresent(uiInstance
						.getDriver()));

	}

	// negatives cases

	// this method validates that the pass must have at less 4 characters
	/**
	 * This test case is the equivalent to the Testlink id: MIRO-'
	 */
	@Test(groups = { "Negative", "lessChangePassword" }, dependsOnMethods = "editUser")
	public void lessChangePassword() {
		using(
				editUserPage.lessChangePassword(uiInstance.getDriver(), "123",
						"123")).check(
				editUserPage.validationNewPass(uiInstance.getDriver()));
	}

	// this method validates that the username must have at less 4 characters
	/**
	 * This test case is the equivalent to the Testlink id: MIRO-680'
	 */
	@Test(groups = { "Negative", "shortUserName" }, dependsOnMethods = "lessChangePassword")
	public void shortUserName() {
		using(editUserPage.shortUserName("tes", "testUser", "testUser", false))
				.check(editUserPage.validationuserName(uiInstance.getDriver()));
	}

	// this method validates that the user can not delete its own accounts
	/**
	 * This test case is the equivalent to the Testlink id: MIRO-342'
	 */
	@Test(groups = { "Negative", "deleteOwnAccount" }, dependsOnMethods = "addMiroUser")
	public void deleteOwnAccount() {
		using(editUserPage.deleteOwnAccount(uiInstance.getDriver()))
				.check(editUserPage.validationDeleteOwnAccount(uiInstance
						.getDriver()));
	}

}
