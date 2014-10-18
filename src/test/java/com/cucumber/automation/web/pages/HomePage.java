package com.cucumber.automation.web.pages;


import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cucumber.automation.utils.DriverFactory;
import com.cucumber.automation.webservices.RestFactory;

public class HomePage extends DriverFactory {
	
			By eleMasterHeadLogo = By.cssSelector("div.trb_masthead_title");
			By lnkFBLike = By.cssSelector("a.trb_masthead_fb_like");
			By lblDate = By.cssSelector("div.trb_masthead_date > time.trb_article_dateline_time");
			By lnkWeather = By.cssSelector("a.trb_masthead_weather");
			By edtSearchBox = By.cssSelector("input.trb_nav_search_form_input");
			By btnSearch = By.cssSelector("input.trb_nav_search_form_submit");
			By lnkSubscribe = By.cssSelector("a[data-reg-handler='signUpHandler']");
			By eleHTML = By.cssSelector("html");
			By lnkSports = By.cssSelector("a.trb_nav_main_menuitem[href='/sports/#navtype=navbar']");
			By linkSportsAngels = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/angels/#navtype=navbar']");
			By linkSportsClippers = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/clippers/#navtype=navbar']");
			By linkSportsKings = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/kings/#navtype=navbar']");
			By linkSportsNFL = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/nfl/#navtype=navbar']");
			By linkSportsWorldCup = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/soccer/worldcup/#navtype=navbar']");
			By linkSportsLakers = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/lakers/#navtype=navbar']");
			By linkSportsUSC = By.cssSelector("div.trb_nav_main_submenu > div > a[href='/sports/usc/#navtype=navbar']");

			By eleMasterHeadLogoContainer = By.cssSelector("header.trb_masthead");
			
			// Menu Bar locators
			By eleNavigationTopBox = By.cssSelector("div.trb_nav_topBox");
			By eleNavigationBottomBox = By.cssSelector("div.trb_nav_bottomBox");
			By eleNavigationBottomLinks = By.cssSelector("div.trb_nav_bottomBox > div[data-role='navmenu_mainmenu scrollable_inner'] > a.trb_nav_main_menuitem");
			
			 
			//Locaotors Tab size window
			By lnkMenuExpander = By.cssSelector("a[data-role='navmenu_bannerbutton']");
			
			//Locators for Nation Now page
			//By eleAroundTheNation = By.cssSelector("div.trb_blogroll[data-role='dogs'] > div.trb_blogroll_barker[data-role='dogs_load']");
			By eleAroundTheNation = By.cssSelector("div.trb_blogroll > div.trb_blogroll_barker");
			By linkNext  = By.cssSelector("aside.trb_barker.trb_barker_galleryCarousel[data-barker-itemcount='15'] > a.trb_barker_next");
			By linkPrevious = By.cssSelector("aside.trb_barker.trb_barker_galleryCarousel[data-barker-itemcount='15'] > a.trb_barker_previous");

			
			WebElement aroundTheNation;
			String links[] = new String[500];
			
			ArrayList<String> brokenLinks = new ArrayList<String>();

	public void verifyHomePageTitle() {
		//assertTrue(driver.getTitle().equals("Los Angeles Times - California, national and world news - Los Angeles Times"));
		
	}

	public void verifyMasterLogoPresence() {
		assertTrue(driver.findElement(eleMasterHeadLogo).isDisplayed());
		
	}

	public void verifyFBLinkPresence() {
		assertTrue(driver.findElement(lnkFBLike).isDisplayed());
		
	}

	public void verifyCurrentDateDisplay() {
		assertTrue(driver.findElement(lblDate).isDisplayed());
		
		
	}

	public void verifyWeatherLinkDisplay() {
		assertTrue(driver.findElement(lnkWeather).isDisplayed());
		
		
	}

	public void typeSearchTerm(String text) {
		driver.findElement(edtSearchBox).sendKeys(text);
		
	}

	public SearchPage clickSearchIcon() {
		driver.findElement(btnSearch).click();
		return new SearchPage();
	}

	public SubscribePage clickSubscribeLink() {
		driver.findElement(lnkSubscribe).click();
		return new SubscribePage();
	}

