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
                System.out.println("\n<할인 전 총주문 금액>\n" + inputComma(printOriginalTotalPrice(orderMap)) + "원\n");
                int originalTotalPrice = printOriginalTotalPrice(orderMap);      //\할인 전 총주문 금액 출력 및 할인전 금액 return
                int applyEvent = applyBenefit(orderMap);            // return 이벤트 적용 대상: 1, 미적용 대상: 0
                existGift(printOriginalTotalPrice(orderMap)); // 증정 메뉴 출력
                int totalDiscount = benefitList(orderMap, reservingDate,applyEvent); //혜택 내역 출력 및 할인 금액 return
                System.out.println("<총혜택 금액>\n" + inputComma(-totalDiscount)+"원\n");    //총 혜택 금액

                int resultPrice = originalTotalPrice-totalDiscount;
                System.out.println("<할인 후 예상 결제 금액>\n"+inputComma(resultPrice)+"원\n");
                giftBadge(resultPrice);
        }
        //todo 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용

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
        public static int benefitList(HashMap<String, Integer> orderMap, int reservingDate,int applyEvent) {
                int totalDiscount = 0;
                System.out.println("<혜택 내역>");
                if (!isApplyEvent(applyEvent)) {
                        System.out.println("없음\n");
                        return totalDiscount;
                }
                int dDayDiscount = dDayDiscount( reservingDate);                // 크리스마스 디데이 할인
                int dayCategory = dayCategory(orderMap, reservingDate);       // 평일or주말 할인
                int specialDiscount = specialDiscount(reservingDate);              // 특별 할인
                int GiftEvent = GiftEvent(printOriginalTotalPrice(orderMap)); // 증정이벤트
                totalDiscount = dDayDiscount + dayCategory + specialDiscount + GiftEvent;
                return Math.abs(totalDiscount);
        }

        public static boolean isApplyEvent(int applyEvent) {
                if (applyEvent == 0) {
                        return false;
                }
                return true;
        }

        public static int GiftEvent(int originalTotalPrice) {
                int discount = 0;
                if (originalTotalPrice > 120000) {
                        System.out.println("증정 이벤트: -25,000원\n");
                        discount += 25000;
                        return discount;
                }
                if (originalTotalPrice <= 120000) {
                        System.out.println("증정 이벤트: 0원\n");
                }
                return discount;
        }

        public static int dayCategory(HashMap<String, Integer> orderMap, int reservingDate) {
                int discount = 0;
                int dayType = isWeekend(reservingDate);
                if (dayType == 0) {
                        int discountDessert = menuDiscount(orderMap, dessertMenu);
                        System.out.println("평일 할인: " + inputComma(-discountDessert) + "원");
                        return discount += discountDessert;
                }
                if (dayType == 1) {
                        int discountMainMenu = menuDiscount(orderMap, mainMenu);
                        System.out.println("주말 할인: " + inputComma(-discountMainMenu) + "원");
                        return discount += discountMainMenu;
                }
                return discount;
        }

        public static int specialDiscount(int reservingDate) {
                int discount = 0;
                int dayType = 0;
                if (reservingDate % 7 == 3 || reservingDate == 25) {
                        dayType = 1;
                }
                if (dayType == 0) {
                        System.out.println("특별 할인: 0원");
                }
                if (dayType == 1) {
                        discount += 1000;
                        System.out.println("특별 할인: -1,000원");
                }
                return discount;
        }

        public static int isWeekend(int reservingDate) {
                int dayType = 0;        //평일
                if (reservingDate % 7 == 1 || reservingDate % 7 == 2) {
                        dayType = 1;    //금토
                }
                return dayType;
        }

        public static int dDayDiscount( int reservingDate) {
                int discount = 0;
                if (reservingDate > 25) {
                        System.out.println("크리스마스 디데이 할인: 0원");
                        discount += 0;
                        return discount;
                }
                String dDayDiscount = inputComma(900 + reservingDate * 100);               //todo 하드코딩 바꾸기
                discount += 900 + reservingDate * 100;
                System.out.println("크리스마스 디데이 할인: -" + dDayDiscount + "원");
                return discount;
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

        public static int printOriginalTotalPrice(HashMap<String, Integer> orderMap) {// 할인 전 총주문 금액
                int price = calculateOriginalTotalPrice(orderMap);
                String priceWithComma = inputComma(price);
//                System.out.println("\n<할인 전 총주문 금액>\n" + priceWithComma + "원\n");
                return price;
        }

        public static String inputComma(int Price) {            // 가격에 , 넣기
                DecimalFormat df = new DecimalFormat("###,###");
                String inputCommaPrice = df.format(Price);
                return inputCommaPrice;
        }
        //                                      주문메뉴,할인전 금액끝 ========================================

        public static int applyBenefit(HashMap<String, Integer> orderMap) {        // 최소금액 확인
                int ex = printOriginalTotalPrice(orderMap);
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
}