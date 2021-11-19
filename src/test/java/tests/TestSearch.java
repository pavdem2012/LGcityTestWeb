package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;


public class TestSearch extends Settings {
    @Epic(value = "Главная страница")
    @Feature(value = "Строка поиска")
    @Description("Поиск по наименованию товара")
    @Test(description = "Поиск по наименованию товара")
    public void SearchByName() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        clickElement(pages.searchIcon, "иконка поиска");
        waitVisibilityElement(pages.searchPopup, "Попап поиска");
        clickElement(pages.searchString, "Поле поиска");
        sendString(pages.searchString, "Футболка");
        pages.searchString.sendKeys(Keys.ENTER);
        waitVisibilityElement(searchResultPage.searchResultPageHeader, "Страница результатов поиска");
        String title = searchResultPage. selectRandomName();
        clickElement(pages.searchIcon, "иконка поиска");
        waitVisibilityElement(pages.searchPopup, "Попап поиска");
        String inputProduct = pages.searchPopup.getAttribute("value").toLowerCase();
        assertString(title,inputProduct);
    }
}
