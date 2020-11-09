package seedu.zoomaster.command;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.slot.Timetable;

//@@author Speedweener
public class ClearCommand extends Command {
    public static final String CLEAR_KW = "clear";

    /**
     * Constructs a new ShowBookmarkCommand instance.
     */
    public ClearCommand() {
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) {
        ui.clearScreen();
    }
}
