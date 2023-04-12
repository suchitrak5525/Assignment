package pageHelpers;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import base.BaseTest;

import pageObjects.HomePageObjects;

import utility.GetElements;

public class SingleTripHelper {
	
	GetElements ge = new GetElements();
	public void selectFareSearch(String cityName,String fareType) throws InterruptedException
	{
		ge.getWebElement("xpath", HomePageObjects.FromCity).click();
		Thread.sleep(3000);
		ge.getWebElement("xpath", HomePageObjects.FromCityInput).sendKeys(cityName);
		Thread.sleep(3000);
		ge.getWebElement("xpath", HomePageObjects.searchFromCity).click();
		Thread.sleep(2000);
		ge.getWebElement("xpath", HomePageObjects.departure).click();
		Thread.sleep(2000);
		JavascriptExecutor js=(JavascriptExecutor)BaseTest.driver; 
		js.executeScript("arguments[0].click();", ge.getWebElement("xpath", HomePageObjects.departureDate));
		Thread.sleep(2000);
		List<WebElement> ls= ge.getWebElements("xpath",HomePageObjects.fareTypeClass);
		for(int i=0;i<ls.size();i++)
		{
			
			if(ls.get(i).getText().contains(fareType))
			{
				
				ls.get(i).click();
			}
		
		}
		Thread.sleep(2000);
		HomePageObjects.clickSearch();
		//ge.getWebElement("xpath",HomePageObjects.clickFareType).click();
		//Thread.sleep(7000);
		
		ge.getWebElement("xpath", HomePageObjects.closeIcon).click();
		Thread.sleep(5000);
		ge.getWebElement("xpath", HomePageObjects.selectFilter).click();
		List<WebElement> priceList=ge.getWebElements("css",HomePageObjects.priceListSelect);
		List<WebElement> flightList=ge.getWebElements("xpath", HomePageObjects.flightName);
		List<WebElement> flightCode=ge.getWebElements("xpath", HomePageObjects.flightCode);
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
		
             //System.out.println(intList);
             Collections.sort(intList);
             int index = intList.indexOf(Collections.min(intList));
		     System.out.println("Min value is :"+Collections.min(intList));
		     System.out.println("Flight Name is:"+flightList.get(index).getText());
		     System.out.println("Flight Code is:"+flightCode.get(index).getText());
		        	  
		        	
		     
	}
}