	public void mouseOverOnSportsLink() {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(lnkSports)).build().perform();
		
		
	}


	public void verifySportMenuBarItems() {
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsAngels));
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsClippers));
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsKings));
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsLakers));
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsNFL));
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsUSC));
		mouseOverOnSportsLink();
		assertNotNull(waitAndGetElement(linkSportsWorldCup));
		

	}
	
	public WebElement waitAndGetElement(By locator) {
			return waitVar.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}

	public void verifyLATimesHeaderLocation() {
		assertTrue(alignmentCheck(driver.findElement(eleMasterHeadLogoContainer), driver.findElement(eleMasterHeadLogo)));
		
	}

	public void clickMenuExpander() throws InterruptedException {
		driver.findElement(lnkMenuExpander).click();
		
	}

	public int countCatalogItems(String browserSize) {
		int items = 0;
		if (browserSize.equalsIgnoreCase("small")) items= 0;
		else if (browserSize.equalsIgnoreCase("medium")) {
			items =Integer.parseInt(driver.findElement(eleNavigationTopBox).getAttribute("data-nav-memberlinks")); 
		}
		else if (browserSize.equalsIgnoreCase("large")) {
			//int bottomItems =  (driver.findElements(eleNavigationBottomLinks)).size();
			int bottomItems =  getCountOfVisibleItems(driver.findElements(eleNavigationBottomLinks));
			items =Integer.parseInt(driver.findElement(eleNavigationTopBox).getAttribute("data-nav-memberlinks"));
			items = items + bottomItems;
		}
			
		return items;
	}

	public int getCountOfVisibleItems(List<WebElement> eleList) {
		int count = 0;
		for (WebElement ele: eleList) {
			System.out.println(ele.getText());
			if (ele.isDisplayed()) count++;
			
		}
		
		return count;
		
	}
	
	public void verifyNavigationLinksCount(int arg1) {
		if(arg1 == 9)
		assertEquals(arg1, countCatalogItems("large"));
		else if (arg1 == 2)
		assertEquals(arg1, countCatalogItems("medium"));
			
	}

	public void goToNationsNowPage() {
		//driver.navigate().to("http://www.latimes.com/nation/nationnow/");
		driver.get("http://www.latimes.com/nation/nationnow/");
		
		System.out.println("Nation now page opened");
		
	}

	public void scrollDownToAroundTheNation() throws InterruptedException {
		   //Locatable element = (Locatable) driver.findElement(eleAroundTheNation);
			aroundTheNation = driver.findElement(eleAroundTheNation);
			Point p = aroundTheNation.getLocation();
		    //Point p= element.getCoordinates().onPage();
		    JavascriptExecutor js = (JavascriptExecutor) driver; 
		    System.out.println("Moving to desired location");
		    js.executeScript("window.scrollTo(" + p.getX() + "," + (p.getY()-50) + ");");
		    
		   
			wait(5);
			
		    driver.findElement(linkNext).click();
		    driver.findElement(linkPrevious).click();
		    
		    wait(5);
		    
	}



	public void verifyImagePixels() {
		
		List<WebElement> imageList = aroundTheNation.findElements(By.cssSelector("div.trb_barker_galleryCarousel_item"));
				
		for(WebElement ele: imageList) {
			WebElement img = ele.findElement(By.cssSelector("figure"));
			Dimension dim = img.getSize();
			System.out.print("Image height in Pixel = " + dim.getHeight());
			System.out.println(" Image width  in Pixel = " + dim.getHeight());
			
		}
	}
	
	
	public void verifyNumberOfImages(int arg1) {
		List<WebElement> imageList = aroundTheNation.findElements(By.cssSelector("div.trb_barker_galleryCarousel_item"));
		assertEquals(arg1, imageList.size());
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void wait(int wSec) throws InterruptedException {
		long sec = wSec * 1000;
		Thread.sleep(sec);
	}


	public int countNumberOfElements(String arg1) {
		List<WebElement> lstElements = driver.findElements(By.cssSelector(arg1));
		System.out.println("Original number of element  = " + lstElements.size());
		
		int count = 0;
		if (arg1.equals("a")) {
			try{
				for (WebElement ele: lstElements) {
					try {
						String hrefValue = ele.getAttribute("href");
						if (!(hrefValue.equals(""))) {
							if (hrefValue.endsWith("/")) {
								links[count] = hrefValue.substring(0, hrefValue.length()-1);
							} else {
								links[count] = hrefValue;
							}
							
							count++;
						}
					} catch (NullPointerException npe) { }

				}
				
			} catch (StaleElementReferenceException sre) {System.out.println("item got staled");}
		} else {
			return lstElements.size();	
		}
		return count;
	}

	public void verifyLinkStatusCode() throws ClientProtocolException, IOException {
		RestFactory rt = new RestFactory();
		for (int i=351; i<418; i++) {
			try {
				System.out.println("Getting " + links[i]);
				rt.getRequest(links[i]);
			} catch (UnknownHostException uhe) {
				System.out.println(" ************************** Got exception for " + links[i]);
				uhe.printStackTrace();
				brokenLinks.add(links[i]);
			
			} catch (Exception e ) {
				System.out.println(" ************************** Got exception for " + links[i]);
				e.printStackTrace();
			}
			System.out.println("Verifying status of request");
			rt.verifyStatusCode(200);
		}
		
	}

	public void verifyBrokenLinks() {
		for (int i=0; i < brokenLinks.size(); i++) {
			String url = brokenLinks.get(i);
			driver.get(url);
			String html = driver.getTitle();
			System.out.println("Link = " + url + "######################## Title = " + html);
		}
		
	}
}



