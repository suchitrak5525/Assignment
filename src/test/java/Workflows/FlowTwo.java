package Workflows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlowTwo {
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
	public void trendingSearch() throws InterruptedException
	{
		driver.findElement(By.xpath("//li[@class='appendLeft10'][1]")).click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		String mainWindowHandle = driver.getWindowHandle(); 
        Set<String> allWindowHandles = driver.getWindowHandles(); 
        Iterator<String> iterator =allWindowHandles.iterator();
        while (iterator.hasNext()) 
        { 
        	String ChildWindow = iterator.next(); 
        	if(!mainWindowHandle.equalsIgnoreCase(ChildWindow)) 
        	{
               driver.switchTo().window(ChildWindow); 
               Thread.sleep(3000);
               driver.findElement(By.xpath("//span[@class='bgProperties icon20 overlayCrossIcon']")).click();
               //driver.findElement(By.xpath("//p[contains(text(),'Late Departures') and @class='checkboxTitle']")).click();
       		List<WebElement> ls1 = driver.findElements(By.xpath("//div[@class='stop-info flexOne']"));
       		List<String> s1 = new ArrayList<String>();
       		List<Integer> array1 = new ArrayList<Integer>();
       		List<WebElement> flightNames = driver.findElements(By.xpath("//p[@class='boldFont blackText airlineName']"));
       		List<WebElement> flightCode = driver.findElements(By.xpath("//p[@class='fliCode']"));
       		for(int i=0;i<ls1.size();i++)
       		{
       			String text = ls1.get(i).findElement(By.tagName("p")).getText();
       			text=text.replaceAll("[^a-zA-Z0-9]", "");
       			text=text.replaceAll("[^\\d]","");
                //s1.removeIf(String::isEmpty);
       			s1.add(text);
       			s1.removeIf(String::isEmpty);
       			String subString1 = text.substring(0,2);
       			String subString2 = text.substring(text.length()-2);
       			int hours=Integer.parseInt(subString1);
       			int minutes = hours*60;
       			int actualMinutes = Integer.parseInt(subString2);
       			int sum = minutes+actualMinutes;
       			array1.add(sum);
       			
       			
       		}
       		s1.removeAll(Arrays.asList("", null));
       		System.out.println(s1);
       		int index = array1.indexOf(Collections.min(array1));
       		System.out.println("index value is:"+index);
       		System.out.println(flightNames.get(index).getText());
       		System.out.println(flightCode.get(index).getText());
       		System.out.println(array1);
       		Collections.sort(array1);
       		//System.out.println(array1);
       		
       		System.out.println("Min value is:"+Collections.min(array1));
       		
       		
        	}
        }
        	
	}   	

}
