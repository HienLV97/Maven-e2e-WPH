import Initialization.Init;
import helpers.ExcelHelper;
import logs.LogUtils;

//import static Support.Initialization.Init.driver;

import java.io.IOException;

public class demo extends Init {

	public static void main(String[] args) throws IOException {
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile("src/test/resources/testdata/DataCMS.xlsx", "sampleDetail");
		// Tìm index của cell chứa giá trị "Value2"
		int rowIndex = excelHelper.findCellIndex("URL", "/samples/epq-essay/essay");
		LogUtils.info("rowIndex: "+ rowIndex);
		if (rowIndex != -1) {
			LogUtils.info("Found value at row: " + rowIndex);
		} else {
			LogUtils.info("Value not found.");
		}
	}
}
