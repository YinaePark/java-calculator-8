package calculator.domain;

import calculator.validator.InputValidator;

public class Calculator {
    private final StringParser parser;
    private final InputValidator validator;

    public Calculator() {
        this.parser = new StringParser();
        this.validator = new InputValidator();
    }

    public long calculate(String input) {
        String[] tokens = parser.parse(input);

        if (tokens.length == 0) {
            return 0;
        }

        return sumNumbers(tokens);
    }

    private long sumNumbers(String[] tokens) {
        long sum = 0L;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                long number = parseNumber(token);
                validator.requireNoOverflow(sum, number);
                sum += number;
            }
        }
        return sum;
    }

    private long parseNumber(String token) {
        validator.requireNotEmptyToken(token);
        long number = validator.parseLongOrThrow(token);
        validator.requireNonNegative(number);
        return number;
    }
}