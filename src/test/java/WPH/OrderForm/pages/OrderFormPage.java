package WPH.OrderForm.pages;


import API.GetAPI.Dashboard.OrderForm.OrderForm;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;
import java.util.List;
import java.time.Duration;

public class OrderFormPage {
	private WebDriver driver;
	private WebDriverWait wait;
	JavascriptExecutor js;
	private By NextBTN = By.xpath("//*[contains(text(),'Next')]");
	private By DocumentDRL = By.xpath("//input[@id='input-document']");
	private By DisciplineDRL = By.xpath("//input[@id='input-discipline']");
	private String Acalevel = "(//button[contains(@class,'button-tag')])";
	private By TitleTXT = By.xpath("//input[@placeholder='Write your paper title']");
	private By InstructionTXT = By.tagName("textarea");
	private By SourceIncBTN = By.xpath("(//button[@id='increase-page'])[1]");
	private By SourceDecBTN = By.xpath("(//button[@id='decrease-page'])[1]");
	private By SourceNumber = By.xpath("(//div[@class='c-input-number1__input c-input-number1 noselect custom-input'])[1]");
	private List<String> academicLevel = OrderForm.handleData(OrderForm.academicLevel);
	private By WriterCB = By.xpath("//label[@for=\"Writer's choice\"]");
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

	public OrderFormPage(WebDriver driver) {
		this.driver = driver;
		//driver = _driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void clickNextButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(NextBTN));
		driver.findElement(NextBTN).click();
	}

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

	public void setTitleTXT(String value){
		wait.until(ExpectedConditions.visibilityOfElementLocated(TitleTXT));
		driver.findElement(TitleTXT).sendKeys(value);
	}
	public void setInstructionTXT(String value){
		wait.until(ExpectedConditions.visibilityOfElementLocated(InstructionTXT));
		driver.findElement(InstructionTXT).sendKeys(value);
	}
	public void clickSourceIncBTN(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(SourceIncBTN));
		driver.findElement(SourceIncBTN).click();
	}
	public void clickSourceDecBTN(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(SourceDecBTN));
		driver.findElement(SourceDecBTN).click();
	}
	public void clickWriterCB(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(WriterCB));
		driver.findElement(WriterCB).click();
	}
	public void verifyCB( ){
		// Kiểm tra ::after có giá trị hay không

		WebElement label = driver.findElement(By.cssSelector("input[type='checkbox']:checked + label"));
		String content = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');", label);

		// Assert rằng ::after có nội dung
		Assert.assertFalse(content.isEmpty(), "Pseudo-element ::after không có nội dung");

	}

}
