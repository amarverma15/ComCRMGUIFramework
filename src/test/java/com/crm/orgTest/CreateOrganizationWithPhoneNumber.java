package com.crm.orgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutulity.JavaUtility;

public class CreateOrganizationWithPhoneNumber {
	public static void main(String[] args) throws Throwable, IOException {
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
	
		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		
		
		 
		 //read testscript data from excel file
		
		String orgname = elib.getDataFromExcel("org", 7, 2)+jlib.getRandomNumber();
		String phoneno = elib.getDataFromExcel("org", 7, 3);
		 
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
		 driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 driver.findElement(By.id("submitButton")).click();
		 
		 //Step 2: navigate to organization module
		 driver.findElement(By.linkText("Organizations")).click();
		 
 //step : 3 click on create organizationTest  + button
		 driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		 
 //step 4 : enter all the details & create new organization
		 driver.findElement(By.name("accountname")).sendKeys(orgname);
		 driver.findElement(By.id("phone")).sendKeys(phoneno);
		 
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
//Verify the phone number info Expected Result
		  String actualPhNo = driver.findElement(By.id("dtlview_Phone")).getText();
		  if(actualPhNo.equals(phoneno))
		  {
			  System.out.println(phoneno +"information is created==PASS");
		  }
		  else
		  {
			  System.out.println(phoneno +"information is not created==FAIL");
		  }
		driver.quit();
		
	}

}
