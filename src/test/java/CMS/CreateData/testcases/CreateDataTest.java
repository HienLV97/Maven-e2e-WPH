package CMS.CreateData.testcases;

import CMS.CreateData.pages.CreateDataPage;
import CMS.SignIn.pages.SignInPage;
import Support.Initialization.Init;
import helpers.Constants;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import logs.LogUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CreateDataTest extends Init {
	@Test
	public void gotoWPH() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		LogUtils.infoCustom(driver.getCurrentUrl());
		LogUtils.info(driver.getCurrentUrl());
		createDataPage.clickWPHBTN();
	}

	@Test
	public void sampleCreateArticle() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleList";
		String sheetNameDetail = "sampleDetail";
		createDataPage.createSamplesArticles(filePath, sheetName,sheetNameDetail);
	}

	@Test
	public void sampleDetail() {
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

	@Test
	public void test2() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		LogUtils.info("hihi");
		LogUtils.infoCustom("haha");
	}

	@Test
	public void sampleIBDetail() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		String fileName = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "ibDetail";
		createDataPage.createSampleDetail(fileName, sheetName);
	}


}
