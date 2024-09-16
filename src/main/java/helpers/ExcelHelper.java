package helpers;

import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	public void setCellData(String text, String columnName, int rowIndex) {
		try {
			row = sh.getRow(rowIndex);
			if (row == null) {
				row = sh.createRow(rowIndex);
			}
			cell = row.getCell(columns.get(columnName));

			if (cell == null) {
				cell = row.createCell(columns.get(columnName));
			}
			cell.setCellValue(text);

			XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
			style.setFillPattern(FillPatternType.NO_FILL);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);

			cell.setCellStyle(style);

			fileOut = new FileOutputStream(excelFilePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	public int getRowCount(String sheetName,int RowNumber) {
		Sheet sheet = wb.getSheet(sheetName);
		return sheet.getRow(RowNumber).getLastCellNum();
//		return sheet.getLastRowNum(); // Trả về số dòng cuối cùng có dữ liệu
	}
	public static int getLastRowWithData(String filePath, String sheetName, String columnName) {
		try (FileInputStream file = new FileInputStream(new File(filePath));
			 Workbook workbook = WorkbookFactory.create(file)) {

			// Lấy sheet theo tên
			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " không tồn tại trong file Excel.");
			}

			// Tìm số dòng có dữ liệu cuối cùng
			return getLastRowWithData(sheet, columnName);

		} catch (IOException e) {
			System.err.println("Đã xảy ra lỗi khi xử lý file: " + e.getMessage());
			return -1;
		}
	}

	public static int getLastRowWithData(Sheet sheet, String columnName) {
		int lastRowWithData = -1;
		int columnIndex = -1;

		// Giả sử tên cột nằm ở hàng đầu tiên (row 0)
		Row headerRow = sheet.getRow(0);

		if (headerRow != null) {
			// Tìm chỉ số của cột theo tên cột
			for (Cell cell : headerRow) {
				if (cell.getStringCellValue().equalsIgnoreCase(columnName)) {
					columnIndex = cell.getColumnIndex();
					break;
				}
			}
		}

		// Nếu không tìm thấy cột với tên tương ứng, trả về -1
		if (columnIndex == -1) {
			return -1;
		}

		// Duyệt qua tất cả các hàng, bắt đầu từ hàng thứ 2
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);

			if (row != null) {
				Cell cell = row.getCell(columnIndex);
				// Kiểm tra nếu ô có dữ liệu
				if (cell != null && cell.getCellType() != CellType.BLANK) {
					lastRowWithData = row.getRowNum();
				}
			}
		}

		return lastRowWithData;
	}


}