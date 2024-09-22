package SampleTest.DataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider {
	@DataProvider(name = "data_provider_01")
	public Object[][] dpMethod() {
		return new Object[][]{{"First-Value"}, {"Second-Value"}};
	}

	@Test(dataProvider = "data_provider_01")
	public void testDataProvider(String value) {
		System.out.println("Passed Parameter is: " + value);
	}
}