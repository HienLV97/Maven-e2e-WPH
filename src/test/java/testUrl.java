import Support.Constants;
import Support.Initialization.Init;
import Support.Routers;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.openqa.selenium.Keys;

import java.util.HashMap;

public class testUrl extends Init {

	@Test
	public void Start() throws InterruptedException {
		Constants Constants = new Constants();
		driver.navigate().to(Routers.SIGN_IN);
//		driver.merge(capabilities);


		WebElement emailTextbox = Constants.getPlaceholder("Email");
		emailTextbox.sendKeys(Constants.emailAccount);
		WebElement passTextbox = Constants.getPlaceholder("Password");
		passTextbox.sendKeys(Constants.passAccount);

		Thread.sleep(1000);
		WebElement SubmitInBTN = Constants.getBtn("Yes, I understood");
		SubmitInBTN.click();
		Thread.sleep(1000);

		WebElement SignInBTN = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
//		WebElement SignInBTN = Constants.getBtn("Sign in");

		SignInBTN.click();
		sleep(4);
//        WebElement h1Tag = driver.findElement(By.className("hero__anchor"));

//        assertEquals(h1Tag.getText(),"IB WRITING SERVICE");
//        assertTrue(h1Tag.getText().contains("IB"));
	}
}
