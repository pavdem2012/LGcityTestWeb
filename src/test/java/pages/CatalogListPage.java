package pages;

import common.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


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
    public List<WebElement> cardsList;
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
    public List<WebElement> leftMenuItemList;
    //Счетчик элементов категории левого меню
    @FindBy(xpath = "//div[@class='catalog__nav-item-title'] /span")
    public WebElement countItemLeftMenu;
    //Подкатегория левого меню
    @FindBy(xpath = "//div[@class='catalog__nav-item catalog__nav-item--active']/div[@class='catalog__nav-item-drop']//a")
    public List<WebElement> leftMenuSubItemList;
    //Счетчик элементов подкатегории левого меню
    //@FindBy(xpath = "//a[@class='catalog__nav-item-drop-link']/span")
    @FindBy(xpath = "//div[@class='catalog__nav-item catalog__nav-item--active']/div[@class='catalog__nav-item-drop']//span")
     public List<WebElement> countSubItemLeftMenuList;
    //Счетчик элементов подкатегории листинга в заголоаке
    @FindBy(xpath = "//div[@class='catalog__subtitle']")
    public WebElement countListingSubCategory;
    //Счетчик в кнопке фильтров "Показать"
    @FindBy(xpath = "//div[@class='button button--fill btn-full filter-submit-btn filter__button--apply']/div")
    public WebElement countsFilterSubmitBtn;
    //Кнопка загрузить еще
    @FindBy(xpath = "//a[@id='load-more']")
    public WebElement loadMoreBtn;
    @FindBy(xpath = "//a[@class='catalog__pagination-next js-pagination']")
    public WebElement arrowIcon;
    //список категорий товаров
    @FindBy(xpath = "//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")
    public List<WebElement> menuItemsList;
    int randomItem;

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
    @Step("Проверяем соответствие счетчиков в заголовке каталога, категории левого меню, кнопке показать все и количество страниц в пагинации")
    public void checkCountsCategory() throws InterruptedException {
        int countCartOnPage = cardsList.size();
        //System.out.println("Количество карточек на странице: "+countCartOnPage);
        double countHeader =Integer.parseInt(countListingSubCategory.getText().replaceAll("[^\\d+]",""));
        //System.out.println("Счетчик товаров в заголовке: "+countHeader);
        double countPage = Integer.parseInt(paginationLast.getText());
        //System.out.println("Количество страниц в пагинации: "+countPage);
        double checkPages = Math.ceil(countHeader/countCartOnPage);
        //System.out.println("Проверочное число количества страниц в пагинацмии: "+checkPages);
        wait(1);
        double countFilterSubmitBtn = Integer.parseInt(countsFilterSubmitBtn.getText().replaceAll("[^\\d+]",""));
        //System.out.println("Счетчик товаров в кнопке 'Показать' в блоке фильтров: " + countFilterSubmitBtn);
        Assert.assertEquals(countPage,checkPages,"Количество товаров не соответствует количеству страниц");
        Assert.assertEquals(countHeader,countFilterSubmitBtn,"Счетчик товаров в заголовке не совпадает со счетчиком товаров в кнопке 'Показать'");
    }
    @Step("Выбираем рандомную категорию левого меню и сравниваем ее счетчик с суммой счетчиков входящих в нее элементов")
    public void checkCountsSumSubcategory(){
        int leftMenuItems = leftMenuItemList.size();
        int randomItem;
        randomItem = getRandom(leftMenuItems);
        clickElement(leftMenuItemList.get(randomItem),"рандомный элемента меню");
        //System.out.println("Наименование товара в рандомном элементе левого меню: "+(leftMenuItemList.get(randomItem).getText().replaceAll("[0-9]","")));
        int countRandomLeftMenuItem= Integer.parseInt(leftMenuItemList.get(randomItem).getText().replaceAll("[^\\d+]",""));
        //System.out.println("Счетчик товаров в рандомном элементе левого меню: "+countRandomLeftMenuItem);
        int subcategoryLeftMenuItems =countSubItemLeftMenuList.size();
        //System.out.println("Количество подкатегорий рандомного элемента меню: "+subcategoryLeftMenuItems);
        int check=0;
        for (int i = 0; i<subcategoryLeftMenuItems;i++){
            int countSubcategoryItem = Integer.parseInt(countSubItemLeftMenuList.get(i).getText());
            check+=countSubcategoryItem;
        }
        //System.out.println("Сумма счетчиков подкатегорий рандомного элемента меню: "+check);
        Assert.assertEquals(countRandomLeftMenuItem,check, "Не совпадает счетчик рандомного элемента левого меню и сумма счетчиков входящих в него подкатегорий ");
    }
    @Step("Переходим в рандомную подкатегорию левого меню и проверяем соответствие: заголовка подкатегории заголовку страницы, счетчика субкатегории счетчику заголовка страницы и счетчика в заголовке страницы счетчику в кнопке показать все")
