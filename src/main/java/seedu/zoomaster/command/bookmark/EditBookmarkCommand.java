package seedu.zoomaster.command.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Timetable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author fchensan
public class EditBookmarkCommand extends Command {
    public static final String EDIT_KW = "edit";

    public static final String FIELD_KW_DESCRIPTION = "desc";
    public static final String FIELD_KW_URL = "url";

    private int bookmarkIndex;
    private String fieldToEdit;
    private String newValue;

    /**
     * Constructs a new EditBookmarkCommand instance based on user input.
     *
     * @param command The command that the user input.
     * @throws ZoomasterException if the user inputs with invalid format.
     */
    public EditBookmarkCommand(String command) throws ZoomasterException {
        assert command.startsWith(EDIT_KW);

        Pattern parser = Pattern.compile(
                EDIT_KW + " (?<fieldToEdit>[a-zA-Z]+)\\s+(?<bookmarkIndex>\\d+)\\s+(?<newValue>[\\w/.:]+)"
        );
        Matcher matcher = parser.matcher(command);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }

        bookmarkIndex = Integer.parseInt(matcher.group("bookmarkIndex")) - 1;
        fieldToEdit = matcher.group("fieldToEdit");
        newValue = matcher.group("newValue");
    }

    /**
     * Edit a bookmark's description or based on user input.
     *
     * @param bookmarks The list of bookmarks containing the bookmark to be editted.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @throws ZoomasterException if there is an error in user input.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        Bookmark bookmark = null;

        if (bookmarks.getSize() == 0) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_BOOKMARK_LIST);
        }

        try {
            bookmark = bookmarks.getBookmark(bookmarkIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new ZoomasterException(ZoomasterExceptionType.BOOKMARK_NUMBER_OUT_OF_BOUNDS,
                Integer.toString(bookmarks.getSize()));
        }
        String message;

        switch (fieldToEdit) {
        case FIELD_KW_DESCRIPTION:
            bookmark.setDescription(newValue);
            message = "Bookmark description updated!";
            break;
        case FIELD_KW_URL:
            bookmark.setUrl(newValue);
            message = "Bookmark url updated!";
            break;
        default:
            message = "Unrecognized field!";
            break;
        }

        ui.print(message + "\n");
    }
}
