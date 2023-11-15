package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;

public class InputView {
        //        ========================================== 날짜 입력 ============================================
        public static int readDate() {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                try {
                        if (validateDate(input) == false) {
                                readDate();
                        }
                        tryParseIntInput(input);
                        System.out.println(tryParseIntInput(input));
                        return tryParseIntInput(input);
                } catch (IllegalArgumentException e) {
                }
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                return readDate();
        }

        public static boolean validateDate(String input) {
                try {
                        int parseInt = tryParseIntInput(input);
                        if (parseInt < 1 || parseInt > 31) {
                                throw new IllegalArgumentException();
                        }
                } catch (NumberFormatException e) {
                        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."); //todo q1 여기서 뜨게 하고 싶음
                }
                return true;
        }
        public static int tryParseIntInput(String input) {
                int intInput = Integer.parseInt(input);
                return intInput;
        }
        //      ==================================== 주문 입력 ============================================
//        public static HashMap<String, Integer> readOrder() {
//                return Order.readOrder();
//        }
        public static HashMap<String, Integer> readOrder() {
                Order order = new Order();
                order.initializeMenu(); // 메뉴 초기화 선언
                order.initializeExceptDrink();  // 음료제외 메뉴 초기화

                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요." +
                        " (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String orderInput = Console.readLine();
                HashMap<String, Integer> orderMap = Order.orderList(orderInput);
                return orderMap;
        }

}