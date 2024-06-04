package DashBoard.OrderDetail.pages;

import Keywords.WebUI;
import Support.Initialization.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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
		boolean containText = sectionText.contains(value);
		Assert.assertTrue(containText, "Thẻ "+sectionText+" chứa " + value);
	}
	public void verifyTopic(String value){
		verifySec(topicSec,value);
	}
	public void verifyDis(String value){
		verifySec(disciplineSec,value);
	}
	public void verifyDoc(String value){
		verifySec(documentSec,value);
	}
	public void verifySpacing(String value){
		verifySec(spacingSec,value);
	}
	public void verifyFormat(String value){
		verifySec(formatSec,value);
	}
	public void verifyReferences(String value){
		verifySec(referencesSec,value);
	}
	public void verifyPages(String value){
		verifySec(pagesSec,value);
	}

	//Order cost
	public By perPageSec = By.xpath("//td[normalize-space()='Per page']//following-sibling::*");
	public By pagesOCSec = By.xpath("//td[@class='fw-bold'][normalize-space()='Per page']//following-sibling::*");

}
