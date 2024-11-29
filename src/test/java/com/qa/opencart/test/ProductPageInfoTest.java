package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void accPageSetup() {
		accPage = login.doLogin(prop.getProperty("username").trim(), prop.getProperty("password".trim()));
	}

	@DataProvider
	public Object[][] getProdductImagesTestData() {
		return new Object[][] { { "MacBook", "MacBook Pro", 4 }, { "Apple", "Apple Cinema 30\"", 6 },
				{ "iMac", "iMac", 3 }, { "Samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(dataProvider = "getProdductImagesTestData")
	public void productInfoImagesTest(String productName, String subProductName, int ActualimgCount) {
		searchPage = accPage.performSearch(productName);
		productInfoPage = searchPage.selectProduct(subProductName);
		int actualImageCout = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImageCout, ActualimgCount);

	}

	@Test
	public void productInfoTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		Map<String, String> actualproductInfomap = productInfoPage.getProductInfo();
		softassert.assertEquals(actualproductInfomap.get("Brand"), "Apple");
		softassert.assertEquals(actualproductInfomap.get("price"), "$2,000.00");
		softassert.assertEquals(actualproductInfomap.get("Product Code"), "Product 18");
		softassert.assertEquals(actualproductInfomap.get("productname"), "MacBook Pro");
		softassert.assertEquals(actualproductInfomap.get("exTax"), "$2,000.00");
		softassert.assertAll();// need this strictly to assert the all assertion else we wont get results

	}

	@Test
	public void addTpoCartTest() {
		searchPage = accPage.performSearch("MacBook");
		productInfoPage = searchPage.selectProduct("MacBook Pro");
		productInfoPage.getProductQuantity(2);
		//Success: You have added MacBook Pro to your shopping cart!
		String actCartSuccessMessage = productInfoPage.addProductToCart();
		softassert.assertTrue(actCartSuccessMessage.contains("Success"));
		softassert.assertTrue(actCartSuccessMessage.contains("MacBook Pro"));
		softassert.assertEquals(actCartSuccessMessage, "Success: You have added MacBook Pro to your shopping cart!");
		softassert.assertAll();
	}

}
