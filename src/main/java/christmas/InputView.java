package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        if (validateDate(input) == false) {
            return readDate();
        }
        return tryParseIntDate(input);
    }

    public static int tryParseIntDate(String input) {
        int intDate;
        try {
            intDate = Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자만 입력해주세요.");
            throw  new IllegalArgumentException();
        }
        return intDate;
    }
    public static boolean validateDate(String input) {
        try {
            if (tryParseIntDate(input) < 1 || tryParseIntDate(input) > 31) {
                System.out.println("[ERROR] 유효한 날짜의 숫자만 입력해주세요.");
                throw new IllegalArgumentException();
            }
            tryParseIntDate(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            return false;
        }
        return true;

    }
}