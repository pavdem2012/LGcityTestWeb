package tests;

import common.Settings;
import common.TestData;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;


public class TestLeftMenu extends Settings {
    @Epic(value = "Левое меню")
    @Feature(value = "Проверка левого меню по категориям и подкатегориям")
    @Description("Проверка левого меню по категориям и подкатегориям")
    @Test
    public void testLeftMenu() throws InterruptedException {
        openWithCloseCookie(TestData.getProperty("baseUrl"));
        waitVisibilityElement(favoritePage.menuItem, "элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        //System.out.println(getUrl());
        catalogListPage.checkCountsCategory();
        catalogListPage.checkCountsSumSubcategory();
        catalogListPage.checkSubCategoryList();
    }

    @Epic(value = "Проверка пагинатора и кнопки \"Загрузить еще\"")
    @Feature(value = "Проверка пагинатора и кнопки \"Загрузить еще\"")
    @Description("Проверка пагинатора и кнопки \"Загрузить еще\"")
    @Test
    public void testPaginationLoadMoreBtn() throws InterruptedException {
        openWithCloseCookie(TestData.getProperty("baseUrl"));
        waitVisibilityElement(favoritePage.menuItem, "элемент подкатегории товаров");
        catalogListPage.searchLoadMoreBtn();
        catalogListPage.clickLoadMoreBtn();
        assertFalse(catalogListPage.isElementPresent(catalogListPage.loadMoreBtn),"Все товары в подборке открыты, а кнопка 'Загрузить еще' отображается");
        assertFalse(catalogListPage.isElementPresent(catalogListPage.arrowIcon),"Все товары в подборке открыты, а стрелка пагинации отображается");
    }
}

