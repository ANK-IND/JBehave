package JbehaveSelenium.steps;

import static JbehaveSelenium.steps.AppUtilities.prop;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.page.locator.HomePage;
import com.page.locator.LoginPage;

public class MySteps {

	static WebDriver driver;
	
	@BeforeStories
	public void configSetup() {

		String url = prop.getProperty("URL");
		String browser = prop.getProperty("Browser");

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "/Users/nirmalkumar/Documents/Software/geckodriver");
			driver = new FirefoxDriver();
			driver.get(url);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/nirmalkumar/Documents/Software/ChromeDriver");
			driver = new ChromeDriver();
			DesiredCapabilities caps = new DesiredCapabilities();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--disable-save-password-bubble");
			caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver.get(url);
		} else if (browser.equalsIgnoreCase("Safari")) {
			driver = new SafariDriver();
			driver.manage().window().maximize();
			driver.get(url);
			
		} else {
			System.out.println("Pls specify a valid browser in Config file");

		}

	}
	

	@AfterStory
	public void browseCleanUp() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

	@Given("user in Flipkart HomePage and clicks on Login button")
	public void Homepage() {
		String title = driver.getTitle();
		String ccurl = driver.getCurrentUrl();
		System.out.println(title);
		System.out.println(ccurl);

		if (title.contains("Flipkart")) {
			System.out.println("User in Flipkart HomePage");
		} else {
			System.out.println("User not in Flipkart Homepage");
		}

	}

	@When("user enters Email and Password for Login")
	public void userLogin() {

		/*String dd = ("Demo@yopmail.com");
		String number = RandomStringUtils.randomNumeric(2);
		String email = dd.substring(0, 4) + number + dd.substring(4);*/
		String password = ("Shopping-11");
		driver.findElement(HomePage.btnLogin).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement pwd = driver.findElement(LoginPage.txtpwd);
		wait.until(ExpectedConditions.visibilityOf(pwd));
		driver.findElement(LoginPage.txtEmail).sendKeys("9176181813");
		pwd.sendKeys(password);
		driver.findElement(LoginPage.btnCreateAccount).click();
		System.out.println("When Block");
	}

	@Then("verify user login is successful")
	public void loginVerifcation() throws InterruptedException {
		
		Thread.sleep(5000);
		String ss = driver.findElement(HomePage.linkAccount).getText();
		System.out.println(ss);
		if (ss.contains("NIRMALKUMAR")) {
			System.out.println("User Logged in successfully");

		} else
			System.out.println("User Log in Failed");
	}
	
	
	public void logout() throws InterruptedException{
		
		 if (driver.findElement(HomePage.btnLogin).isDisplayed()){
			 System.out.println("User not logged in");	
		}
		 driver.manage().deleteAllCookies();
		 driver.navigate().refresh();
		 Thread.sleep(9000);
		 WebElement myAccount=driver.findElement(HomePage.linkAccount);
		 WebElement logout=driver.findElement(HomePage.linkLogout);
		 Actions nn= new Actions(driver);
		 nn.moveToElement(myAccount);
		 nn.click(logout).build().perform();
		 
		 System.out.println("User logged out succesfully");
		
		
	}
}
