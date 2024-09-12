package SampleTest.ExcelTest;

import helpers.ExcelHelper;
import org.testng.annotations.Test;

public class DemoExcel {
	@Test
	public void getDataFromExcel() {
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile("src/test/resources/testdata/DataCMS.xlsx", "Sample");
		int rowNumb = 7;
//		int columnNumb = 10;
		for (int i = 1; i <= rowNumb; i++){
			System.out.println(excelHelper.getCellData("name", i));
			System.out.println(excelHelper.getCellData("url", i));
		}


	}
}
