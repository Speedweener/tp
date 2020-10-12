package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.SlotList;

public class ShowBookmarkCommand extends Command {
    public static final String LIST_KW = "show";

    /**
     * Constructs a new ShowBookmarkCommand instance.
     */
    public ShowBookmarkCommand() {

    }

    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) {
        ui.print(bookmarks.showBookmarks());
    }
}
