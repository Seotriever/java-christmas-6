package christmas;

class OrderItems {              // 주문한 메뉴들을 저장하기 위한 클래스
        private MenuInpo menuInpo;
        private int quantity;

        public OrderItems(MenuInpo menuInpo, int quantity) {
                this.menuInpo = menuInpo;
                this.quantity = quantity;
        }
        public String getMenu() {
                return menuInpo.getName();
        }
        public int getQuantity() {
                return quantity;
        }
}
