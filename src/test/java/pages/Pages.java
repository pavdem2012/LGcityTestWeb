package pages;

public class Pages {
    //Блок новостей на главной странице
    public static String newsBlock = "news";
    //Элемент блока новостей на Главной странице
    public static String newsItemBlock = "//div[@class = 'news__item']";
    //Элемент блока новостей на главной странице с исключением
    public static String newsItemWithException = "//div[@class = 'news__item']//a[not(contains(text(), 'Бестселлеры коллекций'))]";
    //Заголовок страницы новостей
    public static String newsPageBlockTitle = "//div[@class='news-page__back-title']";
}
