package WPH.SignIn.pages;

import AcaWriting.Keywords.WebUI;
import AcaWriting.Support.Initialization.Init;
import AcaWriting.Support.WPH.Routers;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends Init {
	private WebDriver driver;
	private WebDriverWait wait;
	//	private By inputEmail = By.xpath();
//	private By inputPassword = By.xpath("//input[contains(@placeholder, 'Password')]");
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement inputPassword;
	@FindBy(xpath = "(//button[normalize-space()='Continue With Email'])")
	WebElement signInBTN;

	@FindBy(xpath = "//input[@placeholder='Email']")
	WebElement inputEmail;

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
		driver.get(Routers.SIGN_IN);
		System.out.println("Vào đây 1");
		sleep(8);
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
		driver.manage().addCookie(tokenCookie);

		// Làm mới trang để cookie có hiệu lực
		driver.navigate().refresh();
	}
}
