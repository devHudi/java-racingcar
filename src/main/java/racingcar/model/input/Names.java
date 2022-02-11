package racingcar.model.input;

import racingcar.model.car.Name;
import racingcar.util.StringValidator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Names {
    private final List<Name> names;

    private Names(List<Name> names) {
        this.names = names;
    }

    public static Names from(String text) {
        String[] splitText = text.split(",");
        StringValidator.validateDuplicated(splitText);

        return new Names(Arrays.stream(splitText)
                .map(Name::from)
                .collect(Collectors.toList()));
    }

    public List<Name> get() {
        return names;
    }
}
