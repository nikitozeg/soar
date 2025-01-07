import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class JuiceShopSafariTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        // Initialize SafariDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Set up explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

 @Test
    public void task1() {
        driver.get("https://juice-shop.herokuapp.com/");

        driver.findElement(By.xpath("//button[@aria-label='Close Welcome Banner']")).click();
        String COOKIE_BTN_CLOSE = "//a[@aria-label='dismiss cookie message']";

        List<WebElement> cookieButtons = driver.findElements(By.xpath(COOKIE_BTN_CLOSE));

        if (!cookieButtons.isEmpty()) {
            cookieButtons.get(0).click();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".cdk-overlay-backdrop.cdk-overlay-dark-backdrop")
        ));

        WebElement itemsPerPageDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("mat-select[aria-label='Items per page:']")
        ));

        itemsPerPageDropdown.click();

        WebElement maxItemsOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//mat-option/span[contains(text(),'48')]")
        ));
        maxItemsOption.click();

        wait.until(ExpectedConditions.numberOfElementsToBe(
                By.cssSelector("div.mat-grid-tile-content"), 37
        ));

    }


    @Test
    public void task2() {
        // Navigate to Juice Shop
        driver.get("https://juice-shop.herokuapp.com/");

        driver.findElement(By.xpath("//button[@aria-label='Close Welcome Banner']")).click();
        String COOKIE_BTN_CLOSE = "//a[@aria-label='dismiss cookie message']";

        List<WebElement> cookieButtons = driver.findElements(By.xpath(COOKIE_BTN_CLOSE));

        if (!cookieButtons.isEmpty()) {
            cookieButtons.get(0).click();
        }

        driver.findElement(By.xpath("//div[contains(text(),'Apple Juice (1000ml)')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementWithText
                (By.xpath("//span[contains(text(),'()')]"), "()"));

        String imageXPath = "//img[@alt='Apple Juice (1000ml)']";

        Assert.assertTrue(driver.findElement(By.xpath(imageXPath)).isDisplayed());

    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}