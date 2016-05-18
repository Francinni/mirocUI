package com.internap.MiroC_UI.Pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.ts.commons.Validator;
import com.ts.commons.RaceConditions.WaitTool;

public class SaveExportImportPage extends MyProjPage {
	
	//SAVE
	@FindBy(xpath = "//a[@id='save']")
	private WebElement saveTab;
	
	@FindBy(xpath = "//a[contains(.,'Write Memory')]")
	private WebElement writeMemoryButton;
	
	@FindBy(xpath = "//a[@id='export']")
	private WebElement exportTab;
	
	@FindBy(xpath = "//a[contains(.,'Download')]")
	private WebElement downloadButton;
	
	@FindBy(xpath = "//a[@id='import']")
	private WebElement importTab;
	
	@FindBy(xpath = "//input[@id='uploadFileName']")
	private WebElement selectFileButton;
	
	@FindBy(xpath = "//button[contains(.,'Start upload')]")
	private WebElement starUploadButton;
	
	@FindBy(xpath = "//input[@id='uploadFileName']")
	private WebElement changeFileButton;
	
	@FindBy(xpath = "//a[contains(., 'Remove')]")
	private WebElement removeFileButton;
	
	// Method to write running configuration
		public SaveExportImportPage writeRunningConfiguration(WebDriver driver) {
			WaitTool.waitForElementPresentAndVisible(driver, writeMemoryButton);
			this.writeMemoryButton.click();
			return this;
		}
		
		// Validator to verify succesful message when write to memory
		public Validator successfulMessageShouldBePresentedOnSave(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {

					boolean WriteMemorySuccesfullMessage = PageUtils
							.isElementPresent(
									driver,
									By.xpath("//p[text()='Write to memory successfully']"));
					Assert.assertTrue(WriteMemorySuccesfullMessage);
				}
			};
		}
	
		// Validator to verify information text 
		public Validator checkInformationText(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {
					boolean text = PageUtils
							.isElementPresent(
									driver,
									By.xpath("//li[contains(.,'Write running configuration to startup configuration to permanently save your current setup. MIRO Controller also allows you to export the current configuration or import a previously backed-up configuration.')]"));
					Assert.assertTrue(text);
				}
			};
		}
		/////////////////////////////////////////////////////////////////////EXPORT///////////////////////////////////////////////////////////////////////////////////////		
		
		public SaveExportImportPage goExportTab(WebDriver driver){		
			this.exportTab.click();
			return PageFactory.initElements(driver, SaveExportImportPage.class);		
		}	
		
		// Method to download the configuration file
		public SaveExportImportPage exportConfigurationFile(WebDriver driver) {
			WaitTool.waitForElementPresentAndVisible(driver, downloadButton);
			try {
				this.downloadButton.click();
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return this;
		}	
		
		// Validator to check is file was downloaded 
		public Validator checkFileDownload(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {
					//driver.get(Common.downloadPath);
					
					Assert.assertTrue("Failed to download Expected file", PageUtils.isFileDownloaded(Common.downloadPath, "config.json"));
				}
			};
		}
		
       /////////////////////////////////////////////////////////////////////IMPORT///////////////////////////////////////////////////////////////////////////////////////
		
		public SaveExportImportPage goImportTab(WebDriver driver){		
			this.importTab.click();
			return PageFactory.initElements(driver, SaveExportImportPage.class);		
		}
		
		// Method to select a configuration file
		public SaveExportImportPage selectConfigurationFile(WebDriver driver) {
			
			try {
				
				this.selectFileButton.sendKeys(Common.configFile);
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
						
			return this;
		}	
		
		// Method to upload the configuration file
		public SaveExportImportPage uploadConfigurationFile(WebDriver driver) {
			
			try {
				this.starUploadButton.click();
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return this;
		}	
		
		// Validator to check is file was downloaded 
		public Validator checkFileNameOnImportImage(final WebDriver driver, final String fileName) {
			return new Validator() {
				@Override
				public void Validate() {
					
					boolean isFileNameDisplayed = PageUtils.isElementPresent(driver, By.xpath("//div[@class='fileinput-preview fileinput-exists thumbnail'][contains(.,'"+fileName+"')]"));
					Assert.assertTrue("The file name is not displayed or do not corresond to the file selected",isFileNameDisplayed);
				}
			};
		}
		
		// Validator to check is file was downloaded 
		public Validator checkSuccesfulMessageWhenFileIsUploaded(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {
					
					boolean isMessageDisplayed = PageUtils.isElementPresent(driver, By.xpath("//p[text()='Config imported successfully']"));
					Assert.assertTrue("Succesful Message when the file is uploaded is not displayed",isMessageDisplayed);
				}
			};
		}
		

}
