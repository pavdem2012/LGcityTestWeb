package pages;

import common.Settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

public class CheckoutPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Данные авторизации
    String eMail = "testerqasquad@yandex.ru";
    String pass = "QAsquadAcadem";

    //поле "Имя" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='Имя']/following-sibling::input")
    WebElement nameField;
    //поле "Фамилия" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='Фамилия']/following-sibling::input")
    WebElement lastnameField;
    //поле "E-mail" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='E-mail']/following-sibling::input")
    WebElement emailField;
    //поле "Телефон" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='Телефон']/following-sibling::input")
    WebElement phoneField;
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
    @FindBy(xpath = "//div[@class='delivery-methods']//span[(contains(text(), 'Самовывоз'))]")
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
    //радиобатон Картой онлайн
    @FindBy (xpath = "//div[text()='Скидка 2%']/../self::div/preceding-sibling::div/div")
    public WebElement getOnlineCardRadioBtn;
    //Кнопка выбора способа оплаты Картой онлайн
    @FindBy(xpath = "//div[@class='radio__text']/div[@class='pay_notice']")
    public //еще вариант ()(//div[@class='radio__text']/div[(contains(text(), 'Скидка 2%'))])
            WebElement onlineCardRadioBtn;
    //радиобатон звонок оператора
    @FindBy(xpath = "//div[text()='Звонок оператора']/../self::div/preceding-sibling::div/div")
    public WebElement getOperatorCallRadioBtn;
    //Кнопка выбора подтверждения заказа Звонок оператора
    @FindBy(xpath = "//div[@class='radio__text']/div[(contains(text(), 'Звонок оператора'))]")
    public WebElement operatorCallRadioBtn;
    //строка сумма товаров
    @FindBy(xpath = "//div[@class='cart-scope__row']/span[@class='cart-scope__value']")
    WebElement cartScopeValue;
    //Чекбокс
    @FindBy(xpath = "//div[@class='checkbox__text']")
    WebElement checkbox;
    //Строка сумма заказа
    @FindBy(xpath = "//div[@class='cart-scope__row cart-scope__row--total']/span[@class='cart-scope__value']")
    WebElement orderScopeValue;
    //Кнопка Оформить заказ
    @FindBy(xpath = "//div[@class='cart__cta-button']/button")
            WebElement checkoutButton;
    //Заголовок страницы оплаты
    @FindBy(xpath = "//div[@class='styles_merchantName__1GDx-']")
            WebElement merchantName;
    //цена на стронице оплаты
    @FindBy(xpath = "//div[@class='styles_amount__10kWS']/div")
            WebElement merchantPrice;
    //поле "Адрес" в оформлении заказа
    @FindBy(xpath = "//label[text()='Населенный пункт']/following-sibling::input")
    public
    WebElement addressField;
    //первый элемент в выпадающем списке адреса
    @FindBy(xpath = "//li[@class='selected-city']")
    public WebElement addressListElement;
    String merchantNameExam = "lgcity";
    String commentToOrder = "тест заказ";
    String name = "Павел";
    String surName = "Тестеров";

    String telephone = "+7 (983) 303-20-67";
    public String city = "Новосибирск";
    int cartScopeTotal;

    //Нажать ссылку "Войти с помощью пароля"
    public void loginLinkTogglePassLogin() {
        waitVisibilityElement(loginTitle);
        loginLinkTogglePassLogin.click();
    }

    //Нажать кнопку "Зарегистрироваться"
    public void clickLoginBtn() {
        loginBtn.click();
    }


    //Ввести в поле 'E-mail или номер телефона' аунтификационные данные
    public void Authorization() throws InterruptedException {
        waitVisibilityElement(eMailInput);
        eMailInput.isEnabled();
        eMailInput.sendKeys(eMail);
        passInput.sendKeys(pass);
        wait(1);
        toComeInBtn.click();

    }

    //Нажать кнопку самовывоз
    public void clickPickUpBtn() {
        moveTo(textareaPlaceholder);
        pickUpBtn.click();
    }

    //Нажать кнопку выбрать ПВЗ
    public void clickSelectBtn() throws InterruptedException {
        selectBtn.isEnabled();
        wait(1);
        selectBtn.click();
    }

    //Выбрать ПВЗ на странице Пункты выдачи
    public void selectPickupPoint() throws InterruptedException {
        wait(2);
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
    public void assertSendingFormsInOrder() throws InterruptedException, IOException {
        wait(1);
        String nameA = nameField.getAttribute("value");
        Assert.assertEquals(nameA,name,"Неверное имя пользователя: "+nameA+", должно быть: "+name);
        String surNameA = lastnameField.getAttribute("value");
        Assert.assertEquals(surNameA,surName,"Неверная фамилия пользователя:" + surNameA +", должно быть: "+surName);
        String e_mailA = emailField.getAttribute("value");
        Assert.assertEquals(e_mailA,eMail, "Неверный e-mail пользователя:" + e_mailA +", должен быть: "+eMail);
        String phoneA = phoneField.getAttribute("value");
        Assert.assertEquals(phoneA,telephone,"Неверный телефон пользователя:" + phoneA +", должен быть: "+telephone);
        Assert.assertTrue(getOnlineCardRadioBtn.getAttribute("style").contains("translate"),"Кнопка \"Картой онлайн\" не нажата");
        Assert.assertTrue(getOperatorCallRadioBtn.getAttribute("style").contains("translate"),"Кнопка \"Звонок оператора\" не нажата");
        moveTo(checkbox);
        int cartScope = Integer.parseInt(cartScopeValue.getText().toLowerCase().replaceAll(" ","").replaceAll("₽",""));
        int discountValue = Integer.parseInt(onlineCardRadioBtn.getText().toLowerCase().replaceAll("[^0-9]", ""));
        cartScopeTotal = Integer.parseInt(orderScopeValue.getText().toLowerCase().replaceAll(" ","").replaceAll("₽",""));
        int cartScopeTotalCheck = (int) Math.ceil(cartScope - ((double)cartScope / 100) * discountValue);
        getScreen();
        Assert.assertTrue(cartScopeTotalCheck==cartScopeTotal,"Расчет скидки онлайн-заказа неверен!"+"; Сумма заказа: "+cartScope+"; Размер скидки: "+discountValue+"; Сумма заказа со скидкой: "+cartScopeTotalCheck);
    }
    //Нажать кнопку Оформить заказ
    public void clickCheckoutButton(){
        checkoutButton.click();
    }
    //Проверить страницу оплаты
    public void chekPaymentPage() throws  InterruptedException {
        wait(2);
        //getScreen();
        waitVisibilityElement(merchantName);
        String merchantNameCheck=merchantName.getText();
        System.out.println(merchantNameCheck);

        Assert.assertEquals( merchantNameExam.contains(merchantNameCheck),"Неверная страница оплаты");
        int merchantPrice1 = Integer.parseInt(merchantPrice.getText().replaceAll(" ","").replaceAll("₽",""));
        System.out.println(merchantPrice1);
        Assert.assertTrue(merchantPrice1==cartScopeTotal,"Цена на странице оплаты отличается от цены заказа");
    }
    public void setDeliveryAddress() throws InterruptedException {
        addressField.click();
        wait(1);
        addressField.sendKeys(city);
        wait(1);
        addressListElement.click();
    }


    public void setAddress() throws InterruptedException {
        addressField.sendKeys("Новосибирск");
        wait(1);
        addressListElement.click();
    }
    public void setOrderData() throws InterruptedException {
        nameField.sendKeys("Тест");
        wait(1);
        lastnameField.sendKeys("Тестов");
        wait(1);
        emailField.sendKeys("test@test.ru");
        wait(1);
        phoneField.click();
        phoneField.sendKeys("9999999999");
        wait(1);
    }

}
