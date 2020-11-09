package seedu.zoomaster.command.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

import java.util.List;

/**
 * Represents the command to add a bookmark.
 */
//@@author xingrong123
public class AddBookmarkCommand extends Command {
    public static final String ADD_KW = "add";
    private final String description;
    private final String url;

    /**
     * Constructs a new AddBookmarkCommand instance and stores the information of the bookmark given by the input.
     *
     * @param command The user input.
     * @throws ZoomasterException if input command format is invalid, if the description is empty or
     *     if the url is invalid.
     */
    public AddBookmarkCommand(String command) throws ZoomasterException {
        assert command.startsWith(ADD_KW) : "input should always start with \"add\"";
        command = command.substring(AddBookmarkCommand.ADD_KW.length());
        if (command.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, ADD_KW);
        }
        if (!command.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        List<String> descAndUrl = Bookmark.extractDescriptionAndUrl(command.trim());
        description = descAndUrl.get(0);
        url = descAndUrl.get(1);
    }

    /**
     * Adds the bookmark to the bookmark list.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        Bookmark bookmark = new Bookmark(description, url);
        String message = bookmarks.addBookmark(bookmark);
        ui.print(message);
    }
}
