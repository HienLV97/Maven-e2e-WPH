package Support.Initialization;

import Support.ScreenSetup.screenPosition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

public class Init {
	public static WebDriver driver = null;

	@BeforeTest
	public void Setup() {

		driver = new ChromeDriver();
//        screenPosition.MidRight();
		screenPosition.MidRightMac();
//        screenPosition.FullScreen2();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeBrowser() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e){
			throw new RuntimeException(e);
		}
//        driver.quit();
	}
	public void sleep( double second){
		try{
			Thread.sleep((long) (1000*second));
		} catch (InterruptedException e){
			throw new RuntimeException(e);
		}
	}
}
