package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AppConstants {
	
	//timout
	public static final int DEFAULT_SHORT_TIMEOUT=5;
	public static final int DEFAULT_MEDIUM_TIMEOUT=10;
	public static final int DEFAULT_LONG_TIMEOUT=20;
	
	//titles
	public static final String LOGIN_PAGE_TITLE_VALUE="Account Login"; 
	public static final String ACCOUNT_PAGE_TITLE_VALUE="My Account";
	
	//URL
	public static final String LOGIN_PAGE_FRACTION_URL="route=account/login"; 
	public static final String ACCOUNT_PAGE_FRACTION_URL="route=account/account";
	public static final int ACCOUNTS_PAGE_HEADERS_COUNT = 4;
	public static final List<String> ACCOUN_PAGE_EXPECTED_HEADERS_LIST = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final String USER_REG_SUCCESS_MSG="Your Account Has Been Created!";
	
	///****************Test data Sheet Names
	public static final String REGISTER_SHEET_NAME = "register";

}
