package christmas;

class MenuInfo {

        private String name;
        private int price;

        public MenuInfo(String name, int price) {
                this.name = name;
                this.price = price;
        }

        public String getName() {
                return name;
        }

        public int getPrice() {
                return price;
        }
}