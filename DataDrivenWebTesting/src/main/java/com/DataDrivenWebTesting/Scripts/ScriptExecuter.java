package com.DataDrivenWebTesting.Scripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ScriptExecuter {

	public RemoteWebDriver webDriver=null;
	public String thread=null;
	public static String strAbsolutePath =new File("").getAbsolutePath();
	public static String DataPath = strAbsolutePath+"\\data";

	public static HashMap <String,String> Objpropertey = new HashMap<String,String>();
	public static HashMap <String,String> ObjproperteyValue = new HashMap<String,String>();

	static String ObjectPropertyPath = DataPath+"\\objectRepositry.xls";

	public void CreateDriver()
	{

	}

	public static void storeObjectProperties() throws IOException
	{
		// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(ObjectPropertyPath);

		String strObjectPropertyFile =ObjectPropertyPath;

		POIFSFileSystem fs;

		String strCellvalue = null;
		fs=new POIFSFileSystem(new FileInputStream(strObjectPropertyFile));


		HSSFWorkbook ExcelBook = new HSSFWorkbook(fs);


		HSSFSheet ExcelSheet = ExcelBook.getSheet("objectPropertiesSheet");

		int totalRows = ExcelSheet.getLastRowNum();

		HSSFRow row =null;
		int ObjectPropertiesCount=0;

		for(int i=1;i<=totalRows;i++)
		{
			row = ExcelSheet.getRow(i);
			if(!row.getCell(0).toString().equals(null) && !row.getCell(0).toString().equals(""))
			{
				ObjectPropertiesCount++;
				Objpropertey.put(row.getCell(0).toString(), row.getCell(1).toString());


			}
			if(i==totalRows)
			{
				
				System.out.println("total No of Objects stored into collections are--"+ObjectPropertiesCount);
			}

		}



		/*	Iterator <Row> rowIterator = ExcelSheet.iterator();

		while(rowIterator.hasNext())
		{
			Row row=rowIterator.next();
		}

		int totalRows = ExcelSheet.getLastRowNum();

		HSSFRow row =null;*/



	}

	public static void storeObjectProperteyValues() throws FileNotFoundException, IOException
	{
		// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(ObjectPropertyPath);

		String strObjectPropertyFile =ObjectPropertyPath;

		POIFSFileSystem fs;

		String strCellvalue = null;
		fs=new POIFSFileSystem(new FileInputStream(strObjectPropertyFile));


		HSSFWorkbook ExcelBook = new HSSFWorkbook(fs);


		HSSFSheet ExcelSheet = ExcelBook.getSheet("objectPropertiesSheet");

		int totalRows = ExcelSheet.getLastRowNum();
		
		int ObjectProperteyValuesCount=0;

		HSSFRow row =null;

		for(int i=1;i<=totalRows;i++)
		{
			row = ExcelSheet.getRow(i);
			if(!row.getCell(0).toString().equals(null) && !row.getCell(0).toString().equals(""))
			{

				ObjectProperteyValuesCount++;
				ObjproperteyValue.put(row.getCell(0).toString(), row.getCell(2).toString());
				System.out.println(row.getCell(0).toString());

			}
			if(i==totalRows)
			{
				
				System.out.println("total No of Objects stored into collections are--"+ObjectProperteyValuesCount);
			}

		}


	}
}
