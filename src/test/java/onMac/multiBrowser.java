package onMac;
import API.GetAPI.NextProxy.Citation.Citation;
import API.GetAPI.NextProxy.SignIn.SignIn;
import Calculator.Calculator;
import Support.Initialization.Init;
import Support.WPH.Routers;
import WPH.OrderForm.pages.OrderFormPage;
import WPH.SignIn.pages.SignInPage;
import WPH.payment.CreditCard.pages.CreditCardPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class multiBrowser extends Init {

	@Test(enabled = true, description = "simple Test")
	public void simpleTest()  {
		driver.get("https://www.google.com/");
		sleep(5);
	}

}
