package tests;

import common.Settings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CityPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Проверка "УКАЖИТЕ СВОЙ ГОРОД"
 */
public class TestCityLgCity extends Settings {
    /*
    Проверка ввода названия города.
     */
    @Test
    public void setCity() {
        String city = "Омск";
        open("https://lgcity.ru");
        cityPage.selectCity(city);
        Assert.assertEquals(city, cityPage.getHeaderCity());
    }

    /*
    Проверка выбора города из выпадающего списка
     */
    @Test
    public void setPopularCity() throws InterruptedException {
        open("https://lgcity.ru");
        cityPage.iconSetSity.click();
        waitVisibilityElement(cityPage.popupSetSity);
        int popularCityAmount = getElementsByXpath(cityPage.popularCityList).size();
        ArrayList<String> list = cityPage.getRandomCity(popularCityAmount);
        Assert.assertEquals(list.get(0), list.get(1));
        cityPage.btnSaveUserLocate.click();
        waitInvisibilityElement(cityPage.popupSetSity);
        Thread.sleep(1000);
        Assert.assertEquals(list.get(0), cityPage.getHeaderCity());
    }

    /*
    Проверка работоспособности крестика
     */
    @Test
    public void closePopup() {
        String city = "Омск";
        open("https://lgcity.ru");
        String cityInHeader = cityPage.getHeaderCity();
        cityPage.iconSetSity.click();
        waitVisibilityElement(cityPage.popupSetSity);
        cityPage.inputUserLocate.clear();
        cityPage.inputUserLocate.sendKeys(city);
        cityPage.iconBoxPopupClose.click();
        waitInvisibilityElement(cityPage.popupSetSity);
        Assert.assertNotEquals(cityInHeader, city);
    }

    /*
    Проверка автоматического определения города
     */
    @Test
    public void automaticSetCity() {
        open("https://lgcity.ru");
        String city = "Воронеж";
        String autoCity = "Москва";
        cityPage.selectCity(city);
        Assert.assertEquals("Город в шапке и выбранный город в попапе не совпали", city, cityPage.getHeaderCity());
        cityPage.setAutoCity(autoCity);
        cityPage.btnSaveUserLocate.click();
        waitInvisibilityElement(cityPage.popupSetSity);
        Assert.assertNotEquals(cityPage.getHeaderCity(), city);
    }

    /*
    Проверка заполнения выпадающего списка популярных городов
     */
    @Test
    public void popularCityList() throws InterruptedException {
        open("https://lgcity.ru");
        driver.findElement(By.id("header-title-user-location")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        int count = driver.findElements(By.xpath("//a[@class='locate__popular-list-item']")).size();
        Assert.assertTrue(count > 0);
        List<String> cityList = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            cityList.add(driver.findElement(By.xpath("(//a[@class='locate__popular-list-item'])[" + i + "]")).getText());
        }
        boolean city = cityList.contains("Москва");
        System.out.println("Выпадающий список городов: " + city);
    }
}
