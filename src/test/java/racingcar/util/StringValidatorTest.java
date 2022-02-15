package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class StringValidatorTest {
    @DisplayName("validateIsEmpty 메소드에 빈값이 전달되지 않으면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "1111"})
    void validateIsEmptyWithNotEmptyString(String input) {
        assertDoesNotThrow(() -> StringValidator.validateIsEmpty(input));
    }

    @DisplayName("validateIsEmpty 메소드에 빈값이 전달되면 예외가 발생 한다.")
    @ParameterizedTest
    @ValueSource(strings = {""})
    void validateIsEmptyWithEmptyString(String input) {
        assertThatThrownBy(() -> StringValidator.validateIsEmpty(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문자열은 빈값이면 안됩니다.");
    }

    @DisplayName("validateLength 메소드에 한글자 이상, 다섯글자 이하의 문자열이 전달되면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ab", "abc", "abcd", "abcde"})
    void validateIsEmptyWithValidLength(String input) {
        assertDoesNotThrow(() -> StringValidator.validateLength(input, 1, 5));
    }

    @DisplayName("validateLength 메소드에 한글자 미만, 다섯글자 초과의 문자열이 전달되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "abcdef"})
    void validateLengthWithInvalidLength(String input) {
        assertThatThrownBy(() -> StringValidator.validateLength(input, 1, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("문자열의 길이가 1 이상 5 이하가 아닙니다.");
    }

    @DisplayName("validateFormat 메소드에 허용되는 문자로만 구성된 문자열이 전달되면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"hudi", "tonic", "a", "토닉", "후디", "123"})
    void validateFormatWithValidInput(String input) {
        assertDoesNotThrow(() -> StringValidator.validateFormat(input));
    }

    @DisplayName("validateFormat 메소드에 허용되지 않는 문자가 포함된 문자열이 전달되면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {" ", "ton*&", "?a1"})
    void validateFormatWithInvalidInput(String input) {
        assertThatThrownBy(() -> StringValidator.validateFormat(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 형식의 문자열입니다.");
    }
}