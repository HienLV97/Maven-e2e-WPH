package SampleTest.ExcelTest;

import helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcel {
	@Test
	public void getDataFromExcel() {
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile("src/test/resources/testdata/Book1.xlsx","Sheet1");

		System.out.println(excelHelper.getCellData("username", 2));
		System.out.println(excelHelper.getCellData("password", 1));

	}
}
