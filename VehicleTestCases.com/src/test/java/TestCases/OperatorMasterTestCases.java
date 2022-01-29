package TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OperatorMasterTestCases {

	private WebDriver driver;
	CommonTestCases testcase;

	public OperatorMasterTestCases() { }

	public OperatorMasterTestCases(WebDriver driver) {
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
		testcase.goMenu(Util.MASTERS_MENU, "Operator Master");
	}

	@Test(priority = 2)
	public void verifyTitle() {
		testcase.verifyTitle();
	}

	/*@Test(priority = 3)
	public void checkSnackbar(){
		testcase.checkErrorMessage(APIResponse.DataError);
	}*/

	/*@Test(priority = 4)
	public void checkAddButton() {
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}*/

	/*@Test(priority = 5)
	public void checkFirstErrorMsg() {
		testcase.openForm("//*[@id=\"a_addnew\"]", "//*[@id=\"btn_add\"]");

		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);");

		testcase.checkError("//*[@id=\"address_line1\"]", "", "//*[@id=\"add_err\"]", ErrorMessage.operatorM_err3);
		testcase.checkError("//*[@id=\"transporter_name\"]", "", "//*[@id=\"transporter_err\"]", ErrorMessage.operatorM_err1);
		
		//testcase.checkError("//*[@id=\"address_line2\"]", "", "//*[@id=\"add_err\"]", ErrorMessage.operatorM_err3);

		//pagedown
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		testcase.checkError("//*[@id=\"0\"]//*[@id=\"address_line1\"]", "", "//*[@id=\"0\"]//*[@id=\"add_err\"]", ErrorMessage.operatorM_err3);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}*/

	@Test(priority = 6)
	public void addFirstData() {
		testcase.addNewForm("//*[@id=\"a_addnew\"]");

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("return document.readyState").equals("complete");

		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"country\"]")));
		dropSelect.selectByVisibleText("India");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"state\"]")));
		dropSelect.selectByVisibleText("Gujarat");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"city\"]")));
		dropSelect.selectByVisibleText("Rajkot");
		driver.findElement(By.xpath("//*[@id=\"transporter_name\"]")).sendKeys("AA" + Keys.TAB);
		driver.findElement(By.xpath("//*[@id=\"address_line1\"]")).sendKeys("AA" + Keys.TAB);
		driver.findElement(By.xpath("//*[@id=\"address_line2\"]")).sendKeys("A");

		//pagedown
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"country\"]")));
		dropSelect.selectByVisibleText("India");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"state\"]")));
		dropSelect.selectByVisibleText("Gujarat");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"city\"]")));
		dropSelect.selectByVisibleText("Rajkot");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"address_line1\"]")).sendKeys("A");
		//driver.findElement(By.xpath("//*[@id=\"btn_addlocation\"]")).click();

		//pagedown
		/*js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"address_line1\"]")).sendKeys("A");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"country\"]")));
		dropSelect.selectByVisibleText("United States");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"state\"]")));
		dropSelect.selectByVisibleText("California");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"city\"]")));
		dropSelect.selectByVisibleText("California City");
		*/
		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorAdd1);
	}

	@Test(priority = 7)
	public void checkSecondErrorMsg() {

		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);");

		testcase.checkError("//*[@id=\"fname\"]", "", "//*[@id=\"fname_err\"]", ErrorMessage.operatorM_err5);
		testcase.checkError("//*[@id=\"lname\"]", "", "//*[@id=\"lname_err\"]", ErrorMessage.operatorM_err6);
		testcase.checkError("//*[@id=\"email\"]", "", "//*[@id=\"email_err\"]", ErrorMessage.operatorM_err7);
		testcase.checkError("//*[@id=\"email\"]", "!@", "//*[@id=\"email_err\"]", ErrorMessage.operatorM_err8);
		testcase.checkError("//*[@id=\"mobileno\"]", "", "//*[@id=\"mobileno_err\"]", ErrorMessage.operatorM_err9);
		testcase.checkError("//*[@id=\"mobileno\"]", "12", "//*[@id=\"mobileno_err\"]", ErrorMessage.operatorM_err10);
		//testcase.checkError("//*[@id=\"skype\"]", "!@", "//*[@id=\"skype_err\"]", ErrorMessage.operatorM_err11);
		testcase.checkError("//*[@id=\"whatsapp\"]", "12", "//*[@id=\"whatsapp_err\"]", ErrorMessage.operatorM_err12);
		testcase.checkError("//*[@id=\"username\"]", "", "//*[@id=\"username_err\"]", ErrorMessage.userRequire);
		testcase.checkError("//*[@id=\"password\"]", "", "//*[@id=\"password_err\"]", ErrorMessage.passwordRequire);
		testcase.checkError("//*[@id=\"password\"]", "!@#$", "//*[@id=\"password_err\"]", ErrorMessage.passwordLength);

		//pagedown
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		driver.findElement(By.xpath("//*[@id=\"from_hour\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"to_hour\"]")).click();

		//testcase.checkError("//*[@id=\"from_hour\"]", "", "//*[@id=\"from_hour_err\"]", ErrorMessage.operatorM_err13);
		//testcase.checkError("//*[@id=\"to_hour\"]", "", "//*[@id=\"to_hour_err\"]", ErrorMessage.operatorM_err14);
		//testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}

	@Test(priority = 8)
	public void addSecondData() {

		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);");

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"lname\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("a@gamil.com");
		driver.findElement(By.xpath("//*[@id=\"mobileno\"]")).sendKeys("1265478542");
		//driver.findElement(By.xpath("//*[@id=\"skype\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"whatsapp\"]")).sendKeys("1265784826");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Test@111");

		//pagedown
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		driver.findElement(By.xpath("//*[@id=\"from_hour\"]")).sendKeys("1000AM");
		driver.findElement(By.xpath("//*[@id=\"to_hour\"]")).sendKeys("1100PM");
		driver.findElement(By.xpath("//*[@id=\"5\"]")).click();
		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorAdd2);
	}
	
	/*@Test(priority = 9)
	public void addExistData() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);");
		
		testcase.addNewForm("//*[@id=\"a_addnew\"]");

		
		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"country\"]")));
		dropSelect.selectByVisibleText("India");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"state\"]")));
		dropSelect.selectByVisibleText("Gujarat");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"city\"]")));
		dropSelect.selectByVisibleText("Rajkot");
		driver.findElement(By.xpath("//*[@id=\"transporter_name\"]")).sendKeys("AA" + Keys.TAB);
		driver.findElement(By.xpath("//*[@id=\"address_line1\"]")).sendKeys("AA" + Keys.TAB);
		driver.findElement(By.xpath("//*[@id=\"address_line2\"]")).sendKeys("A");

		//pagedown
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"country\"]")));
		dropSelect.selectByVisibleText("India");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"state\"]")));
		dropSelect.selectByVisibleText("Gujarat");
		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"city\"]")));
		dropSelect.selectByVisibleText("Rajkot");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"address_line1\"]")).sendKeys("A");
		
		driver.findElement(By.xpath("//*[@id=\"btn_addlocation\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"btn_deletelocation\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();

		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorExist1);

		//pagedown
//		js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollTo(0, 0);");
//
//		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("A");
//		driver.findElement(By.xpath("//*[@id=\"lname\"]")).sendKeys("A");
//		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("a@gamil.com");
//		driver.findElement(By.xpath("//*[@id=\"mobileno\"]")).sendKeys("12612578542");
//		//driver.findElement(By.xpath("//*[@id=\"skype\"]")).clear();
//		driver.findElement(By.xpath("//*[@id=\"whatsapp\"]")).sendKeys("1265784826");
//		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("A");
//		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Test@111");
//
//		//pagedown
//		js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");
//
//		driver.findElement(By.xpath("//*[@id=\"from_hour\"]")).sendKeys("1000AM");
//		driver.findElement(By.xpath("//*[@id=\"to_hour\"]")).sendKeys("1100PM");
//		driver.findElement(By.xpath("//*[@id=\"5\"]")).click();
//		testcase.clickAdd("//*[@id=\"btn_add\"]");
//		testcase.checkErrorMessage(APIResponse.operatorExist3);
		
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}*/

	@Test(priority = 10)
	public void editFirstData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("return document.readyState").equals("complete");

				Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"country\"]")));
		//		dropSelect.selectByVisibleText("India");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"state\"]")));
		//		dropSelect.selectByVisibleText("Gujarat");
				dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"city\"]")));
				dropSelect.selectByVisibleText("Ahmedabad");

		//clear field
		driver.findElement(By.xpath("//*[@id=\"transporter_name\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"address_line1\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"address_line2\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"transporter_name\"]")).sendKeys("Ankita");
		driver.findElement(By.xpath("//*[@id=\"address_line1\"]")).sendKeys("360008");
		driver.findElement(By.xpath("//*[@id=\"address_line2\"]")).sendKeys("Rajkot");

		//pagedown
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"address_line1\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"address_line1\"]")).sendKeys("Ahmdabad");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"country\"]")));
		//		dropSelect.selectByVisibleText("India");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"state\"]")));
		//		dropSelect.selectByVisibleText("Gujarat");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"city\"]")));
		//		dropSelect.selectByVisibleText("Rajkot");

		//		driver.findElement(By.xpath("//*[@id=\"btn_addlocation\"]")).click();
		//		driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"btn_deletelocation\"]")).click();
		//		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();

		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorUpdate1);

	}

	@Test(priority = 11)
	public void editSecondData() {
		//pagedown
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);");

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"lname\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"email\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"mobileno\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"whatsapp\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("Ankita");
		driver.findElement(By.xpath("//*[@id=\"lname\"]")).sendKeys("Abhyu");
		//driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("a@gamil.com");
		//driver.findElement(By.xpath("//*[@id=\"mobileno\"]")).sendKeys("12612578542");
		//driver.findElement(By.xpath("//*[@id=\"skype\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"whatsapp\"]")).sendKeys("1265784826");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("A");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Test@111");

		//pagedown
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		//driver.findElement(By.xpath("//*[@id=\"from_hour\"]")).sendKeys("1000AM");
		//driver.findElement(By.xpath("//*[@id=\"to_hour\"]")).sendKeys("1100PM");
		driver.findElement(By.xpath("//*[@id=\"5\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"1\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"3\"]")).click();

		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorUpdate2);

	}

	@Test(priority = 12)
	public void editExistData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("return document.readyState").equals("complete");

		//		Select dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"country\"]")));
		//		dropSelect.selectByVisibleText("India");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"state\"]")));
		//		dropSelect.selectByVisibleText("Gujarat");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"city\"]")));
		//		dropSelect.selectByVisibleText("Rajkot");

		//clear field
		driver.findElement(By.xpath("//*[@id=\"transporter_name\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"address_line1\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"address_line2\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"transporter_name\"]")).sendKeys("Ankita" + Keys.TAB);
		driver.findElement(By.xpath("//*[@id=\"address_line1\"]")).sendKeys("360008" + Keys.TAB);
		driver.findElement(By.xpath("//*[@id=\"address_line2\"]")).sendKeys("Rajkot");

		//pagedown
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"address_line1\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"address_line1\"]")).sendKeys("Ahmdabad");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"country\"]")));
		//		dropSelect.selectByVisibleText("India");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"state\"]")));
		//		dropSelect.selectByVisibleText("Gujarat");
		//		dropSelect = new Select(driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"city\"]")));
		//		dropSelect.selectByVisibleText("Rajkot");

		//		driver.findElement(By.xpath("//*[@id=\"btn_addlocation\"]")).click();
		//		driver.findElement(By.xpath("//*[@id=\"1\"]//*[@id=\"btn_deletelocation\"]")).click();
		//		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();

		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorUpdate1);

		//pagedown
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, 0);");

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"lname\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"email\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"mobileno\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"whatsapp\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"username\"]")).clear();

		driver.findElement(By.xpath("//*[@id=\"fname\"]")).sendKeys("Ankita");
		driver.findElement(By.xpath("//*[@id=\"lname\"]")).sendKeys("Abhyu");
		//driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("a@gamil.com");
		//driver.findElement(By.xpath("//*[@id=\"mobileno\"]")).sendKeys("12612578542");
		//driver.findElement(By.xpath("//*[@id=\"skype\"]")).clear();
		//driver.findElement(By.xpath("//*[@id=\"whatsapp\"]")).sendKeys("1265784826");
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Jenny");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Test@111");

		//pagedown
		js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, document.body.scrollHeight);");

		//driver.findElement(By.xpath("//*[@id=\"from_hour\"]")).sendKeys("1000AM");
		//driver.findElement(By.xpath("//*[@id=\"to_hour\"]")).sendKeys("1100PM");
		driver.findElement(By.xpath("//*[@id=\"5\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"1\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"3\"]")).click();

		testcase.clickAdd("//*[@id=\"btn_add\"]");
		testcase.checkErrorMessage(APIResponse.operatorExist3);
		testcase.clickCancel("//*[@id=\"a_cancel\"]");
	}

	@Test(priority = 13)
	public void deleteData() {
		driver.findElement(By.xpath("//*[@id=\"0\"]//*[@id=\"edit\"]")).click();
	}

	@AfterTest
	public void quit() {
		if(driver != null)
			driver.quit();
	}

}
