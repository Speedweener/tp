package seedu.duke.command.bookmark;

import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.Bookmark;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.slot.SlotList;

import java.util.List;

/**
 * Represents the user command exit the Duke program.
 */
public class AddBookmarkCommand extends Command {
    public static final String ADD_KW = "add";
    public String module;
    public String description;
    public String url;

    /**
     * Constructs a new AddBookmarkCommand instance and stores the information of the bookmark given by the input.
     *
     * @param command The user input command.
     * @throws DukeException if input command is invalid, if the description is empty or if the url is invalid.
     */
    public AddBookmarkCommand(String command) throws DukeException {
        List<String> descAndAt = Bookmark.extractModuleDescriptionAndUrl(command);
        module = descAndAt.get(0);
        description = descAndAt.get(1);
        url = descAndAt.get(2);
    }

    /**
     * Adds the bookmark to the bookmark list and saves the bookmarks list in the text file.
     *
     * @param bookmarks The list of bookmarks.
     * @param slotList The list of slots.
     * @param ui The user interface.
     * @param bookmarkStorage The storage for saving and loading bookmarks.
     * @param slotStorage The storage for saving and loading slots.
     * @throws DukeException if there is an error when saving the bookmark.
     */
    @Override
    public void execute(BookmarkList bookmarks, SlotList slotList, Ui ui,
                        Storage bookmarkStorage, Storage slotStorage) throws DukeException {
        Bookmark bookmark = new Bookmark(module, description, url);
        bookmarks.addBookmark(bookmark);
        ui.print("Added bookmark: " + "[" + module + "] "
                + description + " " +  url + System.lineSeparator());
        bookmarkStorage.save(bookmarks.getData());
    }
}
