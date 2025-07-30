package CMS.CreateData.testcases;

import AcaWriting.Support.CMS.Routers;
import helpers.drivers.DriverManager;
import CMS.CreateData.pages.CreateDataPage;
import CMS.SignIn.pages.SignInPage;
import Initialization.Init;
import helpers.dataProvider.DataProviderFactory;
import helpers.Constants;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import logs.LogUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.Hashtable;

public class CreateDataTest extends Init {
	private CreateDataPage createDataPage;

	public void signIn() {
		authenticate("CMS");
		DriverManager.getDriver().get(Routers.SIGN_IN);
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		createDataPage = new CreateDataPage(DriverManager.getDriver());
		LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
		LogUtils.info(DriverManager.getDriver().getCurrentUrl());
	}

	public void signInProd(){
		DriverManager.getDriver().get("https://yeti-cloud.com/");
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		createDataPage = new CreateDataPage(DriverManager.getDriver());
		LogUtils.infoCustom(DriverManager.getDriver().getCurrentUrl());
		LogUtils.info(DriverManager.getDriver().getCurrentUrl());
	}

	public void gotoWPH() {
		signIn();
		createDataPage.clickWPHBTN();
	}

	public void gotoWPHProd(){
		signInProd();
		createDataPage.clickWPHBTN();
	}


	public void gotoIBHProd(){
		signInProd();
		createDataPage.clickIBHBTN();
	}


	public void gotoIBW() {
		signIn();
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.clickIBWBTN();
	}

	public void gotoIBH() {
		signIn();
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.clickIBHBTN();
	}

	@Test
	public void sampleCreateArticle() throws Exception {
		gotoWPH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "tokPage";
		String sheetNameDetail = "dataHeader";
		createDataPage.createTokPageWPH(filePath, sheetName, sheetNameDetail);
	}

	@Test
	public void sampleDetail() throws Exception {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test
	@Parameters({"rowNumb"})
	public void test(int rowNumb) throws IOException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LogUtils.info(now.format(formatter));
	}

	@Test
	public void deleteArticles() {
		authenticate("CMS");
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		gotoWPH();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
		createDataPage.deleteArticles(fileName, sheetName);
	}

