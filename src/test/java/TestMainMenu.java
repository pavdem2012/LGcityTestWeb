import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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
    public void testCategory() throws InterruptedException {
        open("https://lgcity.ru");
        driver.findElement(By.id("confirm-use-cookies")).click();
        ArrayList<String> cartItemInfo = new ArrayList<>();
        ArrayList<String> inCartItemInfo = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            int menuItems = driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div" + "/a")).size() - 4;
            Random random = new Random();
            int randomItem = random.nextInt(menuItems) + 1;
            Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list" + "']/div/a)[" + randomItem + "]"))).perform();
            int menuItemsInner = driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list" + "-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col" + "']//a")).size();
            int randomInner = random.nextInt(menuItemsInner) + 1;
            String randomItemInner = driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav" + "-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category" + "-col']//a)[" + randomInner + "]")).getText().toLowerCase();
            driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
            String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
            Assert.assertTrue(randomPageHeader.contains(randomItemInner));

            int catalogItems = driver.findElements(By.xpath("//a[@class='catalog__item catalog__item--new " + "owox-catalog-list']")).size();
            System.out.println("Длина меню: " + catalogItems);
            int randomCatalogItem = random.nextInt(catalogItems) + 1;
            driver.findElement(By.xpath("(//a[@class='catalog__item catalog__item--new owox-catalog-list'])[" + randomCatalogItem + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card']")));
            cartItemInfo.add(driver.findElement(By.xpath("//div[@class='card__info-title']")).getText().toLowerCase());
            String nameOfProduct = driver.findElement(By.xpath("//div[@class='card__info-title']")).getText().toLowerCase();
            driver.findElement(By.xpath("//input[@class='js-select-input select__input']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select__drop select__drop--sizes']")));
            cartItemInfo.add(driver.findElement(By.xpath("//ul[@id='offer-sizes']/li[contains(@class, 'selected')]")).getAttribute("data-size").toLowerCase());
            String sizeOfProduct = driver.findElement(By.xpath("//ul[@id='offer-sizes']/li[contains(@class, 'selected')]")).getAttribute("data-size").toLowerCase();
            cartItemInfo.add(driver.findElement(By.xpath("//div[@id='offer-price']/div")).getText().replaceAll(" ", ""));
            String priceOfProduct = driver.findElement(By.xpath("//div[@id='offer-price']/div")).getText().replaceAll(" ", "");
            driver.findElement(By.xpath("//div[@id='offer-price']/div")).click();
            System.out.println("цена: " + priceOfProduct);
            System.out.println("название: " + nameOfProduct);
            System.out.println("размер: " + sizeOfProduct);
            driver.findElement(By.id("btn-add-to-cart")).click();
            driver.findElement(By.xpath("//a[@class='header__logo-link']")).click();
        }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-counter__value' and contains (text(),'2')]")));
        driver.findElement(By.xpath("//a[@class='header__r-icons-link js-popup js-header-basket']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket__non-empty']")));
        for (int i = 0; i < 2; i++) {


            inCartItemInfo.add(driver.findElement(By.xpath("//div[@class='basket__item-price']/div")).getText().replaceAll(" ", ""));
            String priceOfProductInCart = driver.findElement(By.xpath("//div[@class='basket__item-price']/div")).getText().replaceAll(" ", "");
            inCartItemInfo.add(driver.findElement(By.xpath("//div[@class='basket__item-title']")).getText().toLowerCase());
            String nameOfProductInCart = driver.findElement(By.xpath("//div[@class='basket__item-title']")).getText().toLowerCase();
            inCartItemInfo.add(driver.findElement(By.xpath("//div[@class='basket__item-prop']")).getText().replaceAll(" ", "").toLowerCase());
            String sizeOfProductInCart = driver.findElement(By.xpath("//div[@class='basket__item-prop']")).getText().replaceAll(" ", "").toLowerCase();
            System.out.println("цена в корзине: " + priceOfProductInCart);
            System.out.println("имя в корзине: " + nameOfProductInCart);
            System.out.println("размер в корзине: " + sizeOfProductInCart);
        }
        System.out.println("список: " + cartItemInfo);
        System.out.println("список в корзине: " + inCartItemInfo);
        /*Assert.assertTrue(priceOfProduct.contains(priceOfProductInCart));
        Assert.assertTrue(nameOfProduct.contains(nameOfProductInCart));
        Assert.assertTrue(sizeOfProductInCart.contains(sizeOfProduct));*/
        for (int i = 0; i < 2; i++) {
            driver.findElement(By.xpath("//div[@class='basket__item-remove js-basket-remove-item']")).click();
        }
        driver.findElement(By.xpath("//div[@class='basket__header-icon-box js-popup-close']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'basket__empty-title')]")));
        Thread.sleep(3000);


    }
}

