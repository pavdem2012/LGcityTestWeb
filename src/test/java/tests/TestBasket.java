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
    Проверка вложеных элементов главного меню и добавления/удаления товаров корзины из карточки товара
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
            //System.out.println("Итерация "+ (i+1)+": Заголовок страницы: " + favoritePage.getTitle() + "; Заголовок меню: " + randomMenuItem);
            Assert.assertTrue("Итерация "+ (i+1)+": Заголовок страницы каталога: " + favoritePage.getTitle() + "; Заголовок меню: " + randomMenuItem,favoritePage.getTitle().contains(randomMenuItem));

            sendKeysToBody(Keys.PAGE_DOWN);
            waitVisibilityElement(favoritePage.catalogListBlock);
            favoritePage.selectRandomCard();
            System.out.println("Итерация "+ (i+1)+": URL карточки товара: " + getUrl());
            waitVisibilityElement(favoritePage.cardPage);
            cartItemName.add(favoritePage.getCardTitle());
            cartItemColor.add(cartProductPage.getColorCartItem());
            cartItemSize.add(cartProductPage.getSizeCartItem());
            cartItemPrice.add(cartProductPage.getPriceCartItem());
            wait(1);
            CardProductPage.addProductToBasket();
            pages.goToMainPage();
        }

        Pages.goToBasket();
        waitVisibilityElement(BasketPage.basketNonEmptyPage);
        Thread.sleep(2000);
        int totalPrice = basketPage.totalPrice();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            //System.out.println("Итерация "+ (i+1)+": Цена в карточке: " + cartItemPrice + ", Цена в корзине: " + basketPage.getPriceOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Цена в карточке: " + cartItemPrice + ", Цена в корзине: " + basketPage.getPriceOfProductInCart(), cartItemPrice.contains(basketPage.getPriceOfProductInCart()));
            //System.out.println("Итерация "+ (i+1)+": Название в карточке: " + cartItemName + ", Название в корзине: " + basketPage.nameOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Название в карточке: " + cartItemName + ", Название в корзине: " + basketPage.nameOfProductInCart(), cartItemName.contains(basketPage.nameOfProductInCart()));
            //System.out.println("Итерация "+ (i+1)+": Цвет в карточке: " + cartItemColor + ", Цвет в корзине: " + basketPage.colorOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Цвет в карточке: " + cartItemColor + ", Цвет в корзине: " +  basketPage.colorOfProductInCart(), cartItemColor.contains(basketPage.colorOfProductInCart()));
            //System.out.println("Итерация "+ (i+1)+": Размер в карточке: " + cartItemSize + ", Размер в корзине: " + basketPage.sizeOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Размер в карточке: " + cartItemSize + ", Размер в корзине: " + basketPage.sizeOfProductInCart(), cartItemSize.contains(basketPage.sizeOfProductInCart()));

            basketPage.setBasketItemRemove();
        }
        int sumInCarts = 0;
        for (int i = 0; i < cartItemPrice.size(); i++) {
            sumInCarts += Integer.parseInt(cartItemPrice.get(i));
        }
        Assert.assertEquals("Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине: " + totalPrice, sumInCarts, totalPrice);
        //System.out.println("Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине: " + totalPrice);
        waitVisibilityElement(basketPage.basketEmptyPage);
        waitVisibilityElement(basketPage.basketEmptyHeader);
        basketPage.setBasketClose();
    }
    @Test
    /*
    Проверка вложеных элементов главного меню и добавления/удаления товаров корзины из каталога товаров
     */
    public void testAddToBasketFromListing () throws InterruptedException {
        open("https://lgcity.ru");

        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            waitVisibilityElement(favoritePage.menuItem);
            favoritePage.selectRandomMenu();
            String randomMenuItem = favoritePage.selectRandomMenuItem();
            System.out.println("Итерация "+ (i+1)+": Заголовок страницы: "+ favoritePage.getTitle() + "; Заголовок меню: " + randomMenuItem + "; URL: "+getUrl());
            //Assert.assertTrue("Итерация "+ (i+1)+": Заголовок страницы каталога: " + favoritePage.getTitle() + "; Заголовок меню: " + randomMenuItem,favoritePage.getTitle().contains(randomMenuItem));
//            sendKeysToBody(Keys.PAGE_DOWN);
            waitVisibilityElement(favoritePage.catalogListBlock);
            catalogListPage.selectQuickBuyList();
        }
        //System.out.println(catalogListPage.cartItemPrice);
        //System.out.println(catalogListPage.cartItemSize);
        //System.out.println(catalogListPage.cartItemName);
        Pages.goToBasket();
        waitVisibilityElement(BasketPage.basketNonEmptyPage);
        Thread.sleep(2000);
        int totalPrice = basketPage.totalPrice();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            //System.out.println("Итерация "+ (i+1)+": Цена в карточке: " + cartItemPrice + ", Цена в корзине: " + basketPage.getPriceOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Цена в карточке: " + catalogListPage.cartItemPrice + ", Цена в корзине: " + basketPage.getPriceOfProductInCart(), catalogListPage.cartItemPrice.contains(basketPage.getPriceOfProductInCart()));
            //System.out.println("Итерация "+ (i+1)+": Название в карточке: " + cartItemName + ", Название в корзине: " + basketPage.nameOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Название в карточке: " + catalogListPage.cartItemName + ", Название в корзине: " + basketPage.nameOfProductInCart(), catalogListPage.cartItemName.contains(basketPage.nameOfProductInCart()));
            //System.out.println("Итерация "+ (i+1)+": Размер в карточке: " + cartItemSize + ", Размер в корзине: " + basketPage.sizeOfProductInCart());
            Assert.assertTrue("Итерация "+ (i+1)+": Размер в карточке: " + catalogListPage.cartItemSize + ", Размер в корзине: " + basketPage.sizeOfProductInCart(), catalogListPage.cartItemSize.contains(basketPage.sizeOfProductInCart()));

            basketPage.setBasketItemRemove();
        }
        int sumInCarts = 0;
        for (int i = 0; i < catalogListPage.cartItemPrice.size(); i++) {
            sumInCarts += Integer.parseInt(catalogListPage.cartItemPrice.get(i));
        }
        Assert.assertEquals("Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине: " + totalPrice, sumInCarts, totalPrice);
        //System.out.println("Сумма цен из карточек: " + sumInCarts + ", Общая цена в корзине: " + totalPrice);
        waitVisibilityElement(basketPage.basketEmptyPage);
        waitVisibilityElement(basketPage.basketEmptyHeader);
        basketPage.setBasketClose();
    }
}
