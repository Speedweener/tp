package seedu.zoomaster.command.planner;

import org.junit.jupiter.api.Test;

import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;
import seedu.zoomaster.slot.Module;

import java.time.LocalTime;

//@@author jusufnathanael
class LoadPlannerCommandTest {

    LoadPlannerCommand command = new LoadPlannerCommand("load");

    LoadPlannerCommandTest() throws ZoomasterException {
    }

    @Test
    void testInitialiseEmptySlots() throws ZoomasterException {
        Timetable t = new Timetable();
        t.addModule("CS1010");
        t.addModule("CS1231");
        Slot slot1 = new Slot(LocalTime.parse("12:00"), LocalTime.parse("14:00"), "mon", "lecture");
        Slot slot2 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "tue", "consultation");
        Slot slot3 = new Slot(LocalTime.parse("09:30"), LocalTime.parse("11:30"), "thu", "lecture");
        t.getModule("CS1010").addSlot(slot1);
        t.getModule("CS1010").addSlot(slot2);
        t.getModule("CS1231").addSlot(slot3);

        Module test = new Module("EMPTY");
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("12:00"), "mon", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("14:00"), LocalTime.parse("23:55"), "mon", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("10:00"), "tue", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("11:00"), LocalTime.parse("23:55"), "tue", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "wed", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("09:30"), "thu", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("11:30"), LocalTime.parse("23:55"), "thu", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "fri", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "sat", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "sun", "<empty slot>"));

        assert (test.getSlotList().toString().equals(command.initialiseEmptySlots(t).getSlotList().toString()));
    }

}