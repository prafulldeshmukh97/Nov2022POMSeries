package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private Map<String, String> productInfoMap;// to creating global to access inside multiple method to avoid large
												// methos split into three method

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	By header = By.tagName("h1");
	By ProductImages = By.cssSelector("ul.thumbnails img");
	By productInformation = By.xpath("(//div[@id=\"content\"] //ul[@class=\"list-unstyled\"])[position()=1]/li");
	By productPriceInformation = By.xpath("(//div[@id=\"content\"] //ul[@class=\"list-unstyled\"])[position()=2]/li");
	By quantity = By.id("input-quantity");
	By addToCartBtn = By.id("button-cart");
    By addToCartSuccessMessage = By.cssSelector(".alert.alert-success");
	public String getProductHeaderValue() {
		String productheaderValue = eleUtil.doElementGetText(header);
		System.out.println("Product Header =" + productheaderValue);
		return productheaderValue;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsPresence(ProductImages, AppConstants.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("Product images count =" + imagesCount);
		return imagesCount;
	}

	public void getProductQuantity(int qty) {
		eleUtil.doSendKeys(quantity, String.valueOf(qty));
	}

	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
	String successMessage= eleUtil.waitForElementVisible(addToCartSuccessMessage, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	System.out.println("Cart success Message = "+successMessage.substring(0,successMessage.length()-1).replace("\n",""));
		return successMessage.substring(0,successMessage.length()-1).replace("\n","");//remove the extra x sign and new line char \nusing substring method
	}

	public Map<String, String> getProductInfo() {

		// 1.Header Product Name
		// productInfoMap = new HashMap<String, String>();
		// productInfoMap = new LinkedHashMap<String, String>();//used to linkedhashmap
		// to maintain order
		productInfoMap = new TreeMap<String, String>();// to maintain sorted order
		productInfoMap.put("productname", getProductHeaderValue());// getting headername by method
		getProductMetaData();
		getProductPricingData();
		System.out.println(productInfoMap);
		return productInfoMap;
	}

	// feaching product data
	private void getProductMetaData() {
		// 2.Metadata
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock

		List<WebElement> inflist = eleUtil.getElements(productInformation);
		for (WebElement e : inflist) {
			String fulltext = e.getText();// Brand :Apple
			String splitedString[] = fulltext.split(":");
			String key = splitedString[0].trim();
			String value = splitedString[1].trim();
			productInfoMap.put(key, value);

		}
	}

	// Featching product price data
	private void getProductPricingData() {

		// 3.Price Data
		// $2,000.00
		// Ex Tax: $2,000.00

		List<WebElement> priceInfoList = eleUtil.getElements(productPriceInformation);
		String Price = priceInfoList.get(0).getText();// $2,000.00

		String exTax = priceInfoList.get(1).getText().trim();
		String exTaxValue = exTax.split(":")[1].trim();// $2,000.00
		productInfoMap.put("price", Price);// custome key created due key not availiable
		productInfoMap.put("exTax", exTaxValue);

	}
	

}
