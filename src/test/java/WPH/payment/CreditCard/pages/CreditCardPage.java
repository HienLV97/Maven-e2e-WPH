package WPH.payment.CreditCard.pages;

import Support.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import API.GetAPI.Dashboard.OrderForm.OrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;
import java.time.Duration;

public class CreditCardPage {

	private WebDriver driver;
	private WebDriverWait wait;
	private By emailTB = By.xpath("//input[@id='email']");
	private By cardInfoTB = By.xpath("//input[@id='cardNumber']");
	private By cardExpiryTB = By.xpath("//input[@id='cardExpiry']");
	private By cardCvcTB = By.xpath("//input[@id='cardCvc']");
	private By billingNameTB = By.xpath("//input[@id='billingName']");
	private By checkOutBTN = By.xpath("//div[@class='SubmitButton-CheckmarkIcon']");

	public CreditCardPage(WebDriver driver) {
		this.driver = driver;
		//driver = _driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	private void setEmailTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailTB));
		driver.findElement(emailTB).sendKeys(Constants.emailAccount);
	}

	private void setCardInfoTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardInfoTB));
		driver.findElement(cardInfoTB).sendKeys(Constants.cardInfo);
	}

	private void setCardExpiryTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardExpiryTB));
		driver.findElement(cardExpiryTB).sendKeys(Constants.cardExpiry);
	}

	private void setCardCvcTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardCvcTB));
		driver.findElement(cardCvcTB).sendKeys(Constants.cardCVC);
	}

	private void setBillingNameTB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(billingNameTB));
		driver.findElement(billingNameTB).sendKeys(Constants.fakeName);
	}

	private void clickCheckOutBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(checkOutBTN));
		driver.findElement(checkOutBTN).click();
	}

	public void getCheckout() {
		setEmailTB();
		setCardInfoTB();
		setCardExpiryTB();
		setCardCvcTB();
		setBillingNameTB();
		clickCheckOutBTN();
	}

	public void getID() {
		String linkText = driver.getCurrentUrl();
		int extractedValue = Integer.parseInt(linkText);
		System.out.println("Giá trị trích xuất: " + extractedValue);
	}
}
