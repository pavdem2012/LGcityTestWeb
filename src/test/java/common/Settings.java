package common;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Settings {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static MainPage mainPage = new MainPage();
    public CityPage cityPage;
    public CardProductPage cartProductPage;
    public Pages pages;
    public FavoritePage favoritePage;
    public FunctionsForTests functions;
    public BasketPage basketPage;
    public CatalogListPage catalogListPage;

    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(1500, 0));
        driver.manage().window().maximize();
        cityPage = new CityPage(driver, wait);
        cartProductPage = new CardProductPage(driver, wait);
        pages = new Pages(driver, wait);
        favoritePage = new FavoritePage(driver, wait);
        functions = new FunctionsForTests();
        basketPage = new BasketPage(driver,wait);
        catalogListPage = new CatalogListPage(driver,wait);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
        pages.setCloseCookieBtn();
        waitInvisibilityElement(pages.closeCookieBtn);
    }

    public WebElement getElementByXpath(String string) {
        return driver.findElement(By.xpath(string));
    }

    public List<WebElement> getElementsByXpath(By string) {
        return driver.findElements(string);
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

    public static void waitVisibilityElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitInvisibilityElement(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitTextToBe(String string, String text) {
        wait.until(ExpectedConditions.textToBe(By.xpath(string), text));
    }

    public void waitValueInElement(WebElement element, String string) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, string));
    }

    public void sendKeysToBody(Keys keys) {
        driver.findElement(By.xpath("//body")).sendKeys(keys);
    }

    public int getRandom(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }

    public void moveTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void wait(int second) throws InterruptedException {
        int time = second * 1000;
        Thread.sleep(time);
    }

    @After
    public void quit() {
        driver.quit();
    }
}
