package CMS.SignIn.pages;

import AcaWriting.Keywords.WebUI;
import Initialization.Init;
import AcaWriting.Support.CMS.Routers;
import helpers.drivers.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends Init {
//	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath ="//input[contains(@placeholder, 'Email')]")
	WebElement inputEmail;
	@FindBy(xpath ="//input[contains(@placeholder, 'Password')]")
	WebElement inputPassword;
	@FindBy(xpath ="(//button[normalize-space()='Let me in!'])[1]")
	WebElement signInBTN;

	public SignInPage(WebDriver driver) {
//		this.driver = driver;
		wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	public void clickLoginButton() {
		WebUI.clickWEBElement(signInBTN);
	}

	public void setEmail(String email) {
		inputEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		inputPassword.sendKeys(password);
	}

	public void Login(String email, String password) {
//		DriverManager.getDriver().get(Routers.SIGN_IN);
		sleep(5);
		setEmail(email);
		setPassword(password);
		clickLoginButton();
	}

	public void signInWithToken(String tokenName, String tokenValue) {
		Cookie tokenCookie = new Cookie.Builder(tokenName, tokenValue)
				.domain(Routers.DomainDEV)
				.path("/")
				.isHttpOnly(true)
				.isSecure(true)
				.build();

		// Thêm cookie vào trình duyệt
		DriverManager.getDriver().manage().addCookie(tokenCookie);

		// Làm mới trang để cookie có hiệu lực
		DriverManager.getDriver().navigate().refresh();
	}
}
