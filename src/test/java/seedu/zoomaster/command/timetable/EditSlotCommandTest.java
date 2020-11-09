package seedu.zoomaster.command.timetable;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;

import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//@@author fchensan
class EditSlotCommandTest {

    private static final String DEFAULT_START_TIME = "10:00";
    private static final String DEFAULT_END_TIME = "12:00";
    private static final String DEFAULT_DAY = "fri";
    private static final String DEFAULT_TITLE = "lecture";
    private static final String DEFAULT_MODULE_TITLE = "lecture";

    Timetable timetable;
    BookmarkList bookmarks = new BookmarkList();
    Ui ui = new Ui();

    void setupTimetable() {
        timetable = new Timetable();
        Module m1 = new Module(DEFAULT_MODULE_TITLE);
        m1.addSlot(new Slot(LocalTime.parse(DEFAULT_START_TIME),
                LocalTime.parse(DEFAULT_END_TIME),
                DEFAULT_DAY,
                DEFAULT_TITLE
        ));
        timetable.addModule(m1);
    }

    @Test
    void constructor_invalidInput_throwsZoomasterException() throws ZoomasterException {
        String input = EditSlotCommand.EDIT_KW + " mon 1 abcd abcd abcd";

        setupTimetable();

        EditSlotCommand command = new EditSlotCommand(input);
        assertThrows(ZoomasterException.class, () -> command.execute(bookmarks, timetable, ui));
    }

    @Test
    void execute_editModule_changesSuccessfully() {
        setupTimetable();

        String input = EditSlotCommand.EDIT_KW + " module fri 1 CS2113";

        EditSlotCommand command = null;
        Slot s = null;

        try {
            command = new EditSlotCommand(input);
            command.execute(bookmarks, timetable, ui);
            s = timetable.getModule("CS2113").getSlot(0);
        } catch (ZoomasterException e) {
            fail();
        }

        assertEquals(s.getStartTime(), LocalTime.parse(DEFAULT_START_TIME));
        assertEquals(s.getEndTime(), LocalTime.parse(DEFAULT_END_TIME));
        assertEquals(s.getDay(), DEFAULT_DAY);
        assertEquals(s.getTitle(), DEFAULT_TITLE);
    }

    @Test
    void execute_editTitle_changesSuccessfully() {
        setupTimetable();

        String newTitle = "tutorial";

        String input = EditSlotCommand.EDIT_KW + " title fri 1 " + newTitle;

        EditSlotCommand command = null;
        Slot s = null;

        try {
            command = new EditSlotCommand(input);
            command.execute(bookmarks, timetable, ui);
            s = timetable.getModule(DEFAULT_MODULE_TITLE).getSlot(0);
        } catch (ZoomasterException e) {
            fail();
        }

        assertEquals(s.getStartTime(), LocalTime.parse(DEFAULT_START_TIME));
        assertEquals(s.getEndTime(), LocalTime.parse(DEFAULT_END_TIME));
        assertEquals(s.getDay(), DEFAULT_DAY);
        assertEquals(s.getTitle(), newTitle);
    }

    @Test
    void execute_editTime_changesSuccessfully() {
        setupTimetable();

        String newDay = "wed";
        String newStartTime = "15:00";
        String newEndTime = "17:00";

        String input = EditSlotCommand.EDIT_KW + " time fri 1 " + newDay + " " + newStartTime + " " + newEndTime;

        EditSlotCommand command = null;
        Slot s = null;

        try {
            command = new EditSlotCommand(input);
            command.execute(bookmarks, timetable, ui);
            s = timetable.getModule(DEFAULT_MODULE_TITLE).getSlot(0);
        } catch (ZoomasterException e) {
            fail();
        }

        assertEquals(s.getStartTime().toString(), newStartTime);
        assertEquals(s.getEndTime().toString(), newEndTime);
        assertEquals(s.getDay(), newDay);
        assertEquals(s.getTitle(), DEFAULT_TITLE);
    }

}
