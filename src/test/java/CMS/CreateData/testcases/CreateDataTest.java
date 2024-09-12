package CMS.CreateData.testcases;

import CMS.CreateData.pages.CreateDataPage;
import CMS.SignIn.pages.SignInPage;
import CMS.SignIn.testcases.SignInTest;
import Support.CMS.Routers;
import Support.Initialization.Init;
import helpers.Constants;
import helpers.ExcelHelper;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;


public class CreateDataTest extends Init {

//	@BeforeClass
//	public void login() throws NoSuchAlgorithmException, KeyManagementException {
//		authenticate("CMS");
//		SignInTest signInTest = new SignInTest();
//		signInTest.test();
//	}


	@Test
	public void sampleCreateArticle() {
		authenticate("CMS");
		SignInPage signInPage = new SignInPage(driver);
		signInPage.Login(Constants.COMMON_EMAIL,Constants.COMMON_PASSWORD);
		CreateDataPage createDataPage = new CreateDataPage(driver);
		createDataPage.clickWPHBTN();
		createDataPage.createNewSamples("test","test","test","test","test","test","test","test","test");
	}
}
