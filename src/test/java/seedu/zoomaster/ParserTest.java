package seedu.zoomaster;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author Speedweener
class ParserTest {


    @Test
    void parseInvalidCommandThrowsUnknownInputException() {
        Parser.programMode = 0;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse("iNvAlidCOmmand"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e.getError());
        Parser.programMode = 1;
        ZoomasterException f = assertThrows(ZoomasterException.class, () -> Parser.parse("iNvAlidCOmmand"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, f.getError());
        Parser.programMode = 2;
        ZoomasterException g = assertThrows(ZoomasterException.class, () -> Parser.parse("iNvAlidCOmmand"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, g.getError());
    }

    @Test
    void parseEmptyCommandThrowsUnknownInputException() {
        Parser.programMode = 0;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse(""));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e.getError());
        Parser.programMode = 1;
        ZoomasterException f = assertThrows(ZoomasterException.class, () -> Parser.parse(""));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, f.getError());
        Parser.programMode = 2;
        ZoomasterException g = assertThrows(ZoomasterException.class, () -> Parser.parse(""));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, g.getError());
    }

    @Test
    void parseHelpCommandWithInvalidCommandThrowsUnknownHelpCommand() {
        Parser.programMode = 0;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse("helpextrawords"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, e.getError());
        Parser.programMode = 1;
        ZoomasterException f = assertThrows(ZoomasterException.class, () -> Parser.parse("helpextrawords"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, f.getError());
        Parser.programMode = 2;
        ZoomasterException g = assertThrows(ZoomasterException.class, () -> Parser.parse("helpextrawords"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_HELP_COMMAND, g.getError());
    }

    @Test
    void parseInvalidCommandsMode0ThrowsUnknownInputException() {
        Parser.programMode = 0;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse("add"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e.getError());
        ZoomasterException f = assertThrows(ZoomasterException.class, () -> Parser.parse("delete"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, f.getError());
        ZoomasterException g = assertThrows(ZoomasterException.class, () -> Parser.parse("show"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, g.getError());
        ZoomasterException h = assertThrows(ZoomasterException.class, () -> Parser.parse("find"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, h.getError());
        ZoomasterException i = assertThrows(ZoomasterException.class, () -> Parser.parse("launch"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, i.getError());
    }

    @Test
    void parseInvalidCommandsMode1ThrowsUnknownInputException() {
        Parser.programMode = 1;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse("load"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e.getError());
    }

    @Test
    void parseInvalidCommandsMode2ThrowsUnknownInputException() {
        Parser.programMode = 2;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse("find"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e.getError());
        ZoomasterException f = assertThrows(ZoomasterException.class, () -> Parser.parse("load"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, f.getError());
    }

    @Test
    void parseInvalidCommandsMode3ThrowsUnknownInputException() {
        Parser.programMode = 3;
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> Parser.parse("find"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, e.getError());
        ZoomasterException f = assertThrows(ZoomasterException.class, () -> Parser.parse("delete"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, f.getError());
        ZoomasterException h = assertThrows(ZoomasterException.class, () -> Parser.parse("find"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, h.getError());
        ZoomasterException i = assertThrows(ZoomasterException.class, () -> Parser.parse("launch"));
        assertEquals(ZoomasterExceptionType.UNKNOWN_INPUT, i.getError());
        Parser.programMode = 0;
    }


    @Test
    void getProgramMode() {
    }
}