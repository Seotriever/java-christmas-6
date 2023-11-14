package christmas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.Order.*;

public class OutputView {
        private static List<String> mainMenu = new ArrayList<>();
        private static List<String> dessertMenu = new ArrayList<>();

        static {
                initializeMainMenu();
                initializeDessertMenu();
        }
        public static void initializeMainMenu() {
                mainMenu.add("티본스테이크");
                mainMenu.add("바비큐립");
                mainMenu.add("해산물파스타");
                mainMenu.add("크리스마스파스타");
        }
        public static void initializeDessertMenu() {
                dessertMenu.add("초코케이크");
                dessertMenu.add("아이스크림");
        }


        public static void printMenu(HashMap<String, Integer> orderMap, int reservingDate) {// OutView출력
                printOrder(orderMap);           //주문메뉴 출력
                printOriginalTotalPrice(orderMap);      //todo 할인 전 총주문 금액 출력 / 상황보고 print()로변경
                int applyEvent = applyBenefit(orderMap);            // 이벤트 적용 대상: 1, 미적용 대상: 0

                existGift(orderMap, Integer.parseInt(printOriginalTotalPrice(orderMap)),applyEvent);            // 증정 메뉴 출력

                System.out.println("<혜택 내역>");
                dDayDiscount(applyEvent,reservingDate);

                //주말일때 할인
                int weekend = 0;
                if (reservingDate % 7 == 1 || reservingDate % 7 == 2) {
                        weekend = 1;
                }
                int mainMenuItems = existMenu(orderMap, mainMenu);
                System.out.println("메인 메뉴 주문 내역: " + mainMenuItems);


//                List<String> dessertMenuItems = extractMenu(orderMap, dessertMenu);
//                System.out.println("디저트 주문 내역: " + dessertMenuItems);
        }

        public static void dDayDiscount(int applyEvent, int reservingDate) {
                if (applyEvent == 0) {
                        System.out.println("없음");
                }
                String dDayDiscount = inputComma(900+reservingDate*100);               //todo 하드코딩 바꾸기
                System.out.println("크리스마스 디데이 할인: -"+dDayDiscount+ "원");
        }
        
        
        public static int existMenu(HashMap<String, Integer> orderMap, List<String> menuCategory) {

                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String item = entry.getKey();
                        if (menuCategory.contains(item)) {
                                return entry.getValue()*2023;
                        }
                }return 0;
        }
        //======================================== 주문메뉴,할인전 금액 출력
        public static void printOrder(HashMap<String, Integer> orderMap) {      //주문메뉴 출력
                System.out.println("<주문 메뉴>");
                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String menu = entry.getKey();
                        int quantity = entry.getValue();
                        System.out.println(menu +" " + quantity + "개");
                }
        }
        public static String printOriginalTotalPrice(HashMap<String, Integer> orderMap) {// 할인 전 총주문 금액
                int price = calculateOriginalTotalPrice(orderMap);
                String priceWithComma = inputComma(price);
                System.out.println("\n<할인 전 총주문 금액>\n" + priceWithComma + "원\n");
                return priceWithComma;
        }

        public static String inputComma(int Price) {            // 가격에 , 넣기
                DecimalFormat df = new DecimalFormat("###,###");
                String inputCommaPrice = df.format(Price);
                return inputCommaPrice;
        }
        //                                      주문메뉴,할인전 금액끝 ========================================

        public static int applyBenefit(HashMap<String, Integer> orderMap) {        // 최소금액 확인
                int ex = Integer.parseInt(printOriginalTotalPrice(orderMap).replace(",", ""));
                if (ex < 10000) {
                        return 0;
                }
                return 1;
        }
//System.out.println("[ERROR] 음료만 주문 시, 주문할 수 없습니다.\n");

//        isValidNumeric(quantity);               // 갯수 유효성 검사
//        checkDuplicate(orderName, orderMap);    // 주문 중복 예외처리
//        validOrderType(parts);          //주문-갯수 양식 예외처리
//        orderOnlyDrink(parts);          //음료만 시키면 예외처리
        public static void existGift(HashMap<String, Integer> orderMap,int originalTotalPrice,int applyEvent) {
                System.out.println("<증정 메뉴>");
//                int originalTotalPrice = calculateOriginalTotalPrice(orderMap);       //todo 확인후 삭제
                int existGift = 0;
                if (originalTotalPrice >= 120000) {
                        existGift ++;
                }
                if (existGift == 1) {
                        System.out.println("샴페인 1개\n");
                }
                if (existGift != 1) {
                        System.out.println("없음\n");
                }
                if (applyEvent == 0) {
                        System.out.println("없음\n");
                }
        }
}