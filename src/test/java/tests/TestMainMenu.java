package tests;

import common.Settings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class TestMainMenu extends Settings {
    /*
    Проверка элементов главного меню.
     */
    @Test
    public void testMenu() {
        open("https://lgcity.ru");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")));
        int menuItems = driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")).size() - 1;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        String randomMenuItem =
                driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav" + "-list']/div/a)[" + randomItem + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)[" + randomItem + "]")).click();
        String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
        Assert.assertTrue(randomPageHeader.contains(randomMenuItem));
    }


}

