package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.OutputView.*;

public class DiscountStrategy {
    public static int applyBenefit(HashMap<String, Integer> orderMap) {        // 최소금액 확인
        int ex = printOriginalTotalPrice(orderMap);
        if (ex < 10000) {
            return 0;       // 이벤트 미 적용
        }
        return 1;               // 이벤트 적용
    }
    public static boolean isApplyEvent(int applyEvent) {
        if (applyEvent == 0) {
            return false;
        }
        return true;
    }
    public static int GiftEvent(int originalTotalPrice) {
        int discount = 0;
        if (originalTotalPrice > 120000) {
            System.out.println("증정 이벤트: -25,000원\n");
            discount += 25000;
            return discount;
        }
        if (originalTotalPrice <= 120000) {
            System.out.println("증정 이벤트: 0원\n");
        }
        return discount;
    }

    public static int dayCategory(HashMap<String, Integer> orderMap, int reservingDate) {
        int discount = 0;
        int dayType = isWeekend(reservingDate);
        if (dayType == 0) {
            int discountDessert = menuDiscount(orderMap, dessertMenu);
            System.out.println("평일 할인: " + inputComma(-discountDessert) + "원");
            return discount += discountDessert;
        }
        if (dayType == 1) {
            int discountMainMenu = menuDiscount(orderMap, mainMenu);
            System.out.println("주말 할인: " + inputComma(-discountMainMenu) + "원");
            return discount += discountMainMenu;
        }
        return discount;
    }

    public static int specialDiscount(int reservingDate) {
        int discount = 0;
        int dayType = 0;
        if (reservingDate % 7 == 3 || reservingDate == 25) {
            dayType = 1;
        }
        if (dayType == 0) {
            System.out.println("특별 할인: 0원");
        }
        if (dayType == 1) {
            discount += 1000;
            System.out.println("특별 할인: -1,000원");
        }
        return discount;
    }

    public static int isWeekend(int reservingDate) {
        int dayType = 0;        //평일
        if (reservingDate % 7 == 1 || reservingDate % 7 == 2) {
            dayType = 1;    //금토
        }
        return dayType;
    }

    public static int dDayDiscount( int reservingDate) {
        int discount = 0;
        if (reservingDate > 25) {
            System.out.println("크리스마스 디데이 할인: 0원");
            discount += 0;
            return discount;
        }
        String dDayDiscount = inputComma(900 + reservingDate * 100);               //todo 하드코딩 바꾸기
        discount += 900 + reservingDate * 100;
        System.out.println("크리스마스 디데이 할인: -" + dDayDiscount + "원");
        return discount;
    }


    public static int menuDiscount(HashMap<String, Integer> orderMap, List<String> menuCategory) {
        for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
            String item = entry.getKey();
            if (menuCategory.contains(item)) {
                return entry.getValue() * 2023;
            }
        }
        return 0;
    }

}
