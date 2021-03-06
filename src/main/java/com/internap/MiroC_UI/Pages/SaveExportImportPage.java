package com.internap.MiroC_UI.Pages;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

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
									By.xpath("//p[text()='Write to memory successfully']"), 5);
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
					
					Assert.assertTrue("Failed to download Expected file", PageUtils.isFileDownloaded(Common.downloadPath, Common.configFileName));
				}
			};
		}
		
       /////////////////////////////////////////////////////////////////////IMPORT///////////////////////////////////////////////////////////////////////////////////////
		
		public SaveExportImportPage goImportTab(WebDriver driver){		
			this.importTab.click();
			return PageFactory.initElements(driver, SaveExportImportPage.class);		
		}
		
		// Method to select the configuration file
		public SaveExportImportPage selectConfigurationFile(WebDriver driver, final String file)throws Exception {
			
			this.selectFileButton.click();
			Thread.sleep(2000);
			StringSelection s=new StringSelection(file);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s,null);
			Robot robot=new Robot();
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
			robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyPress(java.awt.event.KeyEvent.VK_V);
			robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
			Thread.sleep(3000);
			robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
			
			return this;	
		}
		
		// Method to upload the configuration file
		public SaveExportImportPage uploadConfigurationFile(WebDriver driver) {
			this.starUploadButton.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			return this;	
		}	
		
		
		// Method to directly upload the configuration file without the pop up window
		public SaveExportImportPage tryUploadConfigurationFile(WebDriver driver, final String file) {
			this.selectFileButton.sendKeys(file);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			this.starUploadButton.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			return this;	
		}	
		
		// Method to remove a selected file
		public SaveExportImportPage removeFile(WebDriver driver) {
			this.removeFileButton.click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
					
					boolean isMessageDisplayed = PageUtils.isElementPresent(driver, By.xpath("//p[text()='Config imported successfully']"),10);
					Assert.assertTrue("Succesful Message when the file is uploaded is not displayed",isMessageDisplayed);
				}
			};
		}
		
		// Validator to check if error message is displayed when  no file is selected
		public Validator checkValidationWhenTheresNoFileSelected(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {
					
					boolean isMessageDisplayed = PageUtils.isElementPresent(driver, By.xpath("//p[contains(.,'No file selected')]"),10);
					Assert.assertTrue("Validation Message when the file isn't selected is not displayed",isMessageDisplayed);
				}
			};
		}

		
		// Validator to check if error message is displayed when a non valid file is selected
		public Validator checkValidationWhenInvalidFileIsSelected(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {
					
					boolean isMessageDisplayed = PageUtils.isElementPresent(driver, By.xpath("//p[contains(.,'Uploaded file is not valid json')]"),10);
					Assert.assertTrue("Validation Message when the file is invalid is not displayed",isMessageDisplayed);
				}
			};
		}
		
		// Validator to check that file selected was removed
		public Validator checkFileWasRemoved(final WebDriver driver,final String fileName) {
			return new Validator() {
				@Override
				public void Validate() {
					
					boolean isFileRemoved = PageUtils.isElementPresent(driver, By.xpath("//div[@class='fileinput-preview fileinput-exists thumbnail'][contains(.,'"+fileName+"')]"));
					Assert.assertFalse("The file was not removed",isFileRemoved);
				}
			};
		}
				
			
				
}
