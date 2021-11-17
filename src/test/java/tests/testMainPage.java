package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testMainPage extends Settings {
    /*
Проверка элементов главного меню.
 */

    public void testMenu() throws InterruptedException {
        open("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        String randomMenuItem = favoritePage.selectRandomMenuItem();
        String getTitle =favoritePage.getTitle();
        Assert.assertTrue(getTitle.contains(randomMenuItem));
    }

}
