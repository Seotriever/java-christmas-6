package christmas;

class OrderItems {              // 주문한 메뉴들을 저장하기 위한 클래스
        private MenuInfo menuInfo;
        private int quantity;

        public OrderItems(MenuInfo menuInfo, int quantity) {
                this.menuInfo = menuInfo;
                this.quantity = quantity;
        }
        public String getMenu() {
                return menuInfo.getName();
        }
        public int getQuantity() {
                return quantity;
        }
}
