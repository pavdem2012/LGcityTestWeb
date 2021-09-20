import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewsMainLgCity extends Settings{
    @Test
    public void setNews(){
        open("https://lgcity.ru");
        Actions scroll = new Actions(driver);
        scroll.moveToElement(driver.findElement(By.className("news"))).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("news__item")));
        driver.findElement(By.id("bx_651765591_769484")).click();
        wait.until(ExpectedConditions.textToBe(By.className("news-page__back-title"),"Акции и новости"));
    }
}
