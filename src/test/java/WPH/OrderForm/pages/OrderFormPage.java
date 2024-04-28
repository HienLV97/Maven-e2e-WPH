package WPH.OrderForm.pages;


import API.GetAPI.Dashboard.OrderForm.OrderForm;
import Keywords.WebUI;
import Support.Initialization.Init;
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

public class OrderFormPage extends Init {
	private WebDriver driver;
	private WebDriverWait wait;

	public OrderFormPage(WebDriver driver) {
		this.driver = driver;
		//driver = _driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		new WebUI(driver); //Bắt buộc
	}

	//	JavascriptExecutor js;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	private By NextBTN = By.xpath("//*[contains(text(),'Next')]");
	//step1
	private By DocumentDRL = By.xpath("//input[@id='input-document']");
	private By DisciplineDRL = By.xpath("//input[@id='input-discipline']");
	private String Acalevel = "(//button[contains(@class,'button-tag')])";

	//step2
	private By TitleTXT = By.xpath("//input[@placeholder='Write your paper title']");
	private By InstructionTXT = By.tagName("textarea");
	//step3
	private By SourceIncBTN = By.xpath("(//button[@id='increase-page'])[1]");
	private By SourceDecBTN = By.xpath("(//button[@id='decrease-page'])[1]");
	private By SourceNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[1]");

	private By WriterCB = By.xpath("//label[@for=\"Writer's choice\"]");
	private String DeadLine = "(//button[contains(@class,'button-tag')])";

	private By PageIncBTN = By.xpath("(//button[@id='increase-page'])[2]");
	private By PageDecBTN = By.xpath("(//button[@id='decrease-page'])[2]");
	private By PageNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[2]");

	private By SingleBTN = By.xpath("//button[contains(text(),'Single')]");
	private By DoubleBTN = By.xpath("//button[contains(text(),'Double')]");

	private By SlideIncBTN = By.xpath("(//button[@id='increase-page'])[3]");
	private By SlideDecBTN = By.xpath("(//button[@id='decrease-page'])[3]");
	private By SlideNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[3]");

	private String WriterLevel = "//p[contains(text(),'ENL (+35%)')]";

	private List<String> academicLevel = OrderForm.handleData(OrderForm.academicLevel);
	private List<String> deadLineLevel = OrderForm.handleData(OrderForm.urgencyLevel);
	private List<String> writerLevel = OrderForm.handleData(OrderForm.writerLevel);

	//step4

	private By AbstractCB = By.xpath("//label[@for='valueCheckbox-abstract-page']");
	private By PrevWriterCB = By.xpath("//label[@for='valueCheckbox-prev-writer']");

	//step5

	private By CreditBTN = By.xpath("//p[normalize-space()='Credit Card']");
	private By PayPalBTN = By.xpath("//p[normalize-space()='PayPal']");
	private By CheckOutBTN = By.xpath("//button[normalize-space()='Secure Checkout']");
	private By ViewOrderBTN = By.xpath("(//button[normalize-space()='View Details'])[1]");

	//Stripe menthod


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

