package WPH.OrderDetails.Details.pages;

import Keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class DetailsPage {
	private WebDriver driver;
	private WebDriverWait wait;
	public DetailsPage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		new WebUI(driver); //Bắt buộc
	}
	public By h1Element= By.xpath("//h1[@class='m-0']");
	public By writerPrice = By.xpath("//dt[normalize-space()='Writer Category']//following-sibling::dd");
	public By preWriterPrice = By.xpath("//dt[normalize-space()='Previous Writer']//following-sibling::dd");
	public By absPrice = By.xpath("//dt[normalize-space()='One-page Abstract']//following-sibling::dd");
	public By DicountPrice = By.xpath("//dt[normalize-space()='Discount']//following-sibling::dd");
	public By PaidPrice = By.xpath("//dt[normalize-space()='YOU PAID']//following-sibling::dd");
	public By YouSavedPrice = By.xpath("//span[@id='pre-save2']");
	public void verifyh1(String id, String orderType) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(h1Element));
		driver.findElement(h1Element);
		// Tìm phần tử thẻ h1
//		WebElement h1Element = driver.findElement(By.xpath("//h1[@class='m-0']"));

		// Lấy văn bản bên trong thẻ h1
		String h1Text = driver.findElement(h1Element).getText();

		// Kiểm tra xem văn bản có chứa "00088984" và "writing"
		boolean containsOrderId = h1Text.contains(id);
		boolean containsWriting = h1Text.contains(orderType);

		// Sử dụng assert để kiểm tra
		Assert.assertTrue(containsOrderId,"Thẻ h1 phải chứa "+id);
		Assert.assertTrue(containsWriting,"Thẻ h1 phải chứa "+orderType);
	}
	public void verifyWPrice(By by,String value){
		WebUI.waitForElementVisible(by);
		String price = WebUI.getElementText(by);
		System.out.println(by);
		WebUI.assertEquals(price,value,"Passed");
	}
}
