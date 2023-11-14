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
                System.out.println("\n<할인 전 총주문 금액>\n" + printOriginalTotalPrice(orderMap) + "원\n");
                printOriginalTotalPrice(orderMap);      //todo 할인 전 총주문 금액 출력 / 상황보고 print()로변경
                int applyEvent = applyBenefit(orderMap);            // 이벤트 적용 대상: 1, 미적용 대상: 0

                existGift(convertToIntWithoutComma(printOriginalTotalPrice(orderMap)), applyEvent);
                // 증정           // 증정 메뉴 출력
                int totalDiscount = benefitList(orderMap, reservingDate, applyEvent); //혜택 내역
                System.out.println("<총혜택 금액>\n-" + inputComma(totalDiscount)+"원");

        }

        public static int benefitList(HashMap<String, Integer> orderMap, int reservingDate, int applyEvent) {
                int totalDiscount = 0;
                System.out.println("<혜택 내역>");          //todo 추후 혜택통합 메서드 생성? / 없음 조건문 만들어야함
                int dDayDiscount = dDayDiscount(applyEvent, reservingDate, totalDiscount);                // 크리스마스 디데이 할인
                int dayCategory = dayCategory(orderMap, reservingDate, applyEvent, totalDiscount);       // 평일or주말 할인
                int specialDiscount = specialDiscount(reservingDate, applyEvent, totalDiscount);              // 특별 할인
                int GiftEvent = GiftEvent(convertToIntWithoutComma(printOriginalTotalPrice(orderMap)), totalDiscount); // 증정이벤트
                totalDiscount = dDayDiscount + dayCategory + specialDiscount + GiftEvent;

                return totalDiscount;
        }

        public static int GiftEvent(int originalTotalPrice, int totalDiscount) {
                if (originalTotalPrice > 120000) {
                        System.out.println("증정 이벤트: -25,000원\n");
                        totalDiscount += 25000;
                        return totalDiscount;
                }
                if (originalTotalPrice <= 120000) {
                        System.out.println("증정 이벤트: 없음");
                }
                return totalDiscount;
        }

        public static int dayCategory(HashMap<String, Integer> orderMap, int reservingDate, int applyEvent, int totalDiscount) {
                int dayType = isWeekend(reservingDate);
                if (dayType == 0) {
                        int discountDessert = menuDiscount(orderMap, dessertMenu);
                        System.out.println("평일 할인: -" + inputComma(discountDessert) + "원");
                        return totalDiscount += discountDessert;
                }
                if (dayType == 1) {
                        int discountMainMenu = menuDiscount(orderMap, mainMenu);
                        System.out.println("주말 할인: -" + inputComma(discountMainMenu) + "원");
                        return totalDiscount += discountMainMenu;
                }
                return totalDiscount;
        }

        public static int specialDiscount(int reservingDate, int applyEvent, int totalDiscount) {
                int dayType = 0;
                if (reservingDate % 7 == 3 || reservingDate == 25) {
                        dayType = 1;
                }
                if (dayType == 1) {
                        totalDiscount += 1000;
                        System.out.println("특별 할인: -1,000원");
                }
                return totalDiscount;
        }

        public static int isWeekend(int reservingDate) {
                int dayType = 0;        //평일
                if (reservingDate % 7 == 1 || reservingDate % 7 == 2) {
                        dayType = 1;    //금토
                }
                return dayType;
        }

        public static int dDayDiscount(int applyEvent, int reservingDate, int totalDiscount) {
                if (reservingDate > 25 || applyEvent == 0) {
                        System.out.println("없음");
                        totalDiscount += 0;
                        return totalDiscount;
                }
                String dDayDiscount = inputComma(900 + reservingDate * 100);               //todo 하드코딩 바꾸기
                totalDiscount += 900 + reservingDate * 100;
                System.out.println("크리스마스 디데이 할인: -" + dDayDiscount + "원");
                return totalDiscount;
        }


        public static int menuDiscount(HashMap<String, Integer> orderMap, List<String> menuCategory) {
                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String item = entry.getKey();
                        if (menuCategory.contains(item)) {
                                return entry.getValue() * 2023;
                        }
                }
                return 0;
        }

        //======================================== 주문메뉴,할인전 금액 출력
        public static void printOrder(HashMap<String, Integer> orderMap) {      //주문메뉴 출력
                System.out.println("<주문 메뉴>");
                for (Map.Entry<String, Integer> entry : orderMap.entrySet()) {
                        String menu = entry.getKey();
                        int quantity = entry.getValue();
                        System.out.println(menu + " " + quantity + "개");
                }
        }

        public static String printOriginalTotalPrice(HashMap<String, Integer> orderMap) {// 할인 전 총주문 금액
                int price = calculateOriginalTotalPrice(orderMap);
                String priceWithComma = inputComma(price);
//                System.out.println("\n<할인 전 총주문 금액>\n" + priceWithComma + "원\n");
                return priceWithComma;
        }

        public static String inputComma(int Price) {            // 가격에 , 넣기
                DecimalFormat df = new DecimalFormat("###,###");
                String inputCommaPrice = df.format(Price);
                return inputCommaPrice;
        }
        //                                      주문메뉴,할인전 금액끝 ========================================

        public static int applyBenefit(HashMap<String, Integer> orderMap) {        // 최소금액 확인
                int ex = convertToIntWithoutComma(printOriginalTotalPrice(orderMap));
                if (ex < 10000) {
                        return 0;       // 이벤트 미 적용
                }
                return 1;               // 이벤트 적용
        }

        public static int convertToIntWithoutComma(String priceWithComma) {
                // ,를 없애고 정수로 변환
                int priceWithoutComma = Integer.parseInt(priceWithComma.replace(",", ""));
                return priceWithoutComma;
        }

        public static void existGift(int originalTotalPrice, int applyEvent) {
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
//                if (applyEvent == 0) {
//                        System.out.println("없음\n");
//                }
        }
}