package seedu.zoomaster.command.planner;

import seedu.zoomaster.Storage;
import seedu.zoomaster.Ui;
import seedu.zoomaster.Zoomaster;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Day;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

//@@author jusufnathanael
public class LoadPlannerCommand extends Command {
    public static final String LOAD_KW = "load";
    private Storage<Timetable> storage;

    public LoadPlannerCommand(String command) throws ZoomasterException {
        if (command.compareTo(LOAD_KW) != 0) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
    }

    /**
     * Loads the slots to the planner.
     *
     * @param bookmarks The list of bookmarks
     * @param planner The timetable
     * @param ui The user interface
     * @throws ZoomasterException Some exception
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable planner, Ui ui) throws ZoomasterException {
        planner.clearAllModule();
        storage = new Storage<>(Zoomaster.getJarFilepath() + "./data/planner/", Timetable.class);
        try {
            Timetable temp = storage.loadPlanner();
            planner.addModule(initialiseEmptySlots(temp));
            ui.print("Planner loaded." + Ui.NEW_LINE);
        } catch (ZoomasterException e) {
            ui.print("Please insert a non-empty timetable to the planner folder." + Ui.NEW_LINE);
        }
    }


    /**
     * Initialises the empty slots for the empty timetable.
     *
     * @param timetable The timetable that is initialised with the empty slots
     * @return The module "EMPTY" that contains all the empty slots
     */
    public Module initialiseEmptySlots(Timetable timetable) {
        ArrayList<ArrayList<Integer>> array = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            array.add(new ArrayList<>(Collections.nCopies(300, 0)));
        }
        ArrayList<Slot> slots = new ArrayList<>(timetable.getFullSlotList());
        for (Slot s: slots) {
            markAsFull(array, s.getStartMinutes(), s.getEndMinutes(), s.getDay());
        }
        Module module = new Module("EMPTY");
        generateEmptySlots(array, module);
        return module;
    }

    /**
     * Marks a 5-minutes slot as full.
     *
     * @param array An array that represents 5-minutes slots.
     * @param start The start time of the slot.
     * @param end The end time of the slot.
     * @param day The day of the slot.
     */
    private static void markAsFull(ArrayList<ArrayList<Integer>> array, int start, int end, String day) {
        int count = 0;
        for (Day d: Day.values()) {
            if (d.toString().compareTo(day) == 0) {
                for (int i = start / 5; i < Math.ceil((double) end / 5); i++) {
                    array.get(count).set(i, 1);
                }
            }
            count++;
        }
    }

    /**
     * Generates the empty slots and add it to the "EMPTY" module.
     *
     * @param array An array that represents 5-minutes slots.
     * @param module The "EMPTY" module.
     */
    private static void generateEmptySlots(ArrayList<ArrayList<Integer>> array, Module module) {
        int count = 0;
        for (Day d: Day.values()) {
            for (int i = 0; i < 287; i++) {
                if (array.get(count).get(i) == 0) {
                    int startHours = (i * 5) / 60;
                    int startMinutes = (i * 5) % 60;
                    LocalTime start = Slot.convertIntToLocalTime(startHours % 288, startMinutes % 288);
                    do {
                        i++;
                    } while (array.get(count).get(i) == 0 && i < 287);
                    int endHours = (i * 5) / 60;
                    int endMinutes = (i * 5) % 60;
                    LocalTime end = Slot.convertIntToLocalTime(endHours % 288, endMinutes % 288);
                    module.addSlot(new Slot(start, end, d.toString(), "<empty slot>"));
                }
            }
            count++;
        }
    }
}
