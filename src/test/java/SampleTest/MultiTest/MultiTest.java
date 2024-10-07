package SampleTest.MultiTest;

import AcaWriting.Support.Initialization.Init;
import AcaWriting.Support.WPH.Routers;
import AcaWriting.drivers.DriverManager;
import org.testng.annotations.Test;

public class MultiTest extends Init {

	@Test
	public void navigatePage1(){
		authenticate("WPH");
		sleep(3);
		DriverManager.getDriver().get(Routers.TESTIMONIALS);
	}
	@Test
	public void navigatePage2(){
		authenticate("WPH");
		sleep(3);
		DriverManager.getDriver().get(Routers.IB_WRITERS);
	}@Test
	public void navigatePage3(){
		authenticate("WPH");
		sleep(3);
		DriverManager.getDriver().get(Routers.ABOUT);
	}@Test
	public void navigatePage4(){
		authenticate("WPH");
		sleep(3);
		DriverManager.getDriver().get(Routers.POLICY_ACCEPTABLE);
	}
}
