package CMS.CreateData.pages;

import AcaWriting.Keywords.WebUI;
import AcaWriting.Support.CMS.Routers;
import Initialization.Init;
import helpers.drivers.DriverManager;
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
	private final WebDriverWait wait;
	ExcelHelper excelHelper = new ExcelHelper();
	SoftAssert softassert = new SoftAssert();
	private Workbook wb;
	private Sheet sh;
	private String fileName;
	private String dataServicePage;
	private String sheetHeaderData;

	@FindBy(xpath = "(//span[@class='project-name fbaloo'])[1]")
	WebElement WPHBTN;

	@FindBy(xpath = "(//span[@class='project-name fbaloo'])[3]")
	WebElement IBWBTN;

	@FindBy(xpath = "(//span[@class='project-name fbaloo'])[2]")
	WebElement IBHBTN;

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

	@FindBy(xpath = "(//input[@name='headline']/following-sibling::input)[1] | (//input[@name='Headline']/following-sibling::input)[2]")
	WebElement headlineTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[normalize-space()='Title']/following-sibling::div/input[@type='text']")
	WebElement titleTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[normalize-space()='Service title']/following-sibling::div/input[@type='text']")
	WebElement serviceTitleTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[normalize-space()='Support title']/following-sibling::div/input[@type='text']")
	WebElement supportTitleTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//textarea[@name='support_description']")
	WebElement supportDesTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[normalize-space()='Support time']/following-sibling::div/input[@type='text']")
	WebElement supportTimeTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='trust_note']/following-sibling::input[1]")
	WebElement trustNoteTB;

	@FindBy(xpath = "(//input[@name='sub_title']/following-sibling::input)[1] | (//input[@name='sub_title']/following-sibling::input)[2]")
	WebElement subTitleTB;

	@FindBy(xpath = "(//input[@name='intro']/following-sibling::input)[1] | (//input[@name='Intro']/following-sibling::input)[2]")
	WebElement introTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='feature_title']/following-sibling::input)[1] | (//input[@name='feature_title']/following-sibling::input)[2]")
	WebElement featureTitleTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='feature_description']/following-sibling::input)[1] | (//input[@name='feature_description']/following-sibling::input)[2]")
	WebElement featureDesTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[normalize-space(text())='Review Description']/following::textarea)[1]")
	WebElement reviewDesTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='download_title']/following-sibling::input)[1] | (//input[@name='download_title']/following-sibling::input)[2]")
	WebElement downloadTitleTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='download_description']/following-sibling::input)[1] | (//input[@name='download_description']/following-sibling::input)[2]")
	WebElement downloadDesTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='action']/following-sibling::input)[1] | (//input[@name='Action']/following-sibling::input)[2]")
	WebElement actionTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='target']/following-sibling::input)[1] | (//input[@name='Target']/following-sibling::input)[2]")
	WebElement targetTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='promo']/following-sibling::input)[1] | (//input[@name='Promo']/following-sibling::input)[2]")
	WebElement promoTB;

	@FindBy(xpath = "(//input[@name='perks_title']/following-sibling::input)[1] | (//input[@name='Perks title']/following-sibling::input)[2]")
	WebElement perksTitleTB;

	@FindBy(xpath = "(//input[@name='essay_note']/following-sibling::input)[1]")
	WebElement essayNoteTB;

	@FindBy(xpath = "(//input[@name='essay_action']/following-sibling::input)[1]")
	WebElement essayActTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[@class='control-label'][normalize-space()='Offer action']/following-sibling::div/input/following-sibling::input)")
	WebElement offerActTB;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//select[contains(@class,'multiSelect_field multiSelect_field_samples')]/following-sibling::div[1]")
	WebElement sampleDRL;

	@FindBy(xpath = " (//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//div[@class='multiSelect_dropdown multiSelect_dropdown_writers'])[1]")
	WebElement writersDRL;

	@FindBy(xpath = " (//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//div[@class='multiSelect_dropdown multiSelect_dropdown_reviews'])[1]")
	WebElement reviewsDRL;

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

	//	@FindBy(xpath = "(//label[normalize-space(text())='Writer Title']/following::input)[2]")
