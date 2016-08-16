package com.mgm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.mgm.main.Controller;


public class Functions extends Controller{

	public Functions(){
		super();
	}

	public static void skipModule() throws SkipException {
		throw new SkipException("Skipping this module.");
	}

	
	public static Properties loadConfigFile(String folderName, String fileName) throws IOException {
		// load the config property file
		FileInputStream fs = null;
		Properties prop = new Properties();

		if(folderName.equals("objectRepo")) {
			folderName = System.getProperty("user.dir") + "/src/com/aims/objectRepo";
		}else if(folderName.equals("config")) {
			folderName = System.getProperty("user.dir") + "/config";
		}

		fs = new FileInputStream(folderName+ "/"+fileName+".properties");
		prop.load(fs);
		return prop;

	}
	public static void handleTnCPopUp(WebDriver driver, Logger log, Properties OR) {
		log.debug("=============================");
		log.debug("Executing function handleTnCPopUp");

		try {
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.MILLISECONDS);
			if(driver.findElements(By.xpath((OR.getProperty("aims.global.TnCWarningDialogueBox.popUp.xpath")))).size()>0){
				WebElement checkboxButton = driver.findElement(By.xpath(OR.getProperty("aims.global.TnCWarningCheckbox.button.xpath")));
				checkboxButton.click();
				WebElement continueButton = driver.findElement(By.xpath(OR.getProperty("aims.global.TnCWarningContinue.button.xpath")));
				continueButton.click();
				log.debug("TnC handled ");
				Thread.sleep(5000);
			}else {
				log.debug("TnC did not come");
			}
		} catch (Throwable t) {
			log.debug("in catch. No tnc pop up.");
		}finally{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
	}
	


	public static boolean verifyElementPresenceOrAbsence(WebDriver driver,Logger log,String xpath,String verifyOverlay) {
		log.debug("=============================");
		log.debug("Executing verifyElementPresenceOrAbsence");

		WebElement webElement = null;
		boolean result = false;
		try {
			webElement = driver.findElement(By.xpath(OR.getProperty(xpath)));
			String check=webElement.getCssValue("display");
			log.debug("Display: "+check);
			if(check.contains("none") && verifyOverlay.equalsIgnoreCase("n"))
				result = true;
			else if(check.contains("none") && verifyOverlay.equalsIgnoreCase("y"))
				result = false;
			else if(check.contains("block") && verifyOverlay.equalsIgnoreCase("y"))
				result = true;
			else if(check.contains("block") && verifyOverlay.equalsIgnoreCase("n"))
				result = false;
		} catch (Throwable t) {
			//do nothing
			if (webElement==null && verifyOverlay.equalsIgnoreCase("n")) 
				result = true;
			else if(webElement==null  && verifyOverlay.equalsIgnoreCase("y")) 
				result = false;
			else if(webElement!=null  && verifyOverlay.equalsIgnoreCase("y"))
				result = true;
			else if(webElement!=null  && verifyOverlay.equalsIgnoreCase("n"))
				result = false;	
		}	
		return result;
	}


	public static void selectDateFromCalender(WebDriver driver, int dayInput, String monthInput, String yearInput, Logger log) throws InterruptedException{
		//pick year as per input from data sheet

		WebElement yearPicker = driver.findElement(By.xpath(OR.getProperty("aims.MyDropboxYearPicker")));
		yearPicker = driver.findElement(By.xpath(OR.getProperty("aims.MyDropboxYearPicker")));
		yearPicker.click();
		Thread.sleep(1000);
		Select year = new Select(yearPicker);
		year.selectByVisibleText(yearInput);
		yearPicker = driver.findElement(By.xpath(OR.getProperty("aims.MyDropboxYearPicker")));
		yearPicker.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		//pick month as per input from data sheet
		WebElement monthPicker = driver.findElement(By.xpath(OR.getProperty("aims.MyDropboxMonthPicker")));
		monthPicker.click();
		Thread.sleep(1000);
		Select month = new Select(monthPicker);
		month.selectByVisibleText(monthInput);
		monthPicker = driver.findElement(By.xpath(OR.getProperty("aims.MyDropboxMonthPicker")));
		monthPicker.sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		//pick day as per input from data sheet
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/descendant::a[contains(@class,'ui-state-default')]"));
		Iterator<WebElement> i2= days.iterator();
		while(i2.hasNext()){
			System.out.println("inside days");
			WebElement day = i2.next();
			String actualDay = day.getText();
			System.out.println(actualDay);
			int actualDayInt = Integer.parseInt(actualDay);
			Thread.sleep(1000);
			if(dayInput == actualDayInt){
				System.out.println(dayInput + " " + actualDay);
				day.click();
				Thread.sleep(2000);
				break;
			}
		}

	}

	public static void waitForElementClickable(WebDriver driver, Logger log, String locator){

		log.debug("=============================");
		log.debug("Executing waitForElementClickable");

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(locator))));

		}catch(Throwable t) {
			log.debug("An error has occurred while executing waitForElementClickable keyword "+t.getMessage());
		}

	}
	
	public static void visibleWhenAllReady( WebDriver driver,Logger log, String locator) {
		log.debug("=============================");
		log.debug("Executing visibleWhenAllReady");
		try{
		List<WebElement> element=driver.findElements(By.xpath(OR.getProperty(locator)));	
	    WebDriverWait wait = new WebDriverWait(driver, 120);
	    wait.until(ExpectedConditions.visibilityOfAllElements(element)); 
		}
		catch(Throwable t) {
			log.debug("An error has occurred while executing visibleWhenAllReady keyword "+t.getMessage());
		}
	}


	public static void renameFile(String dirPath, String newFileName, File f, Logger log) {
		try{
			File tmpFile = null;
			tmpFile = new File(dirPath+"/"+newFileName);
			log.debug("File Renamed at: "+tmpFile);
			f.renameTo(tmpFile);
		}catch(Throwable t){
			log.debug("Error while executing function renameFile" + t.getMessage());
		}
	}
	
	public void waitForRunTime(){
		
	}
}