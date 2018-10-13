//UPS SignUP Test using TestNG
package MavenProject;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class UpsTest {

	WebDriver driver = null;

	
	@BeforeMethod
	@Parameters(value={"browser"})
	public void beforeMethod(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			System.out.println("CHROME NAVIGATED");
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			// driver.get("http://www.naukri.com/");
			// driver.get("http://www.seleniumeasy.com/test/window-popup-modal-demo.html");

		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println("FIRE NAVIGATED");
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			// driver.get("http://www.naukri.com/");
			// driver.get("http://www.seleniumeasy.com/test/window-popup-modal-demo.html");
			System.out.println("Hello world!!");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	@Test
	public void shippingTest() throws InterruptedException {
		driver.get("https://www.ups.com/us/en/Home.page");
		WebElement ship = driver.findElement(By.id("ups-quickStartShip"));
		ship.click();
		WebElement addDifferentReturnAddress = driver
				.findElement(By.xpath("//*[@id='mainShipView']/div/div/form/div[3]/label/span/span[3]"));
		addDifferentReturnAddress.click();
		WebElement cancel = driver
				.findElement(By.xpath("//*[@id='mainShipView']/div/div/form/app-wizard-nav/div/button[2]"));
		Thread.sleep(1000);
		cancel.click();
	}

	@Test
	public void signUpTest() {
		driver.get("https://www.ups.com/us/en/Home.page");
		WebElement signUp = driver.findElement(By.xpath("//*[@id='ups-navItems']/ul[2]/li[2]/a"));
		signUp.click();
		String s = driver.getCurrentUrl();
		System.out.println(s);

		WebElement confirmCheck = driver.findElement(By.cssSelector("#ups-checkbox_group > div > label"));
		confirmCheck.click();

		WebElement name = driver.findElement(By.name("fullName"));
		name.sendKeys("juel");

		WebElement eMail = driver.findElement(By.name("email_input"));
		eMail.sendKeys("myemail@gmail.com");

		WebElement userID = driver.findElement(By.name("user_id_input"));
		userID.sendKeys("talentech");

		WebElement password = driver.findElement(By.name("password_input"));
		password.sendKeys("password");

		WebElement submit = driver
				.findElement(By.xpath("//*[@id='SignupDiv']/div[1]/div/div/div[2]/div[2]/form/div[4]/div/button"));
		submit.click();

		WebElement errorMsg = driver.findElement(
				By.xpath("//*[@id='SignupDiv']/div[1]/div/div/div[2]/div[2]/form/div[2]/div[2]/section/div"));
		String st1 = errorMsg.getText();
		System.out.println(st1);
		String st2 = "You already have a ups.com ID linked to this email address. If you continue, you'll need to log in with your new user ID to access this profile.\nSend a list of My User IDs";
		Assert.assertEquals(st1, st2);

	}

	@Test
	public void TestPopUp() {
		driver.get("http://www.popuptest.com/");
		/*
		 * WebElement click = driver.findElement(By.id("followall"));
		 * click.click();
		 */
		WebElement click = driver.findElement(By.linkText("Multi-PopUp Test #2"));
		click.click();
		// It will return the parent window name as a String
		String parent = driver.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will
		// return Set of St//rings
		Set<String> s1 = driver.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			// Here we will compare if parent window is not equal to child
			// window then we will close

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);

				System.out.println(driver.switchTo().window(child_window).getTitle());

				
			}

		}
		// once all pop up closed now switch to parent window
		driver.switchTo().window(parent);

	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		Thread.sleep(2000);
		driver.quit();
	}

}
