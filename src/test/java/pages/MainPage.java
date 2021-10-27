package pages;

import common.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class MainPage extends Settings {
    //Блок новостей на главной странице
    String newsBlock = "news";
    //Элемент блока новостей на Главной странице
    String newsItemBlock = "//div[@class = 'news__item']";
    //Элемент блока новостей на главной странице с исключением
    String newsItemWithException = "//div[@class = 'news__item']//a[not(contains(text(), 'Бестселлеры коллекций'))]";
    //Заголовок страницы новостей
    String newsPageBlockTitle = "//div[@class='news-page__back-title']";

    public void scrollToNews() {
        Actions scroll = new Actions(driver);
        scroll.moveToElement(getElementByClassName(newsBlock)).perform();
    }

    public void randomNews() {
        int newsBlock = driver.findElements(By.xpath(newsItemBlock)).size();
        Random random = new Random();
        int randomNumber = random.nextInt(newsBlock) + 1;
        getElementByXpath("(" + newsItemWithException + ")[" + randomNumber + "]").click();
    }
}
