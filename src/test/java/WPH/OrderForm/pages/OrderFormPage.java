package WPH.OrderForm.pages;


import API.GetAPI.CoreAPI.OrderForm.OrderForm;
import AcaWriting.Keywords.WebUI;
import Initialization.Init;
import AcaWriting.Support.WPH.Routers;
import helpers.drivers.DriverManager;
import WPH.OrderDetails.Details.pages.DetailsPage;
import WPH.payment.CreditCard.pages.CreditCardPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;
import java.time.Duration;
import java.util.Objects;

public class OrderFormPage extends Init {
//	private WebDriver driver;
	private WebDriverWait wait;
	public String orderType;
	public String document;
	public int acalevelNumb;
	public String acalevelTXT;
	public String discipline;
	public String paperFormat;
	DetailsPage detailsPage;

	public OrderFormPage(WebDriver driver) {
		wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	public void Step1Data(String orderType, String document, int acalevelNumb, String discipline, String paperFormat, List<String> academicLevels) {
		this.orderType = orderType;
		this.document = document;
		this.acalevelNumb = acalevelNumb;
		this.acalevelTXT = academicLevels.get(acalevelNumb).replace("\"", "");
		this.discipline = discipline;
		this.paperFormat = paperFormat;
	}

	public String title;
	public String instruction;

	public void Step2Data(String title, String instruction) {
		this.title = title;
		this.instruction = instruction;
	}

	public int urgentNumb;
	public String urgentTXT;
	public int pages;
	public int source;
	public int slides;
	public int writerLvlNumb;
	public String writerLvlTxt;
	public String spacing;

	public void Step3Data(int deadlineNumb, int pages, int source, int slides, String spacing, List<String> deadLineLevels) {
		this.urgentNumb = deadlineNumb;
		this.urgentTXT = deadLineLevels.get(deadlineNumb).replace("\"", "");
		this.pages = pages;
		this.source = source;
		this.slides = slides;
		this.spacing = spacing;

	}

	public boolean isAbsPrice;
	public boolean isPreWriter;

	public void Step4Data(int writerLvlNumb, boolean isAbsPrice, boolean isPreWriter, List<String> writerLevels) {
		this.writerLvlNumb = writerLvlNumb;
		this.writerLvlTxt = writerLevels.get(writerLvlNumb).replace("\"", "");

		this.isAbsPrice = isAbsPrice;
		this.isPreWriter = isPreWriter;
	}

	public String disCode;
	public int payment;

	public void Step5Data(String disCode, int payment) {
		this.disCode = disCode;
		this.payment = payment;
	}


	//	JavascriptExecutor js;
	JavascriptExecutor js = (JavascriptExecutor) driver;

	private By NextBTN = By.xpath("//*[contains(text(),'Next')]");
	//step1
	private By DocumentDRL = By.xpath("//input[@id='input-document']");
	private By DisciplineDRL = By.xpath("//input[@id='input-discipline']");
	private String Acalevel = "(//button[contains(@class,'button-tag')])";
	@FindBy(xpath = "//span[normalize-space()='writing']")
	WebElement writeBTN;
	@FindBy(xpath = "//span[normalize-space()='editing']")
	WebElement editBTN;


	//step2
	private By TitleTXT = By.xpath("//input[@placeholder='Write your paper title']");
	private By InstructionTXT = By.tagName("textarea");
	//step3
	public By SourceIncBTN = By.xpath("(//button[@id='increase-page'])[1]");
	private By SourceDecBTN = By.xpath("(//button[@id='decrease-page'])[1]");
	private By SourceNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[1]");

	private By WriterCB = By.xpath("//label[@for=\"Writer's choice\"]");
	private String DeadLine = "(//button[contains(@class,'button-tag')])";

	public By PageIncBTN = By.xpath("(//button[@id='increase-page'])[2]");
	public By PageDecBTN = By.xpath("(//button[@id='decrease-page'])[2]");
	public By PageNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[2]");

	public By SingleBTN = By.xpath("//button[contains(text(),'Single')]");
	public By DoubleBTN = By.xpath("//button[contains(text(),'Double')]");

	public By SlideIncBTN = By.xpath("(//button[@id='increase-page'])[3]");
	public By SlideDecBTN = By.xpath("(//button[@id='decrease-page'])[3]");
	public By SlideNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[3]");

	private String WriterLevel = "//p[contains(text(),'ENL (+35%)')]";

	public List<String> academicLevel = OrderForm.handleData(OrderForm.academicLevel);
	public List<String> deadLineLevel = OrderForm.handleData(OrderForm.urgencyLevel);
	public List<String> writerLevel = OrderForm.handleData(OrderForm.writerLevel);
//	public List<String> writerLevel = OrderForm.handleData(OrderForm.writerLevel);

	//step4

	private By AbstractCB = By.xpath("//label[@for='valueCheckbox-abstract-page']");
	private By PrevWriterCB = By.xpath("//label[@for='valueCheckbox-prev-writer']");
	public By PreWriterTB = By.xpath("//input[@id='input-previous-writer']");
	private By firstValue = By.xpath("(//span[contains(@class,'item-span')])[1]");
	//step5

	private By CreditBTN = By.xpath("//p[normalize-space()='Credit Card']");
	private By PayPalBTN = By.xpath("//p[normalize-space()='PayPal']");
	private By CheckOutBTN = By.xpath("//button[normalize-space()='Secure Checkout']");
	private By ViewOrderBTN = By.xpath("(//button[normalize-space()='View Details'])[1]");
	private By DiscountTB = By.xpath("(//input[@placeholder='Enter your discount code'])[2]");
	public By ApllyBTN = By.xpath("(//button[contains(text(),'Apply')])[2]");
	private By TotalPrice = By.xpath("(//p[@class='price-total'][normalize-space()='Total'])[2]//following-sibling::p");
	private By ExtraPrice = By.xpath("(//p[@class='price-title text-graydish'][normalize-space()='Extras'])[2]//following-sibling::p");
	private By DiscountPrice = By.xpath("(//p[@class='price-value text-reddish'])[2]");
	private By YouPayPrice = By.xpath("(//p[@class='preview__title preview__value'])[2]");
	//Stripe menthod


	public void clickNextButton() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		wait.until(ExpectedConditions.visibilityOfElementLocated(NextBTN));
		WebUI.scrollToElement(NextBTN);
		sleep(2);
		WebUI.clickElement(NextBTN);
	}

