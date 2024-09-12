package helpers;

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
	public static final String EMAIL = "client-12344-fake@kamora.vn";
	public static final String WRITER_EMAIL = "writer-1024-fake@kamora.vn";
	public static final String ACCOUNT_BALANCE = "test1@gmail.c";
	public static final String COMMON_PASSWORD = "iamafriend";
	public static final String CMS = "https://yeti-cms.dev/api";
	public static final String CMS_TOKEN = "2b0c615afb1b72cf093a5fa6d48c7ef1";

	public static final String BASIC_USERNAME = "kamora";
//	public static final String basicPassword = "iamafriend";

	public static final String DASHBOARD_QL = "https://acadashboard.dev/graphql/";
	public static final String PROXY_DEV_URL = "https://writersperhour.dev/api";
	public static final String WRITER = "https://writersagency.dev/gateway";
//	public static final String DashboardToken =

	public static final String COMMON_EMAIL = "support@kamora.vn";

	public static final String CORE_API = "http://199.231.164.147:4422";
	public static final String CORE_API_TOKEN = "ee1bc0f04cae5c786e87db53fdf80432";
	public static final String CARD_INFO = "4242424242424242";
	public static final String CARD_EXPIRY = "1266";
	public static final String CARD_CVC = "333";
	public static final String FAKE_NAME = "John Test";

}