package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.Map;

import static christmas.InputValidator.tryParseInt;
import static christmas.InputValidator.validateDate;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        if (validateDate(input) == false) {
            return readDate();
        }
        return tryParseInt(input);
    }

    public static HashMap<String, Integer> readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Order order = new Order();
        HashMap<String, Integer> orderMap = new HashMap<>();
        String inputOrder = Console.readLine();
        orderMap = orderListMap(inputOrder, orderMap);
        return orderMap;
    }

    public static HashMap<String, Integer> orderListMap(String inputOrder, HashMap<String, Integer> orderMap) {
        orderMap = new HashMap<>();
        String[] items = inputOrder.trim().split(",");
        for (String item : items) {
            String[] parts = item.trim().split("-");
            orderMap.put(parts[0], tryParseInt(parts[1]));
        }
        System.out.println(orderMap);
        return orderMap;
    }

    public static boolean validOrder(String[] items) {
        try {
            for (String item : items) {
                String[] parts = item.trim().split("-");
                if (validOrderQuantity(tryParseInt(parts[1])) == false) {
                }
                checkDuplicate(parts[0]);
            }
        } catch (IllegalArgumentException e) {
            readOrder();
        }
        return true;
    }

    public static void checkDuplicate(String parts) {
        try {
            if (!Order.menuMap.containsKey(parts)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 중복된 메뉴가 있습니다.");
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public static boolean validOrderQuantity(int part) {
        try {
            if (part < 1 || part > 20) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 주문 가능한 개수는 20개 까지 입니다."); //todo 수정
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            return false;
        }
        return true;
    }
}