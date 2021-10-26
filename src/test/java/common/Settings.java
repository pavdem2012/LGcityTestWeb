package common;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.concurrent.TimeUnit;

public class Settings {
    public static WebDriver driver;
    public WebDriverWait wait;
    public static MainPage mainPage = new MainPage();

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
    }

    public WebElement getElementByXpath(String string) {
        return driver.findElement(By.xpath(string));
    }

    public WebElement getElementByClassName(String string) {
        return driver.findElement(By.className(string));
    }

    public WebElement getElementById(String string) {
        return driver.findElement(By.id(string));
    }

    public void waitVisibilityElement(String string) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string)));
    }

    public void waitTextToBe(String string, String text) {
        wait.until(ExpectedConditions.textToBe(By.xpath(string), text));
    }

    /*    public WebElement getElementsByXpath(String string) {
            ArrayList<WebElement> list = driver.findElements(By.xpath(string));
            return driver.findElements(By.xpath(string));
        }*/
    @After
    public void quit() {
        driver.quit();
    }
}
