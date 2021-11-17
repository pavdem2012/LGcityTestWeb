package tests;

import common.Settings;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class TestSearch extends Settings {
    @Epic(value = "Главная страница")
    @Feature(value = "Строка поиска")
    @Description("Поиск по наименованию товара")
    @Test
    public void SearchByName() throws InterruptedException {
        open("https://lgcity.ru");
        driver.findElement(By.xpath("//div[@class='header__right-side-icons']/a[@data-popup='popup--search']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header__search popup popup--search js-popup-open']")));
        driver.findElement(By.name("q")).sendKeys("Футболка");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Найдено')]")));
        int searchProducts = driver.findElements(By.className("catalog__item-desc")).size();
        Random random = new Random();
        int randomSearchProduct=random.nextInt(searchProducts)+1;
        String randomProduct = driver.findElement(By.xpath("(//div[@class='catalog__item-desc'])["+randomSearchProduct+"]")).getText().toLowerCase();
        driver.findElement(By.xpath("//div[@class='header__right-side-icons']/a[@data-popup='popup--search']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header__search popup popup--search js-popup-open']")));
        String inputProduct = driver.findElement(By.xpath("//div[@class='input input--default header__search-input autocomplete is-focused']/input")).getAttribute("value").toLowerCase();
        Assert.assertTrue(randomProduct.contains(inputProduct));
    }
}
