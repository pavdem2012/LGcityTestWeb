package tests;

import common.Settings;
import org.testng.Assert;

public class testMainMenu extends Settings {
    /*
Проверка элементов главного меню.
 */

    public void testMenu() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.movieToRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        String getTitle =favoritePage.getTitle();
        Assert.assertTrue(getTitle.contains(randomMenuItem));
    }

}
