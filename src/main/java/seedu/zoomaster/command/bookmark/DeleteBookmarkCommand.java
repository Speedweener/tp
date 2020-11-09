package seedu.zoomaster.command.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

//@@author Speedweener
/**
 * Represents the command to delete a bookmark from the bookmark list.
 */
public class DeleteBookmarkCommand extends Command {
    public static final String DEL_KW = "delete";
    private final int index;

    /**
     * Constructs a new DeleteBookmarkCommand instance and stores the information of the bookmark given by the input.
     *
     * @param command The user input command.
     * @throws ZoomasterException thrown if input command is invalid or if the bookmark number is invalid.
     */
    public DeleteBookmarkCommand(String command) throws ZoomasterException {
        assert command.startsWith(DEL_KW) : "input should always start with \"delete\"";
        String details = command.substring(DEL_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, DEL_KW);
        }
        if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new ZoomasterException(ZoomasterExceptionType.NON_INTEGER_INPUT);
        }
    }

    /**
     * Deletes the bookmark in the bookmark list.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @throws ZoomasterException if the bookmark number is invalid or if there is an error when saving the bookmark.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        try {
            Bookmark bookmark = bookmarks.getBookmark(index);
            String message = "I've deleted this bookmark!:\n" + bookmarks.deleteBookmark(bookmark);
            ui.print(message);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new ZoomasterException(ZoomasterExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS, ""
                    + bookmarks.getBookmarks().size());
        }
    }
}
