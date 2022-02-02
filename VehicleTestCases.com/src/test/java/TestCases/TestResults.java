package TestCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestResults {

	String excelPath = "././././ExcelSheets/Results.xlsx";
	int totalRow;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	FileInputStream inputFile;
	FileOutputStream outputFile;
	Row row;
	File file;

	public void writeTestResult(String testCasenNm, String[] result) {
		try {
			file = new File(excelPath);
			inputFile = new FileInputStream(file);
			workbook = new XSSFWorkbook(inputFile);
			sheet = workbook.getSheet("Sheet1");

			totalRow = sheet.getPhysicalNumberOfRows();

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			Date date = new Date();
			row = null; 		

			if(sheet.getRow(totalRow) == null) {
				row = sheet.createRow(totalRow);
			} 

			sheet.getRow(totalRow).createCell(0).setCellValue(formatter.format(date));
			sheet.getRow(totalRow).createCell(1).setCellValue(testCasenNm);
			for (int i = 2,j = 0; j < result.length; i++,j++) {
				sheet.getRow(totalRow).createCell(i).setCellValue(result[j]);
			}
			outputFile = new FileOutputStream(file);
			workbook.write(outputFile);
			outputFile.close();
			inputFile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeFile() throws Exception {
		workbook.close();
	}
}