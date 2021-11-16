package pages;

import common.Settings;
import io.qameta.allure.*;
import io.qameta.allure.testng.TestInstanceParameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FavoritePage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public FavoritePage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //список категорий товаров
    @FindBy(xpath = "//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")
    public List<WebElement> menuItemsList;
    //элемент подкатегории товаров
    @FindBy(xpath = "//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")
    public WebElement menuItem;
    //заголовок в КТ
    @FindBy(tagName = "h1")
    WebElement title;
    //КТ в каталоге
    @FindBy(xpath = "//a[@class='catalog__item catalog__item--new owox-catalog-list']")
    public List<WebElement> catalogItems;
    //блок с КТ
    @FindBy(xpath = "//div[@class='catalog__list']")
    public WebElement catalogListBlock;
    //страница КТ
    @FindBy(xpath = "//div[@class='card']")
    public WebElement cardPage;
    //название товара в КТ
    @FindBy(xpath = "//div[@class='card__info-title']")
    WebElement cardTitle;
    //кнопка Добавить в избранное
    @FindBy(xpath = "//div[contains(text(),'Добавить в избранное')]")
    public WebElement addToFavoriteBtn;
    //иконка добавления в избранное
    @FindBy(xpath = "//input[@class='favorite__input']")
    public WebElement addToFavoriteIcon;
    //значок 1 на иконке избранного
    @FindBy(xpath = "//div[@class='basket-counter__value' and contains (text(),'1')]")
    public WebElement favoriteCounter1;
    //значок 0(невидимый?) на иконке избранного
    @FindBy(xpath = "//div[@class='basket-counter__value' and contains (text(),'0')]")
    public WebElement favoriteCounter0;
    //кнопка избранного в шапке
    @FindBy(xpath = "//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")
    public WebElement favoriteInHeader;
    //блок избранного
    @FindBy(xpath = "//div[@class='lk__favorites']")
    public WebElement favoriteBlock;
    //название товара в избранном
    @FindBy(xpath = "//div[@class='catalog__item-title']")
    public WebElement favoriteCardTitle;
    //список названий товаров в избранном
    @FindBy(xpath = "//div[@class='catalog__item-title']")
    List<WebElement> favoriteCardTitleList;
    //кнопка удаления из избранного
    @FindBy(xpath = "//div[@class='favorite__button']")
    public WebElement deleteFavoriteBtn;
    //пустая страница избранного
    @FindBy(xpath = "//div[@class='empty-content__title' and contains (text(),'Список понравившихся товаров пуст')]")
    public WebElement favoriteEmptyPage;
    //кнопка перейти на главную (в избранном)
    @FindBy(xpath = "//a[@class='button button--fill' and contains (text(),'Перейти на главную')]")
    public WebElement goToMainPage;
    //иконки избранного в списке КТ
    @FindBy(xpath = "//input[@class='favorite__input input-add-to-favorite']")
    public List<WebElement> favoritesIconsInCatalogInput;
    //часть шапки сайта - перенести в main
    @FindBy(xpath = "//div[@class='wrapper']")
    public WebElement headerElement;
    //список иконок добавления в избранное в каталоге
    @FindBy(xpath = "//div[@class='favorite__button btn-add-to-favorite owox-add-to-favorite-listing checked']")
    List<WebElement> favoritesIconsInCatalog;
    int randomItem;
    @Step("Выбор рандомного элемента меню")
    public void selectRandomMenu() {
        int menuItems = menuItemsList.size() - 4;
        randomItem = getRandom(menuItems);
        moveTo(menuItemsList.get(randomItem));
    }
@Step("Выбор рандомной карточки товара")
    public String selectRandomMenuItem() {
        int menuItemsInner =
                driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + (randomItem + 1) + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a")).size();
        int randomInner = getRandom(menuItemsInner) + 1;
        String randomItemInner = driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + (randomItem + 1) + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + (randomItem + 1) + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
        return randomItemInner;
    }
@Step("Выбор рандомной карточки товара")
    public void selectRandomCard() {
        int randomCatalogItem = getRandom(catalogItems.size());
        catalogItems.get(randomCatalogItem).click();
    }

    public String getTitle() {
        return title.getText().toLowerCase();
    }

    public String getCardTitle() {
        return cardTitle.getText().toLowerCase().replaceAll(" ", "").split("\n")[0];
    }

    public String getFavoriteCardTitle() {
        return favoriteCardTitle.getText().toLowerCase().replaceAll("[^\\da-zA-Z]", "");
    }

    public String selectFavoritesIcons() {
        int randomCatalogIconsToFavorites = getRandom(favoritesIconsInCatalogInput.size());
        String nameOfItemInCatalogue = favoriteCardTitleList.get(randomCatalogIconsToFavorites).getText().toLowerCase().replaceAll(" ", "");
        favoritesIconsInCatalog.get(randomCatalogIconsToFavorites).click();
        return nameOfItemInCatalogue;
    }
}
