package seedu.zoomaster.command.planner;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;

//@@author jusufnathanael
class SavePlannerCommandTest {

    SavePlannerCommand save = new SavePlannerCommand("save");
    LoadPlannerCommand load = new LoadPlannerCommand("load");

    SavePlannerCommandTest() throws ZoomasterException {
    }

    @Test
    void testWriteToFile() throws ZoomasterException {

        Timetable t = new Timetable();
        t.addModule("CS1010");
        t.addModule("CS1231");
        Slot slot1 = new Slot(LocalTime.parse("12:00"), LocalTime.parse("14:00"), "mon", "lecture");
        Slot slot2 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "tue", "consultation");
        Slot slot3 = new Slot(LocalTime.parse("09:30"), LocalTime.parse("11:30"), "thu", "lecture");
        t.getModule("CS1010").addSlot(slot1);
        t.getModule("CS1010").addSlot(slot2);
        t.getModule("CS1231").addSlot(slot3);

        Timetable result = new Timetable();
        result.addModule("CS1010");
        result.addModule("CS1231");
        result.getModule("CS1010").addSlot(slot1);
        result.getModule("CS1010").addSlot(slot2);
        result.getModule("CS1231").addSlot(slot3);

        Timetable planner = new Timetable();
        planner.addModule(load.initialiseEmptySlots(t));

        planner.addModule("CS1010");
        planner.addModule("CS2040C");
        result.addModule("CS2040C");
        Slot slot4 = new Slot(LocalTime.parse("14:00"), LocalTime.parse("16:00"), "fri", "tutorial");
        Slot slot5 = new Slot(LocalTime.parse("16:00"), LocalTime.parse("18:00"), "thu", "tutorial");

        planner.getModule("CS2040C").addSlot(slot4);
        planner.getModule("CS1010").addSlot(slot5);
        result.getModule("CS2040C").addSlot(slot4);
        result.getModule("CS1010").addSlot(slot5);

        save.writeToFile(planner, t);
        assert (t.getFullSlotList().toString().equals(result.getFullSlotList().toString()));

    }

}