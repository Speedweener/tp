package seedu.zoomaster.slot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author xingrong123
class TimetableTest {
    Timetable timetableTest;
    Module moduleTest;
    Slot slotTest;

    @BeforeEach
    public void initEachSlotTest() {
        moduleTest = new Module("CS2113T");
        timetableTest = new Timetable();
        slotTest = new SlotStub();
    }

    @Test
    void moduleExists_upperAndLowerCase_returnsTrue() {
        timetableTest.addModule(moduleTest);
        assertTrue(timetableTest.moduleExists("CS2113T"));
        assertTrue(timetableTest.moduleExists("cs2113t"));
    }

    @Test
    void moduleExists_mismatchModuleCode_returnsFalse() {
        timetableTest.addModule(moduleTest);
        assertFalse(timetableTest.moduleExists("CS2113"));
    }

    @Test
    void isOverlapTimeSlotTest() {
        timetableTest.addModule(moduleTest);
        moduleTest.addSlot(slotTest);
        // return value of stub: day = "wed", startTime = 11:00, endTime = 12:00

        // earlier day same time
        assertFalse(timetableTest.isOverlapTimeSlot("mon", LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        // later day same time
        assertFalse(timetableTest.isOverlapTimeSlot("fri", LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        // same day different time
        assertFalse(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("12:00"), LocalTime.parse("15:00")));
        // same day start time of input == end time of stub
        assertFalse(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("12:00"), LocalTime.parse("13:00")));
        // same day start time of stub == end time of input
        assertFalse(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("10:00"), LocalTime.parse("11:00")));
        // same day same start and end time
        assertTrue(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("11:00"), LocalTime.parse("12:00")));
        // same day start time of input overlap
        assertTrue(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("11:30"), LocalTime.parse("13:00")));
        // same day end time of input overlap
        assertTrue(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("10:00"), LocalTime.parse("11:30")));
        // same day start and end time of input in stub
        assertTrue(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("11:10"), LocalTime.parse("11:50")));
        // same day start and end time of stub in input
        assertTrue(timetableTest.isOverlapTimeSlot("wed", LocalTime.parse("10:00"), LocalTime.parse("13:00")));

    }
}