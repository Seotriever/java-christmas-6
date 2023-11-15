package christmas;

class MenuDetail {

        private String name;
        private int price;

        public MenuDetail(String name, int price) {
                this.name = name;
                this.price = price;
        }

        public String getName() {       //추후 map 비교 사용
                return name;
        }

        public int getPrice() {
                return price;
        }
}