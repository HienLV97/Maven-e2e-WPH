package CMS.CreateData.pages;

import Keywords.WebUI;
import Support.CMS.Routers;
import Support.Initialization.Init;
import helpers.ExcelHelper;
import logs.LogUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Hashtable;
import java.util.Objects;

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

	@FindBy(xpath = "//*[@name='type']")
	WebElement paperTypeTB;

	@FindBy(xpath = "//*[@name='disciplines']")
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

	@FindBy(xpath = "//img[@id='author-preview']")
	static WebElement imgElement;

	@FindBy(xpath = "//div[contains(text(),'Sorry, the page you are looking for could not be found')]")
	WebElement pageNotFoundMessage;

	@FindBy(xpath = "//*[@name='college']")
	WebElement collegeTB;

	@FindBy(xpath = "//textarea[@name='text']")
	WebElement textTB;

	@FindBy(xpath = "//select[@name='source']")
	WebElement SourceType;

	@FindBy(xpath = "//select[@name='rating']")
	WebElement ratingType;

	@FindBy(xpath = "//input[@name='paper_type']")
	WebElement typePaperTB;

	@FindBy(xpath = "//input[@name='featurable']")
	WebElement isFeaturable;

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
		return WebUI.getValue(achievementTB);
	}

	public String getAchiTB() {
		return WebUI.getValue(achievementTB);
	}

	public String getDisciplines() {
		return WebUI.getValue(disciplineTB);
	}

	public String getCollegeTB() {
		return WebUI.getValue(collegeTB);
	}

	public String getTextTB() {
		return WebUI.getText(textTB);
	}

	public String getSourceTB(){
		return WebUI.getValue(SourceType);
	}

	public String getRatingTB(){
		return WebUI.getValue(ratingType);
	}

	public String getTypePaperTB(){
		return WebUI.getValue(typePaperTB);
	}

	public String getIsFeaturable(){
		 String checked = WebUI.getValue(isFeaturable);
		 if (Objects.equals(checked,"1")){
			 return "yes";
		 } else {
			 return "no";
		 }
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

	public void setSampleDRL(String fileName, String sheetNameDetail) {
		clickSampleDRL();
		excelHelper.setExcelFile(fileName, sheetNameDetail);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetNameDetail, "name");
		for (int i = 1; i <= lastRow; i++) {
			String nameDetail = excelHelper.getCellData("name", i);
			String paperType = excelHelper.getCellData("type_of_paper", i);
			clickOnArticle(nameDetail, paperType);
			sleep(1);
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
		WebElement article = driver.findElement(By.xpath(xpath));
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
				LogUtils.info("Page not found");
				return true;
			}
		} catch (Exception e) {
			// Nếu không tìm thấy element, nghĩa là trang không bị lỗi 404
			LogUtils.info("Page is valid");
		}
		return false;
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

	public void createSampleDetail(String fileName, String sheetName) throws Exception {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "name");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
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

				sleep(5);
				clickSaveBTN();
				sleep(2);
				clickSaveBTN();
				recordFile(driver.getCurrentUrl(), "id");
				recordFile(url, "url");
				LogUtils.infoCustom(driver.getCurrentUrl());
				LogUtils.infoCustom(url);
				excelHelper.setCellData("Passed", "result", i);
			}
		}
	}

	public void createSampleDetailNotSave(String fileName, String sheetName) throws Exception {
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

//				sleep(5);
//				clickSaveBTN();
//				sleep(2);
//				clickSaveBTN();
//				recordFile(driver.getCurrentUrl(), "id");
//				recordFile(url, "url");

				LogUtils.infoCustom(driver.getCurrentUrl());
				LogUtils.infoCustom(url);
				excelHelper.setCellData("Passed", "RESULT", i);
				if (i == 3) {
					WebUI.assertEquals(1, 2);
				}
			}
		}
	}

	public void createSampleDetailTest(Hashtable<String, String> data) throws Exception {
		if (checkResultTest(data.get("RESULT"))) {
			createSample();
			setNameTB(data.get("NAME"));
			setUrlTB(data.get("URL"));
			setMetaTitleSec(data.get("META_TITLE"));
			setMetaDesTB(data.get("META_DESCRIPTION"));
			setShortIntroTB(data.get("SHORT_INTRO"));
			setCreatedDateTB(data.get("CREATE_DATA"));
			setAcademicTB(data.get("ACADEMIC"));
			setPaperTypeTB(data.get("TYPE_OF_PAPER"));
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
//			sleep(5);
			System.out.println("Done");
		}
	}

	public void createSamplesArticles(String fileName, String sheetName, String sheetNameDetail) throws Exception {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "name");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
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
				String editIntro = excelHelper.getCellData("edit_intro", i);
				String editOffer = excelHelper.getCellData("edit_offer", i);

