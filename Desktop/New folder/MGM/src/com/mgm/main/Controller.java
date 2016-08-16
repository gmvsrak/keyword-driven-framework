package com.mgm.main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.mgm.report.ModuleStats;
import com.mgm.report.ModuleTestCasesStats;
import com.mgm.report.TestStepStats;
import com.mgm.util.DurationTracker;
import com.mgm.util.Functions;
import com.mgm.util.ReportsUtil;
import com.mgm.xls.ExcelOperations;

public class Controller extends Keyword{
	
	String result="false";
	ReportsUtil reportsUtil=new ReportsUtil();
	ModuleStats moduleStats;
	boolean stepstatus;
	
	
	
	public Controller(){
		super();
		
	}
	
public void startTesting() {
		
		this.setRunTestApp((String) testCONFIG.get("RunTestApp")); 
		this.getRunTestApp();
		this.setRunTest((String) testCONFIG.get("RunTest"));
		this.getRunTest();
		this.getBrowser();
		this.getModules(); 
		this.setCaptureScreenShot((String) testCONFIG.get("CaptureScreenShot"));
		this.getCaptureScreenShot();
}

public void testAppMain() throws IOException, InterruptedException{
	System.out.println("runTest"+super.getRunTest());
	
	if(getRunTest().equalsIgnoreCase("regressionSuite") || getRunTest().equalsIgnoreCase("sanitySuite")){
		//Thread.sleep(20*1000);
		System.out.println("runTest = ALL "+getBrowser());
			this.setLaunchBrowser(getBrowser());
			System.out.println("ELSE IF launchBrowser "+this.getLaunchBrowser());
			this.setDisplayBrowserVersion(reportsUtil.getBrowserVersion(this.getLaunchBrowser())); 
			System.out.println("displayBrowserVersion "+this.getDisplayBrowserVersion());
			Thread.sleep(5000);
				initializeAndRun(getModules());
		
	}
	
	}
public  void initializeAndRun(String runTestfile) throws IOException, InterruptedException{
	File testFiles = new File(System.getProperty("user.dir")+ "/src/files.modules/"+ runTestfile);
	if(testFiles.isDirectory()){
		String[] files = testFiles.list();
		setTestCasesfileAssigned(false);
		setTestDatafileAssigned(false);
		for(String fileName : files){
					initialize(fileName);
		}
	}
	//Thread.sleep(5000);
	testApp();

}

public  void initialize(String fileName) throws IOException {

	String srcFolder;
	
	srcFolder = System.getProperty("user.dir")+ "/src/files.modules/";

	// initialize test suite controller excel file
	if(fileName.contains("Controller") && (isTestCasesfileAssigned()==false)){
		System.out.println("Controller excel file is : " +fileName);
	    setController(new ExcelOperations(srcFolder+getModules()+"/"+fileName));
	    getLog().debug("controller file : " + fileName);
	    setTestCasesfileAssigned(true);
	}
	
	// initialize test data excel file
	if(fileName.contains("TestData") && isTestDatafileAssigned()==false){
				getLog().debug("test data  file : " +fileName);
				setTestData(new ExcelOperations(srcFolder+getModules()+"/"+fileName));
				setTestDatafileAssigned(true);
			}
	}

public  void testApp() throws InterruptedException, IOException {
	//String actualTestCaseExec = "";
	moduleStats = new ModuleStats();
	DurationTracker durationTracker = new DurationTracker();
	ArrayList<ModuleTestCasesStats> moduleTestCasesStats = new ArrayList<ModuleTestCasesStats>();
	Integer passCount = 0;
	Integer failCount = 0;
	Integer skipCount = 0;
	boolean  testCaseExecuted=false, flagNavigationError=false;
	
	Set<String> set = new HashSet();
	Integer manualSize = 0;
	
	getLog().debug("");
	
	getLog().debug("==================================================================================="); 
	getLog().debug("OS : " + System.getProperty("os.name")+"  Browser : " +getDisplayBrowserVersion() + "   Module : " + getModules().toUpperCase());
	getLog().debug("====================================================================================");
	getLog().debug("");
	System.out.println("OS : " + System.getProperty("os.name")+"  Browser : " +getDisplayBrowserVersion() + "   Module : " + getModules().toUpperCase() );
	String runModeTC, runModeTS;
	boolean isSmokeTest=false;
	String smokeTest = "IsSmokeTest";
	String currentTest_Mapping,testCaseMappingDescription = null;
	runModeTC="Runmode(mgm)";
	runModeTS="Runmode(mgm)";
		
	if(getRunTest().equalsIgnoreCase("sanitySuite")) {
		isSmokeTest = true;
		}

	
	int testSequenceId = 1;
	durationTracker.startTime();
	ReportsUtil.allModulesStats.add(moduleStats);
	moduleStats.setModuleName(getModules());
	moduleStats.setDurationTracker(durationTracker);
	
	DurationTracker testCaseDurationTracker = null;
	ModuleTestCasesStats testCasesStats = null;
	
	for (int tcid = 2; tcid <= getController().getRowCount(getModules()); tcid++) {
//		Thread.sleep(5000);
		testCasesStats = new ModuleTestCasesStats();
		testCaseDurationTracker = new DurationTracker();
		String fileName = null;
		String currentTest = getController().getCellData(getModules(), "TCID", tcid);
		System.out.println(currentTest);
		String currentTest_Description = getController().getCellData(getModules(), "Description", tcid);
		System.out.println(currentTest_Description);
		this.setCurrentTest(currentTest); 
		this.setCurrentTest_Description(currentTest_Description); 
		
		if(getRunTestApp().equalsIgnoreCase("GSAMLibrary")) {
			currentTest_Mapping = getController().getCellData(getModules(), "Manual TC ID(GSamLib)", tcid);
		}else {
			currentTest_Mapping = getController().getCellData(getModules(), "Manual TC ID(Aims)", tcid);
		}
		// initialize start time of test
		if (getController().getCellData(getModules(), runModeTC, tcid).equals("Y") && !flagNavigationError) {
			
			
			testCaseDurationTracker.startTime();
			testCasesStats.setSequenceId(testSequenceId++);
			testCasesStats.setTestCaseDescription(currentTest + " -"+currentTest_Description);
			fileName = getTestCaseFileName(testCasesStats.getTestCaseDescription(),tcid);
			fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1,fileName.length());

			if(isSmokeTest) {
				
				if (getController().getCellData(getModules(), smokeTest, tcid).equals("Y")) {
					System.out.println("### "+ getModules()+ ": Test Case: "+currentTest);
					testCaseMappingDescription = executeTest(runModeTS,currentTest_Mapping, testCaseMappingDescription, tcid);
					testCaseExecuted=true;
				}
//				else {
//					testCaseMappingDescription = skipTestCase(currentTest_Mapping);
//					}
			}else {
				System.out.println("### "+ getModules()+ ": Test Case: "+currentTest);
				testCaseMappingDescription = executeTest(runModeTS,currentTest_Mapping, testCaseMappingDescription, tcid);
				testCaseExecuted=true;
			}
			
			if(testCaseExecuted){
				if(!currentTest_Mapping.isEmpty() || currentTest_Mapping!=null) {
					set.addAll(Arrays.asList(currentTest_Mapping.split(",")));
					manualSize = set.size();
				}
				testCasesStats.setManualTCId(currentTest_Mapping);
				if(stepstatus) {
					testCasesStats.setResult("Pass");
					passCount++;
				} else {
					testCasesStats.setResult("Fail");
					failCount++;
				}

				testCaseDurationTracker.endTime();
				testCasesStats.setDurationTracker(testCaseDurationTracker);
				testCasesStats.setTestCaseHyperLinkName(fileName);
				moduleTestCasesStats.add(testCasesStats);
				durationTracker.endTime();
				testCaseExecuted=false;
				
				
			}
			
		}else if (!(getController().getCellData(getModules(), runModeTC, tcid).equals("N"))){
			testCasesStats.setResult("Fail");
			failCount++;
			testCaseExecuted=false;
		}
//		else {
//			 testCaseMappingDescription = skipTestCase(currentTest_Mapping);
//		}
		setTestStatus(null);
		createReport(durationTracker, moduleTestCasesStats, passCount,
				failCount, skipCount,manualSize);
		
		if(result.contains("Domain Page not Found") && !flagNavigationError) {
			flagNavigationError = true;
		}
	}
}

