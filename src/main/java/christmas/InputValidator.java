package christmas;

class InputValidator {
    public static int tryParseIntDate(String input) {
        int intDate;
        try {
            intDate = Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 숫자만 입력해주세요.");
            throw  new IllegalArgumentException();
        }
        return intDate;
    }
    public static boolean validateDate(String input) {
        try {
            if (tryParseIntDate(input) < 1 || tryParseIntDate(input) > 31) {
                System.out.println("[ERROR] 유효한 날짜의 숫자만 입력해주세요.");
                throw new IllegalArgumentException();
            }
            tryParseIntDate(input);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            return false;
        }
        return true;

    }
}
