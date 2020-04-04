package unoSquareInterview;

import java.io.IOException;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import resources.base;
import resources.interviewconstants;
 

public class unoSquareTest extends base{
	
	 
	@Test 
	public void UnosquareTest1() throws IOException 
	{
		driver = initializeDriver();
		System.out.println(interviewconstants.varOneInter);
		
	}
	@Test 
	public void UnosquareTest2() throws IOException 
	{
		driver = initializeDriver();
		System.out.println(interviewconstants.vartwoInter);
	}
	@Test 
	public void UnosquareTest3() 
	{
		System.out.println(interviewconstants.varthreeInter);
	}
	
	
}

