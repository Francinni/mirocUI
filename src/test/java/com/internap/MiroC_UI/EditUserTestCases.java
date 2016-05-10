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
	 * This test case is the equivalent to the Testlink id: MIRO'
	 */
	@Test(groups = { "Positive",  "addUser" },priority = 0)
	public void addUser() {

		using(editUserPage = home
				.goConfigurationTab(uiInstance.getDriver())
				.goEditUser(uiInstance.getDriver())
				.addUser("testAuto", "test", "test", true))
				.check(editUserPage.assignmentListMustBePresent(uiInstance.getDriver()));
	}
	
}
