package helpers.dataProvider;

import helpers.ExcelHelper;
import helpers.SystemHelper;
import logs.LogUtils;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataProviderFactory {
	@DataProvider(name = "data_provider_01")
	public Object[][] dataProvider1() {
		return new Object[][]{{"First-Value"}, {"1"}};
	}

	@DataProvider(name = "data_provider_02")
	public Object[][] dataProvider2() {
		return new Object[][]{{"Value1", 2, "Value3"}, {"Value4", 2, "Value6"}};
	}
	@DataProvider(name = "data_provider_03", parallel = true)
	public Object[][] dataProvider3() {
		return new Object[][]{{"anhtester", "123456", "Admin"}, {"joe.larson", "joe.larson", "Employee"}};
	}
	@DataProvider(name = "data_provider_login_excel")
	public Object[][] dataLoginHRMFromExcel() {
		ExcelHelper excelHelper = new ExcelHelper();
		Object[][] data = excelHelper.getExcelData(SystemHelper.getCurrentDir()+ "src/test/resources/testdata/DataCMS.xlsx", "test");
		LogUtils.info("Login Data from Excel: " + Arrays.deepToString(data));
		return data;
	}
	@DataProvider(name = "data_sampleDetail")
	public Object[][] dataSampleDetailHashtable() throws Exception {
		ExcelHelper excelHelper = new ExcelHelper();
		String filePath = "src/test/resources/testdata/DataCMS.xlsx";
		String sheetName = "sampleDetail";
		excelHelper.setExcelFile(filePath,sheetName);
		Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() +filePath , sheetName);
		LogUtils.info("Login Data from Excel: " + Arrays.deepToString(data));
		return data;
	}

	@DataProvider(name = "dataLogin",parallel = true)
	public Object[][] dataLogin() throws Exception {
		ExcelHelper excelHelper = new ExcelHelper();
		String filePath = "src/test/resources/testdata/DataDashboard.xlsx";
		String sheetName = "account";
		excelHelper.setExcelFile(filePath,sheetName);
		Object[][] data = excelHelper.getDataHashTable(SystemHelper.getCurrentDir() +filePath , sheetName);
		LogUtils.info("Login Data from Excel: " + Arrays.deepToString(data));
		return data;
	}
}
