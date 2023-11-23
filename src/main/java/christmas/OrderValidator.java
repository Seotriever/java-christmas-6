package christmas;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static christmas.InputView.readOrder;
import static christmas.Order.exceptDrinkMap;
import static christmas.Order.menuMap;

public class OrderValidator {
    public static HashMap<String , Integer> limitQuantity(int totalQuantity, HashMap<String, Integer> orderMap) {
        try {
            if (totalQuantity > 20) {
                throw new IllegalArgumentException();
            }
            return orderMap;
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return readOrder();
        }
    }

    public static void validateOrder(
            String[] parts, String orderName, String quantity, Map<String, Integer> orderMap) {       // todo 메서드 리펙토링(복합)
        try {
            if (!menuMap.containsKey(orderName)) {
                throw new IllegalArgumentException();
            }
            isValidNumeric(quantity);               // 갯수 유효성 검사
            checkDuplicate(orderName, orderMap);    // 주문 중복 검사
            checkOrderType(parts);          //주문-갯수 양식 검사
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            readOrder();
        }
    }


    public static void checkOrderType(String[] parts) {         //todo 메서드 리펙토링(형태)
        if (parts.length != 2) {
            System.out.println("[ERROR] 주문형태를 지켜서 입력해주세요");
            throw new IllegalArgumentException();
        }
    }
    public static void checkDuplicate(String order, Map<String, Integer> orderMap) {
        if (orderMap.containsKey(order)) {
            System.out.println("이미 주문된 메뉴입니다.");
            throw new IllegalArgumentException();
        }
    }
    public static void isValidNumeric(String quantity) {int toIntParts = Integer.parseInt(quantity);
        Integer.parseInt(quantity);
        if (!(toIntParts > 0 && toIntParts < 21)) {
            throw new IllegalArgumentException();
        }
    }
    public static void isNotOnlyDrink(HashMap<String, Integer> orderMap) {
        try {
            if (orderOnlyDrink(orderMap) == false) {
                System.out.println("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            readOrder();
        }
    }
    public static boolean orderOnlyDrink(HashMap<String, Integer> orderMap) {
        Set<String> set = orderMap.keySet();
        for (String key : set) {
            if (exceptDrinkMap.contains(key)) {
                return true;
            }
        }
        return false;
    }

}
