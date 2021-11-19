package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

public class testMainPage extends Settings {

    /*
    Проверка элементов главного меню.
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Главное меню")
    @Description("Проверка перехода из главного меню в каталог")
    @Test(description="Проверка перехода из главного меню в каталог")
    public void testMenu() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        pages.menuItem.isEnabled();
        pages.randomMenuItem();

    }
    /*
    Лого / фавиконка Присутствует фавиконка сайта
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Лого / фавиконка")
    @Description("Присутствует фавиконка сайта")
    @Test(description="Присутствует фавиконка сайта")
    public void faviconPresent() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        String favicon = pages.favicon.getAttribute("href");
        openPage(favicon);
        assertString(getTitle(),"favicon");

    }
    /*
    Слайдер Присутсвует слайдер на главной странице
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Слайдер")
    @Description("Присутсвует слайдер на главной странице")
    @Test(description="Присутсвует слайдер на главной странице")
    public void sliderPresent() throws InterruptedException {
        openPage("https://lgcity.ru");
        int countSlide = Integer.parseInt( mainPage.swiperPaginationTotal.getText());
        System.out.println(countSlide);
        for (int i=0; i<countSlide; i++){
            System.out.println(mainPage.swiperPaginationCurrent.getText());
            String imgName=(mainPage.sliderImg.getAttribute("src"));
            System.out.println(imgName);
            mainPage.swiperButtonNext.click();
            wait(2);
        }

    }

    /*
    Футер
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Футер")
    @Description("Содержет элементы: меню, блок подписки")
    @Test(description="Содержет элементы: меню, блок подписки")
    public void testFooter() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        moveTo(pages.footer,"футер");
        waitVisibilityElement(pages.aboutCompanyFooterMenu,"меню 'О КОМПАНИИ' в Футере");
        waitVisibilityElement(pages.onlineShoppingFooterMenu,"меню 'УСЛУГИ' в Футере");
        waitVisibilityElement(pages.servicesFooterMenu,"меню 'ОНЛАЙН-ПОКУПКИ' в Футере");
        waitVisibilityElement(pages.subscriptionBlockFooterMenu,"Блок подписки в Футере");
    }
}

