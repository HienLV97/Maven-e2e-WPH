package Support.Initialization;

import Support.Routers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Init {
	public WebDriver driver;
	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	public String ScreenName = "3";

	public void Authenticate() {
		ChromeOptions chromeOptions = new ChromeOptions();
		driver.get(Routers.BaseURL2);

	}

	@BeforeTest
	@Parameters({"browser"})
	public void Setup(@Optional("chrome") String browserName) {
		WebDriverWait wait;
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		}
		if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		screenPosition(ScreenName);
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@AfterTest
	public void closeBrowser() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		driver.quit();
	}

	public void sleep(double second) {
		try {
			Thread.sleep((long) (1000 * second));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void waitForPageLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(5000));
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

	public void waitForNavigatePage(String value) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Thời gian chờ 10 giây
		if (Objects.equals(value, "NaN")) {
			wait.until(ExpectedConditions.or(
					ExpectedConditions.urlContains(Routers.BaseURL2),
					ExpectedConditions.urlContains(Routers.BaseURL)
			));
		} else {
			wait.until(ExpectedConditions.urlContains(value));
		}
		System.out.println("Trang đã chuyển thành công!");
	}

	public void screenShot(String ImgName) throws AWTException, IOException {
		Actions action = new Actions(driver);
		org.openqa.selenium.Point windowPosition = driver.manage().window().getPosition();
		int XScreen2 = windowPosition.getX();
		int YScreen2 = windowPosition.getY();

		// Lấy kích thước hiện tại của cửa sổ trình duyệt
		org.openqa.selenium.Dimension windowSize = driver.manage().window().getSize();
		int screenWidth = windowSize.getWidth();
		int screenHeight = windowSize.getHeight();
		if (Objects.equals(ScreenName, "2")) {
			XScreen2 = -1440;
			YScreen2 = -338;
			screenWidth = 1440;
			screenHeight = 2560;
		}
		if (Objects.equals(ScreenName, "3")) {
			XScreen2 = 0;
			YScreen2 = 1440;
			screenWidth = 1920;
			screenHeight = 1080;
		}
		if (Objects.equals(ScreenName, "MidRight")) {
			screenWidth = 1280;
			screenHeight = 1440;
			XScreen2 = 1281;
			YScreen2 = 0;
		}
		if (Objects.equals(ScreenName, "MidRightMac")) {
			screenWidth = 1280;
			screenHeight = 1440;
			XScreen2 = 1440 + 1280;
			YScreen2 = 0;
		}
		sleep(5);
		Robot robot = new Robot();
		Rectangle screenRectangle = new Rectangle(XScreen2, YScreen2, screenWidth, screenHeight);
		BufferedImage image = robot.createScreenCapture(screenRectangle);
		File file = new File("src/test/resources/screenshots/" + ImgName + ".png");
		ImageIO.write(image, "png", file);

	}

	public void screenPosition(String value) {
		if (value.equals("1")) {
			driver.manage().window().maximize();
		}
		if (value.equals("2")) {
			int screenNumber = -1;
			int screenWidth = 1440;
			int screenHeight = 2560;

			// Xác định vị trí và kích thước của cửa sổ trình duyệt trên màn hình mong muốn
			int windowX = screenNumber * screenWidth;
			int windowY = -338; // Vị trí theo chiều cao không thay đổi
			org.openqa.selenium.Dimension windowSize = new org.openqa.selenium.Dimension(screenWidth, screenHeight);

			// Di chuyển cửa sổ và thiết lập kích thước
			driver.manage().window().setPosition(new org.openqa.selenium.Point(windowX, windowY));
			driver.manage().window().setSize(windowSize);
		}
		if (value.equals("3")) {
			int screenWidth = 1920;
			int screenHeight = 1080;
			int windowX = 0;
			int windowY = 1440; // Vị trí theo chiều cao không thay đổi
			org.openqa.selenium.Dimension windowSize = new org.openqa.selenium.Dimension(screenWidth, screenHeight);
			driver.manage().window().setPosition(new org.openqa.selenium.Point(windowX, windowY));
			driver.manage().window().setSize(windowSize);
		}
		if (value.equals("MidRight")) {
			int screenWidth = 1280;
			int screenHeight = 1440;
			int windowX = 1281;
			int windowY = 0; // Vị trí theo chiều cao không thay đổi
			org.openqa.selenium.Dimension windowSize = new org.openqa.selenium.Dimension(screenWidth, screenHeight);
			driver.manage().window().setPosition(new org.openqa.selenium.Point(windowX, windowY));
			driver.manage().window().setSize(windowSize);
		}
		if (value.equals("MidRightMac")) {
			int screenWidth = 1280;
			int screenHeight = 1440;
			int windowX = 1440 + 1280;
			int windowY = 0; // Vị trí theo chiều cao không thay đổi
			org.openqa.selenium.Dimension windowSize = new org.openqa.selenium.Dimension(screenWidth, screenHeight);
			driver.manage().window().setPosition(new org.openqa.selenium.Point(windowX, windowY));
			driver.manage().window().setSize(windowSize);
		}


	}
}
