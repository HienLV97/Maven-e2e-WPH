package DashBoard.SignIn.pages;

import Initialization.Init;
import AcaWriting.Support.DashBoard.Routers;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage extends Init {
	private WebDriver driver;
	private WebDriverWait wait;
	private By inputEmail = By.xpath("//input[@id='email']");
	private By inputPassword =  By.xpath("//input[@id='password']");
	private By signInBTN = By.xpath("(//button[normalize-space()='Sign in'])[1]");

	public SignInPage(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	public void clickLoginButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(signInBTN));
		driver.findElement(signInBTN).click();
	}

	public void setEmail(String email) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
		driver.findElement(inputEmail).sendKeys(email);
	}

	public void setPassword(String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
		driver.findElement(inputPassword).sendKeys(password);
	}

	public void Login(String email, String password) {
		driver.get(Routers.SIGN_IN);
		sleep(10);
		setEmail(email);
		setPassword(password);
		clickLoginButton();
	}
	public void signInWithToken(String tokenName, String tokenValue) {
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
