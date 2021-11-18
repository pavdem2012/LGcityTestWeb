package pages;

import common.Settings;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class SearchResultPage extends Settings {
    WebDriver driver;
    WebDriverWait wait;

    public SearchResultPage(WebDriver driver, WebDriverWait wait) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = wait;
    }

    //Заголовок экрана результатов поиска
    @FindBy(xpath = "//h1[contains(text(),'Найдено')]")
    public WebElement searchResultPageHeader;
    //Название товара в листинге поиска
    @FindBy(xpath = "//div[@class='catalog__item-desc']")
    public List<WebElement> catalogItemName;
    int randomName;




    @Step("Выбор названия рандомного товара в листинге результатов поиска")
    public String selectRandomName() {
        int menuItems = getRandom(catalogItemName.size());
        WebElement randomCard = catalogItemName.get(menuItems);
        moveTo(randomCard);
        return catalogItemName.get(menuItems).getText().toLowerCase().replaceAll(" ","");
    }
}