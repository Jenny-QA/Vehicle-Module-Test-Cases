package TestCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PromotionTestCases {

	private WebDriver driver;
	CommonTestCases testcase;
	
	public PromotionTestCases() {
		// TODO Auto-generated constructor stub
	}
	
	public PromotionTestCases(WebDriver driver) {
		this.driver = driver;
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
		/*WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();*/
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
	public void Login() {		
		testcase.verifyLogin2("Jenny", "Test@111");//, ErrorMessage.loginSuccess);
	}	
	
	@Test(priority = 1)
	public void gotoMenu() {
		testcase.goMenu(Util.OPERATOR_MENU, "Promotion");
		//testcase.goMenu(By.xpath("//*[contains(text(),'Operators'"), By.xpath("//*[contains(text(),'Promotion'"));
	}
	
	@Test(priority = 2)
	public void verifyTitle() {
		testcase.verifyTitle();
	}
	
	@Test(priority = 3)
	public void checkSnackbar(){
		testcase.checkErrorMessage(APIResponse.DataError);
	}
	
	@Test(priority = 4)
	public void checkAddButton() {
		testcase.openForm("//*[@id=\"btn_addnew\"]", "//*[@id=\"btn_action\"]");
		//testcase.openForm("//*[@id='add-promotion']", "//button[contains(@type,'submit')]");
	}
	
	@AfterTest
	public void quit() {
		if(driver != null)
			driver.quit();
	}
	
}
