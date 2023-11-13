package christmas;

public class Menu {

        private String menu;
        private int cost;

        public Menu(String menu, int cost) {
                this.menu = menu;
                this.cost = cost;
        }

        public String getMenu() {
                return menu;
        }

        public int getCost() {
                return cost;
        }
}