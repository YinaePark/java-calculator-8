package calculator;

import calculator.domain.Calculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Calculator calculator = new Calculator();

        String input = inputView.readInput();
        long result = calculator.calculate(input);
        outputView.printResult(result);
    }
}