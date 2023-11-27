package christmas;

import static christmas.InputView.readDate;
import static christmas.InputView.readOrder;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int reservingDate = readDate();
        readOrder();
        System.out.println("12월 " +reservingDate+"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

    }
}
