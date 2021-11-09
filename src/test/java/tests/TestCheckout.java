package tests;

import common.Settings;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pages.BasketPage;
import pages.CardProductPage;
import pages.CheckoutPage;

import java.io.IOException;
import java.util.ArrayList;

public class TestCheckout extends Settings {
    public int totalPrice;

    @Test
    public void testCheckout() throws InterruptedException, IOException {
        open("https://lgcity.ru");
        //ArrayList<String> cartItemPrice = new ArrayList<>();
        waitVisibilityElement(favoritePage.menuItem);
        favoritePage.selectRandomMenu();
        favoritePage.selectRandomMenuItem();
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock);
        catalogListPage.selectQuickBuyList();
        pages.goToMainPage();
        favoritePage.selectRandomMenu();
        favoritePage.selectRandomMenuItem();
        waitVisibilityElement(favoritePage.catalogListBlock);
        favoritePage.selectRandomCard();
        //cartItemPrice.add(cartProductPage.getPriceCartItem());
        wait(1);
        cartProductPage.addProductToBasket();
        wait(2);
        //moveTo(driver.findElement(By.xpath("//div[(contains(text(), 'Поделиться'))]")));
        cartProductPage.goToBasketFromCart();
        waitVisibilityElement(BasketPage.basketNonEmptyPage);
        //Thread.sleep(2000);
        //int totalPrice = basketPage.totalPrice();
        //System.out.println(totalPrice);
        basketPage.clickCheckoutBtn();
        checkoutPage.clickLoginBtn();
        //checkoutPage.loginLinkTogglePassLogin();
        waitVisibilityElement(checkoutPage.loginTitle);
        checkoutPage.loginLinkTogglePassLogin();
        waitVisibilityElement(checkoutPage.eMailInput);
        checkoutPage.Authorization();

        //wait(2);
        waitVisibilityElement(checkoutPage.h1Header);
        //checkoutPage.recipientForm();
        wait(1);
        basketPage.addressField.click();
        basketPage.addressField.sendKeys(checkoutPage.city);
        wait(1);
        basketPage.addressListElement.click();
        wait(1);
        checkoutPage.clickPickUpBtn();
        checkoutPage.clickSelectBtn();
        checkoutPage.selectPickupPoint();
        checkoutPage.assertPrisesInOrder();
        checkoutPage.clickCheckoutButton();
        checkoutPage.chekPaymentPage();
        Thread.sleep(7000);
    }
}
