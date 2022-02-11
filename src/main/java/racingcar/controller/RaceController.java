package racingcar.controller;

import racingcar.model.car.Cars;
import racingcar.model.input.Names;
import racingcar.model.trycount.TryCount;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RaceController {
    private TryCount tryCount;
    private Cars cars = Cars.create();

    public RaceController() {
    }

    public void setUpCars() {
        try {
            String input = InputView.inputNamesUi();
            Names names = Names.from(input);
            cars = Cars.from(names);
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorUi(exception);
            setUpCars();
        }
    }

    public void setUpTryCount() {
        try {
            String input = InputView.inputTryCountUi();
            tryCount = TryCount.from(input);
            System.out.println(tryCount.toInt());
        } catch (IllegalArgumentException exception) {
            OutputView.printErrorUi(exception);
            setUpTryCount();
        }
    }
}
