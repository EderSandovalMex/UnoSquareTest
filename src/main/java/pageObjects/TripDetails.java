package pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TripDetails {
	public WebDriver driver;
	JavascriptExecutor js;
	String getWindowHandle;
	String getCurrentWindowTitle;
	By continueBooking = By.xpath("//button[@id='bookButton']");
	
	public TripDetails(WebDriver driver) 
	{
		this.driver=driver;
		js = (JavascriptExecutor) driver;
	}
	
	public String getMainWindowHandle() 
	{
		return driver.getWindowHandle();
	}
	
	public String getCurrentWindowTitle() 
	{
		String windowTitle = driver.getTitle();
		return windowTitle;
	}
	
	public void WindChildID() 
	{
		  Set<String>WinIds = driver.getWindowHandles();
		  Iterator<String>WinIte =WinIds.iterator();
		  String ParentId = WinIte.next();
		  String ChildId = WinIte.next();
		  driver.switchTo().window(ParentId);
		  driver.close();
		  try 
		  {
			  Thread.sleep(4000);
			  Set<String> windHand = driver.getWindowHandles();
			  for (int i = 0; i < windHand.size(); i++) 
				{
				String currentWindowHandle = driver.getWindowHandle();
				if(!currentWindowHandle.equals(ChildId)) 
					{
					driver.switchTo().window(ChildId);
					}
				
				
					
			}
		  } 
		  	catch (Exception NoSuchWindowException ) 
		  						{
		  			driver.switchTo().window(ChildId);
		  						}
		  
		  
	}
	
	
	
	public WebElement continueBooking() 
	{
		return driver.findElement(continueBooking);
	}
	
	
	public void jsscrolldown() 
	{
		WebElement BookingContinue = driver.findElement(By.xpath("//button[@id='bookButton']"));
		js.executeScript("arguments[0].scrollIntoView();", BookingContinue);
	}
	
}
