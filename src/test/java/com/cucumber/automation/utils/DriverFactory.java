package com.cucumber.automation.utils;

import static org.junit.Assert.assertFalse;
import io.appium.java_client.AppiumDriver;

import java.awt.image.BufferedImage;
import java.awt.image.RasterFormatException;
import java.io.BufferedReader;
/*import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;*/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

	public static WebDriver driver = null;
	public static WebDriverWait waitVar = null;

	// properties files reader object:
	final static PropertyFileReader propObj = new PropertyFileReader();

	public static String browserId = propObj.returnPropVal("browserId");
	public static String baseURL = propObj.returnPropVal("baseURL");
	public static String actualMenuBarImage = propObj.returnPropVal("actualMenuBarImage");
	public static String expectedMenuBarImage = propObj.returnPropVal("expectedMenuBarImage");
	public static String diffMenuBarImage = propObj.returnPropVal("diffMenuBarImage");
	
	public static Platform currentOS = Platform.getCurrent();

	String dim = "";
	String scenarioName = null;
	String screenshotFileName = null;
	/*Scenario scenario = null;*/

	public void createDriver() throws MalformedURLException,
			InterruptedException {

		if (browserId.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			/*
			 * DesiredCapabilities capabillities =
			 * DesiredCapabilities.firefox();
			 * capabillities.setCapability("version", "29.0");
			 * capabillities.setCapability("platform", Platform.WINDOWS);
			 * capabillities.setCapability("name", "TribuneSanityCheck1");
			 * driver = new RemoteWebDriver( new URL(
			 * "http://shnakeygarg:66c91399-8cfb-46ca-a2da-f09c2ce5f170@ondemand.saucelabs.com:80/wd/hub"
			 * ), capabillities); Thread.sleep(5000);
			 */
		} else if (browserId.equalsIgnoreCase("ie")) {

			if (Platform.MAC.is(currentOS)) {
				System.out.println("IE can not be started on iOS");
				return;
			}
			final File file = new File(
					"src/test/resources/drivers/IEDriverServer.exe");

			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());

			final DesiredCapabilities ieCapabilities = DesiredCapabilities
					.internetExplorer();
			ieCapabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			driver = new InternetExplorerDriver(ieCapabilities);
		} else if (browserId.equalsIgnoreCase("chrome")) {

			Platform currentOS = Platform.getCurrent();
            if (Platform.MAC.is(currentOS) || Platform.LINUX.is(currentOS)) {
				final File file = new File(
						"src/test/resources/drivers/chromedriver");
				System.setProperty("webdriver.chrome.driver",
						file.getAbsolutePath());
			} else {
				final File file = new File(
						"src/test/resources/drivers/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver",
						file.getAbsolutePath());
			}

			final DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(capability);

		} else if (browserId.equalsIgnoreCase("safari")) {
			Platform currentOS = Platform.getCurrent();
			if (Platform.MAC.is(currentOS)) {
				driver = new SafariDriver();
			} else {
				System.out.println("safari can only be initiated on MAC");
				return;
			}
		} else {
			System.out.println("Browser not supported : " + browserId);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS);

		driver.get(baseURL);

		waitVar = new WebDriverWait(driver, 30);
	}

	public void appiumDriverandroid() throws MalformedURLException {

		// set up appium
		final File classpathRoot = new File(System.getProperty("user.dir"));
		final File appDir = new File(classpathRoot, "src/test/resources/apps");
		final File app = new File(appDir, "com.xebia.eventsapp_2.1.apk");

		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.xebia.eventsapp");
		capabilities.setCapability("appActivity",
				"com.xebia.eventsapp.HomePageActivity");
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 90);
	}

	public void appiumDriverandroidDevice() throws MalformedURLException {

		// set up appium
		final File classpathRoot = new File(System.getProperty("user.dir"));
		final File appDir = new File(classpathRoot, "src/test/resources/apps");
		final File app = new File(appDir, "com.xebia.eventsapp_2.1.apk");

		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.xebia.eventsapp");
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 90);
	}

	public void appiumDriverandroidRemote() throws MalformedURLException {

		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", "1.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "4.4");
		capabilities.setCapability("deviceType", "phone");
		capabilities.setCapability("app",
				"sauce-storage:com.xebia.eventsapp_2.1.apk");
		capabilities.setCapability("appPackage", "com.xebia.eventsapp");
		capabilities.setCapability("appActivity",
				"com.xebia.eventsapp.HomePageActivity");
		driver = new AppiumDriver(
				new URL(
						"http://shnakeygarg:66c91399-8cfb-46ca-a2da-f09c2ce5f170@ondemand.saucelabs.com:80/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 900);
	}

	public void appiumDriveriOS() throws MalformedURLException {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities
				.setCapability(
						"app",
						"/Users/Apple/Library/Developer/Xcode/DerivedData/PayCloud-fbfzydvnldsmuldjbaozycplnvvo/Build/Products/Debug-iphonesimulator/PayCloud.app");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 90);
	}

	public void appiumDriveriOSRemote() throws MalformedURLException {
		// set up appium
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("appiumVersion", "1.0");
		capabilities.setCapability("app", "sauce-storage:PayCloud.zip");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		driver = new AppiumDriver(
				new URL(
						"http://shnakeygarg:66c91399-8cfb-46ca-a2da-f09c2ce5f170@ondemand.saucelabs.com:80/wd/hub"),
				capabilities);

		waitVar = new WebDriverWait(driver, 90);
	}

	public byte[] returnScreenShot() {
		return ((RemoteWebDriver) driver).getScreenshotAs(OutputType.BYTES);
	}

	/*
	 * public void saveScreenShot(Scenario scenario) throws IOException {
	 * 
	 * File scrFile =
	 * ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); final File
	 * classpathRoot = new File(System.getProperty("user.dir")); final File
	 * imageDir = new File(classpathRoot, "src/test/resources/images");
	 * 
	 * FileUtils.copyFile(scrFile, new
	 * File(imageDir+"\\"+scenario.getName()+getCurrentDateTime()+".png")); }
	 */

	public void teardown() {
		// close the app
		if (driver != null) {
			driver.quit();
		}

	}

	/*
	 * public String getCurrentDateTime(){ DateFormat dateFormat = new
	 * SimpleDateFormat("MMddyyyy-HHmmss"); Date date = new Date(); return
	 * dateFormat.format(date); }
	 */

	public boolean alignmentCheck(WebElement outerElem, WebElement innerElem) {
		// get borders of outer element
		boolean contains = true;
		Point outerLoc = outerElem.getLocation();
		Dimension outerDim = outerElem.getSize();
		int outerLeftX = outerLoc.getX();
		int outerRightX = outerLeftX + outerDim.getWidth();
		int outerTopY = outerLoc.getY();
		int outerBottomY = outerTopY + outerDim.getHeight();

		// get borders of inner element
		Point innerLoc = innerElem.getLocation();
		Dimension innerDim = innerElem.getSize();
		int innerLeftX = innerLoc.getX();
		int innerRightX = innerLeftX + innerDim.getWidth();
		int innerTopY = innerLoc.getY();
		int innerBottomY = innerTopY + innerDim.getHeight();

		// assures the inner borders don't cross the outer borders
		contains = (outerLeftX <= innerLeftX) && (innerRightX <= outerRightX)
				&& (outerTopY <= innerTopY) && (innerBottomY <= outerBottomY);
		return contains;
	}

	public void resizeWebDriver(int a, int b, By locator)
			throws InterruptedException {
		driver.manage().window().setSize(new Dimension(a, b));
		;
		//waitVar.until(ExpectedConditions.elementToBeClickable(locator));
		//waitVar.until(ExpectedConditions.visibilityOfElementLocated(locator));
		dim = Integer.toString(a) + "x" + Integer.toString(b);
		wait(8);
		//waitVar.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void takeScreenshot() throws IOException {

		File snapshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		String fileName = scenarioName + "_"
				+ new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		final File classpathRoot = new File(System.getProperty("user.dir"));
		final File imageDir = new File(classpathRoot,
				"src/test/resources/images");

		screenshotFileName = imageDir + "\\" + dim + "_" + fileName + ".png";
		if (dim.equalsIgnoreCase("")) {
			dim = "fullSize";
		}
		FileUtils.copyFile(snapshot, new File(screenshotFileName));
		
		createHTMLFile();
	}

	public void saveScenarioName(String name) {
		scenarioName = name;
	}

