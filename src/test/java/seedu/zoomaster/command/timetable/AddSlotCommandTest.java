package seedu.zoomaster.command.timetable;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xingrong123
class AddSlotCommandTest {

    @Test
    void constructor_invalidInput_throwsDukeException() {
        // empty detail
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> new AddSlotCommand("add"));
        assertEquals(ZoomasterExceptionType.EMPTY_COMMAND, e.getError());
        // blank detail
        ZoomasterException e2 = assertThrows(ZoomasterException.class, () -> new AddSlotCommand("add  "));
        assertEquals(ZoomasterExceptionType.EMPTY_COMMAND, e2.getError());

        // no whitespace
        ZoomasterException e3 = assertThrows(ZoomasterException.class, () -> new AddSlotCommand("addCS2113T"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e3.getError());
    }

}
