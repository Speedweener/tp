package seedu.zoomaster.command.settings;

import seedu.zoomaster.Ui;
import seedu.zoomaster.Zoomaster;
import seedu.zoomaster.settings.SettingsVariable;
import seedu.zoomaster.settings.UserSettings;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Timetable;

//@@author fchensan
public class ShowSettingsCommand extends Command {
    public static final String SHOW_KW = "showsettings";

    /**
     * Prints out the list of user settings.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @throws ZoomasterException if there is an error in user input.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        ui.print(generateSettingsListMessage());
    }

    private String generateSettingsListMessage() {
        StringBuilder message = new StringBuilder();
        SettingsVariable[] settingsVariables = Zoomaster.userSettings.getVariables();
        for (SettingsVariable settingsVariable: settingsVariables) {
            String lineHeader = String.format(
                    "[%s] %s: ", settingsVariable.getFieldName(), settingsVariable.getDescription());
            message.append(lineHeader);

            message.append(settingsVariable.getOptionsList());
            message.append("\n");
        }

        return message.toString();
    }
}
