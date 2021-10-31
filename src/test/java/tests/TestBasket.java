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
     */
    public void testCategory() throws InterruptedException {
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

        Pages.goToBasket();
        waitVisibilityElement(BasketPage.basketNonEmptyPage);
        Thread.sleep(2000);
        int totalPrice = basketPage.totalPrice();
        for (int i = 0; i < BasketPage.countProductsForTest; i++) {
            Assert.assertTrue(cartItemPrice.contains(BasketPage.getPriceOfProductInCart()));
            Assert.assertTrue(cartItemName.contains(BasketPage.nameOfProductInCart()));
            Assert.assertTrue(cartItemColor.contains(BasketPage.colorOfProductInCart()));
            Assert.assertTrue(cartItemSize.contains(BasketPage.sizeOfProductInCart()));
            BasketPage.setBasketItemRemove();
        }
        int sumInCarts = 0;
        for (int i = 0; i < cartItemPrice.size(); i++) {
            sumInCarts += Integer.parseInt(cartItemPrice.get(i));
        }
        Assert.assertEquals(sumInCarts, totalPrice);
        waitVisibilityElement(basketPage.basketEmptyPage);
        waitVisibilityElement(basketPage.basketEmptyHeader);
        BasketPage.setBasketClose();
    }
}
