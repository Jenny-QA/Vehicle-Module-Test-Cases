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

public class VehicleManufacturerMasterTestCases {

	private WebDriver driver;
	CommonTestCases testcase;
	
	public VehicleManufacturerMasterTestCases() { }
	
	public VehicleManufacturerMasterTestCases(WebDriver driver) {
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
		testcase.verifyLogin2("Jenny", "Test@111");//, ErrorMessage.loginSuccess);
		//testcase.verifyLogin("Jenny", "Test@111", APIResponse.loginSuccess);
	}	
	
	@Test(priority = 1)
	public void gotoMenu() {
		testcase.goMenu(Util.MASTERS_MENU, "Vehicle Manufacturer Master");
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
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 5)
	public void checkErrorMsg() {
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.checkError("//*[@id=\"manfacturer_type\"]", "", "//*[@id=\"type_err\"]", ErrorMessage.manufacturer_err1);
		//testcase.checkError("//*[@id=\"manfacturer_type\"]", "12$%", "//*[@id=\"type_err\"]", ErrorMessage.manufacturer_err2);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 6)
	public void addData(){	
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.addDoubleData("//*[@id=\"manfacturer_type\"]", "AA", "//*[@id=\"description\"]", "Desc", APIResponse.manufacturerAdd);
		//testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 7)
	public void existData(){	
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.addDoubleData("//*[@id=\"manfacturer_type\"]", "Kia", "//*[@id=\"description\"]", "Desc", APIResponse.manufacturerExists);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 8)
	public void editData(){	
		testcase.editDoubleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"manfacturer_type\"]", "AB", "//*[@id=\"description\"]","desc", APIResponse.manufacturerUpdate);
		//testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 8)
	public void editExistData(){	
		testcase.editDoubleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"manfacturer_type\"]", "Kia", "//*[@id=\"description\"]","desc", APIResponse.manufacturerExists);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 10)
	public void deleteData() {
		testcase.delete("//*[@id=\"0\"]//*[@id=\"delete\"]", APIResponse.manufacturerDelete);
	}
	
	@AfterTest
	public void quit() {
		driver.close();
		driver.quit();
	}
}