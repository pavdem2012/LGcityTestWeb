package tests;

import common.Settings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

public class TestFavorites extends Settings {

    /*
    Проверка добавления в "Избранное" из карточки товара
     */
    @Test
    public void addFavoritesFromCartOfProduct() {
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
        favoritePage.addToFavoriteBtn.click();
//        favoritePage.addToFavoriteIcon.isEnabled();
        waitVisibilityElement(favoritePage.favoriteCounter1);
        favoritePage.favoriteInHeader.click();
        waitVisibilityElement(favoritePage.favoriteBlock);
        Assert.assertTrue(cardTitle.contains(favoritePage.getFavoriteCardTitle()));
        favoritePage.deleteFavoriteBtn.click();
        waitVisibilityElement(favoritePage.favoriteEmptyPage);
        favoritePage.goToMainPage.click();
        waitVisibilityElement(favoritePage.favoriteCounter0);
    }

    /*
    Проверка добавления в "Избранное" из каталога
     */
    @Test
    public void addFavoritesFromCatalogue() {
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
        favoritePage.favoriteInHeader.click();
        waitVisibilityElement(favoritePage.favoriteBlock);
        Assert.assertTrue(itemTitleInCatalog.contains(favoritePage.getFavoriteCardTitle()));
        favoritePage.deleteFavoriteBtn.click();
        waitVisibilityElement(favoritePage.favoriteEmptyPage);
        favoritePage.goToMainPage.click();
        waitVisibilityElement(favoritePage.favoriteCounter0);
    }
}
