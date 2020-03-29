package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class base {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public WebDriverWait wait;
	public WebDriver driver;//Global Variable must be at the top to affect and modifier public
	public Properties prop;
	
	public WebDriver initializeDriver() throws IOException {
		
		
		prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\Ederu\\E2EProject\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		else if(browserName.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE")) 
		{
			System.setProperty("webdriver.ie.driver", "C:\\iedriver\\IEDriverServer_x64_3.150.1");
			driver = new InternetExplorerDriver();
		}
		
	
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		wait = new WebDriverWait(driver, 30);
		return driver;
		
		
		
		
	}
	
	
	
	
}
