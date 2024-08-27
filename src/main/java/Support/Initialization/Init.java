package Support.Initialization;

import java.io.*;
import java.util.Properties;

import Support.WPH.Routers;
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
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import javax.net.ssl.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Init {
	public WebDriver driver;
	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private String screenName;

	public void authenticate(String env) {
		ChromeOptions chromeOptions = new ChromeOptions();
		if (env.equals("WPH")) {
			driver.get(Routers.AuthURL);
		}
		if (env.equals("DashBoard")) {
			driver.get(Support.DashBoard.Routers.AuthURL);
		}
		if (env.equals("Writer")) {
			driver.get(Support.Writer.Routers.AuthURL);
		}
	}

	//	@BeforeMethod(groups = {"verifyPrice"})
//	@Test(alwaysRun = true)
//	@BeforeSuite
//	@Parameters({"browser"})
//	public void Setup(@Optional("chrome") String browserName) {
//		System.setProperty("webdriver.http.factory", "jdk-http-client");
//		switch (browserName.trim().toLowerCase()) {
//			case "chrome" -> driver = new ChromeDriver();
//			case "edge" -> driver = new EdgeDriver();
//			case "firefox" -> driver = new FirefoxDriver();
//		}
//		Properties properties = new Properties();
//		try {
//			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
//			properties.load(configFile);
//			screenName = properties.getProperty("ScreenName");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		browserPosition(screenName);
//		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	}

//	@BeforeSuite
	@BeforeMethod
	@Parameters({"browser"})
	public void createDriver(@Optional("chrome") String browser) {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		setupDriver(browser);
	}

	public WebDriver setupDriver(String browserName) {
		switch (browserName.trim().toLowerCase()) {
			case "chrome":
				driver = initChromeDriver();
				break;
			case "firefox":
				driver = initFirefoxDriver();
				break;
			case "edge":
				driver = initEdgeDriver();
				break;
			default:
				System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
				driver = initChromeDriver();
		}
		return driver;
	}

	private WebDriver initChromeDriver() {
		System.out.println("Launching Chrome browser...");
		driver = new ChromeDriver();
		System.out.println("driver: "+driver);
		Properties properties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			properties.load(configFile);
			screenName = properties.getProperty("ScreenName");
			System.out.println("ScreenName: "+ screenName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName);
		return driver;
	}

	private WebDriver initEdgeDriver() {
		System.out.println("Launching Edge browser...");
		driver = new EdgeDriver();
		Properties properties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			properties.load(configFile);
			screenName = properties.getProperty("ScreenName");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName);
		return driver;
	}

	private WebDriver initFirefoxDriver() {
		System.out.println("Launching Firefox browser...");
		driver = new FirefoxDriver();
		Properties properties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			properties.load(configFile);
			screenName = properties.getProperty("ScreenName");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName);
		return driver;
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

	public static String getBaseUrl(String url) {
		try {
			java.net.URL urlObj = new java.net.URL(url);
			String protocol = urlObj.getProtocol();
			String host = urlObj.getHost();
			return protocol + "://" + host + "/";
		} catch (java.net.MalformedURLException e) {
			e.printStackTrace();
			return null;
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
		String router = getBaseUrl(driver.getCurrentUrl());
		if (Objects.equals(value, "NaN")) {
			if (Objects.equals(router, Routers.BaseURL)) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.urlContains(Routers.AuthURL),
						ExpectedConditions.urlContains(Routers.BaseURL)
				));
			} else if (Objects.equals(router, Support.Writer.Routers.AuthURL)) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.urlContains(Support.Writer.Routers.AuthURL),
						ExpectedConditions.urlContains(Support.Writer.Routers.BaseURL)
				));
			} else if (Objects.equals(router, Support.DashBoard.Routers.AuthURL)) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.urlContains(Support.DashBoard.Routers.AuthURL),
						ExpectedConditions.urlContains(Support.DashBoard.Routers.BaseURL)
				));
			}
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
		if (Objects.equals(screenName, "2")) {
			XScreen2 = -1440;
			YScreen2 = -338;
			screenWidth = 1440;
			screenHeight = 2560;
		}
		if (Objects.equals(screenName, "3")) {
			XScreen2 = 0;
			YScreen2 = 1440;
			screenWidth = 1920;
			screenHeight = 1080;
		}
		if (Objects.equals(screenName, "MidRight")) {
			screenWidth = 1280;
			screenHeight = 1440;
			XScreen2 = 1281;
			YScreen2 = 0;
		}
		if (Objects.equals(screenName, "MidRightMac")) {
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

	public static void skipSSL() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkClientTrusted(X509Certificate[] certs, String authType) {
					}

					public void checkServerTrusted(X509Certificate[] certs, String authType) {
					}
				}
		};

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		HostnameVerifier allHostsValid = (hostname, session) -> true;
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}

	public void browserPosition(String value) {
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

	public static String formatPrice(double price) {
		return String.format("%.2f", price);
	}
}
