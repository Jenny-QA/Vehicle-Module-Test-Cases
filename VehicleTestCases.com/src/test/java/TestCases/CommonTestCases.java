package TestCases;

import static org.testng.Assert.assertEquals;

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

	public String verifyTitle() {
		try {
			assertEquals(driver.getTitle(), Util.title);
		}catch (AssertionError e) {
			return e.toString();
		}
		return "PASS";
	}

	public String verifyButton(String button) {
		try {
			js = (JavascriptExecutor)driver;
			element = driver.findElement(By.xpath(button));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			enable = element.isEnabled();
			assertEquals(enable, false);
		}
		catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String verifyErrorMSg(String inputElement, String xpathError,String errMsg ,String msg) {
		try {
			element = driver.findElement(By.xpath(inputElement)); 
			element.sendKeys(errMsg);
			((JavascriptExecutor)driver).executeScript(    
		            "arguments[0].dispatchEvent(new Event('blur'))",element);
			errorMsg = driver.findElement(By.xpath(xpathError)).getText();
			assertEquals(errorMsg , msg);
		}
		catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String verifyLogin(String username, String password ,String msg) {
		try {
			driver.findElement(By.xpath("//*[@id=\"usernm\"]")).sendKeys(username);
			driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys(password + Keys.ENTER);
			element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
			errorMsg = element.getText();
			element.click();
			assertEquals(errorMsg, msg);
		}
		catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String verifyLogin2(String username, String password) {	
		try {
			driver.findElement(By.xpath("//*[@id=\"usernm\"]")).sendKeys(username);
			driver.findElement(By.xpath("//*[@id=\"pwd\"]")).sendKeys(password + Keys.ENTER);
		}
		catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}	

	public String goMenu(String mainMenu, String subMenu) {
		try {
			//element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id= \"" + mainMenu  +"\"]")));
			//element.click();
			driver.findElement(By.xpath("//*[@id= \"" + mainMenu + "\"]")).click();
			js = (JavascriptExecutor)driver;
			element = driver.findElement(By.xpath("//*[@id= \"" + subMenu  +"\"]"));
			js.executeScript("arguments[0].scrollIntoView();arguments[0].click();", element);
		}
		catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String checkToast(String msg) {
		try {
			element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
			errorMsg = element.getText();
			element.click();
			assertEquals(errorMsg, msg);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String clickButton(String xpath1) {
		try {
			//driver.findElement(By.xpath(xpath1)).click();
			element = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath1)));
			element.click();
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String checkError(String element_id, String input, String error_id, String err) {
		try {
			element = driver.findElement(By.xpath(element_id));
			element.sendKeys(input + Keys.TAB);
			errorMsg = driver.findElement(By.xpath(error_id)).getText();
			element.clear();
			assertEquals(errorMsg, err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";

	}

	public String checkSelectError(String element_id, String input, String error_id, String err) {
		try {
			Select dropdown = new Select(driver.findElement(By.xpath(element_id)));
			dropdown.selectByIndex(0);
			errorMsg = driver.findElement(By.xpath(error_id)).getText();
			element.clear();
			assertEquals(err, errorMsg);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String addSingleData(String type,String msg, String err) {
		try {
			driver.findElement(By.xpath(type)).sendKeys(msg);
			clickButton("//*[@id=\"btn_add\"]");
			checkToast(err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String addDoubleData(String type, String msg, String des, String msg1, String err) {
		try{
			driver.findElement(By.xpath(type)).sendKeys(msg);
			driver.findElement(By.xpath(des)).sendKeys(msg1);
			clickButton("//*[@id=\"btn_add\"]");
			checkToast(err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String addTripleData(String dropDrown, String value, String type, String msg, String des, String msg1, String err) {
		try {
			Select dropdown = new Select(driver.findElement(By.xpath(dropDrown)));
			dropdown.selectByVisibleText(value);
			driver.findElement(By.xpath(type)).sendKeys(msg);
			driver.findElement(By.xpath(des)).sendKeys(msg1);
			clickButton("//*[@id=\"btn_add\"]");
			checkToast(err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String editSingleData(String rowId, String type,String msg, String err) {
		try {
			driver.findElement(By.xpath(rowId)).click();
			driver.findElement(By.xpath(type)).clear();
			addSingleData(type, msg, err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String editDoubleData(String rowId, String type, String msg, String des, String msg1, String err) {
		try {
			driver.findElement(By.xpath(rowId)).click();

			driver.findElement(By.xpath(type)).clear();		
			driver.findElement(By.xpath(des)).clear();	

			addDoubleData(type, msg, des, msg1, err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String editTripleData(String rowId, String dropDrown, String value, String type, String msg, String des, String msg1, String err) {
		try {
			driver.findElement(By.xpath(rowId)).click();

			driver.findElement(By.xpath(type)).clear();		
			driver.findElement(By.xpath(des)).clear();		

			addTripleData(dropDrown, value, type, msg, des, msg1, err);
		}catch(AssertionError e) { return e.toString();	}
		catch(Exception e) { return e.toString(); }
		return "PASS";
	}

	public String delete(String id, String err) {
		try {
			//driver.findElement(By.xpath(id)).click();
			clickButton(id);
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//*[@id=\"delete_yes\"]")).click();
			clickButton("//*[@id=\"delete_yes\"]");
			checkToast(err);
		}
		catch(Exception e) { return e.toString(); }
		catch (AssertionError e) { return e.toString();	}
		return "PASS";
	}
}