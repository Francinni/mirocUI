package com.internap.MiroC_UI.Common;

import java.io.File;



public interface Common {

	/**
	 * Common urlLoginPageMiro is for the url of the login page in Miro
	 */
	public final String urlMiro = "https://miro-controller-qa.swenglab.internap.com";
	
	/**
	 * Common userName is the username for Admin automation testing
	 */
	public final String adminUserName = "admin";
	
	/**
	 * Common passWord is the password of the user for Admin automation testing
	 */
	public final String passWord = "password";
	
	/**
	 * Common userName is the username for mirousers role automation testing
	 */
	public final String userUserName = "user";
	
	/**
	 * Common passWord is the password of the user for mirousers role automation testing
	 */
	public final String userPassWord = "password";
	
	/**
	 * Common newPassword is the password use to change the current password automation testing
	 */
	public final String newPassWord = "123456";
	
	
	/**
	 * Common downloadPath to store downloaded files.
	 */
	public final String downloadPath = new File("src/main/resources/Downloads").getAbsolutePath();

	/**
	 * Common path when config.json is stored.
	 */
	public final String configFile = new File("src/main/resources/config.json").getAbsolutePath();
	
	
	public final int TIME_OUT = 15;
	
	
	public final String configFileName = "config.json";
	
	
	
}
