package christmas;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class InputTest {

        @Test
        void 유효한_날짜_입력() {
                String input = "3";
                assertThatNoException().isThrownBy(() -> {
                        InputView.validateDate(input);
                });
        }

        @Test
        void 유효한_메뉴_입력() {

        }
}
