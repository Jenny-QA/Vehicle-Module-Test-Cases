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
//		testcase.checkToast(APIResponse.DataError);
//	}

	@Test(priority = 4)
	public void checkAddButton() {
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		testcase.verifyButton("//*[@id=\"btn_add\"]");
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 5)
	public void checkErrorMsg() {
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		testcase.checkError("//*[@id=\"color_type\"]", "", "//*[@id=\"type_err\"]", ErrorMessage.color_err1);
		testcase.checkError("//*[@id=\"color_type\"]", "12$%", "//*[@id=\"type_err\"]", ErrorMessage.color_err2);
		testcase.checkError("//*[@id=\"color_code\"]", "", "//*[@id=\"code_err\"]", ErrorMessage.color_err3);
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 6)
	public void addData() {
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).sendKeys("A");
		
		driver.findElement(By.xpath("//*[@id=\"color_code\"]")).click();
		try { Thread.sleep(10000);} catch(Exception e) {}
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).clear();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).sendKeys("#ff3eff");
		
		driver.findElement(By.xpath("//*[@id=\"btn_add\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();
		testcase.checkToast(APIResponse.colorAdd);
	}
	
	@Test(priority = 7)
	public void existData() {
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).sendKeys("Red");
		
		driver.findElement(By.xpath("//*[@id=\"color_code\"]")).click();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).clear();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).sendKeys("#ff3eff");
		
		driver.findElement(By.xpath("//*[@id=\"btn_add\"]")).click();
		//driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();
		//testcase.clickButton("//*[@id=\"delete_yes\"]");
		testcase.checkToast(APIResponse.colorNameExists);
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 8)
	public void editData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).sendKeys("AB");
		
		driver.findElement(By.xpath("//*[@id=\"color_code\"]")).click();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).clear();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).sendKeys("#ff3eff");
		
		driver.findElement(By.xpath("//*[@id=\"btn_add\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();
		testcase.checkToast(APIResponse.colorUpdate);	
	}
	
	@Test(priority = 9)
	public void editExistData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"color_type\"]")).sendKeys("AB");
		
		driver.findElement(By.xpath("//*[@id=\"color_code\"]")).click();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).clear();
		driver.findElement(By.xpath("//app-add-edit-vehicle-color/form/div[1]/div[2]/color-picker/div/div[7]/div[1]/input")).sendKeys("#ffff00");
		
		driver.findElement(By.xpath("//*[@id=\"btn_add\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();
		testcase.checkToast(APIResponse.colorCodeExists);
		testcase.clickButton("//*[@id=\"a_cancel\"]");	
	}
	
	@Test(priority = 10)
	public void deleteData() {
		testcase.delete("//*[@id=\"0\"]//*[@id=\"delete\"]", APIResponse.colorDelete);
	}
	
	@AfterTest
	public void quit() {
		if(driver != null)
			driver.quit();
	}
}