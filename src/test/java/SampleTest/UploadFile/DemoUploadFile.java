package SampleTest.UploadFile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoUploadFile {
	public static void main(String[] args) {
		// Khởi tạo WebDriver
		System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
		WebDriver driver = new ChromeDriver();

		try {
			// Điều hướng tới trang web
			driver.get("https://example.com/upload");

			// Tìm thẻ input có type="file"
			WebElement uploadElement = driver.findElement(By.id("file-upload"));

			// Đưa đường dẫn tuyệt đối của file cần upload
			uploadElement.sendKeys("C:/path/to/file.txt");

			// Tìm và click vào nút upload (nếu có)
			WebElement uploadButton = driver.findElement(By.id("upload-button"));
			uploadButton.click();

			// Các bước tiếp theo sau khi upload thành công (nếu có)
		} finally {
			// Đóng trình duyệt
			driver.quit();
		}
	}
}
