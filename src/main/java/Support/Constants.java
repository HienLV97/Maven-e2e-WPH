package Support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//import static Support.Initialization.Init;

public class Constants {
	private WebDriver driver;
	private WebDriverWait wait;
	public Constants(WebDriver driver) {
		this.driver = driver;
		//driver = _driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		new WebUI(driver); //Bắt buộc
	}
	public static String emailAccount = "client-12344-fake@kamora.vn";
	public static String passAccount = "iamafriend";
	public static String YetiCMS = "https://yeti-cms.dev/api";
	public static String YetiCMSToken = "2b0c615afb1b72cf093a5fa6d48c7ef1";

	public static String DashboardQL = "https://acadashboard.dev/graphql/";
//	public static String DashboardToken =

	public static String email = "support@kamora.vn";

	public static String CoreAPI = "http://199.231.164.147:4422";
	public static String CoreAPIToken = "ee1bc0f04cae5c786e87db53fdf80432";
	public static String cardInfo = "4242424242424242";
	public static String cardExpiry = "1266";
	public static String cardCVC = "333";
	public static String fakeName = "John Test";

}