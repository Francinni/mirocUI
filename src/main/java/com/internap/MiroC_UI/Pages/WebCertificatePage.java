package com.internap.MiroC_UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.internap.MiroC_UI.Common.MyProjPage;

public class WebCertificatePage  extends MyProjPage {
	
	@FindBy(xpath = "//textarea[@name='privatekey']")
	private WebElement privateKey;
	
	@FindBy(xpath = "//textarea[@name='cert']")
	private WebElement certificate;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;
	
	
	
}
