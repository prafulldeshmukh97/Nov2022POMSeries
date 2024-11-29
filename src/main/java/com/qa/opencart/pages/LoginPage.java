package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	//1. Private By lovators
	private By emaild =By.id("input-email");
	private By password =By.id("input-password");
	private By loginbtn =By.xpath("//input[@value='Login']");
	private By forgotPwdLink =By.linkText("Forgotten Password");
	private By registrationLink = By.linkText("Register");
	
	//2. public page constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3. Page Actions/Methods
	@Step("getting login page title")
	public String getLoginPageTitle()
	{
		//String title=driver.getTitle();
		String title=eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT,AppConstants.LOGIN_PAGE_TITLE_VALUE);
		System.out.println("Login page title is "+title);
		return title;
	}
	@Step("getting login page url")
	public String getLoginPageUrl()
	{
		//String url=driver.getCurrentUrl();
		String url=eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_MEDIUM_TIMEOUT,AppConstants.LOGIN_PAGE_FRACTION_URL);
		System.out.println("Login page title is "+url);
		return url;
	}	
	@Step("checking forgot password link exust ot not")
	public boolean forgotpwdLinkExist()
	{
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.waitForElementPresence(forgotPwdLink,AppConstants.DEFAULT_SHORT_TIMEOUT).isDisplayed();
	}
	@Step(" login with username :{0} and password :{1}")
	public AccountsPage doLogin(String Username ,String Password)
	{
		/*driver.findElement(emaild).sendKeys(Username);
		driver.findElement(password).sendKeys(Password);
		driver.findElement(loginbtn).click();*/
		System.out.println("Your app credentials are Username = "+Username+"and Password = "+Password);
		eleUtil.waitForElementPresence(emaild,AppConstants.DEFAULT_LONG_TIMEOUT).sendKeys(Username);
		eleUtil.doSendKeys(password, Password);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	@Step("Navigating register page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registrationLink);
		return new RegisterPage(driver);
	}
}
