package seedu.zoomaster.slot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author xingrong123
class SlotTest {
    Slot slotTest;
    Slot slotTest2;

    @BeforeEach
    public void initEachSlotTest() {
        slotTest = new Slot(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "mon", "tutorial");
        slotTest2 = new Slot(LocalTime.parse("10:30"), LocalTime.parse("11:30"), "tue", "lecture");
    }

    @Test
    void match_validDetails_returnsTrue() {
        assertTrue(slotTest.match("tutorial", "mon", LocalTime.parse("10:00"), LocalTime.parse("11:00")));

        // different case for lesson
        assertTrue(slotTest.match("TUTORIAL", "mon", LocalTime.parse("10:00"), LocalTime.parse("11:00")));

        // different case for day
        assertTrue(slotTest.match("tutorial", "MON", LocalTime.parse("10:00"), LocalTime.parse("11:00")));
    }

    @Test
    void match_invalidDetails_returnsFalse() {
        // different lesson
        assertFalse(slotTest.match("lecture", "mon", LocalTime.parse("10:00"), LocalTime.parse("11:00")));

        // different day
        assertFalse(slotTest.match("tutorial", "tue", LocalTime.parse("10:00"), LocalTime.parse("11:00")));

        // different start time
        assertFalse(slotTest.match("tutorial", "mon", LocalTime.parse("09:00"), LocalTime.parse("11:00")));

        // different end time
        assertFalse(slotTest.match("tutorial", "mon", LocalTime.parse("10:00"), LocalTime.parse("11:11")));
    }

    @Test
    void getStartMinutes_returnCorrectValue() {
        // only hours
        assertEquals(600, slotTest.getStartMinutes());

        // hours and minutes
        assertEquals(630, slotTest2.getStartMinutes());
    }

    @Test
    void getEndMinutes_returnCorrectValue() {
        // only hours
        assertEquals(660, slotTest.getEndMinutes());

        // hours and minutes
        assertEquals(690, slotTest2.getEndMinutes());
    }

    @Test
    void convertIntToLocalTime() {
        // hours < 10, minutes < 10
        assertEquals(LocalTime.parse("09:09"), Slot.convertIntToLocalTime(9, 9));

        // hours < 10, minutes >= 10
        assertEquals(LocalTime.parse("09:10"), Slot.convertIntToLocalTime(9, 10));

        // hours >= 10, minutes < 10
        assertEquals(LocalTime.parse("10:09"), Slot.convertIntToLocalTime(10, 9));

        // hours >= 10, minutes >= 10
        assertEquals(LocalTime.parse("10:10"), Slot.convertIntToLocalTime(10, 10));
    }

    @Test
    void toString_returnsCorrectFormat() {
        assertEquals("10:00-11:00 tutorial", slotTest.toString());
    }
}