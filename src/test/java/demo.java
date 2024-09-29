import Support.Initialization.Init;
import helpers.ExcelHelper;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

//import static Support.Initialization.Init.driver;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class demo extends Init {

	public static void main(String[] args) throws IOException {
		ExcelHelper excelHelper = new ExcelHelper();
		excelHelper.setExcelFile("src/test/resources/testdata/DataCMS.xlsx", "sampleDetail");
		// Tìm index của cell chứa giá trị "Value2"
		int rowIndex = excelHelper.findCellIndex("URL", "/samples/epq-essay/essay");
		System.out.println("rowIndex: "+ rowIndex);
		if (rowIndex != -1) {
			System.out.println("Found value at row: " + rowIndex);
		} else {
			System.out.println("Value not found.");
		}
	}
}
