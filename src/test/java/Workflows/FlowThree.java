package Workflows;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlowThree {
	
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
	public void navigate() throws InterruptedException, IOException
	{
		
			driver.get("https://www.swiggy.com/");
		    driver.manage().window().maximize();
		    Thread.sleep(2000);
		    
	 }
	
	@Test
	public void robotClass() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[@class='_1fiQt']")).click();
		Thread.sleep(5000);
	}

}
