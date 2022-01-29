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

public class VehicleMasterTestCases {

	private WebDriver driver;
	CommonTestCases testcase;
	
	public VehicleMasterTestCases() { }
	
	public VehicleMasterTestCases(WebDriver driver) {
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
		testcase.goMenu(Util.MASTERS_MENU, "Vehicle Master");
	}
	
	@Test(priority = 2)
	public void verifyTitle() {
		testcase.verifyTitle();
	}
	
	/*@Test(priority = 3)
	public void checkSnackbar(){
		testcase.checkErrorMessage(APIResponse.DataError);
	}*/
	
	@Test(priority = 4)
	public void checkAddButton() {
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 5)
	public void checkErrorMsg() {
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.checkError("//*[@id=\"vehicle_type\"]", "", "//*[@id=\"type_err\"]", ErrorMessage.vehicleMaster_err1);
		//testcase.checkError("//*[@id=\"vehicle_type\"]", "12$%", "//*[@id=\"type_err\"]", ErrorMessage.vehicleMaster_err2);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}

	@Test(priority = 6)
	public void addData(){	
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.addSingleData("//*[@id=\"vehicle_type\"]", "AA", APIResponse.vehicleAdd);
		//testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 7)
	public void existData(){	
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.addSingleData("//*[@id=\"vehicle_type\"]", "Car", APIResponse.vehicleExists);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 8)
	public void editData(){	
		testcase.editSingleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"vehicle_type\"]", "AB", APIResponse.vehicleUpdate);
		//testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 9)
	public void editExistData(){	
		testcase.editSingleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"vehicle_type\"]", "Car", APIResponse.vehicleExists);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 10)
	public void deleteData() {
		testcase.delete("//*[@id=\"0\"]//*[@id=\"delete\"]", APIResponse.vehicleDelete);
	}
	
	@AfterTest
	public void quit() {
		if(driver!=null)
			driver.quit();
	}	
}