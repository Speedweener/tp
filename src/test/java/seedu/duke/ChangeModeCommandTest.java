package seedu.duke;

import seedu.duke.command.ChangeModeCommand;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeModeCommandTest {

    @Test
    public void testChangeToBookmarkMode() {
        assertEquals(1, new ChangeModeCommand("mode bookmark").setToMode);
    }

    @Test
    public void testChangeToTimetableMode() {
        assertEquals(2, new ChangeModeCommand("mode timetable").setToMode);
    }

    @Test
    public void testInvalidModeInput() {
        assertEquals(0, new ChangeModeCommand("mode abcd").setToMode);
    }
}
