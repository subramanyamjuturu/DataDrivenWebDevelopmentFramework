Step by step explanation for automation setup and Scripts execution

step 1: Download the eclipse from the below link
http://www.eclipse.org/downloads/packages/release/helios/sr1/eclipse-ide-java-ee-developers

step 2: Download Java development kit(i.e. JDK) from the below link
https://www.oracle.com/technetwork/java/javase/downloads/index.html

step 3: Install java in the system and add the environment variable. Please follow below link for installation of Java and setting an environment variable in your system

step 4: Download my project from the below link(i.e. click on Clone or download >> click on Download Zip)
https://github.com/subramanyamjuturu/OptileDatadrivenFramework

tep 5: Extract the project from the Zip file

step 6: Open downloaded Eclipse and select a folder where you want your workspace  to be in your system

step 7: Once Eclipse is opened in your system, import my project by following below steps 
(Click on File which will be on the left side top corner in the eclipse >> click on import >> Select General option on the new pop-up box >> click on Existing Projects in workspace >> click on Next >> click on the Select root directory >> click on the Browse button >> Select the extracted project >> click on finish )

step 8: Click on windows, on the top of the eclipse which is placed next Help

step 9: Click on show view >> click on project Explorer

step 10: Double-click on the pom.xml and click on pom.xml which is placed next to Effective pom in newly opened window

step 11: Left click on the opened POM.xml >> click on Run as >> select maven install

step 12: Once maven install completes in the eclipse, Double-click on src/main/java in the opened DataDrivenWebTesting under project explorer window in eclipse

step 13: Double-Click on com.DataDrivenWebTesting.Task package and again Double click on BookingKit.java

step 14: Left click on the opened java code >> click on Run as >> click on Run configurations >> select project >> select suite and click on browse and select project and select testng file which was placed under this project(execution starts now)

step 15: Once execution is completed, open the result path shown in the eclipse console

step 16: Follow below steps for opening the result in the datasheet
Result can be shown in Excel sheet(Data File) in the below format
to open data sheet please follow Path of the Data file in the project folder -\\DataDrivenWebTesting\data\BookingKit\BookingKit_Data.xls
Open the excel file and click on Scenerio sheet, you can find the method names from the 2nd row under methodname column and click on results cell which is parallel to method names, Automatically result will be opened in a default browser in html format.

Note: update Username and password in the LoginDetails sheet in the BookingKit_Data.xls excelsheet which is placed under BookingKit folder under data folder in the DataDrivenWebTesting project.