private void createReport(DurationTracker durationTracker,
		ArrayList<ModuleTestCasesStats> moduleTestCasesStats,
		Integer passCount, Integer failCount, Integer skipCount, Integer mSize)
		throws IOException {
	durationTracker.endTime();
	String templatePath = "src"+File.separator+"templates"+File.separator+"testCasesReport.ftl";
	Map<String, Object> testCaseData = new HashMap<String, Object>();
	File moduleFile = new File(System.getProperty("user.dir")+ File.separator + reportFolder + File.separator+getModules()+".html");
	
	moduleStats.setTotalPassCount(passCount);
	moduleStats.setTotalFailCount(failCount);
	moduleStats.setTotalSkipCount(skipCount);
	
	//added
	moduleStats.setTotalManualMappingCount(mSize);
	
	testCaseData.put("testcases", moduleTestCasesStats);
	testCaseData.put("moduleStats", moduleStats);
	if(!getBrowser().equalsIgnoreCase("na")){
	testCaseData.put("browserName", getBrowser());
		
	}
	ReportsUtil.prepareWebReport(templatePath, testCaseData, moduleFile);
	
	//prepare index file
	createIndexFile();
	//reporting.endSuite();
}

private void createIndexFile() throws IOException {
	((DurationTracker) ReportsUtil.indexFileData.get("suiteDurationTracker")).endTime();
	String templatePath = "src"+File.separator+"templates"+File.separator+"index.ftl";
	File indexFile = new File(System.getProperty("user.dir")+ File.separator + reportFolder + File.separator+"index"+".html");
	ReportsUtil.prepareWebReport(templatePath, ReportsUtil.indexFileData, indexFile);
}

