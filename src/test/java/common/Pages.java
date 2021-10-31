package common;

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

/*
Закрыть куки
 */
public void setCloseCookieBtn(){
    closeCookieBtn.click();
}


    /*
Войти в Корзину
 */
    public static void goToBasket(){
        basketIcon.click();
    }
    /*
    Перейти на главную страницу по логотипу
     */
    public void goToMainPage(){
        logo.click();
    }
}
