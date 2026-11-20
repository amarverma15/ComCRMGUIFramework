package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutulity.WebDriverUtility;

public class Loginpage extends WebDriverUtility {
	WebDriver driver;
	
	FileUtility flib=new FileUtility();
	
	
		
	
	
	public Loginpage(WebDriver driver)//login page constructor
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);//this refers current class object
	}
	//Rule-2 object creation
		@FindBy(name="user_name")
		private WebElement usernameEdt; 
		
		@FindBy(name="user_password")
		private WebElement passwordEdt; 
		
		//@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//div[@class='button']")})
		//private WebElement loginbtn;
		@FindBy(id="submitButton")
		private WebElement loginbtn ; 

		
		//Rule 3object initialization(Encapsulation)
		public WebElement getUsernameEdt() {
			return usernameEdt;
		}

		public WebElement getPasswordEdt() {
			return passwordEdt;
		}

		public WebElement getLoginbtn() {
			return loginbtn;
		} 
		
		//rule : 5 provide action//thia is called business library can't use for anyother application so that called business library
		public void LoginToappWithUrl(String url,String username,String password) throws Throwable
		{
			
			waitForPageToLoad(driver);
			driver.get(url);
			driver.manage().window().maximize();
			usernameEdt.sendKeys(username);
			passwordEdt.sendKeys(password);
			loginbtn.click();
		}
		public void LoginToapp(String username,String password) throws Throwable
		{
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginbtn.click();
		}
	

}
