package common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class Pages extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public Pages(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    /*Куки*/
    @FindBy(id = "confirm-use-cookies")
    WebElement closeCookieBtn;
    //Иконка "Корзина"
    @FindBy(xpath = "//a[@class='header__r-icons-link js-popup js-header-basket']")
    static WebElement basketIcon;
    //Логотип
    @FindBy(xpath = "//a[@class='header__logo-link']")
    WebElement logo;
    //Лоадер вращающийся
    @FindBy(xpath = "//div[@class='lds-dual-ring']")
    public WebElement loader;
    //Элемент главного меню "Верхняя одежда"
    @FindBy(xpath = "//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")
    public WebElement menuItem;
    //Элемент главного меню "Верхняя одежда"
    @FindBy(xpath = "//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")
    public List<WebElement> menuItemsList;

    //Заголовок страницы
    @FindBy(tagName = "h1")
    WebElement pageHeader;
    //кнопка избранного в шапке
    @FindBy(xpath = "//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")
    public WebElement favoriteInHeader;

    /*
    Название заголовка страницы
     */
    @Step("Получаем заголовок страницы")
    public String getHeaderName() {
        return pageHeader.getText().toLowerCase();
    }


    /*
    Закрыть куки
    */
    @Step("Закрываем куки")
    public void setCloseCookieBtn() {
        closeCookieBtn.click();
        waitInvisibilityElement(closeCookieBtn);
    }


    /*
    Войти в Корзину
    */
    @Step("Переходим в корзину по иконке")
    public void goToBasket() throws InterruptedException {
        basketIcon.click();
        wait(2);
    }

    /*
    Перейти на главную страницу по логотипу
     */
    @Step("Переходим на главную страницу по логотипу")
    public void goToMainPage() {
        logo.click();
    }

    /*
    Ожидание лоадера
     */
    public void loaderWait() {
        try {
            waitInvisibilityElement(pages.loader);
            System.out.println("Лоадер виден");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Лоадер не виден");
        }
    }

    /*
    Выбор рандомного элемента меню
     */
    @Step("Выбираем рандомный элемент меню")
    public void randomMenuItem() {
        int menuItems = driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div" + "/a")).size() - 1;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        String randomMenuItem =  getTextElement(menuItemsList.get(randomItem),"меню");
        clickElement(menuItemsList.get(randomItem),randomMenuItem);
        String headerName = getHeaderName();
        assertString(headerName,randomMenuItem);

    }
    @Step("Нажимаем иконку Избранное в шапке")
    public void clickIconFavorites(){
        favoriteInHeader.click();
    }
}

