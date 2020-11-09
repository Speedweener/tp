package seedu.zoomaster.command;

//@@author
public enum Mode {
    BOOKMARK,
    TIMETABLE,
    PLANNER,
    SETTINGS;

    @Override
    public String toString() {
        String modeString = null;
        switch (this) {
        case BOOKMARK:
            modeString = "bookmark";
            break;
        case TIMETABLE:
            modeString = "timetable";
            break;
        case PLANNER:
            modeString = "planner";
            break;
        case SETTINGS:
            modeString = "settings";
            break;
        default:
            break;
        }
        return modeString;
    }
}