public String getTestCaseFileName(String testDescription, Integer testCaseId) {
	String testCaseName=testDescription.replaceAll("[\"/:*?<>|\\\\]","");
	String fileName = System.getProperty("user.dir")+File.separator+ reportFolder +File.separator+ getModules() + "_TC"	+ testCaseId + "_" + testCaseName.replaceAll(" ", "_")+ ".html";
	return fileName;
}

public String executeTest(String runMode, String currentTest_Mapping,String testCaseMappingDescription, int tcid)
		throws InterruptedException, IOException {
	String isSmokeTestStep;
	ArrayList<TestStepStats> testStepStats = new ArrayList<TestStepStats>();
	int screenshotCount=0;
	setUserAgent(getController().getCellData(getModules(), "UserAgent", tcid));
	String descriptionModified="No";
	stepstatus = true;
	boolean testStepExecuted=false;
	// execute the keywords
	// loop again - rows in test data
	int totalSets = getTestData().getRowCount(getCurrentTest()); //holds total rows TestData sheet. 
	                                                   //If sheet does not exist then 2 by default
	if (totalSets >= 2) {
		totalSets = 2; // run at least once
	}

	int stepSequenceId = 1;
	
	for (setTestRepeat(totalSets); getTestRepeat() <= getTestData().getRowCount(getCurrentTest()); incrementTestRepeat()) {

		 getLog().debug("Executing the test :                                " + getCurrentTest());
		 getLog().debug("Test Description :    " + getCurrentTest_Description());
		 getLog().debug("test repeat : " +totalSets );
		 
		 
		 if(getTestRepeat() >2)
			 Thread.sleep(2000);
		for (int tsid = 2; tsid <= getController().getRowCount(getCurrentTest()); tsid++) {
			String screenshot = null;
			String stepDescription = null;
			String keyword = null;
			// values from Testcase sheet in Controller.xlsx
			if (!getController().getCellData(getCurrentTest(), runMode, tsid).equals("N") ) {
				TestStepStats stepStats = new TestStepStats();
				isSmokeTestStep=getController().getCellData(getCurrentTest(), "IsSmokeTestStep", tsid);
				
				if(!(!getRunTest().equals("sanitySuite") && isSmokeTestStep.equals("N"))) {
					setCurrentTSID(getController().getCellData(getCurrentTest(),"TSID", tsid));
					stepDescription = getController().getCellData(getCurrentTest(),"Decription", tsid);
					keyword = getController().getCellData(getCurrentTest(),"Keyword", tsid);
					setObject(getController().getCellData(getCurrentTest(), "Object",tsid));
					setObjectArr(getObject().split(","));
					String app;
					app = "mgm.";
					
					for(int i = 0; i < getObjectArr().length; i++)
					{
						getObjectArr()[i] = app+getObjectArr()[i];
					}
					
					setProceedOnFail(getController().getCellData(getCurrentTest(),"ProceedOnFail", tsid));
					setData_column_name(getController().getCellData(getCurrentTest(),"Data_Column_Name", tsid));
					setData_column_nameArr(getData_column_name().split(","));
					
					if(!(getData_column_name().equals(""))){
						setData(getTestData().getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat()));
						setData(getData().replaceAll("\\W+", ""));
						setTestCaseDescription(getCurrentTest() + " -"+getCurrentTest_Description());
						testCaseMappingDescription = currentTest_Mapping;
						descriptionModified ="Yes";
					}
					else
					{
						if(! (descriptionModified.equalsIgnoreCase("Yes")))
							setTestCaseDescription(getCurrentTest() + " -"+getCurrentTest_Description())  ;
						testCaseMappingDescription = currentTest_Mapping  ;
					}
					
					
					try {
						if(keyword.isEmpty()){
							continue;
						}
						
						Method method = null;
						try{
							System.out.println("@@@ "+ getModules() +": KEYWORD C1 "+keyword);
							method = this.getClass().getMethod(keyword);
						}catch(NoSuchMethodException nsme){
							//method implemented in CustomeKeyword class
							//method = CustomKeywords.class.getMethod(keyword);
							System.out.println("@@@ ERROR MSG NSME M1 "+nsme.getMessage());
						}
						
//						System.out.println("@@@ METHOD C1"+method);
						
						try {
							result = (String) method.invoke(this);
						}catch(Throwable t) {
							result="Fail-Debug Required";
						}
						
						if(!(getData_column_name().equals(""))){
							getLog().debug("\nData :" + getData());
						}
						
						getLog().debug("***Result of execution -- "+ result);
						if(result.startsWith("Pass")){
							//String fileName = displayBrowserVersion + "AIMSB_Portal_TC" + (tcid - 1) + "_TS"+ tsid + "_" + keyword + testRepeat+ ".jpg";
							//ReportsUtil.takeScreenShot(CONFIG.getProperty("screenshotPath")+ fileName, driver);
							
						}else if (result.startsWith("Fail")) {
							stepstatus = false;
							setTestStatus(result);
							// take screenshot for fail
							screenshot = "AIMSB_Portal_Module-" + getModules() + "_TC" + tcid + "_TS"+ getCurrentTSID() + "_" + getLaunchBrowser() + ++screenshotCount + ".jpeg";
							
							if(getCaptureScreenShot().equals("true"))
								ReportsUtil.takeScreenShot(screenshot, driver,reportFolder, getLog());
							
							
							if (getProceedOnFail().equalsIgnoreCase("N")) {
								driver.close();
								break;
							}
							
						}
						
					} catch (Exception t) {
						t.printStackTrace();
						getLog().debug("Error  " + t.getMessage());
					}
					testStepExecuted=true;
				}
				if (testStepExecuted) {
					stepStats.setTestStepId(stepSequenceId++);
					stepStats.setTestStepDescription(stepDescription);
					stepStats.setTestStepKeyword(keyword);
					stepStats.setTestStepResult(result);
					stepStats.setFailureScreenShot(screenshot);
					testStepStats.add(stepStats);
					testStepExecuted=false;
				}
				
				if(result.contains("Domain Page not Found")) {
					break;
				}
			try{
				boolean driverNull = (driver.toString().contains("(null)")) ? true : false;
				if(!driverNull) {
					Functions.handleTnCPopUp(driver, getLog(), OR);
					System.out.println("executing handle tnc pop up function");
				}
			}catch(Throwable t){
				//do nothing
			}
			}
		}// keywords one loop over
		// report pass or fail
		if(result.contains("Domain Page not Found")) {
			break;
		}
		
		if (getTestStatus() == null) {
			setTestStatus("Pass");
		}
		
		getLog().debug("****************************************************"+ getCurrentTest() + " --- " + getTestStatus());
		descriptionModified = "No";
	}// test data
	String templatePath = "src"+File.separator+"templates"+File.separator+"testStepsReport.ftl";
	Map<String, Object> testStepData = new HashMap<String, Object>();
	File testCaseFile = new File(getTestCaseFileName(getTestCaseDescription(), tcid));
	testCaseFile.createNewFile();
	testStepData.put("testSteps", testStepStats);
	testStepData.put("testCaseName", getTestCaseDescription());
	ReportsUtil.prepareWebReport(templatePath, testStepData, testCaseFile);
	
	return testCaseMappingDescription;
}

