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
	TestResults result;
	int data;
	String nameofCurrMethod;
	String[] value;	

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
		result = new TestResults();
	}

	@Test(priority = 0)
	public void Login() {		
		value = new String[1];
		//testcase.verifyLogin2("Jenny", "Test@111");
		value[0] = testcase.verifyLogin("Jenny", "Test@111", APIResponse.loginSuccess);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}	

	@Test(priority = 1)
	public void gotoMenu() {
		value = new String[1];
		value[0] = testcase.goMenu(Util.MASTERS_MENU, "Vehicle Manufacturer Master");
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 2)
	public void verifyTitle() {
		value = new String[1];
		value[0] = testcase.verifyTitle();
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	/*@Test(priority = 3)
		public void checkSnackbar(){
	      value = new String[1];
			value[0] = testcase.checkToast(APIResponse.DataError);
			nameofCurrMethod = new Throwable()
			.getStackTrace()[0]
					.getMethodName();
	        result.writeTestResult(nameofCurrMethod, value);
	}*/

	@Test(priority = 4)
	public void checkAddButton() {
		value = new String[3];
		value[0] = testcase.clickButton("//*[@id=\"a_addnew\"]");
		value[1] = testcase.verifyButton("//*[@id=\"btn_add\"]");
		value[2] = testcase.clickButton("//*[@id=\"a_cancel\"]");
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 5)
	public void checkErrorMsg() {
		value = new String[4];
		value[0] = testcase.clickButton("//*[@id=\"a_addnew\"]");
		value[1] = testcase.checkError("//*[@id=\"manfacturer_type\"]", "", "//*[@id=\"type_err\"]", ErrorMessage.manufacturer_err1);
		value[2] = testcase.checkError("//*[@id=\"manfacturer_type\"]", "12$%", "//*[@id=\"type_err\"]", ErrorMessage.manufacturer_err2);
		value[3] = testcase.clickButton("//*[@id=\"a_cancel\"]");
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 6)
	public void addData(){	
		value = new String[2];
		value[0] = testcase.clickButton("//*[@id=\"a_addnew\"]");
		value[1] = testcase.addDoubleData("//*[@id=\"manfacturer_type\"]", "AA", "//*[@id=\"description\"]", "Desc", APIResponse.manufacturerAdd);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 7)
	public void existData(){
		value = new String[3];
		value[0] = testcase.clickButton("//*[@id=\"a_addnew\"]");
		value[1] = testcase.addDoubleData("//*[@id=\"manfacturer_type\"]", "Kia", "//*[@id=\"description\"]", "Desc", APIResponse.manufacturerExists);
		value[2] = testcase.clickButton("//*[@id=\"a_cancel\"]");
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 8)
	public void editData(){	
		value = new String[1];
		value[0] = testcase.editDoubleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"manfacturer_type\"]", "AB", "//*[@id=\"description\"]","desc", APIResponse.manufacturerUpdate);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 8)
	public void editExistData(){
		value = new String[2];
		value[0] = testcase.editDoubleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"manfacturer_type\"]", "Kia", "//*[@id=\"description\"]","desc", APIResponse.manufacturerExists);
		value[1] = testcase.clickButton("//*[@id=\"a_cancel\"]");
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@Test(priority = 10)
	public void deleteData() {
		value = new String[1];
		value[0] = testcase.delete("//*[@id=\"0\"]//*[@id=\"delete\"]", APIResponse.manufacturerDelete);
		nameofCurrMethod = new Throwable()
				.getStackTrace()[0]
						.getMethodName();
		result.writeTestResult(nameofCurrMethod, value);
	}

	@AfterTest
	public void quit() {
		driver.close();
		driver.quit();
	}
}