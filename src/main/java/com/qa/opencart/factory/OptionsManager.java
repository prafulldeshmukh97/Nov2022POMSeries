package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	private Properties prop;
	ChromeOptions co;
	FirefoxOptions fo;
	EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {

		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			
		{
			System.out.println("======Running chrome in Headless mode=======");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			
		{
			co.addArguments("--incognito");
		}
		return co;

	}

	public FirefoxOptions getFirefoxOptions() {

		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))

		{
			System.out.println("======Running firefox in Headless mode=======");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))

		{
			fo.addArguments("--incognito");
		}
		return fo;

	}

	public EdgeOptions getEdgeOptions() {

		eo = new EdgeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			System.out.println("======Running Edge in Headless mode=======");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))

		{
			eo.addArguments("--incognito");
		}
		return eo;

	}

}