/*	public void compareImages() throws IOException, InterruptedException {

		Process p = Runtime
				.getRuntime()
				.exec(new String[] {
						"C:\\Program Files\\ImageMagick-6.8.9-Q16\\compare.exe", 
						"-metric",
						"AE",
						"-fuzz",
						"1%",
						"C:\\Work\\Tribune\\CombAuto\\Tribune\\src\\test\\resources\\images\\bar.png",
						"C:\\Work\\Tribune\\CombAuto\\Tribune\\src\\test\\resources\\images\\ExpectedBarImage.png",
						"C:\\Work\\Tribune\\CombAuto\\Tribune\\src\\test\\resources\\images\\output\\output.png",

				});

		p.waitFor();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getErrorStream()));
		String line = reader.readLine();
		while (line != null) {
			System.out.println("$$$$$$$$$$$$$$$$$$$$$" + line);
			if (line.contains("compare.exe: image widths or heights differ")) assertFalse("image widths or heights differ", true);;
			if (!line.equalsIgnoreCase("0")) assertFalse("There are diff(s) in images. Check output file.", true);
			
			line = reader.readLine();
		}

		System.out.println("done");

	}*/

	public void cutRequiredImage(String text) throws IOException {
		WebElement element = null;
		if (text.equalsIgnoreCase("Left menu Bar")) {
			element = driver.findElement(By.cssSelector("div[data-role='navmenu_mainmenusubcontainer']"));
		}
	       File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	        Point p = element.getLocation();

	        int width = element.getSize().getWidth();
	        int height = element.getSize().getHeight();

	        BufferedImage img = ImageIO.read(screen);
	        BufferedImage dest  = null;
	        try {
	        	dest = img.getSubimage(p.getX(), p.getY(), width,   
                        height);
	        } catch (RasterFormatException rfe) {
	        	System.out.println(rfe.getMessage());
	        	driver.navigate().refresh();
	        	
	        	element = driver.findElement(By.cssSelector("div[data-role='navmenu_mainmenusubcontainer']"));
	        	screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		        p = element.getLocation();

		        width = element.getSize().getWidth();
		        height = element.getSize().getHeight();

		        img = ImageIO.read(screen);
		        dest = img.getSubimage(p.getX(), p.getY(), width,   
                        height);
		        
	        }

	        

	        ImageIO.write(dest, "png", screen);

	        File imageDir = new File(System.getProperty("user.dir"), "src/test/resources/images/");
			String actualFileName = imageDir + "//" + actualMenuBarImage;
	        File f = new File(actualFileName);

	        FileUtils.copyFile(screen, f);
	        
	        /*BufferedImage image = ImageIO.read(f);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", baos);
	        byte[] res=baos.toByteArray();

	        scenario.embed(res, "image/png");*/
		
	}

	
	public void compareImages() throws IOException, InterruptedException {
		File imageDir = new File(System.getProperty("user.dir"), "src/test/resources/images/");
		actualMenuBarImage = imageDir + "\\" + actualMenuBarImage;
		expectedMenuBarImage = imageDir +  "\\" + expectedMenuBarImage;
		diffMenuBarImage = imageDir +  "\\output\\" + diffMenuBarImage;
		
		File utilsDir = new File(System.getProperty("user.dir"), "src/test/resources/utils/");
		
		try 
		{ 
			Process p =  Runtime.getRuntime().exec(new String[]{
			     "ruby",
			     utilsDir + "\\diff.rb",
			     actualMenuBarImage,
			     expectedMenuBarImage,
			     diffMenuBarImage
			    });
		
		p.waitFor(); 
		BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
		String line=reader.readLine(); 
		while(line!=null) 
		{ 
		System.out.println(line); 
		
		if (line.contains("pixels changed:")) {
			if (!line.contains("pixels changed:     0")) assertFalse("There is diff in actual and expected images.", true);;
		}
		
		line=reader.readLine(); 
		} 

		} 
		catch(IOException e1) {} 
		catch(InterruptedException e2) {}

	}

	
	public void createHTMLFile() throws IOException {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html><head><title>Tribune Automation Suite</title></html></head>");
		htmlBuilder.append("<body>");
		htmlBuilder.append("Latimes.com screenshots in diff sizes");
		htmlBuilder.append("<br/><img src='file:///" + screenshotFileName + "'/>");
		
		htmlBuilder.append("</body></html>\n");

		FileWriter writer = new FileWriter(new File(System.getProperty("user.dir"), "src/test/resources") + "//screenShots.html");
		writer.write(htmlBuilder.toString());
		writer.close();
	}

/*	public void getScenraio(Scenario scenario2) {
		scenario = scenario2;
		
	}*/
	
	
	public void wait(int sec) throws InterruptedException {
		Thread.sleep(sec*1000);
	}
}
