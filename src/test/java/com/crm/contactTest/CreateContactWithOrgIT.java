package com.crm.contactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.generic.fileutility.ExcelUtility;
import com.crm.generic.fileutility.FileUtility;
import com.crm.generic.webdriverutulity.JavaUtility;
import com.crm.generic.webdriverutulity.WebDriverUtility;

public class CreateContactWithOrgIT {
	public static void main(String[] args) throws Throwable {
		
		
		//create object 
				FileUtility flib=new FileUtility();
				ExcelUtility elib=new ExcelUtility();
				JavaUtility jlib=new JavaUtility();
				WebDriverUtility wlib=new WebDriverUtility();
				
				String BROWSER = flib.getDataFromPropertiesFile("browser");
				String URL = flib.getDataFromPropertiesFile("url");
				String USERNAME = flib.getDataFromPropertiesFile("username");
				String PASSWORD = flib.getDataFromPropertiesFile("password");
			
		 
		 //read testscript data from excel file
		 String orgname = elib.getDataFromExcel("contact", 7, 2)+jlib.getRandomNumber();
		 String ConlastName = elib.getDataFromExcel("contact", 7, 3)+jlib.getRandomNumber();
		
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
		 driver.manage().window().maximize();
		 wlib.waitForPageToLoad(driver);
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
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 
		
		//step5 : navigate to the contact module
		 Thread.sleep(2000);
		
		 // Wait for the Contacts link to be clickable
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		 wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//a[text()='Contacts']"))).click();
		
		//step 6:click on "+" button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//stept 7 :enter all the details & create new contact
		driver.findElement(By.name("lastname")).sendKeys(ConlastName);
		
		//i am getting 2 matching so i found x path of inside "+" button for before ele and then using following-sibling i got 1 of 1 matching
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//switch to child window
		wlib.switchToTabonURL(driver, "module=Accounts");
		
		
		
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		
		
		//switch to parent window
		wlib.switchToTabonURL(driver, "Contacts&action");
		
		
		Thread.sleep(2000);
		
		//driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.findElement(By.xpath("//b[text()='Contact Information']/parent::td/parent::tr/preceding-sibling::tr//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify actorgname msg Expected Result
				String actualorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();
				if(actualorgname.contains(orgname))
				{
					System.out.println(orgname+"header is created==pass");
				}
				else
				{
					System.out.println(orgname+"is not created==fail");
				}
		
				//verify contactlastname and verify the orgName
				String actualcontactlastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(actualcontactlastname.contains(ConlastName))
				{
					System.out.println(ConlastName + "information is created==PASS");
				}
				else
				{
					System.out.println(ConlastName+ "information is not created==FAIL");
				}
				driver.quit();
		
	}

}
