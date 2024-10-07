package Writer.SignIn.testcases;

import AcaWriting.drivers.DriverManager;
import Writer.SignIn.pages.SignInPage;
import helpers.Constants;
import AcaWriting.Support.Initialization.Init;
import org.testng.annotations.Test;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class SignInTest extends Init {
	@Test(enabled = true)
	public void test() throws NoSuchAlgorithmException, KeyManagementException {
		SignInPage signInPage = new SignInPage(DriverManager.getDriver());
		authenticate("DashBoard");
		signInPage.login(Constants.COMMON_EMAIL, Constants.COMMON_PASSWORD);
		sleep(5);
	}
}