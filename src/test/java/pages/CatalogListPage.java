package pages;

import common.Settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Locale;

public class CatalogListPage extends Settings {

    WebDriver driver;
    WebDriverWait wait;

    public CatalogListPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Карточка товара в листиге
    @FindBy(xpath = "//a[@class='catalog__item catalog__item--new owox-catalog-list']")
    public List<WebElement> catalogItems;
    //Всплывающее меню быстрой покупки
    @FindBy(xpath = "//div[@class='catalog__quick-buy catalog__quick-buy--size']")
    public List<WebElement> quickBuyCatalog;
    //карточки товаров на странице
    @FindBy(xpath = "//div[@class='catalog__item-top']")
    List<WebElement> cardsList;
    //Доступные размеры в всплывающее меню быстрой покупки
    @FindBy(xpath = "//div[@class='catalog__quick-buy-list']")
    public List<WebElement> catalogQuickBuyList;
    //Кнопка добавления в корзину в всплывающее меню быстрой покупки
    @FindBy(xpath = "//button[@class='catalog__quick-buy-item']")
    WebElement catalogQuickBuyItem;
    //Цена в листинге товара
    @FindBy(xpath = "//div[@itemprop='price']")
    WebElement priceInList;
    //название товара в листинге товара
    @FindBy(xpath = "//div[@class='catalog__item-title']")
    WebElement catalogItemTitle;
    //int randomQuickBuyList;
    //int randomQuickBuSize;
    //Функции


    //Подвести к рандомной карточке
    public void selectQuickBuyList() throws InterruptedException {
//        int randomQuickBuyList = getRandom(catalogQuickBuyList.size());
        int num = getRandom(cardsList.size());
        WebElement randomCard = cardsList.get(num);
        moveTo(randomCard);
//        System.out.println("рандом 1: " + randomQuickBuyList);
//        System.out.println( getUrl());
        Thread.sleep(1000);
        moveTo(catalogQuickBuyList.get(num));
        Thread.sleep(1000);
        int randomQuickBuSize =
                getRandom(driver.findElements(By.xpath("(//div[@class='catalog__quick-buy-list'])[" + (num + 1) + "]/button")).size()) + 1;
        String randomSize =
                driver.findElement(By.xpath("(//div[@class='catalog__quick-buy-list'])[" + (num + 1) + "]/button[" + randomQuickBuSize + "]")).getText().replaceAll(" ", "");
        System.out.println("рандом 2: " + randomQuickBuSize);
        System.out.println("размер " + randomSize);
    }

    /*public int randomQuickBuSize() {
        System.out.println(randomQuickBuyList);
*//*        int randomQuickBuyList = getRandom(catalogQuickBuyList.size());
        moveTo(catalogQuickBuyList.get(randomQuickBuyList));*//*
        return   getRandom(driver.findElements(By.xpath("(//div[@class='catalog__quick-buy-list'])[" +
        randomQuickBuyList + "]/button")).size());
        }*/

    //Размер товара из листинга каталога
    public String getCatalogQuickBuyItem() {
        //getCatalogQuickBuyItem = getRandom(driver.findElements(By.xpath
        // ("//button[@class='catalog__quick-buy-item'])["+randomQuickBuSize+"]")))
        return catalogQuickBuyItem.getText().toLowerCase().replaceAll(" ", "");
    }

    //Цена товара из листинга каталога
    public String getPriceInList() {
        return priceInList.getText().replaceAll(" ", "").replaceAll("₽", "");
    }

    //Название товара из листинга каталога
    public String getCatalogItemTitle() {
        return catalogItemTitle.getText().toLowerCase().replaceAll(" ", "");
    }
}
