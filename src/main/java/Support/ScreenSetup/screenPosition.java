package Support.ScreenSetup;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import static Support.Initialization.Init.driver;

public class screenPosition {
	public static void LeftTop() {
		int screenNumber = -1;

		int screenWidth = 1440;
		int screenHeight = 768;

		// Xác định vị trí và kích thước của cửa sổ trình duyệt trên màn hình mong muốn
		int windowX = screenNumber * screenWidth;
		int windowY = -600; // Vị trí theo chiều cao không thay đổi
		Dimension windowSize = new Dimension(screenWidth, screenHeight);

		// Di chuyển cửa sổ và thiết lập kích thước
		driver.manage().window().setPosition(new Point(windowX, windowY));
		driver.manage().window().setSize(windowSize);
	}
	public static void FullScreen2() {
		int screenWidth = 1920;
		int screenHeight = 1080;
		int windowX = 2555;
//        int windowY = 330; // Vị trí theo chiều cao không thay đổi
		int windowY = 0; // Vị trí theo chiều cao không thay đổi
		Dimension windowSize = new Dimension(screenWidth, screenHeight);
		driver.manage().window().setPosition(new Point(windowX, windowY));
		driver.manage().window().setSize(windowSize);
	}
	public static void MidRight() {
		int screenWidth = 1280;
		int screenHeight = 1440;
		int windowX = 1281;
		int windowY = 0; // Vị trí theo chiều cao không thay đổi
		Dimension windowSize = new Dimension(screenWidth, screenHeight);
		driver.manage().window().setPosition(new Point(windowX, windowY));
		driver.manage().window().setSize(windowSize);
	}
	public static void MidRightMac() {
		int screenWidth = 1280;
		int screenHeight = 1440;
		int windowX = 1440+1280;
		int windowY = 0; // Vị trí theo chiều cao không thay đổi
		Dimension windowSize = new Dimension(screenWidth, screenHeight);
		driver.manage().window().setPosition(new Point(windowX, windowY));
		driver.manage().window().setSize(windowSize);
	}
}
