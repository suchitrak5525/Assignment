package Workflows;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EnhancedCode {
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
		
			driver.get("https://www.makemytrip.com");
		    driver.manage().window().maximize();
		    Thread.sleep(2000);
		    WebElement click= driver.findElement(By.xpath("//a[@data-cy='mmtLogo']"));
		    Actions acc = new Actions(driver);
		    acc.click(click).build().perform();
		    Thread.sleep(3000);
		    
		   //Thread.sleep(2000);
		    
		}
	

	
	@Test
	public void selectFares() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@id='fromCity']")).click();
	    Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@placeholder='From']")).sendKeys("Lucknow");
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//p[text()='Lucknow, India']")).click();
		
		List<WebElement> ls=driver.findElements(By.xpath("//li[starts-with(@class,'font12 blackText wrapFilter')]"));
		System.out.println(ls.size());
		for(int i=0;i<ls.size();i++)
		{
			System.out.println(ls.get(i).getText());
			if(ls.get(i).getText().contains("Senior Citizen"))
			{
				//Thread.sleep(2000);
				ls.get(i).click();
			}
		}
		driver.findElement(By.xpath("//a[text()='Search']")).click();
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")).click();
		//Thread.sleep(2000);
		driver.findElement(By.xpath("//p[contains(text(),'Non Stop') and @class='checkboxTitle']")).click();
		//Thread.sleep(4000);
		List<WebElement> priceList=driver.findElements(By.cssSelector(".blackText.fontSize18.blackFont.white-space-no-wrap"));
		List<WebElement> buttonsList = driver.findElements(By.xpath("//button[contains(@class,'ViewFareBtn  text-uppercase ')]"));
		List<Integer> intList= new ArrayList<Integer>();
		Map<WebElement,Integer> map = new HashMap<WebElement,Integer>();
		for(int i=0;i<priceList.size();i++)
		{
			String reviewValue=priceList.get(i).getText();
			for (WebElement child : priceList.get(i).findElements(By.xpath("./*"))) {
		          reviewValue = reviewValue.replaceFirst(child.getText(), "");
		          
		        }
			
			reviewValue=reviewValue.replaceAll("[^a-zA-Z0-9]", "");
			int value=Integer.parseInt(reviewValue);
		    map.put(buttonsList.get(i), value);
		    
		    
		  }
		
		  List<Entry<WebElement,Integer>> le = new ArrayList<Map.Entry<WebElement,Integer>>(map.entrySet());
		  le.sort(Entry.comparingByValue());
		  le.get(0).getKey().click();
		  
	}
	
}
