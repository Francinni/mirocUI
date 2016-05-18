package com.internap.MiroC_UI.Pages;

import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.ts.commons.Validator;

import org.testng.Assert;

public class EditUserPage extends MyProjPage {

	// botones principales
	@FindBy(xpath = "html/body/div[4]/div[2]/div/div[2]/div/div/div[4]/div/div/div[1]/div[2]/a")
	private WebElement addUserButton;

	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'testAuto')]/following-sibling::td/a[1]/i")
	private WebElement editUserButton;

	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'testAuto')]/following-sibling::td/a[2]/i")
	private WebElement resetPasswordButton;

	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'testAuto')]/following-sibling::td/a[3]/i")
	private WebElement deleteUserButton;
	
	@FindBy(xpath = ".//tr[@ng-repeat='user in users']/td[1][contains(.,'admin')]/following-sibling::td/a[3]/i")
	private WebElement deleteOwnAccountButton;	

	// botones secundarios
	@FindBy(xpath = ".//*[@id='addUserForm']/div[2]/button[2]")
	private WebElement saveUserButton;

	@FindBy(xpath = ".//*[@id='addUserForm']/div[2]/button[1]")
	private WebElement cancelUserButton;

	// campos de texto utilizados al crear un user
	@FindBy(xpath = ".//*[@id='username']")
	private WebElement userNameField;

	@FindBy(xpath = ".//*[@id='addUserForm']/div[1]/div[2]/div/input")
	private WebElement firstNameField;

	@FindBy(xpath = ".//*[@id='addUserForm']/div[1]/div[3]/div/input")
	private WebElement lastNameField;

	@FindBy(xpath = ".//*[@id='addUserForm']/div[1]/div[4]/div/div/label/div/ins")
	private WebElement adminCheck;

	// campos utilizados para el resetear user pass

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

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/div/form/div[1]/div/small")
	private WebElement newPasswordValidator;

	@FindBy(xpath = ".//*[@id='tab_15_2']/div/div/form/div[2]/div/small[2]")
	private WebElement confirmPasswordValidator;

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
		this.userNameField.clear();
		this.firstNameField.clear();
		this.lastNameField.clear();
	}

	// metodo utilizado para crear un nuevo usuario
	public EditUserPage addUser(String user, String firstName, String lastName, boolean admin) {
		this.addUserButton.click();
		clearFields();
		this.userNameField.sendKeys(user);
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}
	
	// metodo utilizado para crear un user duplicado
	public EditUserPage duplicatedUser(WebDriver driver, String user, String firstName, String lastName, boolean admin) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		this.addUserButton.click();
		clearFields();
		this.userNameField.sendKeys(user);
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}

	// this method allows edit an user
	public EditUserPage editUser(WebDriver driver, String firstName,
			String lastName, boolean admin) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		this.editUserButton.click();

		this.firstNameField.clear();
		this.lastNameField.clear();

		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}

	public EditUserPage deleteUser() {
		this.deleteUserButton.click();
		return this;
	}

	public EditUserPage resetPassword(WebDriver driver) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		this.resetPasswordButton.click();
		this.systemResetButton.click();
		this.generatePasswordButton.click();
		this.updateResetButton.click();
		return this;
	}

	public EditUserPage manualResetPassword(WebDriver driver, String newPass,
			String confirmPass) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		this.resetPasswordButton.click();
		this.userResetButton.click();
		this.newPassword.sendKeys(newPass);
		this.confirmPassword.sendKeys(confirmPass);
		this.manualUpdateResetButton.click();
		return this;
	}

	// miroUser

	// this method allows create a miroUser
	public EditUserPage addMiroUser(WebDriver driver, String user,
			String firstName, String lastName, boolean admin) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.addUserButton.click();
		clearFields();
		this.userNameField.sendKeys(user);
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}

	// negative cases

	// password with less than 4 characters
	public EditUserPage lessChangePassword(WebDriver driver,
			String newPassword, String confirmPass) {
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.resetPasswordButton.click();
		this.userResetButton.click();
		this.newPassword.sendKeys(newPassword);
		this.confirmPassword.sendKeys(confirmPass);
		this.manualUpdateResetButton.click();
		return this;
	}
	
	
	public EditUserPage deleteOwnAccount(WebDriver driver){
		PageUtils.refreshPage(driver);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		this.deleteOwnAccountButton.click();
		return this;
	}
	

	// username with less tha 4 characters
	public EditUserPage shortUserName(String user, String firstName,
			String lastName, boolean admin) {
		this.addUserButton.click();
		clearFields();
		this.userNameField.sendKeys(user);
		this.firstNameField.sendKeys(firstName);
		this.lastNameField.sendKeys(lastName);
		this.adminCheck.equals(admin);
		this.saveUserButton.click();
		return this;
	}

	// this method validates that the operation was successful
	public Validator assignmentListMustBePresent(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				WebElement successfulMessage = driver
						.findElement(By
								.xpath(".//div[@class='ng-scope']/div[@ng-show='messages.length > 0']/p"));
				boolean thereIsAnSuccessfulMessage = successfulMessage != null;
				Assert.assertTrue(thereIsAnSuccessfulMessage);
			}
		};
	}

	// this method validates that the operation change password was successful
	public Validator validationNewPass(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean validationNewMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath(".//*[@id='tab_15_2']/div/div/form/div[1]/div/small[contains(.,'Password should contain at least 6 characters.')]"));
				Assert.assertTrue(validationNewMessage,
						"Password validation messages are not displayed");
				boolean validationConfirmMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath(".//*[@id='tab_15_2']/div/div/form/div[2]/div/small[2][contains(.,'Password does not match')]"));
				Assert.assertTrue(validationConfirmMessage,
						"Password validation messages are not displayed");

			}
		};
	}

	// this method validates that the operation create a user was successful
	public Validator validationuserName(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean validationNewMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath(".//*[@id='addUserForm']/div[1]/div[1]/div/ng-messages/div[contains(.,'Username is too short. Must be at least 4 characters')]"));
				Assert.assertTrue(validationNewMessage,
						"Username validation messages are not displayed");

			}
		};
	}

	// this method validates that the operation create a user was successful
	public Validator validationDeleteOwnAccount(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean validationNewMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[1]/div[1]/p[contains(.,'Unable to delete user')]"));
				Assert.assertTrue(validationNewMessage,
						"User can not delete its own account");

			}
		};
	}

	// this method validates that the operation create a user was successful
	public Validator validationDuplicateUser(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean validationNewMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[1]/div[1]/p[contains(.,'Username testAuto already exists')]"));
				Assert.assertTrue(validationNewMessage, "User already exists");

			}
		};
	}

	// this method validates that the operation create a user was successful
	public Validator validationLastLogin(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {
				boolean validationNewMessage = PageUtils
						.isElementPresent(
								driver,
								By.xpath("html/body/div[4]/div[2]/div/div[2]/div/div/div[4]/div/div/div[2]/div/table/tbody/tr[2]/td[5][contains(.,'Never')]"));
				Assert.assertTrue(validationNewMessage, "User Last Login");
			}
		};
	}

}
