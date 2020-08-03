package com.vtiger.genericUtils;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLib {
	public static String[][] getData(String pathName, String sheetName) {

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
				
			}
		}
        return data;
    }

	
	//Write data into excel also
}
