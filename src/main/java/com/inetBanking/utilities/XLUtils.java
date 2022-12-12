package com.inetBanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static  XSSFWorkbook workbook;
	public static  XSSFSheet sheet;
	public  static XSSFRow row;
	public static XSSFCell col;

	
	

	public static int getRowCount(String path,String sheetname) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String path,String sheetname, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;

	}

	public static String getCellData(String path,String sheetname, int rowcount, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowcount);
		col = row.getCell(colnum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(col);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public void setCellData(String path,String sheetname, int rowcount, int colnum, String data) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetname);
		row = sheet.getRow(rowcount);
		col = row.getCell(colnum);
		col.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

}
