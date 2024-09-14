package CMS.CreateData.testcases;

import CMS.CreateData.pages.CreateDataPage;
import CMS.SignIn.pages.SignInPage;
import Support.Initialization.Init;
import helpers.Constants;
import helpers.ExcelHelper;
import org.testng.annotations.Test;


public class CreateDataTest extends Init {
		@Test(enabled = true)
	public void sampleCreateArticle() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile("src/test/resources/testdata/DataCMS.xlsx", "sampleDetail");
		int rowNumb = 15;
		for (int i = 1; i <= rowNumb; i++) {
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
			createDataPage.createSamplesList(name, url, metaTitle, description, anchor, title, essayNote, essayAct, offer, samples);
		}
	}

//		@Test(enabled = true)
	public void sampleDetail() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile("src/test/resources/testdata/DataCMS.xlsx", "Sample");
		int rowNumb = 31;
		for (int i = 1; i <= rowNumb; i++) {
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
			String fileName = excelHelper.getCellData("fileName", i);
			createDataPage.createSampleDetail(name, url, title, description, intro, date, academic, paperType, discipline, citation, pages, words, fileName);
		}
	}

//	@Test(enabled = true)
	public void test() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		createDataPage.createArticles();
		createDataPage.selectArticle("samples");
//		createDataPage.	clickSampleDRL();
		String value = "Critical reading log of two article, New Job Opportunities in the Age of Artificial Intelligence, MBA application essay, The Tells Teeth Tale, Human Trafficking";
		createDataPage.setSampleDRL(value);


	}
}
