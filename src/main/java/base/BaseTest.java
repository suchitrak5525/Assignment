package base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.Constants;
import utility.FailedScreenshots;



public class BaseTest {
	public static WebDriver driver;
	public ExtentReports extentReports;
	public ExtentTest logger;
    FailedScreenshots fs=new FailedScreenshots();
    
    @BeforeSuite
    public void setUpSuite()
    {
    	ExtentHtmlReporter reporter =  new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/demotest.html"));
    	extentReports = new ExtentReports();
    	extentReports.attachReporter(reporter);
    }
	

	@BeforeTest
	@Parameters("browser")
	public void initDrivers(String browser)
	{
		//logger= extentReports.createTest(testMethod.getName());
		setUpDriver(browser);
		driver.get(Constants.url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
	}
	
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{   String path= fs.capture(driver, "screenshotfailed");
		    logger.addScreenCaptureFromPath(path);
			logger.fail("Execution failed");
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
			logger.pass("Executed successfully");
		extentReports.flush();
	}
	
	@AfterSuite()
	public void quitBrowser()
	{
		driver.quit();
	}

	
	
	public void setUpDriver(String browser)
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(options);
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
	}

}
