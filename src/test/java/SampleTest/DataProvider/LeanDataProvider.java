package SampleTest.DataProvider;

import logs.LogUtils;
import org.testng.annotations.Test;
import helpers.dataProvider.DataProviderFactory;

import java.util.Hashtable;

public class LeanDataProvider {


	@Test(enabled = false,dataProvider = "data_provider_01",dataProviderClass = DataProviderFactory.class)
	public void testDataProvider1(String value) {
		LogUtils.info("Passed Parameter is: " + value);
	}

	@Test(enabled = false,dataProvider = "data_provider_02",dataProviderClass = DataProviderFactory.class)
	public void testDataProviderMultiParam(String username, int password, String role) {
		LogUtils.info("Username is: " + username);
		LogUtils.info("Password is: " + password);
		LogUtils.info("Role is: " + role);
	}
	@Test(enabled = false,dataProvider = "data_provider_login_excel",dataProviderClass = DataProviderFactory.class)
	public void testDataProviderMultiParam2(String short_intro, String type_of_paper, String academic_level) {
		LogUtils.info("Username is: " + short_intro);
		LogUtils.info("Password is: " + type_of_paper);
		LogUtils.info("Role is: " + academic_level);
	}
	@Test(enabled = true,dataProvider = "dataLogin",dataProviderClass = DataProviderFactory.class)
	public void testDataProviderMultiParam3(Hashtable<String, String> data) {
		LogUtils.info("Username is: " + data.get("EMAIL"));
		LogUtils.info("Password is: " + data.get("PASSWORD"));
	}

}