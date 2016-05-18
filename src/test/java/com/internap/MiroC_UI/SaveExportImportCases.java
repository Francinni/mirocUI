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
import com.internap.MiroC_UI.Pages.SaveExportImportPage;

public class SaveExportImportCases extends MyProjTestCaseUtils {

	LoginPage loginPage = InternapUI.goToPortalPage_withFirefox();
	SaveExportImportPage saveExportImportPage = PageFactory.initElements(
			uiInstance.getDriver(), SaveExportImportPage.class);
	ConfigurationPage configurationPage;
	Home home;

	public SaveExportImportCases(String browserType){
		super(BrowserType.FIREFOX);
	}
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception {
		loginPage.login(Common.adminUserName, Common.passWord);
		home = PageFactory.initElements(uiInstance.getDriver(), Home.class);
	}
	
/////////////////////////////////////////////////////////////////////SAVE/EXPORT/IMPORT///////////////////////////////////////////////////////////////////////////	
	
	/**
	 * This test case is the equivalent to the Testlink id:
	 * MBOX-683- Information text display the correct information 
	 */ 
	@Test(groups = { "Positive", "checkDescriptionText" },priority = 0) 
	public void checkDescriptionText() { 
		using(saveExportImportPage = home  
				.goConfigurationTab(uiInstance.getDriver()) 
				.goSaveExportImport(uiInstance.getDriver()))
				
		.check(saveExportImportPage.checkInformationText(uiInstance.getDriver()));
				
	} 
	
/////////////////////////////////////////////////////////////////////SAVE///////////////////////////////////////////////////////////////////////////////////////	
	
	
	/**
	 * This test case is the equivalent to the Testlink id:
	 * MBOX-644: Write running configuration to startup configuration
	 * MBOX-681: A successful message is shown when saving the config
	 */ 
	@Test(groups = { "Positive", "editAdminAccountInfo" },dependsOnMethods = "checkDescriptionText")  
	public void writeRunningConfiguration() { 
		using(saveExportImportPage 
				.writeRunningConfiguration(uiInstance.getDriver()))
				
		.check(saveExportImportPage.successfulMessageShouldBePresentedOnSave(uiInstance.getDriver()));
		
				
	}
	
	
/////////////////////////////////////////////////////////////////////EXPORT///////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * This test case is the equivalent to the Testlink id: 
	 * MBOX-329: Download config is working properly
	 * MBOX-647: A .json file is downloaded with the configuration
	 * 
	 */ 
	@Test(groups = { "Positive", "downloadConfigurationFile" },dependsOnMethods = "writeRunningConfiguration") 
	public void downloadConfigurationFile() { 
		using(saveExportImportPage 
				.goExportTab(uiInstance.getDriver())
				.exportConfigurationFile(uiInstance.getDriver()))
								
		.check(saveExportImportPage.checkFileDownload(uiInstance.getDriver()));				
		
	} 
	
/////////////////////////////////////////////////////////////////////IMPORT///////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-579- User can change image profile
	 */ 
	@Test(groups = { "Positive", "importConfigurationFile"} )//,dependsOnMethods = "downloadConfigurationFile") 
	public void importConfigurationFile() { 
		using(saveExportImportPage = home  
				.goConfigurationTab(uiInstance.getDriver()) 
				.goSaveExportImport(uiInstance.getDriver())
				.goImportTab(uiInstance.getDriver())
				.selectConfigurationFile(uiInstance.getDriver()))				
		.check(); //saveExportImportPage.checkFileNameOnImportImage(uiInstance.getDriver(), Common.configFile)

		//.andUsing(myProfilePage
			//	.uploadImage(uiInstance.getDriver(), "src/main/resources/originalAvatar.jpg")) 
		//.check(myProfilePage.avatarIsUploaded(uiInstance.getDriver()));

	} 
	
}
