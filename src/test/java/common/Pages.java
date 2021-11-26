package common;

import io.qameta.allure.Step;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

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
    //Элементы главного меню "Верхняя одежда" Мужчинам
    @FindBy(xpath = "//div[@id = 'gmenu-tab-326']/nav[@class='header__nav-list']/div/a")
    public List<WebElement> menuItemsListMen;
    //Элементы главного меню "Верхняя одежда" Женщинам
    @FindBy(xpath = "//div[@id = 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")
    public List<WebElement> menuItemsListWomen;
    //Не активный элемент главного меню Мужчинам/Женщинам
    @FindBy(xpath = "//div[@class='header__nav-buttons js-tabs-nav']/div/div[not(contains(@class,'header__nav-button " +
            "js-tabs-button active'))]")
    public WebElement genderItem;
    //Меню выбора пола Мужчинам/Женщинам
    @FindBy(xpath = "//div[@class='wrapper']/div[contains(@class,'header__nav-button')]")
    public List<WebElement> menuGenderList;

    //Заголовок страницы
    @FindBy(tagName = "h1")
    WebElement pageHeader;
    //кнопка избранного в шапке
    @FindBy(xpath = "//a[@class='header__r-icons-link header__r-icons-link--favorite js-header-favorite']")
    public WebElement favoriteInHeader;
    //Иконка поиска
    @FindBy(xpath = "//div[@class='header__right-side-icons']/a[@data-popup='popup--search']")
    public WebElement searchIcon;
    //Попап поиска
    @FindBy(xpath = "//div[@class='input input--default header__search-input autocomplete is-focused']/input")
    public WebElement searchPopup;
    //Строка поиска
    @FindBy(xpath = "//input[@name='q']")
    public WebElement searchString;
    //Футер
    @FindBy(xpath = "//footer[@class='footer']")
    public WebElement footer;
    //Элемент меню 'О КОМПАНИИ' в Футере
    @FindBy(xpath = "//span[contains(text(), 'О компании')]/../self::div/following-sibling::ul/li")
    public WebElement aboutCompanyFooterMenu;
    //Элемент меню 'УСЛУГИ' в Футере
    @FindBy(xpath = "//span[contains(text(), 'Услуги')]/../self::div/following-sibling::ul/li")
    public WebElement servicesFooterMenu;
    //Элемент меню 'ОНЛАЙН-ПОКУПКИ' в Футере
    @FindBy(xpath = "//span[contains(text(), 'Онлайн-покупки')]/../self::div/following-sibling::ul/li")
    public WebElement onlineShoppingFooterMenu;
    //Блок подписки в Футере
    @FindBy(xpath = "//input[@placeholder='Ваш e-mail']")
    public WebElement subscriptionBlockFooterMenu;
    //Фавикон
    @FindBy(xpath = "//link[@rel='icon']")
    public WebElement favicon;
    //К сожалению, данная страница не найдена или возникла непредвиденная ошибка
    public @FindBy(xpath = "//div[contains(text(), 'Что-то пошло не так...')]")
    WebElement somethingWentWrong;
    public String jsTagName = "return document.getElementsByTagName('a').length";
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
        waitInvisibilityElement(closeCookieBtn, "куки");
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
            waitInvisibilityElement(pages.loader, "лоадер");
            System.out.println("Лоадер виден");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("Лоадер не виден");
        }
    }

    /*
    Выбор рандомного элемента меню Мужчинам
     */
    @Step("Выбираем рандомный элемент меню Мужчинам")
    public void randomMenuItemMen() throws InterruptedException {

        int menuItems = menuItemsListMen.size() - 1;
        //int menuItems = driver.findElements(By.xpath("//div[@id =
        // 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")).size() - 1;
        //System.out.println("количество элементов меню " + menuItems);
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        String randomMenuItem = getTextElement(menuItemsListMen.get(randomItem), "меню");
        //System.out.println(randomMenuItem);
        clickElement(menuItemsListMen.get(randomItem), randomMenuItem);
        String headerName = getHeaderName();
        assertString(headerName, randomMenuItem);
        wait(1);

    }

    /*
Выбор рандомного элемента меню Женщинам

 */
    @Step("Выбираем рандомный элемент меню Женщинам")
    public void randomMenuItemWomen() throws InterruptedException {

        int menuItems = menuItemsListWomen.size() - 1;
        //int menuItems = driver.findElements(By.xpath("//div[@id =
        // 'gmenu-tab-327']/nav[@class='header__nav-list']/div/a")).size() - 1;
        //System.out.println("количество элементов меню " + menuItems);
        Random random = new Random();
        int randomItem = random.nextInt(menuItems) + 1;
        String randomMenuItem = getTextElement(menuItemsListWomen.get(randomItem), "меню");
        //System.out.println(randomMenuItem);
        clickElement(menuItemsListWomen.get(randomItem), randomMenuItem);
        String headerName = getHeaderName();
        assertString(headerName, randomMenuItem);
        wait(1);
    }

    @Step("Количество элементов меню")
    public int randomMenuGender() {
        return menuGenderList.size();

    }

    @Step("Нажимаем иконку Избранное в шапке")
    public void clickIconFavorites() {
        favoriteInHeader.click();
    }

    @Step("Сканируем страницу {baseUrl} на наличие ссылок")
    public ArrayList<String> scanForLinks(String jsTagName,String baseUrl) {
        ArrayList<String> urlList = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int num = Integer.parseInt(js.executeScript(jsTagName).toString());
        for (int i = 0; i < num; i++) {
            try {
                String string =
                        js.executeScript("return document.getElementsByTagName('a')[" + i + "].getAttribute('href')").toString();
                char str = js.executeScript("return document.getElementsByTagName('a')[" + i + "].getAttribute('href')").toString().charAt(0);
                if (str == '/') {
                    String url =
                            "https://lgcity.ru" + js.executeScript("return document.getElementsByTagName('a')[" + i + "].getAttribute('href')").toString();
                    urlList.add(url);
                } else if (!string.contains("javascript") && !string.equals("#")) {
                    urlList.add(js.executeScript("return document.getElementsByTagName('a')[" + i + "].getAttribute" + "('href')").toString());
                }
            } catch (Exception e) {

            }
        }
        return urlList;
    }

    @Step("Собираем  массив ссылок")
    public void getLinks(ArrayList<String> urlList, String baseUrl) {
        ArrayList<String> urlsList = new ArrayList<>();
        int sizeUrlList = urlList.size();
        for (int i = 0; i < urlList.size(); i++) {
            int count = i + 1;
            urlsList.add("\nПолучена ссылка №" + count + " URL: " + urlList.get(i));
        }
        System.out.println(urlsList);
        System.out.println("Всего ссылок на странице: " + baseUrl + " - " + sizeUrlList);
    }

    @Step("Отбираем уникальные ссылки")
    public ArrayList<String> getUniqueLinks(ArrayList<String> urlList) {
        Set<String> set = new HashSet<>(urlList);
        urlList.clear();
        urlList.addAll(set);
        for (int i = 0; i < urlList.size(); i++) {
            int count = i + 1;
            System.out.println("Отобрана уникальная ссылка № " + count + " - URL: " + urlList.get(i));
        }
        int resultArrayLinks = urlList.size();
        System.out.println("Количество уникальных ссылок:" + resultArrayLinks);
        return urlList;
    }

    @Step("Отбираем из массива уникальных ссылок битые")
    public void getBadUrlList(ArrayList<String> urlList) {
        ArrayList<String> badUrlList = new ArrayList<>();
        int resultArrayLinks = urlList.size();
        for (int i = 0; i < resultArrayLinks; i++) {
            int statusCode = given().when().get(urlList.get(i)).getStatusCode();

            int count = i + 1;
            System.out.println("Проверена уникальная ссылка № " + count + " - URL: " + urlList.get(i) + " - вернула " +
                    "статус код: " + statusCode + ";");
            if (statusCode != 200) {
                badUrlList.add(urlList.get(i) + " - вернулся статус код: " + statusCode + " № ссылки: " + count + ";");
            }
        }
        System.out.println(badUrlList);
        Assert.assertEquals(badUrlList.size(), 0, "Битые ссылки: " + badUrlList);
    }


}

