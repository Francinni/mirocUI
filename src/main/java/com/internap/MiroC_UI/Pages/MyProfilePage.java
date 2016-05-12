package com.internap.MiroC_UI.Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.internap.MiroC_UI.Home.Home;
import com.ts.commons.Validator;

public class MyProfilePage extends MyProjPage {
	
	@FindBy(xpath = "//input[@class='form-control ng-pristine ng-untouched ng-valid']")
	private WebElement username;
	
	@FindBy(xpath = "//input[@name='firstname']")
	private WebElement firstname;
	
	@FindBy(xpath = "//input[@name='lastname']")
	private WebElement lastname;
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement email;  

	@FindBy(xpath = "(//button[text()='Cancel'])[1]")
	private WebElement cancel;
	
	@FindBy(xpath = "(//button[text()='Submit'])[1]")
	private WebElement submit;
	
	@FindBy(xpath = "//a[text()='Account Info']")
	private WebElement AccountInfo;
	
	@FindBy (xpath = "//span[@class= 'username username-hide-on-mobile ng-binding']")
	private WebElement Header;
	
	@FindBy (xpath = "//a[text()='Change Password']")
	private WebElement changePassword;
	
	@FindBy (xpath = "//input[@name = 'oldPassword']")
	private WebElement oldPassword;
	
	@FindBy (xpath = "//input[@name = 'password']")
	private WebElement password;
	
	@FindBy (xpath = "//input[@name = 'passwordConfirm']")
	private WebElement passwordConfirm;
	
	@FindBy(xpath = "(//button[text()='Submit'])[2]")
	private WebElement submitPassword;
	
	@FindBy(xpath = "(//button[text()='Cancel'])[2]")
	private WebElement cancelPassword;
	
	
	
	
	
	
	public MyProfilePage editMyProfile ( String firstname, String lastname, String email){
		
		this.firstname.clear();	
		this.lastname.clear();
		this.email.clear();
		this.firstname.sendKeys(firstname);
		this.lastname.sendKeys(lastname); 		
		this.email.sendKeys(email);  
		this.submit.click();
		return this;   
	}
	
	public boolean isInserted( String firstname, String lastname, String email){
		
		if (this.firstname.getAttribute("value").equals(firstname) && this.lastname.getAttribute("value").equals(lastname) && this.email.getAttribute("value").equals(email))
			return true;
		else return false;				
		
	} 

	public Validator userIsEdited(final WebDriver driver, final String first, final String last, final String em) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean thereIsAnSuccessfulMessage = PageUtils.isElementPresent(driver, By.xpath("//p[text()='User successfully edited']"));
				Assert.assertTrue(thereIsAnSuccessfulMessage,"Succesful message is not displayed");

			}
		};
	}
	
	public Validator usernameFieldIsDisabled(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {	
				String readonly = username.getAttribute("readonly");
				Assert.assertNotNull(readonly,"The element is enabled");
			}
		};
	}
	
	public Validator verifyUserInfoOnHeader(final WebDriver driver,final String firstN, final String lastN) {
		return new Validator() {
			@Override
			public void Validate() {
				String ruta = "//span[contains(.,'"+firstN+" "+lastN+"')]";
				PageUtils.refreshPage(driver);
				
				boolean theHeaderIsTheSame = PageUtils.isElementPresent(driver, By.xpath(ruta));
				Assert.assertTrue(theHeaderIsTheSame,"Header and user info are not the same");

			}
		};
	}
	
	public Validator validateEmptyValues(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean emptyValuesFirstnameMessage = PageUtils.isElementPresent(driver, By.xpath("//ng-messages[@for = 'signupForm.firstname.$error']/p[contains(.,'This value is required')]"));
				Assert.assertTrue(emptyValuesFirstnameMessage,"Empty values messages are not displayed");
				boolean emptyValuesLastnameMessage = PageUtils.isElementPresent(driver, By.xpath("//ng-messages[@for = 'signupForm.lastname.$error']/p[contains(.,'This value is required')]"));
				Assert.assertTrue(emptyValuesLastnameMessage,"Empty values messages are not displayed");
 
			}
		};
	}
	
	                         //CHANGE PASSWORD
	
	public MyProfilePage goChangePassword (WebDriver driver){		
		this.changePassword.click();
		return PageFactory.initElements(driver, MyProfilePage.class);		
	}
	
	
	public MyProfilePage changePassword ( String oldPass, String pass, String passConfirm){
			
			this.oldPassword.clear();	
			this.password.clear();
			this.passwordConfirm.clear();
			this.oldPassword.sendKeys(oldPass);
			this.password.sendKeys(pass); 		
			this.passwordConfirm.sendKeys(passConfirm);  
			this.submitPassword.click();
			return this;   
		}
	

	public Validator passwordIsChanged(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean thereIsAnSuccessfulMessage = PageUtils.isElementPresent(driver, By.xpath("//p[text()='User password changed']"));
				Assert.assertTrue(thereIsAnSuccessfulMessage,"Succesful message for password changed is not displayed");

			}
		};
	}
	
}
