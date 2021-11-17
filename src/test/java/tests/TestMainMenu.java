package tests;

import common.Settings;
import org.testng.annotations.Test;

public class TestMainMenu extends Settings {

    /*
    Проверка элементов главного меню.
     */
    @Test
    public void testMenu() throws InterruptedException {
        open("https://lgcity.ru");
        pages.menuItem.isEnabled();
        pages.randomMenuItem();

    }


}

