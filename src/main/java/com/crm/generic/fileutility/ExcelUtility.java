package com.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtility {
	public String getDataFromExcel(String sheetName, int rowno,int cellNum) throws Throwable, IOException
	{
	FileInputStream fis=new FileInputStream("./testdata/TestScriptForORG.xlsx");
	Workbook wb = WorkbookFactory.create(fis);
	String data = wb.getSheet(sheetName).getRow(rowno).getCell(cellNum).getStringCellValue();
	wb.close();
		return data;
	}
	
	public int getRowcount(String sheetName) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testdata/TestScriptForORG.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rowcount = wb.getSheet(sheetName).getLastRowNum();
		wb.close();
		return rowcount;
	}
	
	
	public void setDataIntoExcel(String sheetName, int rowno,int cellNum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./testdata/TestScriptForORG.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowno).createCell(cellNum).setCellValue(data);;
		
		FileOutputStream fos=new FileOutputStream("./testdata/TestScriptForORG.xlsx");
		wb.write(fos);
		wb.close();
	}

}
