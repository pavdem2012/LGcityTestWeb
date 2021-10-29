package tests;

import common.Settings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
        driver.findElement(By.id("confirm-use-cookies")).click();
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

    /*
    Проверка вложеных элементов главного меню и добавления/удаления товаров корзины
     */
    @Test
    public void testCategory() throws InterruptedException {
        open("https://lgcity.ru");
        driver.findElement(By.id("confirm-use-cookies")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")));
        ArrayList<String> cartItemPrice = new ArrayList<>();
        ArrayList<String> cartItemSize = new ArrayList<>();
        ArrayList<String> cartItemName = new ArrayList<>();
        ArrayList<String> cartItemColor = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")));
            int menuItems =
                    driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")).size() - 4;
            Random random = new Random();
            int randomItem = random.nextInt(menuItems) + 1;
            Actions actions = new Actions(driver);

            actions.moveToElement(driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)[" + randomItem + "]"))).perform();
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header__drop header__drop--clothing']"))); //!
            int menuItemsInner =
                    driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a")).size();
            int randomInner = random.nextInt(menuItemsInner) + 1;
            String randomItemInner = driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class" +
                    "='header__nav" + "-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).getText().toLowerCase();
            driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
            String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
            Assert.assertTrue(randomPageHeader.contains(randomItemInner));

            driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='catalog__list']")));
            int catalogItems =
                    driver.findElements(By.xpath("//a[@class='catalog__item catalog__item--new owox-catalog-list']")).size();
            int randomCatalogItem = random.nextInt(catalogItems) + 1;
            driver.findElement(By.xpath("(//a[@class='catalog__item catalog__item--new owox-catalog-list'])[" + randomCatalogItem + "]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card']")));

            String[] nameOfProduct =
                    driver.findElement(By.xpath("//div[@class='card__info-title']")).getText().toLowerCase().replaceAll(" ","").split("\n");
            cartItemName.add(nameOfProduct[0]);

            //System.out.println(nameOfProduct[0]);//!
            cartItemColor.add(driver.findElement(By.xpath("//div[@class='card__color-value']")).getText().toLowerCase());
            driver.findElement(By.xpath("//input[@class='js-select-input select__input']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='select__drop " +
                    "select__drop--sizes']")));
            cartItemSize.add(driver.findElement(By.xpath("//ul[@id='offer-sizes']/li[contains(@class, 'selected')]")).getAttribute("data-size").toLowerCase());
            driver.findElement(By.xpath("//div[@id='offer-price']/div")).click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='select__drop select__drop--sizes']")));
            //String sizeOfProduct = driver.findElement(By.xpath("//ul[@id='offer-sizes']/li[contains(@class,'selected')]")).getAttribute("data-size").toLowerCase();
            cartItemPrice.add(driver.findElement(By.xpath("//div[@id='offer-price']/div")).getText().replaceAll(" ","").replaceAll("₽", ""));
            //String priceOfProduct = driver.findElement(By.xpath("//div[@id='offer-price']/div")).getText().replaceAll(" ", "").replaceAll("₽", "");

            driver.findElement(By.id("btn-add-to-cart")).click();
            driver.findElement(By.xpath("//a[@class='header__logo-link']")).click();
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-counter__value' and " + "contains (text(),'"+i+"')]"))); //!!!
        }
        //System.out.println(cartItemName);

        driver.findElement(By.xpath("//a[@class='header__r-icons-link js-popup js-header-basket']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket__non-empty']")));
        Thread.sleep(2000);
        int totalPrice = Integer.parseInt(driver.findElement(By.id("basket-total-price")).getText().replaceAll(" ",
                ""));

        for (int i = 0; i < 2; i++) {
            String priceOfProductInCart =
                    driver.findElement(By.xpath("//div[@class='basket__item-price']/div")).getText().replaceAll(" ","").replaceAll("₽", "");
            Assert.assertTrue(cartItemPrice.contains(priceOfProductInCart));
            String nameOfProductInCart = driver.findElement(By.xpath("//div[@class='basket__item-title']")).getText().toLowerCase().replaceAll("[^\\da-zA-Z]", "");
            //System.out.println(nameOfProductInCart);
            Assert.assertTrue(cartItemName.contains(nameOfProductInCart));
            String colorOfProductInCart= driver.findElement(By.xpath("//div[@class='basket__item-props']/div[(contains(text(), 'Цвет:'))]")).getText().toLowerCase().replaceAll("цвет:","").replaceAll(" ","");
            Assert.assertTrue(cartItemColor.contains(colorOfProductInCart));
            String sizeOfProductInCart = driver.findElement(By.xpath("//div[@class='basket__item-prop']")).getText().replaceAll(" ", "").toLowerCase().replaceAll("размер:", "");
            Assert.assertTrue(cartItemSize.contains(sizeOfProductInCart));
            driver.findElement(By.xpath("//div[@class='basket__item-remove js-basket-remove-item']")).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"basket__empty\"]")));
        int sumInCarts = 0;

        for (int i = 0; i<cartItemPrice.size(); i++){
            sumInCarts += Integer.parseInt(cartItemPrice.get(i));
        }
        //System.out.println(sumInCarts);
        //System.out.println(totalPrice);
        Assert.assertEquals(sumInCarts,totalPrice);


        driver.findElement(By.xpath("//div[@class='basket__header-icon-box js-popup-close']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'basket__empty-title')]")));

    }
    @Test
    /*
    Проверка добавления в "Избранное" из карточки товара
     */
    public  void addFavoritesFromCartOfProduct() throws InterruptedException {
        open("https://lgcity.ru");
        ArrayList<String> cartItemName = new ArrayList<>();
        driver.findElement(By.id("confirm-use-cookies")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")));
        int menuItems =
                driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")).size() - 4;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)[" + randomItem + "]"))).perform();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header__drop header__drop--clothing']"))); //!
        int menuItemsInner =
                driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a")).size();
        int randomInner = random.nextInt(menuItemsInner) + 1;
        String randomItemInner = driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class" +
                "='header__nav" + "-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
        String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
        Assert.assertTrue(randomPageHeader.contains(randomItemInner));
        driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='catalog__list']")));
        int catalogItems =
                driver.findElements(By.xpath("//a[@class='catalog__item catalog__item--new owox-catalog-list']")).size();
        int randomCatalogItem = random.nextInt(catalogItems) + 1;
        driver.findElement(By.xpath("(//a[@class='catalog__item catalog__item--new owox-catalog-list'])[" + randomCatalogItem + "]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card']")));
        String[] nameOfProduct =
                driver.findElement(By.xpath("//div[@class='card__info-title']")).getText().toLowerCase().replaceAll(" ","").split("\n");
        cartItemName.add(nameOfProduct[0]);
        driver.findElement(By.xpath("//div[contains(text(),'Добавить в избранное')]")).click();

        driver.findElement(By.xpath("//input[@class='favorite__input']")).isEnabled();

        //System.out.println(cartItemName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-counter__value' and contains (text(),'1')]")));
        driver.findElement(By.xpath("//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"lk__favorites\"]")));
        String nameOfProductInFavorites = driver.findElement(By.xpath("//div[@class='catalog__item-title']")).getText().toLowerCase().replaceAll("[^\\da-zA-Z]", "");
        //System.out.println(nameOfProductInFavorites);
        Assert.assertTrue(cartItemName.contains(nameOfProductInFavorites));
        driver.findElement(By.xpath("//div[@class='favorite__button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='empty-content__title' and contains (text(),'Список понравившихся товаров пуст')]")));
        driver.findElement(By.xpath("//a[@class='button button--fill' and contains (text(),'Перейти на главную')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-counter__value' and contains (text(),'0')]")));
        //Thread.sleep(2000);
    }
    @Test
    /*
    Проверка добавления в "Избранное" из каталога
     */
    public void addFavoritesFromCatalogue () throws InterruptedException {
        open("https://lgcity.ru");
        //ArrayList<String> cartItemName = new ArrayList<>();
        driver.findElement(By.id("confirm-use-cookies")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("confirm-use-cookies")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")));
        int menuItems =
                driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")).size() - 4;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)[" + randomItem + "]"))).perform();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header__drop header__drop--clothing']"))); //!
        int menuItemsInner =
                driver.findElements(By.xpath("//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a")).size();
        int randomInner = random.nextInt(menuItemsInner) + 1;
        String randomItemInner = driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class" +
                "='header__nav" + "-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).getText().toLowerCase();
        driver.findElement(By.xpath("(//div[@id='gmenu-tab-327']//div[@class='header__nav-list-item'][" + randomItem + "]//div[@class='header__drop-inner']/div[@class='header__drop-category-col']//a)[" + randomInner + "]")).click();
        String randomPageHeader = driver.findElement(By.tagName("h1")).getText().toLowerCase();
        Assert.assertTrue(randomPageHeader.contains(randomItemInner));
        driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_DOWN);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='catalog__list']")));
        int catalogIconsToFavorites =
                driver.findElements(By.xpath("//input[@class='favorite__input input-add-to-favorite']")).size();
        //System.out.println("количество в каталоге: " + catalogIconsToFavorites);
        int randomCatalogIconsToFavorites = random.nextInt(catalogIconsToFavorites) + 1;
        String nameOfItemInCatalogue = driver.findElement(By.xpath("(//div[@class='catalog__item-title'])[" + randomCatalogIconsToFavorites + "]")).getText().toLowerCase().replaceAll(" ","");
        driver.findElement(By.xpath("(//div[@class='favorite__button btn-add-to-favorite owox-add-to-favorite-listing checked'])[" + randomCatalogIconsToFavorites + "]")).click();

        System.out.println("имя в каталоге: " +nameOfItemInCatalogue);
        //driver.findElement(By.xpath("//body")).sendKeys(Keys.PAGE_UP);

        driver.findElement(By.xpath("//input[@class='favorite__input input-add-to-favorite']")).isEnabled();

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='wrapper']"))).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-counter__value' and contains (text(),'1')]")));

        driver.findElement(By.xpath("//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")).click();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='lk__favorites']")));
        String nameOfProductInFavorites = driver.findElement(By.xpath("//div[@class='catalog__item-title']")).getText().toLowerCase().replaceAll("[^\\da-zA-Z]", "");
        System.out.println("имя в избранном: " + nameOfProductInFavorites);
        Assert.assertTrue(nameOfItemInCatalogue.contains(nameOfProductInFavorites));
        driver.findElement(By.xpath("//div[@class='favorite__button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='empty-content__title' and contains (text(),'Список понравившихся товаров пуст')]")));
        driver.findElement(By.xpath("//a[@class='button button--fill' and contains (text(),'Перейти на главную')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='basket-counter__value' and contains (text(),'0')]")));
        //Thread.sleep(2000);
    }
}

