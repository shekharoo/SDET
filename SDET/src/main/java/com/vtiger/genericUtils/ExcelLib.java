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
	
	 public String[][] getData(String sheetName) {

	        String[][] data = null;
	        int k = 0;
	        int l = 0;
	        File file = null;
	        FileInputStream fin = null;
	        Workbook wb = null;
	        Sheet sh = null;

	        try {
	            file = new File(pathName);
	            fin = new FileInputStream(file);
	            wb = WorkbookFactory.create(fin);
	            sh = wb.getSheet(sheetName);
	            int lr = sh.getLastRowNum();
	            int lc = sh.getRow(lr).getLastCellNum();
	            data = new String[lr][lc];
	            for (int i = 1; i < lr+1; i++) {
	                l = 0;
	                Row r = sh.getRow(i);
	                //int k=0;
	                for (int j = 0; j < lc; j++) {
	                    Cell cell = r.getCell(j);

	                    //Cell c = r.getCell(j);
	                    String value = new DataFormatter().formatCellValue(cell);
	                    data[k][l] = value;
	                    l++;
	                }
	                k++;
	            }
	        } catch (Exception e) {
	        	//log.info("Error while fetching Records from Excel"+e);
	        }
	       /*****************************/
	     finally {
				try {
					file = null;
					wb.close();
					wb = null;
					fin.close();
					fin = null;
				} catch (Exception e) {
					//log.info("Unable to close the files"+e);
				}
			}
	        return data;
	    }


}