//	@FindBy(xpath = "//input[@id='js-0zbjP03ZW")
	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='writer_title']/following-sibling::input)[1]")
	WebElement writerTitleTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='writer_banner']/following-sibling::input)[1]")
	WebElement writerBannerTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))] //input[@name='writer_action']/following-sibling::input)[1] ")
	WebElement writerActionTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='goal_title']/following-sibling::input)[1] ")
	WebElement goalTitleTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[normalize-space(text())='Goal Description']/following::textarea)[1] ")
	WebElement goalDesTB;


	//	@FindBy(xpath = "//input[@name='offer_title']/following-sibling::input[@type='text']")
	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//label[@class='control-label'][normalize-space()='Offer title']/following-sibling::div/input/following-sibling::input)")
	WebElement offerTitleTB;

	@FindBy(xpath = "//input[@name='writer_description']/following-sibling::input[1]")
	WebElement writerDesTB;

	@FindBy(xpath = "(//input[@name='method_title']/following-sibling::input)[1]")
	WebElement methodTitleTB;

	@FindBy(xpath = "(//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))]//input[@name='review_title']/following-sibling::input)[1] |(//input[@name='Review_title']/following-sibling::input)[1] ")
	WebElement reviewTitleTB;

	//Header
	@FindBy(xpath = "//i[@class='fa fa-info fa-fw']")
	WebElement editIntroBTN;

	@FindBy(xpath = "//a[@title='Edit content']")
	WebElement editContentBTN;

	@FindBy(xpath = "//a[@title='Edit Offer']")
	WebElement editOfferBTN;

	@FindBy(xpath = "//a[@title='Edit FAQ Banner']")
	WebElement editFAQBannerBTN;

	@FindBy(xpath = "//a[@title='Edit Content Banner']")
	WebElement editContentBannerBTN;

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

	@FindBy(xpath = "//select[@name='priority']")
	WebElement priorityType;

	@FindBy(xpath = "//input[@name='paper_type' or @name='type']")
	WebElement typeOfPaperTB;

	@FindBy(xpath = " //i[@data-behavior='checkbox'] ")
	WebElement isFeaturable;

	@FindBy(xpath = "//div[contains(@class, 'js-article-fields') and not(contains(@class, 'hide'))] //input[@name='in_header']/following-sibling::i[1] ")
	WebElement isShowHeader;

	// Note editable
	private String noteEditable = "(//div[@role='textbox'])";

	private WebElement noteEditableElement(int index) {
		By option = By.xpath(noteEditable + "[" + index + "]");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(option));
	}

	private String tabIndex = "(//a[@data-toggle='tab'])";

	private WebElement tabIndexElement(int index) {
		By option = By.xpath(tabIndex + "[" + index + "]");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(option));
	}

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

	public void clickIBHBTN() {
		WebUI.clickWEBElement(IBHBTN);
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

	public void setProrityType(String type) {
		Select select = new Select(priorityType);
		select.selectByVisibleText(type.toLowerCase());
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

	public void setWriterTitleTB(String value) {
		WebUI.setText(writerTitleTB, value);
	}

	public void setWriterBannerTB(String value) {
		WebUI.setText(writerBannerTB, value);
	}

	public void setWriterActionTB(String value) {
		WebUI.setText(writerActionTB, value);
	}

	public void setGoalTitleTB(String value) {
		WebUI.setText(goalTitleTB, value);
	}

	public void setGoalDesTB(String value) {
		WebUI.setText(goalDesTB, value);
	}

	public void setOfferTitleTB(String value) {
		WebUI.setText(offerTitleTB, value);
	}

	public void setWriterDesTB(String value) {
		WebUI.setText(writerDesTB, value);
	}

	public void setMethodTitleTB(String value) {
		WebUI.setText(methodTitleTB, value);
	}

	public void setReviewTitleTB(String value) {
		WebUI.setText(reviewTitleTB, value);
	}

	public void setHeadlineTB(String value) {
		WebUI.setText(headlineTB, value);
	}

	public void clickShowHeader(String value) {
		if (Objects.equals(value, "yes")) {
			WebUI.clickWEBElement(isShowHeader);
		}
	}

	public void setTitleTB(String value) {
		WebUI.setText(titleTB, value);
	}

	public void setServiceTitleTB(String value) {
		WebUI.setText(serviceTitleTB, value);
	}

	public void setSupportTitleTB(String value) {
		WebUI.setText(supportTitleTB, value);
	}

	public void setSupportDesTB(String value) {
		WebUI.setText(supportDesTB, value);
	}
	public void setSupportTimeTB(String value) {
		WebUI.setText(supportTimeTB, value);
	}


	public void setTrustNoteTB(String value) {
		WebUI.setText(trustNoteTB, value);
	}

	public void setSubTitleTB(String value) {
		WebUI.setText(subTitleTB, value);
	}

	public void setIntroTB(String value) {
		WebUI.setText(introTB, value);
	}

	public void setFeatureTitleTB(String value) {
		WebUI.setText(featureTitleTB, value);
	}

	public void setFeatureDesTBTB(String value) {
		WebUI.setText(featureDesTB, value);
	}

	public void setReviewDesTBTB(String value) {
		WebUI.setText(reviewDesTB, value);
	}

	public void setDownloadDesTB(String value) {
		WebUI.setText(downloadDesTB, value);
	}

	public void setDownloadTitleTB(String value) {
		WebUI.setText(downloadTitleTB, value);
	}

	public void setActionTB(String value) {
		WebUI.setText(actionTB, value);
	}

	public void setTargetTB(String value) {
		WebUI.setText(targetTB, value);
	}

	public void setPromoTB(String value) {
		WebUI.setText(promoTB, value);
	}

	public void setPerksTitleTB(String value) {
		WebUI.setText(perksTitleTB, value);
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
			LogUtils.info("Checked: Yes");
			return "yes";
		} else {
			LogUtils.info("Checked: No");
			return "no";
		}
	}

	public void setNoteTB(String value, int index) {
		// Sử dụng JavaScript để lấy nội dung HTML hiện tại
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//		String currentText = (String) js.executeScript("return arguments[0].textContent;", noteEditableElement(index));
		WebUI.clickWEBElement(tabIndexElement(index));

// Thêm dữ liệu mới vào nội dung hiện tại của thẻ h1
//		String newText = currentText + value;
		LogUtils.info("Set NoteTB: " + value);

// Sử dụng JavaScript để set lại nội dung văn bản mới mà không dùng HTML
//		js.executeScript("arguments[0].textContent = arguments[1];", noteEditableElement(index), newText);
		js.executeScript("arguments[0].innerHTML = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", noteEditableElement(index), value);

		sleep(1);

	}

	public void setEditIntroData(String value, String NAME) {
		if (Objects.equals(value, "")) {
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickEditIntroBTN();
			excelHelper.setExcelFile(fileName, sheetHeaderData);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetHeaderData, "ID");
			int j = 1;
			for (int i = 1; i <= lastRow; i++) {
				String EDIT_INTRO = excelHelper.getCellData("EDIT_INTRO", i);
				String ID = excelHelper.getCellData("ID", i);
				if (Objects.equals(NAME, ID) && !Objects.equals(EDIT_INTRO, "")) {
					clickAddBTN();
					setNoteTB(EDIT_INTRO, j);
					sleep(1);
					clickSaveBTN();
					j++;
				}
			}
		} else {
			clickEditIntroBTN();
			clickAddBTN();
			setNoteTB(value, 1);
			sleep(1);
			clickSaveBTN();
		}
	}

	public void setEditOfferData(String value, String NAME) {
		if (Objects.equals(value, "")) {
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickEditOfferBTN();
			excelHelper.setExcelFile(fileName, sheetHeaderData);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetHeaderData, "ID");
			int j = 1;
			for (int i = 1; i <= lastRow; i++) {
				String EDIT_OFFER = excelHelper.getCellData("EDIT_OFFER", i);
				String ID = excelHelper.getCellData("ID", i);
				if (Objects.equals(NAME, ID) && !Objects.equals(EDIT_OFFER, "")) {
					clickAddBTN();
					setNoteTB(EDIT_OFFER, j);
					sleep(1);
					clickSaveBTN();
					j++;
				}
			}
		} else {
			clickEditOfferBTN();
			clickAddBTN();
			setNoteTB(value, 1);
			sleep(1);
			clickSaveBTN();
		}
	}

	public void setEditContentData(String value, String NAME) {
		if (Objects.equals(value, "")) {
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickEditContentBTN();
			excelHelper.setExcelFile(fileName, sheetHeaderData);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetHeaderData, "ID");
			int j = 1;
			for (int i = 1; i <= lastRow; i++) {
				String EDIT_CONTENT = excelHelper.getCellData("EDIT_CONTENT", i);
				String ID = excelHelper.getCellData("ID", i);
				if (Objects.equals(NAME, ID) && !Objects.equals(EDIT_CONTENT, "")) {
					clickAddBTN();
					setNoteTB(EDIT_CONTENT, j);
					sleep(1);
					clickSaveBTN();
					sleep(2);
					j++;
				}

			}
		} else {
			clickEditContentBTN();
			clickAddBTN();
			setNoteTB(value, 1);
			sleep(1);
			clickSaveBTN();
		}
	}

	public void setEditFAQBannerData(String value, String NAME) {
		if (Objects.equals(value, "")) {
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickEditFAQBannerBTN();
			LogUtils.info("value: " + value);
			LogUtils.info("NAME: " + NAME);
			excelHelper.setExcelFile(fileName, sheetHeaderData);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetHeaderData, "ID");
			int j = 1;
			for (int i = 1; i <= lastRow; i++) {
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String ID = excelHelper.getCellData("ID", i);
				System.out.println("EDIT_FAQ_BANNER: " + EDIT_FAQ_BANNER);
				if (Objects.equals(NAME, ID) && !Objects.equals(EDIT_FAQ_BANNER, "")) {
					LogUtils.info("vao` day");
					System.out.println("EDIT_FAQ_BANNER: " + EDIT_FAQ_BANNER);
					clickAddBTN();
					setNoteTB(EDIT_FAQ_BANNER, j);
					sleep(1);
					clickSaveBTN();
					j++;
				}
			}
		} else {
			clickEditFAQBannerBTN();
			clickAddBTN();
			setNoteTB(value, 1);
			sleep(1);
			clickSaveBTN();
		}
	}

	public void setEditContentBannerData(String value, String NAME) {
		if (Objects.equals(value, "")) {
			return;
		}
		clickEditContentBannerBTN();
		clickAddBTN();
		setNoteTB(value, 1);
		sleep(1);
		clickSaveBTN();

	}

	public void setEditFAQData(String value, String NAME) {
		if (Objects.equals(value, "")) {
			LogUtils.info("FAQ haven't data");
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickEditFAQBTN();
			excelHelper.setExcelFile(fileName, sheetHeaderData);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetHeaderData, "ID");
			int j = 1;
			for (int i = 1; i <= lastRow; i++) {
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);
				String ID = excelHelper.getCellData("ID", i);
				if (Objects.equals(NAME, ID) && !Objects.equals(EDIT_FAQ, "")) {
					clickAddBTN();
					setNoteTB(EDIT_FAQ, j);
					sleep(1);
					clickSaveBTN();
					j++;
				}
			}
		} else {
			clickEditFAQBTN();
			clickAddBTN();
			setNoteTB(value, 1);
			sleep(1);
			clickSaveBTN();
		}
	}

	public void clickSampleDRL() {
		WebUI.clickWEBElement(sampleDRL);
	}

	public void clickWritersDRL() {
		WebUI.clickWEBElement(writersDRL);
	}

	public void clickReviewsDRL() {
		WebUI.clickWEBElement(reviewsDRL);
	}

	public void setSampleDRL(String fileName, String sheetNameDetail) {
		clickSampleDRL();
		excelHelper.setExcelFile(fileName, sheetNameDetail);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetNameDetail, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			String NAME = excelHelper.getCellData("NAME", i);
			String TYPE_OF_PAPER = excelHelper.getCellData("TYPE_OF_PAPER", i);
			clickOnDRLValue(TYPE_OF_PAPER, NAME);
			sleep(1);
		}
	}

	public void setWritersDRL(String fileName, String sheetWriterSel, String value, String idValue) {
		if (Objects.isNull(value)) {
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickWritersDRL();
			excelHelper.setExcelFile(fileName, sheetWriterSel);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetWriterSel, "NAME");
			for (int i = 1; i <= lastRow; i++) {

				String NAME = excelHelper.getCellData("NAME", i);
				String DEGREE = excelHelper.getCellData("DEGREE", i);
				String CITY = excelHelper.getCellData("CITY", i);
				String BIO = excelHelper.getCellData("BIO", i);
				String ID = excelHelper.getCellData("ID", i);
				String prefix = NAME + " - " + DEGREE + " - " + CITY;
				System.out.println("prefix: " + prefix);
				if (Objects.equals(idValue, ID)) {
					clickOnDRLValue(prefix, BIO);
					sleep(1);
				}
			}
			WebUI.clickWEBElement(anchorTB);
			sleep(1);
			LogUtils.info("Done");
		} else {
			LogUtils.info("Invalid value");
		}
	}

	public void setReviewsDRL(String fileName, String sheetNameDetail, String value, String idValue) {
		if (Objects.isNull(value)) {
			return;
		}
		if (Objects.equals(value.toLowerCase(), "multi")) {
			clickReviewsDRL();
			excelHelper.setExcelFile(fileName, sheetNameDetail);
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetNameDetail, "NAME");
			for (int i = 1; i <= lastRow; i++) {

				String NAME = excelHelper.getCellData("NAME", i);
				String COLLEGE = excelHelper.getCellData("COLLEGE", i);
				String TEXT = excelHelper.getCellData("TEXT", i);
				String ID = excelHelper.getCellData("ID", i);
				String prefix = NAME + " - " + COLLEGE;
				System.out.println("prefix: " + prefix);
				if (Objects.equals(idValue, ID)) {
					clickOnDRLValue(prefix, TEXT);
					sleep(1);
				}
			}
			WebUI.clickWEBElement(anchorTB);
			sleep(0.5);
			LogUtils.info("Done");
		}
	}

	public void clickIsFeaturable(String value) {
		if (Objects.equals(value, "yes")) {
			WebUI.clickWEBElement(isFeaturable);
		}
	}

	// click on dropdownlist value
	public void clickOnDRLValue(String value1, String value2) {
		// Tạo XPath động với giá trị được truyền vào
		String value = value1 + " - " + value2;
		if (value.length() > 55) {
			value = value.substring(0, 55);  // Cắt chuỗi nếu vượt quá 60 ký tự
		}
		String xpath = "(//a[contains(normalize-space(text()), \"" + value + "\")])[1]";

		// Tìm phần tử với XPath động và thực hiện thao tác click
		WebElement article = DriverManager.getDriver().findElement(By.xpath(xpath));
		WebUI.clickWEBElement(article);
	}

	public void clickSaveBTN() {
		WebUI.clickWEBElement(saveBTN);
	}

	public void clickPublish(String value) {
		if (Objects.equals(value, "yes")) {
			WebUI.doubleClickElement(publishBTN);
			sleep(2);
		}
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

	public void clickEditContentBannerBTN() {
		WebUI.clickWEBElement(editContentBannerBTN);
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

	public void addArticle() {
		DriverManager.getDriver().get(Routers.ARTICLES);
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
				LogUtils.info("Page not found (404 error)");
				return true;
			}
		} catch (NoSuchElementException e) {
			// Nếu không tìm thấy element, nghĩa là trang không bị lỗi 404
			LogUtils.info("Page is valid");
		}
		return false;
	}

	public boolean isNoteNotFound() {
		try {
			// Kiểm tra xem element có hiện diện và hiển thị hay không
			if (noteEditableElement(1).isDisplayed()) {
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

			LogUtils.info("Ảnh đã được tải về và lưu với tên: " + fileName);
			return fileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void recordFile(String value, String column) {
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "sheet1";

		try {
			// Kiểm tra tệp có tồn tại không
			File file = new File(fileName);
			if (!file.exists()) {
				System.err.println("Tệp không tồn tại, tạo mới: " + fileName);

			}

			// Thiết lập file Excel
			excelHelper.setExcelFile(fileName, sheetName);

			// Lấy dòng cuối cùng có dữ liệu
			int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, column);
			lastRow = (lastRow == -1) ? 0 : lastRow + 1; // Nếu không có dữ liệu, bắt đầu từ dòng 0

			// Ghi dữ liệu vào Excel
			excelHelper.setCellData(value, column, lastRow);

			System.out.println("Ghi dữ liệu thành công vào dòng " + lastRow + " của cột " + column);

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
				LogUtils.info("Current value of file_name: " + value); // In giá trị hiện tại ra console
				return expectedValue.equals(value) || (value != null && !value.isEmpty()) ? value : null;
			});

			LogUtils.info("File name input has the expected value or is not empty: " + expectedValue);
		} catch (TimeoutException e) {
			System.err.println("Timeout waiting for the file_name input to have the expected value: " + expectedValue);
		} catch (Exception e) {
			System.err.println("Error while waiting for file_name input: " + e.getMessage());
		}
	}

	// create data
	public void createSampleDetail(String fileName, String sheetName) {
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
//				String fileNameOnly = excelHelper.getCellData("FILE_NAME", i);

				addSample();

				setUploadPDF(fileNamePDF, paperType);
				sleep(10);
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
				waitForFileNameToHaveValue(fileNamePDF, 240);

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
			LogUtils.info("rowIndex: " + rowIndex);
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
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

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
				setOfferActTB(OFFER_ACTION);

				ExcelHelper excelHelper2 = new ExcelHelper();
				excelHelper2.setExcelFile(fileName, sheetNameDetail);
				setSampleDRL(fileName, sheetNameDetail);

				clickSaveBTN();
				sleep(2);
				clickPublish(PUBLIC);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");
				setEditIntroData(EDIT_INTRO, NAME);
				setEditOfferData(EDIT_OFFER, NAME);
				excelHelper.setCellData("Passed", "RESULT", i);
				sleep(2);
			}
		}
	}

	public void createTokPageWPH(String fileName, String sheetName, String sheetNameDetail) {
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

				String OFFER_ACTION = excelHelper.getCellData("OFFER_ACTION", i);

				String EDIT_INTRO = excelHelper.getCellData("EDIT_INTRO", i);
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);
				String EDIT_OFFER = excelHelper.getCellData("EDIT_OFFER", i);
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

				createArticles();
				selectArticle("tok page");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setTitleTB(TITLE);
				setOfferActTB(OFFER_ACTION);

				clickSaveBTN();
				sleep(2);
				clickPublish(PUBLIC);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				setEditFAQData(EDIT_FAQ, NAME);
				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditIntroData(EDIT_INTRO, NAME);
				setEditOfferData(EDIT_OFFER, NAME);
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
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

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

				setEditIntroData(EDIT_INTRO, NAME);
				clickPublish(PUBLIC);

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

				sleep(10);
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

	public void createServiceArticles(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String URL = excelHelper.getCellData("URL", i);
				String META_TITLE = excelHelper.getCellData("META_TITLE", i);
				String META_DESCRIPTION = excelHelper.getCellData("META_DESCRIPTION", i);
				String ANCHOR = excelHelper.getCellData("ANCHOR", i);
				String HEADLINE = excelHelper.getCellData("HEADLINE", i);
				String TITLE = excelHelper.getCellData("TITLE", i);
				String INTRO = excelHelper.getCellData("INTRO", i);
				String ACTION = excelHelper.getCellData("ACTION", i);
				String TARGET = excelHelper.getCellData("TARGET", i);
				String PROMO = excelHelper.getCellData("PROMO", i);
				String PERKS_TITLE = excelHelper.getCellData("PERKS_TITLE", i);

				String EDIT_CONTENT = excelHelper.getCellData("EDIT_CONTENT", i);
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_OFFER = excelHelper.getCellData("EDIT_OFFER", i);

				createArticles();
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setHeadlineTB(HEADLINE);
				setTitleTB(TITLE);
				setIntroTB(INTRO);
				setActionTB(ACTION);
				setTargetTB(TARGET);
				setPromoTB(PROMO);
				setPerksTitleTB(PERKS_TITLE);

				clickSaveBTN();
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				//set Header
				setEditContentData(EDIT_CONTENT, NAME);
				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditOfferData(EDIT_OFFER, NAME);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				LogUtils.infoCustom(URL);

				sleep(2);
			}
		}
	}

	public void createLandingPage(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
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
				String SUB_TITLE = excelHelper.getCellData("SUB_TITLE", i);
				String INTRO = excelHelper.getCellData("INTRO", i);
				String FEATURE_TITLE = excelHelper.getCellData("FEATURE_TITLE", i);
				String FEATURE_DESCRIPTION = excelHelper.getCellData("FEATURE_DESCRIPTION", i);
				String REVIEW_TITLE = excelHelper.getCellData("REVIEW_TITLE", i);
				String REVIEW_DESCRIPTION = excelHelper.getCellData("REVIEW_DESCRIPTION", i);
				String CUSTOMER_REVIEWS = excelHelper.getCellData("CUSTOMER_REVIEWS", i);
				String DOWNLOAD_TITLE = excelHelper.getCellData("DOWNLOAD_TITLE", i);
				String DOWNLOAD_DESCRIPTION = excelHelper.getCellData("DOWNLOAD_DESCRIPTION", i);

				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

				createArticles();
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setTitleTB(TITLE);
				setSubTitleTB(SUB_TITLE);
				setIntroTB(INTRO);
				setFeatureTitleTB(FEATURE_TITLE);
				setFeatureDesTBTB(FEATURE_DESCRIPTION);

				setReviewTitleTB(REVIEW_TITLE);
				setReviewDesTBTB(REVIEW_DESCRIPTION);
				setReviewsDRL(fileName, "customerSelection", CUSTOMER_REVIEWS, NAME);
				setDownloadTitleTB(DOWNLOAD_TITLE);
				setDownloadDesTB(DOWNLOAD_DESCRIPTION);

				clickSaveBTN();
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");
				sleep(5);

				clickPublish(PUBLIC);

				WebUI.waitForPageLoaded();

				//set Header
				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditFAQData(EDIT_FAQ, NAME);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				LogUtils.infoCustom(URL);

				sleep(2);
			}
		}
	}

	public void createQatarArticles(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String URL = excelHelper.getCellData("URL", i);
				String META_TITLE = excelHelper.getCellData("META_TITLE", i);
				String META_DESCRIPTION = excelHelper.getCellData("META_DESCRIPTION", i);
				String ANCHOR = excelHelper.getCellData("ANCHOR", i);
				String WRITER_TITLE = excelHelper.getCellData("WRITER_TITLE", i);
				String WRITER_DESCRIPTION = excelHelper.getCellData("WRITER_DESCRIPTION", i);
				String WRITERS = excelHelper.getCellData("WRITERS", i);
				String METHOD_TITLE = excelHelper.getCellData("METHOD_TITLE", i);
				String REVIEW_TITLE = excelHelper.getCellData("REVIEW_TITLE", i);
				String CUSTOMER_REVIEWS = excelHelper.getCellData("CUSTOMER_REVIEWS", i);
				String OFFER_ACTION = excelHelper.getCellData("OFFER_ACTION", i);

				String EDIT_CONTENT = excelHelper.getCellData("EDIT_CONTENT", i);
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);
				String EDIT_OFFER = excelHelper.getCellData("EDIT_OFFER", i);
				String SITEMAP = excelHelper.getCellData("SITEMAP", i);

				addArticle();
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);

				setAnchorTB(ANCHOR);
				setWriterTitleTB(WRITER_TITLE);
				setWriterDesTB(WRITER_DESCRIPTION);
				setWritersDRL(fileName, "writerSelection", WRITERS, NAME);
				setMethodTitleTB(METHOD_TITLE);
				setReviewTitleTB(REVIEW_TITLE);
				setReviewsDRL(fileName, "customerSelection", CUSTOMER_REVIEWS, NAME);
				setOfferActTB(OFFER_ACTION);
				setProrityType(SITEMAP);

				clickSaveBTN();
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				//set Header
				setEditContentData(EDIT_CONTENT, NAME);
				setEditFAQData(EDIT_FAQ, NAME);
				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditOfferData(EDIT_OFFER, NAME);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				LogUtils.infoCustom(URL);

				sleep(2);
			}
		}
	}

	public void createHomePageIBHArticles(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String URL = excelHelper.getCellData("URL", i);
				String META_TITLE = excelHelper.getCellData("META_TITLE", i);
				String META_DESCRIPTION = excelHelper.getCellData("META_DESCRIPTION", i);
				String ANCHOR = excelHelper.getCellData("ANCHOR", i);
				String WRITER_TITLE = excelHelper.getCellData("WRITER_TITLE", i);
				String WRITERS = excelHelper.getCellData("WRITERS", i);
				String WRITER_BANNER = excelHelper.getCellData("WRITER_BANNER", i);
				String CUSTOMER_REVIEWS = excelHelper.getCellData("CUSTOMER_REVIEWS", i);
				String OFFER_TITLE = excelHelper.getCellData("OFFER_TITLE", i);

				String EDIT_CONTENT = excelHelper.getCellData("EDIT_CONTENT", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);
				String SITEMAP = excelHelper.getCellData("SITEMAP", i);

				addArticle();
				selectArticle("homepage");

				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);

				setAnchorTB(ANCHOR);
				setWriterTitleTB(WRITER_TITLE);


				setWriterBannerTB(WRITER_BANNER);
				setOfferTitleTB(OFFER_TITLE);

				setWritersDRL(fileName, "writerSelection", WRITERS, NAME);
				setReviewsDRL(fileName, "customerSelection", CUSTOMER_REVIEWS, NAME);

				setProrityType(SITEMAP);

				clickSaveBTN();
				excelHelper.setCellData("Passed", "RESULT", i);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				//set Header
				setEditContentData(EDIT_CONTENT, NAME);
				setEditFAQData(EDIT_FAQ, NAME);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				LogUtils.infoCustom(URL);

				sleep(2);
			}
		}
	}

	public void createNewServiceArticlesIBH(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "NAME");
		for (int i = 1; i <= lastRow; i++) {
			if (checkResult(fileName, sheetName, i)) {
				excelHelper.setExcelFile(fileName, sheetName);
				String NAME = excelHelper.getCellData("NAME", i);
				String URL = excelHelper.getCellData("URL", i);
				String META_TITLE = excelHelper.getCellData("META_TITLE", i);
				String META_DESCRIPTION = excelHelper.getCellData("META_DESCRIPTION", i);
				String ANCHOR = excelHelper.getCellData("ANCHOR", i);

				String SHOW_HEADER = excelHelper.getCellData("SHOW_HEADER", i);
				String TITLE = excelHelper.getCellData("TITLE", i);
				String TRUST_NOTE = excelHelper.getCellData("TRUST_NOTE", i);
				String WRITER_TITLE = excelHelper.getCellData("WRITER_TITLE", i);
				String WRITERS = excelHelper.getCellData("WRITERS", i);
				String WRITERS_BANNER = excelHelper.getCellData("WRITERS_BANNER", i);
				String WRITER_ACTION = excelHelper.getCellData("WRITER_ACTION", i);

				String GOAL_TITLE = excelHelper.getCellData("GOAL_TITLE", i);
				String GOAL_DES = excelHelper.getCellData("GOAL_TITLE", i);

				String REVIEW_TITLE = excelHelper.getCellData("REVIEW_TITLE", i);
				String REVIEW_DES = excelHelper.getCellData("REVIEW_DES", i);
				String CUSTOMER_REVIEWS = excelHelper.getCellData("CUSTOMER_REVIEWS", i);
				String OFFER_TITLE = excelHelper.getCellData("OFFER_TITLE", i);
				String OFFER_ACTION = excelHelper.getCellData("OFFER_ACTION", i);

				String EDIT_CONTENT = excelHelper.getCellData("EDIT_CONTENT", i);
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);

				createArticles();
				selectArticle("new concept");

				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);

				setTitleTB(TITLE);
				clickShowHeader(SHOW_HEADER);
				setTrustNoteTB(TRUST_NOTE);
				setWriterTitleTB(WRITER_TITLE);

				setWritersDRL(fileName, "writerSelection", WRITERS, NAME);
				setWriterBannerTB(WRITERS_BANNER);
				setWriterActionTB(WRITER_ACTION);

				setGoalTitleTB(GOAL_TITLE);
				setGoalDesTB(GOAL_DES);

				setReviewTitleTB(REVIEW_TITLE);
				setReviewDesTBTB(REVIEW_DES);

				setReviewsDRL(fileName, "customerSelection", CUSTOMER_REVIEWS, NAME);
				setOfferTitleTB(OFFER_TITLE);
				setOfferActTB(OFFER_ACTION);

				clickSaveBTN();
				excelHelper.setCellData("Passed", "RESULT", i);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				//set Header
				setEditContentData(EDIT_CONTENT, NAME);
				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditFAQData(EDIT_FAQ, NAME);

				LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
				LogUtils.infoCustom(URL);

				sleep(2);
			}
		}
	}

	public void createSampleListIBH(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
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
				String INTRO = excelHelper.getCellData("INTRO", i);
				String OFFER_TITLE = excelHelper.getCellData("OFFER_TITLE", i);
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);

				createArticles();
				selectArticle("samples");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setTitleTB(TITLE);
				setIntroTB(INTRO);
				setOfferTitleTB(OFFER_TITLE);

				clickSaveBTN();
				sleep(2);
				excelHelper.setCellData("Passed", "RESULT", i);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditFAQData(EDIT_FAQ, NAME);

				sleep(2);
			}
		}
	}

	public void createAboutIBH(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
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
				String SERVICE_TITLE = excelHelper.getCellData("SERVICE_TITLE", i);
				String OFFER_TITLE = excelHelper.getCellData("OFFER_TITLE", i);
				String OFFER_ACTION = excelHelper.getCellData("OFFER_ACTION", i);
				String EDIT_CONTENT_BANNER = excelHelper.getCellData("EDIT_CONTENT_BANNER", i);
				String EDIT_CONTENT = excelHelper.getCellData("EDIT_CONTENT", i);
				String SITEMAP = excelHelper.getCellData("SITEMAP", i);
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

				createArticles();
				selectArticle("about");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);

				setTitleTB(TITLE);
				setServiceTitleTB(SERVICE_TITLE);
				setOfferTitleTB(OFFER_TITLE);
				setOfferActTB(OFFER_ACTION);
				setProrityType(SITEMAP);

				clickSaveBTN();
				sleep(2);
				clickPublish(PUBLIC);
				excelHelper.setCellData("Passed", "RESULT", i);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				setEditContentBannerData(EDIT_CONTENT_BANNER, NAME);
				setEditContentData(EDIT_CONTENT, NAME);

				clickSaveBTN();

				sleep(2);
			}
		}
	}

	public void createToKPageIBH(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
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

				String OFFER_TITLE = excelHelper.getCellData("OFFER_TITLE", i);
				String OFFER_ACTION = excelHelper.getCellData("OFFER_ACTION", i);

				String EDIT_INTRO = excelHelper.getCellData("EDIT_INTRO", i);
				String EDIT_FAQ_BANNER = excelHelper.getCellData("EDIT_FAQ_BANNER", i);
				String EDIT_FAQ = excelHelper.getCellData("EDIT_FAQ", i);
				String SITEMAP = excelHelper.getCellData("SITEMAP", i);
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

				createArticles();
				selectArticle("tok page");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);
				setTitleTB(TITLE);
				setOfferTitleTB(OFFER_TITLE);
				setOfferActTB(OFFER_ACTION);
				setProrityType(SITEMAP);

				clickSaveBTN();
				sleep(2);
				clickPublish(PUBLIC);
				excelHelper.setCellData("Passed", "RESULT", i);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				setEditFAQData(EDIT_FAQ, NAME);
				setEditFAQBannerData(EDIT_FAQ_BANNER, NAME);
				setEditIntroData(EDIT_INTRO, NAME);

				sleep(1);

				sleep(2);
			}
		}
	}

	public void createContactIBH(String fileName, String sheetName, String sheetHeaderData) {
		this.fileName = fileName;
		excelHelper.setExcelFile(fileName, sheetName);
		this.sheetHeaderData = sheetHeaderData;
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
				String SUPPORT_TITLE = excelHelper.getCellData("SUPPORT_TITLE", i);
				String SUPPORT_DES = excelHelper.getCellData("SUPPORT_DES", i);
				String SUPPORT_TIME = excelHelper.getCellData("SUPPORT_TIME", i);
				String SITEMAP = excelHelper.getCellData("SITEMAP", i);
				String PUBLIC = excelHelper.getCellData("PUBLIC", i);

				createArticles();
				selectArticle("contact");
				setNameTB(NAME);
				setUrlTB(URL);
				setMetaTitleSec(META_TITLE);
				setMetaDesTB(META_DESCRIPTION);
				setAnchorTB(ANCHOR);

				setTitleTB(TITLE);
				setSupportTitleTB(SUPPORT_TITLE);
				setSupportDesTB(SUPPORT_DES);
				setSupportTimeTB(SUPPORT_TIME);
				setProrityType(SITEMAP);

				clickSaveBTN();
				sleep(2);
				clickSaveBTN();
				clickPublish(PUBLIC);
				excelHelper.setCellData("Passed", "RESULT", i);
				LogUtils.info(DriverManager.getDriver().getCurrentUrl());
				LogUtils.info(URL);
				recordFile(DriverManager.getDriver().getCurrentUrl(), "ID");
				recordFile(URL, "URL");

				sleep(2);
				WebUI.waitForPageLoaded();

				sleep(2);
			}
		}
	}

	// get and extract data

	public void getDataSampleDetail(String fileName, String sheetName) {
		DriverManager.getDriver().get("https://yeti-cms.dev/yeti/main/samples/edit/173");
		excelHelper.setExcelFile(fileName, sheetName);
		excelHelper.setCellData(getNameTB(), "NAME", 1);
	}

	public void getDataWriterReview(String fileName, String sheetName) {
		excelHelper.setExcelFile(fileName, sheetName);
		int lastRow = ExcelHelper.getLastRowWithData(fileName, sheetName, "URL");
		for (int i = 1; i <= lastRow; i++) {
			DriverManager.getDriver().get(excelHelper.getCellData("URL", i));
			if (checkResult(fileName, sheetName, i) && !checkPageNotFound()) {
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
			DriverManager.getDriver().get(excelHelper.getCellData("URL", i));
			if (checkResult(fileName, sheetName, i) && !checkPageNotFound()) {
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
		if (!noteEditableElement(1).isDisplayed()) {
			return;
		}
		String content = noteEditableElement(1).getAttribute("innerHTML");

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
		String content = noteEditableElement(1).getAttribute("innerHTML");

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
		String content = noteEditableElement(1).getAttribute("innerHTML");

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
		String content = noteEditableElement(1).getAttribute("innerHTML");

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
		String content = noteEditableElement(1).getAttribute("innerHTML");

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

		int rowNum = 0;

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
		}

		// Ghi dữ liệu vào file Excel
		try (FileOutputStream outputStream = new FileOutputStream(dataExcelFilePath)) {
			wb.write(outputStream);
		}

		// Đóng workbook
		wb.close();
		LogUtils.info("Data has been written to Excel file: " + dataExcelFilePath);
	}

	// Hàm con để trích xuất dữ liệu từ một element noteEditable
	private int extractNoteEditableElementDataToExcel(Sheet sheet, String serviceUrl, String element, WebElement noteEditableElement, int rowNum) throws IOException {
		// Lấy nội dung HTML của element
		String content = noteEditableElement(1).getAttribute("innerHTML");

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

	// Simple test
	public void simpleTest(String fileName, String sheetName) {
		DriverManager.getDriver().get("https://yeti-cms.dev/yeti/main/articles/edit/178");
		String id = "exhibit-writing-service";
		clickSaveBTN();
		sleep(15);
	}
}