package seedu.zoomaster.command.timetable;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Day;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@author fchensan
public class EditSlotCommand extends Command {
    public static final String EDIT_KW = "edit";

    public static final String FIELD_KW_MODULE = "module";
    public static final String FIELD_KW_TITLE = "title";
    public static final String FIELD_KW_TIME = "time";

    private String fieldToEdit;
    private String details;

    /**
     * Creates a new EditSlotCommand based on user input.
     *
     * @param command The command input by the user.
     * @throws ZoomasterException if the user inputs in an invalid format.
     */
    public EditSlotCommand(String command) throws ZoomasterException {
        assert command.startsWith(EDIT_KW);

        // details: "NEW_MODULE_CODE" or "NEW_TITLE" or "NEW_DAY START_TIME END_TIME"
        details = command.substring(EDIT_KW.length()).trim();

        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, EDIT_KW);
        }

        Pattern parser = Pattern.compile(
                "(?<fieldToEdit>[a-zA-Z]+)\\s+(?<details>.+)"
        );
        Matcher matcher = parser.matcher(details);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }

        fieldToEdit = matcher.group("fieldToEdit");
        details = matcher.group("details");
    }

    /**
     * Edits a slot's module, title, or time based on user input.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The list of slots.
     * @param ui The user interface.
     * @throws ZoomasterException if there is an error in user input format.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        Pattern parser = Pattern.compile(
                "(?<day>[a-zA-Z]+)\\s+(?<indexInDay>\\d+)\\s+(?<firstArgument>\\w+)"
                + "(\\s+(?<startTime>[\\w:]+)\\s+(?<endTime>[\\w:]+))?"
        );
        Matcher matcher = parser.matcher(details);

        if (!matcher.matches()) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }

        String day = matcher.group("day");
        int indexInDay = Integer.parseInt(matcher.group("indexInDay"));
        Slot slot;

        String message = "";

        switch (fieldToEdit) {
        case FIELD_KW_MODULE:
            if (Module.isValidModule(matcher.group("firstArgument").toUpperCase())) {
                timetable.moveSlotToAnotherModule(day, indexInDay, matcher.group("firstArgument"));
                message = "Slot moved to " + matcher.group("firstArgument").toUpperCase();
            } else {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_MODULE);
            }
            break;
        case FIELD_KW_TITLE:
            slot = timetable.getSlotByIndexInDay(day, indexInDay);
            slot.setTitle(matcher.group("firstArgument"));
            message = "Slot title changed to " + matcher.group("firstArgument");

            break;
        case FIELD_KW_TIME:

            try {
                String firstArgument = matcher.group("firstArgument");
                String startTime = matcher.group("startTime");
                String endTime = matcher.group("endTime");
            } catch (IllegalStateException e) {
                throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIME_FORMAT);
            }


            slot = timetable.getSlotByIndexInDay(day, indexInDay);
            message = changeSlotTime(
                    slot,
                    matcher.group("firstArgument"),
                    matcher.group("startTime"),
                    matcher.group("endTime"),
                    timetable);

            break;
        default:
            message = "Unrecognized field!";
            break;
        }

        ui.print(message + Ui.NEW_LINE);
    }

    /**
     * Change a slot's start and end time with the given user input.
     *
     * @param slot The slot object with the day we want to change.
     * @param startTimeInput The new start time as string.
     * @param endTimeInput The new end time as string.
     */
    private String changeSlotTime(
            Slot slot, String dayInput, String startTimeInput, String endTimeInput, Timetable timetable)
            throws ZoomasterException {
        LocalTime startTime;
        LocalTime endTime;


        try {
            startTime = LocalTime.parse(startTimeInput);
            endTime = LocalTime.parse(endTimeInput);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_EDIT_INPUT);
        }

        if (!Day.isDay(dayInput)) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_TIMETABLE_DAY);
        }

        Module slotModule = timetable.getModuleContainingSlot(slot);
        slotModule.removeSlot(slot);

        if (timetable.isOverlapTimeSlot(dayInput, startTime, endTime)) {
            slotModule.addSlot(slot);
            return "Timing clashes with another slot!";
        }

        slotModule.addSlot(slot);

        slot.setDay(dayInput);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);

        return "Slot time changed to " + dayInput + " " + startTimeInput + " " + endTimeInput;
    }
}
