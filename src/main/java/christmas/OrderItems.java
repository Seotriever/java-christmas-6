package christmas;

public class OrderItems {
        private Menu menu;
        private int quantity;

        public OrderItems(Menu menu, int quantity) {
                this.menu = menu;
                this.quantity = quantity;
        }
        public String getOrder() {
                return menu.getMenu();
        }
        public int getQuantity() {
                return quantity;
        }
}
