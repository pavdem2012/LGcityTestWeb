package tests;

import common.Settings;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFavorites extends Settings {

    /*
    Проверка добавления в "Избранное" из карточки товара
     */
    @Test
    public void addFavoritesFromCartOfProduct() throws InterruptedException {
        open("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock,"Блок с карточками товаров");
        favoritePage.selectRandomCard();
        waitVisibilityElement(favoritePage.cardPage,"Карточка товара");
        String cardTitle = favoritePage.getCardTitle();
        favoritePage.clickAddToFavorites();
//        favoritePage.addToFavoriteIcon.isEnabled();
        waitVisibilityElement(favoritePage.favoriteCounter1,"иконка Избранное со счетчиком");
        pages.clickIconFavorites();
        waitVisibilityElement(favoritePage.favoriteBlock,"блок товаров в Избранное");
        Assert.assertTrue(cardTitle.contains(favoritePage.getFavoriteCardTitle()));
        favoritePage.clickDeleteFavoriteBtn();
        waitVisibilityElement(favoritePage.favoriteEmptyPage,"пустая страница Избранное");
        favoritePage.clickGoToMainPage();
        waitVisibilityElement(favoritePage.favoriteCounter0, "иконка Избранное без счетчика");
    }

    /*
    Проверка добавления в "Избранное" из каталога
     */
    @Test
    public void addFavoritesFromCatalogue() throws InterruptedException {
        open("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock,"Блок с карточками товаров");
        String itemTitleInCatalog = favoritePage.selectFavoritesIcons();
//        favoritePage.addToFavoriteIcon.isEnabled();
        moveTo(favoritePage.headerElement);
        waitVisibilityElement(favoritePage.favoriteCounter1,"иконка Избранное со счетчиком");
        pages.clickIconFavorites();
        waitVisibilityElement(favoritePage.favoriteBlock,"блок товаров в Избранное");
        Assert.assertTrue(itemTitleInCatalog.contains(favoritePage.getFavoriteCardTitle()));
        favoritePage.clickDeleteFavoriteBtn();
        waitVisibilityElement(favoritePage.favoriteEmptyPage,"пустая страница Избранное");
        favoritePage.clickGoToMainPage();
        waitVisibilityElement(favoritePage.favoriteCounter0, "иконка Избранное без счетчика");
    }
}