	//step1
	public void clickDocumentDRL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DocumentDRL));
		DriverManager.getDriver().findElement(DocumentDRL).click();
	}

	public void setDocumentDRL(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DocumentDRL));
		clickDocumentDRL();
		DriverManager.getDriver().findElement(DocumentDRL).sendKeys(value);
	}

	public void clickAcaLevel(Integer value) {
		By option = By.xpath(Acalevel + "//span[contains(text(),'" + academicLevel.get(value).replace("\"", "") + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		WebUI.scrollToElement(option);
		DriverManager.getDriver().findElement(option).click();
	}

	public void clickDisciplineDRL() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DisciplineDRL));
		DriverManager.getDriver().findElement(DisciplineDRL).click();
	}

	public void setDisciplineDRL(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DisciplineDRL));
		WebUI.scrollToElement(DisciplineDRL);
		clickDisciplineDRL();
		DriverManager.getDriver().findElement(DisciplineDRL).sendKeys(value);
	}

	public void clickPaperFormat(String value) {
		By option = By.xpath(Acalevel + "//span[contains(text(),'" + value + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		WebUI.scrollToElement(option);
		WebUI.clickElement(option);
//	DriverManager.getDriver().findElement(option).click();
	}

	public void clickOrderType(WebElement webElement) {
		WebUI.clickWEBElement(webElement);
	}

	//step2
	public void setTitleTXT(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TitleTXT));
		DriverManager.getDriver().findElement(TitleTXT).sendKeys(value);
	}

	public void setInstructionTXT(String value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(InstructionTXT));
		DriverManager.getDriver().findElement(InstructionTXT).sendKeys(value);
	}

	//step3
	public void clickSourceIncBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SourceIncBTN));
		DriverManager.getDriver().findElement(SourceIncBTN).click();

	}

	public void clickSourceDecBTN(int value) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SourceDecBTN));
		DriverManager.getDriver().findElement(SourceDecBTN).click();

	}

	public void clickWriterCB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(WriterCB));
		DriverManager.getDriver().findElement(WriterCB).click();
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
		DriverManager.getDriver().findElement(option).click();
	}

	public void clickPageInc() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageIncBTN));
		DriverManager.getDriver().findElement(PageIncBTN).click();
	}

	public void clickPageDec() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PageDecBTN));
		DriverManager.getDriver().findElement(PageDecBTN).click();
	}

	public void clickSlideInc() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SlideIncBTN));
		DriverManager.getDriver().findElement(SlideIncBTN).click();
	}

	public void clickSlideDec() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SlideDecBTN));
		DriverManager.getDriver().findElement(SlideDecBTN).click();
	}

	public void clickSingleBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SingleBTN));
		DriverManager.getDriver().findElement(SingleBTN).click();
	}

	public void clickDoubleBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DoubleBTN));
		DriverManager.getDriver().findElement(DoubleBTN).click();
	}

	//step4
	public void clickWriterLevelBTN(int value) {
		By option = By.xpath("*//p[contains(text(),'" + writerLevel.get(value).replace("\"", "") + "')]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(option));
		WebUI.scrollToElement(option);
		DriverManager.getDriver().findElement(option).click();
	}

	public void clickAbstractCB() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SingleBTN));
		DriverManager.getDriver().findElement(SingleBTN).click();
	}

	public void clickAbstractBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(AbstractCB));
		DriverManager.getDriver().findElement(AbstractCB).click();
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
		DriverManager.getDriver().findElement(PrevWriterCB).click();
	}

	public void setPrevWriterDRL() {
		clickPrevWriterBTN();
		WebUI.waitForElementClickable(PreWriterTB);
		WebUI.scrollToElement(PreWriterTB);
		WebUI.clickElement(PreWriterTB);
		sleep(3);
		WebUI.scrollToElement(NextBTN);
		WebUI.clickElement(PreWriterTB);
		WebUI.waitForElementClickable(firstValue);
		WebUI.clickElement(firstValue);
	}

	public void setDiscountTB(String value) {
		WebUI.waitForElementVisible(DiscountTB);
		WebUI.setText(DiscountTB, value);
	}

	public void clickApply() {
		WebUI.waitForElementVisible(ApllyBTN);
		WebUI.clickElement(ApllyBTN);
	}

	//step5
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
		DriverManager.getDriver().findElement(CreditBTN).click();
	}

	public void clickPayPalBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PayPalBTN));
		DriverManager.getDriver().findElement(PayPalBTN).click();
	}

	public void clickCheckOutBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(CheckOutBTN));
		DriverManager.getDriver().findElement(CheckOutBTN).click();
	}

	public void checkOutByStrip() {
		new CreditCardPage(driver);
	}

	public void clickViewOrderBTN() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ViewOrderBTN));
		DriverManager.getDriver().findElement(ViewOrderBTN).click();
