package com.uiFramework.companyName.projectName.helper.wait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class WaitHelper {

	private WebDriver driver;

	private Logger log = LoggerHelper.getLogger(WaitHelper.class);

	public WaitHelper(WebDriver driver) {
		this.driver = driver;
		log.info("WaitHelper Object Created...");
	}

	/**
	 * This is implicit wait method
	 * 
	 * @param timeout
	 * @param unit
	 */
	public void setImplicitWait(long timeout, TimeUnit unit) {
		driver.manage().timeouts().implicitlyWait(timeout, unit);
		log.info("Implicit Wait has been set to: " + timeout);
	}

	/**
	 * This will help we get WebDriverWait Object
	 * 
	 * @param TimeOutInSeconds
	 * @param PollingEveryInMiliSec
	 * @return
	 */
	private WebDriverWait getWait(int TimeOutInSeconds, int PollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		
		// The method pollingEvery(long, TimeUnit) from the type FluentWait<WebDriver>
		// is deprecated
		//wait.pollingEvery(PollingEveryInMiliSec, TimeUnit.MILLISECONDS);
		wait.pollingEvery(Duration.ofMillis(PollingEveryInMiliSec));
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);

		// refer:https://stackoverflow.com/questions/49687699/how-to-remove-deprecation-warning-on-timeout-and-polling-in-selenium-java-client
		// import java.time.Duration;
		// //lines of code
		// Wait<WebDriver> gWait = new
		// FluentWait<WebDriver>(pDriver).withTimeout(Duration.ofSeconds(100))
		// .pollingEvery(Duration.ofMillis(600)).ignoring(NoSuchElementException.class);

		return wait;

	}

	/**
	 * This method will make sure element is visible
	 * 
	 * @param element
	 * @param TimeOutInSeconds
	 * @param PollingEveryInMiliSec
	 */
	public void waitForElementVisibleWithPollingTime(WebElement element, int TimeOutInSeconds,
			int PollingEveryInMiliSec) {
		log.info("Wait for element: " + element.toString() + " for " + TimeOutInSeconds + " seconds");
		WebDriverWait wait = getWait(TimeOutInSeconds, PollingEveryInMiliSec);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element " + element + " is visible now");
	}

	/**
	 * This method will make sure element is clickable
	 * 
	 * @param element
	 * @param TimeOutInSeconds
	 */
	public void waitForElementClickable(WebElement element, int TimeOutInSeconds) {
		log.info("Wait for element: " + element.toString() + " for " + TimeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element " + element + " is clickable now");
	}

	/**
	 * This method will make sure element is invisible
	 * 
	 * @param element
	 * @param TimeOutInSeconds
	 * @return
	 */
	public boolean waitForElementNotPresent(WebElement element, int TimeOutInSeconds) {
		log.info("Wait for element: " + element.toString() + " for " + TimeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		boolean status = wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("Element " + element + " is NOT present now");
		return status;
	}

	/**
	 * This method will make sure frame is available and switch to it
	 * 
	 * @param element
	 * @param TimeOutInSeconds
	 */
	public void waitForFrameToBeAvailableAndSwitchToIt(WebElement element, int TimeOutInSeconds) {
		log.info("Wait for element: " + element.toString() + " for " + TimeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, TimeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame is available and switch to it now");

	}
	
	
	/**
	 * This method is return a FluentWait Object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	public Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		Wait<WebDriver> fWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMiliSec)).ignoring(NoSuchElementException.class);
		return fWait;
	}
	
	/**
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec ) {
		Wait<WebDriver> fWait = getFluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fWait.until(ExpectedConditions.visibilityOf(element));
		
		return element;
	}

	public void pageLoadTime(long timeOut, TimeUnit unit) {
		log.info("Wait for page loading for " + unit);
		driver.manage().timeouts().pageLoadTimeout(timeOut, unit);
		log.info("Page is loaded");
	}
	
	/**
	 * This method will make sure elementToBeClickable
	 * 
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		log.info("waiting for :" + element.toString() + " for :" + timeOutInSeconds + " seconds");
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now...");
}
}