public String skipTestCase(String currentTest_Mapping) {
	String testCaseMappingDescription;
	getLog().debug("Skipping the test " + getCurrentTest());
	setTestStatus("Skip");
	// report skipped
	getLog().debug("****************************************************"+ getCurrentTest() + " --- " + getTestStatus());
	setTestCaseDescription(getCurrentTest() + " -"+getCurrentTest_Description());
	testCaseMappingDescription = currentTest_Mapping;
	return testCaseMappingDescription;
}

public void endScript(){
	try {
		FileUtils.copyFileToDirectory(new File(System.getProperty("user.dir")+"/src/com/aims/xls/pageTitles_MasterList.xlsx"), new File(System.getProperty("user.dir")+ "/"+reportFolder));
		getLog().debug("Browser Title sheet copied to the latest report folder.");
		FileUtils.copyDirectoryToDirectory(new File(System.getProperty("user.dir")+"/TestingLogs"), new File(System.getProperty("user.dir")+ "/"+reportFolder));
		getLog().debug("Testing Logs copied to the latest report folder.");
		FileUtils.copyFileToDirectory(new File(System.getProperty("user.dir")+"/"+reportFolder+"/index.html"), new File(System.getProperty("user.dir")));
		getLog().debug("Latest index file copied to the root folder.");
		FileUtils.copyDirectoryToDirectory(new File(System.getProperty("user.dir")+"/dependencies/excelComparison"), new File(System.getProperty("user.dir")+ "/"+reportFolder)); 
		File file1 = new File(System.getProperty("user.dir")+"/dependencies/excelComparison/Test");
		FileUtils.cleanDirectory(file1);
		
		FileUtils.copyDirectoryToDirectory(new File(System.getProperty("user.dir")+"/dependencies/PDFComparison/PDF_Results"), new File(System.getProperty("user.dir")+ "/"+reportFolder)); 
		File file3 = new File(System.getProperty("user.dir")+"/dependencies/PDFComparison/PDF_Results/Test");
		File file2 = new File(System.getProperty("user.dir")+"/dependencies/PDFComparison/PDF_Results/Stage/Differences");
		FileUtils.cleanDirectory(file3);
		FileUtils.cleanDirectory(file2);		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		getLog().debug("Exception caught. Stack trace :" + e.getMessage());
	}
	
	

}


	
}



