package com.internap.MiroC_UI.Pages;


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
	
	@FindBy(xpath = "//input[@class='form-control ng-pristine ng-valid ng-touched']")
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
				boolean theValuesWereInserted = isInserted(first,last,em);
				Assert.assertTrue(theValuesWereInserted,"Values were not edited"); 
			}
		};
	}

	
	
}
