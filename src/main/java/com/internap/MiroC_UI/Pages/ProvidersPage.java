package com.internap.MiroC_UI.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.*;

import com.google.common.collect.Ordering;
import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.ts.commons.Validator;

public class ProvidersPage extends MyProjPage{
	
	@FindBy(xpath = "//a[contains(.,'Add Provider')]")
	private WebElement addProviderButton;
	
	@FindBy(xpath = "//button[@class = 'btn btn-circle miab_outline_blue btn-outline']")
	private WebElement saveChangesButton;
	
	@FindBy(xpath = "//a[contains(., 'Real')]")
	private WebElement real;
	
	@FindBy(xpath = "//a[contains(., 'Simulation')]")
	private WebElement simulation;
	
	@FindBy(xpath = "//input[@name='asn']")
	private WebElement asnField;
	
	@FindBy(xpath = "//input[@name='commit']")
	private WebElement commitField;
	
	@FindBy(xpath = "//input[@name='cost']")
	private WebElement costField;
	
	@FindBy(xpath = "//input[@id='color']")
	private WebElement colorPicker;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	private WebElement cancelButton;

	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveAsnButton;
	
	@Override
	public MyProjPage and() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public MyProjPage then() {
		// TODO Auto-generated method stub
		return this;
	}
	
	// methods
	public void clearFields() {
		this.asnField.clear();
		this.costField.clear();
		this.commitField.clear();

	}
	
	// Method to insert a new Provider
	public ProvidersPage addProvider(WebDriver driver, int asn, int commit, int cost, String color) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		   executor.executeScript("arguments[0].click();", addProviderButton);
		clearFields();
		this.asnField.sendKeys(String.valueOf(asn));
		this.commitField.sendKeys(String.valueOf(commit));
		this.costField.sendKeys(String.valueOf(cost));
		this.colorPicker.sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),color);
		this.saveAsnButton.click();
		return this;
	}
	
	
	//Validates succesfull message is displayed
	public Validator providerIsSaved(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean thereIsAnSuccessfulMessage = PageUtils.isElementPresent(driver, By.xpath("//p [text()='Providers saved successfully']"), 20);
				Assert.assertTrue(thereIsAnSuccessfulMessage,"Succesful message is not displayed");

			}
		};
	}
	
	//Validates that the new asn is added to the list, checking its AS+asn value concatenation and rgb color value.
	public Validator providerIsAddedToList(final WebDriver driver, final String asn, final String hexColor) {
		return new Validator() {
			@Override
			public void Validate() {	
				boolean isElementDisplayed = PageUtils.
						isElementPresent(driver, By.xpath("//td[@class='ng-binding'][contains(.,'AS"+asn+"')]/..//span[contains(@style,'background-color: rgb("+PageUtils.hex2Rgb(hexColor)+");')]"), 20);
				Assert.assertTrue(isElementDisplayed,"Element not found in the table or color value is wrong");
				PageUtils.refreshPage(driver);
			}
		};
	}
	
	//Validates that elements are sorted by Name in the table
	public Validator providersAreSortedByName(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {	
				ArrayList<String> asnNames = new ArrayList<String>();
				List<WebElement> asns = driver.findElements(By.xpath("(//td[@class='ng-binding'])"));
				for(int i=0; i<=asns.size()-1;i++){
					if (i%2!=1)
						asnNames.add((asns.get(i).getText()).trim());
				}

				boolean isSorted = Ordering.natural().isOrdered(asnNames);
				Assert.assertTrue(isSorted,"Elements on the table are not sorted");

			}
		};
	}
	
	//Validates that the empty fields validation are not shown before clicking on Save
	public Validator emptyValidationIsNotShown(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				   executor.executeScript("arguments[0].click();", addProviderButton);
				boolean isElementDisplayed = PageUtils.
						isElementPresent(driver, By.xpath("//p[text()='This value is required']"),5);
				Assert.assertFalse(isElementDisplayed,"Validation MEssages for empty Fields are displayed even Save button has not been clicked");
				PageUtils.refreshPage(driver);
			}
		};
	}


}
