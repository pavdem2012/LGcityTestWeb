package pages;

import common.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


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
    //Список цен в листинге товара
    @FindBy(xpath = "//div[@itemprop='price']")
    List<WebElement> priceItemTitleList;
    //название товара в листинге товара
    @FindBy(xpath = "//div[@class='catalog__item-title']")
    WebElement catalogItemTitle;
    //список названий товаров в листинге товара
    @FindBy(xpath = "//div[@class='catalog__item-title']")
    List<WebElement> catalogItemTitleList;
    public String jsTagName ="return document.querySelectorAll('div.content a').length";
    //Последний элемент пагинации
    @FindBy(xpath = "//a[@class='catalog__pagination-last js-pagination']")
    public WebElement paginationLast;
    //Категория левого меню
    @FindBy(xpath = "//div[@class='catalog__nav-item-title'] ")
    public WebElement leftMenuItem;
    //Счетчик элементов категории левого меню
    @FindBy(xpath = "//div[@class='catalog__nav-item-title'] /span")
    public WebElement countItemLeftMenu;
    //Подкатегория левого меню
    @FindBy(xpath = "//a[@class='catalog__nav-item-drop-link']")
    public WebElement leftMenuSubItem;
    //Счетчик элементов подкатегории левого меню
    @FindBy(xpath = "//a[@class='catalog__nav-item-drop-link']/span")
    public WebElement countSubItemLeftMenu;
    //Счетчик элементов подкатегории листинга в заголоаке
    @FindBy(xpath = "//div[@class='catalog__subtitle']")
    public WebElement countListingSubCategory;


    //Функции


    //Выбрать рандомный товар

    public ArrayList<String> cartItemPrice = new ArrayList<>();
    public ArrayList<String> cartItemSize = new ArrayList<>();
    public ArrayList<String> cartItemName = new ArrayList<>();
    @Step("Выбор рандомного товара из листинга для покупки ")
    public void selectQuickBuyList() throws InterruptedException {
        int num = getRandom(cardsList.size());
        WebElement randomCard = cardsList.get(num);
        moveTo(randomCard,"рандомная карточка товара");
        String title = catalogItemTitleList.get(num).getText().toLowerCase().replaceAll(" ","");
        cartItemName.add(title);
        wait(1);
        moveTo(catalogQuickBuyList.get(num),"рандомный размер для быстрой покупки");
        wait(2);
        int randomQuickBuSize =
                getRandom(driver.findElements(By.xpath("(//div[@class='catalog__quick-buy-list'])[" + (num + 1) + "]/button")).size()) + 1;
        String randomSize =
                driver.findElement(By.xpath("(//div[@class='catalog__quick-buy-list'])[" + (num + 1) + "]/button[" + randomQuickBuSize + "]")).getText().toLowerCase().replaceAll(" ", "");
        cartItemSize.add(randomSize);
        waitVisibilityElement(priceInList,"Цена в листинге товара");
        String price = priceItemTitleList.get(num).getText().replaceAll(" ","").replaceAll("₽","");
        cartItemPrice.add(price);
        driver.findElement(By.xpath("(//div[@class='catalog__quick-buy-list'])[" + (num + 1) + "]/button[" + randomQuickBuSize + "]")).click();

    }



    //Размер товара из листинга каталога
    public String getCatalogQuickBuyItem() {
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
