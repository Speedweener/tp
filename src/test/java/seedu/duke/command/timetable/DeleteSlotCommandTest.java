package seedu.duke.command.timetable;

import org.junit.jupiter.api.Test;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.bookmark.BookmarkList;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeExceptionType;
import seedu.duke.slot.Slot;
import seedu.duke.slot.SlotList;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DeleteSlotCommandTest {

    @Test
    void constructor_invalidInput_throwsDukeException() {
        String input = DeleteSlotCommand.DEL_KW + " 0";

        try {
            DeleteSlotCommand command = new DeleteSlotCommand(input);
        } catch (DukeException e) {
            assertEquals(e.getError(), DukeExceptionType.INVALID_SLOT_NUMBER);
        }
    }

    @Test
    void execute() {
        String startTime = "10:00";
        String endTime = "12:00";
        String title = "CS1231 Lecture";

        SlotList slots = new SlotList();
        slots.addSlot(new Slot(LocalTime.parse(startTime),
                LocalTime.parse(endTime),
                Slot.FRI,
                title
        ));

        String input = DeleteSlotCommand.DEL_KW + " 1";
        try {
            DeleteSlotCommand command = new DeleteSlotCommand(input);
            command.execute(new BookmarkList(), slots, new Ui(), new Storage("test.txt"), new Storage("test.txt"));
        } catch (DukeException e) {
            fail(e.getMessage());
        }

        assertEquals(new SlotList().getData(), slots.getData());
    }
}