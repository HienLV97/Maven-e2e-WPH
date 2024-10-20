package AcaWriting.Support.Initialization;

import java.io.*;
import java.util.Properties;

import AcaWriting.Support.WPH.Routers;
import logs.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	//	public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	private String screenName;

	public void authenticate(String env) {
//		switch (env.trim().toLowerCase()) {
//			case "wph":
//				DriverManager.getDriver().get(Routers.AuthURL);
//			case "dashboard":
//				DriverManager.getDriver().get(AcaWriting.Support.DashBoard.Routers.AuthURL);
//			case "writer":
//				DriverManager.getDriver().get(AcaWriting.Support.Writer.Routers.AuthURL);
//			case "cms":
//				DriverManager.getDriver().get(AcaWriting.Support.CMS.Routers.AuthURL);
//			default:
//				DriverManager.getDriver().get(Routers.AuthURL);
//		}
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

	//Viết hàm trung gian để lựa chọn Browser cần chạy với giá trị tham số "browser" bên trên truyền vào
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
			screenName = properties.getProperty("ScreenName");
			LogUtils.info("ScreenName: " + screenName);
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
			screenName = properties.getProperty("ScreenName");
			LogUtils.info("ScreenName: " + screenName);
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
			screenName = properties.getProperty("ScreenName");
			LogUtils.info("ScreenName: " + screenName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		browserPosition(screenName, driver);
		return driver;
	}


	//a

//	@AfterMethod
	public void closeDriver() {
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

	public void screenShot(String ImgName) throws AWTException, IOException {
//		WebDriver driver;
		Actions action = new Actions(DriverManager.getDriver());
		org.openqa.selenium.Point windowPosition = DriverManager.getDriver().manage().window().getPosition();
		int XScreen2 = windowPosition.getX();
		int YScreen2 = windowPosition.getY();

		// Lấy kích thước hiện tại của cửa sổ trình duyệt
		org.openqa.selenium.Dimension windowSize = DriverManager.getDriver().manage().window().getSize();
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

	public void browserPosition(String value, WebDriver driver) {
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
