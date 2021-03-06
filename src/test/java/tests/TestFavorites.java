package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class TestFavorites extends Settings {

    /*
    Проверка добавления в "Избранное" из карточки товара
     */
    @Epic(value = "Избранное")
    @Feature(value = "Добавление в избранное из карточки товара")
    @Description("Добавление в избранное из карточки товара")
    @Test(description="Добавление в избранное из карточки товара")
    public void addFavoritesFromCartOfProduct() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        String title = favoritePage.getTitle();
        assertString(title,randomMenuItem);
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock,"Блок с карточками товаров");
        favoritePage.selectRandomCard();
        waitVisibilityElement(favoritePage.cardPage,"Карточка товара");
        String cardTitle = favoritePage.getCardTitle();
        favoritePage.clickAddToFavorites();
        waitVisibilityElement(favoritePage.favoriteCounter1,"иконка Избранное со счетчиком");
        pages.clickIconFavorites();
        waitVisibilityElement(favoritePage.favoriteBlock,"блок товаров в Избранное");
        String favoriteCardTitle=favoritePage.getFavoriteCardTitle();
        assertString(cardTitle,favoriteCardTitle);
        favoritePage.clickDeleteFavoriteBtn();
        waitVisibilityElement(favoritePage.favoriteEmptyPage,"пустая страница Избранное");
        favoritePage.clickGoToMainPage();
        waitVisibilityElement(favoritePage.favoriteCounter0, "иконка Избранное без счетчика");
    }

    /*
    Проверка добавления в "Избранное" из каталога
     */
    @Epic(value = "Избранное")
    @Feature(value = "Добавление в избранное из листинга товаров")
    @Description("Добавление в избранное из листинга товаров")
    @Test(description="Добавление в избранное из листинга товаров")
    public void addFavoritesFromCatalogue() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        String title = favoritePage.getTitle();
        assertString(title,randomMenuItem);
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock,"Блок с карточками товаров");
        String itemTitleInCatalog = favoritePage.selectFavoritesIcons();
        moveTo(favoritePage.headerElement,"Шапка сайта");
        waitVisibilityElement(favoritePage.favoriteCounter1,"иконка Избранное со счетчиком");
        pages.clickIconFavorites();
        waitVisibilityElement(favoritePage.favoriteBlock,"блок товаров в Избранное");
        String favoriteCardTitle=favoritePage.getFavoriteCardTitle();
        assertString(itemTitleInCatalog,favoriteCardTitle);
        favoritePage.clickDeleteFavoriteBtn();
        waitVisibilityElement(favoritePage.favoriteEmptyPage,"пустая страница Избранное");
        favoritePage.clickGoToMainPage();
        waitVisibilityElement(favoritePage.favoriteCounter0, "иконка Избранное без счетчика");
    }
}
