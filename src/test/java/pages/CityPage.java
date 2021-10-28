package pages;

import common.Settings;
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
    public WebElement iconSetSity;
    //Попап "Укажите свой город"
    @FindBy(xpath = "//div[contains(text(),'Укажите свой город')]/../..")
    public WebElement popupSetSity;
    //Поле ввода населенного пункта
    @FindBy(id = "input-user-locate")
    public WebElement inputUserLocate;
    //Кнопка "Сохранить" в попапе "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(id = "btn-save-user-locate")
    public WebElement btnSaveUserLocate;
    //Кнопка "Закрыть"  в попапе "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(xpath = "//div[@class='locate__title-icon-box js-popup-close']")
    public WebElement iconBoxPopupClose;

    @FindBy(xpath = "//a[@class='locate__input-auto']")
    public WebElement locateInputAuto;

    public By popularCityList = By.xpath("//div[@class='locate__popular-list']/a");

    public void selectCity(String city) {
        iconSetSity.click();
        waitVisibilityElement(popupSetSity);
        inputUserLocate.clear();
        inputUserLocate.sendKeys(city);
        btnSaveUserLocate.click();
        waitInvisibilityElement(popupSetSity);
    }

    public String getHeaderCity() {
        return iconSetSity.getText();
    }

    public void setAutoCity(String city) {
        iconSetSity.click();
        waitInvisibilityElement(popupSetSity);
        inputUserLocate.clear();
        locateInputAuto.click();
        waitValueInElement(inputUserLocate, city);
    }

    public ArrayList<String> getRandomCity(int cityAmount){
        Random random = new Random();
        int randomNumber = random.nextInt(cityAmount) + 1;
        driver.findElement(By.xpath("(//div[@class='locate__popular-list']/a)[" + randomNumber + "]")).click();
        ArrayList<String> list = new ArrayList<>();
        String popularCity = driver.findElement(By.xpath("(//div[@class='locate__popular-list']/a)[" + randomNumber + "]")).getText();
        String cityInField = driver.findElement(By.id("input-user-locate")).getAttribute("value");
        list.add(popularCity);
        list.add(cityInField);
        return list;
    }


}
