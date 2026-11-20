package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Rule-1 crate a seperate java class
public class HomePage {
	
	WebDriver driver;
	//Rule 3: Object initialization..instead of initialize within the testscript better to go for constructor b'caus eits simplyfy our code
	public HomePage(WebDriver driver) //constructor
	{
	this.driver=driver;
	PageFactory.initElements(driver, this);//this refers current class object
	}
	
	//object creation
	@FindBy(linkText="Organizations")
	private WebElement orgLink; 
	
	@FindBy(linkText="Contacts")
	private WebElement conlink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignlink;
	
	@FindBy(linkText="More")
	private WebElement morelink;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement sighoutbtn;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminimg;

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getAdminimg() {
		return adminimg;
	}

	public WebElement getSighoutbtn() {
		return sighoutbtn;
	}

	public WebElement getConlink() {
		return conlink;
	}

	
	public WebElement getCampaignlink() {
		return campaignlink;
	}
	
	
	
	//5th business library not required.. where required more than one actions is there..
	
	public void navigateToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(morelink).perform();
		campaignlink.click();
	}
	
	public void signout()
	{
		Actions act2=new Actions(driver);
		act2.moveToElement(adminimg).perform();
		sighoutbtn.click();
	}
	
	

}
