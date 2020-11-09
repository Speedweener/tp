package seedu.zoomaster.command.timetable;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xingrong123
class DeleteSlotCommandTest {

    @Test
    void constructor_invalidInput_throwsZoomasterException() {
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> new DeleteSlotCommand("delete"));
        assertEquals(ZoomasterExceptionType.EMPTY_COMMAND, e.getError());

        ZoomasterException e2 = assertThrows(ZoomasterException.class, () -> new DeleteSlotCommand("deleteNo Space"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e2.getError());

        ZoomasterException e3 = assertThrows(ZoomasterException.class, () ->
                new DeleteSlotCommand("delete CS2113T error"));
        assertEquals(ZoomasterExceptionType.NON_INTEGER_INPUT, e3.getError());
    }
}