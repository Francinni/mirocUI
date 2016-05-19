package com.internap.MiroC_UI.Home;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.internap.MiroC_UI.Pages.AuthenticationPage;
import com.internap.MiroC_UI.Pages.EditUserPage;
import com.internap.MiroC_UI.Pages.OptimizationKnobPage;
import com.internap.MiroC_UI.Pages.PortSecurityPage;
import com.internap.MiroC_UI.Pages.ProvidersPage;
import com.internap.MiroC_UI.Pages.SaveExportImportPage;
import com.internap.MiroC_UI.Pages.WebCertificatePage;
import com.ts.commons.Validator;

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
		
		//optimization knobs
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[6]/ul/li[1]/a")
		private WebElement knobsTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[7]/a")
		private WebElement controlTab;

		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[8]/a")
		private WebElement globalTab;
		
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[9]/a")
		private WebElement userTab;
		
		//edit user
		@FindBy(xpath = ".//*[@id='sidebar']/ul/li[11]/ul/li[9]/ul/li/a")
		//@FindBy(xpath = "//a[contains(.,'Edit Users ')]")
		private WebElement editUserTab;
		
		
	
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
	
	//edit user
	public EditUserPage goEditUser (WebDriver driver){
		//this.userTab.click();
		WebElement element = driver.findElement(By.xpath(".//*[@id='sidebar']/ul/li[11]/ul/li[9]/a"));
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", element);
		
		WebElement element2 = driver.findElement(By.xpath(".//*[@id='sidebar']/ul/li[11]/ul/li[9]/ul/li/a"));
		  JavascriptExecutor executor2= (JavascriptExecutor)driver;
		  executor2.executeScript("arguments[0].click();", element2);
		
		return PageFactory.initElements(driver, EditUserPage.class);
	}
	
	//optimization knob
	public OptimizationKnobPage goOptimizationKnob (WebDriver driver){
		//this.userTab.click();
		WebElement element = driver.findElement(By.xpath(".//*[@id='sidebar']/ul/li[11]/ul/li[6]/a"));
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", element);
		
		WebElement element2 = driver.findElement(By.xpath(".//*[@id='sidebar']/ul/li[11]/ul/li[6]/ul/li[1]/a"));
		  JavascriptExecutor executor2= (JavascriptExecutor)driver;
		  executor2.executeScript("arguments[0].click();", element2);
		
		return PageFactory.initElements(driver, OptimizationKnobPage.class);
	}
	
	//Save /Export / Import
		public SaveExportImportPage goSaveExportImport (WebDriver driver){

			  JavascriptExecutor executor = (JavascriptExecutor)driver;
			  executor.executeScript("arguments[0].click();", this.saveImportExportTab);
			
			return PageFactory.initElements(driver, SaveExportImportPage.class);
		}
		
		public AuthenticationPage goAuthentication (WebDriver driver){

			  JavascriptExecutor executor = (JavascriptExecutor)driver;
			  executor.executeScript("arguments[0].click();", this.authenticationTab);
			
			return PageFactory.initElements(driver, AuthenticationPage.class);
		}
		
		public WebCertificatePage goWebCertificate (WebDriver driver){

			  JavascriptExecutor executor = (JavascriptExecutor)driver;
			  executor.executeScript("arguments[0].click();", this.webCertificateTab);
			
			return PageFactory.initElements(driver, WebCertificatePage.class);
		}
		
		public Validator webCertificateNotShownForNonAdmins(final WebDriver driver) {
			return new Validator() {
				@Override
				public void Validate() {	
					boolean isWebCertificateDisplayed = PageUtils.isElementClickable(driver, webCertificateTab);
					Assert.assertFalse(isWebCertificateDisplayed,"Web Certificate Option is displayed for non admin users");
				}
			};
		}
		
		public ProvidersPage goProviders (WebDriver driver){

			WebElement element = driver.findElement(By.xpath(".//*[@id='sidebar']/ul/li[11]/ul/li[6]/a"));
			  JavascriptExecutor executor = (JavascriptExecutor)driver;
			  executor.executeScript("arguments[0].click();", element);
			
			WebElement element2 = driver.findElement(By.xpath(".//*[@id='sidebar']/ul/li[11]/ul/li[6]/ul/li[2]/a"));
			  JavascriptExecutor executor2= (JavascriptExecutor)driver;
			  executor2.executeScript("arguments[0].click();", element2);
			
			return PageFactory.initElements(driver, ProvidersPage.class);
		}

	
}
