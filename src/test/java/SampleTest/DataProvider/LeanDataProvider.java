package SampleTest.DataProvider;

import helpers.ExcelHelper;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import dataProvider.DataProviderFactory;

import java.util.Arrays;

public class LeanDataProvider {


	@Test(enabled = false,dataProvider = "data_provider_01",dataProviderClass = DataProviderFactory.class)
	public void testDataProvider1(String value) {
		System.out.println("Passed Parameter is: " + value);
	}

	@Test(enabled = false,dataProvider = "data_provider_02",dataProviderClass = DataProviderFactory.class)
	public void testDataProviderMultiParam(String username, int password, String role) {
		System.out.println("Username is: " + username);
		System.out.println("Password is: " + password);
		System.out.println("Role is: " + role);
	}
	@Test(enabled = true,dataProvider = "data_provider_login_excel",dataProviderClass = DataProviderFactory.class)
	public void testDataProviderMultiParam2(String short_intro, String type_of_paper, String academic_level) {
		System.out.println("Username is: " + short_intro);
		System.out.println("Password is: " + type_of_paper);
		System.out.println("Role is: " + academic_level);
	}

}