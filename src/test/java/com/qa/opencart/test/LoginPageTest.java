package com.qa.opencart.test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("epic id001")
@Story("Login story tc")
public class LoginPageTest extends BaseTest {
@Severity(SeverityLevel.TRIVIAL)
@Description("logi title test")
	@Test(priority = 1)
	public void loginPageTitletest() {
		String actualtitle = login.getLoginPageTitle();
		Assert.assertEquals(actualtitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
@Severity(SeverityLevel.BLOCKER)
@Description("logi url test")
	@Test(priority = 2)
	public void loginPageUrltest() {
		String actualUrl = login.getLoginPageUrl();
		Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}

	@Test(priority = 3)
	public void forgotPassowrdLinkExistTest() {
		Assert.assertTrue(login.forgotpwdLinkExist());
	}

	@Test(priority = 4)
	public void loginTest() {
		accPage=login.doLogin(prop.getProperty("username").trim(),prop.getProperty("password".trim()));
		Assert.assertTrue(accPage.isLogoutLinkDisplayed());
	}
	@Test
	public void failTestCase()
	{
		Assert.assertFalse(accPage.isLogoutLinkDisplayed());
	}
}
