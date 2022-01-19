package TestCases;

import static org.testng.Assert.assertNotEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTestCases {

	private WebDriver driver;
	CommonTestCases testcase;
    
	public LoginTestCases() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginTestCases(WebDriver driver){
		this.driver = driver;
	}
	
	public void clearElement(String xpath) {
		driver.findElement(By.xpath(xpath)).clear();		
	}
		
	@BeforeSuite
	public void initDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}
	
	@BeforeTest
	public void openBrowser() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.WAIT_TIME));
		driver.get(Util.BASE_URL);
		testcase = new CommonTestCases(driver);
	}
	
	@Test(priority = 0)
	public void verifyTitle() {
		testcase.verifyTitle();
	}
	
	@Test(priority = 1)
	public void checkButton() {
		testcase.verifyButton("white-bbtn");
	}
	
	@Test(priority = 2)
	public void userRequire() {
		//testcase.verifyElement("//input[@type='text']", ErrorMessage.userRequire);
		testcase.verifyErrorMSg("//*[@id=\"usernm\"]", "//*[@id=\"usernm_err\"]", null,ErrorMessage.userRequire);
	}
	
	@Test(priority = 3)
	public void passwordRequire() {
		//testcase.verifyElement("//input[@type='password']", ErrorMessage.passwordRequire);
		testcase.verifyErrorMSg("//*[@id=\"pwd\"]", "//*[@id=\"pwd_err\"]", null,ErrorMessage.passwordRequire);
	}
	
	@Test(priority = 4)
	public void passwordLenght() {
		//testcase.verifyLength("//input[@type='password']", ErrorMessage.passwordLength);
		testcase.verifyErrorMSg("//*[@id=\"pwd\"]", "//*[@id=\"pwd_err\"]", "a",ErrorMessage.passwordLength);
	}
	
	@Test(priority = 5)
	public void Login0() {
		driver.navigate().refresh();
		testcase.verifyLogin("Jainisha", "Test@111", APIResponse.userError);
	}
	
	@Test(priority = 6)
	public void Login1() {
		driver.navigate().refresh();
		testcase.verifyLogin("Jenny", "Test@1111", APIResponse.passwordError);
	}
	
	@Test(priority = 7)
	public void Login2() {
		driver.navigate().refresh();
		testcase.verifyLogin2("Jenny", "Test@111");
		//testcase.verifyLogin("Jenny", "Test@111", APIResponse.loginSuccess);
		
	}
	
	@AfterTest
	public void quit() {
		if(driver!=null) {
			driver.quit();
		}
	}
}