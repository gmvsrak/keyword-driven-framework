package com.mgm.main;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.mgm.util.Functions;
import com.mgm.xls.ExcelOperations;

public class Keyword  {
	
	
	public static Properties OR;
	public static Properties APPTEXT;
	public static Properties testCONFIG;
	public static Properties mobileCONFIG;
	public static Properties CONFIG;
	public static String reportFolder;
	private Logger log;
	private String runTestApp;
	private String runTest;
	private String runModule;
	private String device;
	private String browser;
	private String launchBrowser;
	private String displayBrowserVersion;
	private String devices;
	private String moduleName;
	private String modules;
	private String screenshotFolder;
	public File html;
	private boolean testCasesfileAssigned=false;
	private boolean testDatafileAssigned=false;
	private ExcelOperations controller;
	private ExcelOperations testData;
	private String currentTest;
	private String currentTest_Description;
	private String keyword;
	private String testStatus;
	private String userAgent;
	private int testRepeat;
	private String currentTSID;
	private String object;
	private String objectArr[];
	private String proceedOnFail;
	private String data_column_name;
	private String data_column_nameArr[];
	private String captureScreenShot;
	private String data;
	private String testCaseDescription;
	private String confirmationNUmber;
	public RemoteWebDriver driver = null;
	long WAIT1SEC=1000, WAIT2SEC=2000, WAIT3SEC=3000, WAIT4SEC=4000, WAIT5SEC=5000, WAIT6SEC=6000, WAIT7SEC=7000, WAIT8SEC=8000;
	
