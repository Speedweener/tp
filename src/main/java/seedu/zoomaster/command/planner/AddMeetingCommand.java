package seedu.zoomaster.command.planner;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.timetable.AddSlotCommand;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Day;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

//@@author jusufnathanael
public class AddMeetingCommand extends AddSlotCommand {
    public static final String ADD_KW = "add";

    /**
     * Constructs a new AddSlotCommand instance and stores the information of the slot given by the input.
     *
     * @param command The user input command.
     * @throws ZoomasterException if input command is invalid.
     */
    public AddMeetingCommand(String command) throws ZoomasterException {

        super(command);
    }

    /**
     * Adds the slot to the slot list and saves the slots list in the text file.
     *
     * @param bookmarks The list of bookmarks
     * @param timetable The timetable
     * @param ui The user interface
     * @throws ZoomasterException Some exception // ADD MORE COMMENTS
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {

        super.execute(bookmarks, timetable, ui);
    }

    /**
     * Creates a new slot.
     *
     * @param command The add command.
     * @param module The module of the slot.
     * @param timetable The planner timetable.
     * @return Ui messages whether the adding process is successful or not.
     * @throws ZoomasterException Some exceptions.
     */
    @Override
    protected String create(String command, Module module, Timetable timetable) throws ZoomasterException {
        String message = "";
        List<String> slotAndBookmark = Arrays.asList(command.trim().split("\\s+"));
        if (isAddModuleBookmark(slotAndBookmark)) {
            message = addBookmarkToModule(module, slotAndBookmark);
        } else {
            String lesson = slotAndBookmark.get(0);
            String day = slotAndBookmark.get(1);
            if (!Day.isDay(day)) {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIMETABLE_DAY, "  invalid day");
            }
            LocalTime startTime;
            LocalTime endTime;
            try {
                startTime = LocalTime.parse(slotAndBookmark.get(2));
                endTime = LocalTime.parse(slotAndBookmark.get(3));
            } catch (DateTimeParseException e) {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIME_FORMAT, "Invalid time format. ("
                        + slotAndBookmark.get(2) + " " + slotAndBookmark.get(3) + ") Please check format.");
            }
            Slot newSlot;
            List<Slot> slots = timetable.getFullSlotList();
            if (isAvailable(day, startTime, endTime, slots, timetable)) {
                newSlot = new Slot(startTime, endTime, day, lesson);
                module.addSlot(newSlot);
                message +=  "  " + lesson + " slot added\n";
                message += checkForAndAddBookmarkToSlot(slotAndBookmark, lesson, newSlot);
            } else {
                message +=  "  " + "This slot is already filled." + Ui.NEW_LINE;
            }
        }
        return message;
    }

    /**
     * Checks if the slot can be added.
     *
     * @param day The day of the slot.
     * @param startTime The start time of the slot.
     * @param endTime The end time of the slot.
     * @param slots The list of empty slots.
     * @param localTimetable The planner timetable.
     * @return True if the slot can be added.
     */
    private boolean isAvailable(String day, LocalTime startTime, LocalTime endTime,
                                List<Slot> slots, Timetable localTimetable) {
        for (Slot s: slots) {
            if (s.getDay().equals(day) && s.getTitle().equals("<empty slot>")
                    && s.getStartTime().compareTo(startTime) <= 0 && s.getEndTime().compareTo(endTime) >= 0) {
                updateEmptySlot(s, startTime, endTime, localTimetable);
                return true;
            }
        }
        return false;
    }

    /**
     * Add the slot and update the list of empty slots.
     *
     * @param slot The new slot.
     * @param startTime The start time of the slot.
     * @param endTime The end time of the slot.
     * @param localTimetable The planner timetable.
     */
    private void updateEmptySlot(Slot slot, LocalTime startTime, LocalTime endTime, Timetable localTimetable) {
        Slot slot1;
        Slot slot2;
        if (slot.getStartTime().compareTo(startTime) != 0) {
            slot1 = new Slot(slot.getStartTime(), startTime, slot.getDay(), slot.getTitle());
            localTimetable.getModule("EMPTY").addSlot(slot1);
        }
        if (slot.getEndTime().compareTo(endTime) != 0) {
            slot2 = new Slot(endTime, slot.getEndTime(), slot.getDay(), slot.getTitle());
            localTimetable.getModule("EMPTY").addSlot(slot2);
        }
        localTimetable.getModule("EMPTY").removeSlot(slot);
    }
}
