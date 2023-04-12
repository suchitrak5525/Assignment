package TestQA.DemoQA;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeXpath {
	WebDriver driver; //creating a reference variable for WebDriver interface
	
	@BeforeSuite
	public void launchBrowser()
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);//create the instance of the class and ChromeDriver() is one of the implemented classes of WebDriver
	}
	
	@BeforeTest
	public void loginApp() throws InterruptedException, IOException
	{
		
			driver.get("https://www.edureka.co/");
		    driver.manage().window().maximize();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//span[@data-button-name='Login']")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//input[@id='si_popup_email']")).sendKeys("suchitrak5525@gmail.com");
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[contains(@placeholder,'Enter your Password')]")).sendKeys("Suchitra@55");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//button[text()='Login']")).click();
		    Thread.sleep(3000);
		    
		}
	
	@Test(priority=1)
	public void checkWindowHandles() throws InterruptedException
	{
		driver.findElement(By.xpath("//a[@data-button-name='Community']")).click();
		Thread.sleep(2000);
		List<WebElement> community=driver.findElements(By.xpath("//li[@data-button-location='First fold navigation']"));
		for(int i=0;i<community.size();i++)
		{
			if(community.get(i).getText().equals("Blog"))
				{
				   community.get(i).click();
				}
		}
		Set<String> allWindows = driver.getWindowHandles();
		for(String handle : allWindows)
		{
			driver.switchTo().window(handle);
			Thread.sleep(2000);
			System.out.println(driver.getTitle());
		}
	}
	
	@Test(priority=2,enabled=false)
	public void checkCourse() throws IOException, WebDriverException
	{
		try {
			driver.findElement(By.xpath("//*[@class='open_search_overlay']//self::input")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id='search-input']//self::input")).sendKeys("Selenium");
		    Thread.sleep(5000);
		    driver.findElement(By.xpath("//*[@id='search-results']//child::a[2]")).click();
		}
		catch(Exception e)
		{
			TakesScreenshot scrShot =((TakesScreenshot)driver);//TakesScreeshot-interface and convert webdriver object to takeScreenshot
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);//getScreenshot is method to create image
			File DestFile=new File("D:\\errors\\screenshot.png");
			FileUtils.copyFile(SrcFile, DestFile);
		}
	}
	
	

}
