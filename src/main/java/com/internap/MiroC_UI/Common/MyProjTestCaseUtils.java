package com.internap.MiroC_UI.Common;

import com.ts.commons.TestCaseUtil;

import org.testng.annotations.AfterClass;

public class MyProjTestCaseUtils extends TestCaseUtil {

	protected InternapUI uiInstance;

	public MyProjTestCaseUtils(String browserType) {
		uiInstance = new InternapUI(browserType);
	}

	@AfterClass
	public void after() {
		uiInstance.getDriver().quit();
	}

}
