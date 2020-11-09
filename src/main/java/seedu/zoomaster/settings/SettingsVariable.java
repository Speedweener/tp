package seedu.zoomaster.settings;

import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

/**
 * Represents a setting that the user can set.
 *
 * @param <T> The object type of the options available to the user.
 */
//@@author fchensan
public class SettingsVariable<T> {
    public static final String ON = "on";
    public static final String OFF = "off";

    private String fieldName;

    private String description;
    private T[] options;
    private int chosenOptionIndex;

    public SettingsVariable(String fieldName, String description, T[] options) {
        this.fieldName = fieldName;
        this.options = options;
        this.description = description;
        chosenOptionIndex = 0;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getChosenOptionIndex() {
        return chosenOptionIndex;
    }

    public void setChosenOptionIndex(int chosenOptionIndex) {
        this.chosenOptionIndex = chosenOptionIndex;
    }

    public String getDescription() {
        return description;
    }

    public String getOptionsList() {
        StringBuilder optionsListString = new StringBuilder();
        for (int i = 0; i < options.length; i++) {
            if (i == chosenOptionIndex) {
                optionsListString.append("<<");
            }

            optionsListString.append(options[i].toString().toLowerCase());

            if (i == chosenOptionIndex) {
                optionsListString.append(">>");
            }

            optionsListString.append(" ");
        }
        return optionsListString.toString();
    }

    public void setChosenOption(String optionAsString) throws ZoomasterException {
        for (int i = 0; i < options.length; i++) {
            if (options[i].toString().toLowerCase().equals(optionAsString)) {
                setChosenOptionIndex(i);
                return;
            }
        }

        throw new ZoomasterException(ZoomasterExceptionType.INVALID_SETTING_OPTION, optionAsString);
    }

    public T getChosenOption() {
        return options[getChosenOptionIndex()];
    }
}
