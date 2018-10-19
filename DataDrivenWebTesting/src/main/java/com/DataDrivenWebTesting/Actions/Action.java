package com.DataDrivenWebTesting.Actions;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DataDrivenWebTesting.Results.ResultsClass;
import com.DataDrivenWebTesting.Scripts.ScriptExecuter;


public class Action 
{
	ScriptExecuter scriptexecuter= new ScriptExecuter();
	public static ArrayList passList= new ArrayList();
	static ResultsClass report = new ResultsClass();
	String ObjectProprty;
	String ObjectProprtyValue;

	//This method is for entering text to any web applications 
	public void enterText(String ObjectProperty,String ObjectPropertyValue,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		try
		{
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for entering text
				driver.findElement(By.name(ObjectProprtyValue)).sendKeys(Text);
				//Marking status as true so that in result we can view pass status
				Status=true;
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				driver.findElement(By.id(ObjectProprtyValue)).sendKeys(Text);
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				driver.findElement(By.xpath(ObjectProprtyValue)).sendKeys(Text);
				Status=true;

			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Enter text",Text,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Enter text",Text,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Enter text",Text,"Fail",driver,true);
		}


	}
	//This method is for entering Password to any web applications 
	public void enterPassword(String ObjectProperty,String ObjectPropertyValue,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		try
		{
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for entering text
				driver.findElement(By.name(ObjectProprtyValue)).sendKeys(Text);
				//Marking status as true so that in result we can view pass status
				Status=true;
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				driver.findElement(By.id(ObjectProprtyValue)).sendKeys(Text);
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				driver.findElement(By.xpath(ObjectProprtyValue)).sendKeys(Text);
				Status=true;

			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,"*********","Enter Password","*********","Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,"*********","Enter Password","*********","Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,"*********","Enter Password","*********","Fail",driver,true);
		}


	}

	//This method is for entering text to any web applications 
	public void selectValue(String ObjectProperty,String ObjectPropertyValue,String DropDownOption,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;
		Select dropdown = null ;
		WebDriverWait wait = new WebDriverWait(driver, 15);

		try
		{
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjectProprtyValue)));

				//selenium code for Selecting Dropdown value
				dropdown = new Select(driver.findElement(By.name(ObjectProprtyValue)));
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectProprtyValue)));

				dropdown = new Select(driver.findElement(By.id(ObjectProprtyValue)));
			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectProprtyValue)));

				dropdown = new Select(driver.findElement(By.xpath(ObjectProprtyValue)));
			}

			if(DropDownOption.equalsIgnoreCase("VisibleText"))
			{
				dropdown.selectByVisibleText(Text);
				//Marking status as true so that in result we can view pass status
				Status=true;
			}
			else if(DropDownOption.equalsIgnoreCase("Index"))
			{
				int value= Integer.parseInt(Text);
				dropdown.selectByIndex(value);
				Status=true;
			}
			else if(DropDownOption.equalsIgnoreCase("Value"))
			{
				dropdown.selectByValue(Text);
				Status=true;
			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Selecting DropDown",Text,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Selecting DropDown",Text,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Selecting DropDown",Text,"Fail",driver,true);
		}


	}



	public void VerifyTextDirectObjProperty(String ObjectProprty,String ObjectProprtyValue,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		//ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		//ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		String UIText = null;


		try
		{
			WebElement element;
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjectProprtyValue)));
				element = driver.findElement(By.name(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				//selenium code for clicking
				UIText = driver.findElement(By.name(ObjectProprtyValue)).getText();
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectProprtyValue)));
				element = driver.findElement(By.id(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				UIText = driver.findElement(By.id(ObjectProprtyValue)).getText();
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectProprtyValue)));
				element = driver.findElement(By.xpath(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				Thread.sleep(1000);
				
				UIText = driver.findElement(By.xpath(ObjectProprtyValue)).getText();

			}
			else if(ObjectProprty.equalsIgnoreCase("Class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ObjectProprtyValue)));
				element = driver.findElement(By.className(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				UIText = driver.findElement(By.className(ObjectProprtyValue)).getText();
			}
			
			if(UIText.contains(Text))
			{
				System.out.println("Pass");
				System.out.println("UI Text--"+UIText);
				Status=true;
			}
			else
			{
				System.out.println("Fail");
				System.out.println("UI Text--"+UIText);
				Status=false;
			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Verifying",UIText,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Verifying",UIText,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Verifying",Text,"Fail",driver,true);
			System.out.println(e.getMessage());
		}


	}
	public void VerifyText(String ObjectProperty,String ObjectPropertyValue,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		String UIText = null;


		try
		{
			WebElement element;
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjectProprtyValue)));
				element = driver.findElement(By.name(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				//selenium code for clicking
				UIText = driver.findElement(By.name(ObjectProprtyValue)).getText();
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectProprtyValue)));
				element = driver.findElement(By.id(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				UIText = driver.findElement(By.id(ObjectProprtyValue)).getText();
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectProprtyValue)));
				element = driver.findElement(By.xpath(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				Thread.sleep(1000);
				
				UIText = driver.findElement(By.xpath(ObjectProprtyValue)).getText();

			}
			else if(ObjectProprty.equalsIgnoreCase("Class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ObjectProprtyValue)));
				element = driver.findElement(By.className(ObjectProprtyValue));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				
				UIText = driver.findElement(By.className(ObjectProprtyValue)).getText();
			}
			
			if(UIText.contains(Text))
			{
				System.out.println("Pass");
				System.out.println("UI Text--"+UIText);
				Status=true;
			}
			else
			{
				System.out.println("Fail");
				System.out.println("UI Text--"+UIText);
				Status=false;
			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Verifying",UIText,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Verifying",UIText,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Verifying",Text,"Fail",driver,true);
			System.out.println(e.getMessage());
		}


	}
	public void Select(String ObjectProprty,String ObjectProprtyValue,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		//ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		//ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		WebDriverWait wait = new WebDriverWait(driver, 15);


		try
		{
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjectProprtyValue)));

				//selenium code for clicking
				driver.findElement(By.name(ObjectProprtyValue)).click();
				Status=true;
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectProprtyValue)));
				driver.findElement(By.id(ObjectProprtyValue)).click();
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectProprtyValue)));
				driver.findElement(By.xpath(ObjectProprtyValue)).click();
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ObjectProprtyValue)));
				driver.findElement(By.className(ObjectProprtyValue)).click();
				Status=true;

			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Click",Text,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Click",Text,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Click",Text,"Fail",driver,true);
		}


	}
	
	public void Click(String ObjectProperty,String ObjectPropertyValue,String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
		ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		WebDriverWait wait = new WebDriverWait(driver, 15);


		try
		{
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjectProprtyValue)));

				//selenium code for clicking
				driver.findElement(By.name(ObjectProprtyValue)).click();
				Status=true;
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectProprtyValue)));
				driver.findElement(By.id(ObjectProprtyValue)).click();
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectProprtyValue)));
				driver.findElement(By.xpath(ObjectProprtyValue)).click();
				Status=true;

			}
			else if(ObjectProprty.equalsIgnoreCase("Class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ObjectProprtyValue)));
				driver.findElement(By.className(ObjectProprtyValue)).click();
				Status=true;

			}

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Click",Text,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Click",Text,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Click",Text,"Fail",driver,true);
		}


	}
	public void ClearTex(String ObjectProperty,String ObjectPropertyValue,WebDriver driver) throws Exception
	{
		ObjectProprty=ScriptExecuter.Objpropertey.get(ObjectProperty);
		ObjectProprtyValue=ScriptExecuter.ObjproperteyValue.get(ObjectPropertyValue);

		WebDriverWait wait = new WebDriverWait(driver, 15);

		try
		{
			//checking Object property with any of these and performing the respective operation
			if(ObjectProprty.equalsIgnoreCase("Name"))
			{
				//selenium code for waiting until element is displayed
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(ObjectProprtyValue)));
				//selenium code for clearing
				driver.findElement(By.name(ObjectProprtyValue)).clear();
			}
			else if(ObjectProprty.equalsIgnoreCase("Id"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ObjectProprtyValue)));
				driver.findElement(By.id(ObjectProprtyValue)).clear();

			}
			else if(ObjectProprty.equalsIgnoreCase("Xpath"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ObjectProprtyValue)));
				driver.findElement(By.xpath(ObjectProprtyValue)).clear();

			}
			else if(ObjectProprty.equalsIgnoreCase("Class"))
			{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(ObjectProprtyValue)));
				driver.findElement(By.className(ObjectProprtyValue)).clear();

			}
			
			Thread.sleep(100);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	
	public static String pageSource(WebDriver driver)
	{
		String source=driver.getPageSource();

		return source;

	}


	public void browserLaunch(String Text,WebDriver driver,String ResultPath,String TestDescription) throws Exception
	{
	
		//Initializing the status as false so that in case if we get any exception or any error in the script it will show fail in the result
		boolean Status =false;

		try
		{
			driver.manage().deleteAllCookies();
			//Launching URL
			driver.get(Text);

			//Maximizing window
			driver.manage().window().maximize();
			Status=true;

			if(Status)
			{
				//Storing all Result status in ArrayList so that it will be easy to print final status as Pass in we get all Pass elements in Array list
				passList.add("Pass");
				report.ResultWrite(ResultPath, TestDescription,Text,"Browser Launch",Text,"Pass",driver,true);
			}
			else
			{
				passList.add("Fail");
				//Calling ResultWrite method to insert in the final result once we complete this action
				report.ResultWrite(ResultPath, TestDescription,Text,"Browser Launch",Text,"Fail",driver,true);
			}
		}catch(Exception e)
		{
			//Printing fail in the result in case if we get any exception in middle of execution
			passList.add("Fail");
			report.ResultWrite(ResultPath, TestDescription,Text,"Browser Launch",Text,"Fail",driver,true);
		}


	}
}
