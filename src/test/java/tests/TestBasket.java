package tests;


import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasketPage;

import java.util.ArrayList;

public class TestBasket extends Settings {

    @Epic(value = "Корзина")
    @Feature(value = "Проверка добавления и удаления товаров в корзину из карточки товара")
    @Description("Проверка добавления и удаления товаров в корзину из карточки товара")
    @Test(description = "Проверка добавления и удаления товаров в корзину из карточки товара")
        /*
    Проверка вложеных элементов главного меню и добавления/удаления товаров корзины из карточки товара
     */ public void testCategory() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");
        ArrayList<String> cartItemPrice = new ArrayList<>();
        ArrayList<String> cartItemSize = new ArrayList<>();
        ArrayList<String> cartItemName = new ArrayList<>();
        ArrayList<String> cartItemColor = new ArrayList<>();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            waitVisibilityElement(favoritePage.menuItem, "элемент подкатегории товаров");
            favoritePage.selectRandomMenu();
            String randomMenuItem = favoritePage.selectRandomMenuItem();

            Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem), "Итерация " + (i + 1) + ": Заголовок " +
                    "страницы каталога: " + favoritePage.getTitle() + "; Заголовок меню: " + randomMenuItem);
            sendKeysToBody(Keys.PAGE_DOWN);
            waitVisibilityElement(favoritePage.catalogListBlock, "Блок с карточками товаров");
            favoritePage.selectRandomCard();
            waitVisibilityElement(favoritePage.cardPage, "Карточка товара");
            cartItemName.add(favoritePage.getCardTitle());
            cartItemColor.add(cartProductPage.getColorCartItem());
            cartItemSize.add(cartProductPage.getSizeCartItem());
            cartItemPrice.add(cartProductPage.getPriceCartItem());
            wait(1);
            cartProductPage.addProductToBasket();
            pages.goToMainPage();
        }

        pages.goToBasket();
        waitVisibilityElement(BasketPage.basketNonEmptyPage, "Корзина с товарами");
        Thread.sleep(2000);
        int totalPrice = basketPage.totalPrice();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {

            Assert.assertTrue(cartItemPrice.contains(basketPage.getPriceOfProductInCart()), "Итерация " + (i + 1) +
                    ": Цена в карточке: " + cartItemPrice + ", Цена в корзине: " + basketPage.getPriceOfProductInCart());
            Assert.assertTrue(cartItemName.contains(basketPage.nameOfProductInCart()), "Итерация " + (i + 1) + ": " +
                    "Название в карточке: " + cartItemName + ", Название в " + "корзине: " + basketPage.nameOfProductInCart());
            Assert.assertTrue(cartItemColor.contains(basketPage.colorOfProductInCart()), "Итерация " + (i + 1) + ": " +
                    "Цвет в карточке: " + cartItemColor + ", Цвет в корзине: " + basketPage.colorOfProductInCart());
            Assert.assertTrue(cartItemSize.contains(basketPage.sizeOfProductInCart()), "Итерация " + (i + 1) + ": " +
                    "Размер в карточке: " + cartItemSize + ", Размер в корзине: " + basketPage.sizeOfProductInCart());

            basketPage.setBasketItemRemove();
        }
        int sumInCarts = 0;
        for (int i = 0; i < cartItemPrice.size(); i++) {
            sumInCarts += Integer.parseInt(cartItemPrice.get(i));
        }
        Assert.assertEquals(sumInCarts, totalPrice, "Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине:" +
                " " + totalPrice);
        waitVisibilityElement(basketPage.basketEmptyPage, "Пустая корзина");
        waitVisibilityElement(basketPage.basketEmptyHeader, "В корзине нет товаров");
        basketPage.setBasketClose();
    }

    @Epic(value = "Корзина")
    @Feature(value = "Проверка добавления и удаления товаров в корзину из листинга товаров")
    @Description("Проверка добавления и удаления товаров в корзину из листинга товаров")
    @Test(description = "Проверка добавления и удаления товаров в корзину из листинга товаров")
    /*
    Проверка вложеных элементов главного меню и добавления/удаления товаров корзины из каталога товаров
     */ public void testAddToBasketFromListing() throws InterruptedException {
        openWithCloseCookie("https://lgcity.ru");

        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            waitVisibilityElement(favoritePage.menuItem, "элемент подкатегории товаров");
            favoritePage.selectRandomMenu();
            String randomMenuItem = favoritePage.selectRandomMenuItem();

            String getTitle = favoritePage.getTitle();
            Assert.assertTrue(getTitle.contains(randomMenuItem), "Итерация " + (i + 1) + ": Заголовок страницы " +
                    "каталога: " + getTitle + "; Заголовок меню: " + randomMenuItem + "; URL: " + getUrl());

            waitVisibilityElement(favoritePage.catalogListBlock, "Блок с карточками товаров");
            catalogListPage.selectQuickBuyList();
        }
        pages.goToBasket();
        waitVisibilityElement(BasketPage.basketNonEmptyPage, "Корзина с товарами");
        Thread.sleep(2000);
        int totalPrice = basketPage.totalPrice();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {

            Assert.assertTrue(catalogListPage.cartItemPrice.contains(basketPage.getPriceOfProductInCart()), "Итерация" +
                    " " + (i + 1) + ": Цена в карточке: " + catalogListPage.cartItemPrice + ", " + "Цена" + " в " +
                    "корзине: " + basketPage.getPriceOfProductInCart());
            Assert.assertTrue(catalogListPage.cartItemName.contains(basketPage.nameOfProductInCart()),
                    "Итерация " + (i + 1) + ": Название в карточке: " + catalogListPage.cartItemName + ", " +
                            "Название в корзине: " + basketPage.nameOfProductInCart());
            Assert.assertTrue(catalogListPage.cartItemSize.contains(basketPage.sizeOfProductInCart()),
                    "Итерация " + (i + 1) + ": Размер в карточке: " + catalogListPage.cartItemSize + ", " + "Размер в" +
                            " корзине: " + basketPage.sizeOfProductInCart());

            basketPage.setBasketItemRemove();
        }
        int sumInCarts = 0;
        for (int i = 0; i < catalogListPage.cartItemPrice.size(); i++) {
            sumInCarts += Integer.parseInt(catalogListPage.cartItemPrice.get(i));
        }
        Assert.assertEquals(sumInCarts, totalPrice, "Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине:" +
                " " + totalPrice);
        waitVisibilityElement(basketPage.basketEmptyPage, "Пустая корзина");
        waitVisibilityElement(basketPage.basketEmptyHeader, "В корзине нет товаров");
        basketPage.setBasketClose();


    }

}
