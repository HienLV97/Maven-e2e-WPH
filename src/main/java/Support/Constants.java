package Support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static Support.Initialization.Init.driver;
public class Constants {
//     WebDriver driver = null;

//    public Constants(WebDriver driver) {
//        this.driver = driver;
//    }

	public String emailAccount = "kamoratest1@g.c";
	public String passAccount = "iamafriend";
	public String YetiCMS = "https://yeti-cms.dev/api";
	public String YetiCMSToken = "2b0c615afb1b72cf093a5fa6d48c7ef1";
	public WebElement getPlaceholder(String value) {
		return driver.findElement(By.xpath("//input[contains(@placeholder, '" + value + "')]"));
	}

	public WebElement getBtn(String value) {
		return driver.findElement(By.xpath("//*/button[contains(text(), '" + value + "')]"));
//                                                          button[contains(text(),'Login')]
	}
	public WebElement getText(String value) {
//        return driver.findElement(By.xpath("//*[contains(text(), '" + value + "')]"));
		return driver.findElement(By.xpath("//*[contains(text(), '"+value+"')]"));
	}

}