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

class AddSlotCommandTest {

    @Test
    void constructor_invalidInput_throwsDukeException() {
        String input = AddSlotCommand.ADD_KW;

        try {
            AddSlotCommand command = new AddSlotCommand(input);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getError(), DukeExceptionType.INVALID_SLOT_INPUT);
        }

        input = AddSlotCommand.ADD_KW + "1111 12:00 mon CS1231 lecture";

        try {
            AddSlotCommand command = new AddSlotCommand(input);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getError(), DukeExceptionType.INVALID_TIME_FORMAT);
        }
    }

    @Test
    void execute_validInput_addsSlotInSlotSlit() {
        String startTime = "10:00";
        String endTime = "12:00";
        String title = "CS1231 Lecture";
        String input = AddSlotCommand.ADD_KW + " " + startTime + " " + endTime + " " + Slot.FRI + " " + title;

        SlotList expectedSlots = new SlotList();
        expectedSlots.addSlot(new Slot(LocalTime.parse(startTime),
                LocalTime.parse(endTime),
                Slot.FRI,
                title
                ));

        AddSlotCommand command;
        SlotList slots = new SlotList();

        try {
            command = new AddSlotCommand(input);
            command.execute(new BookmarkList(),
                    slots,
                    new Ui(),
                    new Storage("test.txt"),
                    new Storage("test.txt"));
        } catch (DukeException e) {
            fail(e.getMessage());
        }

        assertEquals(expectedSlots.getData(), slots.getData());
    }
}