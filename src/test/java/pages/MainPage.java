package pages;

import common.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class MainPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }
    //Слайдер
    @FindBy(css = "#bx_3218110189_764204 > a.slider__link > img")
    public List<WebElement> wrapper;
    //Текущая страница слайдера
    @FindBy(xpath = "//div[@class='swiper-pagination swiper-pagination-fraction']/span[@class='swiper-pagination-current']")
    public WebElement swiperPaginationCurrent;
    //Количество страниц слайдера
    @FindBy(xpath = "//div[@class='swiper-pagination swiper-pagination-fraction']/span[@class='swiper-pagination-total']")
    public WebElement swiperPaginationTotal;
    //Изображение слайдера до прокрутки
    @FindBy(xpath = "//div[@class = 'swiper-slide swiper-slide-active']//img")
            //"//div[@class='wrapper wrapper--slider-main']//div[contains(@class, 'swiper-slide') and not(contains(@class, 'duplicate'))]//img")
    public WebElement sliderImg;
    //Изображение слайдера после прокрутки
    @FindBy(xpath = "//div[@class = 'swiper-slide swiper-slide-duplicate swiper-slide-active']//img")
    public WebElement slideDuplicate;
    //Кнопка пролистывания слайдера
    @FindBy(xpath = "//div[@class='swiper-button-next']")
    public WebElement swiperButtonNext;
    //Блок новостей на главной странице
    @FindBy(className = "news")
    WebElement newsBlock;
    public String newsBlockS = "news";
    //Элемент блока новостей на Главной странице
    public String newsItemBlock = "//div[@class = 'news__item']";
    //Элемент блока новостей на главной странице с исключением
    public String newsItemWithException = "//div[@class = 'news__item']//a[not(contains(text(), 'Бестселлеры коллекций'))]";
    //Заголовок страницы новостей
    public String newsPageBlockTitle = "//div[@class='news-page__back-title']";

    /*
    Переход к блоку новостей
     */
    @Step("Подводим указатель к блоку 'Новости'")
    public void scrollToNews() {
        moveTo(newsBlock,"Блок новостей");
    }

    /*
    Выбор рандомной новости
     */
    @Step("Выбираем рандомную новость")
    public void randomNews() {
        int newsBlock = driver.findElements(By.xpath(newsItemBlock)).size();
        Random random = new Random();
        int randomNumber = random.nextInt(newsBlock) + 1;
        getElementByXpath("(" + newsItemWithException + ")[" + randomNumber + "]", "рандомная новость").click();
        waitTextToBe(newsPageBlockTitle, "Акции и новости");
    }

}
