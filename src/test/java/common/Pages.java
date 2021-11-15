package common;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pages extends Settings{
    WebDriver driver;
    WebDriverWait wait;

    public Pages(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }
    //Куки
    @FindBy(id = "confirm-use-cookies")
    WebElement closeCookieBtn;
    //Иконка "Корзина"
    @FindBy(xpath = "//a[@class='header__r-icons-link js-popup js-header-basket']")
    static
    WebElement basketIcon;
    //Логотип
    @FindBy(xpath = "//a[@class='header__logo-link']")
    WebElement logo;
    //Лоадер вращающийся
    @FindBy(xpath = "//div[@class='lds-dual-ring']")
    public WebElement loader;

/*
Закрыть куки
 */
    @Step("Закрыть куки")
public void setCloseCookieBtn()  {
    closeCookieBtn.click();
    waitInvisibilityElement(closeCookieBtn);
}


    /*
Войти в Корзину
 */
    @Step("Переход в корзину по иконке")
    public void goToBasket() throws InterruptedException {
        basketIcon.click();
        wait(2);
    }
    /*
    Перейти на главную страницу по логотипу
     */
    @Step("Переход на главную страницу по логотипу")
    public void goToMainPage(){
        logo.click();
    }
    /*
    Ожидание лоадера
     */
    public void loaderWait(){
        try {
            waitInvisibilityElement(pages.loader);
            System.out.println("Лоадер виден");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Лоадер не виден");
        }
    }
}

