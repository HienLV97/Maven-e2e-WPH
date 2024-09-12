package CMS.SignIn.testcases;

import CMS.SignIn.pages.SignInPage;
import Support.Initialization.Init;
import helpers.Constants;
import org.testng.annotations.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class SignInTest extends Init {
	@Test(enabled = true)
	public void test() throws NoSuchAlgorithmException, KeyManagementException {
		SignInPage signInPage = new SignInPage(driver);
		authenticate("CMS");
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		sleep(5);
	}
}