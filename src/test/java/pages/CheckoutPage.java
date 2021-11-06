package pages;

import common.Settings;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestCheckout;

public class CheckoutPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Данные авторизации
    String eMail = "lysenko.d@qasquad.com";
    String pass = "qweqwe123";
    //Кнопка "Войти с помощью пароля"
    @FindBy(xpath = "//div[@class='links-bottom-center']//a[text() ='Войти с помощью пароля']")
    WebElement loginLinkTogglePassLogin;
    //Кнопка Зарегистрироваться
    @FindBy(xpath = "//div[@class='lk-login-form__cta-btn']/a[text() ='Зарегистрироваться']")
    WebElement loginBtn;
    @FindBy(xpath = "//div[(contains(text(), 'Личный кабинет'))]")
    public
    WebElement loginTitle;
    //Поле ввода "E-mail или номер телефона"
    @FindBy(xpath = "//input[@name='log_email']")
    public
    WebElement eMailInput;
    //Поле ввода
    @FindBy(xpath = "//input[@name='log_pass']")
    WebElement passInput;
    //Кнопка Войти в Личный кабинет
    @FindBy(xpath = "//button[@class='button button--fill login__button']")
    WebElement toComeInBtn;
    //Кнопка самовывоз
    @FindBy(xpath = "//a[@class='button-with-icon ']//span[(contains(text(), 'Самовывоз'))]")
    public WebElement pickUpBtn;
    //Заголовок "Оформление заказа"
    @FindBy(xpath = "//h1[@class='cart__title']")
    public WebElement h1Header;
    //Кнопка выбрать ПВЗ
    @FindBy(xpath = "//div[@class='cart__row-content']/a")
    WebElement selectBtn;
    //Заголовок стравницы Пункты выдачи
    @FindBy(xpath = "//div[@class='order-map-content-description']/p")
    WebElement headerPickupPoint;
    //1 ый ПВЗ из списка
    @FindBy(xpath = "//div[@class='cart-pickup-choose cp-choose js-show-delivery-point ']")
    WebElement firstPickupPoint;
    //Кнопка выбрать этот пункт
    @FindBy(xpath = "//div[@class='delivery-point__cta']/a")
    WebElement selectThisItemButton;
    //Поле ввода коментария к заказу
    @FindBy(xpath = "//div[@class='ui-textarea']")
    WebElement clickTextareaPlaceholder;
    @FindBy(xpath = "//div[@class='ui-textarea']/textarea")
    WebElement textareaPlaceholder;
    //Кнопка выбора способа оплаты Картой онлайн
    @FindBy(xpath = "//div[@class='radio__text']/div[@class='pay_notice']") //еще вариант ()(//div[@class='radio__text']/div[(contains(text(), 'Скидка 2%'))])
            WebElement onlineCardRadioBtn;
    //Кнопка выбора подтверждения заказа Звонок оператора
    @FindBy(xpath = "//div[@class='radio__text']/div[(contains(text(), 'Звонок оператора'))]")
    WebElement operatorCallRadioBtn;
    //строка сумма товаров
    @FindBy(xpath = "//div[@class='cart-scope__row']/span[@class='cart-scope__value']")
    WebElement cartScopeValue;
    //Строка сумма заказа
    @FindBy(xpath = "//div[@class='cart-scope__row cart-scope__row--total']/span[@class='cart-scope__value']")
    WebElement cartScopeRowTotal;
    //Кнопка Оформить заказ
    @FindBy(xpath = "//div[@class='cart__cta-button']/button")
            WebElement checkoutButton;
    //Заголовок страницы оплаты
    @FindBy(xpath = "//div[@class='styles_merchantName__1GDx-']")
            WebElement merchantName;
    //цена на стронице оплаты
    @FindBy(xpath = "//div[@class='styles_amount__10kWS']/div")
            WebElement merchantPrice;
    String merchantNameExam = "lgcity";
    String commentToOrder = "тест заказ";
    String name = "петро 1";
    String surName = "testovui";
    String e_Mail = "lysenko.d@qasquad.com";
    String telephone = "+7 (961) 222-60-17";
    public String city = "Новосибирск";
    int cartScopeTotal;

    //Нажать ссылку "Войти с помощью пароля"
    public void loginLinkTogglePassLogin() {
        loginLinkTogglePassLogin.click();
    }

    //Нажать кнопку "Зарегистрироваться"
    public void clickLoginBtn() {
        loginBtn.click();
    }


    //Ввести в поле 'E-mail или номер телефона' аунтификационные данные
    public void Authorization() throws InterruptedException {
        eMailInput.sendKeys(eMail);
        passInput.sendKeys(pass);
        wait(1);
        toComeInBtn.click();

    }

    //Проверить состояние полей формы Получатель
    public void recipientForm() {
        System.out.println(basketPage.nameField.getAttribute("value").toString());

    }

    //Нажать кнопку самовывоз
    public void clickPickUpBtn() {
        moveTo(textareaPlaceholder);
        pickUpBtn.click();
    }

    //Нажать кнопку выбрать ПВЗ
    public void clickSelectBtn() {
        selectBtn.click();
    }

    //Выбрать ПВЗ на странице Пункты выдачи
    public void selectPickupPoint() throws InterruptedException {
        waitVisibilityElement(headerPickupPoint);
        firstPickupPoint.click();
        selectThisItemButton.click();
        wait(2);
        moveTo(onlineCardRadioBtn);
        clickTextareaPlaceholder.click();
        textareaPlaceholder.sendKeys(commentToOrder);
        onlineCardRadioBtn.click();
        operatorCallRadioBtn.click();

    }
    //Проверка соответствия цен
    public void assertPrisesInOrder() throws InterruptedException {
        moveTo(cartScopeRowTotal);
        wait(2);
        int cartScope = Integer.parseInt(cartScopeValue.getText().toLowerCase().replaceAll(" ","").replaceAll("₽",""));
        System.out.println(cartScope);
        int discountValue = Integer.parseInt(onlineCardRadioBtn.getText().toLowerCase().replaceAll("[^0-9]", ""));
        System.out.println(discountValue);
        cartScopeTotal = Integer.parseInt(cartScopeRowTotal.getText().toLowerCase().replaceAll(" ","").replaceAll("₽",""));
        System.out.println(cartScopeTotal);
        int cartScopeTotalCheck = cartScope - (cartScope / 100) * discountValue;
        System.out.println(cartScopeTotalCheck);
        Assert.assertTrue("Расчет скидки онлайн-заказа неверен!",cartScopeTotalCheck==cartScopeTotal);
    }
    //Нажать кнопку Оформить заказ
    public void clickCheckoutButton(){
        checkoutButton.click();
    }
    //Проверить страницу оплаты
    public void chekPaymentPage(){
        waitVisibilityElement(merchantName);
        String merchantNameCheck=merchantName.getText();
        System.out.println(merchantNameCheck);
        Assert.assertEquals("Неверная страница оплаты",true, merchantNameExam.contains(merchantNameCheck));
        int merchantPrice1 = Integer.parseInt(merchantPrice.getText().replaceAll(" ","").replaceAll("₽",""));
        System.out.println(merchantPrice1);
        Assert.assertTrue("Цена на странице оплаты отличается от цены заказа",merchantPrice1==cartScopeTotal);
    }

}
