package com.crm.generic.webdriverutulity;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void waitForPageToLoad(WebDriver driver)//which browser have to wait
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void WaitForElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
				wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToTabonURL(WebDriver driver, String partialURL)
	{
		//switch to child window
				Set<String> set = driver.getWindowHandles();//get the window handles
				Iterator<String> it = set.iterator();//create iterator because set data is not a indexing order 
															//so using iterator we should get data from set.
				while(it.hasNext())
				{
					String windowID = it.next();//using while loop capturing 1st win ID..
					driver.switchTo().window(windowID);//and using that ID switching to window
					
					@Nullable
					String acturl = driver.getCurrentUrl();//fetching the current url
					if(acturl.contains(partialURL))//comparing with partialURL
					{
						break;
					}
				}	
		}
				
				public void switchToTabOnTitle(WebDriver driver, String partialTitle )
				{
					Set<String> set = driver.getWindowHandles();//get the window handles
					Iterator<String> it = set.iterator();//create iterator because set data is not a indexing order 
																//so using iterator we should get data from set.
					while(it.hasNext())
					{
						String windowID = it.next();//using while loop capturing 1st win ID..
						driver.switchTo().window(windowID);//and using that ID switching to window
						
						@Nullable
						String acturl = driver.getTitle();//fetching the current url
						if(acturl.contains(partialTitle))//comparing with partialURL
						{
							break;
						}
				}
				}		
					public void switchtoFrame(WebDriver driver, int index)
					{
						driver.switchTo().frame(index);
					}
					
					public void switchtoFrame(WebDriver driver, String nameID)
					{
						driver.switchTo().frame(nameID);
					}
					
					public void switchtoFrame(WebDriver driver, WebElement element)
					{
						driver.switchTo().frame(element);
					}
					
				public void switchToAlertAndAccept(WebDriver driver)
				{
					
				}
				
				public void switchToAlertcancel(WebElement element,int index)
				{
					Select sel=new Select(element);
					sel.selectByIndex(index);
					
				}
				
				public void mousemoveonElement(WebDriver driver, WebElement element)
				{
					Actions act=new Actions(driver);
					act.moveToElement(element).perform();
				}
				
				public void doubleClick(WebDriver driver, WebElement element)
				{
					Actions act=new Actions(driver);
					act.doubleClick(element).perform();
				}
				
				public void handleDropdown(WebElement element,String text)
				{
					Select sel=new Select(element);
					sel.selectByVisibleText(text);
				}
				
	}

