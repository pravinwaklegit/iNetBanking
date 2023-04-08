package com.inetBanking.testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.inetBanking.utilities.XLUtils;

public class ReadingExcel {
	
	public static void main(String[] args) throws IOException
	{
		String path=System.getProperty("user.dir") + "/src/test/java/com/inetBanking/testData/testData.xlsx";
		FileInputStream fi=new FileInputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook(fi);
		XSSFSheet sheet=workbook.getSheet("sheet1");
		System.out.println(sheet);
		int rowcount=sheet.getLastRowNum();
		int cellcount=sheet.getRow(1).getLastCellNum();
		System.out.println(rowcount+" "+cellcount);
		
		for (int i = 1; i <= rowcount; i++) {
			
			XSSFRow row=sheet.getRow(i);
			for (int j = 0; j < cellcount; j++) {
				XSSFCell cell=row.getCell(j);
				System.out.print(cell.getStringCellValue());
				System.out.println();
						
//				switch(cell.getCellType()){
//				case STRING:System.out.print(cell.getStringCellValue());break;
//				case NUMERIC:System.out.print(cell.getNumericCellValue());break;
//				case BOOLEAN:System.out.print(cell.getBooleanCellValue());break;
//				}
			}
		}
	}
	
}
