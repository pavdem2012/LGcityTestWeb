package pages;

import common.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class MainPage extends Settings {
    public MainPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }
    //Блок новостей на главной странице
    public String newsBlock = "news";
    //Элемент блока новостей на Главной странице
    public String newsItemBlock = "//div[@class = 'news__item']";
    //Элемент блока новостей на главной странице с исключением
    public String newsItemWithException =
            "//div[@class = 'news__item']//a[not(contains(text(), 'Бестселлеры " + "коллекций'))]";
    //Заголовок страницы новостей
    public String newsPageBlockTitle = "//div[@class='news-page__back-title']";


    /*
    Переход к блоку новостей
     */
    public void scrollToNews() {
        Actions scroll = new Actions(driver);
        scroll.moveToElement(getElementByClassName(newsBlock)).perform();
    }

    /*
    Выбор рандомной новости
     */
    public void randomNews() {
        int newsBlock = driver.findElements(By.xpath(newsItemBlock)).size();
        Random random = new Random();
        int randomNumber = random.nextInt(newsBlock) + 1;
        getElementByXpath("(" + newsItemWithException + ")[" + randomNumber + "]").click();
    }

}
