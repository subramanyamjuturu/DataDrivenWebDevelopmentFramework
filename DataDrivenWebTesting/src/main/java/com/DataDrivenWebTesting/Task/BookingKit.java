package com.DataDrivenWebTesting.Task;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.DataDrivenWebTesting.Actions.Action;
import com.DataDrivenWebTesting.Results.ResultsClass;
import com.DataDrivenWebTesting.Scripts.*;
import com.DataDrivenWebTesting.TestNg.Testng;

public class BookingKit extends Testng{

	static String ObjectProprty;
	static String ObjectProprtyValue;


	static ResultsClass Report = new ResultsClass();
	static Action actions = new Action();
	static ExcelOperations fileoperations = new ExcelOperations();

	public BookingKit(String thread)
	{
		loadObjectProperties();

	}

	public void loadObjectProperties() 
	{
		try {
			ScriptExecuter.storeObjectProperteyValues();
			ScriptExecuter.storeObjectProperties();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void BookingKit() throws IOException, InterruptedException 
	{
		// getting object properties and driver details from Data sheet and storing in Collections
		try {
			Map<String,String> data =fileoperations.readData(CurrentFileName, "BookingKitDetails", currentExecutionRowNumber);
			Map<String,String> Logindata =fileoperations.readData(CurrentFileName, "LoginDetails", currentExecutionRowNumber);


			// Explicit wait using in code for specific code
			WebDriverWait wait = new WebDriverWait(driver, 15);

			//This is for Launching Dropbox and inside this method Detailed Report code is available.
			actions.browserLaunch(Logindata.get("URL_Data"), driver, TempResultFile, "Launching Browser");

			//Entering Email Id
			actions.enterText("emailid", "emailid", Logindata.get("Username"), driver, TempResultFile, "Entering Email Id");

			actions.enterPassword("Password", "Password", Logindata.get("IncorrectPassword"), driver, TempResultFile, "Entering Password");

			//clicking on Login
			actions.Click("LoginInButton", "LoginInButton", "LoginInButton", driver, TempResultFile, "clicking on LoginIn Button");

			// waiting for error message
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LoginForm_password_em_")));

			// checking whether error message is displaying or not using selenium java code
			if(driver.findElement(By.id("LoginForm_password_em_")).isDisplayed())
				Report.ResultWrite(TempResultFile, "User Login Verification","User should not to login with invalid credentials","Verification","User did not logged to the applications","Pass",driver,true);
			else
				Report.ResultWrite(TempResultFile, "User Login Verification","User should not to login with invalid credentials","Verification","User logged to the applications","Fail",driver,true);

			actions.ClearTex("emailid","emailid",driver);
			//Entering Email Id
			actions.enterText("emailid", "emailid", Logindata.get("Username"), driver, TempResultFile, "Entering Email Id");

			actions.ClearTex("Password","Password",driver);
			actions.enterPassword("Password", "Password", Logindata.get("Password"), driver, TempResultFile, "Entering Password");

			//clicking on Login
			actions.Click("LoginInButton", "LoginInButton", "LoginInButton", driver, TempResultFile, "clicking on LoginIn Button");

			// waiting for application load time
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-name")));

			// checking whether user logged to application or not using selenium java code
			if(driver.findElement(By.className("user-name")).isDisplayed())
				Report.ResultWrite(TempResultFile, "User Login Verification","User should login to the application with valid credentials","Verification","User logged to the application","Pass",driver,true);
			else
				Report.ResultWrite(TempResultFile, "User Login Verification","User should login to the application with valid credentials","Verification","User failed to login to the application","Fail",driver,true);

			// click on QA Assessment Account
			actions.Click("UserAccount", "UserAccount", "QA Assessment Account", driver, TempResultFile, "click on QA Assessment Account");

			// Selecting edit profile from the drop down
			actions.Click("UserAccountDropDown", "UserAccountDropDown", "QA Assessment Account", driver, TempResultFile, "Selecting edit profile from the drop down");

			// variables using for language checking and for the loops operations
			int count;
			boolean condition = false;
			boolean secondloop = false;
			String DefaultLanguage ;
			String ChangedLanguage ;

			//Getting default laguage of the user
			DefaultLanguage = driver.findElement(By.xpath("//div[@class='left']//div")).getText();

			do {
				// waiting till language selection page appearing
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("User[language]")));

				// getting all languages available in application and storing in list
				List<WebElement> ele = driver.findElements(By.name("User[language]"));

				// for loop for language selection and verification process
				for(int i=0;i<ele.size();i++)
				{
					if(ele.get(i).isSelected())
					{
						count=i+1;

						// executing only for the first time and printing default selected language
						if(!secondloop)
							Report.ResultWrite(TempResultFile, "Default Selected Language verification","Default Selected Language is","Verification","Default Selected Language is: "+driver.findElement(By.xpath("(//input[@name='User[language]']//..//span)["+count+"]")).getText(),"Pass",driver,true);

						// executing only for the second time and printing second selected language
						if(secondloop)
						{
							Report.ResultWrite(TempResultFile, "Language verification","Language changed to: ","Verification","Language changed to: "+driver.findElement(By.xpath("(//input[@name='User[language]']//..//span)["+count+"]")).getText(),"Pass",driver,true);
							condition= false;
							break;
						}
						Actions action = new Actions(driver);

						// language changing code 
						if(i>0)
						{
							action.moveToElement(ele.get(ele.size()-1));
							action.perform();
							ele.get(i-1).click();

							ChangedLanguage= driver.findElement(By.xpath("//div[@class='left']//div")).getText();

						}
						else
						{
							action.moveToElement(ele.get(ele.size()-1));
							action.perform();
							ele.get(i+1).click();

							ChangedLanguage= driver.findElement(By.xpath("//div[@class='left']//div")).getText();

						}

						// checking whether language is changing or not before clicking on save button
						if(DefaultLanguage.equalsIgnoreCase(ChangedLanguage))
							Report.ResultWrite(TempResultFile, "Language verification","Language Should not changed before clicking on save button","Verification","Language did not changed before clicking on save button","Pass",driver,true);
						else
							Report.ResultWrite(TempResultFile, "Language verification","Language Should not changed before clicking on save button","Verification","Language changed before clicking on save button and changed language is "+driver.findElement(By.xpath("(//input[@name='User[language]']//..//span)["+count--+"]")).getText(),"Fail",driver,true);

						// saving language 
						actions.Click("SaveButton", "SaveButton", "SaveButton", driver, TempResultFile, "clicking on Save Button");

						secondloop= true;
						condition= true;
						break;

					}
				}
			}while(condition);

			ChangedLanguage= driver.findElement(By.xpath("//div[@class='left']//div")).getText();

			// checking whether language is changed or not
			
			// checking whether language is changing or not before clicking on save button
			if(!DefaultLanguage.equalsIgnoreCase(ChangedLanguage))
				Report.ResultWrite(TempResultFile, "Changed language verification","Language Should change Sucessfully","Verification","Language changed Sucessfully to "+ChangedLanguage,"Pass",driver,true);
			else
				Report.ResultWrite(TempResultFile, "Changed language verification","Language Should change Sucessfully","Verification","Language failed to change Sucessfully to "+ChangedLanguage,"Fail",driver,true);

			//Selecting the Dashboard tab
			actions.Click("DashBoard", "DashBoard", "DashBoard", driver, TempResultFile, "click on DashBoard");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-name")));

			// click on QA Assessment Account
			actions.Click("UserAccount", "UserAccount", "QA Assessment Account", driver, TempResultFile, "click on QA Assessment Account");

			//logouting from application
			actions.Click("Logout", "Logout", "Logout", driver, TempResultFile, "click on Logout");

			Report.FinalResultWrite(TempResultFile,MethodName,CurrentFileName);
			Report.InsertOverallStatus(TempResultFile,MethodName,CurrentFileName);



		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			Report.FinalResultWrite(TempResultFile,MethodName,CurrentFileName);
			Report.InsertOverallStatus(TempResultFile,MethodName,CurrentFileName);

		}
	}
}
