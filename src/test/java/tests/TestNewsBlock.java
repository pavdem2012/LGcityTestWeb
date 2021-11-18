package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;


public class TestNewsBlock extends Settings {
    /*
    Проверка "Новостные баннеры"
     */
    @Description(value = "Проверка перехода по 'Новостные баннеры'")
    @Epic(value = "Главная страница")
    @Feature(value = "Блок 'Новостные баннеры'")
    @Test(description="Проверка перехода по 'Новостные баннеры'")
    public void setNews() throws InterruptedException {
        open("https://lgcity.ru");
        mainPage.scrollToNews();
        waitVisibilityElement(mainPage.newsItemBlock,",блока новостей");
        mainPage.randomNews();

    }
    /*    *//*
    Проверка "Шапка сайта"
     *//*
     *//*+
    Проверка иконки поиска
     *//*
    @Test
    public void SiteHeader() throws InterruptedException {
        open("https://lgcity.ru");
            driver.findElement(By.xpath("//a[@class='header__r-icons-link header__r-icons-link--search js-popup']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Что вы хотите найти?')]")));
        driver.findElement(By.className("header__r-icon--close")).click();
        Thread.sleep(3000);
    }
        *//*
        Проверка иконок Корзины и избранного
         *//*
    @Test
    public void favBasket(){
        open("https://lgcity.ru");
        driver.findElement(By.xpath("//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("lk__title")));
        driver.findElement(By.xpath("//a[contains(text(),'Перейти на главную')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header__logo-link")));
        driver.findElement(By.xpath("//a[@class='header__r-icons-link js-popup js-header-basket']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lgcity-basket")));
        driver.findElement(By.xpath("//div[@class='basket__header-icon-box js-popup-close']")).click();
        driver.findElement(By.className("header__logo-link")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header__logo-link")));
        driver.findElement(By.xpath("//a[@class='header__logo-link']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Личный кабинет')]")));
        driver.findElement(By.xpath("//div[@class='login__title-icon-box js-popup-close']")).click();
    }
    @Test
    public void fav() throws InterruptedException {

        open("https://lgcity.ru");
        driver.findElement(By.xpath("//a[@class='header__r-icons-link js-popup js-header-basket']")).click();
        Thread.sleep(3000);
    }*/
}
