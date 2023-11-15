package christmas;
import java.util.*;
import static christmas.InputView.readOrder;

class Order {
        private static Map<String, MenuInfo> menuMap;
        private static List<String> exceptDrinkMap;


        public Order() {
                menuMap = new HashMap<>();
                exceptDrinkMap = new ArrayList<>();

                initializeMenu();
                initializeExceptDrink();
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
                limitQuantity(totalQuantity);   // 총 주문 갯수가 20개가 넘을 때 재주문
                return orderMap;
        }
        public static void limitQuantity(int totalQuantity) {
                try {
                        if (totalQuantity > 20) {
                                throw new IllegalArgumentException();
                        }
                } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        readOrder();
                }
        }

        public static void validateOrder(
                String[] parts, String orderName, String quantity, Map<String, Integer> orderMap) {       // todo 메서드 리펙토링(복합)
                try {
                        if (!menuMap.containsKey(orderName)) {
                                throw new IllegalArgumentException();
                        }
                        isValidNumeric(quantity);               // 갯수 유효성 검사
                        checkDuplicate(orderName, orderMap);    // 주문 중복 검사
                        checkOrderType(parts);          //주문-갯수 양식 검사
                } catch (IllegalArgumentException e) {
                        System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                        readOrder();
                }
        }


        public static void checkOrderType(String[] parts) {         //todo 메서드 리펙토링(형태)
                        if (parts.length != 2) {
                                System.out.println("[ERROR] 주문형태");
                                throw new IllegalArgumentException();
                        }
        }
        public static void checkDuplicate(String order, Map<String, Integer> orderMap) {
                if (orderMap.containsKey(order)) {
                        System.out.println("이미 주문된 메뉴입니다.");
                        throw new IllegalArgumentException();
                }
        }
        public static void isValidNumeric(String quantity) {int toIntParts = Integer.parseInt(quantity);
                        Integer.parseInt(quantity);
                        if (!(toIntParts > 0 && toIntParts < 21)) {
                                throw new IllegalArgumentException();
                        }
        }
        public static void isNotOnlyDrink(HashMap<String, Integer> orderMap) {
                try {
                        if (orderOnlyDrink(orderMap) == false) {
                                System.out.println("[ERROR] 음료만 주문 시, 주문할 수 없습니다.");
                                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                                throw new IllegalArgumentException();
                        }
                } catch (IllegalArgumentException e) {
                        readOrder();
                }
        }
        public static boolean orderOnlyDrink(HashMap<String, Integer> orderMap) {
                Set<String> set = orderMap.keySet();
                for (String key : set) {
                        if (exceptDrinkMap.contains(key)) {
                                return true;
                        }
                }
                return false;
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
}