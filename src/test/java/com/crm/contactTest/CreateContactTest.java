package com.crm.contactTest;

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

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;

public class CreateContactTest {
	public static void main(String[] args) throws Throwable {
		//create object 
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
		//generate a random 2 digit number
		 Random random=new Random();
		 int randomnum=random.nextInt(10000);
		 
		 //read testscript data from excel file
		String lastname = elib.getDataFromExcel( "contact", 1, 2) + randomnum;
		    
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
		 driver.findElement(By.name("lastname")).sendKeys(lastname);
		 Thread.sleep(3000);
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
		driver.quit();
	}

}
