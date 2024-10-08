package CMS.CreateData.pages;

import AcaWriting.Keywords.WebUI;
import AcaWriting.Support.CMS.Routers;
import AcaWriting.Support.Initialization.Init;
import AcaWriting.drivers.DriverManager;
import helpers.ExcelHelper;
import logs.LogUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CreateDataPage extends Init {
	//	private WebDriver driver;
	private WebDriverWait wait;
	ExcelHelper excelHelper = new ExcelHelper();
	SoftAssert softassert = new SoftAssert();
	private Workbook wb;
	private Sheet sh;
	private String fileName;
	private String dataServicePage;

	@FindBy(xpath = "(//span[@class='project-name fbaloo'])[1]")
	WebElement WPHBTN;

	@FindBy(xpath = "(//span[@class='project-name fbaloo'])[3]")
	WebElement IBWBTN;

	@FindBy(xpath = "//i[@class='fa fa-plus']")
	WebElement addBTN;

	@FindBy(xpath = "//select[@id='js-article-type']")
	WebElement articleType;

	@FindBy(xpath = "//input[@name='name']")
	WebElement nameTB;

	@FindBy(xpath = "//input[@name='value']")
	WebElement valueTB;

	@FindBy(xpath = "//input[@name='url']")
	WebElement urlTB;

	@FindBy(xpath = "//input[@name='meta_title']")
	WebElement metaTileTB;

	@FindBy(xpath = "//*[@name='meta_description']")
	WebElement metaDesTB;

	@FindBy(xpath = "//*[@name='college']")
	WebElement collegeTB;

	@FindBy(xpath = "//*[@name='text']")
	WebElement textTB;

	@FindBy(xpath = "//input[@name='anchor']")
	WebElement anchorTB;

	@FindBy(xpath = "//input[@name='degree']")
	WebElement degreeTB;

	@FindBy(xpath = "//input[@name='city']")
	WebElement cityTB;

	@FindBy(xpath = "//textarea[@name='bio']")
	WebElement bioTB;

	@FindBy(xpath = "//input[@name='count']")
	WebElement completedTB;

	@FindBy(xpath = "//input[@name='achievement']")
	WebElement achievementTB;

	@FindBy(xpath = "//input[@name='disciplines']")
	WebElement disciplinesTB;

	@FindBy(xpath = "(//input[@name='title']/following-sibling::input)[1] | (//input[@name='title']/following-sibling::input)[2]")
	WebElement titleTB;

	@FindBy(xpath = "(//input[@name='essay_note']/following-sibling::input)[1]")
	WebElement essayNoteTB;

	@FindBy(xpath = "(//input[@name='essay_action']/following-sibling::input)[1]")
	WebElement essayActTB;

	@FindBy(xpath = "(//input[@name='offer_action']/following-sibling::input)[1] | (//input[@name='offer_action']/following-sibling::input)[2]")
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

	@FindBy(xpath = "//input[@name='file']/following-sibling::input[1]")
	WebElement fileNameElement;

	//Header
	@FindBy(xpath = "//i[@class='fa fa-info fa-fw']")
	WebElement editIntroBTN;

	@FindBy(xpath = "//a[@title='Edit content']")
	WebElement editContentBTN;

	@FindBy(xpath = "//a[@title='Edit Offer']")
	WebElement editOfferBTN;

	@FindBy(xpath = "//a[@title='Edit FAQ Banner']")
	WebElement editFAQBannerBTN;

	@FindBy(xpath = "//a[@title='Edit FAQ']")
	WebElement editFAQBTN;

	@FindBy(xpath = "//a[@title='Edit Perk']")
	WebElement editPerkBTN;

	@FindBy(xpath = "//a[@title='Setting']")
	WebElement settingBTN;

	@FindBy(xpath = "//div[@role='textbox']")
	WebElement noteTB;

	@FindBy(xpath = "//i[@class='fa fa-trash fa-fw']")
	WebElement trashBTN;

	@FindBy(xpath = "//div[contains(text(),'Sorry, the page you are looking for could not be f')]")
	WebElement pageNotFoundTXT;

	@FindBy(xpath = "//img[@id='author-preview']")
	static WebElement imgElement;

	@FindBy(xpath = "//div[contains(text(),'Sorry, the page you are looking for could not be found')]")
	WebElement pageNotFoundMessage;

	@FindBy(xpath = "//div[@class='exception-illustration hidden-xs-down']")
	WebElement exceptionMessage;

	@FindBy(xpath = "//select[@name='source']")
	WebElement sourceType;

	@FindBy(xpath = "//select[@name='rating']")
	WebElement ratingType;

	@FindBy(xpath = "//input[@name='paper_type' or @name='type']")
	WebElement typeOfPaperTB;

	@FindBy(xpath = "//i[@data-behavior='checkbox']")
	WebElement isFeaturable;

	// Note editable
	@FindBy(xpath = "//div[@class='note-editable']")
	WebElement noteEditableElement;

	@FindBy(xpath = "//button[@aria-expanded='false']")
	WebElement styleBTN;

	@FindBy(xpath = "(//a[@data-value='h1'])[1]")
	WebElement styleH1BTN;

	@FindBy(xpath = "(//a[@data-value='h2'])[1]")
	WebElement styleH2BTN;

	@FindBy(xpath = "(//a[@data-value='h3'])[1]")
	WebElement styleH3BTN;

	@FindBy(xpath = "(//a[@data-value='h4'])[1]")
	WebElement styleH4BTN;

	@FindBy(xpath = "(//a[@data-value='h5'])[1]")
	WebElement styleH5BTN;

	@FindBy(xpath = "(//a[@data-value='h6'])[1]")
	WebElement styleH6BTN;

	public CreateDataPage(WebDriver driver) {
//		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		PageFactory.initElements(driver, this);
	}

	public void clickWPHBTN() {
		WebUI.clickWEBElement(WPHBTN);
		sleep(3);
	}

	public void clickIBWBTN() {
		WebUI.clickWEBElement(IBWBTN);
	}

	public void clickAddBTN() {
		WebUI.clickWEBElement(addBTN);
		sleep(3);
	}

	public void createArticles() {
		DriverManager.getDriver().get(Routers.ARTICLES);
		clickAddBTN();
	}

	public void selectArticle(String type) {
		Select select = new Select(articleType);
		select.selectByVisibleText(type);
	}

	public void setSourceDRL(String type) {
		Select select = new Select(sourceType);
		select.selectByVisibleText(type);
	}

	public void setRatingDRL(String type) {
		Select select = new Select(ratingType);
		select.selectByVisibleText(type);
	}

	public void setNameTB(String value) {
		WebUI.setText(nameTB, value);
	}

	public void setValueTB(String value) {
		WebUI.setText(valueTB, value);
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

	public void setCollegeTB(String value) {
		WebUI.setText(collegeTB, value);
	}

	public void setDegreeTB(String value) {
		WebUI.setText(degreeTB, value);
	}

	public void setCityTB(String value) {
		WebUI.setText(cityTB, value);
	}

	public void setBioTB(String value) {
		WebUI.setText(bioTB, value);
	}

	public void setCompletedTB(String value) {
		WebUI.setText(completedTB, value);
	}

	public void setAchievementTB(String value) {
		WebUI.setText(achievementTB, value);
	}

	public void setDisciplinesTB(String value) {
		WebUI.setText(disciplinesTB, value);
	}

	public void setTextTB(String value) {
		WebUI.setText(textTB, value);
	}

	public String getUrlTB() {
		return WebUI.getValue(urlTB);
	}

	public String getNameTB() {
		return WebUI.getValue(nameTB);
	}

	public String getDegreeTB() {
		return WebUI.getValue(degreeTB);
	}

	public String getCityTB() {
		return WebUI.getValue(cityTB);
	}

	public String getBioTB() {
		return WebUI.getText(bioTB);
	}

	public String getComOrderTB() {
		return WebUI.getValue(completedTB);
	}

	public String getAchiTB() {
		return WebUI.getValue(achievementTB);
	}

	public String getDiscipline() {
		return WebUI.getValue(disciplineTB);
	}

	public String getDisciplines() {
		return WebUI.getValue(disciplinesTB);
	}

	public String getCollegeTB() {
		return WebUI.getValue(collegeTB);
	}

	public String getTextTB() {
		return WebUI.getText(textTB);
	}

	public String getSourceTB() {
		return WebUI.getValue(sourceType);
	}

	public String getRatingTB() {
		return WebUI.getValue(ratingType);
	}

	public String getTypeOfPaperTB() {
		return WebUI.getValue(typeOfPaperTB);
	}

	public String getIsFeaturable() {
		String checked = WebUI.getValue(isFeaturable);
		if (Objects.equals(checked, "1")) {
			return "yes";
		} else {
			return "no";
		}
	}

	public void setNoteTB(String value) {
		// Sử dụng JavaScript để lấy nội dung HTML hiện tại
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		String currentHTML = (String) js.executeScript("return arguments[0].innerHTML;", noteEditableElement);

		// Thêm dữ liệu mới vào cuối nội dung HTML hiện tại
		String newHTML = currentHTML + value;

		// Sử dụng JavaScript để set lại nội dung với dữ liệu mới
		js.executeScript("arguments[0].innerHTML = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", noteEditableElement, newHTML);
		sleep(1);
	}

	public void setEditIntroData(String value) {
		clickEditIntroBTN();
		clickAddBTN();
		setNoteTB(value);
		sleep(1);
		clickSaveBTN();
	}

	public void setEditOfferData(String value) {
		clickEditOfferBTN();
		setNoteTB(value);
	}

	public void clickSampleDRL() {
		WebUI.clickWEBElement(sampleDRL);
	}

	public void setSampleDRL(String fileName, String sheetNameDetail) {
		clickSampleDRL();
		excelHelper.setExcelFile(fileName, sheetNameDetail);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetNameDetail, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			String nameDetail = excelHelper.getCellData("NAME", i);
			String paperType = excelHelper.getCellData("TYPE_OF_PAPER", i);
			clickOnArticle(nameDetail, paperType);
			sleep(1);
		}
	}

	public void clickIsFeaturable(String value) {
		if (Objects.equals(value, "yes")) {
			WebUI.clickWEBElement(isFeaturable);
		}
	}

	public void clickOnArticle(String title, String type) {
		// Tạo XPath động với giá trị được truyền vào
		String value = type + " - " + title;
		if (value.length() > 55) {
			value = value.substring(0, 55);  // Cắt chuỗi nếu vượt quá 60 ký tự
		}
		String xpath = "(//a[contains(normalize-space(text()), '" + value + "')])[1]";

		// Tìm phần tử với XPath động và thực hiện thao tác click
		WebElement article = DriverManager.getDriver().findElement(By.xpath(xpath));
		WebUI.clickWEBElement(article);
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

	public void clickEditContentBTN() {
		WebUI.clickWEBElement(editContentBTN);
	}

	public void clickEditFAQBannerBTN() {
		WebUI.clickWEBElement(editFAQBannerBTN);
	}

	public void clickEditFAQBTN() {
		WebUI.clickWEBElement(editFAQBTN);
	}

	public void clickEditOfferBTN() {
		WebUI.clickWEBElement(editOfferBTN);
	}

	public void clickSettingBTN() {
		WebUI.clickWEBElement(settingBTN);
	}

	public void clickH1BTN() {
		WebUI.clickWEBElement(styleH1BTN);
	}

	public void clickH2BTN() {
		WebUI.clickWEBElement(styleH2BTN);
	}

	public void clickH3BTN() {
		WebUI.clickWEBElement(styleH3BTN);
	}

	public void clickH4BTN() {
		WebUI.clickWEBElement(styleH4BTN);
	}

	public void clickH5BTN() {
		WebUI.clickWEBElement(styleH5BTN);
	}

	public void clickH6BTN() {
		WebUI.clickWEBElement(styleH6BTN);
	}


	public void clickStyleBTN() {
		WebUI.clickWEBElement(styleBTN);
	}

	public void clickTrashBTN() {
		WebUI.doubleClickElement(trashBTN);
		sleep(5);
	}

	public void addSample() {
		DriverManager.getDriver().get(Routers.SAMPLES);
		clickAddBTN();
	}

	public void addCustomerReview() {
		DriverManager.getDriver().get(Routers.CUSTOMER_REVIEW);
		clickAddBTN();
	}

	public void addWriterReview() {
		DriverManager.getDriver().get(Routers.WRITER_REVIEW);
		clickAddBTN();
	}

	public void addConstants() {
		DriverManager.getDriver().get(Routers.CONSTANTS);
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

	public void setTypeOfPaperTB(String value) {
		WebUI.setText(typeOfPaperTB, value);
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

	public void setUploadIMG(String fileName) {
		File file = new File("src/test/resources/Image/writerReview/" + fileName);
		String absolutePath = file.getAbsolutePath();
		uploadFileBTN.sendKeys(absolutePath);
	}

	public boolean checkResult(String fileName, String sheetName, int i) {
		excelHelper.setExcelFile(fileName, sheetName);
		return !Objects.equals("Passed", excelHelper.getCellData("RESULT", i));
	}

	public boolean checkResultTest(String result) {
		return !Objects.equals("Passed", result);
	}

	public boolean checkPageNotFound() {
		try {
			// Kiểm tra xem element có hiện diện và hiển thị hay không
			if (pageNotFoundMessage.isDisplayed()) {
				LogUtils.info("Page is valid");
				return true;
			}
		} catch (Exception e) {
			// Nếu không tìm thấy element, nghĩa là trang không bị lỗi 404
			LogUtils.info("Page not found");
		}
		return false;
	}

	public boolean isNoteNotFound() {
		try {
			// Kiểm tra xem element có hiện diện và hiển thị hay không
			if (noteEditableElement.isDisplayed()) {
				LogUtils.info("Note not found");
				return false;
			}
		} catch (Exception e) {
			// Nếu không tìm thấy element, nghĩa là trang không bị lỗi 404
			LogUtils.info("Page is valid");
		}
		return true;
	}

	public boolean isPageReady() {
		try {
			// Kiểm tra xem trashBTN có hiện diện và hiển thị hay không
			if (trashBTN.isDisplayed()) {
				LogUtils.info("Trash button is displayed. Page is ready.");
				return true;
			} else {
				LogUtils.info("Trash button is not displayed.");
			}
		} catch (NoSuchElementException e) {
			// Nếu không tìm thấy element, nghĩa là trang có thể bị lỗi 404
			LogUtils.info("Trash button not found. Page may be invalid.");
		}
		return false;
	}


	public String DownloadImage() {
		try {
			// Lấy URL của ảnh từ thẻ <img> bằng Selenium
			String imgUrl = imgElement.getAttribute("src");

			// Lấy tên file từ URL
			String fileName = Paths.get(new URL(imgUrl).getPath()).getFileName().toString() + ".jpg";

			// Đặt đường dẫn tới thư mục cần lưu
			Path targetPath = new File("src/test/resources/Image/writerReview/" + fileName).toPath(); // Đường dẫn tới thư mục

			// Tải ảnh về và lưu
			URL url = new URL(imgUrl);
			InputStream in = url.openStream();
			Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
			in.close();

			System.out.println("Ảnh đã được tải về và lưu với tên: " + fileName);
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public void recordFile(String value, String column) {
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "sheet1";

		// Thiết lập file Excel
		excelHelper.setExcelFile(fileName, sheetName);

		// Lấy dòng có dữ liệu cuối cùng
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, column);

		// Nếu không có dòng nào có dữ liệu, bắt đầu từ dòng 1
		if (lastRow == -1) {
			lastRow = 0;
		}

		// Ghi dữ liệu vào file Excel
		try {
			excelHelper.setCellData(value, column, lastRow + 1);
		} catch (Exception e) {
			System.err.println("Lỗi khi ghi dữ liệu vào file: " + e.getMessage());
		}
	}

	public void waitForFileNameToHaveValue(String expectedValue, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeoutInSeconds));

		try {
			wait.until(driver -> {
				// Lấy element 'file_name'
				WebElement fileNameElement = driver.findElement(By.name("file_name"));
				String value = fileNameElement.getAttribute("value");
				System.out.println("Current value of file_name: " + value); // In giá trị hiện tại ra console
				return expectedValue.equals(value) || (value != null && !value.isEmpty()) ? value : null;
			});

			System.out.println("File name input has the expected value or is not empty: " + expectedValue);
		} catch (TimeoutException e) {
			System.err.println("Timeout waiting for the file_name input to have the expected value: " + expectedValue);
		} catch (Exception e) {
			System.err.println("Error while waiting for file_name input: " + e.getMessage());
		}
	}
	// create data
	public void createSampleDetail(String fileName, String sheetName){
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String name = excelHelper.getCellData("NAME", i);
				String url = excelHelper.getCellData("URL", i);
				String title = excelHelper.getCellData("META_TITLE", i);
				String description = excelHelper.getCellData("META_DESCRIPTION", i);
				String intro = excelHelper.getCellData("SHORT_INTRO", i);
				String date = excelHelper.getCellData("CREATE_DATA", i);
				String academic = excelHelper.getCellData("ACADEMIC", i);
				String paperType = excelHelper.getCellData("TYPE_OF_PAPER", i);
				String discipline = excelHelper.getCellData("DISCIPLINE", i);
				String citation = excelHelper.getCellData("CITATION", i);
				String pages = excelHelper.getCellData("PAGES", i);
				String words = excelHelper.getCellData("TOTAL_WORDS", i);
				String fileNamePDF = excelHelper.getCellData("FILE_NAME", i);
				String fileNameOnly = excelHelper.getCellData("ONLY_NAME", i);

				addSample();
				setNameTB(name);
				setUrlTB(url);
				setMetaTitleSec(title);
				setMetaDesTB(description);
				setShortIntroTB(intro);
				setCreatedDateTB(date);
				setAcademicTB(academic);
				setTypeOfPaperTB(paperType);
				setDisciplineTB(discipline);
				setCitationTB(citation);
				setPagesTB(pages);
				setWordsTB(words);
				setUploadPDF(fileNamePDF, paperType);
				waitForFileNameToHaveValue(fileNameOnly, 240);

//				sleep(240);
				clickSaveBTN();
				sleep(2);
				clickSaveBTN();
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(url, "URL");

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				LogUtils.infoCustom(url);

				sleep(2);
			}
		}
	}

	public void createSampleDetailTest(Hashtable<String, String> data) throws Exception {
		if (checkResultTest(data.get("RESULT"))) {
			addSample();
			setNameTB(data.get("NAME"));
			setUrlTB(data.get("URL"));
			setMetaTitleSec(data.get("META_TITLE"));
			setMetaDesTB(data.get("META_DESCRIPTION"));
			setShortIntroTB(data.get("SHORT_INTRO"));
			setCreatedDateTB(data.get("CREATE_DATA"));
			setAcademicTB(data.get("ACADEMIC"));
			setTypeOfPaperTB(data.get("TYPE_OF_PAPER"));
			setDisciplineTB(data.get("DISCIPLINE"));
			setCitationTB(data.get("CITATION"));
			setPagesTB(data.get("PAGES"));
			setWordsTB(data.get("TOTAL_WORDS"));
			setUploadPDF(data.get("FILENAME"), data.get("TYPE_OF_PAPER"));
			LogUtils.infoCustom("index: " + data.get("RESULT").indexOf("RESULT"));


			LogUtils.infoCustom("Processing index for RESULT");
			int rowIndex = excelHelper.findCellIndex("NAME", data.get("NAME"));
			System.out.println("rowIndex: " + rowIndex);
			excelHelper.setCellData("Passed", "RESULT", rowIndex);
			sleep(2);
		}
	}

	public void createSamplesArticlesWPH(String fileName, String sheetName, String sheetNameDetail) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String URL = excelHelper.getCellData("URL", i);
				String META_TITLE = excelHelper.getCellData("META_TITLE", i);
				String META_DESCRIPTION = excelHelper.getCellData("META_DESCRIPTION", i);
				String ANCHOR = excelHelper.getCellData("ANCHOR", i);
				String TITLE = excelHelper.getCellData("TITLE", i);
				String ESSAY_NOTE = excelHelper.getCellData("ESSAY_NOTE", i);
				String ESSAY_ACTION = excelHelper.getCellData("ESSAY_ACTION", i);
				String OFFER_ACTION = excelHelper.getCellData("OFFER_ACTION", i);
				String EDIT_INTRO = excelHelper.getCellData("EDIT_INTRO", i);
				String EDIT_OFFER = excelHelper.getCellData("EDIT_OFFER", i);

				createArticles();
				selectArticle("samples");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setTitleTB(TITLE);
				setEssayNoteTB(ESSAY_NOTE);
				setEssayActTB(ESSAY_ACTION);
//				setOfferActTB(OFFER_ACTION);

				ExcelHelper excelHelper2 = new ExcelHelper();
				excelHelper2.setExcelFile(fileName, sheetNameDetail);
				setSampleDRL(fileName, sheetNameDetail);

				clickSaveBTN();
				sleep(2);
				clickPublish();
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");
				setEditIntroData(EDIT_INTRO);
				setEditOfferData(EDIT_OFFER);
				excelHelper.setCellData("Passed", "RESULT", i);
				sleep(2);
			}
		}
	}

	public void createSamplesArticlesIBW(String fileName, String sheetName, String sheetNameDetail) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String URL = excelHelper.getCellData("URL", i);
				String META_TITLE = excelHelper.getCellData("META_TITLE", i);
				String META_DESCRIPTION = excelHelper.getCellData("META_DESCRIPTION", i);
				String ANCHOR = excelHelper.getCellData("ANCHOR", i);
				String TITLE = excelHelper.getCellData("TITLE", i);
				String ESSAY_NOTE = excelHelper.getCellData("ESSAY_NOTE", i);
				String ESSAY_ACTION = excelHelper.getCellData("ESSAY_ACTION", i);
				String EDIT_INTRO = excelHelper.getCellData("EDIT_INTRO", i);

				createArticles();
				selectArticle("samples");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setTitleTB(TITLE);
				setEssayNoteTB(ESSAY_NOTE);
				setEssayActTB(ESSAY_ACTION);

				ExcelHelper excelHelper2 = new ExcelHelper();
				excelHelper2.setExcelFile(fileName, sheetNameDetail);
				setSampleDRL(fileName, sheetNameDetail);

				clickSaveBTN();
				sleep(2);
				excelHelper.setCellData("Passed", "RESULT", i);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				setEditIntroData(EDIT_INTRO);
				clickPublish();

				sleep(2);
			}
		}
	}

	public void createCustomerReview(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String COLLEGE = excelHelper.getCellData("COLLEGE", i);
				String TEXT = excelHelper.getCellData("TEXT", i);
				String SOURCE_LINKED = excelHelper.getCellData("SOURCE_LINKED", i);
				String RATING = excelHelper.getCellData("RATING", i);
				String TYPE_OF_PAPER = excelHelper.getCellData("TYPE_OF_PAPER", i);
				String IS_FEATURABLE = excelHelper.getCellData("IS_FEATURABLE", i);

				addCustomerReview();
				setNameTB(NAME);
				setCollegeTB(COLLEGE);
				setTextTB(TEXT);
				setSourceDRL(SOURCE_LINKED);
				setRatingDRL(RATING);
				setTypeOfPaperTB(TYPE_OF_PAPER);
				clickIsFeaturable(IS_FEATURABLE);

				clickSaveBTN();
				sleep(2);
				clickSaveBTN();
				excelHelper.setCellData(DriverManager.getDriver().getCurrentUrl(), "URL", i);
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				excelHelper.setCellData("Passed", "RESULT", i);
				sleep(2);
			}
		}
	}

	public void createWriterReview(String fileName, String sheetName) throws IOException {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String DEGREE = excelHelper.getCellData("DEGREE", i);
				String CITY = excelHelper.getCellData("CITY", i);
				String BIO = excelHelper.getCellData("BIO", i);
				String COMPLETED_ORDER = excelHelper.getCellData("COMPLETED_ORDER", i);
				String ACHIEVEMENT = excelHelper.getCellData("ACHIEVEMENT", i);
				String DISCIPLINES = excelHelper.getCellData("DISCIPLINES", i);
				String FILE_NAME = excelHelper.getCellData("FILE_NAME", i);

				addWriterReview();
				setUploadIMG(FILE_NAME);
				setNameTB(NAME);
				setDegreeTB(DEGREE);
				setCityTB(CITY);
				setBioTB(BIO);
				setCompletedTB(COMPLETED_ORDER);
				setAchievementTB(ACHIEVEMENT);
				setDisciplinesTB(DISCIPLINES);

				sleep(2);
				clickSaveBTN();
				excelHelper.setCellData(DriverManager.getDriver().getCurrentUrl(), "URL", i);
				excelHelper.setCellData("Passed", "RESULT", i);
				sleep(2);
				clickSaveBTN();

				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				sleep(2);
			}
		}
	}

	public void createConstants(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String VALUE = excelHelper.getCellData("VALUE", i);

				addConstants();
				setNameTB(NAME);
				setValueTB(VALUE);

				sleep(2);
				clickSaveBTN();
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				sleep(1);

			}
		}
	}

	public void deleteArticles(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "ID");

		// Kiểm tra độ dài của hai mảng
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				String id = excelHelper.getCellData("ID", i);
				String url = excelHelper.getCellData("URL", i);
				DriverManager.getDriver().get(id);
				if (isPageReady()) {
					boolean urlTBFetched = false;
					String urlTBValue = null;

					try {
						// Sử dụng WebDriverWait để chờ element 'urlTB' nếu nó tồn tại
						WebElement urlTBElement = wait.until(ExpectedConditions.visibilityOf(urlTB));
						urlTBValue = urlTBElement.getAttribute("value");
						urlTBFetched = true; // Đánh dấu rằng đã lấy được giá trị của urlTB
					} catch (NoSuchElementException | TimeoutException e) {
						LogUtils.warn("urlTB element not found, proceeding with null url");
					}

					// Kiểm tra điều kiện dựa trên giá trị của url hoặc nếu không tìm thấy urlTB
					if (Objects.equals(url, urlTBValue) || urlTBFetched) {
						LogUtils.info(url + " " + id);
						clickTrashBTN();
						sleep(3);
						LogUtils.info("Deleted");
						excelHelper.setCellData("Passed", "RESULT", i);
						LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
						sleep(1);
					} else {
						LogUtils.info(url + " " + id);
						LogUtils.info("Not delete");
					}
				}
			}
		}
	}


