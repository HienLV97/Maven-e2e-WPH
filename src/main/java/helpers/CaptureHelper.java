package helpers;

//import helpers.Initialization.Init;

import logs.LogUtils;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


import helpers.drivers.DriverManager;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class CaptureHelper extends ScreenRecorder {

	// Record with Monte Media library
	public static ScreenRecorder screenRecorder;
	public String name;
	private static String screenName;

	 public static void readFileConfig() throws IOException {
			 Properties properties = new Properties();
			 FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
			 properties.load(configFile);
			 screenName = properties.getProperty("SCREENAME");
	}

	//Hàm xây dựng
	public CaptureHelper(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat, Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder, String name) throws IOException, AWTException {
		super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
		this.name = name;
	}

	//Hàm này bắt buộc để ghi đè custom lại hàm trong thư viên viết sẵn
	@Override
	protected File createMovieFile(Format fileFormat) throws IOException {

		if (!movieFolder.exists()) {
			movieFolder.mkdirs();
		} else if (!movieFolder.isDirectory()) {
			throw new IOException("\"" + movieFolder + "\" is not a directory.");
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(fileFormat));
	}

	public static Rectangle browserPosition(String value, WebDriver driver) {
		Map<String, org.openqa.selenium.Dimension> screenSizes = Map.of(
				"2", new org.openqa.selenium.Dimension(1440, 2560),
				"3", new org.openqa.selenium.Dimension(1920, 1080),
				"4", new org.openqa.selenium.Dimension(1920, 1080),
				"MidRight", new org.openqa.selenium.Dimension(1280, 1440),
				"MidRightMac", new org.openqa.selenium.Dimension(1280, 1440)
		);
		Map<String, org.openqa.selenium.Point> screenPositions = Map.of(
				"2", new org.openqa.selenium.Point(-1440, -338),
				"3", new org.openqa.selenium.Point(0, 1440),
				"4",new org.openqa.selenium.Point(2560,0),
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

	// Start record video
	public static void startRecord(String methodName) throws IOException {
		//Tạo thư mục để lưu file video vào
		File file = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("RECORDVIDEO_PATH"));
		readFileConfig();
		Rectangle captureSize = browserPosition(screenName, DriverManager.getDriver());

//		Properties properties = new Properties();
//		try {
//			FileInputStream configFile = new FileInputStream("src/Config/browserConfig.properties");
//			properties.load(configFile);
//			screenName = properties.getProperty("SCREENNAME");
//			LogUtils.info("SCREENNAME: " + screenName);
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}

		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		try {
			screenRecorder = new CaptureHelper(gc, captureSize, new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, methodName);
			screenRecorder.start();
		} catch (IOException | AWTException e) {
			throw new RuntimeException(e);
		}
	}

	// Stop record video
	public static void stopRecord() {
		try {
			screenRecorder.stop();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	//Tạo format ngày giờ để xíu gắn dô cái name của screenshot hoặc record video không bị trùng tên (không bị ghi đè file)
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

	public static void captureScreenshot(String screenshotName) {
		try {
			// Tạo tham chiếu đối tượng của TakesScreenshot với driver hiện tại
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
			// Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
			File source = ts.getScreenshotAs(OutputType.FILE);

			// Xác định đường dẫn lưu ảnh dựa trên trạng thái
			String folderPath;
			if (screenshotName.toLowerCase().contains("fail")) {
				folderPath = PropertiesHelper.getValue("SCREENSHOT_PATH_FAIL");
			} else if (screenshotName.toLowerCase().contains("success")) {
				folderPath = PropertiesHelper.getValue("SCREENSHOT_PATH_SUCCESS");
			} else {
				folderPath = PropertiesHelper.getValue("SCREENSHOT_PATH_GENERAL");
			}

			// Kiểm tra folder nếu không tồn tại thì tạo folder
			File theDir = new File(SystemHelper.getCurrentDir() + folderPath);
			if (!theDir.exists()) {
				theDir.mkdirs();
			}

			// Lưu ảnh chụp màn hình vào thư mục chỉ định
			FileHandler.copy(source, new File(SystemHelper.getCurrentDir() + folderPath
					+ File.separator + screenshotName + "_" + dateFormat.format(new Date()) + ".png"));

			LogUtils.info("Screenshot taken: " + screenshotName);
			LogUtils.info("Screenshot current URL: " + DriverManager.getDriver().getCurrentUrl());
		} catch (Exception e) {
			LogUtils.error("Exception while taking screenshot: " + e.getMessage());
		}
	}

}
