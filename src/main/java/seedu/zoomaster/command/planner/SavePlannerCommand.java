package seedu.zoomaster.command.planner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import seedu.zoomaster.Storage;
import seedu.zoomaster.Ui;
import seedu.zoomaster.Zoomaster;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@@author jusufnathanael
public class SavePlannerCommand extends Command {

    public static final String SAVE_KW = "save";

    public SavePlannerCommand(String command) throws ZoomasterException {
        if (command.compareTo(SAVE_KW) != 0) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
    }

    /**
     * Adds the slot to the slot list and saves the slots list in the text file.
     *
     * @param bookmarks The list of bookmarks
     * @param planner The timetable
     * @param ui The user interface
     * @throws ZoomasterException Some exceptions
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable planner, Ui ui) throws ZoomasterException {
        File folder = new File(Zoomaster.getJarFilepath() + "./data/planner/");
        ArrayList<File> listOfFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));

        for (File f: listOfFiles) {
            Storage<Timetable> storage = new Storage<>(f.getPath(), Timetable.class);
            Timetable timetableF = storage.load();
            writeToFile(planner, timetableF);
            storage.writePlanner(timetableF, f);
        }
        ui.print("Planner saved." + Ui.NEW_LINE);
    }

    /**
     * Writes the newly added meeting to each individual timetables.
     *
     * @param planner The planner timetable
     * @param timetable The individual timetable
     * @throws ZoomasterException Some exceptions
     */
    void writeToFile(Timetable planner, Timetable timetable) throws ZoomasterException {
        for (Module m : planner.getFullModuleList()) {
            if (!m.getModuleCode().equals("EMPTY")) {
                if (timetable.moduleExists(m.getModuleCode())) {
                    Module module = timetable.getModule(m.getModuleCode());
                    for (int i = 0; i < m.getSlotList().size(); i++) {
                        module.addSlot(m.getSlot(i));
                    }
                } else {
                    timetable.addModule(m);
                }
            }
        }
    }

}