//				createArticles();
//				selectArticle("samples");
//				setNameTB(name);
//				setUrlTB(url);
//				setMetaTitleSec(metaTitle);
//				setMetaDesTB(description);
//				setAnchorTB(anchor);
//				setTitleTB(title);
//				setEssayNoteTB(essayNote);
//				setEssayActTB(essayAct);
//				setOfferActTB(offer);

				driver.get("https://yeti-cms.dev/yeti/main/articles/edit/166");

				ExcelHelper excelHelper2 = new ExcelHelper();
				excelHelper2.setExcelFile(fileName, sheetNameDetail);
				setSampleDRL(fileName, sheetNameDetail);

				clickSaveBTN();
				sleep(2);
				clickPublish();
				LogUtils.info(driver.getCurrentUrl());
				LogUtils.info(url);
				recordFile(driver.getCurrentUrl(), "id");
				recordFile(url, "url");
				setEditIntroData(editIntro);
				setEditOfferData(editOffer);
				excelHelper.setCellData("Passed", "result", i);
			}
		}
	}

	public void deleteArticles(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "id");
		// Kiểm tra độ dài của hai mảng
		for (int i = 1; i <= lastRow; i++) {
			String id = excelHelper.getCellData("id", i);
			String url = excelHelper.getCellData("url", i);
			driver.get(id);

			try {
				if (pageNotFoundTXT.isDisplayed()) {
					LogUtils.info(url + " " + id);
					LogUtils.info("Page not exit");
				}
			} catch (NoSuchElementException e) {
				if (Objects.equals(url, urlTB.getAttribute("value"))) {
					LogUtils.info(url + " " + id);
					clickTrashBTN();
					LogUtils.info("Deleted");
				} else {
					LogUtils.info(url + " " + id);
					LogUtils.info("Not delete");
				}
			}
		}
	}

	public void getDataSampleDetail(String fileName, String sheetName) {
		driver.get("https://yeti-cms.dev/yeti/main/samples/edit/173");
		System.out.println(getNameTB());
		excelHelper.setExcelFile(fileName, sheetName);
		excelHelper.setCellData(getNameTB(), "NAME", 1);
	}

	public String DownloadImage() {
		try {
			// Lấy URL của ảnh từ thẻ <img> bằng Selenium
			String imgUrl = imgElement.getAttribute("src");

			// Lấy tên file từ URL
			String fileName = Paths.get(new URL(imgUrl).getPath()).getFileName().toString()+".jpg";

			// Đặt đường dẫn tới thư mục cần lưu
			Path targetPath = new File("src/test/resources/Image/writerReview/"+fileName).toPath(); // Đường dẫn tới thư mục

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

	public void getDataWriterReview(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "URL");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i) && !checkPageNotFound()) {

				driver.get(excelHelper.getCellData("URL", i));
				excelHelper.setExcelFile(fileName, sheetName);
				excelHelper.setCellData(getNameTB(), "NAME", i);
				excelHelper.setCellData(DownloadImage(), "FILE_NAME", i);
				excelHelper.setCellData(getDegreeTB(), "DEGREE", i);
				excelHelper.setCellData(getCityTB(), "CITY", i);
				excelHelper.setCellData(getBioTB(), "BIO", i);
				excelHelper.setCellData(getComOrderTB(), "COMPLETED_ORDER", i);
				excelHelper.setCellData(getAchiTB(), "ACHIEVEMENT", i);
				excelHelper.setCellData(getDisciplines(), "DISCIPLINES", i);

				LogUtils.infoCustom(driver.getCurrentUrl());

			}
		}
	}
	public void getDataCustomerReview(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "URL");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i) && !checkPageNotFound()) {
				driver.get(excelHelper.getCellData("URL", i));
				excelHelper.setExcelFile(fileName, sheetName);

				excelHelper.setCellData(getNameTB(), "NAME", i);
				excelHelper.setCellData(getCollegeTB(), "COLLEGE", i);
				excelHelper.setCellData(getTextTB(), "TEXT", i);
				excelHelper.setCellData(getSourceTB(), "SOURCE_LINKED", i);
				excelHelper.setCellData(getRatingTB(), "RATING", i);
				excelHelper.setCellData(getTypePaperTB(), "TYPE_OF_PAPER", i);
				excelHelper.setCellData(getIsFeaturable(), "IS_FEATURABLE", i);

				LogUtils.infoCustom(driver.getCurrentUrl());

			}
		}
	}
}