import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        driver.findElement(By.id("header-title-user-location")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        driver.findElement(By.id("input-user-locate")).clear();
        driver.findElement(By.id("input-user-locate")).sendKeys(city);
        driver.findElement(By.id("btn-save-user-locate")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        String cityInHeader = driver.findElement(By.id("header-title-user-location")).getText();
        Assert.assertEquals(city, cityInHeader);
    }

    /*
    Проверка выбора города из выпадающего списка
     */
    @Test
    public void setPopularCity() {
        open("https://lgcity.ru");
        driver.findElement(By.id("header-title-user-location")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        int popularCityAmount =driver.findElements(By.xpath("//div[@class='locate__popular-list']/a")).size();
        Random random =new Random();
        int randomNumber = random.nextInt(popularCityAmount)+1;
        driver.findElement(By.xpath("(//div[@class='locate__popular-list']/a)["+randomNumber+"]")).click();
        String popularCity = driver.findElement(By.xpath("(//div[@class='locate__popular-list']/a)["+randomNumber+"]")).getText();
        String attr = driver.findElement(By.id("input-user-locate")).getAttribute("value");
        Assert.assertEquals(popularCity, attr);
        driver.findElement(By.id("btn-save-user-locate")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город']/../..")));
        String cityInHeader = driver.findElement(By.id("header-title-user-location")).getText();
        Assert.assertEquals(popularCity, cityInHeader);
    }

    /*
    Проверка работоспособности крестика
     */
    @Test
    public void closePopup() {
        String city = "Омск";
        open("https://lgcity.ru");
        String cityInHeader = driver.findElement(By.id("header-title-user-location")).getText();
        driver.findElement(By.id("header-title-user-location")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        driver.findElement(By.id("input-user-locate")).clear();
        driver.findElement(By.id("input-user-locate")).sendKeys(city);
        driver.findElement(By.xpath("//div[@class='locate__title-icon-box js-popup-close']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        Assert.assertNotEquals(cityInHeader, city);
    }

    /*
    Проверка автоматического определения города
     */
    @Test
    public void automaticSetCity() {

        open("https://lgcity.ru");
        String city = "Воронеж";
        driver.findElement(By.id("header-title-user-location")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        driver.findElement(By.id("input-user-locate")).clear();
        driver.findElement(By.id("input-user-locate")).sendKeys(city);
        driver.findElement(By.id("btn-save-user-locate")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        String cityInHeader = driver.findElement(By.id("header-title-user-location")).getText();
        Assert.assertEquals(city, cityInHeader);
        driver.findElement(By.id("header-title-user-location")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        driver.findElement(By.id("input-user-locate")).clear();
        driver.findElement(By.xpath("//a[@class='locate__input-auto']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("input-user-locate"), "Москва"));
        driver.findElement(By.xpath("//a[@class='button button--fill locate__button']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(text(),'Укажите свой город')]/../..")));
        cityInHeader = driver.findElement(By.id("header-title-user-location")).getText();
        Assert.assertNotEquals(cityInHeader, city);
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
        System.out.println("Выпадающий список городов: "+city);
    }
}
