package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class testMainPage extends Settings {

    /*
    Проверка элементов главного меню.
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Главное меню")
    @Description("Проверка перехода из главного меню в каталог")
    @Test(description = "Проверка перехода из главного меню в каталог")
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
    @Test(description = "Присутствует фавиконка сайта")
    public void faviconPresent() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        String favicon = pages.favicon.getAttribute("href");
        openPage(favicon);
        assertString(getTitle(), "favicon");

    }

    /*
    Слайдер Присутсвует слайдер на главной странице
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Слайдер")
    @Description("Присутсвует слайдер на главной странице")
    @Test(description = "Присутсвует слайдер на главной странице")
    public void sliderPresent() throws InterruptedException {
        openPage("https://lgcity.ru");
        int countSlide = Integer.parseInt(mainPage.swiperPaginationTotal.getText());
        //int numberSlideBefore = Integer.parseInt(mainPage.swiperPaginationCurrent.getText());
        ArrayList<String> list = new ArrayList<>();
        //System.out.println(countSlide);
        for (int i = 0; i < countSlide; i++) {
            //System.out.println("Число до: "+mainPage.swiperPaginationCurrent.getText());
            int numberSlide = Integer.parseInt(mainPage.swiperPaginationCurrent.getText());
            String imgName = (mainPage.sliderImg.getAttribute("src"));
            list.add(imgName);
            //System.out.println("Картинка до: "+imgName);
            mainPage.swiperButtonNext.click();
            //System.out.println("Число после: "+mainPage.swiperPaginationCurrent.getText());
            int numberSlideNext = Integer.parseInt(mainPage.swiperPaginationCurrent.getText());
            wait(2);
            int checkNumber = numberSlide + 1;
            if (numberSlide < countSlide) {
                Assert.assertEquals(numberSlideNext,checkNumber, "Номер слайда: " + numberSlideNext + " Проверочное " +
                        "число: " + checkNumber);
            } else if (numberSlide == countSlide) {
                Assert.assertEquals(numberSlideNext,1, " Пагинатор слайдера не вернулся на первую страницу");
                //System.out.println(" проверочное число: " + (numberSlide + 1));
            }
        }
        //System.out.println(" проверочное число: "+numberSlideAfter);
        String imgNameAfter = (mainPage.slideDuplicate.getAttribute("src"));
        //System.out.println("картинка после: "+imgNameAfter);
        //System.out.println("Первый элемент списка: "+list.get(0));
        Assert.assertTrue(list.get(0).contains(imgNameAfter));

    }

    /*
    Футер
     */
    @Epic(value = "Главная страница")
    @Feature(value = "Футер")
    @Description("Содержет элементы: меню, блок подписки")
    @Test(description = "Содержет элементы: меню, блок подписки")
    public void testFooter() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        moveTo(pages.footer, "футер");
        waitVisibilityElement(pages.aboutCompanyFooterMenu, "меню 'О КОМПАНИИ' в Футере");
        waitVisibilityElement(pages.onlineShoppingFooterMenu, "меню 'УСЛУГИ' в Футере");
        waitVisibilityElement(pages.servicesFooterMenu, "меню 'ОНЛАЙН-ПОКУПКИ' в Футере");
        waitVisibilityElement(pages.subscriptionBlockFooterMenu, "Блок подписки в Футере");
    }
}

