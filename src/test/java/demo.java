import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.util.HashMap;

public class demo {
	@Test
	public void t() throws InterruptedException {
		// Set đường dẫn của ChromeDriver (cần tải và cài đặt trước)

		// Khởi tạo trình duyệt Chrome với DesiredCapabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		HashMap<String, Object> browserstackOptions = new HashMap<>();
		browserstackOptions.put("unhandledPromptBehavior", "accept");
		capabilities.setCapability("bstack:options", browserstackOptions);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.merge(capabilities);

		// Mở trang web
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get("https://ibhelper.dev/signin");

		// Xác thực cơ bản bằng cách xử lý alert
		Thread.sleep(5000);
		Alert alert1 = driver.switchTo().alert();
		alert1.sendKeys("kamora" + "\t" + "iamafriend");
		alert1.accept();

		// Bây giờ bạn đã được đăng nhập và có thể thực hiện các thao tác tiếp theo trên trang web

		// Đóng trình duyệt sau khi hoàn thành
		driver.quit();
	}
}
