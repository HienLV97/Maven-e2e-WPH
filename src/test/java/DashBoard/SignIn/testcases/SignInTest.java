package DashBoard.SignIn.testcases;

import DashBoard.SignIn.pages.SignInPage;
import Support.Constants;
import Support.Initialization.Init;
import org.testng.annotations.Test;

public class SignInTest extends Init {
	@Test (enabled = true)
	public void test(){
		SignInPage signInPage = new SignInPage(driver);
		Authenticate("DashBoard");
		signInPage.Login(Constants.email, Constants.passAccount);
		sleep(5);
	}
}
