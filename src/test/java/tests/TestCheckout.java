package tests;

import common.Settings;

import io.qameta.allure.Description;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCheckout extends Settings {

    @Description(value = "Проверка оформления заказа, соответствия цен и скидки")
    @Epic(value = "Оформление заказа")
    @Feature(value = "Проверка оформления заказа, соответствия цен и скидки")

    @Test(description="Проверка оформления заказа, соответствия цен и скидки")
    public void testCheckout() throws InterruptedException, IOException {
        openWithCloseCookie("https://lgcity.ru");
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.movieToRandomMenu();
        favoritePage.selectRandomMenuItem();
        sendKeysToBody(Keys.PAGE_DOWN);
        waitVisibilityElement(favoritePage.catalogListBlock,"Блок с карточками товаров");
        catalogListPage.selectQuickBuyList();
        pages.goToMainPage();
        favoritePage.movieToRandomMenu();
        favoritePage.selectRandomMenuItem();
        waitVisibilityElement(favoritePage.catalogListBlock,"Блок с карточками товаров");
        favoritePage.selectRandomCard();
        cartProductPage.addProductToBasket();
        pages.goToBasket();
        basketPage.clickCheckoutBtn();
        checkoutPage.clickLoginBtn();
        checkoutPage.loginLinkTogglePassLogin();
        checkoutPage.Authorization();
        checkoutPage.setDeliveryAddress();
        checkoutPage.clickPickUpBtn();
        checkoutPage.clickSelectBtn();
        checkoutPage.selectPickupPoint();
        checkoutPage.assertSendingFormsInOrder();
        //checkoutPage.clickCheckoutButton();
        //checkoutPage.chekPaymentPage();

    }
    @Test
    public void getAllUrlsCodes() throws InterruptedException {
        openPage("https://lgcity.ru/personal/");
        pages.getLinks(pages.scanForLinks(catalogListPage.jsTagName,"https://lgcity.ru/personal/"),"https://lgcity.ru/personal/");
        pages.getBadUrlList(pages.getUniqueLinks(pages.scanForLinks(catalogListPage.jsTagName,"https://lgcity.ru/personal/")));
    }
}
