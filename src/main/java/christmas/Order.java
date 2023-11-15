package christmas;
import java.util.*;
import static christmas.InputView.readOrder;
import static christmas.OrderValidator.*;

class Order {
        public static Map<String, MenuDetail> menuMap;
        public static List<String> exceptDrinkMap;


        public Order() {
                menuMap = new HashMap<>();
                exceptDrinkMap = new ArrayList<>();

                initializeMenu();
                initializeExceptDrink();
        }
        public void initializeMenu() {         // 메뉴 선언
                menuMap.put("양송이수프", new MenuDetail("양송이수프", 6000));
                menuMap.put("타파스", new MenuDetail("타파스", 5500));
                menuMap.put("시저샐러드", new MenuDetail("시저샐러드", 8000));
                menuMap.put("티본스테이크", new MenuDetail("티본스테이크", 55000));
                menuMap.put("바비큐립", new MenuDetail("바비큐립", 54000));
                menuMap.put("해산물파스타", new MenuDetail("해산물파스타", 35000));
                menuMap.put("크리스마스파스타", new MenuDetail("크리스마스파스타", 25000));
                menuMap.put("초코케이크", new MenuDetail("초코케이크", 15000));
                menuMap.put("아이스크림", new MenuDetail("아이스크림", 5000));
                menuMap.put("제로콜라", new MenuDetail("제로콜라", 3000));
                menuMap.put("레드와인", new MenuDetail("레드와인", 60000));
                menuMap.put("샴페인", new MenuDetail("샴페인", 25000));
        }
        public void initializeExceptDrink() {         // 메뉴 선언
                exceptDrinkMap.add("양송이수프");
                exceptDrinkMap.add("타파스");
                exceptDrinkMap.add("시저샐러드");
                exceptDrinkMap.add("티본스테이크");
                exceptDrinkMap.add("바비큐립");
                exceptDrinkMap.add("해산물파스타");
                exceptDrinkMap.add("크리스마스파스타");
                exceptDrinkMap.add("초코케이크");
                exceptDrinkMap.add("아이스크림");
        }

        public static HashMap<String, Integer> orderList(String orderInput,HashMap<String, Integer> orderMap) {String[] items = orderInput.trim().split(",");
                int totalQuantity = 0;
                for (String item : items) {
                        String[] parts = item.trim().split("-");
                        validateOrder(parts,parts[0],parts[1],orderMap);        // 주문 통합 유효성 검사 //todo 인덱스자체가 안맞을시 대처
                        totalQuantity +=Integer.parseInt(parts[1]);
                }
                for (String item : items) {
                        String[] parts = item.trim().split("-");
                        orderMap.put(parts[0], Integer.parseInt(parts[1]));
                }
                isNotOnlyDrink(orderMap); // 음료만 주문 했을때 재주문
                return limitQuantity(totalQuantity, orderMap);  // 총 주문 갯수가 20개가 넘을 때 재주문, 정상 값일시 return : orderMap
        }

}