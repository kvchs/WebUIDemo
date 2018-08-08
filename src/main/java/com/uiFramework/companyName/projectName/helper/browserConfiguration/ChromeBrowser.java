package com.uiFramework.companyName.projectName.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("--display-popup-blocking");
		DesiredCapabilities chrome = DesiredCapabilities.chrome();
		chrome.setJavascriptEnabled(true);
		options.setCapability(ChromeOptions.CAPABILITY, chrome);

		// For Linux
		if (System.getProperty("os.name").contains("Linux")) {
			options.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
		}
		return options;
	}

	public WebDriver getChromeDriver(ChromeOptions cap) {

		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResource("/src/main/resources/drivers/chromedriver"));
			return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResource("/src/main/resources/drivers/chromedriver.exe"));
			return new ChromeDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chrome");
			return new ChromeDriver(cap);
		}
		return null;
	}
	

}
