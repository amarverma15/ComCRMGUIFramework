package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(name="industry")
	private WebElement indudtryDD;

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getIndudtryDD() {
		return indudtryDD;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	
	public void createorg(String orgName)//here orgName from excel
	{
		orgNameEdt.sendKeys(orgName);
		savebtn.click();
	}
	
	public void createorg(String orgName,String industry)//here orgName from excel
	{
		orgNameEdt.sendKeys(orgName);
		Select dd=new Select(indudtryDD);
		indudtryDD.click();
		savebtn.click();
	}
	
}
