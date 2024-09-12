package CMS.CreateData.pages;

import Keywords.WebUI;
import Support.CMS.Routers;
import Support.Initialization.Init;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateDataPage extends Init {
	private WebDriver driver;
//	private WebDriverWait wait;

	@FindBy(xpath = "(//span[@class='project-name fbaloo'])[1]")
	WebElement WPHBTN;

	@FindBy(xpath = "//i[@class='fa fa-plus']")
	WebElement addBTN;

	@FindBy(xpath = "//select[@id='js-article-type']")
	WebElement articleType;

	@FindBy(xpath = "//input[@name='name']")
	WebElement nameSec;

	@FindBy(xpath = "//input[@name='url']")
	WebElement urlSec;

	@FindBy(xpath = "//input[@name='meta_title']")
	WebElement metaTileSec;

	@FindBy(xpath = "//*[@name='meta_description']")
	WebElement metaDesSec;

	@FindBy(xpath = "//input[@name='anchor']")
	WebElement anchorSec;

	@FindBy(xpath = "(//input[@name='title']/following-sibling::input)[2]")
	WebElement titleSec;

	@FindBy(xpath = "(//input[@name='essay_note']/following-sibling::input)[1]")
	WebElement essayNoteSec;

	@FindBy(xpath = "(//input[@name='essay_action']/following-sibling::input)[1]")
	WebElement essayActSec;

	@FindBy(xpath = "(//input[@name='offer_action']/following-sibling::input)[1]")
	WebElement offerActSec;

	@FindBy(xpath = "//a[@title='Save']")
	WebElement saveBTN;

	@FindBy(xpath = "//a[@title='Publish']")
	WebElement publishBTN;

	public CreateDataPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		new WebUI(driver); //Bắt buộc
		PageFactory.initElements(driver, this);
	}


	public void clickWPHBTN() {
		WebUI.clickWEBElement(WPHBTN);
		sleep(3);
	}

	public void clickAddBTN() {
		WebUI.clickWEBElement(addBTN);
		sleep(3);
	}

	public void createNewPage() {
		driver.get(Routers.ARTICLES);
		clickAddBTN();
	}

	public void selectArticle(String tye) {
		Select select = new Select(articleType);
		select.selectByVisibleText(tye);
	}

	public void setNameSec(String value) {
		WebUI.setText(nameSec, value);
	}

	public void setUrlSec(String value) {
		WebUI.setText(urlSec, value);
	}

	public void setMetaTitleSec(String value) {
		WebUI.setText(metaTileSec, value);
	}
	public void setMetaDesSec(String value) {
		WebUI.setText(metaDesSec, value);
	}
	public void setAnchorSec(String value) {
		WebUI.setText(anchorSec, value);
	}
	public void setTitleSec(String value) {
		WebUI.setText(titleSec, value);
	}
	public void setEssayNoteSec(String value) {
		WebUI.setText(essayNoteSec, value);
	}
	public void setEssayActSec(String value) {
		WebUI.setText(essayActSec, value);
	}
	public void setOfferActSec(String value) {
		WebUI.setText(offerActSec, value);
	}
	public void clickSaveBTN() {
		WebUI.clickWEBElement(saveBTN);
	}
	public void clickPublish() {
		WebUI.clickMultiElement(publishBTN,2);
	}
	public void createNewSamples(String name,String url,String metaTitle,String metaDes,String anchor,String title, String essayNote, String essayAct, String offer) {
		createNewPage();
		selectArticle("samples");
		setNameSec(name);
		setUrlSec(url);
		setMetaTitleSec(metaTitle);
		setMetaDesSec(metaDes);
		setAnchorSec(anchor);
		setTitleSec(title);
		setEssayNoteSec(essayNote);
		setEssayActSec(essayAct);
		setOfferActSec(offer);
		clickSaveBTN();
		sleep(2);
		clickPublish();
	}
}
