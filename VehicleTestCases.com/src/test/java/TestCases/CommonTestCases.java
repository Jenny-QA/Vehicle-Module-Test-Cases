package TestCases;

import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertFalse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonTestCases {

	public WebDriver driver;
	private WebElement element;
	public boolean enable;
	public String errorMsg;
	public WebDriverWait wait;
	public JavascriptExecutor js;

	public CommonTestCases() { }

	public CommonTestCases(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyTitle() {
		assertEquals(driver.getTitle(), Util.title); 
	}

	public void verifyButton(String button) {
		enable = driver.findElement(By.className(button)).isEnabled();
		assertEquals(enable, false);
	}

	public void verifyErrorMSg(String inputElement, String xpathError,String errMsg ,String msg) {
		element = driver.findElement(By.xpath(inputElement));
		
		if(errMsg != null) 
			element.sendKeys(errMsg + Keys.TAB);
		else
			element.sendKeys(Keys.TAB);
		
		//errorMsg = driver.findElement(RelativeLocator.with(By.tagName("span")).below(element)).getText();
		errorMsg = driver.findElement(By.xpath(xpathError)).getText();
		assertEquals(errorMsg , msg);
	}

	public void verifyLogin(String username, String password ,String msg) {
		driver.findElement(By.xpath("//*[@id=\"usernm\"]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys(password + Keys.ENTER);
		element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		errorMsg = element.getText();
		element.click();
		assertEquals(errorMsg, msg);
	}

	public void verifyLogin2(String username, String password) {	
		driver.findElement(By.xpath("//*[@id=\"usernm\"]")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys(password + Keys.ENTER);
		//assertNotEquals(driver.getCurrentUrl(), Util.BASE_URL);
	}	

	public void goMenu(String mainMenu, String subMenu) {
		try { Thread.sleep(5000);}catch(Exception e) {}
		//new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id= \"" + mainMenu  +"\"]")));
		//driver.findElement(By.xpath("//*[@id= \"" + mainMenu + "\"]")).click();
		js = (JavascriptExecutor)driver;
		element = driver.findElement(By.xpath("//*[@id= \"" + subMenu  +"\"]"));//.click();
		js.executeScript("arguments[0].click();", element);
	}
	
	public void checkErrorMessage(String msg) {
		element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		errorMsg = element.getText();
		element.click();
		assertEquals(errorMsg, msg);
	}

	public void addNewForm(String xpath1) {
		driver.findElement(By.xpath(xpath1)).click();
	}
	
	public void openForm(String xpath1, String xpath2) {
		driver.findElement(By.xpath(xpath1)).click();
		checkAddEnable(xpath2);
	}

	public void checkAddEnable(String xpath) {
		js = (JavascriptExecutor)driver;
		element = driver.findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		enable = element.isEnabled();
		assertEquals(false, enable);
	}

	public void clickAdd(String id) {
		driver.findElement(By.xpath(id)).click();
	}
	
	public void clickCancel(String id) {
		driver.findElement(By.xpath(id)).click();
	}
	
	public void checkError(String element_id, String input, String error_id, String err) {
		element = driver.findElement(By.xpath(element_id));
		if(input == "")
			element.sendKeys(Keys.TAB);
		else
			element.sendKeys(input + Keys.TAB);
		//System.out.println(element);
		errorMsg = driver.findElement(By.xpath(error_id)).getText();
		element.clear();
		assertEquals(err, errorMsg);
	}
	
	public void checkSelectError(String element_id, String input, String error_id, String err) {
		Select dropdown = new Select(driver.findElement(By.xpath(element_id)));
		dropdown.selectByIndex(0);
		System.out.println("working");
		errorMsg = driver.findElement(By.xpath(error_id)).getText();
		element.clear();
		assertEquals(err, errorMsg);
	}
	
	public void addSingleData(String type,String msg, String err) {
		driver.findElement(By.xpath(type)).sendKeys(msg);
		clickAdd("//*[@id=\"btn_add\"]");
		checkErrorMessage(err);
	}
	
	public void addDoubleData(String type, String msg, String des, String msg1, String err) {
		driver.findElement(By.xpath(type)).sendKeys(msg);
		driver.findElement(By.xpath(des)).sendKeys(msg1);
		clickAdd("//*[@id=\"btn_add\"]");
		checkErrorMessage(err);
	}
	
	public void addTripleData(String dropDrown, String value, String type, String msg, String des, String msg1, String err) {
		Select dropdown = new Select(driver.findElement(By.xpath(dropDrown)));
		dropdown.selectByVisibleText(value);
		driver.findElement(By.xpath(type)).sendKeys(msg);
		driver.findElement(By.xpath(des)).sendKeys(msg1);
		clickAdd("//*[@id=\"btn_add\"]");
		checkErrorMessage(err);
	}
	
	public void editSingleData(String rowId, String type,String msg, String err) {
		driver.findElement(By.xpath(rowId)).click();
		driver.findElement(By.xpath(type)).clear();
		addSingleData(type, msg, err);
		//driver.findElement(By.xpath(type)).sendKeys(msg);
		//clickAdd("//*[@id=\"btn_add\"]");
		//checkErrorMessage(err);
	}
	
	public void editDoubleData(String rowId, String type, String msg, String des, String msg1, String err) {
		driver.findElement(By.xpath(rowId)).click();
		driver.findElement(By.xpath(type)).clear();
		driver.findElement(By.xpath(des)).clear();
		driver.findElement(By.xpath(type)).sendKeys(msg);
		driver.findElement(By.xpath(des)).sendKeys(msg1);
		clickAdd("//*[@id=\"btn_add\"]");
		checkErrorMessage(err);
	}
	
	public void editTripleData(String rowId, String dropDrown, String value, String type, String msg, String des, String msg1, String err) {
		driver.findElement(By.xpath(rowId)).click();
		Select dropdown = new Select(driver.findElement(By.xpath(dropDrown)));
		dropdown.selectByVisibleText(value);
		driver.findElement(By.xpath(type)).clear();
		driver.findElement(By.xpath(des)).clear();		
		driver.findElement(By.xpath(type)).sendKeys(msg);
		driver.findElement(By.xpath(des)).sendKeys(msg1);
		clickAdd("//*[@id=\"btn_add\"]");
		checkErrorMessage(err);
	}
	
	public void delete(String id, String err) {
		driver.findElement(By.xpath(id)).click();
		//driver.findElement(By.xpath("//*[@id=\"mat-dialog-1\"]/app-confirm-common-dialog/mat-dialog-actions/button")).click();
		try {
			Thread.sleep(10000);
		}catch(Exception e) {	
		}
		driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();
		//*[@id="mat-dialog-1"]/app-confirm-common-dialog/mat-dialog-actions/button
		checkErrorMessage(err);
	}
}