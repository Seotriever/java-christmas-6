package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
        //        ========================================== 날짜 입력 ============================================
        public static void readDate() {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                if (validateDate(input) == false) {
                        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");       //todo q1. error를 예외처리에서 뜨게 하려면?
                        readDate();
                }
                tryParseIntInput(input);
                System.out.println(tryParseIntInput(input));
        }

        public static boolean validateDate(String input) {
                try {
                        int parseInt = tryParseIntInput(input);
                        if (parseInt > 0 && parseInt < 32) {
                                return true;
                        }
                        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                } catch (NumberFormatException e) {
                        System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."); //todo q1 여기서 뜨게 하고 싶음
                }
                return false;
        }
        public static int tryParseIntInput(String input) {
                int intInput = Integer.parseInt(input);
                return intInput;
        }
        //      ==================================== 주문 입력 ============================================
        public static void readOrder() {
                System.out.println("<주문 메뉴>");
                Order.inputOrder();

        }

}
