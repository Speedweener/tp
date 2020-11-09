package seedu.zoomaster.bookmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xingrong123
class BookmarkListTest {
    Bookmark bookmarkTest;

    @BeforeEach
    public void initEachSlotTest() {
        bookmarkTest = new Bookmark("tutorial", "www.google.com");
    }

    @Test
    void addBookmark_validInput_returnsExpectedMessage() throws IndexOutOfBoundsException {
        BookmarkList bookmarks = new BookmarkList();
        assertEquals("Added bookmark: [tutorial] www.google.com" + System.lineSeparator(),
                bookmarks.addBookmark(bookmarkTest));
    }

    @Test
    void getBookmark_validIndex_returnsBookmark() throws IndexOutOfBoundsException, ZoomasterException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(bookmarkTest);
        assertEquals(bookmarkTest, bookmarks.getBookmark(0));
    }

    @Test
    void getBookmark_invalidIndex_throwsIndexOutOfBoundsException() throws IndexOutOfBoundsException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(bookmarkTest);
        assertThrows(IndexOutOfBoundsException.class, () -> bookmarks.getBookmark(1));
        assertThrows(IndexOutOfBoundsException.class, () -> bookmarks.getBookmark(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> bookmarks.getBookmark(2));
    }

    /**
     * Adds 4 valid bookmarks to the BookmarkList.
     * Checks that size decreases to 4 after deletion.
     * Checks that bookmark at index 2 changes after deletion.
     */
    //@@author Speedweener
    @Test
    void deleteBookmark() throws IndexOutOfBoundsException, ZoomasterException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("CS2113T", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("CS2113T", "www.yahoo.com"));
        bookmarks.addBookmark(new Bookmark("CS1010", "https://www.youtube.com/"));
        bookmarks.addBookmark(new Bookmark("GEH1049", "https://www.stackoverflow.com/"));
        assertEquals(bookmarks.getSize(), 4);
        assertEquals(bookmarks.getBookmark(2).getBookmarkAsString(),
                "[CS1010] https://www.youtube.com/" + System.lineSeparator());
        bookmarks.deleteBookmark(bookmarks.getBookmark(2));
        assertEquals(bookmarks.getSize(), 3);
        assertEquals(bookmarks.getBookmark(2).getBookmarkAsString(),
                "[GEH1049] https://www.stackoverflow.com/" + System.lineSeparator());
    }

    @Test
    void showBookmark() {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("CS2113T", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("CS2113T", "www.yahoo.com"));
        assertEquals("Here are the bookmarks in your list:" + System.lineSeparator()
                + "  " + (1) + "." + "[CS2113T] www.google.com" + System.lineSeparator()
                + "  " + (2) + "." + "[CS2113T] www.yahoo.com" + System.lineSeparator() + System.lineSeparator(),
                bookmarks.showBookmarks());
    }

    @Test
    void findBookmark_matchingBookmark() throws IndexOutOfBoundsException, ZoomasterException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("CS2112T", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("CS2113T", "www.yahoo.com"));
        assertEquals("Here are your matching bookmarks" + System.lineSeparator() + (2) + "."
                        + bookmarks.getBookmark(1).getBookmarkAsString() + System.lineSeparator(),
                bookmarks.findBookmarks("CS2113T"));
    }

    @Test
    void findBookmark_emptyList_returnsEmptyListMessage() throws IndexOutOfBoundsException {
        BookmarkList bookmarks = new BookmarkList();
        assertEquals("Bookmark list is empty" + System.lineSeparator(),
                bookmarks.findBookmarks("CS2113T"));
    }

    @Test
    void findBookmark_noMatchingKeyword_returnsNoMatchingBookmarkMessage() throws IndexOutOfBoundsException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("CS2112T", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("CS2113T", "www.yahoo.com"));
        assertEquals("No bookmarks contain the specified keyword!" + System.lineSeparator(),
                bookmarks.findBookmarks("CS3113T"));
    }

    @Test
    void launchBookmark_matchingBookmark() throws IndexOutOfBoundsException, ZoomasterException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("tutorial", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("lecture", "www.yahoo.com"));
        assertEquals("Launched these bookmarks:" + System.lineSeparator()
                + (2) + "." + bookmarks.getBookmark(1).getBookmarkAsString() + System.lineSeparator(),
                bookmarks.launchBookmarks("lecture"));
    }

    @Test
    void launchBookmark_emptyBookmarkList_returnEmptyListMessage() throws IndexOutOfBoundsException {
        BookmarkList bookmarks = new BookmarkList();
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> bookmarks.launchBookmarks("lecture"));
        assertEquals(ZoomasterExceptionType.EMPTY_BOOKMARK_LIST, e.getError());
    }


    @Test
    void launchBookmark_noMatchingBookmarks() throws ZoomasterException {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("tutorial", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("lecture", "www.yahoo.com"));
        String description = "NONMATCHINGTERM";
        assertEquals("No bookmarks contain the specified keyword!" + System.lineSeparator(),
                bookmarks.launchBookmarks(description));
    }

    @Test
    void launchBookmark_noEmptyList_throwsZoomasterException() throws ZoomasterException {
        BookmarkList bookmarks = new BookmarkList();
        String description = "NONMATCHINGTERM";
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> bookmarks.launchBookmarks(description));
        assertEquals(ZoomasterExceptionType.EMPTY_BOOKMARK_LIST, e.getError());
    }

    @Test
    void launchAllBookmark_returnLaunchBookmarksMessage() {
        BookmarkList bookmarks = new BookmarkList();
        bookmarks.addBookmark(new Bookmark("tutorial", "www.google.com"));
        bookmarks.addBookmark(new Bookmark("lecture", "www.yahoo.com"));
        assertEquals("Launched these bookmarks:" + System.lineSeparator()
                        + "1.[tutorial] www.google.com" + System.lineSeparator()
                        + "2.[lecture] www.yahoo.com" + System.lineSeparator(),
                bookmarks.launchAllBookmarks());
    }

    @Test
    void launchAllBookmark_emptyBookmarkList_returnEmptyListMessage() {
        BookmarkList bookmarks = new BookmarkList();
        assertEquals("Bookmark list is empty" + System.lineSeparator(),
                bookmarks.launchAllBookmarks());
    }
}