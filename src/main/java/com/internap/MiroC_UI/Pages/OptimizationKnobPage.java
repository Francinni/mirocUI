package com.internap.MiroC_UI.Pages;

import org.testng.AssertJUnit;

import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.Common;
import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.nemo.javaexpect.term.CleanResultTelnet;
import com.ts.commons.Validator;
import com.ts.commons.RaceConditions.WaitTool;

public class OptimizationKnobPage extends MyProjPage {

	// components
	@FindBy(xpath = ".//a[@ng-click='!auth.admin||saveKnobs(knobsForm)'][contains(.,'Save')]/i")
	private WebElement saveKnobUserButton;

	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/a[1]")
	private WebElement realButton;

	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/a[2]")
	private WebElement simulationButton;

	// knobs
	@FindBy(xpath = "//div[@class='well']/h4[contains(.,'Cost')]/following-sibling:: div[@class='form-group']/div[@class='col-md-4']/input[@id='cost']")
	private WebElement costField;

	@FindBy(xpath = "//div[@class='well']/h4[contains(.,'Latency')]/following-sibling:: div[@class='form-group']/div[@class='col-md-4']/input[@id='cost']")
	private WebElement latencyField;

	@FindBy(xpath = "//div[@class='well']/h4[contains(.,'Loss')]/following-sibling:: div[@class='form-group']/div[@class='col-md-4']/input[@id='cost']")
	private WebElement lossField;

	@FindBy(xpath = "//div[@class='well']/h4[contains(.,'Max Capacity')]/following-sibling:: div[@class='form-group']/div[@class='col-md-4']/input[@id='cost']")
	private WebElement maxCapacityField;

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

	// metodos
	public void clearFields() {
		this.costField.clear();
		this.latencyField.clear();
		this.lossField.clear();
		this.maxCapacityField.clear();
	}

	// this method allow update the knobs
	public OptimizationKnobPage updateKnobs(WebDriver driver, int cost,
			int latency, int loss, int maxCapacity) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		clearFields();
		this.costField.sendKeys(Integer.toString(cost));
		this.latencyField.sendKeys(Integer.toString(latency));
		this.lossField.sendKeys(Integer.toString(loss));
		this.maxCapacityField.sendKeys(Integer.toString(maxCapacity));
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveKnobUserButton);
		return this;
	}

	// this method allow update the knobs
	public OptimizationKnobPage updateInvalidKnobs(WebDriver driver, int cost,
			int latency, int loss, int maxCapacity) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		clearFields();
		this.costField.sendKeys(Integer.toString(cost));
		this.latencyField.sendKeys(Integer.toString(latency));
		this.lossField.sendKeys(Integer.toString(loss));
		this.maxCapacityField.sendKeys(Integer.toString(maxCapacity));
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveKnobUserButton);
		return this;
	}

	// this method allow update the knobs
	public OptimizationKnobPage updateSimulationKnobs(WebDriver driver,
			int cost, int latency, int loss, int maxCapacity) {
		// reload page
		PageUtils.refreshPage(driver);
		this.simulationButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WaitTool.waitForElementPresentAndVisible(driver, costField);
		costField.clear();
		costField.clear();
		WaitTool.waitForElementPresentAndVisible(driver, lossField);
		lossField.clear();
		WaitTool.waitForElementPresentAndVisible(driver, latencyField);
		latencyField.clear();
		WaitTool.waitForElementPresentAndVisible(driver, maxCapacityField);
		maxCapacityField.clear();

		this.costField.sendKeys(Integer.toString(cost));
		this.latencyField.sendKeys(Integer.toString(latency));
		this.lossField.sendKeys(Integer.toString(loss));
		this.maxCapacityField.sendKeys(Integer.toString(maxCapacity));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveKnobUserButton);
		return this;
	}

	// negative test cases
	// this method allow update the knobs
	public OptimizationKnobPage updateInvalidSimulationKnobs(WebDriver driver,
			int cost, int latency, int loss, int maxCapacity) {
		// reload page
		//PageUtils.refreshPage(driver);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//this.simulationButton.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WaitTool.waitForElementPresentAndVisible(driver, costField);
		costField.clear();
		WaitTool.waitForElementPresentAndVisible(driver, lossField);
		lossField.clear();
		WaitTool.waitForElementPresentAndVisible(driver, latencyField);
		latencyField.clear();
		WaitTool.waitForElementPresentAndVisible(driver, maxCapacityField);
		maxCapacityField.clear();

		this.costField.sendKeys(Integer.toString(cost));
		this.latencyField.sendKeys(Integer.toString(latency));
		this.lossField.sendKeys(Integer.toString(loss));
		this.maxCapacityField.sendKeys(Integer.toString(maxCapacity));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveKnobUserButton);
		return this;
	}

	// validations

	// this method validates that the operation was successful
	public Validator assignmentListMustBePresent(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				WebElement successfulMessage = driver
						.findElement(By
								.xpath(".//div[@class='ng-scope']/div[contains(.,'Optimization knobs saved successfully')]/p"));
				boolean thereIsAnSuccessfulMessage = successfulMessage != null;
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}

	// this method validates that the operation was successful
	public Validator negativeValuesValidator(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean costMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[1]/div/div[2]/div/p[contains(.,'The value is below the minimum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						costMessage);
				boolean latencyMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[2]/div/div[2]/div/p[contains(.,'The value is below the minimum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						latencyMessage);
				boolean lossMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[3]/div/div[2]/div/p[contains(.,'The value is below the minimum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						lossMessage);
				boolean maxMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[4]/div/div[2]/div/p[contains(.,'The value is below the minimum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						maxMessage);
			}
		};
	}

	// this method validates that the operation was successful
	public Validator overValuesValidator(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean costMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[1]/div/div[2]/div/p[contains(.,'The value is above the maximum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						costMessage);
				boolean latencyMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[2]/div/div[2]/div/p[contains(.,'The value is above the maximum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						latencyMessage);
				boolean lossMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[3]/div/div[2]/div/p[contains(.,'The value is above the maximum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						lossMessage);
				boolean maxMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/form/div/div[4]/div/div[2]/div/p[contains(.,'The value is above the maximum')]"));
				Assert.assertTrue(
						"Password validation messages are not displayed",
						maxMessage);
			}
		};
	}

	// this method validates that miro user can not update the configuration
	public Validator miroUserValidator(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				String disabled = saveKnobUserButton.getAttribute("disabled");
				Assert.assertNotNull(disabled, "The element is enabled");
			}
		};
	}

}
