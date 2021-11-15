package pages;

import common.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardProductPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public CardProductPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Цвет товара
    @FindBy(xpath = "//div[@class='card__color-value']")
    WebElement colorItemCart;
    //Строка выбора размера
    @FindBy(xpath = "//input[@class='js-select-input select__input']")
    WebElement  sizeMenuClick;
    //Всплывющее меню с размерами
    @FindBy(xpath = "//div[@class='select__drop select__drop--sizes']")
    WebElement sizesMenu;
    //Размер в меню выбора размера
    @FindBy(xpath = "//ul[@id='offer-sizes']/li[contains(@class, 'selected')]")
    WebElement sizeItemCart;
    //Цена товара
    @FindBy(xpath = "//div[@id='offer-price']/div")
    WebElement priceItemCart;
    //Кнопка "В корзину"
    @FindBy(id = "btn-add-to-cart")
    WebElement addToBasketBtn;
    //Кнопка "Прейти в корзину"
    @FindBy (xpath = "//button[text()='Перейти в корзину']")
    WebElement buttonFillCardYellow;

    //Цвет товара из карточки
    public String getColorCartItem() {
        //waitVisibilityElement(colorItemCart);
        return colorItemCart.getText().toLowerCase();
    }

    //Размер товара из карточки
    public String getSizeCartItem() throws InterruptedException {
        Thread.sleep(1000);
        sizeMenuClick.click();
        waitVisibilityElement(sizeItemCart);
        String cartSize = sizeItemCart.getAttribute("data-size").toLowerCase();
        colorItemCart.click();
        waitInvisibilityElement(sizesMenu);
        return cartSize;
    }

    //Цена товара из карточки
    public String getPriceCartItem() {
        //waitValueInElement(priceItemCart,"₽");
        return priceItemCart.getText().replaceAll(" ", "").replaceAll("₽", "");
    }

    //Добавить товар в корзину
    @Step("Добавить товар в корзину")
    public void addProductToBasket() throws InterruptedException {
        waitVisibilityElement(addToBasketBtn);
        wait(1);
        addToBasketBtn.isEnabled();
        addToBasketBtn.click();
    }
    //Перейти в корзину.
    public void goToBasketFromCart () {
        waitVisibilityElement(buttonFillCardYellow);
        buttonFillCardYellow.click();
    }

}
