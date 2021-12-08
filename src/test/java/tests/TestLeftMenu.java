package tests;

import common.Settings;
import common.TestData;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;


public class TestLeftMenu extends Settings {
    @Epic(value = "Левое меню")
    @Feature(value = "Проверка левого меню по категориям и подкатегориям")
    @Description("Проверка левого меню по категориям и подкатегориям")
    @Test
    public void testLeftMenu() throws InterruptedException {
        openWithCloseCookie(TestData.getProperty("baseUrl"));
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        //System.out.println(getUrl());
        catalogListPage.checkCountsCategory();
        catalogListPage.checkCountsSumSubcategory();
        catalogListPage.checkSubCategoryList();
    }
}
