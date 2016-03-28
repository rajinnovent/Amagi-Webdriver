package com.innoventes.amagi;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Hello world!
 *
 */
public class Amagi_DSRPage 
{
	WebDriver driver;


	  By userid= By.id("user_email_id");	
	  By password = By.id("user_password");
	  By login_button= By.id("buttonSubmit");
	  By userlist=By.xpath("//*[@id='sidebar-default']/div[3]/ul/li[2]/ul/li[2]/a");
	  By dsrlist=By.xpath("//*[@id='sidebar-default']/div[3]/ul/li/a");
	  By searchdropdown=By.id("filter_search_by");
	  By searchbox=By.id("filter_search");
	  By export=By.linkText("Export");
	  By campaignlink=By.linkText("view campaigns");
	  By userlevel=By.id("user_level_id");
	  String adduser="http://www.innoventestech.in/amagi-dsr/users/new";
	  String dsrs="http://www.innoventestech.in/amagi-dsr/dsrs";
	  String listingusers="http://www.innoventestech.in/amagi-dsr/users";
	 
	  
	  public Amagi_DSRPage(WebDriver driver){
		  this.driver=driver;
	  }
			
	 public void logintest(String usertype,String username,String pass)throws InterruptedException{
	  new Select(driver.findElement(By.id("user_user_type"))).selectByVisibleText(usertype);
	  driver.findElement(userid).sendKeys(username);
	  driver.findElement(password).sendKeys(pass);
	  driver.findElement(login_button).submit();
	  
	}
	 public void invalidlogin(String usertype,String username,String pass)throws InterruptedException{
	  new Select(driver.findElement(By.id("user_user_type"))).selectByVisibleText(usertype);
	  driver.findElement(userid).sendKeys(username);
	  driver.findElement(password).sendKeys(pass);
	  driver.findElement(login_button).click();
	  Assert.assertTrue(isTextPresent("Incorrect Email/Password"));
	 }

	public  void adminlinktest()throws InterruptedException{
		 Assert.assertTrue(isTextPresent("Total Users")); 
	     String user=driver.findElements(By.tagName("a")).toString();
	     System.out.println(user);
		 driver.get(adduser); 
	     driver.findElement(login_button).isDisplayed();
		 driver.findElement(userlist).isDisplayed();
		 driver.get(listingusers);
		 Assert.assertTrue(isTextPresent("Krishnan"));
		 
		 
	 }
	public void adduserform()throws InterruptedException{
		driver.get(adduser);
		WebElement user_level=new Select(driver.findElement(userlevel)).getFirstSelectedOption();	String defaultvalue=user_level.getText();
		Assert.assertEquals("Select level",defaultvalue);
		new Select(driver.findElement(userlevel)).selectByVisibleText("Group Head");
		driver.findElement(login_button).click();
	    new Select(driver.findElement(userlevel)).selectByVisibleText("Front Liners");
	 }

	 public void admin_dsrcheck(String searchtype,String searchvalue)throws InterruptedException{
		driver.get(dsrs);
		driver.findElement(export).isDisplayed();
		new Select (driver.findElement(searchdropdown)).selectByVisibleText(searchtype);
		driver.findElement(searchbox).sendKeys(searchvalue);
	    driver.findElement(login_button).click();
	    driver.findElement(campaignlink).isDisplayed();
	}
	 public void normaluser_dsrcheck(String searchtype,String searchvalue)throws InterruptedException{
		driver.findElement(dsrlist).click();
		driver.findElement(export).isDisplayed();
		new Select(driver.findElement(searchdropdown)).selectByVisibleText(searchtype);
		driver.findElement(searchbox).sendKeys(searchvalue);
		driver.findElement(login_button).click();
		Assert.assertTrue(isTextPresent("Krishnan"));
		driver.findElement(campaignlink).click();
		}	

	public void userhierachytest(String searchtype,String searchvalue)throws InterruptedException{
	   new Select (driver.findElement(searchdropdown)).selectByVisibleText(searchtype);
	   driver.findElement(searchbox).sendKeys(searchvalue);
	   driver.findElement(login_button).click();
	   }	
		
	   
		 
	 

	 private boolean isTextPresent(String message) {
			// TODO Auto-generated method stub
			return driver.getPageSource().contains(message);
		}
	 
}
