package seedu.zoomaster.bookmark;

import seedu.zoomaster.Ui;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This class represents the bookmark list used to store and organize bookmarks.
 */
//@@author Speedweener
public class BookmarkList {
    public static final String FAILED_TO_LAUNCH_THESE_BOOKMARKS = "Failed to launch these bookmarks:";
    public static final String BOOKMARK_LIST_IS_EMPTY = "Bookmark list is empty";
    public static final String LAUNCHED_THESE_BOOKMARKS = "Launched these bookmarks:";
    public static final String NO_BOOKMARKS_CONTAIN_KEYWORD = "No bookmarks contain the specified keyword!";
    public static final String SHOW_BOOKMARKS_MESSAGE = "Here are the bookmarks in your list:";
    public static final String MATCHING_BOOKMARKS_MESSAGE = "Here are your matching bookmarks";
    private ArrayList<Bookmark> bookmarks;
    private static Logger logger = Logger.getLogger(BookmarkList.class.getName());


    /**
     * Constructs a BookmarkList object with an empty ArrayList to store Bookmark objects.
     */
    public BookmarkList() {
        this.bookmarks = new ArrayList<>();
    }

    /**
     * Adds the given bookmark object to the bookmark list.
     *
     * @param bookmark the bookmark to be added to the list.
     */
    public String addBookmark(Bookmark bookmark) {
        assert bookmark != null : "Added bookmark should not be null!";
        bookmarks.add(bookmark);
        return "Added bookmark: [" + bookmark.getDescription() + "] "
                + bookmark.getUrl() + Ui.NEW_LINE;
    }

    /**
     * Returns the number of bookmarks in the list.
     *
     * @return the size of the bookmark list.
     */
    public int getSize() {
        return bookmarks.size();
    }

    /**
     * Returns bookmark based on the index.
     *
     * @param index The index of the bookmark in the list.
     * @return The bookmark with the corresponding index in the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     * @throws ZoomasterException if the bookmarkList is empty.
     */
    public Bookmark getBookmark(int index) throws IndexOutOfBoundsException, ZoomasterException {
        if (bookmarks.isEmpty()) {
            throw new  ZoomasterException(ZoomasterExceptionType.EMPTY_BOOKMARK_LIST);
        }
        return bookmarks.get(index);
    }

    /**
     * Returns the bookmark list.
     *
     * @return The bookmark list.
     */
    public ArrayList<Bookmark> getBookmarks() {
        return bookmarks;
    }

    /**
     * Deletes the bookmark from the list. Use the method getBookmark to check if the bookmark exists.
     *
     * @param bookmark The bookmark to be deleted.
     * @throws NullPointerException if the bookmark does not exist in the list.
     */
    //@@author Speedweener
    public String deleteBookmark(Bookmark bookmark) {
        assert bookmarks.contains(bookmark) : "Bookmark to be deleted not in list!";
        bookmarks.remove(bookmark);
        return "[" + bookmark.getDescription() + "] " + bookmark.getUrl() + "\n";
    }

    /**
     * Returns a string containing all the bookmarks from the list in a
     * fixed format to be printed on the user interface.
     *
     * @return The string message containing the matching bookmarks.
     */
    public String showBookmarks() {
        String message = SHOW_BOOKMARKS_MESSAGE + Ui.NEW_LINE;
        if (bookmarks.isEmpty()) {
            message = message + BOOKMARK_LIST_IS_EMPTY + Ui.NEW_LINE;
        } else {
            for (int i = 0; i < bookmarks.size(); i++) {
                message = message + "  " + (i + 1) + "." + bookmarks.get(i).getBookmarkAsString();
            }
        }
        return message + Ui.NEW_LINE;
    }

