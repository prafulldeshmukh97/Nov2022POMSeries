package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class searchPage {
//1.
	private WebDriver driver;
	public ElementUtil eleutil;

	// 2 const
	public searchPage(WebDriver driver) {
		this.driver = driver;
		eleutil=new ElementUtil(driver);
	}

	// 3. locatore
	private By searchProductResults = By.cssSelector("div#content .product-layout");
	// 4 methods

	public int getSearchedProductCount() {
		int productCount = eleutil.waitForElementsVisible(searchProductResults, AppConstants.DEFAULT_MEDIUM_TIMEOUT)
				.size();
		System.out.println("Product count =" + productCount);
		return productCount;
	}

	public ProductInfoPage selectProduct(String productName) {
		By productLocator = By.linkText(productName);
		eleutil.waitForElementPresence(productLocator, AppConstants.DEFAULT_MEDIUM_TIMEOUT).click();

		return new ProductInfoPage(driver);
	}

}
