package ActualTest;

import org.testng.annotations.Test;

import base.BaseTest;
import pageHelpers.RoundTripHelper;
import pageHelpers.SingleTripHelper;
import utility.GetElements;

public class NewTest extends BaseTest{
 GetElements getElement = new GetElements();
 SingleTripHelper singleTrip = new SingleTripHelper();
 RoundTripHelper roundTrip = new RoundTripHelper();
 
  @Test(priority=1)
  public void flowOne() throws InterruptedException {
	  logger=extentReports.createTest("single trip flow execution");
	  singleTrip.selectFareSearch("Lucknow", "Armed Forces");
  }
  
  @Test(priority=2)
  public void flowTwo() throws InterruptedException {
	  logger=extentReports.createTest("round trip flow execution");
	  roundTrip.clickSearch();
	 
	  
  }
  
}
