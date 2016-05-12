package com.internap.MiroC_UI.Pages;

import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.ts.commons.Validator;
import com.ts.commons.RaceConditions.WaitTool;

public class PortSecurityPage extends MyProjPage {

	// Allowed networks

	@FindBy(xpath = ".//label[@ng-click='!auth.admin||addPrefix(prefixForm)']")
	private WebElement addNetworkButton;

	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[1]/div[2]/div[1]/div/input")
	private WebElement networkFIeld;

	@FindBy(xpath = "(.//div[@class='input-group']/span[@class='input-group-addon'][@ng-click='prefix.disabled=!prefix.disabled'])[1]")
	private WebElement deleteNetworkButton;

	// Blocked Ports

	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[2]/div[1]/div[2]/div/label")
	private WebElement addPortButton;

	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[2]/div[2]/div[1]/div/input")
	private WebElement portField;

	@FindBy(xpath = "(.//div[@class='input-group']/span[@class='input-group-addon'][@ng-click='port.disabled=!port.disabled'])[1]")
	private WebElement deletePortButton;

	// General

	@FindBy(xpath = ".//div[@class='actions']/a[contains(.,'Save')]")
	private WebElement saveButton;

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

	// Method to add a port security
	public PortSecurityPage addPortSecurity(WebDriver driver,
			String portSecurity) {
		WaitTool.waitForElementPresentAndVisible(driver, addPortButton);
		this.addPortButton.click();

		this.portField.sendKeys(portSecurity);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveButton);
		return this;
	}

	// Method to edit a port security
	public PortSecurityPage editPortSecurity(WebDriver driver,
			String portSecurity, String newport) {
		PageUtils.refreshPage(driver);
		List<WebElement> element2 = driver
				.findElements(By
						.xpath(".//div[@class='input-group']/input[@ng-model='port.value']"));
		int count = elementsList(element2, portSecurity) - 1;

		String xpath = ".//input[@name='port" + count + "']";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(newport);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveButton);
		return this;
	}

	// Method to delete a port security
	public PortSecurityPage deletePortSecurity(WebDriver driver,
			String portSecurity) {
		String xpath;
		PageUtils.refreshPage(driver);
		List<WebElement> element2 = driver
				.findElements(By
						.xpath(".//div[@class='input-group']/input[@ng-model='port.value']"));

		xpath = "(.//div[@class='input-group']/span[@class='input-group-addon'][@ng-click='port.disabled=!port.disabled'])["
				+ elementsList(element2, portSecurity) + "]";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();

		this.saveButton.click();
		return this;
	}

	// Method to add a network security
	public PortSecurityPage addAllowedNetwork(WebDriver driver,
			String allowedNetwork) {
		PageUtils.refreshPage(driver);
		WaitTool.waitForElementPresentAndVisible(driver, addNetworkButton);
		this.addNetworkButton.click();

		this.networkFIeld.sendKeys(allowedNetwork);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveButton);
		return this;
	}

	// Method to edit an allowed network
	public PortSecurityPage editAllowedNetwork(WebDriver driver,
			String network, String newNetwork) {
		PageUtils.refreshPage(driver);
		List<WebElement> element2 = driver
				.findElements(By
						.xpath(".//div[@class='input-group']/input[@ng-model='prefix.value']"));
		int count = elementsList(element2, network) - 1;

		String xpath = ".//input[@name='prefix" + count + "']";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.clear();
		element.sendKeys(newNetwork);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", saveButton);
		return this;
	}

	// Method to delete an allowed network
	public PortSecurityPage deleteAllowedNetwork(WebDriver driver,
			String allowedNetwork) {
		String xpath;
		PageUtils.refreshPage(driver);
		List<WebElement> element2 = driver
				.findElements(By
						.xpath(".//div[@class='input-group']/input[@ng-model='prefix.value']"));

		xpath = "(.//div[@class='input-group']/span[@class='input-group-addon'][@ng-click='prefix.disabled=!prefix.disabled'])["
				+ elementsList(element2, allowedNetwork) + "]";
		WebElement element = driver.findElement(By.xpath(xpath));
		element.click();

		this.saveButton.click();
		return this;
	}

	// Method that return the position to be edited or deleted a port security
	public int elementsList(List<WebElement> element2, String sendValue) {
		int count = 1;
		String value;
		int size = 0;

		for (WebElement element : element2) {
			value = element.getAttribute("value");
			if (value.equals(sendValue)) {
				size = count;
				break;
			} else
				count++;
		}
		return size;
	}

	// Validator for successful message on Port security
	public Validator successfulMessageShouldBePresented(final WebDriver driver) {
		return new Validator() {

			@Override
			public void Validate() {

				boolean thereIsAnSuccessfulMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath(".//div[@class='ng-scope']/div[@ng-show='messages.length > 0']/p"));
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}

	// Validator for Port security
	public Validator existentPortSecurity(final WebDriver driver,
			final String sendValue) {
		return new Validator() {

			@Override
			public void Validate() {
				String xpath = ".//p[contains(.,'" + sendValue
						+ " is a duplicate')]";
				WebElement element = driver.findElement(By.xpath(xpath));
				boolean thereIsAnSuccessfulMessage = PageUtils
						.isElementPresent(driver, element);
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}

}
