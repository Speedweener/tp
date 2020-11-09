package seedu.zoomaster.command.timetable;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

//@@author xingrong123
public class DeleteSlotCommand extends Command {
    public static final String DEL_KW = "delete";
    private static final String DELETE_BOOKMARKS_MSG = "deleted bookmarks from ";
    private static final String BOOKMARKS_KW = "bookmarks";
    private static final String DELETE_MODULE_MSG = "deleted module: ";
    private static final String DELETE_SLOT_MSG = "deleted ";
    private static final int INPUT_LIMIT = 3;
    private static final int MODULE_INDEX = 0;
    private static final int SECOND_INPUT = 1;
    private static final int BOOKMARK_INDEX = 2;
    private static final String FROM = " from ";
    private String moduleCode;
    private Integer slotIndex = null;
    private boolean deleteBookmarks = false;

    /**
     * Constructs a new DeleteSlotCommand instance and stores the information of the Slot given by the input.
     *
     * @param command The user input command.
     * @throws ZoomasterException if details is blank, if there is no spacing after the command keyword or if
     *     the input after the module code is not the bookmarks keyword or a slot index.
     */
    public DeleteSlotCommand(String command) throws ZoomasterException {
        assert command.startsWith(DEL_KW);

        String details = command.substring(DEL_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, DEL_KW);
        }
        if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        String[] deleteCommands = details.trim().split("\\s+", INPUT_LIMIT);
        moduleCode = deleteCommands[MODULE_INDEX];

        try {
            String bookmarksOrSlotCommand = deleteCommands[SECOND_INPUT];
            if (bookmarksOrSlotCommand.trim().compareTo(BOOKMARKS_KW) == 0) {
                deleteBookmarks = true;
            } else {
                slotIndex = Integer.parseInt(bookmarksOrSlotCommand) - 1;
                if (deleteCommands[BOOKMARK_INDEX].trim().compareTo(BOOKMARKS_KW) == 0) {
                    deleteBookmarks = true;
                }
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            // No slot index or delete bookmark keyword provided.
            // The application will proceed with deleting the module.
        } catch (NumberFormatException e) {
            throw new ZoomasterException(ZoomasterExceptionType.NON_INTEGER_INPUT);
        }
    }

    /**
     * Deletes the module, slot or their bookmarks depending on user input.
     *
     * @param bookmarks The list of bookmarks.
     * @param timetable The timetable.
     * @param ui The user interface.
     * @throws ZoomasterException if the module does not exist or the slot number provided is invalid.
     */
    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        String message = "";
        if (!timetable.moduleExists(moduleCode)) {
            throw new ZoomasterException(ZoomasterExceptionType.INVALID_MODULE);
        } else {
            Module module = timetable.getModule(moduleCode);

            if (slotIndex == null && !deleteBookmarks) {
                // delete module
                timetable.deleteModule(module);
                message += DELETE_MODULE_MSG + moduleCode + Ui.NEW_LINE;
            } else if (slotIndex != null && !deleteBookmarks) {
                // delete slot
                Slot slot = module.getSlot(slotIndex);
                module.removeSlot(slot);
                message += DELETE_SLOT_MSG + slot + FROM + moduleCode + Ui.NEW_LINE;
            } else if (slotIndex == null) {
                // delete module bookmarks
                module.removeAllBookmarks();
                message += DELETE_BOOKMARKS_MSG + moduleCode + Ui.NEW_LINE;
            } else {
                // delete slot bookmarks
                Slot slot = module.getSlot(slotIndex);
                slot.removeAllBookmarks();
                message += DELETE_BOOKMARKS_MSG + slot.getDay() + " " + slot + FROM
                        + moduleCode + Ui.NEW_LINE;
            }

        }
        ui.print(message);
    }

}
