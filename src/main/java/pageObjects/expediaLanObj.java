package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class expediaLanObj {
	public WebDriver driver; 
	 
	public expediaLanObj(WebDriver driver) 
		{
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
	
	 @FindBy(xpath = "//button[@id='tab-flight-tab-hp']") 
	 WebElement Flights;
	 @FindBy(xpath = "//input[@id='flight-origin-hp-flight']")
	 WebElement Flyingfrom;
	 @FindBy(xpath = "//input[@id='flight-destination-hp-flight']")
	 WebElement Goingto;
	 @FindBy(xpath = "//input[@id='flight-departing-hp-flight']")
	 WebElement Departing;
	 @FindBy(xpath = "//div[@class='col gcw-date-field']//div[2]//table[1]//tbody/tr/td/button")
	 WebElement LeftCalendar;
	 @FindBy(xpath = "//input[@id='flight-returning-hp-flight']")
	 WebElement Returning;
	 @FindBy(xpath = "//form[@id='gcw-flights-form-hp-flight']//button[@class='btn-primary btn-action gcw-submit']")
	 WebElement SearchButton;
	public WebElement Flights() 
	 {
     return Flights;
		
	}
	 public WebElement Flyingfrom()
	 {
		 return Flyingfrom;
	 }
	 public WebElement Goingto() 
	 {
		 return Goingto;
	 }
	 public WebElement Departing()
	 {
		 return Departing;
	 }
	 public List<WebElement> DerRCalendar()
	 {
		 return driver.findElements(By.xpath("//div[@class='col gcw-date-field']//div[2]//div[@class='datepicker-cal-month'][1]//td"));
	 }
	 
	 public List<WebElement> DerLCalendar()
	 {
		 return driver.findElements(By.xpath("//div[@class='col gcw-date-field']//div[2]//div[@class='datepicker-cal-month'][2]//td"));
	 }
	 
	 public WebElement Returning() 
	 {
		 return Returning;
	 }
	 
	 public WebElement Searchbutton() 
	 {
		return SearchButton;
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void DeparSelCalDayL(String day)
	 {
		 int NumberofDays = DerLCalendar().size();
		 for (int i = 0; i < NumberofDays; i++) 
		 {
			 String[] departingleftdate = driver.findElements(By.xpath("//div[@class='col gcw-date-field']//div[2]//div[@class='datepicker-cal-month'][1]//td/button")).get(i).getText().split("\\R");
			 String daynumber = departingleftdate[1];
			 WebElement departingclick = driver.findElements(By.xpath("//div[@class='col gcw-date-field']//div[2]//div[@class='datepicker-cal-month'][1]//td/button")).get(i);
			 if(daynumber.equalsIgnoreCase(day)) 
			 {
				departingclick.click();
				break;
				
			 }
		 }
		 
	 }
	 public void DeparSelCalDayR(String day)
	 {
		 int NumberofDays = DerRCalendar().size();
		 for (int i = 0; i < NumberofDays; i++) 
		 {
			 String[] departingrighttdate = driver.findElements(By.xpath("//div[@class='col gcw-date-field']//div[2]//div[@class='datepicker-cal-month'][2]//td/button")).get(i).getText().split("\\R");
			 String daynumber = departingrighttdate[1];
			 WebElement departingclick = driver.findElements(By.xpath("//div[@class='col gcw-date-field']//div[2]//div[@class='datepicker-cal-month'][2]//td/button")).get(i);
			 if(daynumber.equalsIgnoreCase(day)) 
			 {
				departingclick.click();
				break;
				
			 }
		 }
		 
	 }
	 
}
