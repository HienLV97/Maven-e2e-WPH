package DashBoard.OrderDetail.pages;

import API.GetAPI.DashboardGraphQL.Discounts;
import Calculator.Calculator;
import Keywords.WebUI;
import Support.Initialization.Init;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderDetailPage extends Init {
	WebDriver driver;
	WebDriverWait wait;
	String codeDis;

	public OrderDetailPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}
	//HEADER
	@FindBy(xpath = "//button[@title='Assign']")
	WebElement assignBTN;
	@FindBy(xpath = "//div[@class='form-outline']")
	WebElement assDDL;
	@FindBy(xpath = "//input[@placeholder='Search...']")
	WebElement assTB;
	@FindBy (xpath ="//button[@title='Save']")
	WebElement saveBTN;
	//DETAIL
	@FindBy(xpath = "//td[normalize-space()='Topic']//following-sibling::*")
	WebElement topicSec;
	@FindBy(xpath = "//td[normalize-space()='Discipline']//following-sibling::*")
	WebElement disciplineSec;
	@FindBy(xpath = "//td[normalize-space()='Document type']//following-sibling::*")
	WebElement documentSec;
	@FindBy(xpath = "//td[normalize-space()='Spacing']//following-sibling::*")
	WebElement spacingSec;
	@FindBy(xpath = "//td[normalize-space()='Citation style']//following-sibling::*")
	WebElement formatSec;
	@FindBy(xpath = "//td[normalize-space()='References']//following-sibling::*")
	WebElement referencesSec;
	@FindBy(xpath = "//td[@class='fw-bold text-nowrap'][normalize-space()='Pages']//following-sibling::*")
	WebElement pagesSec;
	@FindBy(xpath = "//td[normalize-space()='Words per page']//following-sibling::*")
	WebElement wordsSec;
	@FindBy(xpath = "//td[normalize-space()='Urgency']//following-sibling::*")
	WebElement urgencySec;
	@FindBy(xpath = "//td[normalize-space()='Writer rate']//following-sibling::*")
	WebElement rateSec;
	@FindBy(xpath = "//td[normalize-space()='Total payout']//following-sibling::*")
	WebElement feeSec;
	//ORDER COST
	@FindBy(xpath = "//td[normalize-space()='Per page']//following-sibling::*")
	WebElement perPageSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Pages']//following-sibling::*")
	WebElement costPagesSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Per slide']//following-sibling::*")
	WebElement perSildesSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Slides']//following-sibling::*")
	WebElement sildesSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Total']//following-sibling::*")
	WebElement totalSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Additional']//following-sibling::*")
	WebElement addSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Paid']//following-sibling::*")
	WebElement paidSec;
	//INSTRUCTIONS
	@FindBy(xpath = "//p[@class='p-rm__txt p-rm__txt--full']")
	WebElement insSec;
	//DISCOUNT
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Promo code']//following-sibling::*")
	WebElement codeSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Percent']//following-sibling::*")
	WebElement percentSec;
	//EXTRAS
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Writer‘s category']//following-sibling::*")
	WebElement writerCateSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Previous Writer']//following-sibling::*")
	WebElement preWriterSec;
	@FindBy(xpath = "//td[@class='fw-bold'][normalize-space()='Abstract Page']//following-sibling::*")
	WebElement absSec;
	//ORDER EVENT
	@FindBy(xpath = "//div[@role='button']")
	WebElement eventBTN;
	@FindBy(xpath = "//span[contains(text(),'Approve')]")
	WebElement appBTN;
	@FindBy(xpath = "//span[contains(text(),'Decline')]")
	WebElement decBTN;
	//HEADER
	public void clickAssBTN(){
		wait.until(ExpectedConditions.visibilityOf(assignBTN));
		assignBTN.click();
	}
	public void setAssDDL(String value){
		sleep(3);
		clickAssBTN();
		wait.until(ExpectedConditions.visibilityOf(assDDL));
		assDDL.click();
		wait.until(ExpectedConditions.visibilityOf(assTB));
		assTB.sendKeys(value+ Keys.ENTER);
	}
	public void clickSaveBTN(){
		WebUI.waitForWebElementVisible(saveBTN);
		saveBTN.click();
	}
	//DETAIL
	private void verifySec(WebElement element, String value) {
//		WebUI.w(element);
		String sectionText = WebUI.getWebElementText(element);
//		boolean   = sectionText.contains(value.toLowerCase());
		String message = "error";
		WebUI.assertEquals(sectionText, value, message);
//		Assert.assertTrue(containText, "Actual: "+sectionText+", expected " + value);
	}

	public void verifyTopic(String value) {
		verifySec(topicSec, value);
	}

	public void verifyDis(String value) {
		verifySec(disciplineSec, value);
	}

	public void verifyDoc(String value) {
		verifySec(documentSec, value);
	}

	public void verifySpacing(String value) {
		verifySec(spacingSec, value.toUpperCase());
	}

	public void verifyFormat(String value) {
		verifySec(formatSec, value);
	}

	public void verifyReferences(String value) {
		verifySec(referencesSec, value);
	}

	public void verifyPages(String value) {
		verifySec(pagesSec, value);
	}

	public void verifyWPP(String value) {
		String WPP = value.toLowerCase();
		if (WPP.equals("double")) {
			WPP = "275";
		} else if (WPP.equals("single")) {
			WPP = "550";
		}
		verifySec(wordsSec, WPP);
	}

	public void verifyUrgency(String value) {
		verifySec(urgencySec, value);
	}

	//ORDER COST
	public void verifyPerPage(String value) {
		verifySec(perPageSec, value);
	}

	public void verifyCostPages(String value) {
		verifySec(costPagesSec, value);
	}

	public void verifyPerSlide(String value) {
		verifySec(perSildesSec, value);
	}

	public void verifySilde(String value) {
		verifySec(sildesSec, String.valueOf(value));
	}

	public void verifyTotal(String value) {
		verifySec(totalSec, value);
	}

	public void verifyAdd(String value) {
		verifySec(addSec, value);
	}

	public void verifyPaid(String value) {
		verifySec(paidSec, value);
	}

	public void verifyRate() {
		verifySec(rateSec, Calculator.writerRate + "%");
	}

	public void verifyWriterFee() {
		verifySec(feeSec, "$" + Calculator.writerFee());
	}

	//INSTRUCTIONS
	public void verifyIns(String value) {
		wait.until(ExpectedConditions.visibilityOf(insSec));
		verifySec(insSec, value);
	}

	//DISCOUNT
	public void verifyCode(String value) {
		this.codeDis = value;
		verifySec(codeSec, value);
	}

	public void verifyPercent() {
		verifySec(percentSec, String.valueOf(Discounts.GetDiscount(this.codeDis)) + "%");
	}

	//EXTRAS
	public void verifywriterCate(String value) {
		verifySec(writerCateSec, value);
	}

	public void verifyPreWriter(String value) {
		verifySec(preWriterSec, value + " (+" + Calculator.preWriterPercent + "%)");
	}

	public void verifyAbsPrice() {
		verifySec(absSec, "$" + String.format("%.2f", Calculator.absPrice));
	}

	//ORDER EVENT
	public void clickEventBTN(){
		wait.until(ExpectedConditions.visibilityOf(eventBTN));
		eventBTN.click();
	}
	public void clickAppBTN(){
		clickEventBTN();
		wait.until(ExpectedConditions.visibilityOf(appBTN));
		appBTN.click();
	}
	public void clickDecBTN(){
		clickEventBTN();
		wait.until(ExpectedConditions.visibilityOf(decBTN));
		decBTN.click();
	}
}
