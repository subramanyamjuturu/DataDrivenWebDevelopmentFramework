package com.DataDrivenWebTesting.TestNg;

import org.testng.annotations.Test;

import com.DataDrivenWebTesting.Results.ResultsClass;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Testng {
	
	ResultsClass htmlInt = new ResultsClass();
	public static String TestDescription;
	public static String CurrentFileName;
	public static String MethodName;
	public static int currentExecutionRowNumber;
	
	List <String> listExecutionClasses = new ArrayList<String>(0);
	List <String> listExecutionPackages = new ArrayList<String>(0);
	List <String> listExecutionStatus = new ArrayList<String>(0);
	List <String> listExecutionMethods = new ArrayList<String>(0);
	List <String> listExecutionMethodsDescriptions = new ArrayList<String>(0);
	List <String> listExecutionMethodsNumber = new ArrayList<String>(0);

	String filepath ="com.DataDrivenWebTesting."; 
	String SrcDataFolder=null;

	String className =null;
	POIFSFileSystem file;
	


	public String strAbsolutePath =new File("").getAbsolutePath();
	public String ResourcePath = strAbsolutePath+"\\Resources\\";
	public String DataPath = strAbsolutePath+"\\data\\";
	public String ChromeDriverPath = ResourcePath+"chromedriver.exe";
	public String ResultPath = strAbsolutePath+"\\Results";
	public String TempResult=ResultPath+"\\TemproryResultFile";
	String MasterconfigPath = DataPath+"\\masterconfig.xls";
	public static String TempResultFile;
	public String DataFilepath;


	ResultsClass Report = new ResultsClass();
	public static WebDriver driver;

	@Test
	public void f() {
	}
	@BeforeClass
	public void beforeClass() throws Exception
	{
		File folder = new File(ResourcePath);
		File[] listOfFiles = folder.listFiles();
		
		System.setProperty("webdriver.chrome.driver",listOfFiles[0].toString());	
		//Launching chrome Browser
		driver = new ChromeDriver();
		
		setExcelFile(DataPath,"ClassPackageDetails");
		
		System.out.println("Done");
		//Mathematical Random  number function just for external Report purpose
		Random rand = new Random();
		double  n = rand.nextInt(50)*1.13;
		
		//This line is for saving result in Result folder In our project location
		TempResultFile =TempResult+n+".htm";
		
		//This is for external Report for the Automation execution and it contains detailed step by step execution line which we performed in automation including with screenshots
		Report.HTMLInitialisation(TempResultFile, ResultPath);


	}
	
	

//	@AfterClass
//	public void afterClass() throws IOException, InterruptedException {
//		driver.quit();
//		
//		Report.FinalResultWrite(TempResultFile,htmlInt.MethodName,htmlInt.CurrentFileName);
//	}
//	
	public void setExcelFile(String Path,String SheetName) throws Exception {



		setExecutionDetails();
		try {
			for(int i=0;i<listExecutionClasses.size();i++)
			{

				//List <String> listExecutionMethods = new ArrayList<String>(0);

				/*filepath= filepath+listExecutionPackages.get(i)+".";
					className=filepath+listExecutionClasses.get(i);*/

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


					DataFilepath=DataPath+listExecutionClasses.get(i)+"\\"+listExecutionClasses.get(i)+"_Data.xls";

					CurrentFileName= DataFilepath;
					htmlInt.CurrentFileName=this.CurrentFileName;
					//FileInputStream DatasheetExcelFile = new FileInputStream(DataFile);

					String strDatasheetFile =DataFilepath;



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



		// Open the Excel file


	}



}
