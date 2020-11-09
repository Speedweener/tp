package seedu.zoomaster;

import com.google.gson.JsonParseException;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.ChangeModeCommand;
import seedu.zoomaster.settings.SettingsVariable;
import seedu.zoomaster.settings.UserSettings;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.slot.Timetable;

import java.io.File;
import java.io.IOException;

//@@author
public class Zoomaster {

    private Storage<BookmarkList> bookmarkStorage;
    private Storage<Timetable> timetableStorage;
    private Storage<Timetable> plannerStorage;
    private Storage<UserSettings> userSettingsStorage;
    private BookmarkList bookmarks;
    private Timetable timetable;
    private Timetable planner;
    private Ui ui;

    public static UserSettings userSettings;

    /**
     * Constructs a new Zoomaster instance.
     * Pass the filepath of the txt file to set up storage.
     *
     * @param bookmarkFilePath The filepath of the bookmark txt file.
     * @param timetableFilePath The filepath of the slot txt file.
     */
    public Zoomaster(String bookmarkFilePath, String timetableFilePath, String storageFilePath) {
        ui = new Ui();

        bookmarkStorage = new Storage<>(getJarFilepath() + bookmarkFilePath, BookmarkList.class);
        timetableStorage = new Storage<>(getJarFilepath() + timetableFilePath, Timetable.class);
        userSettingsStorage = new Storage<>(getJarFilepath() + storageFilePath, UserSettings.class);
      
        try {
            bookmarks = bookmarkStorage.load();
            timetable = timetableStorage.load();
            planner = new Timetable();
            userSettings = userSettingsStorage.load();
            Module.setModuleList(timetableStorage.loadModuleList());
        } catch (ZoomasterException e) {
            ui.showErrorMessage(e);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JsonParseException | ClassCastException e) {
            ui.showParsingErrorAndExit();
        }

        applySettings();
    }

    /**
     * This method is used run the Duke program.
     */
    public void run() {
        boolean isExit = false;

        ui.showWelcomeScreen();

        do {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                if (Parser.getProgramMode() == 3) {
                    c.execute(bookmarks, planner, ui);
                } else {
                    c.execute(bookmarks, timetable, ui);
                }
                isExit = c.isExit();

                performSave(isExit);
            } catch (ZoomasterException e) {
                ui.showErrorMessage(e);
            }
        } while (!isExit);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Zoomaster("./data/bookmarks.txt",
                "./data/timetable.txt",
                "./data/settings.txt").run();
    }

    //@@author Speedweener
    /**
     * Returns path of jar file during execution to allow
     * app to create txt file in the same location.
     */
    public static String getJarFilepath() {
        return new File(Zoomaster.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath()).getParent().replace("%20", " ");
    }

    //@@author fchensan
    private void applySettings() {
        try {
            String defaultMode;
            defaultMode = (String) userSettings.getSettingsVariable(UserSettings.DEFAULT_MODE_FIELD).getChosenOption();
            if (defaultMode.equals(UserSettings.MODE_MAINMENU)) {
                return;
            }
            new ChangeModeCommand(ChangeModeCommand.MODE_KW + " " + defaultMode).execute(bookmarks, timetable, ui);
        } catch (ZoomasterException e) {
            e.printStackTrace();
        }
    }

    private void performSave(boolean isExit) throws ZoomasterException {
        String autosaveSetting = (String) userSettings.getSettingsVariable(UserSettings.AUTO_SAVE_FIELD)
                .getChosenOption();

        if (autosaveSetting == SettingsVariable.ON || isExit) {
            bookmarkStorage.save(bookmarks);
            timetableStorage.save(timetable);
            userSettingsStorage.save(userSettings);
        }
    }
}