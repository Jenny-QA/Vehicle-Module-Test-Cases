package TestCases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonTestCases {

	public WebDriver driver;
	private WebElement element;
	public boolean enable;
	public String errorMsg;
	public WebDriverWait wait;
	public JavascriptExecutor js;

	public CommonTestCases() {
		// TODO Auto-generated constructor stub
	}

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

	public void verifyElement(String inputElement, String xpathError  ,String msg) {
		element = driver.findElement(By.xpath(inputElement));
		element.sendKeys(Keys.TAB);
		//errorMsg = driver.findElement(RelativeLocator.with(By.tagName("span")).below(element)).getText();
		errorMsg = driver.findElement(By.xpath(xpathError)).getText();
		assertEquals(errorMsg , msg);
	}

	public void verifyLength(String inputElement,  String msg) {
		element = driver.findElement(By.xpath(inputElement));
		element.sendKeys("a" + Keys.TAB);
		errorMsg = driver.findElement(RelativeLocator.with(By.tagName("span")).below(element)).getText();
		assertEquals(errorMsg , msg);
	}

	public void verifyLogin(String username, String password ,String msg) {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password + Keys.ENTER);
		element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		errorMsg = element.getText();
		element.click();
		assertEquals(errorMsg, msg);
	}

	public void verifyLogin2(String username, String password) {
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password + Keys.ENTER);
	}	

	public void goMenu(String mainMenu, String subMenu) {
		//driver.findElement(By.xpath("//*[contains(text(),'" + mainMenu + "')]")).click();
		driver.findElement(By.xpath("//*[@id= '" + mainMenu  +" ']")).click();
		//element = new WebDriverWait(driver, Duration.ofSeconds(Util.WAIT_TIME)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + subMenu + "')]")));
		driver.findElement(By.xpath("//*[@id= '" + subMenu  +" ']")).click();
		//js = (JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();", element);
	}
	
	public void checkErrorMessage(String msg) {
		element = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		errorMsg = element.getText();
		element.click();
		assertEquals(errorMsg, msg);
	}

	public void openForm(String xpath1, String xpath2) {
		driver.findElement(By.xpath(xpath1)).click();
		verifyAddButton(xpath2);
	}

	public void verifyAddButton(String xpath) {
		js = (JavascriptExecutor)driver;
		//element = new WebDriverWait(driver, Duration.ofSeconds(Util.WAIT_TIME)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		element = driver.findElement(By.xpath(xpath));
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		enable = element.isEnabled();
		assertEquals(enable, false);
	}
}