package seedu.zoomaster.bookmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xingrong123
class BookmarkTest {
    Bookmark bookmarkTest;

    @BeforeEach
    public void initEachSlotTest() {
        bookmarkTest = new Bookmark("desc", "www.google.com");
    }

    @Test
    void extractModuleDescriptionAndUrl_validInput_returnsDescAndUrl() throws ZoomasterException {
        // Valid url variation 1
        List<String> expectedUrlAndDescription = new ArrayList<>();
        expectedUrlAndDescription.add("tutorial");
        expectedUrlAndDescription.add("www.google.com");
        String input = "tutorial www.google.com";
        assertEquals(expectedUrlAndDescription, Bookmark.extractDescriptionAndUrl(input));

        // Valid url variation 2
        List<String> expectedUrlAndDescription2 = new ArrayList<>();
        expectedUrlAndDescription2.add("tutorial");
        expectedUrlAndDescription2.add("http://www.google.com");
        String input2 = "tutorial http://www.google.com";
        assertEquals(expectedUrlAndDescription2, Bookmark.extractDescriptionAndUrl(input2));

        // Valid url variation 2
        List<String> expectedUrlAndDescription3 = new ArrayList<>();
        expectedUrlAndDescription3.add("tutorial");
        expectedUrlAndDescription3.add("https://www.google.com");
        String input3 = "tutorial https://www.google.com";
        assertEquals(expectedUrlAndDescription3, Bookmark.extractDescriptionAndUrl(input3));

        // Multiple spaces
        List<String> expectedUrlAndDescription4 = new ArrayList<>();
        expectedUrlAndDescription4.add("tutorial");
        expectedUrlAndDescription4.add("www.google.com");
        String input4 = "tutorial         www.google.com";
        assertEquals(expectedUrlAndDescription4, Bookmark.extractDescriptionAndUrl(input4));
    }

    @Test
    void extractModuleDescriptionAndUrl_missingParameter_throwsZoomasterException() {
        String missingDescription = "www.google.com";
        ZoomasterException e = assertThrows(ZoomasterException.class, () ->
                Bookmark.extractDescriptionAndUrl(missingDescription));
        assertEquals(ZoomasterExceptionType.INVALID_ADD_BOOKMARK_INPUT, e.getError());

        String missingUrl = "description";
        ZoomasterException e2 = assertThrows(ZoomasterException.class, () ->
                Bookmark.extractDescriptionAndUrl(missingUrl));
        assertEquals(ZoomasterExceptionType.INVALID_ADD_BOOKMARK_INPUT, e2.getError());
    }

    @Test
    void extractModuleDescriptionAndUrl_invalidUrl_throwsZoomasterException() {
        String invalidUrl = "CS2113T google.com";
        ZoomasterException e = assertThrows(ZoomasterException.class, () ->
                Bookmark.extractDescriptionAndUrl(invalidUrl));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e.getError());

        String additionalParameter = "description www.google.com additional_word";
        ZoomasterException e2 = assertThrows(ZoomasterException.class, () ->
                Bookmark.extractDescriptionAndUrl(additionalParameter));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e2.getError());
    }

    @Test
    void launch() throws ZoomasterException {
        assertEquals("  [desc] www.google.com", bookmarkTest.launch());
    }

    @Test
    void setUrl_invalidUrl_throwsZoomasterException() {
        // missing "www.", "http://" or "https://"
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> bookmarkTest.setUrl("google.com"));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e.getError());

        // contains spacing
        ZoomasterException e2 = assertThrows(ZoomasterException.class, () -> bookmarkTest.setUrl("www.google.com hi"));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e2.getError());
    }

    @Test
    void getBookmarkAsString_returnsDescriptionAndUrl() {
        String expectedString = "[desc] www.google.com" + System.lineSeparator();
        assertEquals(expectedString, bookmarkTest.getBookmarkAsString());
    }

}