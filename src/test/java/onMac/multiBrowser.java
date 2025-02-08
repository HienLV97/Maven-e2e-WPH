package onMac;
import Initialization.Init;
import helpers.drivers.DriverManager;
import org.testng.annotations.*;

public class multiBrowser extends Init {

	@Test(enabled = true, description = "simple Test")
	public void simpleTest()  {
		DriverManager.getDriver().get("https://www.google.com/");
		sleep(5);
	}

}
