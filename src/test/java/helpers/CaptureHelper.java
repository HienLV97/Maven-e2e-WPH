package helpers;

import AcaWriting.Support.Initialization.Init;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;


import AcaWriting.drivers.DriverManager;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			 screenName = properties.getProperty("ScreenName");
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

	// Start record video
	public static void startRecord(String methodName) throws IOException {
		//Tạo thư mục để lưu file video vào
		File file = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("RECORDVIDEO_PATH"));
		readFileConfig();
		Rectangle captureSize = Init.browserPosition(screenName, DriverManager.getDriver());

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
			// Tạo tham chiếu đối tượng của TakesScreenshot với dirver hiện tại
			TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
			// Gọi hàm getScreenshotAs để chuyển hóa hình ảnh về dạng FILE
			File source = ts.getScreenshotAs(OutputType.FILE);
			//Kiểm tra folder nếu không tồn tại thì tạo folder
			File theDir = new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH"));
			if (!theDir.exists()) {
				theDir.mkdirs();
			}
			// Chổ này đặt tên thì truyền biến "screenName" gán cho tên File chụp màn hình
			FileHandler.copy(source, new File(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("SCREENSHOT_PATH") + File.separator + screenshotName + "_" + dateFormat.format(new Date()) + ".png"));
			System.out.println("Screenshot taken: " + screenshotName);
			System.out.println("Screenshot taken current URL: " + DriverManager.getDriver().getCurrentUrl());
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot: " + e.getMessage());
		}
	}
}
