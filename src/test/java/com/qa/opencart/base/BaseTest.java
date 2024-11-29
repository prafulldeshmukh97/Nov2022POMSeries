package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.searchPage;

public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage login;
	protected AccountsPage accPage;
	protected searchPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softassert;
	protected RegisterPage registerPage;
	@BeforeTest
	public void setup() 
	{
		df= new DriverFactory();
		prop=df.initprop();
		driver=df.initDriver(prop);
		login =new LoginPage(driver);
		softassert= new SoftAssert();
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
	

}
