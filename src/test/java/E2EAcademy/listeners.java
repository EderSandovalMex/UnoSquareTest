package E2EAcademy;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import resources.ExtentManager;
import resources.base;

public class listeners implements ITestListener {
	base b = new base();	
	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extenTest = new ThreadLocal<ExtentTest>();
	
	
	public void onTestStart(ITestResult result) 
	{
		ExtentTest test = extent.createTest(result.getTestClass()+"::"+
				result.getMethod().getMethodName());
		extenTest.set(test);
	}

	public void onTestSuccess(ITestResult result) 
	{
		String logText = "<b>Test Method"+result.getMethod().getMethodName()+"</b>"+"Successful";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extenTest.get().log(Status.PASS, m);
		

	}
	public void onTestFailure(ITestResult result) 
	{
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		extenTest.get().fail("<details><summary><b><font color=red>Exception Occured, click to see details:"
				+"</font></b></summary>"+exceptionMessage.replaceAll(",", "<br>")+ "</details> \n"); 
		WebDriver driver = ((base)result.getInstance()).driver;
		String path = null;
		try {
			path = TakesScreenshot(driver, result.getMethod().getMethodName());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			extenTest.get().fail("<b><font color =red>"+"Screenshot of failure"+"</font></b>",
			MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			
			} 
		catch (IOException e) {
			extenTest.get().fail("Test Failed cannot attach screenshot");
		}
		String logText =  "<b>Test Method"+methodName+" Failed </b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		extenTest.get().log(Status.FAIL, m);
	
	}

	public void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method"+result.getMethod().getMethodName()+"</b>"+"Skipped";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extenTest.get().log(Status.SKIP, m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) 
	{
		if(extent!=null)
		{
			extent.flush();
		}

	}

	public String TakesScreenshot(WebDriver driver, String methodName) throws IOException 
	{
		String fileName = getScreenShotName(methodName);
		String directory = System.getProperty("user.dir")+"/screenshots/";
		new File(directory).mkdirs();
		String path = directory+fileName;
		try {
			File screenshots = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshots, new File(path));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	public static String getScreenShotName(String methodName) 
	{
		Date d = new Date();
		String filename = "Automation Report_"+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return filename;
	}
	
}
