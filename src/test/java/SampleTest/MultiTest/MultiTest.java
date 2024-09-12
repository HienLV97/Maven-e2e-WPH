package SampleTest.MultiTest;

import Support.Initialization.Init;
import Support.WPH.Routers;
import org.testng.annotations.Test;

public class MultiTest extends Init {

	@Test
	public void navigatePage1(){
		authenticate("WPH");
		sleep(3);
		driver.get(Routers.TESTIMONIALS);
	}
	@Test
	public void navigatePage2(){
		authenticate("WPH");
		sleep(3);
		driver.get(Routers.IB_WRITERS);
	}@Test
	public void navigatePage3(){
		authenticate("WPH");
		sleep(3);
		driver.get(Routers.ABOUT);
	}@Test
	public void navigatePage4(){
		authenticate("WPH");
		sleep(3);
		driver.get(Routers.POLICY_ACCEPTABLE);
	}
}
