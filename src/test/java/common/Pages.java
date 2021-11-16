package common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Random;

public class Pages extends Settings {
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

    //Заголовок страницы
    @FindBy(tagName = "h1")
    WebElement pageHeader;
    //кнопка избранного в шапке
    @FindBy(xpath = "//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")
    public WebElement favoriteInHeader;

    /*
    Название заголовка страницы
     */
    @Step("Название заголовка страницы")
    public String headerName() {
        return pageHeader.getText().toLowerCase();
    }


    /*
    Закрыть куки
    */
    @Step("Закрыть куки")
    public void setCloseCookieBtn() {
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
    @Step("Выбор рандомного элемента меню")
    public void randomMenuItem() {
        int menuItems = driver.findElements(By.xpath("//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div" + "/a")).size() - 1;
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        String randomMenuItem =
                driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)" + "[" + randomItem + "]")).getText().toLowerCase();

        driver.findElement(By.xpath("(//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a)[" + randomItem + "]")).click();
        String headerName = headerName();
        Assert.assertTrue(headerName.contains(randomMenuItem),"Заголовок страницы: "+headerName + "; Заголовок элемента меню: " +randomMenuItem);

    }
    @Step("Нажать иконку Избранное в шапке")
    public void clickIconFavorites(){
        favoriteInHeader.click();
    }
}

