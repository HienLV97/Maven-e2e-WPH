package AcaWriting.Support.Initialization;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import AcaWriting.Support.WPH.Routers;
import helpers.CaptureHelper;
import logs.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
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

import AcaWriting.drivers.DriverManager;


public class Init {
	public WebDriver driver;
	private String screenName;
	public void authenticate(String env) {
		if (env.trim().equalsIgnoreCase("wph")) {
			DriverManager.getDriver().get(Routers.AuthURL);
		}
		if (env.trim().equalsIgnoreCase("dashboard")) {
			DriverManager.getDriver().get(AcaWriting.Support.DashBoard.Routers.AuthURL);
		}
		if (env.trim().equalsIgnoreCase("writer")) {
			DriverManager.getDriver().get(AcaWriting.Support.Writer.Routers.AuthURL);
		}
		if (env.trim().equalsIgnoreCase("cms")) {
			DriverManager.getDriver().get(AcaWriting.Support.CMS.Routers.AuthURL);
		}
	}

	@BeforeMethod
	@Parameters({"browser"})
	public void createDriver(@Optional("chrome") String browserName) {
		WebDriver driver = setupDriver(browserName);
		DriverManager.setDriver(driver);
	}

	//Viết hàm trung gian để lựa chọn Browser cần chạy với giá trị tham số "browser" bên trên truyền vào\
	public WebDriver setupDriver(String browserName) {
		WebDriver driver;
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
				LogUtils.info("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
				driver = initChromeDriver();
		}
		return driver;
	}

	// Viết các hàm khởi chạy cho từng Browser đó
	private WebDriver initChromeDriver() {
		WebDriver driver;
		LogUtils.info("Launching Chrome browser...");
		driver = new ChromeDriver();
		Properties properties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			properties.load(configFile);
			screenName = properties.getProperty("SCREENNAME");
			LogUtils.info("SCREENNAME: " + screenName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName, driver);
		return driver;
	}

	private WebDriver initEdgeDriver() {
		WebDriver driver;
		LogUtils.info("Launching Edge browser...");
		driver = new EdgeDriver();
		Properties properties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			properties.load(configFile);
			screenName = properties.getProperty("SCREENNAME");
			LogUtils.info("SCREENNAME: " + screenName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName, driver);
		return driver;
	}

	private WebDriver initFirefoxDriver() {
		WebDriver driver;
		LogUtils.info("Launching Firefox browser...");
		driver = new FirefoxDriver();
		Properties properties = new Properties();
		try {
			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			properties.load(configFile);
			screenName = properties.getProperty("SCREENNAME");
			LogUtils.info("SCREENNAME: " + screenName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName, driver);
		return driver;
	}

	@AfterMethod
	public void closeDriver(ITestResult iTestResult) {
		if (iTestResult.getStatus() == ITestResult.FAILURE){
			CaptureHelper.captureScreenshot("Fail_"+iTestResult.getName());
		}
		if (iTestResult.getStatus() == ITestResult.SUCCESS){
			CaptureHelper.captureScreenshot("Success_"+iTestResult.getName());
		}
		try {
			// Giả sử đây là nơi bạn thực hiện các thao tác, ví dụ:
			// driver.findElement(By.id("someElement"));

		} catch (Exception e) {
			LogUtils.info("Có lỗi xảy ra: " + e.getMessage());

			// Chờ 10 giây trước khi đóng driver
			try {
				Thread.sleep(10000); // Chờ 10 giây
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		} finally {
			// Đóng driver sau khi chờ 10 giây nếu có lỗi
			if (DriverManager.getDriver() != null) {
				DriverManager.quit();
				LogUtils.info("Driver đã được đóng.");
			}
		}
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

	public void waitForNavigatePage(String value) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10)); // Khởi tạo wait

		String router = getBaseUrl(DriverManager.getDriver().getCurrentUrl());
		if (Objects.equals(value, "NaN")) {
			if (Objects.equals(router, Routers.BaseURL)) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.urlContains(Routers.AuthURL),
						ExpectedConditions.urlContains(Routers.BaseURL)
				));
			} else if (Objects.equals(router, AcaWriting.Support.Writer.Routers.AuthURL)) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.urlContains(AcaWriting.Support.Writer.Routers.AuthURL),
						ExpectedConditions.urlContains(AcaWriting.Support.Writer.Routers.BaseURL)
				));
			} else if (Objects.equals(router, AcaWriting.Support.DashBoard.Routers.AuthURL)) {
				wait.until(ExpectedConditions.or(
						ExpectedConditions.urlContains(AcaWriting.Support.DashBoard.Routers.AuthURL),
						ExpectedConditions.urlContains(AcaWriting.Support.DashBoard.Routers.BaseURL)
				));
			}
		} else {
			wait.until(ExpectedConditions.urlContains(value));
		}
		LogUtils.info("Trang đã chuyển thành công!");
	}

	public void screenShot(String imgName) throws AWTException, IOException {
		Rectangle screenRect = browserPosition(screenName, DriverManager.getDriver());
		sleep(5);
		BufferedImage image = new Robot().createScreenCapture(screenRect);
		ImageIO.write(image, "png", new File("src/test/resources/screenshots/" + imgName + ".png"));
		LogUtils.info("Screenshot success!");
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

	public static Rectangle browserPosition(String value, WebDriver driver) {
		Map<String, org.openqa.selenium.Dimension> screenSizes = Map.of(
				"2", new org.openqa.selenium.Dimension(1440, 2560),
				"3", new org.openqa.selenium.Dimension(1920, 1080),
				"MidRight", new org.openqa.selenium.Dimension(1280, 1440),
				"MidRightMac", new org.openqa.selenium.Dimension(1280, 1440)
		);
		Map<String, org.openqa.selenium.Point> screenPositions = Map.of(
				"2", new org.openqa.selenium.Point(-1440, -338),
				"3", new org.openqa.selenium.Point(0, 1440),
				"MidRight", new org.openqa.selenium.Point(1281, 0),
				"MidRightMac", new org.openqa.selenium.Point(2720, 0)
		);

		if (value.equals("1")) {
			driver.manage().window().maximize();
			return new Rectangle(driver.manage().window().getPosition().getX(),
					driver.manage().window().getPosition().getY(),
					driver.manage().window().getSize().getWidth(),
					driver.manage().window().getSize().getHeight());
		} else {
			driver.manage().window().setPosition(screenPositions.get(value));
			driver.manage().window().setSize(screenSizes.get(value));
			return new Rectangle(screenPositions.get(value).getX(), screenPositions.get(value).getY(),
					screenSizes.get(value).getWidth(), screenSizes.get(value).getHeight());
		}
	}

	public static String formatPrice(double price) {
		return String.format("%.2f", price);
	}
}
