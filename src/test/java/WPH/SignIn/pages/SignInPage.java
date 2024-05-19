package WPH.SignIn.pages;

import Support.Constants;
import Support.Initialization.Init;
import Support.Routers;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class SignInPage extends Init {
	private WebDriver driver;
	private WebDriverWait wait;
	private By inputEmail = By.xpath("//input[contains(@placeholder, 'Email')]");
	private By inputPassword = By.xpath("//input[contains(@placeholder, 'Password')]");
	private By signInBTN = By.xpath("(//button[normalize-space()='Continue with email'])[1]");

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

	public void Login(String email, String password){
		driver.get(Routers.SIGN_IN);
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
