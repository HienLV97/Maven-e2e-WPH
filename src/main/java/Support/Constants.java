package Support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Support.Initialization.Init.driver;

public class Constants {

	public static String emailAccount = "kamoratest1@g.c";
	public static String passAccount = "iamafriend";
	public String YetiCMS = "https://yeti-cms.dev/api";
	public String YetiCMSToken = "2b0c615afb1b72cf093a5fa6d48c7ef1";

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