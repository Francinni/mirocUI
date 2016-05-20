package com.internap.MiroC_UI.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.internap.MiroC_UI.Common.MyProjPage;
import com.internap.MiroC_UI.Common.PageUtils;
import com.ts.commons.Validator;

public class OverridesPage extends MyProjPage{
	
	@FindBy(xpath = "//a[contains(@ng-click,'Commit Override')]")
	private WebElement addCommitButton;
	
	@FindBy(xpath = "//a[contains(@ng-click,'Capacity Override')]")
	private WebElement addCapacityButton;
	
	@FindBy(xpath = "//a[contains(@ng-click,'provider priority')]")
	private WebElement addPriorityButton;
	
	
	//Validates that a specific ASN value is in the 3 selects options
	public Validator asnIsInSelectOptions(final WebDriver driver, final String asn) {
		return new Validator() {
			@Override
			public void Validate() {
				
				int size = 0;
				boolean isInAllSelects = true;
				List<WebElement> asnInSelect = driver.findElements(By.xpath("//option[contains(.,'("+asn+")')]"));
				size = asnInSelect.size();
				System.out.println("Matches: " + size);
				if (size != 3) isInAllSelects = false;
				Assert.assertTrue(isInAllSelects,"Element not found in all/some Overrides Selects: # matches= "+size+"");
				PageUtils.refreshPage(driver);
			}
		};
	}

}
