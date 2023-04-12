package pageObjects;

import utility.GetElements;

public interface HomePageObjects {
	String FromCity="//*[@id='fromCity']";
	 String FromCityInput="//*[@placeholder='From']";
	 String searchFromCity="//p[text()='Lucknow, India']";
	 String departure="//span[@class='lbl_input appendBottom10' and text()='Departure']";
	 String departureDate="//div[@aria-label='Tue Apr 25 2023']";
	 String fareTypeClass="//li[starts-with(@class,'font12 blackText wrapFilter')]";
	 String clickFareType="//a[text()='Search']";
	 String closeIcon="//span[@class='bgProperties icon20 overlayCrossIcon']";
	 String selectFilter="//p[contains(text(),'Non Stop') and @class='checkboxTitle']";
	 String priceListSelect=".blackText.fontSize18.blackFont.white-space-no-wrap";
	 String flightName="//p[@class='boldFont blackText airlineName']";
	 String flightCode="//p[@class='fliCode']";
	 
	  String mmtLogo = "//a[@class='chMmtLogo']";
	  String clickRoundTrip="//li[@data-cy='roundTrip']";
	  String returnClick="//span[@class='lbl_input appendBottom10' and text()='Return']";
	  String returnDate="//div[@aria-label='Sat Apr 29 2023']";
	  String searchClick = "//a[@class='primaryBtn font24 latoBold widgetSearchBtn ']";
	  String FlightDetailsLink="//p[@class='skyBlueText fontSize12 pointer '][1]";
	  String FlightDetails="//p[@class='makeFlex hrtlCenter appendBottom20 gap-x-10']";
	  
	  GetElements ge = new GetElements();
	  
	  public static void clickSearch() throws InterruptedException
		{
			ge.getWebElement("xpath", HomePageObjects.searchClick).click();
			Thread.sleep(5000);
		}

}
