package seedu.zoomaster.command.settings;

import seedu.zoomaster.Ui;
import seedu.zoomaster.Zoomaster;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.settings.SettingsVariable;
import seedu.zoomaster.slot.Timetable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author fchensan
public class SetSettingsCommand extends Command {
    public static final String SET_KW = "set";

    private String fieldName;
    private String newValue;

    /**
     * Constructs a new SetSettingsCommand instance based on user input.
     *
     * @param command The user command input.
     * @throws ZoomasterException if the user inputs in an invalid format.
     */
    public SetSettingsCommand(String command) throws ZoomasterException {
        Pattern pattern = Pattern.compile(SET_KW + "\\s+(?<fieldName>\\w+)\\s+(?<newValue>\\w+)");
        Matcher matcher = pattern.matcher(command);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_SETTING_INPUT);
        }

        fieldName = matcher.group("fieldName");
        newValue = matcher.group("newValue");
    }

    /**
     * Changes one of the user settings.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @throws ZoomasterException if the user inputs an invalid setting option.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        SettingsVariable settingsVariable = Zoomaster.userSettings.getSettingsVariable(fieldName);
        settingsVariable.setChosenOption(newValue);

        new ShowSettingsCommand().execute(bookmarks, timetable, ui);
        ui.print("Settings updated!\n");
    }
}
