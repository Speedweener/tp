package seedu.zoomaster.slot;

import java.time.LocalDate;

//@@author xingrong123
public enum Day {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN;

    /**
     * Returns String of today's day of the week.
     *
     * @return outputDay String of today's day of the week readable by Slot class.
     */
    public static String getDayToday() {
        String outputDay;

        assert (LocalDate.now().getDayOfWeek().getValue() <= 7) && (LocalDate.now().getDayOfWeek().getValue() >= 1) :
                "LocalDate.now().getDayOfWeek().getValue() only returns value within range 1 to 7";
        switch (LocalDate.now().getDayOfWeek().getValue()) {
        case 1:
            outputDay = MON.toString();
            break;
        case 2:
            outputDay = TUE.toString();
            break;
        case 3:
            outputDay = WED.toString();
            break;
        case 4:
            outputDay = THU.toString();
            break;
        case 5:
            outputDay = FRI.toString();
            break;
        case 6:
            outputDay = SAT.toString();
            break;
        case 7:
            // Fallthrough
        default:
            outputDay = SUN.toString();
            break;
        }

        return outputDay;
    }

    public static String getDayFromCommand(String input) {
        String outputData;
        if (input.compareToIgnoreCase(MON.toString()) == 0) {
            outputData = MON.toString();
        } else if (input.compareToIgnoreCase(TUE.toString()) == 0) {
            outputData = TUE.toString();
        } else if (input.compareToIgnoreCase(WED.toString()) == 0) {
            outputData = WED.toString();
        } else if (input.compareToIgnoreCase(THU.toString()) == 0) {
            outputData = THU.toString();
        } else if (input.compareToIgnoreCase(FRI.toString()) == 0) {
            outputData = FRI.toString();
        } else if (input.compareToIgnoreCase(SAT.toString()) == 0) {
            outputData = SAT.toString();
        } else if (input.compareToIgnoreCase(SUN.toString()) == 0) {
            outputData = SUN.toString();
        } else {
            outputData = null;
        }
        return outputData;
    }

    public static boolean isDay(String input) {
        boolean isDay = false;
        if (getDayFromCommand(input) != null) {
            isDay = true;
        }
        return isDay;
    }

    public static int getDayValue(String day) {
        int value;
        if (day.compareToIgnoreCase(MON.toString()) == 0) {
            value = 1;
        } else if (day.compareToIgnoreCase(TUE.toString()) == 0) {
            value = 2;
        } else if (day.compareToIgnoreCase(WED.toString()) == 0) {
            value = 3;
        } else if (day.compareToIgnoreCase(THU.toString()) == 0) {
            value = 4;
        } else if (day.compareToIgnoreCase(FRI.toString()) == 0) {
            value = 5;
        } else if (day.compareToIgnoreCase(SAT.toString()) == 0) {
            value = 6;
        } else {
            value = 7;
        }
        return value;
    }

    @Override
    public String toString() {
        String day;
        switch (this) {
        case MON:
            day = "mon";
            break;
        case TUE:
            day = "tue";
            break;
        case WED:
            day = "wed";
            break;
        case THU:
            day = "thu";
            break;
        case FRI:
            day = "fri";
            break;
        case SAT:
            day = "sat";
            break;
        case SUN:
            // Fallthrough
        default:
            day = "sun";
            break;
        }
        return day;
    }
}