	@Test(dataProvider = "data_sampleDetail", dataProviderClass = DataProviderFactory.class)
	public void test2(Hashtable<String, String> data) throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.clickWPHBTN();
		createDataPage.createSampleDetailTest(data);
		sleep(3);
		DriverManager.getDriver().quit();
	}

	@Test(description = "test3")
	public void test3() throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test(description = "test4 for")
	public void test4() throws Exception {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerSelection";
		createDataPage.simpleTest(fileName, sheetName);
	}


	//WPH
	@Test
	public void createWriterReviewWPH() throws IOException {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerReview";
		createDataPage.createWriterReview(fileName, sheetName);
	}
	@Test
	public void deleteWriterWPH() {
		gotoWPH();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
		createDataPage.deleteReviews(fileName, sheetName);
	}


	@Test
	public void sampleIBDetail() throws Exception {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "ibDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}


	@Test(description = "Get data writer review")
	public void getDataWriterReview() {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerReview";
		createDataPage.getDataWriterReview(fileName, sheetName);
	}

	@Test(description = "Get data customer review")
	public void getDataCustomerReview() {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "customerReview";
		createDataPage.getDataCustomerReview(fileName, sheetName);
	}

	@Test(description = "Get data services page")
	public void getDataService() throws IOException {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String urlExcelSheet = "urlServiceSheet";
		String dataServiceSheet = "dataServicePage";
		createDataPage.getDataServicePage(fileName, urlExcelSheet, dataServiceSheet);
	}

	// Create for IB writing
	@Test(description = "create ib sample detail")
	public void ibSampleDetailIBW() throws Exception {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "ibDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test(description = "createSampleArticleIBW")
	public void createSampleArticleIBW() throws Exception {
		gotoIBW();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleList";
		String sheetNameDetail = "ibDetail";
		createDataPage.createSamplesArticlesIBW(filePath, sheetName, sheetNameDetail);
	}


	@Test(description = "Create customer review")
	public void createCustomerReviewIBW() {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "customerReview";
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.createCustomerReview(fileName, sheetName);

	}

	@Test(description = "Create writer review")
	public void createWriterReviewIBW() throws IOException {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerReview";
		createDataPage.createWriterReview(fileName, sheetName);

	}

	@Test(description = "Create constants")
	public void createConstantsIBW() {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "constants";
		String sheetHeaderData = "dataHeader";
		createDataPage.createServiceArticles(fileName, sheetName,sheetHeaderData);
	}

	@Test
	public void deleteArticlesIBW() {
		gotoIBW();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
		createDataPage.deleteArticles(fileName, sheetName);
	}

	// Create Service page
	@Test(description = "Create service page")
	public void createServicesIBW() {
		gotoIBW();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
//		createDataPage.deleteArticles(fileName, sheetName);
	}

	@Test(description = "Create service page")
	public void createServicesWPH() {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "tokPage";
		createDataPage.deleteArticles(fileName, sheetName);
	}

	@Test(description = "createLandingPageWPH")
	public void createLandingPageWPH() {
		gotoWPH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "landingpage";
		String sheetHeaderData = "dataHeader";
		createDataPage.createLandingPage(fileName, sheetName,sheetHeaderData);
	}


	//Create Qatar site
	@Test(description = "Create qatar page")
	public void createQatarArticles() {
		gotoWPHProd();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "home";
		String sheetHeaderData = "dataHeader";
		createDataPage.createQatarArticles(fileName, sheetName, sheetHeaderData);
	}

	//Create IBHelper
	@Test(description = "Create service page IBH")
	public void createServicesIBH() {
		gotoIBH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "servicesIBH";
		String sheetHeaderData = "dataHeader";
		createDataPage.createServiceArticles(fileName, sheetName,sheetHeaderData);
	}

	@Test(description = "Create customer review IBH")
	public void createCustomerReviewIBH() {
		gotoIBH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "customerReview";
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.createCustomerReview(fileName, sheetName);

	}

	@Test(description = "Create writer review IBH")
	public void createWriterReviewIBH() throws IOException {
		gotoIBH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerReview";
		createDataPage.createWriterReview(fileName, sheetName);

	}
	@Test(description = "Create service page IBH")
	public void createServicesIBHProd() {
		gotoIBHProd();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "servicesIBH";
		String sheetHeaderData = "dataHeader";
		createDataPage.createServiceArticles(fileName, sheetName,sheetHeaderData);
	}

	@Test(description = "Create customer review")
	public void createCustomerReviewIBHProd() {
		gotoIBHProd();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "customerReview";
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.createCustomerReview(fileName, sheetName);

	}

	@Test(description = "Create writer review")
	public void createWriterReviewIBHProd() throws IOException {
		gotoIBHProd();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerReview";
		createDataPage.createWriterReview(fileName, sheetName);
	}

	@Test(description = "Create homepage IBH")
	public void createHomePageIBH() {
		gotoIBH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "homepageIBH";
		String sheetHeaderData = "dataHeader";
		createDataPage.createHomePageIBHArticles(fileName, sheetName, sheetHeaderData);
	}

	@Test(description = "Create new service page IBH")
	public void createNewServicesIBH() {
		gotoIBH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "newServicesIBH";
		String sheetHeaderData = "dataHeader";
		createDataPage.createNewServiceArticlesIBH(fileName, sheetName,sheetHeaderData);
	}

	@Test
	public void sampleCreateArticleIBH() throws Exception {
		gotoIBH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "tokPage";
		String sheetNameDetail = "dataHeader";
		createDataPage.createTokPageWPH(filePath, sheetName, sheetNameDetail);
	}

	@Test(description = "Create sample detail IBH")
	public void createSampleDetailIBH() throws Exception {
		gotoIBH();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test(description = "Create sample detail IBH")
	public void createSampleListIBH() throws Exception {
		gotoIBH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleListIBH";
		String sheetHeaderData = "dataHeader";
		createDataPage.createSampleListIBH(filePath, sheetName,sheetHeaderData);
	}
	@Test(description = "Create Tok Page")
	public void aboutIBH() throws Exception {
		gotoIBH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "aboutIBH";
		String sheetNameDetail = "dataHeader";
		createDataPage.createAboutIBH(filePath, sheetName, sheetNameDetail);
	}

	@Test(description = "Create about IBH")
	public void ToKPageIBH() throws Exception {
		gotoIBH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "ToKPageIBH";
		String sheetNameDetail = "dataHeader";
		createDataPage.createToKPageIBH(filePath, sheetName, sheetNameDetail);
	}
	@Test(description = "Create contact IBH")
	public void contactIBH() throws Exception {
		gotoIBH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "contactIBH";
		String sheetNameDetail = "dataHeader";
		createDataPage.createContactIBH(filePath, sheetName, sheetNameDetail);
	}
	@Test
	public void deleteArticlesIBH() {
		gotoIBH();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
		createDataPage.deleteArticles(fileName, sheetName);
	}
}
