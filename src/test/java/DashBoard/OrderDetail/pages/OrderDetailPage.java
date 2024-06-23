package DashBoard.OrderDetail.pages;

import Keywords.WebUI;
import Support.Initialization.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderDetailPage extends Init {
	private WebDriver driver;
	private WebDriverWait wait;

	public OrderDetailPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	//Detail grid
	public By topicSec = By.xpath("//td[normalize-space()='Topic']//following-sibling::*");
	public By disciplineSec = By.xpath("//td[normalize-space()='Discipline']//following-sibling::*");
	public By documentSec = By.xpath("//td[normalize-space()='Document type']//following-sibling::*");
	public By spacingSec = By.xpath("//td[normalize-space()='Spacing']//following-sibling::*");
	public By formatSec = By.xpath("//td[normalize-space()='Citation style']//following-sibling::*");
	public By referencesSec = By.xpath("//td[normalize-space()='References']//following-sibling::*");
	public By pagesSec = By.xpath("//td[@class='fw-bold text-nowrap'][normalize-space()='Pages']//following-sibling::*");
	public By wordsSec = By.xpath("//td[normalize-space()='Words per page']//following-sibling::*");
	public By urgencySec = By.xpath("//td[normalize-space()='Urgency']//following-sibling::*");

	private void verifySec(By by, String value) {
		WebUI.waitForElementVisible(by);
		String sectionText = WebUI.getElementText(by);
//		boolean   = sectionText.contains(value.toLowerCase());
		WebUI.verifyContains(sectionText, value);
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
		verifySec(spacingSec, value);
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
		if (value.contains("double")) {
			WPP = "275";
		}
		if (value.contains("single")) {
			WPP = "550";
		}
		verifySec(wordsSec, WPP);
	}

	public void verifyUrgency(String value) {
		verifySec(urgencySec, value);
	}

	//Order cost
	public By perPageSec = By.xpath("//td[normalize-space()='Per page']//following-sibling::*");
	public By costPagesSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Per page']//following-sibling::*");
	public By perSildesSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Per slide']//following-sibling::*");
	public By sildesSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Slides']//following-sibling::*");
	public By totalSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Total']//following-sibling::*");
	public By addSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Additional']//following-sibling::*");
	public By paidSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Paid']//following-sibling::*");

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
		verifySec(sildesSec, value);
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
}
