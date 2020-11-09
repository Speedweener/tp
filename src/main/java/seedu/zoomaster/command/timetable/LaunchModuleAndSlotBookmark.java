package seedu.zoomaster.command.timetable;

import seedu.zoomaster.Ui;
import seedu.zoomaster.bookmark.BookmarkList;
import seedu.zoomaster.command.Command;
import seedu.zoomaster.exception.ZoomasterException;
import seedu.zoomaster.exception.ZoomasterExceptionType;
import seedu.zoomaster.slot.Module;
import seedu.zoomaster.slot.Slot;
import seedu.zoomaster.slot.Timetable;

import java.util.Arrays;
import java.util.List;

//@@author xingrong123
public class LaunchModuleAndSlotBookmark extends Command {
    public static final String LAUNCH_KW = "launch";
    private List<String>  moduleAndSlotIndex;

    public LaunchModuleAndSlotBookmark(String command) throws ZoomasterException {
        assert command.startsWith(LAUNCH_KW) : "command should start with launch keyword";
        String details = command.substring(LAUNCH_KW.length());
        if (details.isBlank()) {
            throw new ZoomasterException(ZoomasterExceptionType.EMPTY_COMMAND, LAUNCH_KW);
        } else if (!details.startsWith(" ")) {
            throw new ZoomasterException(ZoomasterExceptionType.UNKNOWN_INPUT);
        }
        moduleAndSlotIndex = Arrays.asList(details.trim().split("\\s+", 2));
    }

    @Override
    public void execute(BookmarkList bookmarks, Timetable timetable, Ui ui) throws ZoomasterException {
        String moduleCode = moduleAndSlotIndex.get(0);
        Integer slotIndex = null;
        if (moduleAndSlotIndex.size() == 2) {
            try {
                slotIndex = Integer.parseInt(moduleAndSlotIndex.get(1)) - 1;
            } catch (NumberFormatException e) {
                throw new ZoomasterException(ZoomasterExceptionType.NON_INTEGER_INPUT);
            }
        }
        Module module;
        String message;
        if (!timetable.moduleExists(moduleCode)) {
            message = "module does not exist" + Ui.NEW_LINE;
        } else {
            module = timetable.getModule(moduleCode);
            if (slotIndex == null) {
                message = launchModuleBookmark(module);
            } else {
                Slot slot = module.getSlot(slotIndex);
                message = launchSlotBookmark(slot);
            }
        }
        ui.print(message);
    }



    private String launchModuleBookmark(Module module) {
        String message = module.launchBookmarks();
        return message;
    }

    private String launchSlotBookmark(Slot slot) {
        BookmarkList bookmarkList = slot.getBookmarkList();
        String message = bookmarkList.launchAllBookmarks();
        return message;
    }
}
