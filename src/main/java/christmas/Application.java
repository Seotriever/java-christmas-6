package christmas;

import java.util.HashMap;
import java.util.Map;

import static christmas.InputView.readDate;
import static christmas.InputView.readOrder;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int reservingDate = readDate();       // 예약 날짜입력 메소드       return 날짜
        HashMap<String, Integer> orderMap = readOrder();      // 주문 메서드   return orderMap<주문메뉴, 갯수>
        System.out.println(orderMap);
        System.out.println("12월" + reservingDate +"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        
        //  ================================================= OutputView  구현

    }
}
