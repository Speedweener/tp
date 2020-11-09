package seedu.zoomaster.slot;

import java.util.ArrayList;

/**
 * Stores an ArrayList of Slots and Strings.
 * Used to help produce a sorted timetable of Slots and its associated module code.
 */
//@@author TYS0n1
public class SlotContainer {
    private ArrayList<Slot> thisDaySlots;
    private ArrayList<String> thisDayModuleCodesList;

    /**
     * Constructor of SlotContainer object.
     * Initializes the ArrayLists to be stored in SlotContainer object.
     *
     * @param slotsListInput The ArrayList of Slots to be stored in SlotContainer object.
     * @param moduleCodesListInput The ArrayList of Strings to be stored in SlotContainer object.
     */
    public SlotContainer(ArrayList<Slot> slotsListInput, ArrayList<String> moduleCodesListInput) {
        this.thisDaySlots = slotsListInput;
        this.thisDayModuleCodesList = moduleCodesListInput;
    }

    /**
     * Returns the ArrayList of Slots stored in SlotContainer object.
     *
     * @return thisDaySlots.
     */
    public ArrayList<Slot> getSlotList() {
        return thisDaySlots;
    }

    /**
     * Replaces the existing ArrayList of Slots stored in SlotContainer object with a new
     * ArrayList of Slots.
     *
     * @param slotsListInput The ArrayList of Slots replacing the existing.
     */
    public void setToSlotList(ArrayList<Slot> slotsListInput) {
        thisDaySlots = slotsListInput;
    }

    /**
     * Returns the ArrayList of Strings stored in SlotContainer object.
     *
     * @return thisDayModuleCodesList.
     */
    public ArrayList<String> getModuleCodesList() {
        return thisDayModuleCodesList;
    }

    /**
     * Replaces the existing ArrayList of String stored in SlotContainer object with a new
     * ArrayList of String.
     *
     * @param moduleCodeListInput The ArrayList of Strings replacing the existing.
     */
    public void setModuleCodesList(ArrayList<String> moduleCodeListInput) {
        thisDayModuleCodesList = moduleCodeListInput;
    }

    /**
     * Sorts the timetable, ArrayList of Slots and The ArrayList of Strings of ModuleCodes
     * based on day of the week and 24 hour timing.
     * It sorts the timetable day from monday and ends with sunday.
     * It sorts the timetable timing from 00:00 to 23:59.
     *
     * @param slotContainer The container containing the timetable to be sorted.
     * @return new SlotContainer(sortedThisDaySlots, sortedThisDayModuleCodesList)
     */
    public static SlotContainer sortSlotsByTime(SlotContainer slotContainer) {
        ArrayList<Slot> thisDaySlots = slotContainer.getSlotList();
        ArrayList<String> thisDayModuleCodesList = slotContainer.getModuleCodesList();
        ArrayList<Slot> sortedThisDaySlots = new ArrayList<>();
        ArrayList<String> sortedThisDayModuleCodesList = new ArrayList<>();
        int indexEarliestTimeSlot;
        Slot earliestTimeSlot;

        while (thisDaySlots.size() != 0) {
            earliestTimeSlot = thisDaySlots.get(0);
            indexEarliestTimeSlot = 0;
            for (int i = 1; i < thisDaySlots.size(); i++) {
                if (earliestTimeSlot.getStartTime().isAfter(thisDaySlots.get(i).getStartTime())) {
                    earliestTimeSlot = thisDaySlots.get(i);
                    indexEarliestTimeSlot = i;
                }
            }
            sortedThisDaySlots.add(thisDaySlots.get(indexEarliestTimeSlot));
            sortedThisDayModuleCodesList.add(thisDayModuleCodesList.get(indexEarliestTimeSlot));
            thisDaySlots.remove(indexEarliestTimeSlot);
            thisDayModuleCodesList.remove(indexEarliestTimeSlot);
        }


        return new SlotContainer(sortedThisDaySlots, sortedThisDayModuleCodesList);
    }
}
