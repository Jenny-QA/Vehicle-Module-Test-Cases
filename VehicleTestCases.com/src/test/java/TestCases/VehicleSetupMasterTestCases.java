package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VehicleSetupMasterTestCases {

	private WebDriver driver;
	CommonTestCases testcase;

	public VehicleSetupMasterTestCases() { }

	public VehicleSetupMasterTestCases(WebDriver driver) {
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
		testcase.goMenu(Util.MASTERS_MENU, "Vehicle Setup Master");
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
		testcase.openForm("//*[@id=\"btn_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 5)
	public void checkErrorMsg() {
		testcase.openForm("//*[@id=\"btn_addnew\"]", "//*[@id=\"btn_add\"]");
		//testcase.checkSelectError("//*[@id=\"vehicle_type\"]", "", "//*[@id=\"vehicle_err\"]", ErrorMessage.setupM_err1);
		//testcase.checkError("//*[@id=\"subvehicle_type\"]", "", "//*[@id=\"subvehicle_err\"]", ErrorMessage.setupM_err2);
		//testcase.checkError("//*[@id=\"manufacturer_type\"]", "", "//*[@id=\"manufacturer_err\"]", ErrorMessage.setupM_err3);
		//testcase.checkError("//*[@id=\"model\"]", "", "//*[@id=\"model_err\"]", ErrorMessage.setupM_err4);
		//testcase.checkError("//*[@id=\"variant\"]", "@!", "//*[@id=\"variant_err\"]", ErrorMessage.setupM_err2); //not necessary
		//testcase.checkError("//*[@id=\"make_year\"]", "", "//*[@id=\"year_err\"]", ErrorMessage.setupM_err6);
		//testcase.checkError("//*[@id=\"vehicle_name\"]", "", "//*[@id=\"vehicle_err\"]", ErrorMessage.setupM_err1); //read only
		testcase.checkError("//*[@id=\"vehicle_description\"]", "", "//*[@id=\"desc_err\"]", ErrorMessage.setupM_err7);
		testcase.checkError("//*[@id=\"luggage\"]", "", "//*[@id=\"luggage_err\"]", ErrorMessage.setupM_err8);
		testcase.checkError("//*[@id=\"luggage\"]", "20.08", "//*[@id=\"luggage_err\"]", ErrorMessage.setupM_err9);
		testcase.checkError("//*[@id=\"no_of_doors\"]", "", "//*[@id=\"door_err\"]", ErrorMessage.setupM_err10);
		testcase.checkError("//*[@id=\"no_of_doors\"]", "12.05", "//*[@id=\"door_err\"]", ErrorMessage.setupM_err11);
		testcase.checkError("//*[@id=\"capacity\"]", "12.05", "//*[@id=\"capacity_err\"]", ErrorMessage.setupM_err12);
		testcase.checkError("//*[@id=\"fuel_consumption\"]", "130.502", "//*[@id=\"fuel_err\"]", ErrorMessage.setupM_err13);
		testcase.checkError("//*[@id=\"unit_no\"]", "", "//*[@id=\"unit_no_err\"]", ErrorMessage.setupM_err14);
		testcase.checkError("//*[@id=\"unit_no\"]", "12.053", "//*[@id=\"unit_no_err\"]", ErrorMessage.setupM_err15);
		//testcase.checkError("//*[@id=\"unit\"]", "", "//*[@id=\"unit_err\"]", ErrorMessage.setupM_err16);
		testcase.checkError("//*[@id=\"co2_emission\"]", "12.025", "//*[@id=\"co2_err\"]", ErrorMessage.setupM_err17);		
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}

	@Test(priority = 6)
	public void addData() {
		testcase.openForm("//*[@id=\"btn_addnew\"]", "//*[@id=\"btn_add\"]");
		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"vehicle_type\"]")));
		dropSelect.selectByVisibleText("B");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"subvehicle_type\"]")));
		dropSelect.selectByVisibleText("Sudan");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"manufacturer_type\"]")));
		dropSelect.selectByVisibleText("Kia");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"make_year\"]")));
		dropSelect.selectByVisibleText("2022");
		driver.findElement(By.xpath("//*[@id=\"model\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"variant\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"vehicle_description\"]")).sendKeys("desc");
		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		driver.findElement(By.xpath("//*[@id=\"luggage\"]")).sendKeys("6");
		driver.findElement(By.xpath("//*[@id=\"no_of_doors\"]")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"capacity\"]")).sendKeys("8");
		driver.findElement(By.xpath("//*[@id=\"fuel_consumption\"]")).sendKeys("10.02");
		driver.findElement(By.xpath("//*[@id=\"unit_no\"]")).sendKeys("150");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"unit\"]")));
		dropSelect.selectByVisibleText("Per Pound");
		driver.findElement(By.xpath("//*[@id=\"co2_emission\"]")).sendKeys("0.25");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"fuel_policy\"]")).click();
		//driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"fuel_policy\"]")).click();
		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.setupAdd);
	}

	@Test(priority = 7)
	public void existData() {
		testcase.openForm("//*[@id=\"btn_addnew\"]", "//*[@id=\"btn_add\"]");
		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"vehicle_type\"]")));
		dropSelect.selectByVisibleText("B");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"subvehicle_type\"]")));
		dropSelect.selectByVisibleText("Sudan");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"manufacturer_type\"]")));
		dropSelect.selectByVisibleText("Kia");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"make_year\"]")));
		dropSelect.selectByVisibleText("2022");
		driver.findElement(By.xpath("//*[@id=\"model\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"variant\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"vehicle_description\"]")).sendKeys("desc");
		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		driver.findElement(By.xpath("//*[@id=\"luggage\"]")).sendKeys("6");
		driver.findElement(By.xpath("//*[@id=\"no_of_doors\"]")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"capacity\"]")).sendKeys("8");
		driver.findElement(By.xpath("//*[@id=\"fuel_consumption\"]")).sendKeys("10.02");
		driver.findElement(By.xpath("//*[@id=\"unit_no\"]")).sendKeys("150");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"unit\"]")));
		dropSelect.selectByVisibleText("Per Pound");
		driver.findElement(By.xpath("//*[@id=\"co2_emission\"]")).sendKeys("0.25");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"fuel_policy\"]")).click();
		//driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"fuel_policy\"]")).click();
		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.setupExist);
		testcase.clickAdd("//*[@id=\"a_cancel\"]");
	}

	@Test(priority = 8)
	public void editData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"vehicle_type\"]")));
		dropSelect.selectByVisibleText("B");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"subvehicle_type\"]")));
		dropSelect.selectByVisibleText("Sudan");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"manufacturer_type\"]")));
		dropSelect.selectByVisibleText("Kia");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"make_year\"]")));
		dropSelect.selectByVisibleText("2021");
		
		//Clear Data
		driver.findElement(By.xpath("//*[@id=\"model\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"variant\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"vehicle_description\"]")).clear();
		
		driver.findElement(By.xpath("//*[@id=\"model\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"variant\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"vehicle_description\"]")).sendKeys("desc");
		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		//clear Data
		driver.findElement(By.xpath("//*[@id=\"luggage\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"no_of_doors\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"capacity\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"fuel_consumption\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"unit_no\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"co2_emission\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"luggage\"]")).sendKeys("6");
		driver.findElement(By.xpath("//*[@id=\"no_of_doors\"]")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"capacity\"]")).sendKeys("8");
		driver.findElement(By.xpath("//*[@id=\"fuel_consumption\"]")).sendKeys("10.02");
		driver.findElement(By.xpath("//*[@id=\"unit_no\"]")).sendKeys("150");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"unit\"]")));
		dropSelect.selectByVisibleText("Per Pound");
		driver.findElement(By.xpath("//*[@id=\"co2_emission\"]")).sendKeys("0.25");
		//driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"fuel_policy\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"fuel_policy\"]")).click();
		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.setupUpdate);
	}

	@Test(priority = 9)
	public void editExistData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"vehicle_type\"]")));
		dropSelect.selectByVisibleText("Tourist Matador");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"subvehicle_type\"]")));
		dropSelect.selectByVisibleText("Mazda Range");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"manufacturer_type\"]")));
		dropSelect.selectByVisibleText("Kia");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"make_year\"]")));
		dropSelect.selectByVisibleText("2022");
		
		//Clear Data
		driver.findElement(By.xpath("//*[@id=\"model\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"variant\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"vehicle_description\"]")).clear();
		
		driver.findElement(By.xpath("//*[@id=\"model\"]")).sendKeys("Desire 12");
		driver.findElement(By.xpath("//*[@id=\"variant\"]")).sendKeys("AT MS Plus");
		driver.findElement(By.xpath("//*[@id=\"vehicle_description\"]")).sendKeys("desc");
		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		//clear Data
		driver.findElement(By.xpath("//*[@id=\"luggage\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"no_of_doors\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"capacity\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"fuel_consumption\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"unit_no\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"co2_emission\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"luggage\"]")).sendKeys("6");
		driver.findElement(By.xpath("//*[@id=\"no_of_doors\"]")).sendKeys("5");
		driver.findElement(By.xpath("//*[@id=\"capacity\"]")).sendKeys("8");
		driver.findElement(By.xpath("//*[@id=\"fuel_consumption\"]")).sendKeys("10.02");
		driver.findElement(By.xpath("//*[@id=\"unit_no\"]")).sendKeys("150");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"unit\"]")));
		dropSelect.selectByVisibleText("Per Pound");
		driver.findElement(By.xpath("//*[@id=\"co2_emission\"]")).sendKeys("0.25");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"fuel_policy\"]")).click();
		//driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"fuel_policy\"]")).click();
		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.setupExist);
		testcase.clickAdd("//*[@id=\"a_cancel\"]");
	}

	@Test(priority = 10)
	public void deleteData() {
		testcase.delete("//*[@id=\"0\"]//*[@id=\"delete\"]", APIResponse.setupDelete);
	}

	@AfterTest
	public void quit() {
		if(driver != null)
			driver.quit();
	}

}