    /**
     * Returns a string containing the details of the bookmarks from the list with matching module and description.
     * The string is in a fixed format to be printed on the user interface.
     *
     * @param description The description to be searched.
     * @return The string message containing the matching bookmarks.
     */
    public String findBookmarks(String description) {
        assert !description.equals("") : "Description should not be empty!";
        String message = "";
        if (bookmarks.size() == 0) {
            message = BOOKMARK_LIST_IS_EMPTY + Ui.NEW_LINE;
        } else {
            message = getMatchingBookmarks(description.toUpperCase());
            if (!message.isEmpty()) {
                message = MATCHING_BOOKMARKS_MESSAGE + Ui.NEW_LINE + message;
            } else {
                message = NO_BOOKMARKS_CONTAIN_KEYWORD + Ui.NEW_LINE;
            }
        }
        return message;
    }

    private String getMatchingBookmarks(String description) {
        String message = "";
        for (int i = 0; i < bookmarks.size(); i++) {
            if (bookmarks.get(i).getDescription().toUpperCase().contains(description)) {
                message += (i + 1) + "." + bookmarks.get(i).getBookmarkAsString() + Ui.NEW_LINE;
            }
        }
        return message;
    }

    /**
     * Returns a string containing the information of the bookmarks from the list with matching description
     * and launches them accordingly.
     *
     * @param description The description to be searched.
     * @return The string message containing the matching bookmarks.
     */
    public String launchBookmarks(String description) throws ZoomasterException {
        assert !description.equals("") : "Description should not be empty!";
        String message = "";
        if (bookmarks.isEmpty()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_BOOKMARK_LIST);
        } else {
            message = launchMatchingBookmarks(description.toUpperCase());
            if (!message.isBlank()) {
                message = LAUNCHED_THESE_BOOKMARKS + Ui.NEW_LINE + message;
            } else {
                message = NO_BOOKMARKS_CONTAIN_KEYWORD + Ui.NEW_LINE;
            }
        }
        return message;
    }

    private String launchMatchingBookmarks(String description) {
        String message = "";
        String errorMessage = "";
        for (int i = 0; i < bookmarks.size(); i++) {
            if (bookmarks.get(i).getDescription().toUpperCase().contains(description)) {
                try {
                    bookmarks.get(i).launch();
                    message += (i + 1) + "." + bookmarks.get(i).getBookmarkAsString();
                } catch (ZoomasterException e) {
                    errorMessage += (i + 1) + "." + bookmarks.get(i).getBookmarkAsString();
                }
            }
        }
        if (!errorMessage.isBlank()) {
            message += FAILED_TO_LAUNCH_THESE_BOOKMARKS + Ui.NEW_LINE + errorMessage;
        }
        return message + Ui.NEW_LINE;
    }

    //@@author xingrong123
    /**
     * Returns a string containing the information of all the bookmarks in the list and launches the bookmarks.
     * The string also contains information of the bookmarks which failed to launch.
     *
     * @return the string of all the bookmarks in the list.
     */
    public String launchAllBookmarks() {
        String message = "";
        if (bookmarks.size() == 0) {
            message = BOOKMARK_LIST_IS_EMPTY + Ui.NEW_LINE;
        } else {
            message = launchBookmarksFromList();
            if (!message.startsWith(FAILED_TO_LAUNCH_THESE_BOOKMARKS)) {
                message = LAUNCHED_THESE_BOOKMARKS + Ui.NEW_LINE + message;
            }
        }
        return message;
    }

    private String launchBookmarksFromList() {
        String message = "";
        String errorMessage = "";
        for (int i = 0; i < bookmarks.size(); i++) {
            try {
                bookmarks.get(i).launch();
                message += (i + 1) + "." + bookmarks.get(i).getBookmarkAsString();
            } catch (ZoomasterException e) {
                errorMessage += (i + 1) + "." + bookmarks.get(i).getBookmarkAsString();
            }
        }
        if (!errorMessage.isBlank()) {
            message += FAILED_TO_LAUNCH_THESE_BOOKMARKS + Ui.NEW_LINE + errorMessage;
        }
        return message;
    }
}
