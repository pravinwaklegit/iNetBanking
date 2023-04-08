package com.inetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	//String static path=null;
	
//	XLUtils(String path){
//		this.path=path;
//	}
	
	public static int getRowCount(String xlfile,String xlsheet) throws IOException
	{
		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook();
		System.out.println(xlsheet);
		sheet=workbook.getSheet(xlsheet);
		sheet=workbook.getSheetAt(0);
		if (sheet == null) {
			   throw new IllegalArgumentException("No sheet exists with name " + sheet);
			}
		int rowcount=sheet.getLastRowNum();
		System.out.println("rowcount"+rowcount);
		workbook.close();
		fi.close();
		return rowcount;
		
	}
	
	public static int getCellCount(String xlfile,String xlsheet, int rownum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook();
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public static String getCellData(String xlfile,String xlsheet, int rownum, int colnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook();
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data=formatter.formatCellValue(cell); //Returns formatted value of cell as string regardless of cell value
		}
		catch(Exception e)
		{
			data="";
		}
		
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String xlfile,String xlsheet, int rownum,int colnum,String data) throws IOException
	{
		fi=new FileInputStream(xlfile);
		workbook=new XSSFWorkbook();
		sheet=workbook.getSheet(xlsheet);
		row=sheet.getRow(rownum);
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		
		fo=new FileOutputStream(xlfile);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	
	
	
	
}
