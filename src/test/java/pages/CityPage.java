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
    public void selectCity(String city) {
        iconSetCity.click();
        waitVisibilityElement(popupSetCity);
        inputUserLocate.clear();
        inputUserLocate.sendKeys(city);
        btnSaveUserLocate.click();
        waitInvisibilityElement(popupSetCity);
    }

    public String getHeaderCity() {
        return iconSetCity.getText();
    }

    public void setAutoCity(String city) {
        iconSetCity.click();
        waitInvisibilityElement(popupSetCity);
        inputUserLocate.clear();
        locateInputAuto.click();
        waitValueInElement(inputUserLocate, city);
        btnSaveUserLocate.click();
    }

    public ArrayList<String> getRandomCity(int cityAmount){
        Random random = new Random();
        int randomNumber = random.nextInt(cityAmount) + 1;
        driver.findElement(By.xpath("(//div[@class='locate__popular-list']/a)[" + randomNumber + "]")).click();
        ArrayList<String> list = new ArrayList<>();
        String popularCity = driver.findElement(By.xpath("(//div[@class='locate__popular-list']/a)[" + randomNumber + "]")).getText();
        String cityInField = inputUserLocate.getAttribute("value");
        list.add(popularCity);
        list.add(cityInField);
        return list;
    }

    public boolean checkPopularCityList (int popularCityAmount){
        List<String> cityList = new ArrayList<>();
        List<WebElement> menuElements = getElementsByXpath(popularCityList);
        for (WebElement menuElement: menuElements) {
            cityList.add(menuElement.getText());
            //cityList.add(driver.findElement(By.xpath("(//a[@class='locate__popular-list-item'])[" + i + "]")).getText());
        }
        return cityList.contains("Москва");
    }

    public boolean hasCity (String cityName){
        for (WebElement element : getElementsByXpath(popularCityList)){
            if (element.getText().equals(cityName)){
                return true;
            }
        }
        return false;
    }
}
