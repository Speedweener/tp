package seedu.zoomaster.command.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

//@@author Speedweener
public class LaunchBookmarkCommand extends Command {
    public static final String LAUNCH_KW = "launch";
    private int index;
    private String description;
    private int launchTypeFlag;

    /**
     * Constructs a new LaunchBookmarkCommand instance and gets the index of the bookmark to launch.
     * Determines whether the launch command was input with an integer or string, and sets launchTypeFlag accordingly.
     * Integer (launchTypeFlag = 1)
     * Strings (launchTypeFlag = 2)
     *
     * @param command The command input by the user.
     * @throws ZoomasterException if the input is unknown or if the bookmark number is invalid.
     */
    //@@author Speedweener
    public LaunchBookmarkCommand(String command) throws ZoomasterException {
        String details = command.substring(LAUNCH_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, LAUNCH_KW);
        }
        if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
            launchTypeFlag = 1; // (flag to launch bookmark at specified index)
        } catch (NumberFormatException e) {
            if (details.trim().contains(" ")) {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_COMMAND_FORMAT);
            }
            description = details.trim();
            launchTypeFlag = 2; // (flag to launch bookmarks with matching description)
        }
    }

    /**
     * Launches the bookmark based on the launchTypeFlag previously determined in LaunchBookmarkCommand initialization.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @throws ZoomasterException if the bookmark number is invalid or if there is an error launching the URL.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        if (launchTypeFlag == 1) { // Launch based on index
            try {
                Bookmark bookmark = bookmarks.getBookmark(index);
                String message = "Nice! I've launched this bookmark!:\n" + bookmark.launch() + Ui.NEW_LINE;
                ui.print(message);
            } catch (IndexOutOfBoundsException e) {
                throw new ZoomasterException(ZoomasterExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS, ""
                        + bookmarks.getBookmarks().size());
            }
            return;
        } else if (launchTypeFlag == 2) { // Launch based on matching module and description
            String message = bookmarks.launchBookmarks(description);
            ui.print(message);
        }
    }
}
