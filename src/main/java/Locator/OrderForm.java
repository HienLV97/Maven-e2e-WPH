package Locator;

import Support.Initialization.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Duration;

public class OrderForm  {
	private WebDriver driver;
	private WebDriverWait wait;
	private By NextBTN = By.xpath("//*[contains(text(),'Next')]");
	private By DocumentDRL = By.xpath("//input[@id='input-document']");
	private String Acalevel = "(//button[contains(@class,'button-tag')])";

	private String[] academicLevel = {
			"High School",
			"Undergrad. (yrs 1-2)",
			"Undergrad. (yrs 3-4)",
			"Master's",
			"Doctoral",
			"IB Level"
	};
	public void waitForPageLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		//Wait for Javascript to load
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return js.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		//Check JS is Ready
		boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

		//Wait Javascript until it is Ready!
		if (!jsReady) {
			System.out.println("Javascript is NOT Ready.");
			//Wait for Javascript to load
			try {
				wait.until(jsLoad);
			} catch (Throwable error) {
				error.printStackTrace();
				Assert.fail("FAILED. Timeout waiting for page load.");
			}
		}
	}
	public OrderForm(WebDriver driver) {
		this.driver = driver;
		//driver = _driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void clickLoginButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(NextBTN));
		driver.findElement(NextBTN).click();
	}
	public void DocumentDRL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DocumentDRL));
		driver.findElement(DocumentDRL).sendKeys("Admission Essay");
	}
	public void AcalevelOptBTN(Integer value){
		By option =  By.xpath(Acalevel+"//span[contains(text(),'"+academicLevel[value]+"')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		driver.findElement(option).click();
	}
}
