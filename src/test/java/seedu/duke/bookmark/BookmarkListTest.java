package seedu.duke.bookmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BookmarkListTest {

    private BookmarkList bookmarks;
    private final Bookmark bookmark = new Bookmark("CS2113T", "tutorial", "www.google.com");
    private final String lineSeparator = System.lineSeparator();


    @BeforeEach
    void init() {
        bookmarks = new BookmarkList();
        bookmarks.addBookmark(bookmark);
    }


    @Test
    void addBookmark() throws DukeException {
        BookmarkList bookmarks = new BookmarkList();
        String module = "CS2113T";
        String description = "something";
        String url = "www.google.com";
        Bookmark bookmark = new Bookmark(module, description, url);
        bookmarks.addBookmark(bookmark);
        assertEquals(bookmark, bookmarks.getBookmark(0));
    }

    @Test
    void initializeBookmarkListWithArrayList_validBookmark_addsToBookmarkList() throws DukeException {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");
        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.getBookmark(0).getExport(), "CS2113T | tutorial | www.google.com");
        assertEquals(bookmarks.getBookmark(1).getExport(), "CS2113T | lecture | www.yahoo.com");
    }


    /**
     * Adds 1 valid and 1 invalid bookmark to the BookmarkList.
     * Asserts IndexOutOfBoundsException for index 1 as only 1 bookmark will be added to index 0.
     */
    @Test
    void initializeBookmarkListWithArrayList_invalidBookmark_throwsDukeException() {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T");
        BookmarkList bookmarks = new BookmarkList(input);
        DukeException e = assertThrows(DukeException.class, () -> bookmarks.getBookmark(1));
        assertEquals(DukeExceptionType.INVALID_BOOKMARK_NUMBER, e.getError());
    }

    @Test
    void getData() {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");
        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.getData(), "CS2113T | tutorial | www.google.com" + System.lineSeparator()
                + "CS2113T | lecture | www.yahoo.com");
    }

    @Test
    void getSize() {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");
        input.add("CS1010 | lecture | https://www.youtube.com/");

        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.getSize(), 3);
    }

    @Test
    void getBookmark() throws DukeException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(bookmark);
        assertEquals(bookmarks.getBookmark(0), bookmark);
    }

    @Test
    void getBookmarkList() {

    }

    /**
     * Adds 4 valid bookmarks to the BookmarkList.
     * Checks that size decreases to 4 after deletion.
     * Checks that bookmark at index 2 changes after deletion.
     */
    @Test
    void deleteBookmark() throws DukeException {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");
        input.add("CS1010 | lecture | https://www.youtube.com/");
        input.add("GEH1049 | lecture | https://www.stackoverflow.com/");


        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.getSize(), 4);
        assertEquals(bookmarks.getBookmark(2).getBookmarkAsString(),
                "[CS1010] lecture https://www.youtube.com/" + System.lineSeparator());

        bookmarks.deleteBookmark(bookmarks.getBookmark(2));
        assertEquals(bookmarks.getSize(), 3);
        assertEquals(bookmarks.getBookmark(2).getBookmarkAsString(),
                "[GEH1049] lecture https://www.stackoverflow.com/" + System.lineSeparator());

    }

    @Test
    void showBookmark() {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");

        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.showBookmarks(), "\tHere are the bookmarks in your list:" + lineSeparator
                + "\t" + (1) + "." + "[CS2113T] tutorial www.google.com" + System.lineSeparator() + lineSeparator
                + "\t" + (2) + "." + "[CS2113T] lecture www.yahoo.com" + System.lineSeparator() + lineSeparator);
    }

    @Test
    void findBookmark_matchingBookmark() throws DukeException {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");

        ArrayList<String> findTerm = new ArrayList<>();
        findTerm.add("CS2113T");
        findTerm.add("lecture");

        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.findBookmarks(findTerm), "Here are your matching bookmarks" + lineSeparator
                + (2) + "." + bookmarks.getBookmark(1).getBookmarkAsString() + lineSeparator);
    }

    @Test
    void findBookmark_noMatchingBookmarks() {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");

        ArrayList<String> findTerm = new ArrayList<>();
        findTerm.add("NONMATCHINGTERM");
        findTerm.add("NONMATCHINGTERM");

        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.findBookmarks(findTerm), "No bookmarks contain the specified keyword!" + lineSeparator);
    }

    @Test
    void launchBookmark_matchingBookmark() throws DukeException {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");

        ArrayList<String> findTerm = new ArrayList<>();
        findTerm.add("CS2113T");
        findTerm.add("lecture");

        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.findBookmarks(findTerm), "Here are your matching bookmarks" + lineSeparator
                + (2) + "." + bookmarks.getBookmark(1).getBookmarkAsString() + lineSeparator);
    }


    @Test
    void launchBookmark_noMatchingBookmarks() {
        ArrayList<String> input = new ArrayList<>();
        input.add("CS2113T | tutorial | www.google.com");
        input.add("CS2113T | lecture | www.yahoo.com");

        ArrayList<String> findTerm = new ArrayList<>();
        findTerm.add("NONMATCHINGTERM");
        findTerm.add("NONMATCHINGTERM");

        BookmarkList bookmarks = new BookmarkList(input);
        assertEquals(bookmarks.launchBookmarks(findTerm),
                "No bookmarks contain the specified keyword!" + lineSeparator);
    }
}