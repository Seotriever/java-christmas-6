package christmas;

import java.util.HashMap;
import java.util.Map;

import static christmas.Order.menuMap;

public class InputValidator {
    public static boolean validateDate(String input) {
        try {
            int parseInt = tryParseIntInput(input);
            if (parseInt < 1 || parseInt > 31) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
        return true;
    }

    public static int tryParseIntInput(String input) {
        int intInput = Integer.parseInt(input);
        return intInput;
    }
}
