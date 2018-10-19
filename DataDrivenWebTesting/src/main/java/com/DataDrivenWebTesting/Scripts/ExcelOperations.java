package com.DataDrivenWebTesting.Scripts;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.DataDrivenWebTesting.Results.ResultsClass;
import com.DataDrivenWebTesting.TestNg.Testng;

public class ExcelOperations extends Testng
{
	ResultsClass htmlInt = new ResultsClass();
	public static String TempResultFile;
	public static String TestDescription;
	public static String CurrentFileName;
	public static String MethodName;
	public static int currentExecutionRowNumber;
	public String DataFile;
	String MasterconfigPath = DataPath+"\\masterconfig.xls";


	List <String> listExecutionClasses = new ArrayList<String>(0);
	List <String> listExecutionPackages = new ArrayList<String>(0);
	List <String> listExecutionStatus = new ArrayList<String>(0);
	List <String> listExecutionMethods = new ArrayList<String>(0);
	List <String> listExecutionMethodsDescriptions = new ArrayList<String>(0);
	List <String> listExecutionMethodsNumber = new ArrayList<String>(0);



	String filepath ="com.subbuchinni."; 
	String SrcDataFolder=null;

	String className =null;
	POIFSFileSystem file;

	public void setExcelFile(String Path,String SheetName) throws Exception {



		setExecutionDetails();
		try {
			for(int i=0;i<listExecutionClasses.size();i++)
			{

				if(listExecutionStatus.get(i).equalsIgnoreCase("Yes") || listExecutionStatus.get(i).equalsIgnoreCase("Y"))
				{
					SrcDataFolder=listExecutionClasses.get(i);

					className=filepath+listExecutionPackages.get(i)+"."+listExecutionClasses.get(i);

					Class<?> objclass =Class.forName(className);

					System.out.println(objclass);
					String Thrd=String.valueOf(Thread.currentThread().getId());

					System.out.println(Thrd);

					Object obj =null;

					System.out.println(String.class);

					Constructor constructor =objclass.getConstructor(String.class);

					obj = constructor.newInstance(Thrd);

					Method method =null;


					DataFile=DataPath+listExecutionClasses.get(i)+"\\"+listExecutionClasses.get(i)+"_Data.xls";

					CurrentFileName= DataFile;
					htmlInt.CurrentFileName=this.CurrentFileName;

					String strDatasheetFile =DataFile;



					String strCellvalues = null;
					file=new POIFSFileSystem(new FileInputStream(strDatasheetFile));


					HSSFWorkbook DataSheetExcelBook = new HSSFWorkbook(file);


					HSSFSheet DataExcelSheet = DataSheetExcelBook.getSheet("Scenerio");

					int totalnoRows = DataExcelSheet.getLastRowNum();

					HSSFRow rows =null;

					for(int j=1;j<=totalnoRows;j++)
					{
						rows = DataExcelSheet.getRow(j);

						String ExecutionMethodsStatus=rows.getCell(2).toString();

						if(ExecutionMethodsStatus.equalsIgnoreCase("Yes") || ExecutionMethodsStatus.equalsIgnoreCase("Y"))

						{
							listExecutionMethods.add(rows.getCell(0).toString());
							listExecutionMethodsDescriptions.add(rows.getCell(5).toString());
							listExecutionMethodsNumber.add(rows.getCell(1).toString());
							System.out.println("Methods was added to list -"+rows.getCell(0).toString());
							System.out.println("Methods Descriptions was added to list -"+rows.getCell(5).toString());
							System.out.println("Methods execution row number -"+rows.getCell(1).toString());

						}
					}

					for(int ExeMethCoun=0;ExeMethCoun<listExecutionMethods.size();ExeMethCoun++)
					{
						String RowNumexe=listExecutionMethodsNumber.get(ExeMethCoun);
						currentExecutionRowNumber=Integer.parseInt(RowNumexe);
						MethodName=listExecutionMethods.get(ExeMethCoun);
						htmlInt.MethodName=this.MethodName;
						Random rand = new Random();

						double  n = rand.nextInt(50)*1.13;

						TempResultFile =TempResult+n+".htm";
						TestDescription=listExecutionMethodsDescriptions.get(ExeMethCoun);

						htmlInt.HTMLInitialisation(TempResultFile,TestDescription);
						method =objclass.getMethod(listExecutionMethods.get(ExeMethCoun));

						System.out.println(method);
						method.invoke(obj);
					}
				}
			}

		} catch (Exception e){

			System.out.println(e.getMessage());

			System.out.println(e.getStackTrace());
		}
		finally
		{
			//DatasheetExcelFile.
		}

	}



	public void setExecutionDetails() throws IOException
	{
		// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(MasterconfigPath);

		String strMasterConfigFile =MasterconfigPath;

		POIFSFileSystem fs;

		String strCellvalue = null;
		fs=new POIFSFileSystem(new FileInputStream(strMasterConfigFile));


		HSSFWorkbook ExcelBook = new HSSFWorkbook(fs);


		HSSFSheet ExcelSheet = ExcelBook.getSheet("ClassPackageDetails");

		int totalRows = ExcelSheet.getLastRowNum();

		HSSFRow row =null;

		for(int i=1;i<=totalRows;i++)
		{
			row = ExcelSheet.getRow(i);

			String ExecutionStatus=row.getCell(3).toString();

			if(ExecutionStatus.equalsIgnoreCase("Yes") || ExecutionStatus.equalsIgnoreCase("Y"))

			{

				System.out.println(ExecutionStatus);

				listExecutionClasses.add(row.getCell(1).toString());
				System.out.println("Class was added to list -"+row.getCell(1).toString());

				listExecutionPackages.add(row.getCell(4).toString());
				System.out.println("Package was added to list -"+row.getCell(4).toString());

				listExecutionStatus.add(row.getCell(3).toString());
				System.out.println("Package was added to list -"+row.getCell(3).toString());


			}
		}

	}



	public Map<String,String> readData(String DataSheetName,String MethodName,int SheetRowNumber) throws  IOException
	{

		Map<String,String> data= new HashMap();

		try
		{
			//File file= new File(AbsolutePath+"\\"+"masterconfig.xlsx");

			FileInputStream inputStream = new FileInputStream(DataSheetName);

			HSSFWorkbook wrkbook= new HSSFWorkbook(inputStream);

			HSSFSheet DataExcelSheet = wrkbook.getSheet(MethodName);

			int rows =DataExcelSheet.getLastRowNum();
			HSSFRow RowValue;
			HSSFRow row =DataExcelSheet.getRow(0);

			RowValue= DataExcelSheet.getRow(SheetRowNumber);

			for(int i=0;i<row.getLastCellNum();i++)
			{
				RowValue.getCell(i).setCellType(RowValue.getCell(i).CELL_TYPE_STRING);

				String Value=RowValue.getCell(i).getStringCellValue();

				if(!Value.equalsIgnoreCase(""))
				{
					String KeyValue=DataExcelSheet.getRow(0).getCell(i).getStringCellValue();

					data.put(KeyValue, Value);
				}

			}


			//mastconfigwrkbook = new HSSFWorkbook(inputStream);
		}catch(Exception e)
		{

			System.out.println(e.getMessage());
		}

		return data;

	}
}
