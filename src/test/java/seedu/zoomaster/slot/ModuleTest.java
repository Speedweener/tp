package seedu.zoomaster.slot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author xingrong123
class ModuleTest {
    Module moduleTest;
    Module moduleTest2;

    @BeforeEach
    public void initEachSlotTest() {
        moduleTest = new Module("CS2113T");
        moduleTest2 = new Module("CS2101");
    }

    @Test
    void addSlot_addSlotsWithDifferentDayAndStartTime_slotsOrderedAccordingToDayAndTime() throws ZoomasterException {
        Slot slot1 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "wed", "input1");
        Slot slot2 = new Slot(LocalTime.parse("12:00"), LocalTime.parse("13:00"), "wed", "input2");
        Slot slot3 = new Slot(LocalTime.parse("08:00"), LocalTime.parse("09:00"), "wed", "input3");
        Slot slot4 = new Slot(LocalTime.parse("02:00"), LocalTime.parse("03:00"), "fri", "input4");
        Slot slot5 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "fri", "input5");
        Slot slot6 = new Slot(LocalTime.parse("15:00"), LocalTime.parse("16:00"), "mon", "input6");
        Slot slot7 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "mon", "input7");

        // target slot
        moduleTest.addSlot(slot1);
        // same day later time
        moduleTest.addSlot(slot2);
        // same day earlier time
        moduleTest.addSlot(slot3);
        // later day earlier time
        moduleTest.addSlot(slot4);
        // later day same time
        moduleTest.addSlot(slot5);
        // earlier day later time
        moduleTest.addSlot(slot6);
        // earlier day same time
        moduleTest.addSlot(slot7);

        // slots are ordered by day first then time
        assertEquals(slot7, moduleTest.getSlot(0));
        assertEquals(slot6, moduleTest.getSlot(1));
        assertEquals(slot3, moduleTest.getSlot(2));
        assertEquals(slot1, moduleTest.getSlot(3));
        assertEquals(slot2, moduleTest.getSlot(4));
        assertEquals(slot4, moduleTest.getSlot(5));
        assertEquals(slot5, moduleTest.getSlot(6));
    }

    @Test
    void getSlot_emptyListOrInvalidIndex_throwsZoomasterException() throws ZoomasterException {

        // empty list
        ZoomasterException e = assertThrows(ZoomasterException.class, () -> moduleTest.getSlot(0));
        assertEquals(ZoomasterExceptionType.ZERO_SLOTS_IN_MODULE, e.getError());

        ZoomasterException e2 = assertThrows(ZoomasterException.class, () -> moduleTest.getSlot(1));
        assertEquals(ZoomasterExceptionType.ZERO_SLOTS_IN_MODULE, e2.getError());

        ZoomasterException e3 = assertThrows(ZoomasterException.class, () -> moduleTest.getSlot(-1));
        assertEquals(ZoomasterExceptionType.ZERO_SLOTS_IN_MODULE, e3.getError());

        // non-empty list
        Slot slot1 = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "wed", "input1");
        moduleTest.addSlot(slot1);

        assertEquals(slot1, moduleTest.getSlot(0));

        ZoomasterException e5 = assertThrows(ZoomasterException.class, () -> moduleTest.getSlot(1));
        assertEquals(ZoomasterExceptionType.INVALID_SLOT_NUMBER, e5.getError());

        ZoomasterException e6 = assertThrows(ZoomasterException.class, () -> moduleTest.getSlot(-1));
        assertEquals(ZoomasterExceptionType.INVALID_SLOT_NUMBER, e6.getError());
    }

    @Test
    void getBookmarksToString_emptyList_returnsEmptyListMessage() {
        assertEquals("no bookmarks found in module" + System.lineSeparator() + System.lineSeparator(),
                moduleTest.getBookmarksToString());
    }
}