package TestCases;

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
	TestResults result;
	int data;
	String nameofCurrMethod;
	String[] value;
	public LoginTestCases() {	}

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
		//options.addArguments("--headless");
		driver = new ChromeDriver(options);
	}

	@BeforeTest
	public void openBrowser()  {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.WAIT_TIME));
		driver.get(Util.BASE_URL);
		testcase = new CommonTestCases(driver);
		result = new TestResults();
		value = new String[5];
	}

	@Test(priority = 0)
	public void verifyTitle(){
		value[0] = testcase.verifyTitle();
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 1)
	public void checkButton() {
		value[0] = testcase.verifyButton("//*[@id=\"btn_login\"]");
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);		
	}		

	@Test(priority = 2)
	public void userRequire()  {
		value[0] = testcase.verifyErrorMSg("//*[@id=\"usernm\"]", "//*[@id=\"usernm_err\"]", "",ErrorMessage.userRequire);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}		

	@Test(priority = 3)
	public void passwordRequire() {
		value[0] = testcase.verifyErrorMSg("//*[@id=\"pwd\"]", "//*[@id=\"pwd_err\"]", "",ErrorMessage.passwordRequire);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 4)
	public void passwordLenght() {
		value[0] = testcase.verifyErrorMSg("//*[@id=\"pwd\"]", "//*[@id=\"pwd_err\"]", "a",ErrorMessage.passwordLength);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 5)
	public void Login0() {
		driver.navigate().refresh();
		value[0] = testcase.verifyLogin("Jainisha", "Test@111", APIResponse.userError);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 6)
	public void Login1(){
		driver.navigate().refresh();
		value[0] = testcase.verifyLogin("Jenny", "Test@1111", APIResponse.passwordError);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 7)
	public void Login2(){
		driver.navigate().refresh();
		//value = testcase.verifyLogin2("Jenny", "Test@111");
		value[0] = testcase.verifyLogin("Jenny", "Test@111", APIResponse.loginSuccess);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@AfterTest
	public void quit() throws Exception {
		result.closeFile();
		if(driver!=null) {
			driver.quit();
		}
	}
}