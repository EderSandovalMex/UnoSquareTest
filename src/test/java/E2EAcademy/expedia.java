package E2EAcademy;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageObjects.FlightsListObj;
import pageObjects.TripDetails;
import pageObjects.expediaLanObj;
import resources.base;

public class expedia extends base{
	public static Logger log = LogManager.getLogger(base.class.getName());
	
	@Test(dataProvider = "CitysandDays") 
	public void expediaOrderDateAsc(String from,String goingt, String departing, String returning) throws IOException, InterruptedException 
	{
		
		driver = initializeDriver();
		log.info("Driver has been initialized");
		driver.get(prop.getProperty("url"));
		log.info("Visiting Expedia");
		expediaLanObj exlan = new expediaLanObj(driver);
		exlan.Flights().click();
		log.info("Click on Flights");
		exlan.Flyingfrom().sendKeys(from);
		log.info("Input of the from city");
		exlan.Goingto().sendKeys(goingt);
		log.info("Input of the going to City");
		exlan.Departing().click();
		log.info("Click on the Calendar");
		exlan.DeparSelCalDayR(departing);
		log.info("Entering Departing Date");
		exlan.Returning().click();
		log.info("Click on the Calendar of the Returning");
		exlan.DeparSelCalDayR(returning);
		log.info("Entering the Returning Date");
		exlan.Searchbutton().click();
		log.info("Search for Flights on the Dates input");
		FlightsListObj FL = new FlightsListObj(driver);
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		FL.SelFlySort().selectByVisibleText(FL.DepartureEarliest());
		log.info("Selecting sort By the Earliest on the Departing List");
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		Assert.assertTrue(FL.ScheludeComparator());
		log.info("Validate that the List its on the correct Order");
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		FL.SelFlySort().selectByVisibleText(FL.DepartureEarliest());
		log.info("Selecting Sory by the Earliest on the Returning List");
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		Assert.assertTrue(FL.ScheludeComparator());
		log.info("Validate that the list its on the Correct Order");
		TripDetails TD = new TripDetails(driver);
		TD.WindChildID();
		log.info("Close and move to the New Window");
		TD.jsscrolldown();
		log.info("Scroll down to the button of Booking using the JavaScript script");
		TD.continueBooking().click();
		log.info("Click on the Booking Button");
		TD.driver.close();
		log.info("Close Driver");
		TD.driver.quit();
		log.info("Quit Driver");
	}
	@Test(dataProvider = "CitysandDays") 
	public void expediaOrderDateDesc(String from,String goingt, String departing, String returning) throws IOException, InterruptedException
	{
		driver = initializeDriver();
		log.info("Driver has been initialized");
		driver.get(prop.getProperty("url"));
		expediaLanObj exlan = new expediaLanObj(driver);
		log.info("Visiting Expedia");
		exlan.Flights().click();
		log.info("Click on Flights");
		exlan.Flyingfrom().sendKeys(from);
		log.info("Input of the from city");
		exlan.Goingto().sendKeys(goingt);
		log.info("Input of the going to City");
		exlan.Departing().click();
		log.info("Click on the Calendar");
		exlan.DeparSelCalDayR(departing);
		log.info("Entering Departing Date");
		exlan.Returning().click();
		log.info("Click on the Calendar of the Returning");
		exlan.DeparSelCalDayR(returning);
		log.info("Entering the Returning Date");
		exlan.Searchbutton().click();
		log.info("Search for Flights on the Dates input");
		FlightsListObj FL = new FlightsListObj(driver);
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		FL.SelFlySort().selectByVisibleText(FL.DepartureLatest());
		log.info("Selecting sort By the Latest on the Departing List");
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		Assert.assertTrue(FL.ScheludeComparatorDesc());
		log.info("Evaluate the List and compare that the records are Equal");
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		FL.SelFlySort().selectByVisibleText(FL.DepartureLatest());
		log.info("Selecting sort By the Latest on the Returning List");
		wait.until(ExpectedConditions.elementToBeClickable(FL.SortFlightDropdown()));
		Assert.assertTrue(FL.ScheludeComparatorDesc());
		log.info("Evaluate the List and compare that the records are Equal");
		FL.WindChildID();
		log.info("Move to the new Window");
		TripDetails TD = new TripDetails(driver);
		TD.jsscrolldown();
		log.info("Use the JS to move to the booking Button");
		TD.continueBooking().click();
		log.info("click on booking button");
		TD.driver.close();
		log.info("Close window");
		TD.driver.quit();
		log.info("Quit Driver");
	}
  
	@DataProvider
	public Object[][] CitysandDays()
	{
		Object[][] data= new Object[1][4];
		data[0][0] = "San Francisco, CA (SFO-San Francisco Intl.)";
		data[0][1] = "New York, New York";
		data[0][2] = "25";
		data[0][3] = "26";
		
		/*
		data[1][0] = "Colorado Springs, CO (COS)";
		data[1][1] = "Kansas City, MO (MCI-Kansas City Intl.)";
		data[1][2] = "26";
		data[1][3] = "26";
		
		data[2][0] = "Dallas (DFW-All Airports)";
		data[2][1] = "Albuquerque, NM (ABQ-Albuquerque Intl. Sunport)";
		data[2][2] = "27";
		data[2][3] = "28";

		data[3][0] = "Atlanta, GA (ATL-All Airports)";
		data[3][1] = "Wichita, KS (ICT-Wichita Dwight D. Eisenhower National))";
		data[3][2] = "29";
		data[3][3] = "30";
		*/
		return data;
	
	}
}

