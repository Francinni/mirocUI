package com.internap.MiroC_UI.Home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Pages.MyProfilePage;

public class Home extends MyProjPage {
	
	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[3]/a/span[text()='Dashboard']")
	private WebElement dashboardTab;
	
	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[4]/a/span[text()='Traffic']")
	private WebElement TrafficTab;
	
	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[5]/a/span[text()=' Performance ']")
	private WebElement performanceTab;
	
	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[6]/a/span[text()=' Cost ']")
	private WebElement costTab;

	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[7]/a/span[text()=' Optimizations  ']")
	private WebElement optimizationsTab;
	
	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[10]/a/span[text()=' System Diagnostics ']")
	private WebElement systemDiagnosticsTab;
	
	@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/a/span[text()=' Configuration']")
	private WebElement configurationTab;

	@FindBy(xpath = "html/body/div[2]/div/div[3]/div/ul/li[2]/a")
	private WebElement userName;
	
	@FindBy(xpath = "html/body/div[2]/div/div[3]/div/ul/li[3]/i")
	private WebElement logout;
	
	@FindBy(xpath =  "//a[contains(.,'My Profile')]")
	private WebElement myprofile;
	
	@Override
	public Home and() {
		return this;
	}

	@Override
	public Home then() {
		return this;
	}
			
	public ConfigurationPage goConfigurationTab (WebDriver driver){
		this.configurationTab.click();
		return PageFactory.initElements(driver, ConfigurationPage.class);
	}
	
	public MyProfilePage goMyProfilePage (WebDriver driver){		
		this.userName.click();
		this.myprofile.click();
		return PageFactory.initElements(driver, MyProfilePage.class);		
	}
	
	
	
	
}
