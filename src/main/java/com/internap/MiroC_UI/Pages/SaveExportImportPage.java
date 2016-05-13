package com.internap.MiroC_UI.Pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	// Method to write running configuration
		public SaveExportImportPage writeRunningConfiguration(WebDriver driver,
				String portSecurity) {
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
				public Validator verifyInformationText(final WebDriver driver) {
					return new Validator() {

						@Override
						public void Validate() {
							String infoText = "Write running configuration to startup configuration to permanently save your current setup. MIRO Controller also allows you to export the current configuration or import a previously backed-up configuration.";
							boolean text = PageUtils
									.isElementPresent(
											driver,
											By.xpath("//li[contains(.,"+infoText+")]"));
							Assert.assertTrue(text);
						}
					};
				}

}
