package tests;

import common.Settings;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testMainPage extends Settings {
    /*
Проверка элементов главного меню.
 */
    @Test
    public void testMenu() throws InterruptedException {
        open("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem);
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
    }
}
