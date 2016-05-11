package com.internap.MiroC_UI.Pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.ts.commons.Validator;


public class EditUserPage extends MyProjPage {
	
	//botones principales
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[4]/div/div/div[1]/div[2]/a")
	private WebElement addUserButton;
	
	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'testAuto')]/following-sibling::td/a[1]/i")
	private WebElement editUserButton;
	
	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'testAuto')]/following-sibling::td/a[2]/i")
	private WebElement resetPasswordButton;
	
	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'testAuto')]/following-sibling::td/a[3]/i")
	private WebElement deleteUserButton;
	
	//botones secundarios
	@FindBy(xpath = ".//*[@id='addUserForm']/div[2]/button[2]")
	private WebElement saveUserButton;
	
	@FindBy(xpath = ".//*[@id='addUserForm']/div[2]/button[1]")
	private WebElement cancelUserButton;
	
	//campos de texto utilizados al crear un user
	@FindBy(xpath = ".//*[@id='username']")
	private WebElement userNameField;
	
	@FindBy(xpath = ".//*[@id='addUserForm']/div[1]/div[2]/div/input")
	private WebElement firstNameField;
	
	@FindBy(xpath = ".//*[@id='addUserForm']/div[1]/div[3]/div/input")
	private WebElement lastNameField;
	
	@FindBy(xpath = ".//*[@id='addUserForm']/div[1]/div[4]/div/div/label/div/ins")
	private WebElement adminCheck;
	

	//campos utilizados para el resetear user pass
	
	@FindBy(xpath = ".//*[@id='tab_15_1']/div/div/form/div[3]/button[2]")						
	private WebElement updateResetButton;
	
	@FindBy(xpath = ".//*[@id='tab_15_2']/div/div/form/div[3]/button[2]")						
	private WebElement manualUpdateResetButton;
	
	@FindBy(xpath = ".//*[@id='tab_15_1']/div/div/form/div[3]/button[1]")
	private WebElement cancelResetButton;
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/div/ul/li[1]/a")
	private WebElement systemResetButton;
	
	@FindBy(xpath = ".//*[@id='tab_15_1']/div/div/form/div[2]/div/a")
	private WebElement generatePasswordButton;
	
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[3]/div/div/div[2]/div/ul/li[2]/a")
	private WebElement userResetButton;
	
	@FindBy(xpath = ".//*[@id='tab_15_2']/div/div/form/div[1]/div/input")
	private WebElement newPassword;
	
	@FindBy(xpath = ".//*[@id='tab_15_2']/div/div/form/div[2]/div/input")
	private WebElement confirmPassword;
	
	
	
	
	
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
	
	
	//methods
	public void clearFields(){
		this.userNameField.clear();
		this.firstNameField.clear();
		this.lastNameField.clear();		
	}
	
	//metodo utilizado para crear un nuevo usuario
	public EditUserPage addUser (String user, String firstName, String lastName, boolean admin){
		this.addUserButton.click();
		clearFields();
		this.userNameField.sendKeys(user);
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}
	
	//this method allows edit an user
	public EditUserPage editUser (String firstName, String lastName, boolean admin){
		this.editUserButton.click();
		
		this.firstNameField.clear();
		this.lastNameField.clear();	
		
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}
	
	
	public EditUserPage deleteUser(){
		this.deleteUserButton.click();
		return this;
	}
	
	public EditUserPage resetPassword(){
		this.resetPasswordButton.click();
		this.systemResetButton.click();
		this.generatePasswordButton.click();
		this.updateResetButton.click();
		return this;
	}
	
	public EditUserPage manualResetPassword(String newPass, String confirmPass){
		this.resetPasswordButton.click();
		this.userResetButton.click();
		this.newPassword.sendKeys(newPass);
		this.confirmPassword.sendKeys(confirmPass);
		this.manualUpdateResetButton.click();
		return this;
	}
	
	
	//this method validates that the operation was successful
	public Validator assignmentListMustBePresent(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				WebElement successfulMessage = driver
						.findElement(By.xpath(".//div[@class='ng-scope']/div[@ng-show='messages.length > 0']/p"));
				boolean thereIsAnSuccessfulMessage = successfulMessage != null;
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}	
}
