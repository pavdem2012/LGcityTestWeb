package pages;

import common.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public BasketPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Адреса на странице
    //Не пустая корзина страница
    @FindBy(xpath = "//div[@class='basket__non-empty']")
    public static WebElement basketNonEmptyPage;
    //Общая цена в корзине
    @FindBy(id = "basket-total-price")
    public WebElement basketTotalPrice;
    //Цвет товара в корзине
    @FindBy(xpath = "//div[@class='basket__item-props']/div[(contains(text(), 'Цвет:'))]")
    static
    WebElement colorOfProductInCart;
    //Название товара в корзине
    @FindBy(xpath = "//div[@class='basket__item-title']")
    public static WebElement nameOfProductInCart;
    //Размер товара в корзине
    @FindBy(xpath = "//div[@class='basket__item-prop']")
    static
    WebElement sizeOfProductInCart;
    //Цена товара в корзине 
    @FindBy(xpath = "//div[@class='basket__item-price']/div")
    static
    WebElement priceOfProductInCart;
    //Кнопка "Удалить из корзины"
    @FindBy(xpath = "//div[@class='basket__item-remove js-basket-remove-item']")
    static
    WebElement basketItemRemove;
    //Пустая корзина страница
    @FindBy(xpath = "//div[@class='basket__empty']")
    public
    WebElement basketEmptyPage;
    //Пустая корзина заголовок
    @FindBy(xpath = "//div[contains(@class, 'basket__empty-title')]")
    public
    WebElement basketEmptyHeader;
    //Крестик закрыть корзину
    @FindBy(xpath = "//div[@class='basket__header-icon-box js-popup-close']")
    static
    WebElement basketPopupClose;
    //кнопка "К оформлению" в корзине
    @FindBy(xpath = "//button[text()='К оформлению']")
    WebElement checkoutBtn;
    //кнопка "Продолжить без регистрации"
    @FindBy(xpath = "//button[text()='Продолжить без регистрации']")
    WebElement btnWithoutRegistration;
    //поле "Имя" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='Имя']/following-sibling::input")
    WebElement nameField;
    //поле "Фамилия" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='Фамилия']/following-sibling::input")
    WebElement lastnameField;
    //поле "Имя" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='E-mail']/following-sibling::input")
    WebElement emailField;
    //поле "Имя" в оформлении заказа
    @FindBy(xpath = "//div[@name='scroll-user']//label[text()='Телефон']/following-sibling::input")
    WebElement phoneField;
    //поле "Адрес" в оформлении заказа
    @FindBy(xpath = "//label[text()='Населенный пункт']/following-sibling::input")
    WebElement addressField;
    //первый элемент в выпадающем списке адреса
    @FindBy(xpath = "//li[@class='selected-city']")
    WebElement addressListElement;
    //Количество товаров для теста
    public static int countProductsForTest = 2;


    //Функции
    //Общая цена в корзине число
    public int totalPrice() {
        //waitVisibilityElement(basketTotalPrice);
        return Integer.parseInt(basketTotalPrice.getText().replaceAll(" ", ""));
    }

    //Цена товара в корзине
    public String getPriceOfProductInCart() {

        return priceOfProductInCart.getText().replaceAll(" ", "").replaceAll("₽", "");
    }

    //Название товара в корзине
    public String nameOfProductInCart() {
        //waitVisibilityElement(nameOfProductInCart);
        return nameOfProductInCart.getText().toLowerCase().replaceAll(" ", "");//!!
    }

    //Цвет товара в корзине
    public String colorOfProductInCart() {
        //waitVisibilityElement(colorOfProductInCart);
        return colorOfProductInCart.getText().toLowerCase().replaceAll("цвет:", "").replaceAll(" ", "");
    }

    //Размер товара в корзине
    public String sizeOfProductInCart() {
        //waitVisibilityElement(sizeOfProductInCart);
        return sizeOfProductInCart.getText().replaceAll(" ", "").toLowerCase().replaceAll("размер:", "");
    }

    //Удалить товар из корзины
    public void setBasketItemRemove() {
        //waitVisibilityElement(basketItemRemove);
        basketItemRemove.click();
    }

    //Закрыть корзину
    public void setBasketClose() {
        //waitVisibilityElement(basketPopupClose);
        basketPopupClose.click();
    }

    public void clickCheckoutBtn() {
        checkoutBtn.click();
    }

    public void clickBtnWithoutRegistration() {
        btnWithoutRegistration.click();
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

    public void setAddress() throws InterruptedException {
        addressField.sendKeys("Новосибирск");
        wait(1);
        addressListElement.click();
    }

    //доставка курьером
    @FindBy(xpath = "//span[text()='Доставка курьером']")
    WebElement courierDelivery;
    //самовывоз
    @FindBy(xpath = "//span[text()='Самовывоз']")
    WebElement pickup;
    //улица в доставке курьером
    @FindBy(xpath = "//label[text()='Улица']/following-sibling::input")
    WebElement courierDeliveryStreet;
    //дом в доставке курьером
    @FindBy(xpath = "//label[text()='Дом*']/following-sibling::input")
    WebElement courierDeliveryHouse;
    //корпус в доставке курьером
    @FindBy(xpath = "//label[text()='Корпус']/following-sibling::input")
    WebElement courierDeliveryBuilding;
    //квартира в доставке курьером
    @FindBy(xpath = "//label[text()='Квартира']/following-sibling::input")
    WebElement courierDeliveryApartment;
    //индекс в доставке курьером
    @FindBy(xpath = "//label[text()='Индекс']/following-sibling::input")
    WebElement courierDeliveryIndex;

    public void setDelivery(String string) throws InterruptedException {
        if (string.equals("Курьер")) {
            courierDelivery.click();
            courierDeliveryStreet.sendKeys("Ленина");
            addressListElement.click();
            wait(1);
            courierDeliveryHouse.sendKeys("12");
            wait(1);
            courierDeliveryBuilding.sendKeys("1");
            wait(1);
            courierDeliveryApartment.sendKeys("1");
//            courierDeliveryIndex.sendKeys();
        } else {
            pickup.click();
        }
    }
}
