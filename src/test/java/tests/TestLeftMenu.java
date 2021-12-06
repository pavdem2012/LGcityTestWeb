package tests;

import common.Settings;
import common.TestData;

import org.testng.annotations.Test;


@Test
public class TestLeftMenu extends Settings {
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
