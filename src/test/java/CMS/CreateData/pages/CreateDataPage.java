package CMS.CreateData.pages;

import Keywords.WebUI;
import Support.CMS.Routers;
import Support.Initialization.Init;
import helpers.ExcelHelper;
//import logs.LogUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v128.log.Log;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CreateDataPage extends Init {
	private WebDriver driver;
	//	private WebDriverWait wait;
	ExcelHelper excelHelper = new ExcelHelper();
	SoftAssert softassert = new SoftAssert();
	private Workbook wb;
	private Sheet sh;
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

	//Header
	@FindBy(xpath = "//i[@class='fa fa-info fa-fw']")
	WebElement editIntroBTN;

	@FindBy(xpath = "//i[@class='fa fa-money fa-fw']")
	WebElement editOfferBTN;

	@FindBy(xpath = "//div[@role='textbox']")
	WebElement noteTB;

	@FindBy(xpath = "//i[@class='fa fa-trash fa-fw']")
	WebElement trashBTN;

	@FindBy(xpath = "//div[contains(text(),'Sorry, the page you are looking for could not be f')]")
	WebElement pageNotFoundTXT;

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

	public void setNoteTB(String value) {
		clickAddBTN();
		WebUI.setText(noteTB, value);
		clickSaveBTN();
		sleep(2);
	}

	public void setEditIntroData(String value) {
		clickEditIntroBTN();
		setNoteTB(value);
	}

	public void setEditOfferData(String value) {
		clickEditOfferBTN();
		setNoteTB(value);
	}

	public void clickSampleDRL() {
		WebUI.clickWEBElement(sampleDRL);
	}

	public void clickOnArticle(String value) {
		// Tạo XPath động với giá trị được truyền vào
		if (value.length() > 60) {
			value = value.substring(0, 60);  // Cắt chuỗi nếu vượt quá 60 ký tự
		}
		String xpath = "(//a[contains(normalize-space(text()), '" + value + "')])[1]";

		// Tìm phần tử với XPath động và thực hiện thao tác click
		WebElement article = driver.findElement(By.xpath(xpath));
		WebUI.clickWEBElement(article);
	}

	public void setSampleDRL(String value) {
		clickSampleDRL();

		String[] parts = value.split(", ");

		for (String part : parts) {
			clickOnArticle(part);
			sleep(1);
		}
	}


	public void clickSaveBTN() {
		WebUI.clickWEBElement(saveBTN);
	}

	public void clickPublish() {
		WebUI.doubleClickElement(publishBTN);
		sleep(2);
	}

	public void clickEditIntroBTN() {
		WebUI.clickWEBElement(editIntroBTN);
	}

	public void clickEditOfferBTN() {
		WebUI.clickWEBElement(editOfferBTN);
	}

	public void clickTrashBTN() {
		WebUI.doubleClickElement(trashBTN);
		sleep(5);
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

	public void createSampleDetail(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName);
		for (int i = 1; i <= lastRow; i++) {
			excelHelper.setExcelFile(fileName, sheetName);
			String name = excelHelper.getCellData("name", i);
			String url = excelHelper.getCellData("url", i);
			String title = excelHelper.getCellData("meta_title", i);
			String description = excelHelper.getCellData("meta_description", i);
			String intro = excelHelper.getCellData("short_intro", i);
			String date = excelHelper.getCellData("created_date", i);
			String academic = excelHelper.getCellData("academic_level", i);
			String paperType = excelHelper.getCellData("type_of_paper", i);
			String discipline = excelHelper.getCellData("discipline", i);
			String citation = excelHelper.getCellData("citation", i);
			String pages = excelHelper.getCellData("pages", i);
			String words = excelHelper.getCellData("total_words", i);
			String fileNamePDF = excelHelper.getCellData("fileName", i);
			createSample();
			setNameTB(name);
			setUrlTB(url);
			setMetaTitleSec(title);
			setMetaDesTB(description);
			setShortIntroTB(intro);
			setCreatedDateTB(date);
			setAcademicTB(academic);
			setPaperTypeTB(paperType);
			setDisciplineTB(discipline);
			setCitationTB(citation);
			setPagesTB(pages);
			setWordsTB(words);
			setUploadPDF(fileNamePDF, paperType);
			recordFile(driver.getCurrentUrl(), "id",i);
			recordFile(url, "url",i-1);
			sleep(5);
			clickSaveBTN();
			sleep(2);
			clickSaveBTN();
			// LogUtils.infoCustom(driver.getCurrentUrl());
			// LogUtils.infoCustom(url);
		}

	}
	public void recordFile(String value, String column, int row) {
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "sheet1";
		excelHelper.setExcelFile(fileName,sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName);
		excelHelper.setCellData(value,column,lastRow+row);
	}
	public void createSamplesArticles(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName);
		for (int i = 1; i <= lastRow; i++) {
			excelHelper.setExcelFile(fileName, sheetName);
			String name = excelHelper.getCellData("name", i);
			String url = excelHelper.getCellData("url", i);
			String metaTitle = excelHelper.getCellData("meta_title", i);
			String description = excelHelper.getCellData("meta_description", i);
			String anchor = excelHelper.getCellData("anchor", i);
			String title = excelHelper.getCellData("title", i);
			String essayNote = excelHelper.getCellData("essay_note", i);
			String essayAct = excelHelper.getCellData("essay_action", i);
			String offer = excelHelper.getCellData("offer_action", i);
			String samples = excelHelper.getCellData("samples", i);
			String editIntro = excelHelper.getCellData("edit_intro", i);
			String editOffer = excelHelper.getCellData("edit_offer", i);
			createArticles();
			selectArticle("samples");
			setNameTB(name);
			setUrlTB(url);
			setMetaTitleSec(metaTitle);
			setMetaDesTB(description);
			setAnchorTB(anchor);
			setTitleTB(title);
			setEssayNoteTB(essayNote);
			setEssayActTB(essayAct);
			setOfferActTB(offer);
			setSampleDRL(samples);
			clickSaveBTN();
			sleep(2);
			clickPublish();
			// LogUtils.info(driver.getCurrentUrl());
			// LogUtils.info(url);
			recordFile(driver.getCurrentUrl(), "id",i);
			recordFile(url, "url",i-1);
			setEditIntroData(editIntro);
			setEditOfferData(editOffer);
		}

	}

	public void deleteArticles(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName);
		// Kiểm tra độ dài của hai mảng
		for (int i = 1; i <= lastRow; i++) {
			String id = excelHelper.getCellData("id", i);
			String url = excelHelper.getCellData("url", i);
			driver.get(id);

			try {
				if (pageNotFoundTXT.isDisplayed()) {
					// LogUtils.info(url+" "+id);
					// LogUtils.info("Page not exit");
					System.out.println("Page not exit");
				}
			} catch (NoSuchElementException e) {
				if (Objects.equals(url, urlTB.getAttribute("value"))) {
					// LogUtils.info(url+" "+id);
					clickTrashBTN();
					// LogUtils.info("Đã xóa");
					System.out.println("Đã xóa");
				} else {
					// LogUtils.info(url+" "+id);
					System.out.println("Không xóa");
				}
			}
		}
	}
}