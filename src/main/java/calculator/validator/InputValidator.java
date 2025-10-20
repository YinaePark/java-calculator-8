    package calculator.validator;

    public class InputValidator {

        public void requireNotEmptyToken(String token) {
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }
        }

        public long parseLongOrThrow(String token) {
            try {
                return Long.parseLong(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("커스텀 구분자, 숫자가 아닌 값이 포함되어 있습니다: " + token);
            }
        }

        public void requireValidCustomHeader(int delimiterEndIndex) {
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 입력 형식입니다.");
            }
        }

        public void requireNoOverflow(long a, long b) {
            // 양수만 더하지만, 방어적으로 일반 케이스까지 처리
            if (b > 0 && a > Long.MAX_VALUE - b) {
                throw new IllegalArgumentException("계산 결과가 Long 범위를 초과합니다.");
            }
            if (b < 0 && a < Long.MIN_VALUE - b) {
                throw new IllegalArgumentException("계산 결과가 Long 범위를 초과합니다.");
            }
        }

        public void requireNonEmptyDelimiter(String delimiter) {
            if (delimiter == null || delimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }
        }

        public void requireNonNegative(long number) {
            if (number < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + number);
            }
        }
    }