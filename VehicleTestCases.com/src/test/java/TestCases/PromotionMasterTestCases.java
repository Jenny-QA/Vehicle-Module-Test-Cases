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

public class PromotionMasterTestCases {

	private WebDriver driver;
	CommonTestCases testcase;
	
	public PromotionMasterTestCases() {	}
	
	public PromotionMasterTestCases(WebDriver driver) {
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
	}	
	
	@Test(priority = 1)
	public void gotoMenu() {
		testcase.goMenu(Util.MASTERS_MENU, "Promotion Master");
	}
	
	@Test(priority = 2)
	public void verifyTitle() {
		testcase.verifyTitle();
	}
	
	/*@Test(priority = 3)
	public void checkSnackbar(){
		testcase.checkToast(APIResponse.DataError);
	}*/
	
	@Test(priority = 4)
	public void checkAddButton() {
		testcase.clickButton("//*[@id=\"a_addnew\"]"); 
		testcase.verifyButton("//*[@id=\"btn_add\"]");
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 5)
	public void checkErrorMsg() {
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		testcase.checkError("//*[@id=\"promotion_type\"]", "", "//*[@id=\"type_err\"]", ErrorMessage.promotionM_err1);
		testcase.checkError("//*[@id=\"promotion_type\"]", "12$%", "//*[@id=\"type_err\"]", ErrorMessage.promotionM_err2);
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 6)
	public void addData(){	
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		testcase.addDoubleData("//*[@id=\"promotion_type\"]", "AA", "//*[@id=\"description\"]", "Desc", APIResponse.promotionAdd);
	}
	
	@Test(priority = 7)
	public void existData(){	
		testcase.clickButton("//*[@id=\"a_addnew\"]");
		testcase.addDoubleData("//*[@id=\"promotion_type\"]", "Promo", "//*[@id=\"description\"]", "Desc", APIResponse.promotionExists);
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 8)
	public void editData(){	
		testcase.editDoubleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"promotion_type\"]", "AB", "//*[@id=\"description\"]", "Desc", APIResponse.promotionUpdate);
	}
	
	@Test(priority = 9)
	public void editExistData(){	
		testcase.editDoubleData("//*[@id=\"0\"]//*[@id=\"edit\"]", "//*[@id=\"promotion_type\"]", "Promo", "//*[@id=\"description\"]", "Desc", APIResponse.promotionExists);
		testcase.clickButton("//*[@id=\"a_cancel\"]");
	}
	
	@Test(priority = 10)
	public void deleteData() {
		testcase.delete("//*[@id=\"0\"]//*[@id=\"delete\"]", APIResponse.promotionDelete);
	}
	
	@AfterTest
	public void quit() {
		if(driver != null)
			driver.quit();
	}
}