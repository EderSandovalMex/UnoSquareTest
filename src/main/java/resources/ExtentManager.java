package resources;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
		
	private static ExtentReports extent;
	
	public static ExtentReports createInstance() 
	{
		String filename = getReportName();
		String directory = System.getProperty("user.dir")+"/reports/";
		new File(directory).mkdirs();
		String path = directory + filename;
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
		htmlReporter = new ExtentHtmlReporter("./reports/extent.hmtl");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Orgnization", "Expedia");
		extent.attachReporter(htmlReporter);
		
		return extent;
	}
	
	public static String getReportName() 
	{
		Date d = new Date();
		String filename = "Automation Report_"+"_"+d.toString().replace(":", "_").replace(" ", "_")+".png";
		return filename;
	}
}
