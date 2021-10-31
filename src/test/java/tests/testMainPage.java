package tests;

import common.Settings;
import org.junit.Assert;
import org.junit.Test;

public class testMainPage extends Settings {
    /*
Проверка элементов главного меню.
 */
    @Test
    public void testMenu() {
        open("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem);
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
    }
}
