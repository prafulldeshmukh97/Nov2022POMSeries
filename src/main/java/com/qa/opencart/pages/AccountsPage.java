package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By logoutLink = By.linkText("Logout");
	private By accntsHeaders = By.cssSelector("div #content h2");
	private By search = By.name("search");
	private By searchIcon=By.cssSelector("#search button");
	
	
	public String getAccnPageTitle()
	{
		//String title=driver.getTitle();
		String title=eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT,AppConstants.ACCOUNT_PAGE_TITLE_VALUE );
		System.out.println("Accounts page title is: :" +title);
		return title;
	}
	
	public String getAccPageURL()
	{
		//String url=driver.getCurrentUrl();
		String url= eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNT_PAGE_FRACTION_URL);
		return url;
	}
	
	public boolean isLogoutLinkDisplayed()
	{
		//return driver.findElement(logoutLink).isDisplayed();
		return eleUtil.waitForElementVisible(logoutLink,AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}
	
	public boolean isSearchExists()
	{
		//return driver.findElement(search).isDisplayed();
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}
	
	public List<String> getAcctPageHeadersList()
	
	{
		List<WebElement> accHeaderlist=eleUtil.waitForElementsPresence(accntsHeaders, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		//List<WebElement> accHeaderlist=driver.findElements(accntsHeaders);
		List<String> heardtextList = new ArrayList<String>();
		for(WebElement e:accHeaderlist)
		{
			String text=e.getText();
			heardtextList.add(text);
			
		}
		return heardtextList;
	}
	public searchPage performSearch(String searchKey)
	{
		if(isSearchExists())
		{
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new searchPage(driver);
		}
		else {
			System.out.println("Search field is not availibale on this page ");
			return null;
		}
		
	}

}
