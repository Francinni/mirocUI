package com.internap.MiroC_UI.Common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageUtils {
	
	public static boolean isElementPresent(WebDriver driver, By by) {
		   
		   try {
		  
		   driver.findElement(by); 
		  
		   return true;     
		  } catch (Exception e) {
		   
		   return false;
		  }
		 }
	
		public static boolean isElementPresent(WebDriver driver, WebElement element) {
		   
		   try {
		  
		   element.isDisplayed(); 
		  
		   return true;     
		  } catch (Exception e) {
		   
		   return false;
		  }
		 }
		
		public static void refreshPage(WebDriver driver){
			
			driver.navigate().refresh();
		}

}
