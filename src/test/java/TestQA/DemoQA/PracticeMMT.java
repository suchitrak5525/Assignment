package TestQA.DemoQA;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeMMT {
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
	
	
	@Test(enabled=false)
	public void viewResults() throws InterruptedException
	{
		    driver.findElement(By.xpath("//a[@href='https://www.makemytrip.com/cabs/']")).click();
		    Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='fromCity']")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@placeholder='From']")).sendKeys("Mumbai");
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//span[text()='Mumbai, Maharashtra, India']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[@placeholder='To']")).sendKeys("Delhi");
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//*[contains(@class,'sr_city blackText')]")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//div[@class='DayPicker-Day' and text()='26']")).click();
		    Thread.sleep(3000);
		    driver.findElement(By.xpath("//span[@class='hrSlotItemChild ' and text()='08  Hr']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//span[@class='minSlotItemChild ' and text()='30  min']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//span[@class='meridianSlotItemChild ' and text()='PM']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//span[@class='applyBtnText']")).click();
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("//a[text()='Search']")).click();
		    Thread.sleep(4000);
		    
		
	}
	
	@Test(enabled=false)
	public void checkMinPrice()
	{
		List<WebElement> list = driver.findElement(By.id("List")).findElements(By.xpath("//span[@class='latoBlack font20 appendRight5']"));
		List<WebElement> list1 = driver.findElement(By.id("List")).findElements(By.xpath("//p[@class='font28 latoBlack blackText ']"));
		for(int i=0;i<list.size();i++)
		{
			
			
			if(list.get(i).getText().contains("Xylo, Ertiga"))
			{
				String value=list1.get(i).getText();
				System.out.println(value);
				
			}
			
		}
		
	    
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
			//System.out.println(ls.get(i).getText());
			if(ls.get(i).getText().contains("Armed Forces"))
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
		List<Integer> intList= new ArrayList<Integer>();
		List<String> strList = new ArrayList<String>();
		for(int i=0;i<priceList.size();i++)
		{   
			String reviewValue=priceList.get(i).getText();
			for (WebElement child : priceList.get(i).findElements(By.xpath("./*"))) {
		          reviewValue = reviewValue.replaceFirst(child.getText(), "");
		          
		        }
			
			reviewValue=reviewValue.replaceAll("[^a-zA-Z0-9]", "");
			int value=Integer.parseInt(reviewValue);
		    intList.add(value);
		    strList.add(reviewValue);
		    
		  }
		
             System.out.println(intList);
             Collections.sort(intList);
		     System.out.println("Min value is :"+Collections.min(intList));
		     System.out.println("Max value is :"+Collections.max(intList));
		     int min= intList.get(0);
		     int max= Collections.max(intList);
		     //driver.navigate().to(driver.getCurrentUrl());
		     List<WebElement> buttonsList = driver.findElements(By.xpath("//button[contains(@class,'ViewFareBtn  text-uppercase ')]"));
		     for(int j=0;j<strList.size();j++) {
				 
		          if(Integer.parseInt(strList.get(j))==min) 
		          {
		        	  
		        	//List<WebElement> button=driver.findElements(By.xpath("//button[contains(@class,'ViewFareBtn  text-uppercase ')]"));
		        	  buttonsList.get(j).click();
		            driver.findElement(By.xpath("//button[text()='Book Now']")).click();
		            //driver.findElement(By.xpath("//button[text()='Yes! I understand']")).click();
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
		                   driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		                   driver.findElement(By.xpath("//span[@class='couponCode']")).click();
		                   Thread.sleep(2000);
		                   String totalFare2=driver.findElement(By.xpath("//div[@class='pageRightContainer customScroll']")).findElement(By.xpath("//p[@class='fareRow']")).getText();
		                   System.out.println(totalFare2);
		            	} 
		            	 
		            	} 
		           
		            }
		          
		          
		 
		  }
			 
		    
		
		
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.quit();
		
	}
	
	
}
