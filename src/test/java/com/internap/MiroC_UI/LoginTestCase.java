package com.internap.MiroC_UI;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;
import com.internap.MiroC_UI.Home.LoginPage;

public class LoginTestCase extends MyProjTestCaseUtils {

	public LoginTestCase(String browserType) {
		super(BrowserType.FIREFOX);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This test case is the equivalent to the Testlink id: MIRO'
	 */
	@Test(groups = { "Positive", "Login" })
	public void Login() {

		LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();

		using(
				loginPage.login(Common.adminUserName,
						Common.passWord))
						.check(
				loginPage.successfulMessageMustBePresent())
						;
		
	}

}