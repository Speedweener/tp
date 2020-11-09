package seedu.zoomaster.slot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author xingrong123
class DayTest {

    @Test
    void isDay_invalidDay_false() {
        assertFalse(Day.isDay("monday"));
        assertFalse(Day.isDay("mond"));
        assertFalse(Day.isDay("invalid"));
    }

    @Test
    void isDay_validDay_true() {
        assertTrue(Day.isDay("mon"));
        assertTrue(Day.isDay("Tue"));
        assertTrue(Day.isDay("WED"));
    }

    @Test
    void getDayFromCommand_validDayInputs_smallLetterAbbreviationOfDays() {
        assertEquals("mon", Day.getDayFromCommand("mon"));
        assertEquals("tue", Day.getDayFromCommand("Tue"));
        assertEquals("wed", Day.getDayFromCommand("WED"));
    }

    @Test
    void getDayFromCommand_invalidDayInputs_null() {
        assertNull(Day.getDayFromCommand("monday"));
        assertNull(Day.getDayFromCommand("hello"));
        assertNull(Day.getDayFromCommand("mon "));
    }
}