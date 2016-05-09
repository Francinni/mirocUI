package com.internap.MiroC_UI.Home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.internap.MiroC_UI.Common.MyProjPage;

public class ConfigurationPage extends MyProjPage {
	
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[1]/a")
		private WebElement authenticationTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[2]/a")
		private WebElement portSecurityTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[3]/a")
		private WebElement saveImportExportTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[4]/a")
		private WebElement webCertificateTab;

		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[5]/a")
		private WebElement routersTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[6]/a")
		private WebElement optimizationTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[7]/a")
		private WebElement controlTab;

		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[8]/a")
		private WebElement globalTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[9]/a")
		private WebElement userTab;
	
	@Override
	public ConfigurationPage and() {
		return this;
	}

	@Override
	public ConfigurationPage then() {
		return this;
	}
	
	public PortSecurityPage goPortSecurity (WebDriver driver){
		this.portSecurityTab.click();
		return PageFactory.initElements(driver, PortSecurityPage.class);
	}

	
}
