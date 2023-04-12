package Workflows;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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

public class FlowOne {
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
				
				ls.get(i).click();
			}
		}
		driver.findElement(By.xpath("//a[text()='Search']")).click();
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")).click();
		driver.findElement(By.xpath("//p[contains(text(),'Vistara') and @class='checkboxTitle']")).click();
		
		List<WebElement> priceList=driver.findElements(By.cssSelector(".blackText.fontSize18.blackFont.white-space-no-wrap"));
		List<WebElement> buttonsList = driver.findElements(By.xpath("//button[contains(@class,'ViewFareBtn  text-uppercase ')]"));
		List<Integer> intList= new ArrayList<Integer>();
		List<String> strList = new ArrayList<String>();
		
		for(int i=0;i<priceList.size();i++)
		{   
			String reviewValue=priceList.get(i).getText();
			for (WebElement child : priceList.get(i).findElements(By.xpath("./*"))) {
		          reviewValue = reviewValue.replaceFirst(child.getText(), "");
		          
		        }
			
			reviewValue=reviewValue.replaceAll("[^a-zA-Z0-9]", "");
		    intList.add(Integer.parseInt(reviewValue));
		    strList.add(reviewValue);
		    
		  }
		    
		     Collections.sort(intList);
		     int min= intList.get(0);
			 for(int j=0;j<strList.size();j++) {
				 
			          if(min==Integer.parseInt(strList.get(j))) 
			          {
			        	  
			            buttonsList.get(j).click(); 
			            driver.findElement(By.xpath("//button[text()='Book Now']")).click();
			            driver.findElement(By.xpath("//button[text()='Yes! I understand']")).click();
			            String mainWindowHandle = driver.getWindowHandle(); 
			            Set<String> allWindowHandles = driver.getWindowHandles(); 
			            Iterator<String> iterator =allWindowHandles.iterator();
			            while (iterator.hasNext()) 
			            { 
			            	String ChildWindow = iterator.next(); 
			            	if(!mainWindowHandle.equalsIgnoreCase(ChildWindow)) 
			            	{
			                   driver.switchTo().window(ChildWindow); 
			                   Thread.sleep(2000);
			                   String totalFare=driver.findElement(By.xpath("//div[@class='pageRightContainer customScroll']")).findElement(By.xpath("//p[@class='fareRow']")).getText(); 
			                   System.out.println(totalFare); 
			                   
			            	} 
			            	 
			            	} 
			           
			            }
			 
			  }
			 
		    
		
		
	}
	
}
