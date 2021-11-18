package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

public class TestMainMenu extends Settings {

    /*
    Проверка элементов главного меню.
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Главное меню")
    @Description("Проверка перехода из главного меню в каталог")
    @Test(description="Проверка перехода из главного меню в каталог")
    public void testMenu() throws InterruptedException {
        open("https://lgcity.ru");
        pages.menuItem.isEnabled();
        pages.randomMenuItem();

    }

    /*
    Футер
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Футер")
    @Description("Содержет элементы: меню, блок подписки")
    @Test(description="Содержет элементы: меню, блок подписки")
    public void testFooter() throws InterruptedException {
        open("https://lgcity.ru");
        moveTo(pages.footer,"футер");
        waitVisibilityElement(pages.aboutCompanyFooterMenu,"меню 'О КОМПАНИИ' в Футере");
        waitVisibilityElement(pages.onlineShoppingFooterMenu,"меню 'УСЛУГИ' в Футере");
        waitVisibilityElement(pages.servicesFooterMenu,"меню 'ОНЛАЙН-ПОКУПКИ' в Футере");
        waitVisibilityElement(pages.subscriptionBlockFooterMenu,"Блок подписки в Футере");
    }
}

