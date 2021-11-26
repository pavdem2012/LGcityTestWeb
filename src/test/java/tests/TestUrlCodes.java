package tests;

import common.Settings;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

import io.restassured.RestAssured;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.given;


public class TestUrlCodes extends Settings {
    @Epic(value = "Главная страница")
    @Feature(value = "Ссылки")
    @Description("Проверка на наличие битых ссылок")
    @Test(description = "Проверка на наличие битых ссылок")

/*    public void getAllUrl() throws InterruptedException {
        openPage("https://lgcity.ru/outerwear/women/");
        ArrayList<String> urlList = new ArrayList<>();
        //pages.scanForLinks(urlList);
        //ArrayList<String> urlList = new ArrayList<>();
        //ArrayList<String> urlsList =new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int num = Integer.parseInt(js.executeScript("return document.querySelectorAll('div.content a').length").toString());
        for (int i = 0; i < num; i++) {

            try {
                String string =
                        js.executeScript("return document.getElementsByTagName('a')[" + i + "].getAttribute" +
                                "('href')").toString();
                char str = js.executeScript("return document.getElementsByTagName('a')[" + i + "].getAttribute" +
                        "('href')").toString().charAt(0);
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

        ArrayList<String> urlsList =new ArrayList<>();
        int sizeUrlList = urlList.size();
        for(int i =0; i<urlList.size();i++){
            int count = i + 1;
            urlsList.add("\nПолучена ссылка №"+count+" URL: " + urlList.get(i));
        }
        System.out.println(urlsList);
        System.out.println("Количество ссылок на странице (https://lgcity.ru/outerwear/women/): " + sizeUrlList);
        Set<String> set = new HashSet<>(urlList);
        urlList.clear();
        urlList.addAll(set);
        for (int i = 0; i < urlList.size(); i++) {
            int count = i + 1;
            System.out.println("Отобрана уникальная ссылка № "+count+" - URL: " + urlList.get(i));
        }
        int resultArrayLinks = urlList.size();
        System.out.println("Количество уникальных ссылок:" + resultArrayLinks);
        ArrayList<String> badUrlList = new ArrayList<>();
        for (int i = 0; i < resultArrayLinks; i++) {
            int statusCode = given().when().get(urlList.get(i)).getStatusCode();

            int count = i + 1;
            System.out.println("Проверена уникальная ссылка № " + count + " - URL: " + urlList.get(i) + " - вернула статус код: " + statusCode + ";");
            if (statusCode != 200) {
                badUrlList.add(urlList.get(i) + " - вернулся статус код: " + statusCode + " № ссылки: " +count+";");
            }

        }
        System.out.println(badUrlList);
        Assert.assertEquals(badUrlList.size(), 0, "Битые ссылки: " + badUrlList);
    }



    @Test*/
    public void getAllUrlsCodes() throws InterruptedException {
        openPage("https://lgcity.ru");
        pages.getLinks(pages.scanForLinks(pages.jsTagName,"https://lgcity.ru"),"https://lgcity.ru");
        pages.getBadUrlList(pages.getUniqueLinks(pages.scanForLinks(pages.jsTagName,"https://lgcity.ru")));
    }
}
