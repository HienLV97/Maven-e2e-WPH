package Writer.SignIn.pages;

import AcaWriting.Keywords.WebUI;
import Initialization.Init;
import AcaWriting.Support.Writer.Routers;
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
//	private By signInBTN = By.xpath("(//button[normalize-space()='Continue with email'])[1]");

	@FindBy(xpath ="//input[@id='email']")
	WebElement inputEmail;
	@FindBy(xpath ="//input[@id='password']")
	WebElement inputPassword;
	@FindBy(xpath ="//button[normalize-space()='Login']")
	WebElement signInBTN;

	public SignInPage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	public void clickLoginButton() {
		WebUI.waitForWebElementVisible(signInBTN);
		signInBTN.click();
	}

	public void setEmail(String email) {
		WebUI.waitForWebElementVisible(inputEmail);
		inputEmail.sendKeys(email);
	}

	public void setPassword(String password) {
		WebUI.waitForWebElementVisible(inputPassword);
		inputPassword.sendKeys(password);
	}

	public void login(String email, String password){
		driver.get(Routers.SIGN_IN);
		setEmail(email);
		setPassword(password);
		clickLoginButton();
	}
	public void signInWithToken(String tokenName, String tokenValue)  {

//		String token = "a6b71b98c958b4505646f7ccedb25261";  // replace with your actual token
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
