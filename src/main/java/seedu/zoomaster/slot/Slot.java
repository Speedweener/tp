package seedu.zoomaster.slot;

import seedu.zoomaster.bookmark.Bookmark;
import seedu.zoomaster.bookmark.BookmarkList;

import java.time.LocalTime;

//@@author fchensan
public class Slot {
    private LocalTime startTime;
    private LocalTime endTime;
    private String day;
    private String title;
    private BookmarkList bookmarks;

    public Slot(LocalTime startTimeInput, LocalTime endTimeInput, String dayInput, String titleInput) {
        startTime = startTimeInput;
        endTime = endTimeInput;
        day = dayInput;
        title = titleInput;
        bookmarks = new BookmarkList();
    }

    public boolean match(String lesson, String day, LocalTime startTime, LocalTime endTime) {
        if (title.compareToIgnoreCase(lesson) == 0
                && this.day.compareToIgnoreCase(day) == 0
                && this.startTime.equals(startTime)
                && this.endTime.equals(endTime)) {
            return true;
        }
        return false;
    }

    public BookmarkList getBookmarkList() {
        return bookmarks;
    }

    public void removeAllBookmarks() {
        bookmarks = new BookmarkList();
    }

    public void addBookmark(Bookmark bookmark) {
        bookmarks.addBookmark(bookmark);
    }

    public void setStartTime(LocalTime timeInput) {
        startTime = timeInput;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    //@@author jusufnathanael
    public int getStartMinutes() {
        int hours = Integer.parseInt(startTime.toString().substring(0,2));
        int minutes = Integer.parseInt(startTime.toString().substring(3));
        return hours * 60 + minutes;
    }

    public void setEndTime(LocalTime timeInput) {
        endTime = timeInput;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getEndMinutes() {
        int hours = Integer.parseInt(endTime.toString().substring(0,2));
        int minutes = Integer.parseInt(endTime.toString().substring(3));
        return hours * 60 + minutes;
    }

    public static LocalTime convertIntToLocalTime(int hours, int minutes) {
        String h = String.valueOf(hours);
        String m = String.valueOf(minutes);
        if (hours < 10) {
            h = "0" + h;
        }
        if (minutes < 10) {
            m = "0" + m;
        }
        return LocalTime.parse(h + ":" + m);
    }

    public void setDay(String dayInput) {
        day = dayInput;
    }

    public String getDay() {
        return day;
    }

    public void setTitle(String titleInput) {
        title = titleInput;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return startTime.toString() + "-" + endTime.toString() + " " + title;
    }
}
