package WPH.SignIn.testcases;

import helpers.drivers.DriverManager;
import WPH.SignIn.pages.SignInPage;
import Initialization.Init;
import helpers.Constants;
import org.testng.annotations.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class SignInTest extends Init {
	@Test
	public void test() throws NoSuchAlgorithmException, KeyManagementException {
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		authenticate("WPH");
		signInPage.Login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		sleep(5);
	}
}
