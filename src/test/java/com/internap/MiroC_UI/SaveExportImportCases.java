package com.internap.MiroC_UI;

import java.io.File;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.InternapUI;
import com.internap.MiroC_UI.Common.MyProjTestCaseUtils;
import com.internap.MiroC_UI.Common.PageUtils;
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
	
///////////////////////////////////////////////////////////SAVE/EXPORT/IMPORT -- GENERAL///////////////////////////////////////////////////////////////////////////	
	
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
	 * This test case is the equivalent to the Testlink id: 
	 * MBOX-331: Upload the Configuration using an existing backup
	 * MBOX-712: Import file image correctly displayed
	 * @throws Exception 
	 */ 
	@Test(groups = { "Positive", "importConfigurationFile"} ,dependsOnMethods = "downloadConfigurationFile") 
	public void importConfigurationFile() throws Exception { 
		using(saveExportImportPage 
				.goImportTab(uiInstance.getDriver())
				.selectConfigurationFile(uiInstance.getDriver(),Common.configFile))	
		.check(saveExportImportPage.checkFileNameOnImportImage(uiInstance.getDriver(),Common.configFileName))
		
		.andUsing(saveExportImportPage
				.uploadConfigurationFile(uiInstance.getDriver())) 
		.check(saveExportImportPage.checkSuccesfulMessageWhenFileIsUploaded(uiInstance.getDriver()));

	} 

	/**
	 * This test case is the equivalent to the Testlink id:MBOX-339 Validation message when the user try to upload the Configuration without a file
	 */ 
	@Test(groups = { "Negative", "importConfigurationFile"}, dependsOnMethods = "importConfigurationFile") 
	public void validateWhenNoFileIsSelected() { 
		PageUtils.refreshPage(uiInstance.getDriver());
		using(saveExportImportPage
				.goImportTab(uiInstance.getDriver())
				.tryUploadConfigurationFile(uiInstance.getDriver(),""))				
		.check(saveExportImportPage.checkValidationWhenTheresNoFileSelected(uiInstance.getDriver())
				);

	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: MBOX-340: Validation message when the user try to upload the Configuration with a different format file
	 */ 
	@Test(groups = { "Negative", "validateWhenInvalidFileIsSelected"}, dependsOnMethods = "validateWhenNoFileIsSelected") 
	public void validateWhenInvalidFileIsSelected() { 
		String f = new File("src/main/resources/newAvatar.jpg").getAbsolutePath();
		PageUtils.refreshPage(uiInstance.getDriver());
		using(saveExportImportPage
				.goImportTab(uiInstance.getDriver())
				.tryUploadConfigurationFile(uiInstance.getDriver(),f))				
		.check(saveExportImportPage.checkValidationWhenInvalidFileIsSelected(uiInstance.getDriver())
				);
		PageUtils.refreshPage(uiInstance.getDriver());

	} 
	
	/**
	 * This test case is the equivalent to the Testlink id: 
	 *MBOX-621: Remove MIRO Configuration File
	 */ 
	@Test(groups = { "Positive", "removeSelectedFile"},dependsOnMethods = "validateWhenInvalidFileIsSelected") 
	public void removeSelectedFile() throws Exception { 
		using(saveExportImportPage 
				.goImportTab(uiInstance.getDriver())
				.selectConfigurationFile(uiInstance.getDriver(),Common.configFileName))	
		.check(saveExportImportPage.checkFileNameOnImportImage(uiInstance.getDriver(),Common.configFileName))
		
		.andUsing(saveExportImportPage
				.removeFile(uiInstance.getDriver())) 
		.check(saveExportImportPage.checkFileWasRemoved(uiInstance.getDriver(), Common.configFileName));

	}
	
	
	
}
