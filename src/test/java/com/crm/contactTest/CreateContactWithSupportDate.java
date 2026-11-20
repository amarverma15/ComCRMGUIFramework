package com.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutulity.JavaUtility;

public class CreateContactWithSupportDate {
	public static void main(String[] args) throws Throwable {
		
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
	
		
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
	
		 //read testscript data from excel file
		
		String lastname = elib.getDataFromExcel("contact", 1, 2)+jlib.getRandomNumber();
		
		    
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
		 
		 //Step 2: navigate to contact module
		 driver.findElement(By.linkText("Contacts")).click();
		 
		 //step : 3 click on create contact  + button
		 driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		 
		 //step 4 : enter all the details & create new organization
		 String startDate = jlib.getSystemDateYYYYDDMM();
		 String endDate = jlib.getRequiredDateYYYYDDMM(30);
		 
		 System.out.println("startdate :"+startDate);
		 System.out.println("enddate :"+endDate);
		
		 
		 driver.findElement(By.name("lastname")).sendKeys(lastname);
		 driver.findElement(By.name("support_start_date")).clear();
		 driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		 driver.findElement(By.name("support_end_date")).clear();
		 driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		 
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		 		//verify Header msg Expected Result
		String actuallastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actuallastname.equals(lastname))
		{
			System.out.println(lastname+"is created==pass");
		}
		else
		{
			System.out.println(lastname+"is not created==fail");
		}
	//verify actual start date
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartdate.equals(startDate))
		{
			System.out.println(startDate + "infomation is verified");
		}
		else
		{
			System.out.println(startDate + "infomation is not verified");
		}
		
		//Verify actual end date
		String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actenddate.equals(endDate))
		{
			System.out.println(endDate + "information is verified");
		}
		else
		{
			System.out.println(endDate + "information is not verified");
		}
	
	driver.quit();
	}
	
	

}
