package com.mgm.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mgm.util.DurationTracker;
import com.mgm.util.Functions;
import com.mgm.util.ReportsUtil;

public class Driver extends Controller {
	@Parameters({"moduleName","browser","device"})
	public Driver(String moduleName, String browser,String device)
	{
		super();
		this.setModuleName(moduleName);
		this.setBrowser(browser);
		this.setDevice(device);
	}
	private DurationTracker durationTracker;
	private String enviroment;
	private String envLink;
	private String suiteName;
	private String browserName;
	File batchFileDir;
	static {
		try {
			System.out.println("inside driver");
			testCONFIG = Functions.loadConfigFile("config", "TestConfiguration");

			CONFIG = Functions.loadConfigFile("config", "config_"+testCONFIG.getProperty("RunTestEnv"));

			OR = Functions.loadConfigFile("config", "OR");
			
			mobileCONFIG = Functions.loadConfigFile("config", "mobileCapabilites");
			ReportsUtil.shutDownGrid();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@BeforeTest
	public void initiationOfGrid() throws FileNotFoundException, IOException, InterruptedException, JSONException
	{	
		
		String sTestBrowser =this.getBrowser();
		//String sAppBrowser = (String)testCONFIG.getProperty("AppBrowser");
		String sEnv = (String)testCONFIG.getProperty("Env");
		if(sEnv.equals("LocalMachine") || sEnv.equals("TestMachine") ) {
			batchFileDir = new File(System.getProperty("user.dir")+ "/lib/grid");
			Runtime.getRuntime().exec("cmd.exe /c start grid_LOCAL_WIN_GRID.bat", null, batchFileDir );
			Thread.sleep(3*1000);
		 
		}
		
		if(sEnv.equals("LocalMachine") && sTestBrowser.equalsIgnoreCase("internetexplorer") ) {
			Thread.sleep(3*1000);
			batchFileDir = new File(System.getProperty("user.dir")+ "/lib/grid");
			Runtime.getRuntime().exec("cmd.exe /c start grid_LOCAL_WIN_IE.bat", null, batchFileDir );
			Thread.sleep(3*1000);
		 
		}else if(sEnv.equals("LocalMachine") && sTestBrowser.equalsIgnoreCase("firefox") ) {
			Thread.sleep(3*1000);
			batchFileDir = new File(System.getProperty("user.dir")+ "/lib/grid");
			Runtime.getRuntime().exec("cmd.exe /c start grid_LOCAL_WIN_FF.bat", null, batchFileDir );
			Thread.sleep(3*1000);
		}else if(sEnv.equals("LocalMachine") && sTestBrowser.equalsIgnoreCase("chrome") ) {
			Thread.sleep(3*1000);
			batchFileDir = new File(System.getProperty("user.dir")+ "/lib/grid");
			Runtime.getRuntime().exec("cmd.exe /c start grid_LOCAL_WIN_CHROME.bat", null, batchFileDir );
			Thread.sleep(3*1000);
		}
		else if(sEnv.equals("LocalMachine") && sTestBrowser.equalsIgnoreCase("android") ) {
			Thread.sleep(3*1000);
			System.out.println("Killed the browser");
			batchFileDir = new File(System.getProperty("user.dir")+ "/lib/grid");
			appendToBATfile();
			Runtime.getRuntime().exec("cmd.exe /c start grid_LOCAL_WIN_DEVICE.bat", null, batchFileDir );
			Thread.sleep(3*1000);
		 
		}
		else if(sEnv.equals("LocalMachine") && sTestBrowser.equalsIgnoreCase("app") ) {
			Thread.sleep(3*1000);
			System.out.println("Killed the browser");
			batchFileDir = new File(System.getProperty("user.dir")+ "/lib/grid");
			Runtime.getRuntime().exec("cmd.exe /c start appium_server.bat", null, batchFileDir );
			Thread.sleep(3*1000);
			
	}
		durationTracker = new DurationTracker();
		durationTracker.startTime();
		
		reportFolder = "WebReport-" + durationTracker.getStartTime();
		reportFolder.replaceAll(" ", "_");
		new File(reportFolder).mkdir();
		setReportVariables();
	    ReportsUtil.indexFileData.put("indexFileData", ReportsUtil.allModulesStats);
	    ReportsUtil.indexFileData.put("suiteDurationTracker", durationTracker);
	    ReportsUtil.indexFileData.put("environment", enviroment);
	    ReportsUtil.indexFileData.put("envLink", envLink);
	    ReportsUtil.indexFileData.put("suiteName", suiteName);
	    ReportsUtil.indexFileData.put("browserName", browserName);
}
	
public void appendToBATfile() throws IOException { 
        
        BufferedWriter bw = null;
        //String fileName = fileName().trim();
        int port=Integer.parseInt((String)mobileCONFIG.getProperty("port"));
        int deviceNumber=Integer.parseInt((String)mobileCONFIG.getProperty("NoOfDevices"));
        FileWriter file1 = new FileWriter(System.getProperty("user.dir")+"/lib/grid/grid_LOCAL_WIN_DEVICE.bat");
        PrintWriter writer = new PrintWriter(file1);
        writer.print("");
        writer.flush();
   
        try { 
           // APPEND MODE SET HERE 
           bw = new BufferedWriter(file1);
           for (int i = 0; i < deviceNumber; i++) {
        	   bw.write("java -jar selenium-server-standalone-2.48.2.jar -role node"+" "+"-Dwebdriver.chrome.driver=..\\..\\drivers\\chromedriver.exe -port"+" "+port+" "+"-hub http://localhost:4444/grid/register -browser"+" "+"\"browserName=chrome,version=android,maxInstance=1,platform=WINDOWS\""+" "+"-hubHost localhost"); 
               bw.newLine();
               port++;
		}  
           bw.flush(); 
        } catch (IOException ioe) { 
       ioe.printStackTrace(); 
        } finally {                       // always close the file 
       if (bw != null) try { 
          bw.close(); 
       } catch (IOException ioe2) { 
          // just ignore it 
       } 
        } // end try/catch/finally 
   
     }
@AfterSuite
public void createMainReport() throws IOException
	{
	  ReportsUtil.clearTempFolder();
//	  ReportsUtil.shutDownGrid();
	  ReportsUtil.shutDownGrid();
	  if(!((String)testCONFIG.getProperty("Env")).equals("LocalMachine")) {
			Runtime.getRuntime().exec("cmd.exe /c start grid_TEST_WIN.bat", null, batchFileDir );
	  }

	}

private void setReportVariables() throws FileNotFoundException, IOException{
	  browserName =this.getBrowser();
	  
	  enviroment = testCONFIG.getProperty("RunTestEnv");
	  
	  if(!testCONFIG.getProperty("RunTestApp").equals("prod"))
		  envLink = CONFIG.getProperty("mgm.mainApp.url");
	  else
		  envLink = CONFIG.getProperty("prod.mainApp.url");
	  if (testCONFIG.getProperty("RunTest").equalsIgnoreCase("sanitySuite")) {
		  suiteName = "Smoke Test";
	  } else if(testCONFIG.getProperty("RunTest").equalsIgnoreCase("regressionSuite")) {
		  suiteName = "Regression Test";
	  }
}


}
