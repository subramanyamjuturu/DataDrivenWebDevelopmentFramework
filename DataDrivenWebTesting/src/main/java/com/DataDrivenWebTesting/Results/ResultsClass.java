package com.DataDrivenWebTesting.Results;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ResultsClass 
{
	
	
	// All these are for initializing purpose
	public String DateandTime=null;

	public String ExecutionStartingTime;
	public static String ExecutionDate;
	public static String CurrentFileName;
	public static String MethodName;
	public static String ResultFilePath;
	public String ExecutionEndingTime;
	public String ExecutionEndDate;
	public String OverAllTime;
	public long EndDate;
	public long startDate;
	public static double sumDate;
	public static double EndTime;
	public static double StartTime;
	public static String TotalTime;
	
	Cell cell;
	public static String AbsolutePathToProject =new File("").getAbsolutePath();
	public static String ScreenshotFolder=AbsolutePathToProject+"\\Results\\ScreenShots\\";
	
	//This method is for Initialization of External HTML report so that we can view the report in html format
	public void HTMLInitialisation(String TempResult,String Description)
	{
		try{
			// calling  DateAndTimeInitialisation for date and time Initialization in report
			DateAndTimeInitialisation();
			// Create new file
			String content = "<!DOCTYPE html><html><head><style>table {width:100%;}table, th, td {border: 1px solid black;}th, td {padding: 15px;text-align: left;}table#t01 tr:nth-child(even) {background-color: #eee;}table#t01 tr:nth-child(odd) {background-color: #fff;}table#t01 th {background-color: hsl(39, 100%, 50%);color: white;}table#t02 th {background-color: hsl(39, 100%, 50%);color: white;}</style></head><body><h2 style=\"background-color:powderblue; font-family:verdana;\">Subramanyam Juturu designed this result for automation testing</h2><table  style=\"background-color:rgb(240, 196, 108);\"><tr><th>Path : "+Description+"</th><th>Execution Date : "+ExecutionStartingTime+"</th><th>Execution Start Time : "+ExecutionDate+"</th>"+
					"</tr></table><table id=\"t01\" ><tr><th>Module</th><th>Expected Value</th><th>Task</th><th>Actual Value</th><th>Status</th></tr>";
			
			// Path of the Report storing in string path
			String path=TempResult;
			File file = new File(path);

			// If file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// code for writing report in the file(File operation code for External Report)
			FileWriter fw = new FileWriter(file.getAbsoluteFile());

			System.out.println(file.getAbsoluteFile());
			ResultFilePath=file.getAbsoluteFile().toString();
			System.out.println("path of the Result--"+ResultFilePath);
			BufferedWriter bw = new BufferedWriter(fw);

			// Write in file
			bw.write(content);

			// Close connection
			bw.close();

			System.out.println("Done");
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public void FinalResultWrite(String TempResult,String SheetName,String DataFile) throws IOException, InterruptedException
	{

		EndDateAndTimeInitialisation();
		String content = "<table id=\"t02\"><tr><th>Execution end date :"+ExecutionEndingTime+"</th><th>Execution end time : "+ExecutionEndDate+"</th><th>Overall Time taken :"+TotalTime+" minutes</th></tr></table>";

		String path=TempResult;
		File file = new File(path);

		// If file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);

		System.out.println(file.getAbsoluteFile());
		ResultFilePath=file.getAbsoluteFile().toString();
		System.out.println("path of the Result--"+ResultFilePath);
		//BufferedWriter bw = new BufferedWriter(fw);

		fw.write("\n"+content);

		// Write in file
		//bw.append(content);

		// Close connection
		fw.close();

		InsertResult(TempResult,SheetName,DataFile);

		System.out.println("Done");

	}
	
	public void InsertOverallStatus(String TempResultFile,String SheetName,String DataFile) throws IOException
	{
		String path=TempResultFile;
		File Inputfile = new File(path);

		// If file doesn't exists, then create it
		if (!Inputfile.exists()) {
			Inputfile.createNewFile();
		}



		System.out.println(Inputfile.getAbsoluteFile());
		ResultFilePath=Inputfile.getAbsoluteFile().toString();
		System.out.println("path of the Result--"+ResultFilePath);

		String resultData ;
		String Result= new String();
		BufferedReader br= new BufferedReader((new FileReader(Inputfile)));

		while ((resultData = br.readLine()) != null) {
			Result = Result.concat(resultData + " ");
		}



		if(Result != null && Result !="")
		{

			//String DataFile=CurrentFileName;

			FileInputStream DatasheetExcelFile = new FileInputStream(DataFile);

			String strDatasheetFile =DataFile;

			POIFSFileSystem file;

			String strCellvalues = null;
			file=new POIFSFileSystem(new FileInputStream(strDatasheetFile));


			HSSFWorkbook DataSheetExcelBook = new HSSFWorkbook(file);


			HSSFSheet DataExcelSheet = DataSheetExcelBook.getSheet("Scenerio");

			int totalnoRows = DataExcelSheet.getLastRowNum();

			HSSFRow rows =null;

			cell=null;

			try{
				for(int j=1;j<=totalnoRows;j++)
				{
					rows = DataExcelSheet.getRow(j);

					String ExecutionMethods=rows.getCell(0).toString();

					if(ExecutionMethods.equalsIgnoreCase(SheetName))
					{
						cell=DataExcelSheet.getRow(j).getCell(3);


						// XSSFWorkbook workbook = new HSSFWorkbook(file); 
						// XSSFCell cell = null;
						if(Result.contains("Fail") || Result.contains("fail") || Result.contains("FAIL"))
						{
							cell.setCellValue("FAIL");
						}
						else if(Result.contains("Pass") || Result.contains("pass") || Result.contains("PASS"))
						{
							cell.setCellValue("PASS");
						}
						else
						{
							cell.setCellValue("Not Completed");
						}
						DatasheetExcelFile.close();

						FileOutputStream out = new FileOutputStream(new File(strDatasheetFile));
						DataSheetExcelBook.write(out);

						DatasheetExcelFile.close();

						System.out.println("Done");

					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println();


		}
	}

	
	public void InsertResult(String TempResult,String SheetName,String DataFile) throws IOException
	{

		//String DataFile=DataFile;

		FileInputStream DatasheetExcelFile = new FileInputStream(DataFile);

		String strDatasheetFile =DataFile;

		POIFSFileSystem file;

		String strCellvalues = null;
		file=new POIFSFileSystem(new FileInputStream(strDatasheetFile));


		HSSFWorkbook DataSheetExcelBook = new HSSFWorkbook(file);


		HSSFSheet DataExcelSheet = DataSheetExcelBook.getSheet("Scenerio");

		int totalnoRows = DataExcelSheet.getLastRowNum();

		HSSFRow rows =null;

		cell=null;

		try{
			for(int j=1;j<=totalnoRows;j++)
			{
				rows = DataExcelSheet.getRow(j);

				String ExecutionMethods=rows.getCell(0).toString();

				if(ExecutionMethods.equalsIgnoreCase(SheetName))
				{
					cell=DataExcelSheet.getRow(j).getCell(4);


					// XSSFWorkbook workbook = new HSSFWorkbook(file); 
					// XSSFCell cell = null;

					HSSFHyperlink link = new HSSFHyperlink(org.apache.poi.hssf.usermodel.HSSFHyperlink.LINK_FILE);
					link.setAddress(TempResult);

					cell.setHyperlink(link);

					DatasheetExcelFile.close();

					FileOutputStream out = new FileOutputStream(new File(strDatasheetFile));
					DataSheetExcelBook.write(out);

					DatasheetExcelFile.close();

					System.out.println("Done");

				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println();

	}

	public String EndDateAndTimeInitialisation() throws InterruptedException
	{

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());

		Calendar calendar = Calendar.getInstance();
		EndTime = System.currentTimeMillis();

		sumDate = EndTime-StartTime;

		String Time [] = timeStamp.split("_");

		ExecutionEndingTime=Time[0];

		ExecutionEndDate= Time[1];


		double seconds =Math.round((sumDate/(60000)) *60);


		sumDate=((double)seconds/60);

		DecimalFormat df = new DecimalFormat("#########.##");
		TotalTime=df.format(sumDate);

		System.out.print(df.format(sumDate));
		System.out.println("Final Time***&&&%%----: "+seconds);

		System.out.println("Final Time in Minutes***&&&%%----: "+seconds/60);

		return DateandTime;
	}
	
	// This method is for getting Date and Time from System so that we can use in the report
	public String DateAndTimeInitialisation()
	{

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());

		Calendar calendar = Calendar.getInstance();
		StartTime = System.currentTimeMillis();

		String Time [] = timeStamp.split("_");

		ExecutionStartingTime=Time[0];

		ExecutionDate= Time[1];

		System.out.println(timeStamp);
		return DateandTime;
	}

	//This method is for inserting each and every step in the result section with detailed description
	public void ResultWrite(String TempResult,String Description,String ExpectedValue,String Action,String ActualValue,String status,WebDriver driver,boolean isScreenShot) throws Exception
	{
		String ScreenShotPath = null;
		if(isScreenShot)
		{
			//calling screenshot method and storing reference of screenshot in ScreenShotPath
			 ScreenShotPath =takeSnapShot(driver);
		}
		String content;
		if(status.equalsIgnoreCase("pass"))
		{
			//HTML code to insert in the report
			content= "<tr><td>"+Description+"</td><td>"+ExpectedValue+"</td><td>"+Action+"</td><td>"+ActualValue+"</td><td><a href=\""+ScreenShotPath+"\" style=\"color:Green;\" >"+status+"</a></td></tr>";

		}else
		{
			//HTML code to insert in the report
			content= "<tr><td>"+Description+"</td><td>"+ExpectedValue+"</td><td>"+Action+"</td><td>"+ActualValue+"</td><td><a href=\""+ScreenShotPath+"\" style=\"color:Red;\" >"+status+"</a></td></tr>";

		}
		String path=TempResult;
		File file = new File(path);

		// If file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);

		System.out.println(file.getAbsoluteFile());
		ResultFilePath=file.getAbsoluteFile().toString();
		//System.out.println("path of the Result--"+ResultFilePath);
		//BufferedWriter bw = new BufferedWriter(fw);

		fw.write("\n"+content);

		// Close connection
		fw.close();

		//InsertResult();

		//System.out.println("Done");

	}
	
	// This method is for complete screenshot taking purpose when we perform an operation with application.
	public static String takeSnapShot(WebDriver webdriver) throws Exception{


		File scrFile = ((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
		
		Random rand = new Random();
		double  n = rand.nextInt(50)*1.13;

		String  ScreenShotFile =ScreenshotFolder+n+".png";
		FileUtils.copyFile(scrFile, new File(ScreenShotFile));
		
		return ScreenShotFile;

	}
	
}
