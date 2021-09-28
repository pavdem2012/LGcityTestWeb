import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.util.Random;

public class TestMainMenu extends Settings {
    /*
    Проверка элементов главного меню.
     */
    @Test
    public void testMenu() {
        open("https://lgcity.ru");
        int menuItems = driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div"
                + "/a")).size() - 1;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        String randomMenuItem =
                driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav" + "-list']/div/a)" +
                        "[" + randomItem + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)[" + randomItem + "]")).click();
        String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
        Assert.assertTrue(randomPageHeader.contains(randomMenuItem));
    }

    /*
    Проверка вложеных элементов главного меню
     */
    @Test
    public void testCategory() {
        open("https://lgcity.ru");
        int menuItems = driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div"
                + "/a")).size() - 4;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list"
                + "']/div/a)[" + randomItem + "]"))).perform();
        int menuItemsInner =
                driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list" + "-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col" + "']//a")).size();
        int randomInner = random.nextInt(menuItemsInner) + 1;
        String randomItemInner =
                driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav" + "-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category" + "-col']//a)[" + randomInner + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
        String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
        Assert.assertTrue(randomPageHeader.contains(randomItemInner));
    }
}

