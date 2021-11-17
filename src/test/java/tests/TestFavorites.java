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
        waitVisibilityElement(favoritePage.menuItem);
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock);
        favoritePage.selectRandomCard();
        waitVisibilityElement(favoritePage.cardPage);
        String cardTitle = favoritePage.getCardTitle();
        favoritePage.clickAddToFavorites();
//        favoritePage.addToFavoriteIcon.isEnabled();
        waitVisibilityElement(favoritePage.favoriteCounter1);
        pages.clickIconFavorites();
        waitVisibilityElement(favoritePage.favoriteBlock);
        Assert.assertTrue(cardTitle.contains(favoritePage.getFavoriteCardTitle()));
        favoritePage.clickDeleteFavoriteBtn();
        waitVisibilityElement(favoritePage.favoriteEmptyPage);
        favoritePage.clickGoToMainPage();
        waitVisibilityElement(favoritePage.favoriteCounter0);
    }

    /*
    Проверка добавления в "Избранное" из каталога
     */
    @Test
    public void addFavoritesFromCatalogue() throws InterruptedException {
        open("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem);
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock);
        String itemTitleInCatalog = favoritePage.selectFavoritesIcons();
//        favoritePage.addToFavoriteIcon.isEnabled();
        moveTo(favoritePage.headerElement);
        waitVisibilityElement(favoritePage.favoriteCounter1);
        pages.clickIconFavorites();
        waitVisibilityElement(favoritePage.favoriteBlock);
        Assert.assertTrue(itemTitleInCatalog.contains(favoritePage.getFavoriteCardTitle()));
        favoritePage.clickDeleteFavoriteBtn();
        waitVisibilityElement(favoritePage.favoriteEmptyPage);
        favoritePage.clickGoToMainPage();
        waitVisibilityElement(favoritePage.favoriteCounter0);
    }
}
