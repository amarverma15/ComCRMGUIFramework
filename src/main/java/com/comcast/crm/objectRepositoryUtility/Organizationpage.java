package com.comcast.crm.objectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizationpage {
	WebDriver driver;
	public Organizationpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;
	
	@FindBy(name="search_text")
	private WebElement searchorgEdt;
	
	@FindBy(name="search_field")
	private WebElement searchDD;
	
	
	@FindBy(name="submit")
	private WebElement searchbtn;
	
	
	public WebElement getSearchorgEdt() {
		return searchorgEdt;
	}



	public WebElement getSearchbtn() {
		return searchbtn;
	}



	public WebElement getSearchDD() {
		return searchDD;
	}

	
	
	
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}

}
