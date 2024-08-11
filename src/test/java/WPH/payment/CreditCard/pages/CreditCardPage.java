package WPH.payment.CreditCard.pages;

import Support.Constants;
import WPH.OrderDetails.Details.pages.DetailsPage;
import Writer.OrderDetail.pages.OrderDetailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreditCardPage {

	private WebDriver driver;
	private WebDriverWait wait;
//	DetailsPage detailsPage;
	private By emailTB = By.xpath("//input[@id='email']");
	private By cardInfoTB = By.xpath("//input[@id='cardNumber']");
	private By cardExpiryTB = By.xpath("//input[@id='cardExpiry']");
	private By cardCvcTB = By.xpath("//input[@id='cardCvc']");
	private By billingNameTB = By.xpath("//input[@id='billingName']");
	private By checkOutBTN = By.xpath("//div[@class='SubmitButton-CheckmarkIcon']");

	public CreditCardPage(WebDriver driver) {
		this.driver = driver;
		//driver = _driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}

	private void setEmailTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailTB));
		driver.findElement(emailTB).sendKeys(Constants.EMAIL);
	}

	private void setCardInfoTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardInfoTB));
		driver.findElement(cardInfoTB).sendKeys(Constants.CARD_INFO);
	}

	private void setCardExpiryTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardExpiryTB));
		driver.findElement(cardExpiryTB).sendKeys(Constants.CARD_EXPIRY);
	}

	private void setCardCvcTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardCvcTB));
		driver.findElement(cardCvcTB).sendKeys(Constants.CARD_CVC);
	}

	private void setBillingNameTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(billingNameTB));
		driver.findElement(billingNameTB).sendKeys(Constants.FAKE_NAME);
	}

	private void clickCheckOutBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutBTN));
		driver.findElement(checkOutBTN).click();
	}

	public DetailsPage getCheckout() {
		setEmailTB();
		setCardInfoTB();
		setCardExpiryTB();
		setCardCvcTB();
		setBillingNameTB();
		clickCheckOutBTN();
		return new DetailsPage(driver);
	}

	public void getID() {
		String linkText = driver.getCurrentUrl();
		int extractedValue = Integer.parseInt(linkText);
		System.out.println("Giá trị trích xuất: " + extractedValue);
	}
}
