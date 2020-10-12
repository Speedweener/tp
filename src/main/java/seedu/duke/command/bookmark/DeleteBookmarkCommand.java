package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.SlotList;

public class DeleteBookmarkCommand extends Command {
    public static final String DEL_KW = "delete";
    private final int index;

    /**
     * Constructs a new DeleteBookmarkCommand instance and stores the information of the bookmark given by the input.
     *
     * @param command The user input command.
     * @throws DukeException thrown if input command is invalid or if the bookmark number is invalid.
     */
    public DeleteBookmarkCommand(String command) throws DukeException {
        String details = command.substring(DEL_KW.length());
        if (!details.startsWith(" ")) {
            throw new DukeException(DukeExceptionType.UNKNOWN_INPUT);
        }
        try {
            index = Integer.parseInt(details.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(DukeExceptionType.INVALID_BOOKMARK_NUMBER);
        }
    }

    /**
     * Deletes the bookmark in the bookmark list.
     *
     * @param bookmarks The list of bookmarks.
     * @param slotList The list of slots.
     * @param ui The user interface.
     * @param bookmarkStorage The storage for saving and loading bookmarks.
     * @param slotStorage The storage for saving and loading slots.
     * @throws DukeException if the bookmark number is invalid or if there is an error when saving the bookmark.
     */
    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        try {
            Bookmark bookmark = bookmarks.getBookmark(index);
            bookmarks.deleteBookmark(bookmark);
            ui.print(getMessage(bookmark));
            bookmarkStorage.save(bookmarks.getData());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(DukeExceptionType.INVALID_BOOKMARK_NUMBER, ""
                    + bookmarks.getBookmarkList().size());
        }
    }

    private String getMessage(Bookmark bookmark) {
        String message = "\tI've deleted this bookmark!:\n"
                + "\t  [" + bookmark.getModule() + "] " + bookmark.getDescription() + " " + bookmark.getUrl() + "\n";
        return message;
    }
}
