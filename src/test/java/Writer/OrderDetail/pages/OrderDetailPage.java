package Writer.OrderDetail.pages;

import AcaWriting.Keywords.WebUI;
import AcaWriting.Support.Initialization.Init;
import AcaWriting.Support.Writer.Routers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class OrderDetailPage extends Init {
	private WebDriver driver;
	private WebDriverWait wait;
	double pagePrice;
	double writerPrice;
	double preWriter;
	public static double absPrice = 22.00;
	public static int preWriterPercent = 5;
	double discount;
	double balance;
	double singlePagePrice;
	double slidePrice;
	String orderType;
	String urgent;
	String level;
	String typeDoc;
	String discipline;
	String paperFormat;
	String title;
	String instruction;
	int sources;
	int pages;
	int slides;
	String spacing;

	public OrderDetailPage(
			WebDriver driver, String type, String level, String typeDoc, String discipline,
			String paperFormat, String title, String instruction,
			String urgent, int sources, int pages, int slides, String spacing) {
		this.driver = driver;
		this.orderType = type;
		this.level = level;
		this.typeDoc = typeDoc;
		this.discipline = discipline;
		this.paperFormat = paperFormat;
		this.title = title;
		this.instruction = instruction;
		this.urgent = urgent;
		this.sources = sources;
		this.pages = pages;
		this.slides = slides;
		this.spacing = spacing;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	public static String absContent = "Write a summary of the paper to capture its main findings.";
	//Extras
	@FindBy(xpath = "//div[contains(text(),'Requested writer')]//following-sibling::*")
	WebElement preWriterSec;
	@FindBy(xpath = "//div[contains(text(),'One-Page Abstract')]//following-sibling::*")
	WebElement absPage;
	//Order ID
	@FindBy(xpath = "//*[@data-testid='uid']")
	WebElement orderID;
	@FindBy(xpath = "//div[contains(text(),'Type')]//following-sibling::*")
	WebElement orderTypeSec;
	@FindBy(xpath = "//div[contains(text(),'Topic')]//following-sibling::*")
	WebElement topicSec;
	@FindBy(xpath = "//div[contains(text(),'Status')]//following-sibling::*")
	WebElement statusSec;
	@FindBy(xpath = "//div[contains(text(),'Type')]//following-sibling::*")
	WebElement docTypeSec;
	@FindBy(xpath = "//div[contains(text(),'Discipline')]//following-sibling::*")
	WebElement disciplineSec;
	@FindBy(xpath = "//div[contains(text(),'Level')]//following-sibling::*")
	WebElement levelSec;
	@FindBy(xpath = "//div[contains(text(),'Citation Style')]//following-sibling::*")
	WebElement citationSec;
	@FindBy(xpath = "//div[contains(text(),'Spacing')]//following-sibling::*")
	WebElement spacingSec;
	@FindBy(xpath = "//div[contains(text(),'Words')]//following-sibling::*")
	WebElement wordSec;
	@FindBy(xpath = "//div[contains(text(),'Urgency')]//following-sibling::*")
	WebElement urgencySec;
	@FindBy(xpath = "//div[contains(text(),'References')]//following-sibling::*")
	WebElement sourceSec;
	@FindBy(xpath = "//div[@class='overview-order-item__dd d-flex']")
	WebElement sizeSec;
	@FindBy(xpath = "//div[contains(text(),'Instructions')]//following-sibling::*")
	WebElement instructionSec;


	public OrderDetailPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	private void verifySec(WebElement element, String value, boolean isEquals) {
		String sectionText = WebUI.getWebElementText(element);
		if (isEquals) {
			WebUI.assertEquals(sectionText, value);
		} else {
			WebUI.assertContains(sectionText, value, "Not contains");
		}
	}

	public void goToOD(String value) {
		driver.get(Routers.ORDERS_DETAILS + value);
	}

	//Extras
	public void verifyPreWriter(String value) {
		verifySec(preWriterSec, value, false);
	}

	public void verifyAbsPage(boolean isAbsPrice) {
		if (isAbsPrice) {
			verifySec(absPage, absContent, true);
		}
	}

	//Order ID
	public void verifyOrderID(String value) {
		verifySec(orderID, value, false);
	}

	public void verifyOrderType() {
		verifySec(orderTypeSec, orderType, true);
	}

	public void verifyTopic() {
		verifySec(topicSec, title, true);
	}

	public void verifyStatus(String value) {
		verifySec(statusSec, value, true);
	}

	public void verifyDoc() {
		verifySec(docTypeSec, typeDoc, true);
	}

	public void verifyDis() {
		verifySec(disciplineSec, discipline, true);
	}

	public void verifyLevel() {
		verifySec(levelSec, level, true);
	}

	public void verifyFormat() {
		verifySec(citationSec, paperFormat, true);
	}

	public void verifySpacing() {
//		spacing = spacing.toLowerCase();
		verifySec(spacingSec, spacing.toUpperCase(), false);
	}

	public void verifyWord() {
		int words;
		spacing = spacing.toLowerCase();
		if (Objects.equals(spacing, "double")) {
			words = pages * 275;
		} else if (Objects.equals(spacing, "single")) {
			words = pages * 550;
		} else {
			throw new IllegalArgumentException("Invalid spacing value: " + spacing);
		}
		verifySec(wordSec, "~ " + words, true);
	}

	public void verifyUrgency() {
		verifySec(urgencySec, urgent, true);
	}

	public void verifySource() {
		verifySec(sourceSec, String.valueOf(sources), true);
	}

	public void verifySize() {
		String pagesText;
		String slidesText;

		if (pages == 0) {
			pagesText = "~";
		} else {
			pagesText = pages + " pages";
		}
		if (slides == 0) {
			slidesText = "~";
		} else {
			slidesText = slides + " slides";
		}
		WebElement parentElement = driver.findElement(By.cssSelector("div.overview-order-item__dd.d-flex"));

// Lấy toàn bộ văn bản của phần tử cha
		String combinedText = parentElement.getText().replace("\n","");

		System.out.println("combinedText: "+combinedText);
		String sizeTxt = WebUI.getWebElementText(sizeSec).replace("\n","");
		WebUI.assertContains(combinedText, pagesText + " / " + slidesText, "error");
//		verifySec(	WebUI.getWebElementText(sizeSec), pagesText + "/" + slidesText, false);
	}

	public void verifyIns() {
		verifySec(instructionSec, String.valueOf(instruction), true);
	}


}
