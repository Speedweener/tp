package seedu.zoomaster.command.bookmark;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xingrong123
class AddBookmarkCommandTest {

    @Test
    void constructor_invalidInput_throwsZoomasterException() {
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> new AddBookmarkCommand("add"));
        assertEquals(ZoomasterExceptionType.EMPTY_COMMAND, e.getError());

        ZoomasterException e2 = assertThrows(ZoomasterException.class, () -> new AddBookmarkCommand("addNo Space"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e2.getError());

        ZoomasterException e3 = assertThrows(ZoomasterException.class, () -> new AddBookmarkCommand("add noUrl"));
        assertEquals(ZoomasterExceptionType.INVALID_ADD_BOOKMARK_INPUT, e3.getError());

        ZoomasterException e4 = assertThrows(ZoomasterException.class, () -> new AddBookmarkCommand("add noDesc"));
        assertEquals(ZoomasterExceptionType.INVALID_ADD_BOOKMARK_INPUT, e4.getError());

        ZoomasterException e5 = assertThrows(ZoomasterException.class, () ->
                new AddBookmarkCommand("add desc www.google.com extraWord"));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e5.getError());

        ZoomasterException e6 = assertThrows(ZoomasterException.class, () ->
                new AddBookmarkCommand("add desc google.com"));
        assertEquals(ZoomasterExceptionType.INVALID_URL, e6.getError());
    }
}