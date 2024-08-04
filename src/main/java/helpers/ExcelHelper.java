package helpers;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;

public class ExcelHelper {

	private FileInputStream fis;
	private FileOutputStream fileOut;
	private Workbook wb;
	private Sheet sh;
	private Cell cell;
	private Row row;
	private CellStyle cellstyle;
	private Color mycolor;
	private String excelFilePath;
	private Map<String, Integer> columns = new HashMap<>();

	public void setExcelFile(String ExcelPath, String SheetName) {
		try {
			File f = new File(ExcelPath);

			if (!f.exists()) {
				throw new Exception("File doesn't exist.");
			}

			fis = new FileInputStream(ExcelPath);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(SheetName);

			if (sh == null) {
				throw new Exception("Sheet name doesn't exist.");
			}

			this.excelFilePath = ExcelPath;

			//adding all the column header names to the map 'columns'
			sh.getRow(0).forEach(cell -> {
				columns.put(cell.getStringCellValue(), cell.getColumnIndex());
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public String getCellData(int columnIndex, int rowIndex) {
		try {
			cell = sh.getRow(rowIndex).getCell(columnIndex);
			String CellData = null;
			switch (cell.getCellType()) {
				case STRING:
					CellData = cell.getStringCellValue();
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						CellData = String.valueOf(cell.getDateCellValue());
					} else {
						CellData = String.valueOf((long) cell.getNumericCellValue());
					}
					break;
				case BOOLEAN:
					CellData = Boolean.toString(cell.getBooleanCellValue());
					break;
				case BLANK:
					CellData = "";
					break;
			}
			return CellData;
		} catch (Exception e) {
			return "";
		}
	}

	//Gọi ra hàm này nè
	public String getCellData(String columnName, int rowIndex) {
		return getCellData(columns.get(columnName), rowIndex);
	}

}