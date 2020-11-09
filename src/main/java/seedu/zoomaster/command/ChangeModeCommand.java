package seedu.zoomaster.command;

import seedu.zoomaster.Ui;
import seedu.zoomaster.Parser;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

/**
 * Executes the change mode functionality of the program.
 */
//@@author TYS0n1
public class ChangeModeCommand extends Command {
    public static final String MODE_KW = "mode";
    public int setToMode;

    /**
     * Constructs a new ChangeModeCommand instance.
     * It decodes the command received to find out the mode the user wants
     * the program to be in.
     * If an invalid mode or error in the command occurs, it is set to 0;
     *
     * @param command The command sent by the user.
     */
    public ChangeModeCommand(String command) {
        setToMode = getModeFromCommand(command);
    }

    /**
     * Executes ChangeModeCommand.
     *
     * @param bookmarks BookmarkList of the program.
     * @param timetable Timetable containing the lesson slots of the program.
     * @param ui The User Interface used to send messages to the user.
     * @throws ZoomasterException if an invalid mode is submitted.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui)
            throws ZoomasterException {
        if (setToMode == 1) {
            Parser.programMode = 1;
            ui.print("Changing to bookmark mode" + Ui.NEW_LINE);
        } else if (setToMode == 2) {
            Parser.programMode = 2;
            ui.print("Changing to timetable mode" + Ui.NEW_LINE);
        } else if (setToMode == 3) {
            Parser.programMode = 3;
            ui.print("Changing to planner mode" + Ui.NEW_LINE);
        } else {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_MODE);
        }
    }

    /**
     * Decodes the command sent from the user.
     * Assert line catches when a non ChangeModeCommand compatible command has called this function.
     * Any input commands with less than equals length of 5 is an invalid input mode by default.
     * For example "mode " is an invalid input as it has no mode input.
     * Another example "mode1" is an invalid input too as mode command requires a space before it's
     * mode input.
     * Any mode input not "bookmark", "timetable" or "planner" are invalid inputs. Inputs are not
     * case sensitive.
     *
     * @param input The input command from the user.
     * @return outputData
     */
    public int getModeFromCommand(String input) {
        int outputData;

        assert input.startsWith(ChangeModeCommand.MODE_KW) : "input should always start with \"mode\"";
        if (input.length() <= 5) {
            return 0;
        }

        String modeData = input.substring(5);

        if (modeData.toLowerCase().compareTo("bookmark") == 0) {
            outputData = 1;
        } else if (modeData.toLowerCase().compareTo("timetable") == 0) {
            outputData = 2;
        } else if (modeData.toLowerCase().compareTo("planner") == 0) {
            outputData = 3;
        } else {
            outputData = 0;
        }

        return outputData;
    }
}
