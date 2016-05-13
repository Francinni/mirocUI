package com.internap.MiroC_UI.Pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
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
	
	//Change Password xpaths
	
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
	
	//Change Avatar xpaths
	
	@FindBy (xpath = "//a[text()='Change Avatar']")
	private WebElement changeAvatar;
	
	@FindBy (xpath = "//div[@class='fileUpload btn btn-primary']")
	private WebElement selectImageButton;
	
	@FindBy (xpath = "//input[@type='file']")
	private WebElement selectImage;
	
	@FindBy (xpath = "//img[@id='headerProfileImg']")
	private WebElement headerProfileImg;
	
	@FindBy (xpath = "//img[@id='profileImg']")
	private WebElement profileImg;
	
	@FindBy (xpath = "//button[contains(.,'Upload')]")
	private WebElement uploadButton;
	
	
	@FindBy (xpath = "//button[contains(.,'Remove')]")
	private WebElement removeButton;
	
	
	 ////////////////////////////////////////////////////////-----ACCOUNT INFO-------/////////////////////////////////////////////////////////////////////////
	
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
	
	 ////////////////////////////////////////////////////////-----CHANGE PASSWORD-------/////////////////////////////////////////////////////////////////////////
	
	public MyProfilePage goChangePassword (WebDriver driver){		
		this.changePassword.click();
		return PageFactory.initElements(driver, MyProfilePage.class);		
	}
	
	
	public MyProfilePage changePassword (WebDriver driver, String oldPass, String pass, String passConfirm){
			this.oldPassword.sendKeys(oldPass);
			this.password.sendKeys(pass); 		
			this.passwordConfirm.sendKeys(passConfirm);  
			this.submitPassword.click();
			return this;   
		}
	
	public MyProfilePage clearPassword (WebDriver driver){
		this.oldPassword.clear();	
		this.password.clear();
		this.passwordConfirm.clear();
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
	
	public Validator validateOldPasswordMatch(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean wrongOldPasswordValidation = PageUtils.isElementPresent(driver, By.xpath("//p[contains(.,'Change password failed for user with username admin old passwords do not match')]"));
				Assert.assertTrue(wrongOldPasswordValidation,"Old Password validation message not displayed");
 
			}
		};
	}
	
	public Validator oldAndNewPasswordShouldNotMatch(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean validationMessage = PageUtils.isElementPresent(driver, By.xpath("//p[contains(.,'Old password and new password should not match')]"));
				Assert.assertTrue(validationMessage,"Old and new password should not match message not dispplayed");
 
			}
		};
	}
	
	public Validator retypePasswordAndPasswordShouldMatch(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean validationMessage = PageUtils.isElementPresent(driver, By.xpath("//p[contains(.,'Password does not match')]"));
				Assert.assertTrue(validationMessage,"Validation Message does not show up when retype password and new password do not match");
 
			}
		};
	}
	
	public Validator validateEmptyValuesOnChangePassword(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean emptyValuesOldPasswordMessage = PageUtils.isElementPresent(driver, By.xpath("//ng-messages[@for = 'passwordForm.oldPassword.$error']/p[contains(.,'This value is required')]"));
				Assert.assertTrue(emptyValuesOldPasswordMessage,"Empty values messages are not displayed  in Change Password");
				boolean emptyValuesNewPasswordMessage = PageUtils.isElementPresent(driver, By.xpath("//ng-messages[@for = 'passwordForm.password.$error']/p[contains(.,'This value is required')]"));
				Assert.assertTrue(emptyValuesNewPasswordMessage,"Empty values messages are not displayed in Change Password");
				boolean emptyValuesRetypedPasswordMessage = PageUtils.isElementPresent(driver, By.xpath("//ng-messages[@for = 'passwordForm.passwordConfirm.$error']/p[contains(.,'This value is required')]"));
				Assert.assertTrue(emptyValuesRetypedPasswordMessage,"Empty values messages are not displayed in Change Password");
 
			}
		};
	}
	
	 ////////////////////////////////////////////////////////-----CHANGE AVATAR-------/////////////////////////////////////////////////////////////////////////
	
	public MyProfilePage goChangeAvatar(WebDriver driver){		
		this.changeAvatar.click();
		return PageFactory.initElements(driver, MyProfilePage.class);		
	}
	
	public MyProfilePage uploadImage (WebDriver driver, String imgPath){
		String f = new File(imgPath).getAbsolutePath();
		try {
			Thread.sleep(1500);
			this.selectImage.sendKeys(f);
		} catch (Exception e) {
			
		}
		
		try {
			Thread.sleep(1500);
			this.uploadButton.click();
		} catch (Exception e) {
			
		}		
		return this;   
	}
	
	public Validator avatarIsUploaded(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {							
				boolean thereIsASuccessfulMessage = PageUtils.isElementPresent(driver, By.xpath("//p[text()='File uploaded']"));
				Assert.assertTrue(thereIsASuccessfulMessage,"Succesful message for avatar changed is not displayed");					
			}
		};
	}
	
	public MyProfilePage cancelUploadImage (WebDriver driver){
		String f = new File("src/main/resources/newAvatar.jpg").getAbsolutePath();
		this.selectImage.sendKeys(f);
		try {
			Thread.sleep(1500);
			this.removeButton.click();
		} catch (Exception e) {
			
		}
		
		return this;   
	}
	

	public Validator avatarUploadIsCancelled(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {	
				boolean imgCancelled = PageUtils.isElementPresent(driver, removeButton);
				Assert.assertFalse(imgCancelled,"The image uploading was not cancelled");				
			}
		};
	}
	
	public Validator wrongFormatValidation(final WebDriver driver) {
		return new Validator() {
			@Override
			public void Validate() {	
				boolean wrongFormatMessage = PageUtils.isElementPresent(driver, By.xpath("//p[text()='Please upload your profile image in format: jpg, png, jpeg, bmp or gif']"));
				Assert.assertTrue(wrongFormatMessage,"Wrong Format Message is not displayed");				
			}
		};
	}
	
}
