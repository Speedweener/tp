package seedu.zoomaster.settings;

import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

/**
 * Stores the user's settings as a series of SettingsVariable instances.
 */
//@@author fchensan
public class UserSettings {
    public static final String MODE_MAINMENU = "mainmenu";
    public static final String MODE_TIMETABLE = "timetable";
    public static final String MODE_BOOKMARK = "bookmark";

    public static final String DEFAULT_MODE_FIELD = "def_mode";
    private static final String DEFAULT_MODE_DESC = "Default mode at start up";
    private static final String[] DEFAULT_MODE_OPTIONS = {MODE_MAINMENU, MODE_TIMETABLE, MODE_BOOKMARK};

    public static final String AUTO_SAVE_FIELD = "autosave";
    private static final String AUTO_SAVE_DESC = "Autosave after each command (if off, only save on exit)";
    private static final String[] AUTO_SAVE_OPTIONS = {SettingsVariable.ON, SettingsVariable.OFF};

    private SettingsVariable[] variables = {
        new SettingsVariable<String>(DEFAULT_MODE_FIELD, DEFAULT_MODE_DESC, DEFAULT_MODE_OPTIONS),
        new SettingsVariable<String>(AUTO_SAVE_FIELD, AUTO_SAVE_DESC, AUTO_SAVE_OPTIONS),
    };

    public SettingsVariable[] getVariables() {
        return variables;
    }

    /**
     * Go through the SettingsVariable list and retrieve the one with the given field name.
     *
     * @param fieldName The field name of the SettingsVariable instance to be retrieved.
     * @return A SettingsVariable instance from the list.
     * @throws ZoomasterException if no SettingsVariable instance is found with the given field name.
     */
    public SettingsVariable getSettingsVariable(String fieldName) throws ZoomasterException {
        for (SettingsVariable variable: variables) {
            if (variable.getFieldName().equals(fieldName)) {
                return variable;
            }
        }

        throw new ZoomasterException(ZoomasterExceptionType.INVALID_SETTING_FIELD, fieldName);
    }
}
