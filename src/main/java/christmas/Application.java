package christmas;

<<<<<<< HEAD
import java.util.HashMap;

import static christmas.InputView.readDate;
import static christmas.InputView.readOrder;
import static christmas.OutputView.printMenu;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        int reservingDate = readDate();       // 예약 날짜입력 메소드       return 날짜
        HashMap<String, Integer> orderMap = readOrder();      // 주문 메서드   return orderMap<주문메뉴, 갯수>
        System.out.println("12월" + reservingDate +"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        //  ================================================= OutputView  구현
        printMenu(orderMap, reservingDate);

=======
public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
>>>>>>> a5fd319 (feat: setup precourse christmas project)
    }
}
