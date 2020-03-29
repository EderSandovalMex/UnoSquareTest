package pageObjects;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class FlightsListObj {
	public WebDriver driver;
	Select FlySort;
	SimpleDateFormat SDF;
	Boolean PresentSecTopSelectButton;
	Boolean PresentNothanksbutton;
	
	public FlightsListObj(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	String PriceLowest= "Price (Lowest)";
	String PriceHighest= "Price (Highest)";
	String DurationShortest = "Duration (Shortest)";
	String DurationLongest = "Duration (Longest)";
	String DepartureEarliest =  "Departure (Earliest)";
	String DepartureLatest = "Departure (Latest)";	
	String ArrivalEarliest = "Arrival (Earliest)";
	String ArrivalLatest = "Arrival (Latest)";
	
	
	By SortFlightDropdown = By.xpath("//select[@id='sortDropdown']");
	//Boton Superior
	By TopSelectButton = By.xpath("//button[@class='btn-secondary btn-action t-select-btn']");
	//SecondaryButton
	By SecTopSelectButton = By.xpath("//div[@id='basic-economy-tray-content-1']//button[@class='btn-secondary btn-action t-select-btn']");
	By Nothanksbutton = By.xpath("//a[@id='forcedChoiceNoThanks']");
	
	public String PriceLowest() 
	{
		return PriceLowest;
	}
	public String PriceHighest() 
	{
		return PriceHighest;
	}
	public String DurationShortest() 
	{
		return DurationShortest;
		
	}
	public String DurationLongest()
	{
		return DurationLongest;
		
	}
	public String DepartureEarliest() 
	{
		return DepartureEarliest;
	}
	public String DepartureLatest() 
	{
		return DepartureLatest;
	}
	public String ArrivalEarliest() 
	{
		return ArrivalEarliest;
	}
	
	
	public WebElement SortFlightDropdown() 
	
	{
		return driver.findElement(SortFlightDropdown);
	}
	public WebElement SelectTopButton() 
	{
		return driver.findElement(TopSelectButton);
	}
	public WebElement SecTopSelectButton() 
	{
		return driver.findElement(SecTopSelectButton);
	}
	public WebElement Nothanksbutton() 
	{
	    return driver.findElement(Nothanksbutton);	
	}
	
	
	public Select SelFlySort() {
		Select FlySort = new Select(driver.findElement(SortFlightDropdown));
		return FlySort;
		
	}
	
	public List<WebElement> ScheludeFlights() 
	{
		 
		return driver.findElements(By.xpath("//div[contains(@class,'grid-container standard-padding')]/div/div/div/div/div/div/div/span[1]/span[1]"));
	}
	
	public void WindChildID() 
	{
		  Set<String>WinIds = driver.getWindowHandles();
		  Iterator<String>WinIte =WinIds.iterator();
		  String ParentId = WinIte.next();
		  String ChildId = WinIte.next();
		  driver.switchTo().window(ParentId);
		  driver.close();
		  driver.switchTo().window(ChildId);
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
	
	public void closeAllOtherWindows() {
		Set<String> allWindowHandles = driver.getWindowHandles();
		String currentWindowHandle =getMainWindowHandle();
		for (int i = 0; i < allWindowHandles.size(); i++) 
		{
		
		if (!currentWindowHandle.equals(allWindowHandles.iterator().next())) 
			{
				driver.switchTo().window(allWindowHandles.iterator().next());
				driver.close();
			}
		driver.switchTo().window(getMainWindowHandle());
		
			
	}
	}
	
	
	public boolean ScheludeComparator() throws InterruptedException 
	{
		boolean ListCompRes;
		ArrayList<String> ScheFligStr = new ArrayList<String>();
		ArrayList<String> ScheFligStrComp = new ArrayList<String>();
		List<Date> ScheFligStrDate = new ArrayList<Date>();
		List<Date> ScheFligStrCompDate = new ArrayList<Date>();
		
	    for (int i = 0; i < ScheludeFlights().size(); i++) 
	    {
			ScheFligStr.add(ScheludeFlights().get(i).getText());
			ScheFligStrComp.add(ScheludeFlights().get(i).getText());
		} 
		SDF = new SimpleDateFormat("hh:mma");
		for (int i = 0; i < ScheFligStr.size(); i++) 
		{
			try {
				ScheFligStrDate.add(SDF.parse(ScheFligStr.get(i)));
				ScheFligStrCompDate.add(SDF.parse(ScheFligStrComp.get(i)));
			    } catch (ParseException e) 
					{
				
					}
		
		}
		Collections.sort(ScheFligStrCompDate, new Comparator<Date>() {
			public int compare(Date o1, Date o2) {return o1.compareTo(o2);}
		});
		
		if(ScheFligStrDate.equals(ScheFligStrCompDate)) 
		{
			 ListCompRes=true;
			 System.out.println("Schelude are equal");
			 getMainWindowHandle();
			 //Selecciona el Primer Boton
			 SelectTopButton().click();
			 Thread.sleep(4000);
			 closeAllOtherWindows();
			 
			 try {
		     PresentSecTopSelectButton = driver.findElement(SecTopSelectButton).isDisplayed();
		     if (PresentSecTopSelectButton==true) 
		     {
		    	 SecTopSelectButton().click();
		     }
			         } catch (NoSuchElementException exception) 
			 			{
			        	 //Not Required
			            }
			 Thread.sleep(2000);
			 try 
			 	 {
				  PresentNothanksbutton = Nothanksbutton().isDisplayed();
				  if(PresentNothanksbutton==true)
					  Nothanksbutton().click();
			 	 } 
			 		catch (NoSuchElementException exception) 
			 		{
				// TODO: handle exception
			 		}
			 
		}
		else {ListCompRes=false;}
		return ListCompRes;
	}
	
	
	public boolean ScheludeComparatorDesc() throws InterruptedException 
	{
		boolean ListCompRes;
		ArrayList<String> ScheFligStr = new ArrayList<String>();
		ArrayList<String> ScheFligStrComp = new ArrayList<String>();
		List<Date> ScheFligStrDate = new ArrayList<Date>();
		List<Date> ScheFligStrCompDate = new ArrayList<Date>();
		
	    for (int i = 0; i < ScheludeFlights().size(); i++) 
	    {
			ScheFligStr.add(ScheludeFlights().get(i).getText());
			ScheFligStrComp.add(ScheludeFlights().get(i).getText());
		} 
		SDF = new SimpleDateFormat("hh:mma");
		for (int i = 0; i < ScheFligStr.size(); i++) 
		{
			try {
				ScheFligStrDate.add(SDF.parse(ScheFligStr.get(i)));
				ScheFligStrCompDate.add(SDF.parse(ScheFligStrComp.get(i)));
			    } catch (ParseException e) 
					{
				
					}
		
		}
		Comparator<Date> cmp = Collections.reverseOrder();
		Collections.sort(ScheFligStrCompDate, new Comparator<Date>() {
			public int compare(Date o1, Date o2) {return o1.compareTo(o2);}
		});
		Collections.sort(ScheFligStrCompDate, cmp);
		
		if(ScheFligStrDate.equals(ScheFligStrCompDate)) 
		{
			 ListCompRes=true;
			 System.out.println("Schelude are equal");
			 getMainWindowHandle();
			 //Selecciona el Primer Boton
			 SelectTopButton().click();
			 Thread.sleep(4000);
			 closeAllOtherWindows();
			 try {
		     PresentSecTopSelectButton = driver.findElement(SecTopSelectButton).isDisplayed();
		     if (PresentSecTopSelectButton==true) 
		     {
		    	 SecTopSelectButton().click();
		     }
			         } catch (NoSuchElementException exception) 
			 			{
			        	 //Not Required
			            }
			 Thread.sleep(2000);
			 try 
			 	 {
				  PresentNothanksbutton = Nothanksbutton().isDisplayed();
				  if(PresentNothanksbutton==true)
					  Nothanksbutton().click();
			 	 } 
			 		catch (NoSuchElementException exception) 
			 		{
				// TODO: handle exception
			 		}
			 
		}
		else {ListCompRes=false;}
		return ListCompRes;
	}

	
	
	
	
	


   




}//final de la clase
	