package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.cssSelector("input[name='firstname']");
	private By lastName = By.cssSelector("input[name='lastname']");
	private By email = By.cssSelector("input[name='email']");
	private By telephone = By.cssSelector("input[name='telephone']");
	private By password = By.cssSelector("input[name='password']");
	private By confirmPassword = By.cssSelector("input[name='confirm']");
	private By subscribeYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");
	private By subscribeNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	private By agreeCheckbox = By.xpath("//input[@type='checkbox'and @name='agree']");
	private By continueBtn = By.xpath("//input[@type='submit']");
	private By registrationSuccessMessage = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementVisible(this.firstName, AppConstants.DEFAULT_MEDIUM_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(confirmPassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);

		}
		eleUtil.doActionsClick(agreeCheckbox);
		eleUtil.doClick(continueBtn);
		String successMessage = eleUtil
				.waitForElementVisible(registrationSuccessMessage, AppConstants.DEFAULT_MEDIUM_TIMEOUT).getText();
		System.out.println("Success message is : " + successMessage);
		if (successMessage.contains(AppConstants.USER_REG_SUCCESS_MSG))

		{
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		return false;
	}
}
