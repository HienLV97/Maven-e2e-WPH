package helpers;

import java.awt.Color;
import java.io.*;
import java.util.*;

import logs.LogUtils;
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
			LogUtils.info(e.getMessage());
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

	public int getRowCount(String sheetName, int RowNumber) {
		Sheet sheet = wb.getSheet(sheetName);
		return sheet.getRow(RowNumber).getLastCellNum();
//		return sheet.getLastRowNum(); // Trả về số dòng cuối cùng có dữ liệu
	}

	public static int getLastRowWithData(String filePath, String sheetName, String columnName) {
		File excelFile = new File(filePath);

		// Kiểm tra tệp rỗng
		if (excelFile.length() == 0) {
			LogUtils.info("Tệp rỗng: " + filePath);
			return -1;
		}

		try (FileInputStream file = new FileInputStream(excelFile);
			 Workbook workbook = WorkbookFactory.create(file)) {

			// Lấy sheet theo tên
			Sheet sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				throw new IllegalArgumentException("Sheet " + sheetName + " không tồn tại trong file Excel.");
			}

			// Tìm số dòng có dữ liệu cuối cùng
			return getLastRowWithData(sheet, columnName);

		} catch (IOException e) {
			LogUtils.info("Đã xảy ra lỗi khi xử lý file: " + e.getMessage());
			return -1;
		}
	}

	public int[] getFirstAndLastRowByValue(String value) {
		int firstRow = -1;
		int lastRow = -1;

		for (Row row : sh) {
			for (Cell cell : row) {
				if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(value)) {
					int currentRowNum = row.getRowNum();
					if (firstRow == -1) {
						firstRow = currentRowNum; // Hàng đầu tiên
					}
					lastRow = currentRowNum; // Cập nhật hàng cuối cùng mỗi lần tìm thấy
				}
			}
		}
		return new int[]{firstRow, lastRow}; // Trả về chỉ số của hàng đầu tiên và hàng cuối cùng
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

	public Object[][] getExcelData(String filePath, String sheetName) {
		Object[][] data = null;
		Workbook workbook = null;
		try {
			// load the file
			FileInputStream fis = new FileInputStream(filePath);

			// load the workbook
			workbook = new XSSFWorkbook(fis);

			// load the sheet
			Sheet sh = workbook.getSheet(sheetName);

			// load the row
			Row row = sh.getRow(0);

			// get the number of rows and columns
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();

			LogUtils.info(noOfRows + " - " + noOfCols);

			data = new Object[noOfRows - 1][noOfCols];

			// iterate through the rows and columns
			for (int i = 1; i < noOfRows; i++) {
				row = sh.getRow(i);
				if (row == null) continue;  // Skip if row is null
				for (int j = 0; j < noOfCols; j++) {
					Cell cell = row.getCell(j);

					// Check if the cell is null before accessing its type
					if (cell == null) {
						data[i - 1][j] = "";  // Assign an empty string for null cells
						continue;
					}

					switch (cell.getCellType()) {
						case STRING:
							data[i - 1][j] = cell.getStringCellValue();
							break;
						case NUMERIC:
							data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
							break;
						case BLANK:
							data[i - 1][j] = "";  // Handle blank cells as empty strings
							break;
						default:
							data[i - 1][j] = cell.getStringCellValue();
							break;
					}
				}
			}
		} catch (Exception e) {
			LogUtils.info("The exception is: " + e.getMessage());
			throw new RuntimeException(e);
		}
		return data;
	}

	//Hàm này dùng cho trường hợp nhiều Field trong File Excel
	public int getColumns() {
		try {
			row = sh.getRow(0);
			return row.getLastCellNum();
		} catch (Exception e) {
			LogUtils.info(e.getMessage());
			throw (e);
		}
	}

	//Get last row number (lấy vị trí dòng cuối cùng tính từ 0)
	public int getLastRowNum() {
		return sh.getLastRowNum();
	}

	//Lấy số dòng có data đang sử dụng
	public int getPhysicalNumberOfRows() {
		return sh.getPhysicalNumberOfRows();
	}

	public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) throws Exception, IOException {
		LogUtils.info("Excel Path: " + excelPath);
		Object[][] data = null;

		File f = new File(excelPath);
		if (!f.exists()) {
			LogUtils.info("File Excel path not found.");
			throw new IOException("File Excel path not found.");
		}

		fis = new FileInputStream(excelPath);

		wb = new XSSFWorkbook(fis);

		sh = wb.getSheet(sheetName);

		int rows = getLastRowNum();
		int columns = getColumns();

		LogUtils.info("Row: " + rows + " - Column: " + columns);
		LogUtils.info("StartRow: " + startRow + " - EndRow: " + endRow);

		data = new Object[(endRow - startRow) + 1][1];
		Hashtable<String, String> table = null;
		for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
			table = new Hashtable<>();
			for (int colNum = 0; colNum < columns; colNum++) {
				table.put(getCellData(colNum, 0), getCellData(colNum, rowNums));
			}
			data[rowNums - startRow][0] = table;
		}

		return data;
	}

	public Object[][] getDataHashTable(String excelPath, String sheetName) throws Exception {
		LogUtils.info("Excel Path: " + excelPath);
		Object[][] data = null;

		// Kiểm tra xem file có tồn tại không
		File f = new File(excelPath);
		if (!f.exists()) {
			LogUtils.info("File Excel path not found.");
			throw new IOException("File Excel path not found.");
		}

		// Load file và workbook
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook wb = new XSSFWorkbook(fis);
		sh = wb.getSheet(sheetName);

		int rows = getLastRowNum();  // Sử dụng sheet để lấy số hàng
		int columns = getColumns();  // Sử dụng sheet để lấy số cột

		LogUtils.info("Rows: " + rows + " - Columns: " + columns);

		// Khởi tạo mảng dữ liệu
		data = new Object[rows][1];
		Hashtable<String, String> table;

		// Duyệt qua các hàng từ hàng 1 (giả sử hàng 0 là tiêu đề)
		for (int rowNums = 1; rowNums <= rows; rowNums++) {
			table = new Hashtable<>();
			for (int colNum = 0; colNum < columns; colNum++) {
				String header = getCellData( colNum, 0); // Lấy dữ liệu tiêu đề cột
				String cellData = getCellData( colNum, rowNums); // Lấy dữ liệu từng ô
				if (cellData == null) {
					LogUtils.info("Data issue at row " + rowNums + ", column " + colNum);
					throw new RuntimeException("Invalid data at row " + rowNums + " and column " + colNum);
				}
				table.put(header, cellData);
			}
			data[rowNums - 1][0] = table;  // Gán bảng hash vào mảng dữ liệu
		}

		// Đóng workbook sau khi sử dụng
		wb.close();
		fis.close();

		return data;
	}

	public int findCellIndex(String columnName, String cellValue) {
		LogUtils.info("cellValue: "+ cellValue);
		try {
			// Lấy column index từ tên cột
			Integer colIndex = columns.get(columnName);

			if (colIndex == null) {
				throw new Exception("Column name doesn't exist.");
			}

			// Duyệt qua tất cả các hàng để tìm giá trị trong cột
			for (Row row : sh) {
				Cell cell = row.getCell(colIndex);

				if (cell != null && cell.toString().equals(cellValue)) {
					return row.getRowNum();  // Trả về index của hàng chứa giá trị
				}
			}
		} catch (Exception e) {
			LogUtils.info(e.getMessage());
		}

		return -1;  // Không tìm thấy giá trị
	}

	// Hàm để đọc danh sách URL từ Excel
	public List<String> getServiceUrls() {
		List<String> serviceUrls = new ArrayList<>();
		for (Row row : sh) {
			Cell cell = row.getCell(0);  // Giả định URL nằm ở cột đầu tiên
			if (cell != null) {
				serviceUrls.add(cell.getStringCellValue());  // Lấy URL từ ô đầu tiên
			}
		}
		return serviceUrls;
	}
	public static void createExcelFile(String fileName, String sheetName) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);

		try (FileOutputStream fos = new FileOutputStream(new File(fileName))) {
			workbook.write(fos);
			System.out.println("Tạo file Excel mới thành công: " + fileName);
		} catch (IOException e) {
			System.err.println("Lỗi khi tạo file Excel: " + e.getMessage());
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	// Đóng workbook sau khi hoàn thành
	public void close() throws IOException {
		if (wb != null) {
			wb.close();
		}
		if (fis != null) {
			fis.close();
		}
	}
}