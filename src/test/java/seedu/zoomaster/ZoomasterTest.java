package seedu.zoomaster;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author
class ZoomasterTest {
    Zoomaster test;

    @Test
    public void defaultTest() {
        test = new Zoomaster("./data/bookmarks.txt",
                "./data/timetable.txt",
                "./data/settings.txt");

        assertTrue(true);
    }
}

