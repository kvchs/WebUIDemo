package com.uiFramework.companyName.projectName.helper.javaScript;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

public class JavaScriptHelper {
	
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(JavaScriptHelper.class);
	
	public JavaScriptHelper(WebDriver driver) {
		this.driver = driver;
		log.info("JavaScriptHelper has been initialised");
	}
	
	public Object executeScript(String script) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(script);
	}
	
	public Object executeScript(String script, Object...args) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		return executor.executeScript(script, args);
	}
	
	public void scrollToElement(WebElement element) {
		log.info("Scroll to WebElement...");
		executeScript("window.scrollTo(arguments[0], arguments[1])", element.getLocation().x, element.getLocation().y);
		
	}
	
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("Element is clicked: " + element.toString());
	}
	
	public void scrollIntoView(WebElement element) {
		log.info("Scroll element in to view");
		executeScript("arguments[0].scrollIntoView()", element);
	}
	
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("Element is clicked: " + element.toString());
	}
	
	public void scrollDownVertically() {
		log.info("Scrolling down vertically");
		executeScript("window.scrollTo(0, document.body.scrollHight)");
	}
	
	public void scrollUpVertically() {
		log.info("Scrolling up vertically");
		executeScript("window.scrollTo(0, -document.body.scrollHight)");
	}
	
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBY(0, "+pixel+")");
		log.info("scroll down " + pixel + " by pixel");
	}
	
	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBY(0, -"+pixel+")");
		log.info("scroll down " + pixel + " by pixel");
	}
	
	public void zoomInBy100Precentage() {
		log.info("zoom in to 100%");
		executeScript("document.body.style.zoom='100%'");
	}
	
	public void zoomOutBy50Precentage() {
		log.info("zoom out to 50%");
		executeScript("document.body.style.zoom='50%'");
	}
	
	public void clickElement(WebElement element) {
		log.info("Click element by script method");
		executeScript("arguments[0].click()", element);
	}
}