	public String getConfirmationNUmber() {
		return confirmationNUmber;
	}
	public void setConfirmationNUmber(String confirmationNUmber) {
		this.confirmationNUmber = confirmationNUmber;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getTestCaseDescription() {
		return testCaseDescription;
	}
	public void setTestCaseDescription(String testCaseDescription) {
		this.testCaseDescription = testCaseDescription;
	}
	public String getCaptureScreenShot() {
		return captureScreenShot;
	}
	public void setCaptureScreenShot(String captureScreenShot) {
		this.captureScreenShot = captureScreenShot;
	}
	public String getProceedOnFail() {
		return proceedOnFail;
	}
	public void setProceedOnFail(String proceedOnFail) {
		this.proceedOnFail = proceedOnFail;
	}
	public String getData_column_name() {
		return data_column_name;
	}
	public void setData_column_name(String data_column_name) {
		this.data_column_name = data_column_name;
	}
	public String[] getData_column_nameArr() {
		return data_column_nameArr;
	}
	public void setData_column_nameArr(String[] data_column_nameArr) {
		this.data_column_nameArr = data_column_nameArr;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String[] getObjectArr() {
		return objectArr;
	}
	public void setObjectArr(String[] objectArr) {
		this.objectArr = objectArr;
	}
	public String getCurrentTSID() {
		return currentTSID;
	}
	public void setCurrentTSID(String currentTSID) {
		this.currentTSID = currentTSID;
	}
	public int getTestRepeat() {
		return testRepeat;
	}
	public void setTestRepeat(int testRepeat) {
		this.testRepeat = testRepeat;
	}
	public void incrementTestRepeat() {
		this.testRepeat++;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public boolean isTestCasesfileAssigned() {
		return testCasesfileAssigned;
	}
	public void setTestCasesfileAssigned(boolean testCasesfileAssigned) {
		this.testCasesfileAssigned = testCasesfileAssigned;
	}
	public boolean isTestDatafileAssigned() {
		return testDatafileAssigned;
	}
	public void setTestDatafileAssigned(boolean testDatafileAssigned) {
		this.testDatafileAssigned = testDatafileAssigned;
	}
	public ExcelOperations getController() {
		return controller;
	}
	public void setController(ExcelOperations controller) {
		this.controller = controller;
	}
	public ExcelOperations getTestData() {
		return testData;
	}
	public void setTestData(ExcelOperations testData) {
		this.testData = testData;
	}
	public String getCurrentTest() {
		return currentTest;
	}
	public void setCurrentTest(String currentTest) {
		this.currentTest = currentTest;
	}
	public String getCurrentTest_Description() {
		return currentTest_Description;
	}
	public void setCurrentTest_Description(String currentTest_Description) {
		this.currentTest_Description = currentTest_Description;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getModules() {
		return modules;
	}
	public void setModules(String modules) {
		this.modules = modules;
	}
	public String getScreenshotFolder() {
		return screenshotFolder;
	}
	public void setScreenshotFolder(String screenshotFolder) {
		this.screenshotFolder = screenshotFolder;
	}
	public Logger getLog() {
		return log;
	}
	public void setLog(Logger log) {
		this.log = log;
	}
	public String getRunTestApp() {
		return runTestApp;
	}
	public void setRunTestApp(String runTestApp) {
		this.runTestApp = runTestApp;
	}
	public String getRunTest() {
		return runTest;
	}
	public void setRunTest(String runTest) {
		this.runTest = runTest;
	}
	public String getRunModule() {
		return runModule;
	}
	public void setRunModule(String runModule) {
		this.runModule = runModule;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getLaunchBrowser() {
		return launchBrowser;
	}
	public void setLaunchBrowser(String launchBrowser) {
		this.launchBrowser = launchBrowser;
	}
	public String getDisplayBrowserVersion() {
		return displayBrowserVersion;
	}
	public void setDisplayBrowserVersion(String displayBrowserVersion) {
		this.displayBrowserVersion = displayBrowserVersion;
	}
	public String getDevices() {
		return devices;
	}
	public void setDevices(String devices) {
		this.devices = devices;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public By getBy(Properties objectFile, String locator) {

		By by = null;
		String value= null;

		try {
			value = objectFile.getProperty(locator);

			if(locator.endsWith("xpath"))
				by = By.xpath(value);
			else if(locator.endsWith("id"))
				by = By.id(value);
			else if(locator.endsWith("cssSelector"))
				by = By.cssSelector(value);
			else if(locator.endsWith("linkText"))
				by = By.linkText(value);
			else if(locator.endsWith("partialLinkText"))
				by = By.partialLinkText(value);
			else if(locator.endsWith("tagName"))
				by = By.tagName(value);
			else if(locator.endsWith("name"))
				by = By.name(value);
			else if(locator.endsWith("className"))
				by = By.className(value);
			else
				by = By.xpath(value);      //statement added to cater to the rest locator properties
		}catch(Throwable t) {
			log.debug("Exception caught while accessing the locator :" +locator);
		}
		return by;
	}
	public WebElement getWebElement(Properties objectFile,String locator)
	{
		WebElement element = null;
		try {

			element = driver.findElement(getBy(objectFile, locator));
			//Functions.highlighter(driver, element);

		}catch(Throwable t) {
			log.debug("Exception caught at object :" +locator);
		}
		return element;
	}

	public List<WebElement> getWebElements(Properties objectFile,String locator)
	{
		List<WebElement> element = null;
		try {
			element = driver.findElements(getBy(objectFile, locator));

		}catch(Throwable t) {
			log.debug("Exception caught at object :" +locator);
		}
		return element;
	}
	public String quitBrowser(){
		log.debug("=============================");
		log.debug("Executing closeBrowser");
		try {
			if(driver != null){

				driver.quit();

				}
		} catch (Throwable t) {
			// report error
			log.debug("Error while closing the browser -" + t.getMessage());
			return "Fail - QUIT Browser issue";
		}
		return "Pass";
	}
	
	public String clickWidget() {
		
		log.debug("=============================");
		log.debug("executing keyword clickWidget");
		
		
		try {
			WebElement dropdownObject = getWebElement(OR, objectArr[0]);
			dropdownObject.click();
			
			
		}catch(Throwable r) {
			log.debug("Error while executing selectFromDropdown keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	public String Wait() {
		log.debug("=============================");
		log.debug("Executing wait Keyword");

		/*try{
			if(!(testBrowser.equals("Safari"))) {
				Functions.waitForElementClickable(driver, log, objectArr[1]);
			}
		}catch(ArrayIndexOutOfBoundsException e) {

		}*/

		try {
			String data = OR.getProperty(objectArr[0]);
			Thread.sleep(Long.parseLong(data));
		}catch(Throwable t) {

		}

		return "Pass";
	}

	public String setDataInFields() {
		
		log.debug("=============================");
		log.debug("executing keyword setDataInFields");
		
		
		try {
			String getData=testData.getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat());
			System.out.println(getData);
			WebElement textField = getWebElement(OR, objectArr[0]);
			textField.click();
			textField.clear();
			textField.sendKeys(getData);
			
			
		}catch(Throwable r) {
			log.debug("Error while executing setDataInFields keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
public String sendKeyboardKeys() {
		
		log.debug("=============================");
		log.debug("executing keyword sendKeyboardKeys");
		
		
		try {
			WebElement textField = getWebElement(OR, objectArr[0]);
			textField.sendKeys(Keys.RETURN);	
		}catch(Throwable r) {
			log.debug("Error while executing sendKeyboardKeys keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String verifyTextBoxText() {
		log.debug("=============================");
		log.debug("Executing verifyText");

		String expected = testData.getCellData(currentTest, data_column_nameArr[0],
				testRepeat);
		String actual = driver.findElement(By.xpath(OR.getProperty(objectArr[0])))
				.getAttribute("value");
		log.debug("expected Text  -  " + expected);
		log.debug("actual Text  -  " + actual);
		try {
			Assert.assertEquals(expected.trim(), actual.trim());
		} catch (Throwable t) {
			// error
			log.debug("Error in text - " + objectArr[0]);
			log.debug("Actual - " + actual);
			log.debug("Expected -" + expected);
			return "Fail";
		}
		return "Pass";
	}
	
	

	public String getCookies() {
		
		log.debug("=============================");
		log.debug("executing keyword getCookies");
		
		
		try {
			String getCookieName=testData.getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat());
			System.out.println(getCookieName);
			int i = 0;
			int a = 0;
			ArrayList<String> cookieName = new ArrayList<String>();
			while(i<30){
			Set<Cookie> cookieSet =driver.manage().getCookies();
			i++;
			Iterator<Cookie> iterator = cookieSet.iterator();
			while (iterator.hasNext()) {
				Cookie cookie = (Cookie) iterator.next();
				cookieName.add(cookie.getName());
				
			}
			
				if(cookieName.contains(getCookieName)){
					a++;
					System.out.println("Aut cookie is present");
					break;
				}
				else{
					try {
						Thread.sleep(2*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(a==0){
				throw new Exception("Aut Cookie not present");				
			}
			
			
		}catch(Throwable r) {
			log.debug("Error while executing getCookies keyword" + r.getMessage());
			return "Fail";
			}
		return "Pass";
	}
	
public String getNoCookies() {
		
		log.debug("=============================");
		log.debug("executing keyword getCookies");
		
		
		try {
			String getCookieName=testData.getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat());
			
			int i = 0;
			int a = 0;
			ArrayList<String> cookieName = new ArrayList<String>();
			
			while(i<30){
			Set<Cookie> cookieSet =driver.manage().getCookies();
			Iterator<Cookie> iterator = cookieSet.iterator();
			while (iterator.hasNext()) {
				Cookie cookie = (Cookie) iterator.next();
				cookieName.add(cookie.getName());
			}
			
				if(cookieName.contains(getCookieName)){
					a++;
					break;
				}
				i++;
			}
			if(a>0){
				throw new Exception("Auth Cookie is present");
			}
			
			
		}catch(Throwable r) {
			log.debug("Error while executing getCookies keyword" + r.getMessage());
			return "Fail";
			}
		return "Pass";
	}


	public String waitForElementAndClick() throws InterruptedException {
		log.debug("=============================");
		log.debug("Executing waitForElementAndClick Keyword");
		
		int i= 0;
		int a=0;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(getBy(OR, objectArr[0])));
			while (i < 10) {
			    // Browsers which render content (such as Firefox and IE) return "RenderedWebElements"
			    try {
					WebElement resultsDiv = element;
					resultsDiv.getSize().getHeight();
					// If results have been returned, the results are displayed in a drop down.
					if (resultsDiv.getSize().getHeight()>0) {
						log.debug("Element is present");
						a++;
					  break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Thread.sleep(2*1000);
				}
			    i++;
			}if(a==0){
				throw new Exception("Element is not present");
			}
		} catch (Exception e) {
			log.debug("Error while executing waitForElementAndClick-" + e.getMessage());
			return "Fail";
		}
	/*	try {
			String data = OR.getProperty(objectArr[0]);

			Functions.waitForElementClickable(driver, log, objectArr[1]);
			Thread.sleep(Long.parseLong(data));

			
		}catch(Throwable t) {
			log.debug("Error while executing waitForElementAndClick-" + t.getMessage());
			return "Fail";
		}*/

		return "Pass";
	}
	
	public String waitForVisibilityOfElement() {
		log.debug("=============================");
		log.debug("Executing waitForVisibilityOfElement Keyword");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getBy(OR, objectArr[0])));
		int i= 0;
		int a=0;
		try {
			while (i < 10) {
			    // Browsers which render content (such as Firefox and IE) return "RenderedWebElements"
			    try {
					WebElement resultsDiv = element;
					
					// If results have been returned, the results are displayed in a drop down.
					if (!resultsDiv.getCssValue("display").contains("none")) {
						log.debug("Element is present");
						a++;
					  break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Thread.sleep(2*1000);
				}
			    i++;
			}if(a==0){
				throw new Exception("Element is not present");
			}
		}catch(Throwable t) {
			log.debug("Error while executing waitForVisibilityOfElement-" + t.getMessage());
			return "Fail";
		}

		return "Pass";
	}
	
	public String waitForNonVisibilityOfElement() {
		log.debug("=============================");
		log.debug("Executing waitForNonVisibilityOfElement Keyword");
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy(OR, objectArr[0])));
				
			
		}catch(Throwable t) {
			log.debug("Error while executing waitForVisibilityOfElement-" + t.getMessage());
			return "Fail";
		}
		return "Pass";
		}
	
	public String waitForEmail() throws InterruptedException {
		log.debug("=============================");
		log.debug("Executing waitForElementAndClick Keyword");
		WebElement element = getWebElement(OR,objectArr[0]);
		int i= 0;
		int a=0;
		try {
			while (i < 20) {
			    // Browsers which render content (such as Firefox and IE) return "RenderedWebElements"
			    try {
					WebElement resultsDiv = element;
					resultsDiv.getSize().getHeight();
					// If results have been returned, the results are displayed in a drop down.
					if (resultsDiv.getSize().getHeight()>0) {
						log.debug("Email is present");
						a++;
					  break;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					Thread.sleep(2*1000);
					driver.navigate().refresh();
				}
			    i++;
			}if(a==0){
				throw new Exception("Email is not present");
			}
		} catch (Exception e) {
			log.debug("Error while executing waitForElementAndClick-" + e.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	
	public String waitForAllElementsAndClick() {
		log.debug("=============================");
		log.debug("Executing waitForAllElementsAndClick Keyword");

		try {
			int a=0;
			WebDriverWait wait = new WebDriverWait(driver, 120);
			List<WebElement> element = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy(OR, objectArr[0])));
			long end = System.currentTimeMillis() + 60000;
			while (System.currentTimeMillis() < end) {
			    Iterator<WebElement> ii = element.iterator();
				while (ii.hasNext()) {
					WebElement anchor = ii.next();
					if(anchor.isEnabled()){
							a++;
						 //System.out.println("CTA is clicked");
						}
					if(a>element.size()){
						break;
					}
			}if(a>0){
				break;
			}
		} if(a==0){
			throw new Exception("element not found");
		}
		
		

			
		}catch(Throwable t) {
			log.debug("Error while executing waitForAllElementsAndClick-" + t.getMessage());
			return "Fail";
		}

		return "Pass";
	}
	
	
	
	public String clickOnElements() {
		log.debug("=============================");
		log.debug("Executing clickOnElements Keyword");

		try {
			String testdata=testData.getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat());
			System.out.println("this is test data"+testdata);
			List<WebElement> anchorss =  getWebElements(OR,objectArr[0]);
			Iterator<WebElement> ii = anchorss.iterator();
			while (ii.hasNext()) {
				WebElement anchor = ii.next();
				System.out.println(anchor.getText());
				if (anchor.getText().equalsIgnoreCase(testdata)) {
					 ((JavascriptExecutor)driver).executeScript("arguments[0].click();",anchor);
					 System.out.println("CTA is clicked");
					break;
				}
			}
		 

			
		}catch(Throwable t) {
			log.debug("Error while executing clickOnElements-" + t.getMessage());
			return "Fail";
		}

		return "Pass";
	}
	
	public String isSavedRemoved(){
		try {
			int i=0;
			int a=0;
			List<WebElement> anchorss =  getWebElements(OR,objectArr[0]);
			List<WebElement> resultsDiv = anchorss;
			resultsDiv.size();
			Iterator<WebElement> removeIterator = resultsDiv.iterator();
			while (removeIterator.hasNext()) {
				WebElement anchor = removeIterator.next();
				System.out.println(anchor.getTagName() + "---" + anchor.getText()
						+ "--" + anchor.toString());
				if (anchor.getText().trim().equalsIgnoreCase(("Remove"))) {
					((JavascriptExecutor)driver).executeScript("arguments[0].click();",anchor);
					break;
				}
			}
				while(i<20){
					List<WebElement> removeDiv = driver.findElements(By.xpath(data));
					removeDiv.size();
					if(resultsDiv.size()>removeDiv.size()){
						a++;
						break;
					}
				}if(a==0){
					return "Fail";
				}
				
		}
		catch(Throwable t) {
			log.debug("Error while executing isElementEnabled-" + t.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String selectValueFromDropDown(){
		try {
			int a=0;
			String resturant=testData.getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat());
			System.out.println(resturant);
			List<WebElement> resultsDiv = getWebElements(OR,objectArr[0]);
			Iterator<WebElement> Iterator = resultsDiv.iterator();
			while (Iterator.hasNext()) {
				WebElement anchor = Iterator.next();
				System.out.println(anchor.getTagName() + "---" + anchor.getText()
						+ "--" + anchor.toString());
				if (anchor.getText().trim().contains((resturant))) {
					anchor.click();
					a++;
					break;
				}
			}
			if(a==0){
				return "Fail";
			}
				}
				
		catch(Throwable t) {
			log.debug("Error while executing isElementEnabled-" + t.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	public String isElementEnabled() {
		log.debug("=============================");
		log.debug("Executing isElementEnabled Keyword");

		try {
			String data = OR.getProperty(objectArr[0]);

			WebElement resultsDiv = driver.findElement(By.xpath(data));
			if(resultsDiv.isEnabled())
			{
				return "Pass";
			}else{
				return "Fail";
			}

			
		}catch(Throwable t) {
			log.debug("Error while executing isElementEnabled-" + t.getMessage());
			return "Fail";
		}
	}
	
	public String isWebElementPresent() {
		log.debug("====================================");
		log.debug("Executing isWebElementPresent");

		String expected = null;
		WebElement webElement = null;
		try {
			try {
				expected = testData.getCellData(currentTest, data_column_nameArr[0],testRepeat);
				log.debug("Expected:" + expected);

				if(!(expected.contains("true") || expected.contains("false"))) {
					return "Fail- Debug Required";
				}

			}catch(Throwable t) {
				log.debug("Test Data Column is not present in controller sheet .Expected variable value :"+ expected);
				return "Fail- Debug Required";
			}

			try{
				webElement = getWebElement(OR,objectArr[0]);
				log.debug("WebElement: "+ webElement);
			}catch(Throwable e){
				webElement=null;
			}
			if (webElement == null) {
				if (expected.equalsIgnoreCase("true"))
					return "Fail -" + " Element not present";
				else
					return "Pass";
			}
			else{
				if (expected.equalsIgnoreCase("true"))
					return "Pass";
				else
					return "Fail -" + " Element should not be present";
			}
		}catch (Throwable t) {
			log.debug("Error while executing isWebElementPresent -"+ t.getMessage());
			return "Fail";
		}
	} 
	public String clickCta() {
		
		log.debug("=============================");
		log.debug("executing keyword clickCta");
		
		
		try {
			WebElement clickcta = getWebElement(OR, objectArr[0]);
			clickcta.click();
			
			
		}catch(Throwable r) {
			log.debug("Error while executing selectFromDropdown keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
		}
	
public String clickButton() {
		
		log.debug("=============================");
		log.debug("executing keyword clickCta");
		
		
		try {
			WebElement clickcta = getWebElement(OR, objectArr[0]);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();",clickcta);
			
			
		}catch(Throwable r) {
			log.debug("Error while executing selectFromDropdown keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
		}
	
public String actionClickCta() {
		
		log.debug("=============================");
		log.debug("executing keyword actionClickCta");
		
		
		try {
			WebElement clickcta = getWebElement(OR, objectArr[0]);
			Actions action = new Actions(driver);
			action.moveToElement(clickcta).click().perform();
			
			
		}catch(Throwable r) {
			log.debug("Error while executing selectFromDropdown keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
		}
public String findPageSource() {
		
		log.debug("=============================");
		log.debug("executing findPageSource");
		
		
		try {
			String pagesource=testData.getCellData(getCurrentTest(), getData_column_nameArr()[0],getTestRepeat());
			if(driver.getPageSource().contains(pagesource))
			{
				return "Pass";
			}else{
				return "Fail";
			}
			
			
		}catch(Throwable r) {
			log.debug("Error while executing findPageSource keyword" + r.getMessage());
			return "Fail";
		}
		
		}	
	
public String clickCheckBox() {
		
		log.debug("=============================");
		log.debug("executing keyword clickCheckBox");
		
		
		try {
			WebElement clickCheckBox = getWebElement(OR, objectArr[0]);
			((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;",clickCheckBox);
			
			
		}catch(Throwable r) {
			log.debug("Error while executing clickCheckBox keyword" + r.getMessage());
			return "Fail";
		}
		return "Pass";
}
	
	public String alertPopUp(){
		log.debug("=============================");
		log.debug("executing keyword clickCta");
		try {
			driver.switchTo().alert();
		} catch (Throwable r) {
			log.debug("Error while switching to alert" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String findTile(){
		log.debug("=============================");
		log.debug("executing keyword findTile");
		try {
			int i =0;
			int a=0;
			String dine = OR.getProperty(objectArr[0]);
			String data = OR.getProperty("mgm.prod.loadmorecta.xpath");
			while(i<10){
				try {
					if(driver.findElementByXPath(dine).isDisplayed()){
						a++;
						   break;
					}
				} catch (Exception e) {
					log.debug("clicking on loadMoreTiles CTA");
					driver.findElementByXPath(data).click();
				}
				i++;
				Thread.sleep(WAIT3SEC);
			}
			if(a==0){
				throw new Exception("Not able to find tile");
			}
			
			
		} catch (Throwable r) {
			log.debug("Error while findTile" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String selectSaveToItineray(){
		log.debug("=============================");
		log.debug("executing keyword selectSaveToItineray");
		try {
			String upcoming = OR.getProperty("mgm.itinerary.upcoming.xpath");
			String savetoitineray = OR.getProperty("mgm.itinerary.savetoitineray.xpath");
			if(driver.findElementByXPath(upcoming).isDisplayed()){
				driver.findElementByXPath(savetoitineray).click();
			}else{
				driver.findElementByXPath(savetoitineray).click();
			}
							
		} catch (Throwable r) {
			log.debug("Error while selectSaveToItineray" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String selectDeliveryMethod(){
		log.debug("=============================");
		log.debug("executing keyword selectDeliveryMethod");
		try {
			String deliveryMethodClicked = OR.getProperty("mgm.show.deliverymethodchecked.xpath");
			String deliveryMethod = OR.getProperty("mgm.show.deliverymethod.xpath");
			try {
				driver.findElementByXPath(deliveryMethodClicked);
				log.debug("DeliveryMethod is clicked");
			} catch (Exception e) {
				driver.findElementByXPath(deliveryMethod).click();
			}
							
		} catch (Throwable r) {
			log.debug("Error while selectDeliveryMethod" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String selectCompleted(){
		log.debug("=============================");
		log.debug("executing keyword selectCompleted");
		try {
			String upcoming = OR.getProperty("mgm.itinerary.upcoming.xpath");
			String savetoitineray = OR.getProperty("mgm.itinerary.savetoitineray.xpath");
			String completed= OR.getProperty("mgm.itinerary.completed.xpath");
			if(driver.findElementByXPath(upcoming).isDisplayed()){
				driver.findElementByXPath(completed).click();
			}else{
				if(driver.findElementByXPath(savetoitineray).isDisplayed())
				driver.findElementByXPath(completed).click();
			}
							
		} catch (Throwable r) {
			log.debug("Error while selectSaveToItineray" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String selectReservationTime(){
		log.debug("=============================");
		log.debug("executing keyword selectReservationTime");
		try {
			int a=0;
			String selectTime = OR.getProperty(objectArr[0]);
			String time = OR.getProperty("mgm.dining.selectTimeValue.xpath");
			String dineDate = OR.getProperty("mgm.dining.reservationnextday.xpath");
			List<WebElement> selectDate = driver.findElements(By.xpath(dineDate));
			List<WebElement> selectTimeValue = driver.findElements(By.xpath(time));
			WebElement currentDate = getWebElement(OR, "mgm.dining.selecteddate.xpath");
			if(currentDate.isDisplayed()){
			driver.findElement(By.xpath(selectTime)).click();
			if(selectTimeValue.size()>1){
				WebElement icon = selectTimeValue.get(1);
				icon.click();
				a++;
			}
			}else{
				for (int i = 0; i < selectDate.size(); i++) {
					WebElement webelement = selectDate.get(i);
					webelement.click();
					WebDriverWait wait = new WebDriverWait(driver, 60);
					wait.until(ExpectedConditions.elementToBeClickable(currentDate));
					if(currentDate.isDisplayed()){
						driver.findElement(By.xpath(selectTime)).click();
						if(selectTimeValue.size()>1){
							WebElement icon = selectTimeValue.get(1);
							icon.click();
							a++;
						}break;
			}}}if(a==0){
				throw new Exception("Time is not clicked");
			}
			
			} catch (Throwable r) {
			log.debug("Error while switching to alert" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String setDiningConfirmationNumber(){
		log.debug("=============================");
		log.debug("executing keyword setDiningConfirmationNumber");
		try {
			WebElement element = getWebElement(OR, objectArr[0]);
			String a = element.getText().trim();
			log.debug(a.substring((a.length()-5)).trim());
			setConfirmationNUmber(a.substring((a.length()-5)).trim());
			}
			
			 catch (Throwable r) {
			log.debug("Error while switching to setDiningConfirmationNumber" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String findCancledReservation(){
		log.debug("=============================");
		log.debug("executing keyword findCancledReservation");
		try {
			int a=0;
			String confirmationId=getConfirmationNUmber();
			System.out.println(confirmationId);
			String data = OR.getProperty(objectArr[0]);
			List<WebElement> resultsDiv = driver.findElements(By.xpath(data));
			Iterator<WebElement> Iterator = resultsDiv.iterator();
			while (Iterator.hasNext()) {
				WebElement anchor = Iterator.next();
				log.debug(anchor.getTagName() + "---" + anchor.getText()
						+ "--" + anchor.toString());
				if (anchor.getText().contains((confirmationId))) {
					a++;
					break;
				}
			}
			if(a==0){
				return "Fail";
			}
			}
			
			 catch (Throwable r) {
			log.debug("Error while switching to setDiningConfirmationNumber" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String waitForFindSeats(){
		log.debug("=============================");
		log.debug("executing keyword waitForFindSeats");
		try {
			String selectprice = OR.getProperty("mgm.show.priceselection.cssSelector");
			String pricevalue = OR.getProperty("mgm.show.priceselectionvalue.cssSelector");
			int i=0;
			int j=0;
			int a=0;
			while(i<10){
				try{
				if(driver.findElement(By.cssSelector(selectprice)).isDisplayed()){
					log.debug("price drop down is avilable");
					while(j<10){
						try {
							driver.findElement(By.cssSelector(selectprice)).click();
							List<WebElement> showsprice=driver.findElements(By.cssSelector(pricevalue));
							if(!showsprice.isEmpty()){
							if(showsprice.size()>=2){
								System.out.println("price list drop down is avilable");
								a++;
								break;
							} 
							}}catch (Exception e) {
							// TODO Auto-generated catch block
							Thread.sleep(2*1000);
						}j++;
					}
					break;
				}}catch (Exception e) {
					// TODO Auto-generated catch block
					Thread.sleep(2*1000);
				}i++;
			}if(a==0){
				throw new Exception("Prices are not found");
			}
			
		} catch (Throwable r) {
			log.debug("Error while selectFindSeats" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
			
	public String selectFindSeats(){
		log.debug("=============================");
		log.debug("executing keyword selectFindSeats");
		try {
			int a=0;
			int l=0;
			String selectprice = OR.getProperty("mgm.show.priceselection.cssSelector");
			String pricevalue = OR.getProperty("mgm.show.priceselectionvalue.cssSelector");
			String selectqunatity = OR.getProperty("mgm.show.quantity.cssSelector");
			String qunatityvalue = OR.getProperty("mgm.show.quantityselectionvalue.cssSelector");
			String timerCount = OR.getProperty("mgm.show.timer.cssSelector");
			String error = OR.getProperty("mgm.show.seatsnotfound.cssSelector");
			List<WebElement> selectPrice = driver.findElements(By.cssSelector(pricevalue));
			List<WebElement> selectQuantity = driver.findElements(By.cssSelector(qunatityvalue));
			WebElement findseats = getWebElement(OR, "mgm.show.findseatscta.cssSelector");
			if(selectPrice.size()>=2){
			for (int i = 2; i < selectPrice.size(); i++) {
				driver.findElement(By.cssSelector(selectprice)).click();
				WebElement webelement = selectPrice.get(i);
				webelement.click();
				Thread.sleep(WAIT1SEC);
				driver.findElement(By.cssSelector(selectqunatity)).click();
				for (int j = 2; j < selectQuantity.size(); j++) {
					WebElement quantityclick = selectQuantity.get(i);
					quantityclick.click();
					Thread.sleep(WAIT1SEC);
					if(findseats.isEnabled()){
						findseats.click();
						break;
					}
				}
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy(OR, "mgm.mlife.pageload.cssSelector")));
				while(l<10){
					try {
						if(driver.findElement(By.cssSelector(error)).isDisplayed()){
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						log.debug("Seats not found error is not displayed");;
					}
					try {
						if(driver.findElement(By.cssSelector(timerCount)).isDisplayed()){
							a++;
							break;
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.debug("timer not found");
						Thread.sleep(2*1000);
					
					}
			}if(a>0){
				break;
			}
			
			}if(a==0){
				throw new Exception("Seats are not found");
			}
			
			}else{
				throw new Exception("Prices are not found");
			}
			} catch (Throwable r) {
			log.debug("Error while selectFindSeats" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	public String selectADASeats(){
		log.debug("=============================");
		log.debug("executing keyword selectFindSeats");
		try {
			int a=0;
			int l=0;
			String selectprice = OR.getProperty("mgm.show.priceselection.cssSelector");
			String pricevalue = OR.getProperty("mgm.show.priceselectionvalue.cssSelector");
			String selectqunatity = OR.getProperty("mgm.show.quantity.cssSelector");
			String qunatityvalue = OR.getProperty("mgm.show.quantityselectionvalue.cssSelector");
			String timerCount = OR.getProperty("mgm.show.timer.cssSelector");
			String error = OR.getProperty("mgm.show.seatsnotfound.cssSelector");
			String selectadaqunatity = OR.getProperty("mgm.show.adaquantity.cssSelector");
			String adaQunatityValue = OR.getProperty("mgm.show.adaquantityvalue.cssSelector");
			List<WebElement> selectPrice = driver.findElements(By.cssSelector(pricevalue));
			List<WebElement> selectQuantity = driver.findElements(By.cssSelector(qunatityvalue));
			List<WebElement> selectAdaQuantity = driver.findElements(By.cssSelector(adaQunatityValue));
			WebElement findseats = getWebElement(OR, "mgm.show.findseatscta.cssSelector");
			if(selectPrice.size()>=2){
			for (int i = 2; i < selectPrice.size(); i++) {
				driver.findElement(By.cssSelector(selectprice)).click();
				WebElement webelement = selectPrice.get(i);
				webelement.click();
				Thread.sleep(WAIT1SEC);
				driver.findElement(By.cssSelector(selectqunatity)).click();
				for (int j = 2; j < selectQuantity.size(); j++) {
					WebElement quantityclick = selectQuantity.get(j);
					quantityclick.click();
					Thread.sleep(WAIT2SEC);
					driver.findElement(By.cssSelector(selectadaqunatity)).click();
					WebElement adaquantityclick = selectAdaQuantity.get(j);
						adaquantityclick.click();
						Thread.sleep(WAIT2SEC);
						if(findseats.isEnabled()){
							findseats.click();
							break;
						}
					
				}
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy(OR, "mgm.mlife.pageload.cssSelector")));
				while(l<10){
					try {
						if(driver.findElement(By.cssSelector(error)).isDisplayed()){
							break;
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						log.debug("Seats not found error is not displayed");;
					}
					try {
						if(driver.findElement(By.cssSelector(timerCount)).isDisplayed()){
							a++;
							break;
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						log.debug("timer not found");
						Thread.sleep(2*1000);
					
					}
			}if(a>0){
				break;
			}
			
			}if(a==0){
				throw new Exception("Seats are not found");
			}
			}else{
				throw new Exception("Prices are not found");
			}
			} catch (Throwable r) {
			log.debug("Error while selectFindSeats" + r.getMessage());
			return "Fail";
		}
		return "Pass";
	}
	
	
	public String launchWebpage()
	{
		log.debug("=============================");
		log.debug("executing keyword launchWebpage");


		DesiredCapabilities cap = null;
		setLaunchBrowser(getBrowser());
		setDevices(getDevice());
		String currentTitle;
		System.out.println(getLaunchBrowser());

		if (getLaunchBrowser().equalsIgnoreCase("Firefox")) {

			/*try {
				//Thread.sleep(20*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("geo.prompt.testing", true);
			profile.setPreference("geo.prompt.testing.allow", true);
			getLog().debug("inside navigate firefox");
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setCapability(FirefoxDriver.PROFILE, profile);

		}else if (getLaunchBrowser().equalsIgnoreCase("InternetExplorer")){
			/*try {
				Thread.sleep(20*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			getLog().debug("webdriver.ie.driver: "+System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			getLog().debug("inside navigate IE");
			cap = DesiredCapabilities.internetExplorer();
			//cap.setBrowserName("iexplore");
			cap.setPlatform(Platform.WINDOWS);
			cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			cap.setCapability("enablePersistentHover", false);
			//				cap.setCapability("requireWindowFocus", true);
			cap.setCapability("ignoreProtectedModeSettings", true);
			cap.setCapability("ie.ensureCleanSession", true); 

		}else if (getLaunchBrowser().equalsIgnoreCase("Chrome")){
			/*try {
				Thread.sleep(20*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			getLog().debug("inside navigate chrome");
			cap = DesiredCapabilities.chrome();
			cap.setBrowserName("chrome");

			String chromeDriver;
			if(System.getProperty("os.name").equals("Mac OS X")) {
				cap.setPlatform(Platform.MAC);
				chromeDriver = "chromedriver";
			}else {
				cap.setPlatform(Platform.WINDOWS);
				chromeDriver = "chromedriver.exe";

				getLog().debug("webdriver.chrome.driver: "+System.getProperty("user.dir")+"/drivers/"+chromeDriver);
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/"+chromeDriver);
			}

			ChromeOptions options = new ChromeOptions();
			

			options.addArguments("--silent");
			options.addArguments("--disable-extensions");
			options.addArguments("test-type");
			options.addArguments("start-maximized");		
			
			cap.setCapability(ChromeOptions.CAPABILITY, options);

		}else if (getLaunchBrowser().equalsIgnoreCase("Safari")){
			try {
				Thread.sleep(20*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cap = DesiredCapabilities.safari();
			cap.setBrowserName("safari");
			cap.setPlatform(Platform.MAC);
		}else if(launchBrowser.equalsIgnoreCase("android")){
			System.out.println((String) mobileCONFIG.get("device1"));
		  	cap = DesiredCapabilities.chrome();
		  	System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/drivers/"+"chromedriver.exe");
	        ChromeOptions chromeoptions = new ChromeOptions();                
	        chromeoptions.setExperimentalOption("androidPackage", "com.android.chrome");
	        if(getDevice().equalsIgnoreCase("device1")){
	        chromeoptions.setExperimentalOption("androidDeviceSerial",(String) mobileCONFIG.get("device1"));}
	        else if(getDevice().equalsIgnoreCase("device2")){
	        chromeoptions.setExperimentalOption("androidDeviceSerial",(String) mobileCONFIG.get("device1"));	
	        }
	        else if(getDevice().equalsIgnoreCase("device3")){
	            chromeoptions.setExperimentalOption("androidDeviceSerial",(String) mobileCONFIG.get("device1"));	
	            }
	        else if(getDevice().equalsIgnoreCase("device4")){
	            chromeoptions.setExperimentalOption("androidDeviceSerial",(String) mobileCONFIG.get("device1"));	
	            }
	        cap.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
	        cap.setCapability("platform", "WINDOWS");
	        cap.setCapability("browserName", "chrome");
	        cap.setCapability("version", "android");
	        
	        }

		getLog().debug("Url: "+CONFIG.getProperty(objectArr[0]));

		try {
			if(testCONFIG.getProperty("Env").equals("LocalMachine")) {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			}

			driver.navigate().to(CONFIG.getProperty(objectArr[0]));
			currentTitle = driver.getTitle();

		}catch(Throwable e) {
			getLog().debug(e.getMessage());
			return "Fail";
		}
		getLog().debug("@@@ DRIVER K1 "+driver);

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		if(launchBrowser.equalsIgnoreCase("Chrome") && System.getProperty("os.name").equals("Mac OS X")) {
			driver.manage().window().setSize(new Dimension(1920, 978));
		}else {
			driver.manage().window().maximize();
		}
		return "Pass";
	}
	

	
}