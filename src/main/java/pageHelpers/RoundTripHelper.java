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

public class RoundTripHelper {
	
	GetElements ge = new GetElements();
	public void clickSearch() throws InterruptedException
	{
		ge.getWebElement("xpath", HomePageObjects.mmtLogo).click();
		Thread.sleep(3000);
		ge.getWebElement("xpath", HomePageObjects.returnClick).click();
		Thread.sleep(4000);
		JavascriptExecutor js=(JavascriptExecutor)BaseTest.driver; 
		js.executeScript("arguments[0].click();", ge.getWebElement("xpath", HomePageObjects.returnDate));
		Thread.sleep(2000);
		//ge.getWebElement("xpath", HomePageObjects.searchClick).click();
		//Thread.sleep(5000);
		HomePageObjects.clickSearch();
		ge.getWebElement("xpath", HomePageObjects.closeIcon).click();
		Thread.sleep(2000);
		ge.getWebElement("xpath", HomePageObjects.FlightDetailsLink).click();
		Thread.sleep(1000);
		List<WebElement> detailsList=ge.getWebElements("xpath", HomePageObjects.FlightDetails);
		for(int i=0;i<detailsList.size();i++)
		{
		 System.out.println("Round trip info:"+"\n"+detailsList.get(i).getText());
		}
		
		
	}

}
