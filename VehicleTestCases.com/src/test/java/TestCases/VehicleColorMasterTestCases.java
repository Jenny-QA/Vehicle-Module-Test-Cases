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

public class VehicleColorMasterTestCases {

	private WebDriver driver;
	CommonTestCases testcase;
	
	public VehicleColorMasterTestCases() { }
	
	public VehicleColorMasterTestCases(WebDriver driver) {
		this.driver = driver;
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
	public void openBrowser() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Util.WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Util.WAIT_TIME));
		driver.get(Util.BASE_URL);
		testcase = new CommonTestCases(driver);
	}
	
	@Test(priority = 0)
	public void Login() {		
		testcase.verifyLogin2("Jenny", "Test@111");
		//testcase.verifyLogin("Jenny", "Test@111", APIResponse.loginSuccess);
	}	
	
	@Test(priority = 1)
	public void gotoMenu() {
		testcase.goMenu(Util.MASTERS_MENU, "Vehicle Color Master");
	}
	
	@Test(priority = 2)
	public void verifyTitle() {
		testcase.verifyTitle();
	}
	
//	@Test(priority = 3)
//	public void checkSnackbar(){
//		testcase.checkErrorMessage(APIResponse.DataError);
//	}
	
	@Test(priority = 4)
	public void checkAddButton() {
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
	}
	
	/*@Test(priority = 5)
	public void checkErrorMsg() {
		testcase.checkError("//*[@id=\"color_type\"]", null, "//*[@id=\"type_err\"]", ErrorMessage.color_err1);
		testcase.checkError("//*[@id=\"color_type\"]", "12$%", "//*[@id=\"type_err\"]", ErrorMessage.color_err2);
	}*/
	
	@Test(priority = 6)
	public void addData() {
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).sendKeys("Cloudy");
		driver.findElement(By.xpath("//*[@id=\"color_code\"]")).sendKeys("#ffaeff");
		driver.findElement(By.xpath("//*[@id=\"btn_add\"]")).click();
	}
	
	@AfterTest
	public void quit() {
		if(driver != null)
			driver.quit();
	}
	
}