//			if(isPageReady()){
//				System.out.println("vao` day");
//
//				boolean urlTBFetched = false;
//				String urlTBValue = null;
//
//				try {
//					// Sử dụng WebDriverWait để chờ element 'urlTB' nếu nó tồn tại
//					WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
//					WebElement urlTBElement = wait.until(ExpectedConditions.visibilityOf(urlTB));
//					urlTBValue = urlTBElement.getAttribute("value");
//					urlTBFetched = true; // Đánh dấu rằng đã lấy được giá trị của urlTB
//				} catch (NoSuchElementException e) {
//					LogUtils.warn("urlTB element not found, proceeding with null url");
//				}
//
//				if (Objects.equals(url, urlTB.getAttribute("value")) || Objects.isNull(url)) {
//					LogUtils.info(url + " " + id);
//					clickTrashBTN();
//					LogUtils.info("Deleted");
//				} else {
//					LogUtils.info(url + " " + id);
//					LogUtils.info("Not delete");
//				}
//			}
//
//			try {
//				if (isPageReady()) {
//					LogUtils.info(url + " " + id);
//					LogUtils.info("Page not exit");
//				}
//			} catch (NoSuchElementException e) {
//				System.out.println("vao day");
//				if (Objects.equals(url, urlTB.getAttribute("value"))) {
//					LogUtils.info(url + " " + id);
//					clickTrashBTN();
//					LogUtils.info("Deleted");
//				} else {
//					LogUtils.info(url + " " + id);
//					LogUtils.info("Not delete");
//				}
//			}


	// get and extract data

	public void getDataSampleDetail(String fileName, String sheetName) {
		DriverManager.getDriver().get("https://yeti-cms.dev/yeti/main/samples/edit/173");
		System.out.println(getNameTB());
		excelHelper.setExcelFile(fileName, sheetName);
		excelHelper.setCellData(getNameTB(), "NAME", 1);
	}

	public void getDataWriterReview(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "URL");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i) && checkPageNotFound()) {

				DriverManager.getDriver().get(excelHelper.getCellData("URL", i));
				excelHelper.setExcelFile(fileName, sheetName);
				excelHelper.setCellData(getNameTB(), "NAME", i);
				excelHelper.setCellData(DownloadImage(), "FILE_NAME", i);
				excelHelper.setCellData(getDegreeTB(), "DEGREE", i);
				excelHelper.setCellData(getCityTB(), "CITY", i);
				excelHelper.setCellData(getBioTB(), "BIO", i);
				excelHelper.setCellData(getComOrderTB(), "COMPLETED_ORDER", i);
				excelHelper.setCellData(getAchiTB(), "ACHIEVEMENT", i);
				excelHelper.setCellData(getDisciplines(), "DISCIPLINES", i);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());

			}
		}
	}

	public void getDataCustomerReview(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "URL");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i) && checkPageNotFound()) {
				DriverManager.getDriver().get(excelHelper.getCellData("URL", i));
				excelHelper.setExcelFile(fileName, sheetName);

				excelHelper.setCellData(getNameTB(), "NAME", i);
				excelHelper.setCellData(getCollegeTB(), "COLLEGE", i);
				excelHelper.setCellData(getTextTB(), "TEXT", i);
				excelHelper.setCellData(getSourceTB(), "SOURCE_LINKED", i);
				excelHelper.setCellData(getRatingTB(), "RATING", i);
				excelHelper.setCellData(getTypeOfPaperTB(), "TYPE_OF_PAPER", i);
				excelHelper.setCellData(getIsFeaturable(), "IS_FEATURABLE", i);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());

			}
		}
	}

	//Testing
	public void checkContentNoteEdit(String content, String serviceUrl, String type) throws IOException {

		// Sử dụng Jsoup để phân tích nội dung HTML
		Document doc = Jsoup.parse(content);

		// Mở file Excel hiện có thay vì tạo mới Workbook
		FileInputStream fileInputStream = new FileInputStream(fileName);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(dataServicePage);

		if (sheet == null) {
			// Nếu sheet không tồn tại, tạo mới sheet
			sheet = workbook.createSheet(dataServicePage);
		}

		int rowNum = sheet.getLastRowNum() + 1; // Tiếp tục ghi từ dòng cuối cùng

		Elements elements = doc.body().children();
		int order = 1;
		for (Element element : elements) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serviceUrl);  // Ghi URL
			row.createCell(1).setCellValue("Edit intro");  // Ghi NoteEditableElement index
			row.createCell(2).setCellValue(order++);  // Ghi Order

			Cell typeCell = row.createCell(3);
			Cell contentCell = row.createCell(4);

			// Kiểm tra loại thẻ và ghi vào Excel
			switch (element.tagName()) {
				case "h1":
					typeCell.setCellValue("h1");
					contentCell.setCellValue(element.text());
					break;
				case "h2":
					typeCell.setCellValue("h2");
					contentCell.setCellValue(element.text());
					break;
				case "h3":
					typeCell.setCellValue("h3");
					contentCell.setCellValue(element.text());
					break;
				case "h4":
					typeCell.setCellValue("h4");
					contentCell.setCellValue(element.text());
					break;
				case "h5":
					typeCell.setCellValue("h5");
					contentCell.setCellValue(element.text());
					break;
				case "p":
					typeCell.setCellValue("p");
					contentCell.setCellValue(element.text());
					break;
				case "ul":
				case "ol":
					typeCell.setCellValue("List");
					// Xử lý các mục danh sách
					Elements listItems = element.getElementsByTag("li");
					for (Element li : listItems) {
						row = sheet.createRow(rowNum++);
						row.createCell(0).setCellValue(serviceUrl);
						row.createCell(1).setCellValue("Edit Intro");
						row.createCell(2).setCellValue(order++);
						row.createCell(3).setCellValue("List Item");
						row.createCell(4).setCellValue(li.text());
					}
					break;
				case "br":
					// Nếu gặp thẻ <br>, tạo một dòng trống trong file Excel
					typeCell.setCellValue("Line Break");
					contentCell.setCellValue("");  // Dòng trống
					break;
				case "blockquote":
					typeCell.setCellValue("blockquote");
					contentCell.setCellValue(element.text());
					break;
				case "pre":
					typeCell.setCellValue("pre");
					contentCell.setCellValue(element.text());
					break;
				default:
					typeCell.setCellValue("Other");
					contentCell.setCellValue(element.text());
					break;
			}
		}
		// Đóng fileInputStream trước khi ghi file
		fileInputStream.close();

		// Ghi file Excel đã sửa
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			workbook.write(outputStream);
		}

		// Đóng workbook
		workbook.close();

	}

	public void extractDataIntro(String serviceUrl) throws IOException {
		// Lấy nội dung HTML của element
		clickEditIntroBTN();
		if (!noteEditableElement.isDisplayed()) {
			return;
		}
		String content = noteEditableElement.getAttribute("innerHTML");

		// Lấy tất cả các thẻ từ trong div
		checkContentNoteEdit(content, serviceUrl, "Edit intro");

		LogUtils.info("Data has been written to Excel file: " + fileName);
	}

	public void extractDataContent(String serviceUrl) throws IOException {
		// Lấy nội dung HTML của element
		clickEditContentBTN();
		if (isNoteNotFound()) {
			return;
		}
		String content = noteEditableElement.getAttribute("innerHTML");

		// Sử dụng Jsoup để phân tích nội dung HTML
		Document doc = Jsoup.parse(content);

		// Mở file Excel hiện có thay vì tạo mới Workbook
		FileInputStream fileInputStream = new FileInputStream(fileName);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(dataServicePage);

		if (sheet == null) {
			// Nếu sheet không tồn tại, tạo mới sheet
			sheet = workbook.createSheet(dataServicePage);
		}

		int rowNum = sheet.getLastRowNum() + 1; // Tiếp tục ghi từ dòng cuối cùng

		// Lấy tất cả các thẻ từ trong div
		Elements elements = doc.body().children();
		int order = 1;
		for (Element element : elements) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serviceUrl);  // Ghi URL
			row.createCell(1).setCellValue("Content");  // Ghi NoteEditableElement index
			row.createCell(2).setCellValue(order++);  // Ghi Order

			Cell typeCell = row.createCell(3);
			Cell contentCell = row.createCell(4);

			// Kiểm tra loại thẻ và ghi vào Excel
			switch (element.tagName()) {
				case "h1":
				case "h2":
				case "h3":
					typeCell.setCellValue("Header");
					contentCell.setCellValue(element.text());
					break;
				case "p":
					typeCell.setCellValue("Paragraph");
					contentCell.setCellValue(element.text());
					break;
				case "ul":
				case "ol":
					typeCell.setCellValue("List");
					// Xử lý các mục danh sách
					Elements listItems = element.getElementsByTag("li");
					for (Element li : listItems) {
						row = sheet.createRow(rowNum++);
						row.createCell(0).setCellValue(serviceUrl);
						row.createCell(1).setCellValue("Edit Intro");
						row.createCell(2).setCellValue(order++);
						row.createCell(3).setCellValue("List Item");
						row.createCell(4).setCellValue(li.text());
					}
					break;
				case "br":
					// Nếu gặp thẻ <br>, tạo một dòng trống trong file Excel
					typeCell.setCellValue("Line Break");
					contentCell.setCellValue("");  // Dòng trống
					break;
				default:
					typeCell.setCellValue("Other");
					contentCell.setCellValue(element.text());
					break;
			}
		}

		// Đóng fileInputStream trước khi ghi file
		fileInputStream.close();

		// Ghi file Excel đã sửa
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			workbook.write(outputStream);
		}

		// Đóng workbook
		workbook.close();

		LogUtils.info("Data has been written to Excel file: " + fileName);
	}

	public void extractDataFAQBanner(String serviceUrl) throws IOException {
		// Lấy nội dung HTML của element
		clickEditFAQBannerBTN();
		if (isNoteNotFound()) {
			return;
		}
		String content = noteEditableElement.getAttribute("innerHTML");

		// Sử dụng Jsoup để phân tích nội dung HTML
		Document doc = Jsoup.parse(content);

		// Mở file Excel hiện có thay vì tạo mới Workbook
		FileInputStream fileInputStream = new FileInputStream(fileName);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(dataServicePage);

		if (sheet == null) {
			// Nếu sheet không tồn tại, tạo mới sheet
			sheet = workbook.createSheet(dataServicePage);
		}

		int rowNum = sheet.getLastRowNum() + 1; // Tiếp tục ghi từ dòng cuối cùng

		// Lấy tất cả các thẻ từ trong div
		Elements elements = doc.body().children();
		int order = 1;
		for (Element element : elements) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serviceUrl);  // Ghi URL
			row.createCell(1).setCellValue("FAQ Banner");  // Ghi NoteEditableElement index
			row.createCell(2).setCellValue(order++);  // Ghi Order

			Cell typeCell = row.createCell(3);
			Cell contentCell = row.createCell(4);

			// Kiểm tra loại thẻ và ghi vào Excel
			switch (element.tagName()) {
				case "h1":
				case "h2":
				case "h3":
					typeCell.setCellValue("Header");
					contentCell.setCellValue(element.text());
					break;
				case "p":
					typeCell.setCellValue("Paragraph");
					contentCell.setCellValue(element.text());
					break;
				case "ul":
				case "ol":
					typeCell.setCellValue("List");
					// Xử lý các mục danh sách
					Elements listItems = element.getElementsByTag("li");
					for (Element li : listItems) {
						row = sheet.createRow(rowNum++);
						row.createCell(0).setCellValue(serviceUrl);
						row.createCell(1).setCellValue("Edit Intro");
						row.createCell(2).setCellValue(order++);
						row.createCell(3).setCellValue("List Item");
						row.createCell(4).setCellValue(li.text());
					}
					break;
				case "br":
					// Nếu gặp thẻ <br>, tạo một dòng trống trong file Excel
					typeCell.setCellValue("Line Break");
					contentCell.setCellValue("");  // Dòng trống
					break;
				default:
					typeCell.setCellValue("Other");
					contentCell.setCellValue(element.text());
					break;
			}
		}

		// Đóng fileInputStream trước khi ghi file
		fileInputStream.close();

		// Ghi file Excel đã sửa
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			workbook.write(outputStream);
		}

		// Đóng workbook
		workbook.close();

		LogUtils.info("Data has been written to Excel file: " + fileName);
	}

	public void extractDataFAQ(String serviceUrl) throws IOException {
		// Lấy nội dung HTML của element
		clickEditFAQBTN();
		if (isNoteNotFound()) {
			return;
		}
		String content = noteEditableElement.getAttribute("innerHTML");

		// Sử dụng Jsoup để phân tích nội dung HTML
		Document doc = Jsoup.parse(content);

		// Mở file Excel hiện có thay vì tạo mới Workbook
		FileInputStream fileInputStream = new FileInputStream(fileName);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(dataServicePage);

		if (sheet == null) {
			// Nếu sheet không tồn tại, tạo mới sheet
			sheet = workbook.createSheet(dataServicePage);
		}

		int rowNum = sheet.getLastRowNum() + 1; // Tiếp tục ghi từ dòng cuối cùng

		// Lấy tất cả các thẻ từ trong div
		Elements elements = doc.body().children();
		int order = 1;
		for (Element element : elements) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serviceUrl);  // Ghi URL
			row.createCell(1).setCellValue("FAQ");  // Ghi NoteEditableElement index
			row.createCell(2).setCellValue(order++);  // Ghi Order

			Cell typeCell = row.createCell(3);
			Cell contentCell = row.createCell(4);

			// Kiểm tra loại thẻ và ghi vào Excel
			switch (element.tagName()) {
				case "h1":
				case "h2":
				case "h3":
					typeCell.setCellValue("Header");
					contentCell.setCellValue(element.text());
					break;
				case "p":
					typeCell.setCellValue("Paragraph");
					contentCell.setCellValue(element.text());
					break;
				case "ul":
				case "ol":
					typeCell.setCellValue("List");
					// Xử lý các mục danh sách
					Elements listItems = element.getElementsByTag("li");
					for (Element li : listItems) {
						row = sheet.createRow(rowNum++);
						row.createCell(0).setCellValue(serviceUrl);
						row.createCell(1).setCellValue("Edit Intro");
						row.createCell(2).setCellValue(order++);
						row.createCell(3).setCellValue("List Item");
						row.createCell(4).setCellValue(li.text());
					}
					break;
				case "br":
					// Nếu gặp thẻ <br>, tạo một dòng trống trong file Excel
					typeCell.setCellValue("Line Break");
					contentCell.setCellValue("");  // Dòng trống
					break;
				default:
					typeCell.setCellValue("Other");
					contentCell.setCellValue(element.text());
					break;
			}
		}

		// Đóng fileInputStream trước khi ghi file
		fileInputStream.close();

		// Ghi file Excel đã sửa
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			workbook.write(outputStream);
		}

		// Đóng workbook
		workbook.close();

		LogUtils.info("Data has been written to Excel file: " + fileName);
	}

	public void extractDataOffer(String serviceUrl) throws IOException {
		// Lấy nội dung HTML của element
		clickEditOfferBTN();
		if (isNoteNotFound()) {
			return;
		}
		String content = noteEditableElement.getAttribute("innerHTML");

		// Sử dụng Jsoup để phân tích nội dung HTML
		Document doc = Jsoup.parse(content);

		// Mở file Excel hiện có thay vì tạo mới Workbook
		FileInputStream fileInputStream = new FileInputStream(fileName);
		Workbook workbook = WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet(dataServicePage);

		if (sheet == null) {
			// Nếu sheet không tồn tại, tạo mới sheet
			sheet = workbook.createSheet(dataServicePage);
		}

		int rowNum = sheet.getLastRowNum() + 1; // Tiếp tục ghi từ dòng cuối cùng

		// Lấy tất cả các thẻ từ trong div
		Elements elements = doc.body().children();
		int order = 1;
		for (Element element : elements) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serviceUrl);  // Ghi URL
			row.createCell(1).setCellValue("Offer");  // Ghi NoteEditableElement index
			row.createCell(2).setCellValue(order++);  // Ghi Order

			Cell typeCell = row.createCell(3);
			Cell contentCell = row.createCell(4);

			// Kiểm tra loại thẻ và ghi vào Excel
			switch (element.tagName()) {
				case "h1":
				case "h2":
				case "h3":
					typeCell.setCellValue("Header");
					contentCell.setCellValue(element.text());
					break;
				case "p":
					typeCell.setCellValue("Paragraph");
					contentCell.setCellValue(element.text());
					break;
				case "ul":
				case "ol":
					typeCell.setCellValue("List");
					// Xử lý các mục danh sách
					Elements listItems = element.getElementsByTag("li");
					for (Element li : listItems) {
						row = sheet.createRow(rowNum++);
						row.createCell(0).setCellValue(serviceUrl);
						row.createCell(1).setCellValue("Edit Intro");
						row.createCell(2).setCellValue(order++);
						row.createCell(3).setCellValue("List Item");
						row.createCell(4).setCellValue(li.text());
					}
					break;
				case "br":
					// Nếu gặp thẻ <br>, tạo một dòng trống trong file Excel
					typeCell.setCellValue("Line Break");
					contentCell.setCellValue("");  // Dòng trống
					break;
				default:
					typeCell.setCellValue("Other");
					contentCell.setCellValue(element.text());
					break;
			}
		}

		// Đóng fileInputStream trước khi ghi file
		fileInputStream.close();

		// Ghi file Excel đã sửa
		try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
			workbook.write(outputStream);
		}

		// Đóng workbook
		workbook.close();

		LogUtils.info("Data has been written to Excel file: " + fileName);
	}

	//
	public void extractMultipleServicesDataToExcel(String urlExcelPath, String urlSheetName, String dataExcelFilePath) throws IOException {
		// Đọc URL từ file Excel
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile(urlExcelPath, urlSheetName);
		List<String> serviceUrls = excelHelper.getServiceUrls(); // Đọc danh sách URL
		excelHelper.close();

		// Khởi tạo Workbook và Sheet trong file Excel để ghi dữ liệu
//		Workbook wb = new XSSFWorkbook();
//		Sheet sh = workbook.createSheet("Service Data");

		int rowNum = 0;
//		extractDataToExcel(dataExcelFilePath,);
		// Thêm tiêu đề cột để phân biệt rõ loại và nội dung
		Row headerRow = sh.createRow(rowNum++);
		headerRow.createCell(0).setCellValue("Service URL");
		headerRow.createCell(1).setCellValue("NoteEditableElement");
		headerRow.createCell(2).setCellValue("Order");
		headerRow.createCell(3).setCellValue("Element Type");
		headerRow.createCell(4).setCellValue("Content");

		// Duyệt qua danh sách URL
		for (String url : serviceUrls) {
			DriverManager.getDriver().get(url);  // Mở trang dịch vụ

			// Lấy dữ liệu từ các phần tử noteEditableElement
//			rowNum = extractNoteEditableElementDataToExcel(sheet, url, "Element 1", noteEditableElement1, rowNum);
//			rowNum = extractNoteEditableElementDataToExcel(sheet, url, "Element 2", noteEditableElement2, rowNum);
//			rowNum = extractNoteEditableElementDataToExcel(sheet, url, "Element 3", noteEditableElement3, rowNum);
//			rowNum = extractNoteEditableElementDataToExcel(sheet, url, "Element 4", noteEditableElement4, rowNum);
//			rowNum = extractNoteEditableElementDataToExcel(sheet, url, "Element 5", noteEditableElement5, rowNum);
		}

		// Ghi dữ liệu vào file Excel
		try (FileOutputStream outputStream = new FileOutputStream(dataExcelFilePath)) {
			wb.write(outputStream);
		}

		// Đóng workbook
		wb.close();
		System.out.println("Data has been written to Excel file: " + dataExcelFilePath);
	}

	// Hàm con để trích xuất dữ liệu từ một element noteEditable
	private int extractNoteEditableElementDataToExcel(Sheet sheet, String serviceUrl, String element, WebElement noteEditableElement, int rowNum) throws IOException {
		// Lấy nội dung HTML của element
		String content = noteEditableElement.getAttribute("innerHTML");

		// Sử dụng Jsoup để phân tích nội dung HTML
		Document doc = Jsoup.parse(content);
		Elements elements = doc.body().children();
		int order = 1;

		for (Element htmlElement : elements) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(serviceUrl); // Ghi URL dịch vụ
			row.createCell(1).setCellValue(element); // Ghi thông tin phần tử noteEditableElement
			row.createCell(2).setCellValue(order++); // Ghi thứ tự

			// Kiểm tra loại thẻ và ghi vào Excel
			Cell typeCell = row.createCell(3);
			Cell contentCell = row.createCell(4);

			switch (htmlElement.tagName()) {
				case "h1":
				case "h2":
				case "h3":
					typeCell.setCellValue("Header");
					contentCell.setCellValue(htmlElement.text());
					break;
				case "p":
					typeCell.setCellValue("Paragraph");
					contentCell.setCellValue(htmlElement.text());
					break;
				case "ul":
				case "ol":
					typeCell.setCellValue("List");
					Elements listItems = htmlElement.getElementsByTag("li");
					for (Element li : listItems) {
						Row listRow = sheet.createRow(rowNum++);
						listRow.createCell(0).setCellValue(serviceUrl);
						listRow.createCell(1).setCellValue(element);
						listRow.createCell(2).setCellValue(order++);
						listRow.createCell(3).setCellValue("List Item");
						listRow.createCell(4).setCellValue(li.text());
					}
					break;
				case "br":
					typeCell.setCellValue("Line Break");
					contentCell.setCellValue("");
					break;
				default:
					typeCell.setCellValue("Other");
					contentCell.setCellValue(htmlElement.text());
					break;
			}
		}
		return rowNum;
	}

	//Final
	public void getDataServicePage(String fileName, String urlExcelSheet, String dataServicePage) throws IOException {
		excelHelper.setExcelFile(fileName, urlExcelSheet);
		this.fileName = fileName;
		this.dataServicePage = dataServicePage;

		int lastRow = ExcelHelper.getLastRowWithData(fileName, urlExcelSheet, "URL");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, urlExcelSheet, i) && checkPageNotFound()) {

				String currentUrl = excelHelper.getCellData("URL", i);
				DriverManager.getDriver().get(currentUrl);
				excelHelper.setExcelFile(fileName, dataServicePage);
				extractDataIntro(currentUrl);
				extractDataContent(currentUrl);
				extractDataFAQBanner(currentUrl);
				extractDataFAQ(currentUrl);
				extractDataOffer(currentUrl);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());

			}
		}

	}

	//Test service
	public void testGetDataServicePage(String fileName, String urlExcelSheet, String dataServicePage) throws IOException {
		excelHelper.setExcelFile(fileName, urlExcelSheet);
		this.fileName = fileName;
		this.dataServicePage = dataServicePage;

		int lastRow = ExcelHelper.getLastRowWithData(fileName, urlExcelSheet, "URL");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, urlExcelSheet, i) && checkPageNotFound()) {

				String currentUrl = excelHelper.getCellData("URL", i);
				DriverManager.getDriver().get(currentUrl);
				excelHelper.setExcelFile(fileName, dataServicePage);
				extractDataIntro(currentUrl);
				extractDataContent(currentUrl);
				extractDataFAQBanner(currentUrl);
				extractDataFAQ(currentUrl);
				extractDataOffer(currentUrl);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());

			}
		}

	}


	// Simple test
	public void simpleTest(String fileName, String sheetName) {
		DriverManager.getDriver().get("https://yeti-cms.dev/yeti/main/articles/edit/175");
		String text = "<h3>Here is how to use our writing service</h3><p>Our ordering process is easy, and you can buy an internal assessment within a few minutes. However, there are some insights we would like to share with you before getting started. As an IB writing service provider with experience working with students from around the world, including Qatar, Turkey, India, the United Kingdom, and other countries, we’ve noticed that some prefer to simply share the rubric, while others pay more attention to details. That’s why we’ve designed a professional online order form to meet the expectations of all our clients.</p><h4>1. Complete the order form</h4><p>The most important step is providing us with the initial information. Be clear, choose the right data, and give us insights and detailed instructions.</p><p><b>Level of writing</b>: For <b>SL</b>, we recommend choosing the '<u>high school</u>' level. For <b>HL</b>, it’s better to select the '<u>IB student</u>' level.</p><p><b>Choose a discipline</b>: There are multiple IB disciplines, ranging from the most complex to less challenging. We can work on most of them, including: Math, SEHS, Physics, Chemistry, Biology, ESS, Business, Computer Science, Economics, Global Politics, History, English, Digital Society, Psychology, World Studies, Design Technology. For the ToK essays, there are areas of knowledge (AoK) and ways of knowledge (WoK) we can work on them all.</p><p><b>Write instructions</b>: The instructions section is designed to establish communication with your IB writer and provide them with a starting point. You can specify whether you want them to write an extended essay from scratch or edit an existing one based on your tutor’s feedback. Share general recommendations, suggest a research topic of interest, and include your own suggestions. You can also share your personal reading list beyond the classroom syllabus to help the writer better connect with your ideas and personality.</p><p><b>Deadline selection</b>: When you want to buy an IB IA, you can choose your preferred deadline when ordering online. Our company offers the flexibility to select a deadline that suits you, ranging from urgent orders starting at 5 hours to longer deadlines up to 30 days.</p><p><b>Citation style</b>: To avoid any plagiarism issues and ensure your IB assessment is properly formatted, you can select the required citation style. Our IB writers are familiar with all major academic formats, including APA, MLA, In-text citation, Footnotes, Harvard, and others.</p><p><b>Number of words</b>: Each type of IB assessment (EE, IA, TOK) has its own set of criteria, so it’s crucial to review the rubrics provided by your teachers and ensure the required word count for each essay. When you want to buy <i>extended essay</i>, be sure to pay for 15 pages, which equals 4,000 words. </p><p>For an <i>internal assessment</i>, the minimum number of pages is 12, as it often requires additional components such as calculations, graphs, and images. Our IB writer will ensure the word count meets the required 2,200 words. </p><p>For the <i>TOK essay</i> writing service, we recommend paying for 6 pages, which is approximately 1,600 words.</p><h4>2. Make your payment</h4><p>The final step of the ordering process is the payment for your IB essay. You can choose the most convenient way to pay on our website. Our company accepts PayPal, all major credit cards, and Apple Pay. You can pay in full or in installments. To ensure a safe and legal IB writing services, we guarantee a full money-back refund in case of dissatisfaction or other issues, in accordance with our policy.</p><h4>3. We match you with an expert IB writer</h4><p>After completing your order and payment for your IB essay, our company will match you with a professional academic writer. This could be an IB alumnus, an IB tutor, or a freelance academic writer familiar with the IB curriculum who has written many high-scoring assessments in the past. We prioritize confidentiality and recommend not sharing your real name, email, WhatsApp, or other personal data. Our company ensures that the assigned writer is an expert in your specific discipline, ready to meet your deadline—even if it’s urgent—and available 24/7 to answer your questions, share drafts, or discuss research topics. We guarantee that your assessment will be written from scratch, entirely plagiarism-free, by an experienced expert.</p><h4>4. Receive final essay</h4><p>Once your assessment is ready, it will be emailed to you directly and available for download in your personal account on our website. If any corrections are needed based on your teacher’s feedback, you can request free revisions an unlimited number of times within 30 days after submission, right from your personal account. Be sure to follow our revision policy, which requires that you do not change the topic if it was initially approved or provide new information that wasn’t requested at the start. This is why filling out the order form carefully and maintaining clear communication with specific instructions is so important.</p></div>";
		setEditIntroData(text);
		clickSaveBTN();
		sleep(5);
	}
}