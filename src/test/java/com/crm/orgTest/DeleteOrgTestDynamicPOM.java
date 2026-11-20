package com.crm.orgTest;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.Loginpage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.objectRepositoryUtility.Organizationpage;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutulity.JavaUtility;
import com.crm.generic.webdriverutulity.WebDriverUtility;

public class DeleteOrgTestDynamicPOM {
	public static void main(String[] args) throws Throwable {
		/*create object*/
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();
		
		
		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
	
		 
		 //read testscript data from excel file
		
		//Thread.sleep(2000);
		
		String orgName = elib.getDataFromExcel("org", 10, 2)+jlib.getRandomNumber();
		
		
		WebDriver driver=null;
		 if(BROWSER.equals("chrome"))
		 {
			 driver=new ChromeDriver();
		 }
		 else if(BROWSER.equals("firefox"))
		 {
			 driver=new FirefoxDriver();
		 }
		 else if(BROWSER.equals("Edge"))
		 {
			 driver=new EdgeDriver();
		 }
		 else
		 {
			 driver=new ChromeDriver();
		 }
		 
		 
		 
		 //step : 1 login to application
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		 driver.get(URL);
		Loginpage lp = new Loginpage(driver);//rule 3 initialization should be done inside the constructor.
		 lp.LoginToapp(USERNAME, PASSWORD);
		
		 
		 //Step 2: navigate to organization module
		 HomePage hp=new HomePage(driver);
		 hp.getOrgLink().click();
		 
		 //step : 3 click on create organizationTest  + button
		Organizationpage cnp=new Organizationpage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		 
		 //step 4 : enter all the details & create new organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createorg(orgName);
		cnop.getOrgNameEdt();
		cnop.getSavebtn();
		
		
		//verify Header msg Expected Result
				OrganizationInformationPage oip=new OrganizationInformationPage(driver);
				
				String actualorgname = oip.getHeaderMsg().getText();
				if(actualorgname.contains(orgName))
				{
					System.out.println(orgName+" name is verified==PASS");
				}
				else
				{
					System.out.println(orgName+" name is not verified==PASS");
				}
				
				
				//go back to organization page
				 hp.getOrgLink().click();
				
				//search for organization
				 cnp.getSearchorgEdt().sendKeys(orgName);
				 wlib.handleDropdown(cnp.getSearchDD(),"Organization Name");
				 Thread.sleep(2000);				
				 cnp.getSearchbtn().click();
				 
				 driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
				//in dynamic webtable select & delete org
				 
				 Alert alt = driver.switchTo().alert();
				 alt.accept();
				
				hp.signout();
				
				//driver.quit();
	}

}