	public void clickNextButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(NextBTN));

		WebElement element = driver.findElement(NextBTN);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		WebUI.scrollToElement(NextBTN);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		sleep(2);
		element.click();
	}

	//step1
	public void clickDocumentDRL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DocumentDRL));
		driver.findElement(DocumentDRL).click();
	}

	public void SetDocumentDRL(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DocumentDRL));
		clickDocumentDRL();
		driver.findElement(DocumentDRL).sendKeys("Admission Essay");
	}

	public void AcalevelOptBTN(Integer value) {
		By option = By.xpath(Acalevel + "//span[contains(text(),'" + academicLevel.get(value).replace("\"", "") + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		driver.findElement(option).click();
	}

	public void clickDisciplineDRL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DisciplineDRL));
		driver.findElement(DisciplineDRL).click();
	}

	public void setDisciplineDRL(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DisciplineDRL));
		clickDisciplineDRL();
		driver.findElement(DisciplineDRL).sendKeys(value);
	}

	//step2
	public void setTitleTXT(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TitleTXT));
		driver.findElement(TitleTXT).sendKeys(value);
	}

	public void setInstructionTXT(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(InstructionTXT));
		driver.findElement(InstructionTXT).sendKeys(value);
	}

	//step3
	public void clickSourceIncBTN(int value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SourceIncBTN));
		for (int i = 0; i < value; i++) {
			driver.findElement(SourceIncBTN).click();
		}
	}
	public void clickSourceDecBTN(int value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SourceDecBTN));
		for (int i = 0; i < value; i++) {
			driver.findElement(SourceDecBTN).click();
		}
	}

	public void clickWriterCB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(WriterCB));
		driver.findElement(WriterCB).click();
	}

	public void verifyWriterCB() {
		// Kiểm tra ::after có giá trị hay không
		js = (JavascriptExecutor) driver;
		WebElement label = driver.findElement(By.cssSelector("input[type='checkbox']:checked + label"));
		String content = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", label);
		// Assert rằng ::after có nội dung
		Assert.assertFalse(content.isEmpty(), "Pseudo-element ::after không có nội dung");

	}

	public void clickDeadLine(int value) {
		By option = By.xpath(DeadLine + "//span[contains(text(),'" + deadLineLevel.get(value).replace("\"", "") + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		driver.findElement(option).click();
	}

	public void clickPageInc() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageIncBTN));
		driver.findElement(PageIncBTN).click();
	}

	public void clickPageDec() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageDecBTN));
		driver.findElement(PageDecBTN).click();
	}

	public void clickSlideInc() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SlideIncBTN));
		driver.findElement(SlideIncBTN).click();
	}

	public void clickSlideDec() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SlideDecBTN));
		driver.findElement(SlideDecBTN).click();
	}

	public void clickSingleBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SingleBTN));
		driver.findElement(SingleBTN).click();
	}

	public void clickDoubleBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DoubleBTN));
		driver.findElement(DoubleBTN).click();
	}

	public void clickWriterLevelBTN(int value) {
		By option = By.xpath("*//p[contains(text(),'" + writerLevel.get(value).replace("\"", "") + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		driver.findElement(option).click();
	}

	public void clickAbstractCB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SingleBTN));
		driver.findElement(SingleBTN).click();
	}

	public void clickAbstractBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(AbstractCB));
		driver.findElement(AbstractCB).click();
	}

	public void verifyAbstractCB() {
		clickAbstractBTN();
		js = (JavascriptExecutor) driver;
		WebElement label = driver.findElement(By.cssSelector("input[id='valueCheckbox-abstract-page']:checked + label"));
		String content = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", label);
		// Assert rằng ::after có nội dung
		Assert.assertFalse(content.isEmpty(), "Pseudo-element ::after không có nội dung");

	}

	public void clickPrevWriterBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PrevWriterCB));
		driver.findElement(PrevWriterCB).click();
	}

	public void verifyPrevWriterCB() {
		clickPrevWriterBTN();
		js = (JavascriptExecutor) driver;
		WebElement label = driver.findElement(By.cssSelector("input[id='valueCheckbox-prev-writer']:checked + label"));
		String content = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", label);
		// Assert rằng ::after có nội dung
		Assert.assertFalse(content.isEmpty(), "Pseudo-element ::after không có nội dung");

	}

	public void clickCreditBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CreditBTN));
		driver.findElement(CreditBTN).click();
	}

	public void clickCheckOutBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckOutBTN));
		driver.findElement(CheckOutBTN).click();
	}

	public void clickViewOrderBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ViewOrderBTN));
		driver.findElement(ViewOrderBTN).click();
	}

	public String getID() {
		String currentUrl = driver.getCurrentUrl();
		String[] parts = currentUrl.split("/");
		String orderId = parts[4];
		System.out.println(currentUrl);
		System.out.println("Order ID: " + orderId);
		return orderId;
	}

	public void inputStep1() {
		SetDocumentDRL("Admission Essay");
		sleep(2);
		AcalevelOptBTN(2);
		setDisciplineDRL("Accounting");
		sleep(2);
		AcalevelOptBTN(2);
	}

	public void inputStep2() {
		setTitleTXT("test");
		setInstructionTXT("test");
	}

	public void inputStep3() {

	}
}