public void checkSubCategoryList() throws InterruptedException {
        int randomSubItem = getRandom(countSubItemLeftMenuList.size());
        //System.out.println(randomSubItem);
        String subcategoryLeftMenuName =  leftMenuSubItemList.get(randomSubItem).getText().toLowerCase().replaceAll("[0-9]","");
        //System.out.println("Наименование товара рандомной подкатегории рандомного элементе левого меню: " + subcategoryLeftMenuName);
        int countSubcategoryLeftMenu = Integer.parseInt(countSubItemLeftMenuList.get(randomSubItem).getText());
        //System.out.println("Счетчик товара рандомной подкатегории рандомного элементе левого меню: " + countSubcategoryLeftMenu);
        clickElement(leftMenuSubItemList.get(randomSubItem),"");
        wait(1);
        String randomPageHeader =getTitle();
        //System.out.println(randomPageHeader);
        assertString(randomPageHeader,subcategoryLeftMenuName);
        int countRandomPageHeader =Integer.parseInt(countListingSubCategory.getText().replaceAll("[^\\d+]",""));
        //System.out.println("Счетчик товаров заголовка страницы при переходе из субкатегории: "+countRandomPageHeader);
        Assert.assertEquals(countSubcategoryLeftMenu,countRandomPageHeader,"Не совпадает счетчик субкатегории левого меню и счетчик заголовка страницы на которую она ведет");
        int countRandomPageFilterSubmitBtn = Integer.parseInt(countsFilterSubmitBtn.getText().replaceAll("[^\\d+]",""));
        //System.out.println("Счетчик товаров в кнопке 'Показать' в блоке фильтров: "+countRandomPageFilterSubmitBtn);
        Assert.assertEquals(countRandomPageHeader,countRandomPageFilterSubmitBtn,"Счетчик товаров в заголовке не совпадает со счетчиком товаров в кнопке 'Показать'");
    }
    @Step("Плучаем число страниц в пагинации и проверяем их соответствие количеству товаров")
    public double countPaginationPages(int countPage){
        int countCartOnPage = cardsList.size();
        System.out.println("Количество карточек на странице: "+countCartOnPage);
        double countHeader =Integer.parseInt(countListingSubCategory.getText().replaceAll("[^\\d+]",""));
        System.out.println("Счетчик товаров в заголовке: "+countHeader);
        double countPages = Integer.parseInt(paginationLast.getText());
        System.out.println("Количество страниц в пагинации: "+countPages);
        double checkPages = Math.ceil(countHeader/countCartOnPage);
        System.out.println("Проверочное число количества страниц в пагинацмии: "+checkPages);
        Assert.assertEquals(countPages,checkPages,"Количество товаров не соответствует количеству страниц");
        return countPages;
    }
    //Поиск раздела каталога с пагинацией
    @Step("Ищем раздел каталога с пагтнацией")
    public void searchLoadMoreBtn(){
        for (int i = 0; i < 10; i++) {
            movieToRandomMenu();
            selectRandomMenuItem();
            System.out.println(getUrl());
            double countPage = 1;
            try {
                countPage = countPaginationPages(0);
                if (countPage > 1) {
                    break;
                }
            } catch (Exception pages) {
                System.out.println("На странице не более 24 товаров, пробуем заново");
            }
        }
    }
    @Step("Выбираем рандомный пункт категории меню")
    public String selectRandomMenuItem() {
        int menuItemsInner = driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list" +
                "-item'][" + (randomItem + 1) + "]//div[@class='header__drop-inner']/div[@class='header__drop" +
                "-category-col']//a")).size();
        int randomInner = getRandom(menuItemsInner) + 1;
        String randomItemInner = driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav" +
                "-list-item'][" + (randomItem + 1) + "]//div[@class='header__drop-inner']/div[@class='header__drop" +
                "-category-col']//a)[" + randomInner + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + (randomItem + 1) + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
        return randomItemInner;
    }
    //Проверка наличия элемента
    @Step("Проверяем отсутствие элемента на странице")
    public boolean isElementPresent(WebElement element) {

        try {
            new WebDriverWait(driver, 2).until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Step("Подводим курсор к рандомному элементу меню")
    public void movieToRandomMenu() {
        int menuItems = menuItemsList.size() - 4;
        randomItem = getRandom(menuItems);
        moveTo(menuItemsList.get(randomItem),"рандомный элемента меню");
    }
    //Прокликиваем кнопку "Загрузить еще"
    @Step("Прокликиваем кнопку \"Загрузить еще\"")
    public void clickLoadMoreBtn(){
        while (true) try {
            moveTo(paginationLast, "кнопка 'Загрузить еще'");
            wait(1);
            clickElement(loadMoreBtn, "Нажимаем кнопку 'Загрузить еще'");
            wait(1);
        } catch (Exception e) {
            break;
        }
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
