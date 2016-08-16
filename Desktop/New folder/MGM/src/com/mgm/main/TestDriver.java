package com.mgm.main;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mgm.util.CreateLogger;
import com.mgm.util.Functions;

public class TestDriver extends Controller{
	
	@Parameters({"moduleName","browser","device"})
	public TestDriver(String moduleName, String browser,String device)
	{
		super();
		this.setModuleName(moduleName);
		this.setBrowser(browser);
		this.setDevice(device);
	}
	
	@BeforeTest
	public void startTesting()
	{
		String runTest = (String) testCONFIG.get("RunTest");
		String testBrowser = this.getBrowser();
		String moduleName = this.getModuleName();
		if(runTest.equals(moduleName) || runTest.equals("sanitySuite") || runTest.equals("regressionSuite")) {	
			if(((moduleName.contains("workflows") || moduleName.contains("reverseReplication")) && !testBrowser.equals("Chrome")) || 
					(moduleName.contains("excelDataDownload") && (testBrowser.equals("InternetExplorer") || testBrowser.equals("Safari")))) {
				Functions.skipModule();
			}else {
				CreateLogger logger = new CreateLogger();
				logger.setModuleName(this.getModuleName());
				logger.createLogger();	
				this.setLog(Logger.getLogger(this.getModuleName()));
				
				reportsUtil.log = this.getLog();
				
				html = new File(System.getProperty("user.dir")+File.separator+ reportFolder , this.getModuleName()+".html");
				
				this.setScreenshotFolder(System.getProperty("user.dir") +File.separator+ reportFolder+ File.separator);
				this.setModules(this.getModuleName());
				super.startTesting();
			}
			
		}else {
			Functions.skipModule();
		}
		
	}
	

	  @Test
	  public void callMainTest() throws InterruptedException, IOException
	  {
		  System.out.println("Start testAppMain");
		  super.testAppMain();
	  }
	  
	  @AfterClass
		public void endScript(){
		  super.endScript();
			
		}
	}


