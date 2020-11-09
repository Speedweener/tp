package seedu.zoomaster.command.planner;

import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;

//@@author jusufnathanael
class AddMeetingCommandTest {

    LoadPlannerCommand load = new LoadPlannerCommand("load");

    AddMeetingCommandTest() throws ZoomasterException {
    }

    @Test
    void testCreate() throws ZoomasterException {

        Module test = new Module("EMPTY");
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("12:00"), "mon", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("14:00"), LocalTime.parse("23:55"), "mon", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "wed", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "fri", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "sat", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("23:55"), "sun", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("10:00"), "tue", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("11:00"), LocalTime.parse("23:55"), "tue", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("00:00"), LocalTime.parse("09:30"), "thu", "<empty slot>"));
        test.addSlot(new Slot(LocalTime.parse("11:30"), LocalTime.parse("23:55"), "thu", "<empty slot>"));

        Timetable result = new Timetable();
        result.addModule(test);


        Timetable temp = new Timetable();
        temp.addModule("CS1010");
        temp.getModule("CS1010").addSlot(new Slot(LocalTime.parse("12:00"), LocalTime.parse("14:00"), "mon", "lec"));

        Timetable compare = new Timetable();
        compare.addModule(load.initialiseEmptySlots(temp));

        Module module1 = new Module("CS3243");
        Module module2 = new Module("CS3244");
        String command1 = "tutorial tue 10:00 11:00";
        String command2 = "lecture thu 09:30 11:30";
        String command3 = "lecture mon 11:00 13:00";

        AddMeetingCommand add1 = new AddMeetingCommand("add CS3243");
        add1.create(command1, module1, compare);
        AddMeetingCommand add2 = new AddMeetingCommand("add CS3244");
        add2.create(command2, module2, compare);
        AddMeetingCommand add3 = new AddMeetingCommand("add CS3244");
        add3.create(command3, module2, compare);

        assert (compare.getFullSlotList().toString().equals(result.getFullSlotList().toString()));

    }

}