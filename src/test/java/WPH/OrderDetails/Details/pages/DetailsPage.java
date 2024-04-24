package WPH.OrderDetails.Details.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailsPage {
	private WebDriver driver;
	private WebDriverWait wait;
	public DetailsPage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
}
