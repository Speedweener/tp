package seedu.zoomaster.command.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

//@@author Speedweener
public class FindBookmarkCommand extends Command {
    public static final String FIND_KW = "find";
    private final String description;

    /**
     * Constructs a new FindBookmarkCommand instance and gets the module and description to be searched.
     * If only one term is entered (term meaning string without a space), method asserts
     * this to be the module and inserts an empty description.
     *
     * @param command The command input by the user.
     * @throws ZoomasterException if the input is unknown.
     */
    public FindBookmarkCommand(String command) throws ZoomasterException {
        String details = command.substring(FIND_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, FIND_KW);
        }
        if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        description = details.trim();
    }

    /**
     * Prints the bookmark with the matching keyword.
     *  @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) {
        String message = bookmarks.findBookmarks(description);
        ui.print(message);
    }
}
