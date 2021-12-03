package tests;

import common.Settings;
import common.TestData;

import org.testng.Assert;
import org.testng.annotations.Test;


@Test
public class TestLeftMenu extends Settings {
    public void testLeftMenu() throws InterruptedException {
        openWithCloseCookie(TestData.getProperty("baseUrl"));
        waitVisibilityElement(favoritePage.menuItem,"элемент подкатегории товаров");
        favoritePage.selectRandomMenu();
        System.out.println(getUrl());
        int countCartOnPage = catalogListPage.cardsList.size();
        System.out.println("Количество карточек на странице: "+countCartOnPage);
        double countHeader =Integer.parseInt(catalogListPage.countListingSubCategory.getText().replaceAll("[^\\d+]",""));
        System.out.println("Счетчик товаров в заголовке: "+countHeader);
        double countPage = Integer.parseInt(catalogListPage.paginationLast.getText());
        System.out.println("Количество страниц в пагинации: "+countPage);
        double checkPages = Math.ceil(countHeader/countCartOnPage);
        System.out.println("Проверочное число количества страниц в пагинацмии: "+checkPages);
        wait(1);
        double countFilterSubmitBtn = Integer.parseInt(catalogListPage.countFilterSubmitBtn.getText().replaceAll("[^\\d+]",""));
        System.out.println("Счетчик товаров в кнопке 'Показать' в блоке фильтров: " + countFilterSubmitBtn);
        Assert.assertEquals(countPage,checkPages,"Количество товаров не соответствует количеству страниц");
        Assert.assertEquals(countHeader,countFilterSubmitBtn,"Счетчик товаров в заголовке не совпадает со счетчиком товаров в кнопке 'Показать'");
        int leftMenuItems = catalogListPage.leftMenuItemList.size();
        int randomItem;
        randomItem = getRandom(leftMenuItems);
        clickElement(catalogListPage.leftMenuItemList.get(randomItem),"рандомный элемента меню");
        System.out.println("Наименование товара в рандомном элементе левого меню: "+(catalogListPage.leftMenuItemList.get(randomItem).getText().replaceAll("[0-9]","")));
        int countRandomLeftMenuItem= Integer.parseInt(catalogListPage.leftMenuItemList.get(randomItem).getText().replaceAll("[^\\d+]",""));
        System.out.println("Счетчик товаров в рандомном элементе левого меню: "+countRandomLeftMenuItem);
        int subcategoryLeftMenuItems =catalogListPage.countSubItemLeftMenuList.size();
        System.out.println("Количество подкатегорий рандомного элемента меню: "+subcategoryLeftMenuItems);
        int check=0;
        for (int i = 0; i<subcategoryLeftMenuItems;i++){
            int countSubcategoryItem = Integer.parseInt(catalogListPage.countSubItemLeftMenuList.get(i).getText());
            check+=countSubcategoryItem;
        }
        System.out.println("Сумма счетчиков подкатегорий рандомного элемента меню: "+check);
        Assert.assertEquals(countRandomLeftMenuItem,check, "Не совпадает счетчик рандомного элемента левого меню и сумма счетчиков входящих в него подкатегорий ");
        int randomSubItem = getRandom(subcategoryLeftMenuItems);
        System.out.println(randomSubItem);
        String subcategoryLeftMenuName =  catalogListPage.leftMenuSubItemList.get(randomSubItem).getText().toLowerCase().replaceAll("[0-9]","");
        System.out.println("Наименование товара рандомной подкатегории рандомного элементе левого меню: " + subcategoryLeftMenuName);
        int countSubcategoryLeftMenu = Integer.parseInt(catalogListPage.countSubItemLeftMenuList.get(randomSubItem).getText());
        System.out.println("Счетчик товара рандомной подкатегории рандомного элементе левого меню: " + countSubcategoryLeftMenu);
        clickElement(catalogListPage.leftMenuSubItemList.get(randomSubItem),"");
        String randomPageHeader =pages.getTitle();
        System.out.println(randomPageHeader);
        assertString(randomPageHeader,subcategoryLeftMenuName);
        int countRandomPageHeader =Integer.parseInt(catalogListPage.countListingSubCategory.getText().replaceAll("[^\\d+]",""));
        System.out.println("Счетчик товаров заголовка страницы при переходе из субкатегории: "+countRandomPageHeader);
        Assert.assertEquals(countSubcategoryLeftMenu,countRandomPageHeader,"Не совпадает счетчик субкатегории левого меню и счетчик заголовка страницы на которую она ведет");
        int countRandomPageFilterSubmitBtn = Integer.parseInt(catalogListPage.countFilterSubmitBtn.getText().replaceAll("[^\\d+]",""));
        System.out.println("Счетчик товаров в кнопке 'Показать' в блоке фильтров: "+countRandomPageFilterSubmitBtn);
        Assert.assertEquals(countRandomPageHeader,countRandomPageFilterSubmitBtn,"Счетчик товаров в заголовке не совпадает со счетчиком товаров в кнопке 'Показать'");
    }
}
