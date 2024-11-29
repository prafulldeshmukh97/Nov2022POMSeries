package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		String broweserName = prop.getProperty("browser").trim().toLowerCase();
		System.out.println("Browser name is :" + broweserName);

		if (broweserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionManager.getChromeOptions()));
		} else if (broweserName.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
		} else if (broweserName.trim().equalsIgnoreCase("safari")) {
			// driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		} else if (broweserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(optionManager.getEdgeOptions());
			tldriver.set(new EdgeDriver(optionManager.getEdgeOptions()));
		} else {
			System.out.println("Please pass the correct browsername " + broweserName);
			throw new FrameworkException("No BROWSER FOUND EXCEPTION");
		}

		// driver.manage().deleteAllCookies();
		// driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		// driver.get(prop.getProperty("url"));
		// driver.manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getDriver().get(prop.getProperty("url"));
		// return driver;
		return getDriver();
	}

	/*
	 * this method will retunr seperate copy of local driver
	 */
	public synchronized static WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * this method initialize properties
	 * 
	 * @return
	 */
	public Properties initprop() {

		// mvn clean install -Denv="qa"
				// mvn clean install
				prop = new Properties();
				FileInputStream ip = null;
				String envName = System.getProperty("env");
				System.out.println("Running test cases on Env: " + envName);

				try {
					if (envName == null) {
						System.out.println("no env is passed....Running tests on QA env...");
						ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					} else {
						switch (envName.toLowerCase().trim()) {
						case "qa":
							ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
							break;
						case "stage":
							ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
							break;
						case "dev":
							ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
							break;
						case "prod":
							ip = new FileInputStream("./src/test/resources/config/config.properties");
							break;

						default:
							System.out.println("....Wrong env is passed....No need the test cases....");
							throw new FrameworkException("Wrong Environment Name is Passed");
						// break;
						}

					}
				} catch (FileNotFoundException e) {

				}

				try {
					prop.load(ip);
				} catch (IOException e) {
					e.printStackTrace();
				}

				return prop;

		/*
		 * prop = new Properties(); try { FileInputStream ip = new
		 * FileInputStream(".\\src\\test\\resources\\config\\config.properties");
		 * prop.load(ip); } catch (FileNotFoundException e) { e.printStackTrace(); }
		 * catch (IOException e) { e.printStackTrace(); } return prop;
		 */

	}

	public static String getScreenshot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user-dir") + "/screenshots" + System.currentTimeMillis() + ".png";
		File destinationpath = new File(path);
		try {
			FileUtil.copyFile(srcFile, destinationpath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}

}
