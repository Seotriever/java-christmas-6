package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;

import static christmas.InputValidator.tryParseIntDate;
import static christmas.InputValidator.validateDate;

public class InputView {
    public static int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        if (validateDate(input) == false) {
            return readDate();
        }
        return tryParseIntDate(input);
    }



}