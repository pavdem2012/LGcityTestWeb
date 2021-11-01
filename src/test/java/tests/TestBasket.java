package tests;

import common.Pages;
import common.Settings;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import pages.BasketPage;
import pages.CardProductPage;

import java.util.ArrayList;

public class TestBasket extends Settings {


    @Test
        /*
    Проверка вложеных элементов главного меню и добавления/удаления товаров корзины
     */ public void testCategory() throws InterruptedException {
        open("https://lgcity.ru");
        ArrayList<String> cartItemPrice = new ArrayList<>();
        ArrayList<String> cartItemSize = new ArrayList<>();
        ArrayList<String> cartItemName = new ArrayList<>();
        ArrayList<String> cartItemColor = new ArrayList<>();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            waitVisibilityElement(favoritePage.menuItem);
            favoritePage.selectRandomMenu();
            String randomMenuItem = favoritePage.selectRandomMenuItem();
            Assert.assertTrue(favoritePage.getTitle().contains(randomMenuItem));
            System.out.println("Заголовок страницы: " + favoritePage.getTitle() + " Заголовок меню: " + randomMenuItem);
            sendKeysToBody(Keys.PAGE_DOWN);
            waitVisibilityElement(favoritePage.catalogListBlock);
            favoritePage.selectRandomCard();
            waitVisibilityElement(favoritePage.cardPage);
            cartItemPrice.add(cartProductPage.getPriceCartItem());
            cartItemName.add(favoritePage.getCardTitle());
            cartItemColor.add(cartProductPage.getColorCartItem());
            cartItemSize.add(cartProductPage.getSizeCartItem());
            CardProductPage.addProductToBasket();
            pages.goToMainPage();
        }
        System.out.println(cartItemPrice);
        System.out.println(cartItemName);
        System.out.println(cartItemColor);
        System.out.println(cartItemSize);
        Pages.goToBasket();
        waitVisibilityElement(BasketPage.basketNonEmptyPage);
        Thread.sleep(2000);
        int totalPrice = basketPage.totalPrice();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            System.out.println("Цена в карточке: " + cartItemPrice + ", Цена в корзине: " + basketPage.getPriceOfProductInCart());
            Assert.assertTrue(cartItemPrice.contains(basketPage.getPriceOfProductInCart()));
            System.out.println("Название в карточке: " + cartItemName + ", Название в корзине: " + basketPage.nameOfProductInCart());
            Assert.assertTrue(cartItemName.contains(basketPage.nameOfProductInCart()));
            System.out.println("Цвет в карточке: " + cartItemColor + ", Цвет в корзине: " + basketPage.colorOfProductInCart());
            Assert.assertTrue(cartItemColor.contains(basketPage.colorOfProductInCart()));
            System.out.println("Размер в карточке: " + cartItemSize + ", Размер в корзине: " + basketPage.sizeOfProductInCart());
            Assert.assertTrue(cartItemSize.contains(basketPage.sizeOfProductInCart()));

            basketPage.setBasketItemRemove();
        }
        int sumInCarts = 0;
        for (int i = 0; i < cartItemPrice.size(); i++) {
            sumInCarts += Integer.parseInt(cartItemPrice.get(i));
        }
        Assert.assertEquals(sumInCarts, totalPrice);
        System.out.println("Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине: " + totalPrice);
        waitVisibilityElement(basketPage.basketEmptyPage);
        waitVisibilityElement(basketPage.basketEmptyHeader);
        basketPage.setBasketClose();
    }
}
