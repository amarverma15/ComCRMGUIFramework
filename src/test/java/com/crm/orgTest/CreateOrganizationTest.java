package com.crm.orgTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.objectRepositoryUtility.CreateNewOrganizationPage;
import com.comcast.crm.objectRepositoryUtility.HomePage;
import com.comcast.crm.objectRepositoryUtility.Loginpage;
import com.comcast.crm.objectRepositoryUtility.OrganizationInformationPage;
import com.comcast.crm.objectRepositoryUtility.Organizationpage;
import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutulity.JavaUtility;

public class CreateOrganizationTest {
	public static void main(String[] args) throws Throwable {
		/*create object*/
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		
		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
	
		 
		 //read testscript data from excel file
		
		String orgName = elib.getDataFromExcel("org", 1, 2)+jlib.getRandomNumber();
		
		
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
		
		hp.signout();
		
		driver.quit();
	}

}
