package tests;

import common.Settings;
import common.TestData;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.ArrayList;

/*
Проверка "УКАЖИТЕ СВОЙ ГОРОД"
 */
public class TestCityLgCity extends Settings {
    /*
    Проверка ввода названия города.
     */
    @Test
        public void setCity() throws InterruptedException {
        String city = "Омск";
        open(TestData.getProperty("baseUrl"));
        cityPage.selectCity(city);
        String getHeaderCity= cityPage.getHeaderCity();
        Assert.assertEquals(city, getHeaderCity, "Введеный текст: "+city+"; Текст иконки открытия попапа 'Укажите свой город': "+getHeaderCity);
    }

    /*
    Проверка выбора города из выпадающего списка
     */
    @Test
    public void setPopularCity() throws InterruptedException {
        open(TestData.getProperty("baseUrl"));
        cityPage.clickIconSetCity();
        int popularCityAmount = getElementsByXpath(cityPage.popularCityList).size();
        ArrayList<String> list = cityPage.getRandomCity(popularCityAmount);
        Assert.assertEquals(list.get(0), list.get(1), "Выбранный населенный пункт: "+list.get(0)+"; Населенный пункт из списка:" +list.get(1));
        cityPage.clickBtnSaveUserLocate();
        String getHeaderCity= cityPage.getHeaderCity();
        Assert.assertEquals(list.get(0), getHeaderCity,"Выбранный населенный пункт: "+list.get(0)+"; Текст иконки открытия попапа 'Укажите свой город': "+getHeaderCity);
    }

    /*
    Проверка работоспособности крестика
     */
    @Test
    public void closePopup() throws InterruptedException {
        String city = "Омск";
        open(TestData.getProperty("baseUrl"));
        String cityInHeader = cityPage.getHeaderCity();
        cityPage.clickIconSetCity();
        cityPage.inputUserLocate.clear();
        sendString(cityPage.inputUserLocate,city);
        cityPage.clickIconBoxPopupClose();
        Assert.assertNotEquals(cityInHeader, city, "Текст иконки открытия попапа 'Укажите свой город': "+cityPage.getHeaderCity() + "; Введеный текст: "+city);
    }

    /*
    Проверка автоматического определения города
     */
    @Test
    public void automaticSetCity() throws InterruptedException {
        open(TestData.getProperty("baseUrl"));
        String city = "Воронеж";
        String autoCity = "Москва";
        cityPage.selectCity(city);
        String getHeaderCity=cityPage.getHeaderCity();
        Assert.assertEquals( city, getHeaderCity,"Город в шапке и выбранный город в попапе не совпали");
        cityPage.setAutoCity(autoCity);
        Assert.assertNotEquals(getHeaderCity, city,"Текст иконки открытия попапа 'Укажите свой город': "+getHeaderCity + "; Населенный пункт для сравнения: "+city);
    }

    /*
    Проверка заполнения выпадающего списка популярных городов
     */
    @Test
    public void popularCityList() throws InterruptedException {
        open(TestData.getProperty("baseUrl"));
        cityPage.clickIconSetCity();
         int popularCityAmount = getElementsByXpath(cityPage.popularCityList).size();
        Assert.assertTrue(popularCityAmount > 0,"Список популярных городов пуст");
        Assert.assertTrue(cityPage.checkPopularCityList(popularCityAmount),"В списке отстутствует город 'Москва'");

    }
}
