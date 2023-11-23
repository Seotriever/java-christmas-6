package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;

import static christmas.InputValidator.tryParseIntInput;
import static christmas.InputValidator.validateDate;

public class InputView {
        //        ========================================== 날짜 입력 ============================================
        public static int readDate() {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                try {
                        if (validateDate(input) == false) {
                                throw new IllegalArgumentException();
                        }
                        return tryParseIntInput(input);
                } catch (IllegalArgumentException e) {
                        return readDate();
                }
        }


        //      ==================================== 주문 입력 ============================================
        public static HashMap<String, Integer> readOrder() {
                Order order = new Order();
                order.initializeMenu(); // 메뉴 초기화 선언
                order.initializeExceptDrink();  // 음료제외 메뉴 초기화

                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요." +
                        " (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                HashMap<String, Integer> orderMap = new HashMap<>();
                String orderInput = Console.readLine();
                orderMap = Order.orderList(orderInput, orderMap);
                return orderMap;
        }

}