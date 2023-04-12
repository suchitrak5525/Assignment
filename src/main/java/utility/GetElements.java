package utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class GetElements {
	
	public WebElement getWebElement(String identifierType, String identifierValue)
	{
		switch(identifierType)
		{ 
		  case "xpath":
		  return BaseTest.driver.findElement(By.xpath(identifierValue));
		  
		  case "css":
	      return BaseTest.driver.findElement(By.cssSelector(identifierValue));
	      
		  case "id":
		  return BaseTest.driver.findElement(By.id(identifierValue));
		  
		  case "class":
	      return BaseTest.driver.findElement(By.className(identifierValue));
	      
	      default:
	      return null;
			  
		}
	}
	
	public List<WebElement> getWebElements(String identifierType, String identifierValue)
	{
		switch(identifierType)
		{ 
		  case "xpath":
		  return BaseTest.driver.findElements(By.xpath(identifierValue));
		  
		  case "css":
	      return BaseTest.driver.findElements(By.cssSelector(identifierValue));
	      
		  case "id":
		  return BaseTest.driver.findElements(By.id(identifierValue));
		  
		  case "class":
	      return BaseTest.driver.findElements(By.className(identifierValue));
	      
	      default:
	      return null;
			  
		}
	}

}
