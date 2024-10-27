package CMS.CreateData.testcases;

import AcaWriting.drivers.DriverManager;
import CMS.CreateData.pages.CreateDataPage;
import CMS.SignIn.pages.SignInPage;
import AcaWriting.Support.Initialization.Init;
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

	public void gotoIBW() {
		signIn();
		CreateDataPage createDataPage = new CreateDataPage(DriverManager.getDriver());
		createDataPage.clickIBWBTN();
	}

	@Test
	public void sampleCreateArticle() throws Exception {
		gotoWPH();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleList";
		String sheetNameDetail = "sampleDetail";
		createDataPage.createSamplesArticlesWPH(filePath, sheetName, sheetNameDetail);
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
		createDataPage.createConstants(fileName, sheetName);
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
		gotoWPH();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
		createDataPage.deleteArticles(fileName, sheetName);
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

}
