package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password".trim()));
	}

	@Test
	public void accntPageTitleTest() {
		String title = accPage.getAccnPageTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}

	@Test
	public void accntPageURLTest() {
		String url = accPage.getAccPageURL();
		Assert.assertTrue(url.contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL));
	}

	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accPage.isLogoutLinkDisplayed());
	}

	@Test
	public void accntsPageHeadersCount() {
		List<String> actualAccPageHeadresList = accPage.getAcctPageHeadersList();
		System.out.println(actualAccPageHeadresList);
		Assert.assertEquals(actualAccPageHeadresList.size(), AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}

	@Test
	public void accntsPageHeadersList() {
		List<String> actualAccPageHeadresList = accPage.getAcctPageHeadersList();
		System.out.println("Actual Headers list =" + actualAccPageHeadresList);
		System.out.println("Expected Headers list =" + AppConstants.ACCOUN_PAGE_EXPECTED_HEADERS_LIST);
		Assert.assertEquals(actualAccPageHeadresList, AppConstants.ACCOUN_PAGE_EXPECTED_HEADERS_LIST);
	}

	@DataProvider
	public Object getSearchProductData(){
		return new Object[][]{
			{"MacBook"},
			{"Apple"},
			{"iMac"},
			{"Samsung"}	
		};
	}
	@Test(dataProvider = "getSearchProductData")
	public void searchProductCountTest(String productName) {
		searchPage = accPage.performSearch(productName);
		Assert.assertTrue(searchPage.getSearchedProductCount() != 0);
	}

	@DataProvider
	public Object[][] getProdductTestData()
	{
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"Apple","Apple Cinema 30\""},
			{"iMac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"}	
		};
	}
	@Test(dataProvider = "getProdductTestData")
	public void selectProduct(String productName,String subProductName) {
		searchPage = accPage.performSearch(productName);
		productInfoPage = searchPage.selectProduct(subProductName);
		String actualProductHeader=productInfoPage.getProductHeaderValue();
		Assert.assertEquals(actualProductHeader,subProductName);

	}
	
	
}
