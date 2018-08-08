package com.uiFramework.companyName.projectName.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;




public class HomePage {
	
	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(HomePage.class);
	WaitHelper waitHelper;
	
	
	
	public HomePage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dressesMenu;
	
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;



}