package com.vtiger.genericUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib {
	/** used to read the data from excel workbook based on
	 * @author SHEKHAR
	 * @param pathName
	 * @param sheetName
	 * @return String[][]
	 */
	public static String pathName = "./testData/testScriptData.xlsx";
	   /**
	    * used to read the data from excel workook based on
	    * @author SHEKHAR
	    * @param shettName
	    * @param rowNum
	    * @param colNum
	    * @return
	    * @throws Throwable
	    */
		public String getExcelData(String sheetName , int rowNum , int colNum) throws Throwable {
			FileInputStream fis = new FileInputStream(pathName);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			wb.close();
			return row.getCell(colNum).getStringCellValue();
					
		}
	
	/**
	 * used to set data back to excel based on parameter
	 * @param shettName
	 * @param rowNum
	 * @param colNum
	 * @param data
	 * @throws Throwable
	 */
	
	public void setExcelData(String sheetName , int rowNum , int colNum ,String data) throws Throwable {
		FileInputStream fis = new FileInputStream(pathName);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.createCell(colNum);
		cel.setCellValue(data);
		FileOutputStream fos = new FileOutputStream(pathName);
				wb.write(fos);
		wb.close();
	}
	/**
	 * used to get the excel used row count
	 * @param shettName
	 * @return
	 * @throws Throwable
	 */
	public int getRowCount(String shettName)throws Throwable {
		FileInputStream fis = new FileInputStream(pathName);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(shettName);
		return sh.getLastRowNum();
				
	}

}
