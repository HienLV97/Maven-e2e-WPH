package Support.Initialization;

import Support.Routers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class Init {
	public static WebDriver driver = null;
	public  void Authenticate() {
		ChromeOptions chromeOptions = new ChromeOptions();
		driver.get(Routers.BaseURL2);

	}
	@BeforeTest
	public void Setup() {
		WebDriverWait wait;
		System.setProperty("webdriver.http.factory","jdk-http-client");

		driver = new ChromeDriver();

//		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));

//        screenPosition.MidRight();
//		screenPosition.MidRightMac();
//		Authenticate();
//        screenPosition.FullScreen2();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@AfterTest
	public void closeBrowser() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e){
			throw new RuntimeException(e);
		}
       driver.quit();
	}
	public void sleep( double second){
		try{
			Thread.sleep((long) (1000*second));
		} catch (InterruptedException e){
			throw new RuntimeException(e);
		}
	}
	public void waitForPageLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//Wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		//Check JS is Ready
		boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

		//Wait Javascript until it is Ready!
		if (!jsReady) {
			System.out.println("Javascript is NOT Ready.");
			//Wait for Javascript to load
			try {
				wait.until(jsLoad);
			} catch (Throwable error) {
				error.printStackTrace();
				Assert.fail("FAILED. Timeout waiting for page load.");
			}
		}
	}
}
