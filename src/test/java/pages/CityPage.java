package pages;

import common.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public CityPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Иконка открытия попапа "Укажите свой город"
    @FindBy(id = "header-title-user-location")
    public WebElement iconSetCity;
    //Попап "Укажите свой город"
    @FindBy(xpath = "//div[contains(text(),'Укажите свой город')]/../..")
    public WebElement popupSetCity;
    //Поле ввода населенного пункта
    @FindBy(id = "input-user-locate")
    public WebElement inputUserLocate;
    //Кнопка "Сохранить" в попапе "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(id = "btn-save-user-locate")
    public WebElement btnSaveUserLocate;
    //Кнопка "Закрыть"  в попапе "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(xpath = "//div[@class='locate__title-icon-box js-popup-close']")
    public WebElement iconBoxPopupClose;
    //Кнопка "Определить автоматически" в попап "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(xpath = "//a[@class='locate__input-auto']")
    public WebElement locateInputAuto;
    //Поупулярные города
    public By popularCityList = By.xpath("//a[@class='locate__popular-list-item']");
    //список элементов списка популярных городов
    @FindBy(xpath = "//a[@class='locate__popular-list-item']")
    List<WebElement> popularCityElements;
    @Step("Нажать иконку открытия попапа 'Укажите свой город' и ввести название города в попапе")
    public void selectCity(String city) {
        iconSetCity.click();
        waitVisibilityElement(popupSetCity);
        inputUserLocate.clear();
        sendString(inputUserLocate,city);
        btnSaveUserLocate.click();
        waitInvisibilityElement(popupSetCity);
    }
    @Step("Получить текст иконки открытия попапа 'Укажите свой город'")
    public String getHeaderCity() {
        return iconSetCity.getText();
    }
    @Step("Нажать иконку открытия попапа 'Укажите свой город'")
    public void clickIconSetCity() throws InterruptedException {
        iconSetCity.click();
        wait(1);
        waitVisibilityElement(popupSetCity);
    }
    @Step("Нажать кнопку 'Сохранить' в попапе 'УКАЖИТЕ СВОЙ ГОРОД'")
    public void clickBtnSaveUserLocate() throws InterruptedException {
        btnSaveUserLocate.click();
        waitInvisibilityElement(popupSetCity);
        wait(1);

    }
    @Step("Нажать кнопку 'Определить автоматически'")
    public void setAutoCity(String city) {
        iconSetCity.click();
        waitInvisibilityElement(popupSetCity);
        inputUserLocate.clear();
        locateInputAuto.click();
        waitValueInElement(inputUserLocate, city);
        btnSaveUserLocate.click();
        waitInvisibilityElement(popupSetCity);
    }
    @Step("Выбрать рандомный город из списка популярных городов")
    public ArrayList<String> getRandomCity(int cityAmount) {
        Random random = new Random();
        int randomNumber = random.nextInt(cityAmount);
        popularCityElements.get(randomNumber).click();
        ArrayList<String> list = new ArrayList<>();
        String popularCity = popularCityElements.get(randomNumber).getText();
        String cityInField = inputUserLocate.getAttribute("value");
        list.add(popularCity);
        list.add(cityInField);
        return list;
    }
    @Step("Нажать кнопку 'Закрыть'(Крестик)  в попапе 'УКАЖИТЕ СВОЙ ГОРОД'")
    public void clickIconBoxPopupClose() throws InterruptedException {
        iconBoxPopupClose.click();
        wait(1);
        waitInvisibilityElement(popupSetCity);
    }
    @Step("Собрать список популярных городов")
    public boolean checkPopularCityList(int popularCityAmount) {
        List<String> cityList = new ArrayList<>();
        for (int i = 0; i < popularCityAmount; i++) {
            cityList.add(popularCityElements.get(i).getText());
        }
        return cityList.contains("Москва");
    }
}
