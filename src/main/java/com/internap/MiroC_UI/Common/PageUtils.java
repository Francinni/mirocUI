package com.internap.MiroC_UI.Common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;

public class PageUtils {
	
	
	 public static boolean isElementPresent(WebDriver driver, By by, int waitSeconds) {
		   
		   try {
		   driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
		   driver.findElement(by); 
		   driver.manage().timeouts().implicitlyWait(Common.TIME_OUT, TimeUnit.SECONDS);
		   return true;     
		  } catch (Exception e) {
		    driver.manage().timeouts().implicitlyWait(Common.TIME_OUT, TimeUnit.SECONDS);
		   return false;
		  }
		 }
	 
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
		
		
		public static boolean isElementClickable(WebDriver driver, WebElement element) {
			   
			   try {
			  
			   element.click(); 
			  
			   return true;     
			  } catch (Exception e) {
			   
			   return false;
			  }
		}
		
		public static void refreshPage(WebDriver driver){
			
			driver.navigate().refresh();
		}
		
		public static FirefoxProfile firefoxProfile() {
			
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			
			try {
				
				firefoxProfile.setPreference("browser.download.folderList",2);
				firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
				firefoxProfile.setPreference("browser.download.dir",Common.downloadPath);
				firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/json");	
				
			} catch (Exception e) {
				// TODO: handle exception
			}
				
			return firefoxProfile;
		}
		
		public static boolean isFileDownloaded(String downloadPath, String fileName) {
			boolean flag = false;
		    File dir = new File(downloadPath);
		    File[] dir_contents = dir.listFiles();
		  	    
		    for (int i = 0; i < dir_contents.length; i++) {
		        if (dir_contents[i].getName().equals(fileName)){
		        	
		        	flag=true;
		        	try {
						Thread.sleep(5000);
						dir_contents[i].delete();
					} catch (Exception e) {
						// TODO: handle exception
					}
		        	
		        }
                else flag=false;
		    }

		    return flag;
		}
		


}
