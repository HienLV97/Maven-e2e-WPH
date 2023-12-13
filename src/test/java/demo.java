import Support.Initialization.Init;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.HashMap;

//import static Support.Initialization.Init.driver;

public class demo extends Init {

	@Test
	public void t()  {
		Authenticate();
		driver.get("https://ibhelper.dev/signin");

		WebElement emailTextbox = Support.Constants.getPlaceholder("Email");
		emailTextbox.sendKeys(Support.Constants.emailAccount);
		WebElement passTextbox = Support.Constants.getPlaceholder("Password");
		passTextbox.sendKeys(Support.Constants.passAccount);

		sleep(2);
		WebElement SubmitInBTN =  Support.Constants.getBtn("Yes, I understood");
		SubmitInBTN.click();
		sleep(2);

		WebElement SignInBTN = driver.findElement(By.xpath("//button[contains(text(),'Sign in')]"));
//		WebElement SignInBTN = Constants.getBtn("Sign in");

		SignInBTN.click();
		sleep(4);
	}
}
