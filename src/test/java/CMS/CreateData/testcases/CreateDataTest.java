package CMS.CreateData.testcases;

import CMS.CreateData.pages.CreateDataPage;
import CMS.SignIn.pages.SignInPage;
import Support.Initialization.Init;
import dataProvider.DataProviderFactory;
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
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		createDataPage = new CreateDataPage(driver);
		LogUtils.infoCustom(driver.getCurrentUrl());
		LogUtils.info(driver.getCurrentUrl());
	}

	public void gotoWPH() {
		signIn();
		createDataPage.clickWPHBTN();
	}

	public void gotoIBW() {
		signIn();
		createDataPage.clickIBWBTN();
	}

	@Test
	public void sampleCreateArticle() throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleList";
		String sheetNameDetail = "sampleDetail";
		createDataPage.createSamplesArticles(filePath, sheetName, sheetNameDetail);
	}

	@Test
	public void sampleDetail() throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test
	@Parameters({"rowNumb"})
	public void test(int rowNumb) throws IOException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println(now.format(formatter));
	}

	@Test
	public void deleteArticles() {
		authenticate("CMS");
		CreateDataPage createDataPage = new CreateDataPage(driver);
		gotoWPH();
		String fileName = "src/test/resources/testdata/outputArticles.xlsx";
		String sheetName = "Sheet1";
		createDataPage.deleteArticles(fileName, sheetName);
	}

	@Test(dataProvider = "data_sampleDetail", dataProviderClass = DataProviderFactory.class)
	public void test2(Hashtable<String, String> data) throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		createDataPage.createSampleDetailTest(data);
		sleep(3);
		driver.quit();
	}

	@Test(description = "test3")
	public void test3() throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test(description = "test4 for")
	public void test4() throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "test";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test
	public void sampleIBDetail() throws Exception {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
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
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "customerReview";
		createDataPage.getDataCustomerReview(fileName, sheetName);
	}

	@Test(description = "Get data services page")
	public void getDataService() throws IOException {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String urlExcelSheet = "urlServiceSheet";
		String dataServiceSheet = "dataServicePage";
		createDataPage.getDataServicePage(fileName, urlExcelSheet, dataServiceSheet);

	}

	@Test(description = "create data")
	public void sampleDetailIB() throws Exception {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}

	@Test(description = "Create customer review")
	public void createCustomerReview() {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "customerReview";
		createDataPage.createCustomerReview(fileName, sheetName);

	}

	@Test(description = "Create writer review")
	public void createWriterReview() {
		gotoIBW();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "writerReview";
		createDataPage.createWriterReview(fileName, sheetName);

	}

}
