package com.uiFramework.companyName.projectName.helper.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;

public class ExcelHelper {
	private Logger log = LoggerHelper.getLogger(ExcelHelper.class);
	
	public Object[][] getExcelData(String excelLocation, String sheetName) {
		
		try {
			Object dataSets[][] = null;
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			// Get sheetName from workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// Count number of active row
			int totalRow = sheet.getLastRowNum();
			//System.out.println(totalRow);
			// Count number of columns in row
			int totalColumn = sheet.getRow(0).getLastCellNum();
			//System.out.println(totalColumn);
			dataSets = new Object[totalRow+1][totalColumn];
			//Iterate each row one by one
			Iterator<Row> iterator = sheet.iterator();
			int i = 0;
			while (iterator.hasNext()) {
				i++;
				// Iterate each column
				Row row = iterator.next();
				Iterator<Cell> cellIterator = row.iterator();
				int j = 0;
				while(cellIterator.hasNext()) {
					j++;
					Cell cell = cellIterator.next();
					switch(cell.getCellTypeEnum()) {
					case STRING:
						dataSets[i-1][j-1] = cell.getStringCellValue();
						break;
					case NUMERIC:
						dataSets[i-1][j-1] = cell.getNumericCellValue();
						break;	
					case BOOLEAN:
						dataSets[i-1][j-1] = cell.getBooleanCellValue();
						break;
					case FORMULA:
						dataSets[i-1][j-1] = cell.getCellFormula();
						break;
					default:
						System.out.println("No match enum data type found");
						break;
				
					}
					
				}
				
			}
			return dataSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateResult(String excelLocation, String sheetName, String testCaseName, String testStatus){
		try{
			FileInputStream file = new FileInputStream(new File(excelLocation));
			// Create Workbook instance
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			// Get sheet Name from Workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			// count number of active rows in excel sheet
			int totalRow = sheet.getLastRowNum()+1;
			for(int i =1; i<totalRow; i++){
				XSSFRow r = sheet.getRow(i);
				String ce = r.getCell(0).getStringCellValue();
				if(ce.contains(testCaseName)){
					r.createCell(1).setCellValue(testStatus);
					file.close();
					log.info("result updated..");
					FileOutputStream out = new FileOutputStream(new File(excelLocation));
					workbook.write(out);
					out.close();
					break;
				}
			}
		}
		catch(Exception e){
			log.info(e.getCause());
		}
}
	
	
	
	public static void main(String[] args) {
		ExcelHelper excelHelper = new ExcelHelper();
		String excelLocation = ResourceHelper.getResource("\\src\\main\\resources\\configFile\\ExcelData.xlsx");
		//excelHelper.updateResult(excelLocation, "Sheet3","login","fail");
		excelHelper.updateResult(excelLocation, "Sheet3","register","failed");
		
	}
}