//		OrderFormPage orderFormPage = new OrderFormPage(driver);
//		detailsPage.setValuesFromOrderForm();
	}

	public void verifyTotal(String expectedPrice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(TotalPrice));
//		String expectedPrice = "$144.95";
		String totalPriceText = WebUI.getElementText(TotalPrice);
		Assert.assertTrue(totalPriceText.contains(expectedPrice), "TotalPrice is wrong: " + expectedPrice);
	}

	public void verifyExtra(String expectedPrice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(ExtraPrice));
//		String expectedPrice = "$79.98";
		String totalPriceText = WebUI.getElementText(ExtraPrice);
		Assert.assertTrue(totalPriceText.contains(expectedPrice), "ExtraPrice is wrong: " + expectedPrice);
	}

	public void verifyDiscount(String expectedPrice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(DiscountPrice));
//		String expectedPrice = "$8.10";
		String totalPriceText = WebUI.getElementText(DiscountPrice);
		Assert.assertTrue(totalPriceText.contains(expectedPrice), "ExtraPrice is wrong: " + expectedPrice);
	}

	public void verifyYouPay(String expectedPrice) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(YouPayPrice));
//		String expectedPrice = "$203.19";
		String totalPriceText = WebUI.getElementText(YouPayPrice);
		Assert.assertTrue(totalPriceText.contains(expectedPrice), "YouPayPrice is wrong: " + expectedPrice);
	}

	public String getID() {
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		sleep(3);
		String[] parts = currentUrl.split("/");
		return parts[4];
	}

	public void setStep1() {
		if (Objects.equals(this.orderType.toLowerCase(), "writing")) {
			clickOrderType(writeBTN);
		} else if (Objects.equals(this.orderType.toLowerCase(), "editing")) {
			clickOrderType(editBTN);
		}
		clickAcaLevel(this.acalevelNumb);
		setDocumentDRL(this.document);
		sleep(2);
		clickAcaLevel(this.acalevelNumb);
//		setDisciplineDRL("Accounting");
		setDisciplineDRL(this.discipline);
		sleep(2);
		clickAcaLevel(this.acalevelNumb);
		clickPaperFormat(this.paperFormat);
		clickNextButton();
	}

	public void setStep2() {
		setTitleTXT(this.title);
		setInstructionTXT(this.instruction);
		clickNextButton();
	}

	public void setStep3() {
		WebUI.clickMultiElement(SourceIncBTN, this.source);
		clickDeadLine(this.urgentNumb);
		WebUI.clickMultiElement(PageIncBTN, (this.pages - 2));
		if (this.spacing.equals("Single")) {
			clickSingleBTN();
		}
		if (this.spacing.equals("Double")) {
			clickDoubleBTN();
		}
		if (this.slides >= 2) {
			clickSlideInc();
		}
		clickNextButton();
	}

	public void setStep4() {
		clickWriterLevelBTN(this.writerLvlNumb);
		if (this.isAbsPrice) {
			clickAbstractBTN();
		}
		if (this.isPreWriter) {
			setPrevWriterDRL();
		}
		clickNextButton();
	}

	public void setStep5() {
		if (Objects.nonNull(this.disCode)) {
			setDiscountTB(this.disCode);
			sleep(5);
			clickApply();
		}
		if (this.payment == 1) {
			clickCreditBTN();

			clickCheckOutBTN();
		} else if (this.payment == 2) {
			clickPayPalBTN();
			clickCheckOutBTN();
		}
	}

	public void createOrder() {
		DriverManager.getDriver().get(Routers.ORDER);
		WebUI.waitForPageLoaded();
		setStep1();
		setStep2();
		setStep3();
		setStep4();
		setStep5();
//		new Calculator();
//		Calculator();
	}
}
