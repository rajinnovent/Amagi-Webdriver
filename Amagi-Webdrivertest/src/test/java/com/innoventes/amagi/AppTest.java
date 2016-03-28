package com.innoventes.amagi;

import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.innoventes.amagi.Amagi_DSRPage;

/**
 * Unit test for simple App.
 */
public class AppTest
{
	WebDriver driver;
	String baseurl;
	Amagi_DSRPage login;
		
	    @Before public void setUp(){
	  
			driver = new HtmlUnitDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(
		    		 	Level.OFF);
		    
		    baseurl = "http://www.innoventestech.in/";
		    driver.get(baseurl +"amagi-dsr");
			login= new Amagi_DSRPage(driver);
		}
		@Test
		public void adminlinktest_dsrcheck() throws InterruptedException{
			login.invalidlogin("Admin","raju.cva+09@gmail.com","c789d4");
			login.logintest("Admin","admin@admin.com","innoventes");
			login.adminlinktest();
		    login.admin_dsrcheck("Client/Agency","fanta");
		    login.adduserform();
		}
		
	    @Test
	    public void normaluserlinktestanddsrentrytes()throws InterruptedException{
	    	login.logintest("Normal User","raju.cva+09@gmail.com","c789d4");
	    	login.normaluser_dsrcheck("Client/Agency","Cannon");
	    	Thread.sleep(2000);
	    	login.normaluser_dsrcheck("Brands/Key Markets","Marico");
	    	AssertJUnit.assertTrue(isTextPresent("Marico"));
	    	
	    }
	    @Test
		public void userhierachytest()throws InterruptedException{
		   	login.logintest("Normal User","raju.cva+09@gmail.com","c789d4");
	        login.userhierachytest("Client/Agency","shsj");
	        AssertJUnit.assertTrue(isTextPresent("Raj Kumar"));
	    }
	    private boolean isTextPresent(String message) {
			
			return driver.getPageSource().contains(message);
		}
		@After public void quit(){
			driver.close();
		}
		
			
			
			
   
}
