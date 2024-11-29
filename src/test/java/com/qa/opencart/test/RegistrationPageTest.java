package com.qa.opencart.test;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void regitrationPageSetup() {
		registerPage = login.navigateToRegisterPage();

	}

	@DataProvider
	public Object[][] getregUserTestData() {
		Object registrationData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return registrationData;

	}
public String getRandomEmail()//it will generate random email id
{
	Random random = new Random();
	String email="prafull"+random.nextInt(1000)+"@gamil.com";
	return email;
}
	@Test(dataProvider = "getregUserTestData")
	public void regUserTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		registerPage.registerUser(firstName, lastName, getRandomEmail(), telephone, password, subscribe);
	}
}
