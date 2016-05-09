package com.internap.MiroC_UI.Home;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.ts.commons.Validator;

public class PortSecurityPage extends MyProjPage {
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[1]/div[1]/div[2]/div/label")
	private WebElement addNetworkButton;
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[1]/div[2]/div[1]/div/input")
	private WebElement networkFIeld;
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[2]/div[1]/div[2]/div/label[@ng-click=\"!auth.admin||addPort(portForm)\"]")
	private WebElement addPortButton;
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[2]/div/form[2]/div[2]/div[1]/div/input")
	private WebElement portField;
	
	@FindBy(xpath = "(.//div[@class='input-group']/span[@class='input-group-addon'][@ng-click='port.disabled=!port.disabled'])[1]")
	private WebElement deleteButton;
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[2]/div/div/div[1]/div[2]/a")
	private WebElement saveButton;
	
/*	@FindBy(xpath = "(.//div[@class='input-group']/input)[1]")
	private WebElement portField;
	
	@FindBy(xpath = "(.//div[@class='input-group']/span[@class='input-group-addon'][@ng-click='port.disabled=!port.disabled'])[1]")
	private WebElement deleteButton;
	
	@FindBy(xpath = ".//div[@class='actions']/a[1]/i[@class='fa fa-save']")
	private WebElement saveButton;*/

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
	
	public PortSecurityPage addPortSecurity (String portSecurity){
		this.addPortButton.click();
		this.portField.clear();
		this.portField.sendKeys(portSecurity);
		this.saveButton.click();
		return this;
	}
	
	public PortSecurityPage editPortSecurity (String portSecurity){
		this.portField.clear();
		this.portField.sendKeys(portSecurity);
		this.saveButton.click();
		return this;
	}
	
	public PortSecurityPage deletePortSecurity (String portSecurity){
		this.deleteButton.click();
		this.saveButton.click();
		return this;
	}
	
	public Validator assignmentListMustBePresent(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				WebElement successfulMessage = driver
						.findElement(By.xpath(".//div[@class='ng-scope']/div[@ng-show='messages.length > 0']/p"));
						//.findElement(By.xpath(".//div[@class='ng-scope']/div[@ng-show='messages.length > 0']/p[contains(text(),'Port security saved successfully')]"));
//.findElement(By.xpath(".//div[@class='ng-scope']/div[@ng-show='messages.length > 0']/p[@text='Port security saved successfully']"));
				boolean thereIsAnSuccessfulMessage = successfulMessage != null;
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}

	
}
