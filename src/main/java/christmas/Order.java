package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.HashMap;
import java.util.Map;

class Order {
        private static Map<String, MenuInfo> menuMap;
        private Map<String, Integer> orderItems;

        public Order() {
                menuMap = new HashMap<>();
                orderItems = new HashMap<>();
                initializeMenu();
        }
        public void initializeMenu() {         // 메뉴 선언
                menuMap.put("양송이수프", new MenuInfo("양송이수프", 6000));
                menuMap.put("타파스", new MenuInfo("타파스", 5500));
                menuMap.put("시저샐러드", new MenuInfo("시저샐러드", 8000));
                menuMap.put("티본스테이크", new MenuInfo("티본스테이크", 55000));
                menuMap.put("바비큐립", new MenuInfo("바비큐립", 54000));
                menuMap.put("해산물파스타", new MenuInfo("해산물파스타", 35000));
                menuMap.put("크리스마스파스타", new MenuInfo("크리스마스파스타", 25000));
                menuMap.put("초코케이크", new MenuInfo("초코케이크", 15000));
                menuMap.put("아이스크림", new MenuInfo("아이스크림", 5000));
                menuMap.put("제로콜라", new MenuInfo("제로콜라", 3000));
                menuMap.put("레드와인", new MenuInfo("레드와인", 60000));
                menuMap.put("샴페인", new MenuInfo("샴페인", 25000));
        }

        public static HashMap<String, Integer> readOrder() {
                Order order = new Order();
                order.initializeMenu();
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String orderInput = Console.readLine();
                HashMap<String, Integer> orderMap = new HashMap<>();            // 주문할 오더테이블
                String[] items = orderInput.trim().split(",");
                for (String item : items) {
                        String[] parts = item.trim().split("-");
                        validateOrder(parts,parts[0],parts[1],orderMap);
                        orderMap.put(parts[0], Integer.parseInt(parts[1]));
//                        System.out.println("주문내역 확인값"+parts[0] + " " + parts[1] + "개");    //todo 주문내역 제출전 삭제
                }
                return orderMap;
        }

        public static int calculateOriginalTotalPrice(HashMap<String, Integer> orderMap) {
                int total = 0;

                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String menuName = entry.getKey();
                        int quantity = entry.getValue();
                        if (menuMap.containsKey(menuName)) {
                                MenuInfo menuInfo = menuMap.get(menuName);
                                int menuTotalPrice = menuInfo.getPrice() * quantity;
                                total += menuTotalPrice;
                        }
                }
                return total;
        }



        public static void validateOrder(String[] parts, String order, String quantity, Map<String, Integer> orderMap) {       // todo 메서드 리펙토링(복합)
                try {
                        if (!menuMap.containsKey(order)) {
                                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                                throw new IllegalArgumentException();           
                        }
                        isValidNumeric(quantity);
                        checkDuplicate(order, orderMap);
                        validOrderType(parts);
                } catch (IllegalArgumentException e) {
                        readOrder();
                }
        }
        public static void validOrderType(String[] parts) {         //todo 메서드 리펙토링(형태)
                try {
                        if (parts.length != 2) {
                                throw new IllegalArgumentException();
                        }
                } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        readOrder();
                }
        }
        public static void checkDuplicate(String order, Map<String, Integer> orderMap) {
                if (orderMap.containsKey(order)) {
                        System.out.println("이미 주문된 메뉴입니다.");
                        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        throw new IllegalArgumentException();
                }
        }
        public static void isValidNumeric(String quantity) {
                try {
                        int toIntParts = Integer.parseInt(quantity);
                        Integer.parseInt(quantity);
                        if (!(toIntParts > 0 && toIntParts < 21)) {
                                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                                throw new IllegalArgumentException();
                        }
                } catch (NumberFormatException e) {
                        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        throw new IllegalArgumentException();
                }
        }
//        public static void main(String[] args) {                // 주문 내역 테스트를 위한 main()
//                Order order = new Order();
//                order.initializeMenu(); // 메뉴초기화
//
//                order.inputOrder();
//        }
}
