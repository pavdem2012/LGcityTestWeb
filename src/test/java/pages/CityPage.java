package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CityPage {

    //Иконка открытия попапа "Укажите свой город"
    @FindBy(id = "header-title-user-location")
    WebElement iconSetSity;
    //Попап "Укажите свой город"
    @FindBy(xpath = "//div[contains(text(),'Укажите свой город')]/../..")
    WebElement popupSetSity;
    //Поле ввода населенного пункта
    @FindBy(id = "input-user-locate")
    WebElement inputUserLocate;
    //Кнопка "Сохранить" в попапе "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(id = "btn-save-user-locate")
    WebElement btnSaveUserLocate;
    //Кнопка "Закрыть"  в попапе "УКАЖИТЕ СВОЙ ГОРОД"
    @FindBy(xpath =  "//div[@class='locate__title-icon-box js-popup-close']")
    WebElement iconBoxPopupClose;

}
