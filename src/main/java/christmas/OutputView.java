package christmas;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static christmas.Order.calculateOriginalTotalPrice;

public class OutputView {
        public static void printMenu(HashMap<String, Integer> orderMap, int reservingDate) {// OutView출력

                printOrder(orderMap);           //주문메뉴 출력

                printOriginalTotalPrice(orderMap);      //todo 할인 전 총주문 금액 출력 / 상황보고 print()로변경

                System.out.println("<증정 메뉴>");
                existGift(orderMap);


        }

        public static void existGift(HashMap<String, Integer> orderMap) {
                int originalTotalPrice = calculateOriginalTotalPrice(orderMap);
                int existGift = 0;
                if (originalTotalPrice >= 120000) {
                        existGift ++;
                }
                if (existGift == 1) {
                System.out.println("샴페인 1개");
                }
                if (existGift != 1) {
                        System.out.println("없음");
                }
        }

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
}