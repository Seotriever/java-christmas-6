package christmas;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public static Map<String, MenuInfo> menuMap;

    public Order() {
        menuMap = new HashMap<>();
    }
    public void initializeMenu() {
        menuMap.put("양송이수프", new MenuInfo("양송이수프", 6000));
        menuMap.put("타파스", new MenuInfo("타파스", 5500));
        menuMap.put("시저샐러드", new MenuInfo("시저샐러드", 8000));
        menuMap.put("티본스테이크", new MenuInfo("티본스테이크", 55000));
        menuMap.put("바비큐립", new MenuInfo("바비큐립", 54000));
        menuMap.put("해산물파스타", new MenuInfo("해산물파스타", 35000));
        menuMap.put("크리스마스파스타", new MenuInfo("크리스마스파스타", 25000));
        menuMap.put("초코케이크", new MenuInfo("초코케이크", 15000));
        menuMap.put("아이스크림", new MenuInfo("아이스크림", 5000));
        menuMap.put("제로콜라", new MenuInfo("제로콜라", 3000));
        menuMap.put("레드와인", new MenuInfo("레드와인", 60000));
        menuMap.put("샴페인", new MenuInfo("샴페인", 25000));    }
}
