package pages;

import common.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CityPage extends Settings{
    WebDriver driver;
    WebDriverWait wait;

    public CityPage(WebDriver driver, WebDriverWait wait){
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
    @FindBy(xpath =  "//div[@class='locate__title-icon-box js-popup-close']")
    public WebElement iconBoxPopupClose;

    @FindBy(xpath = "//a[@class='locate__input-auto']")
    public WebElement locateInputAuto;

    @FindBy(xpath = "//a[@class='button button--fill locate__button']")
    public WebElement btnSave;

    public void selectCity(String city){
        iconSetSity.click();
        waitVisibilityElement(popupSetSity);
        inputUserLocate.clear();
        inputUserLocate.sendKeys(city);
        btnSaveUserLocate.click();
        waitInvisibilityElement(popupSetSity);
    }

    public String getHeaderCity(){
        return iconSetSity.getText();
    }

    public void setAutoCity(String city){
        iconSetSity.click();
        waitInvisibilityElement(popupSetSity);
        inputUserLocate.clear();
        locateInputAuto.click();
        waitValueInElement(inputUserLocate, city);
    }


}
