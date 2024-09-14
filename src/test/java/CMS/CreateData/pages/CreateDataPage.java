package CMS.CreateData.pages;

import Keywords.WebUI;
import Support.CMS.Routers;
import Support.Initialization.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

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
	WebElement nameTB;

	@FindBy(xpath = "//input[@name='url']")
	WebElement urlTB;

	@FindBy(xpath = "//input[@name='meta_title']")
	WebElement metaTileTB;

	@FindBy(xpath = "//*[@name='meta_description']")
	WebElement metaDesTB;

	@FindBy(xpath = "//input[@name='anchor']")
	WebElement anchorTB;

	@FindBy(xpath = "(//input[@name='title']/following-sibling::input)[2]")
	WebElement titleTB;

	@FindBy(xpath = "(//input[@name='essay_note']/following-sibling::input)[1]")
	WebElement essayNoteTB;

	@FindBy(xpath = "(//input[@name='essay_action']/following-sibling::input)[1]")
	WebElement essayActTB;

	@FindBy(xpath = "(//input[@name='offer_action']/following-sibling::input)[1]")
	WebElement offerActTB;

	@FindBy(xpath = "//select[contains(@class,'multiSelect_field multiSelect_field_samples')]/following-sibling::div[1]")
	WebElement sampleDRL;

	@FindBy(xpath = "//li[@data-value]") // Xác định danh sách các phần tử li
	private List<WebElement> options;


	@FindBy(xpath = "//a[@title='Save']")
	WebElement saveBTN;

	@FindBy(xpath = "//a[@title='Publish']")
	WebElement publishBTN;

	@FindBy(xpath = "//*[@name='intro']")
	WebElement shortIntroTB;

	@FindBy(xpath = "//*[@name='created_date']")
	WebElement createdDateTB;

	@FindBy(xpath = "//*[@name='academic_level']")
	WebElement academicTB;

	@FindBy(xpath = "//*[@name='paper_type']")
	WebElement paperTypeTB;

	@FindBy(xpath = "//*[@name='discipline']")
	WebElement disciplineTB;

	@FindBy(xpath = "//*[@name='citation']")
	WebElement citationTB;

	@FindBy(xpath = "//*[@name='pages']")
	WebElement pagesTB;

	@FindBy(xpath = "//*[@name='words']")
	WebElement wordsTB;

	@FindBy(xpath = "//input[@type='file']")
	WebElement uploadFileBTN;

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

	public void createArticles() {
		driver.get(Routers.ARTICLES);
		clickAddBTN();
	}

	public void selectArticle(String tye) {
		Select select = new Select(articleType);
		select.selectByVisibleText(tye);
	}

	public void setNameTB(String value) {
		WebUI.setText(nameTB, value);
	}

	public void setUrlTB(String value) {
		WebUI.setText(urlTB, value);
	}

	public void setMetaTitleSec(String value) {
		WebUI.setText(metaTileTB, value);
	}

	public void setMetaDesTB(String value) {
		WebUI.setText(metaDesTB, value);
	}

	public void setAnchorTB(String value) {
		WebUI.setText(anchorTB, value);
	}

	public void setTitleTB(String value) {
		WebUI.setText(titleTB, value);
	}

	public void setEssayNoteTB(String value) {
		WebUI.setText(essayNoteTB, value);
	}

	public void setEssayActTB(String value) {
		WebUI.setText(essayActTB, value);
	}

	public void setOfferActTB(String value) {
		WebUI.setText(offerActTB, value);
	}

	public void clickSampleDRL() {
		WebUI.clickWEBElement(sampleDRL);
	}
	public void clickOnArticle(String value) {
		// Tạo XPath động với giá trị được truyền vào
		String xpath = "(//a[normalize-space(text())='" + value + "'])[1]";

		// Tìm phần tử với XPath động và thực hiện thao tác click
		WebElement article = driver.findElement(By.xpath(xpath));
		WebUI.clickWEBElement(article);
	}

	public void setSampleDRL(String value) {
		clickSampleDRL();

		String[] parts = value.split(", ");

		for (String part : parts) {
			sleep(1);
			clickOnArticle(part);
			sleep(1);
		}
    }


	public void clickSaveBTN() {
		WebUI.clickWEBElement(saveBTN);
	}

	public void clickPublish() {
		WebUI.clickMultiElement(publishBTN, 2);
	}


	public void createSamplesList(String name, String url, String metaTitle,
								  String metaDes, String anchor, String title,
								  String essayNote, String essayAct, String offer, String sampleDetail) {
		createArticles();
		selectArticle("samples");
		setNameTB(name);
		setUrlTB(url);
		setMetaTitleSec(metaTitle);
		setMetaDesTB(metaDes);
		setAnchorTB(anchor);
		setTitleTB(title);
		setEssayNoteTB(essayNote);
		setEssayActTB(essayAct);
		setOfferActTB(offer);
		clickSampleDRL();
		setSampleDRL(sampleDetail);
		clickSaveBTN();
		sleep(2);
		clickPublish();
	}

	public void createSample() {
		driver.get(Routers.SAMPLES);
		clickAddBTN();
	}

	public void setShortIntroTB(String value) {
		WebUI.setText(shortIntroTB, value);
	}

	public void setCreatedDateTB(String value) {
		WebUI.setText(createdDateTB, value);
	}

	public void setAcademicTB(String value) {
		WebUI.setText(academicTB, value);
	}

	public void setPaperTypeTB(String value) {
		WebUI.setText(paperTypeTB, value);
	}

	public void setDisciplineTB(String value) {
		WebUI.setText(disciplineTB, value);
	}

	public void setCitationTB(String value) {
		WebUI.setText(citationTB, value);
	}

	public void setPagesTB(String value) {
		WebUI.setText(pagesTB, value);
	}

	public void setWordsTB(String value) {
		WebUI.setText(wordsTB, value);
	}

	public void clickUploadFile() {
		WebUI.clickWEBElement(uploadFileBTN);
	}

	public void setUploadPDF(String fileName, String paperType) {
//		uploadFileBTN.sendKeys("src/test/resources/filePDF/"+value+".pdf");
		if (Objects.equals(paperType, "PowerPoint Presentation")) {
			File file = new File("src/test/resources/filePDF/samples" + fileName + ".pptx");
			String absolutePath = file.getAbsolutePath();
			uploadFileBTN.sendKeys(absolutePath);
		} else {
			File file = new File("src/test/resources/filePDF/samples" + fileName + ".pdf");
			String absolutePath = file.getAbsolutePath();
			uploadFileBTN.sendKeys(absolutePath);
		}

	}

	public void createSampleDetail(String name, String url, String metaTitle, String metaDes, String intro, String date, String academic, String type,
								   String discipline, String citation, String pages, String words, String filename) {
		createSample();
		setNameTB(name);
		setUrlTB(url);
		setMetaTitleSec(metaTitle);
		setMetaDesTB(metaDes);
		setShortIntroTB(intro);
		setCreatedDateTB(date);
		setAcademicTB(academic);
		setPaperTypeTB(type);
		setDisciplineTB(discipline);
		setCitationTB(citation);
		setPagesTB(pages);
		setWordsTB(words);
		setUploadPDF(filename, type);
		sleep(5);
		clickSaveBTN();
		sleep(2);
		clickSaveBTN();
	}
}
