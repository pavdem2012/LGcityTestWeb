import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import pages.Pages;

import java.util.Random;

public class FunctionsForTests extends Settings {
    public void scrollToNews(){
        Actions scroll = new Actions(driver);
        scroll.moveToElement(getElementByClassName(Pages.newsBlock)).perform();
    }
    public void randomNews(){
        int newsBlock =driver.findElements(By.xpath(Pages.newsItemBlock)).size();
        Random random =new Random();
        int randomNumber = random.nextInt(newsBlock)+1;
        getElementByXpath("("+Pages.newsItemWithException+")["+randomNumber+"]").click();
    }
}
