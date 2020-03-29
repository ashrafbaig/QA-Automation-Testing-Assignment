package BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.Utilities.Utilities;
import com.qa.Utilities.WebEventListener;

public class BaseClass {
	public static WebDriver driver;
	public static Properties pr;
	public static FileInputStream fi;
	public static XSSFWorkbook wbo;
	public static XSSFSheet wso;
	public static JavascriptExecutor jse;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static WebDriverWait wait;

	public BaseClass() {
		pr = new Properties();
		try {
			fi = new FileInputStream(
					"E:\\Projects\\AutomationPractice\\src\\main\\java\\com\\qa\\Properties\\AutomationPractice.properties");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			pr.load(fi); // To load the Properties file path in Properties Instance
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void initialization() throws IOException {
		// Initializing the browser
		String BrowserName = pr.getProperty("browser").trim();
		if (BrowserName.equalsIgnoreCase("FireFox")) {
			FirefoxProfile newProfile = new FirefoxProfile();
			newProfile.setPreference("dom.webnotifications.enabled", false);
			DesiredCapabilities desCap = DesiredCapabilities.firefox();
			desCap.setCapability(FirefoxDriver.PROFILE, newProfile);
			FirefoxOptions opt = new FirefoxOptions();
			opt.merge(desCap);
			System.setProperty("webdriver.gecko.driver", "E:/FireFox0.26/geckodriver.exe");

			driver = new FirefoxDriver(opt);
			jse = (JavascriptExecutor) driver;
			wait = new WebDriverWait(driver, 30);

		} else if (BrowserName.equalsIgnoreCase("Chrome")) {
			ChromeOptions coptions = new ChromeOptions();
			coptions.addArguments("--disable-notifications");
			coptions.setPageLoadStrategy(PageLoadStrategy.NONE);
			System.setProperty("webdriver.chrome.driver", "E:/ChromeDriver80/chromedriver.exe");

			driver = new ChromeDriver(coptions);
			jse = (JavascriptExecutor) driver;
			wait = new WebDriverWait(driver, 30);
		}
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(pr.getProperty("URL").trim());
		///////////// DEBUG [main] cache:45 - Couldn't find template in cache
		///////////// for///////////////
		System.setProperty("org.freemarker.loggerLibrary", "none");
		driver.manage().timeouts().pageLoadTimeout(Utilities.Page_Load, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Utilities.Implicit_Time, TimeUnit.SECONDS);

	}
}