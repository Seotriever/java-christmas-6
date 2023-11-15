package christmas;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static christmas.DiscountStrategy.*;
import static christmas.Order.*;

public class OutputView {
        public static List<String> mainMenu = new ArrayList<>();
        public static List<String> dessertMenu = new ArrayList<>();

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


        public static void printMenu(HashMap<String, Integer> orderMap, int reservingDate) { // OutView출력
                int applyEvent = applyBenefit(orderMap);            // return 이벤트 적용 대상: 1, 미적용 대상: 0
                printOrder(orderMap);           //주문메뉴 출력
                System.out.println("\n<할인 전 총주문 금액>\n" + inputComma(printOriginalTotalPrice(orderMap)) + "원\n");
                int originalTotalPrice = printOriginalTotalPrice(orderMap);      //및 할인전 금액 return

                existGift(originalTotalPrice); // 증정 메뉴 출력
                int totalDiscount = benefitList(orderMap, reservingDate,applyEvent); //혜택 내역 출력 및 할인 금액 return
                System.out.println("<총혜택 금액>\n" + inputComma(-totalDiscount)+"원\n");    //총 혜택 금액 출력
                int resultPrice = originalTotalPrice-totalDiscount;
                System.out.println("<할인 후 예상 결제 금액>\n"+inputComma(resultPrice)+"원\n");  //할인 후 예상 결제 금액 출력
                giftBadge(resultPrice);         // 이벤트 베지 출력
        }
        public static void printOrder(HashMap<String, Integer> orderMap) {      //주문메뉴 출력
                System.out.println("<주문 메뉴>");
                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String menu = entry.getKey();
                        int quantity = entry.getValue();
                        System.out.println(menu + " " + quantity + "개");
                }
        }
        public static int printOriginalTotalPrice(HashMap<String, Integer> orderMap) { // 할인 전 총주문 금액
                int price = calculateOriginalTotalPrice(orderMap);
                return price;
        }
        public static void existGift(int originalTotalPrice) {
                System.out.println("<증정 메뉴>");
                int existGift = 0;
                if (originalTotalPrice >= 120000) {
                        existGift++;
                }
                if (existGift == 1) {
                        System.out.println("샴페인 1개\n");
                }
                if (existGift != 1) {
                        System.out.println("없음\n");
                }
        }
        public static int benefitList(HashMap<String, Integer> orderMap, int reservingDate,int applyEvent) {
                int totalDiscount = 0;
                System.out.println("<혜택 내역>");
                if (!isApplyEvent(applyEvent)) {
                        System.out.println("없음\n");
                        return applyEvent;
                }
                int dDayDiscount = dDayDiscount( reservingDate);                // 크리스마스 디데이 할인
                int dayCategory = dayCategory(orderMap, reservingDate);       // 평일or주말 할인
                int specialDiscount = specialDiscount(reservingDate);              // 특별 할인
                int GiftEvent = GiftEvent(printOriginalTotalPrice(orderMap)); // 증정이벤트
                totalDiscount = dDayDiscount + dayCategory + specialDiscount + GiftEvent;
                return Math.abs(totalDiscount);
        }
        public static int giftBadge(int resultPrice) {
                if (resultPrice > 20000) {
                        System.out.println("<12월 이벤트 배지>\n산타\n");
                        return 3;
                }
                if (resultPrice > 10000) {
                        System.out.println("<12월 이벤트 배지>\n트리\n");
                        return 2;
                }
                if (resultPrice > 5000) {
                        System.out.println("<12월 이벤트 배지>\n별\n");
                        return 1;
                }
                System.out.println("<12월 이벤트 배지>\n없음\n");
                return 0;
        }

        public static int calculateOriginalTotalPrice(HashMap<String, Integer> orderMap) {
                int total = 0;

                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String menuName = entry.getKey();
                        int quantity = entry.getValue();
                        if (menuMap.containsKey(menuName)) {
                                MenuDetail menuDetail = menuMap.get(menuName);
                                int menuTotalPrice = menuDetail.getPrice() * quantity;
                                total += menuTotalPrice;
                        }
                }
                return total;
        }

        public static String inputComma(int Price) {            // 가격에 , 넣기
                DecimalFormat df = new DecimalFormat("###,###");
                String inputCommaPrice = df.format(Price);
                return inputCommaPrice;
        }
}