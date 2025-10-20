package calculator.domain;

import calculator.validator.InputValidator;
import java.util.regex.Pattern;

public class StringParser {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final char CUSTOM_DELIMITER_SUFFIX = '\n';
    private static final String DEFAULT_DELIMITERS = "[,:]";
    private static final int DELIMITER_START_INDEX = 2;
    private final InputValidator validator = new InputValidator();

    public String[] parse(String input) {
        if (input == null || input.isEmpty()) {
            return new String[0];
        }

        // 정규화
        input = input.replace("\\n", "\n");

        if (hasCustomDelimiter(input)) {
            return parseWithCustomDelimiter(input);
        }

        return input.split(DEFAULT_DELIMITERS);
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private String[] parseWithCustomDelimiter(String input) {
        int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        validator.requireValidCustomHeader(delimiterEndIndex);

        String customDelimiter = extractCustomDelimiter(input, delimiterEndIndex);
        validator.requireNonEmptyDelimiter(customDelimiter);

        String numbers = extractNumbers(input, delimiterEndIndex);
        if (numbers.isEmpty()) {
            return new String[0];
        }
        return numbers.split(Pattern.quote(customDelimiter));
    }

    private String extractCustomDelimiter(String input, int delimiterEndIndex) {
        return input.substring(DELIMITER_START_INDEX, delimiterEndIndex);
    }

    private String extractNumbers(String input, int delimiterEndIndex) {
        return input.substring(delimiterEndIndex + 1);
    }
}