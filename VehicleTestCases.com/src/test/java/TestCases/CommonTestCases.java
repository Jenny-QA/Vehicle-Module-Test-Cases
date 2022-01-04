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
	
	public void verifyElement(String inputElement,  String msg) {
		element = driver.findElement(By.xpath(inputElement));
		element.sendKeys(Keys.TAB);
		errorMsg = driver.findElement(RelativeLocator.with(By.tagName("span")).below(element)).getText();
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
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.className("white-bbtn")).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		errorMsg = element.getText();
		//System.out.println(errorMsg);
		element.click();
		assertEquals(errorMsg, msg);
		//enable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + msg + "')]"))).isDisplayed();
		//assertEquals(enable, true);
	}

	public void goMenu(String mainMenu, String subMenu) {
		driver.findElement(By.xpath("//*[contains(text(),'" + mainMenu + "')]")).click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + subMenu + "')]")));
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void checkErrorMessage(String msg) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"toast-container\"]/div/div[2]")));
		errorMsg = element.getText();
		element.click();
		assertEquals(errorMsg, msg);
	}
	
	public void openForm(String xpath1, String xpath2) {
		driver.findElement(By.xpath(xpath1)).click();
		verifyAddButton(xpath2);
	}
	
	public void verifyAddButton(String xpath) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		enable = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath))).isEnabled();
		assertEquals(enable, false);
	}
	
}