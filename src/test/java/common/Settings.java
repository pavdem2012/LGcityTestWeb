package common;


import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Settings {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public MainPage mainPage;
    public CityPage cityPage;
    public CardProductPage cartProductPage;
    public Pages pages;
    public FavoritePage favoritePage;
    public FunctionsForTests functions;
    public BasketPage basketPage;
    public CatalogListPage catalogListPage;
    public CheckoutPage checkoutPage;
    public SearchResultPage searchResultPage;

    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        //driver.manage().window().setPosition(new Point(2000, 0));//Старт правый экран (не убирать)
        //driver.manage().window().setPosition(new Point(-2000,0));//Старт левый экран (не убирать)

        driver.manage().window().maximize();
        cityPage = new CityPage(driver, wait);
        cartProductPage = new CardProductPage(driver, wait);
        pages = new Pages(driver, wait);
        favoritePage = new FavoritePage(driver, wait);
        functions = new FunctionsForTests();
        basketPage = new BasketPage(driver, wait);
        catalogListPage = new CatalogListPage(driver, wait);
        checkoutPage = new CheckoutPage(driver, wait);
        mainPage = new MainPage(driver, wait);
        searchResultPage=new SearchResultPage(driver,wait);
    }
    @Step("Получить скриншот страницы")
    public void getScreen() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("screenshot/qe.jpg"));
    }

    @Step("Открываем главную страницу {baseUrl}")
    public void open(String baseUrl) throws InterruptedException {
        driver.get(baseUrl);
        wait(1);
        pages.setCloseCookieBtn();
    }
    @Step("Переходим к элементу '{commentString}'")
    public WebElement getElementByXpath(String string, String commentString) {
        return driver.findElement(By.xpath(string));
    }
    @Step("Получить список элементов")
    public List<WebElement> getElementsByXpath(By string) {
        return driver.findElements(string);
    }
    @Step("Получить элемент по имени класса '{commentString}'")
    public WebElement getElementByClassName(String string, String commentString) {
        return driver.findElement(By.className(string));
    }
    @Step("Получить элемент по ID")
    public WebElement getElementById(String string) {
        return driver.findElement(By.id(string));
    }

    @Step("Ожидаем видимость '{commentString}'")
    public void waitVisibilityElement(String string, String commentString) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(string)));
    }

    @Step("Ожидаем видимость элемента '{string}'")
    public static void waitVisibilityElement(WebElement element,String string) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    @Step("Ожидание невидимости элемента '{string}'")
    public void waitInvisibilityElement(WebElement element, String string) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    @Step("Ожидание присутствия текста '{text}'")
    public void waitTextToBe(String string, String text) {
        wait.until(ExpectedConditions.textToBe(By.xpath(string), text));
    }
    @Step("Ожидание значение элемента")
    public void waitValueInElement(WebElement element, String string) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, string));
    }

    @Step("Передать текст в поле '{keys}'")
    public void sendKeysToBody(Keys keys) {
        driver.findElement(By.xpath("//body")).sendKeys(keys);
    }
    @Step("Выбор рандомного элемента")
    public int getRandom(int number) {
        Random random = new Random();
        return random.nextInt(number);
    }
    @Step("Передаем строку в поле ввода")
    public void sendString(WebElement element, String string){
        element.sendKeys(string);
    }
    @Step("Переместиться к элементу")
    public void moveTo(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
    @Step("Получить URL страницы")
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void wait(int second) throws InterruptedException {
        int time = second * 1000;
        Thread.sleep(time);
    }
    @Step("Проверяем содержит ли элемент '{string}', проверочное слово '{verificationString}'.")
    public void assertString(String string,String verificationString){
        Assert.assertTrue(string.contains(verificationString),"Проверяемый элемент: "+string+" не содержит: "+verificationString);
    }
    @Step("Клик по элементу {string}")
    public void clickElement(WebElement element, String string){
        element.click();
    }
    @Step("Получаем текст элемента '{string}'")
    public String getTextElement(WebElement element, String string){
       return element.getText().toLowerCase();
    }
    @AfterMethod
    public void quit() {
        driver.quit();
    }
}
