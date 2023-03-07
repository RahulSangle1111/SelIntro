package Flipkart;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipkartTest {

	public static void main(String[] args) throws InterruptedException {
		// System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");

//		Exit from login window by clicking on close
		driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
//		//searching IPad in search box
		driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("ipad");
//		clicking on search button
		driver.findElement(By.xpath("//button[@type='submit']//*[name()='svg']")).click();
//		There is no  filter all results by the Online Only filter according to me please correct me 		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// main window handle
		String MainWindow = driver.getWindowHandle();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//div[normalize-space()='APPLE iPad (10th Gen) 256 GB ROM 10.9 inch with Wi-Fi Only (Silver)']")));
		driver.navigate().refresh();
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.xpath(
				"//div[normalize-space()='APPLE iPad (10th Gen) 256 GB ROM 10.9 inch with Wi-Fi Only (Silver)']"))
				.click();
		// To handle all new opened window -->Child window handle is s1
		Set<String> s1 = driver.getWindowHandles();
		java.util.Iterator<String> i1 = s1.iterator();
		// Here we will check if child window has other child windows and when child
		// window
		// is the main window it will come out of loop.
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				Thread.sleep(5);
				// placing the order by clicking on BuyNow
				driver.findElement(By.xpath("//button[@type='button']")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='CONTINUE']")));
				// providing mobile no to get OTP
				driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("9607649960");
				// clicking on continue to get OTP
				driver.findElement(By.xpath("//span[normalize-space()='CONTINUE']")).click();
				// driver.close();

			}
		}
		// closing all windows
		driver.quit();

	}

}
