package seedu.zoomaster.slot;

import java.time.LocalTime;

public class SlotStub extends Slot {

    public SlotStub() {
        super(LocalTime.parse("11:00"), LocalTime.parse("12:00"), "wed", "test");
    }

    @Override
    public String getDay() {
        return "wed";
    }

    @Override
    public LocalTime getStartTime() {
        return LocalTime.parse("11:00");
    }

    @Override
    public LocalTime getEndTime() {
        return LocalTime.parse("12:00");
    }

}
