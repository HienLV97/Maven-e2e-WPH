package Support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Support.Initialization.Init.driver;

public class Constants {

	public static String emailAccount = "client-12344-fake@kamora.vn";
	public static String passAccount = "iamafriend";
	public static String YetiCMS = "https://yeti-cms.dev/api";
	public static String YetiCMSToken = "2b0c615afb1b72cf093a5fa6d48c7ef1";
	public static String CoreAPI = "http://199.231.164.147:4422";
	public static String CoreAPIToken = "ee1bc0f04cae5c786e87db53fdf80432";
	public static String cardInfo = "4242424242424242";
	public static String cardExpiry = "1266";
	public static String cardCVC = "333";
	public static String fakeName = "John Test";
	public static WebElement getPlaceholder(String value) {
		return driver.findElement(By.xpath("//input[contains(@placeholder, '" + value + "')]"));
	}

	public static WebElement getBtn(String value) {
		return driver.findElement(By.xpath("//*/button[contains(text(), '" + value + "')]"));
	}

	public WebElement getText(String value) {
		return driver.findElement(By.xpath("//*[contains(text(), '" + value + "')]"));
	}

}