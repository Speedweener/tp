package seedu.zoomaster.command.timetable;

import org.junit.jupiter.api.Test;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author
public class ShowTimetableCommandTest {
    static Timetable time1 = new Timetable();


    @Test
    public void testShowAllSlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testMondaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show mon");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testTuesdaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show tue");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testWednesdaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show wed");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testThursdaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show thu");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testFridaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show fri");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testSatudaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show sat");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testSundaySlots() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show sun");
            setupTimetable();
            timetable.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    @Test
    public void testInvalidSlots1() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show1");
            assertEquals(false, true);
        } catch (ZoomasterException e) {
            assertEquals(true, true);
        }
    }

    @Test
    public void testInvalidSlots2() {
        ShowTimetableCommand timetable;
        try {
            timetable = new ShowTimetableCommand("show abc");
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

    public static void setupTimetable() {
        try {
            AddSlotCommand addSlot1 = new AddSlotCommand("add laj1201 tutorial_1 mon 08:00 10:00");
            addSlot1.execute(new BookmarkList(), time1, new Ui());
            AddSlotCommand addSlot2 = new AddSlotCommand("add cs2101 tutorial_1 tue 10:00 12:00");
            addSlot2.execute(new BookmarkList(), time1, new Ui());
            AddSlotCommand addSlot3 = new AddSlotCommand("add cg2271 lecture wed 09:00 11:00 ");
            addSlot3.execute(new BookmarkList(), time1, new Ui());
            AddSlotCommand addSlot4 = new AddSlotCommand("add laj1201 tutorial_2 thu 08:00 10:00 ");
            addSlot4.execute(new BookmarkList(), time1, new Ui());
            AddSlotCommand addSlot5 = new AddSlotCommand("add cg2271 lab fri 08:00 10:00 ");
            addSlot5.execute(new BookmarkList(), time1, new Ui());
        } catch (ZoomasterException e) {
            assertEquals(false, true);
        }
    }

}
