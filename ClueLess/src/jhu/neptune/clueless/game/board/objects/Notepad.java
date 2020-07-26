package jhu.neptune.clueless.game.board.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Notepad {

    private List<Checkmark> list;
    private HashMap<Rooms, List<Checkmark>> rooms;
    private HashMap<Weapons, List<Checkmark>> weapons;
    private HashMap<Characters, List<Checkmark>> suspects;

    // Number of rows starting from 0 to numberOfRows -1 -> [0, numberOfRows)
    private final int numberOfRows;

    /**
     * Construct a notepad object to be used client side.
     */
    public Notepad(int numberOfRows) {
        this.numberOfRows = numberOfRows;
        list = new ArrayList<>();

        // Initialize a list of unticked checkmarks
        for (int i = 0; i < numberOfRows; i++) {
            list.add(Checkmark.UNTICKED);
        }

        rooms    = new HashMap<>();
        weapons  = new HashMap<>();
        suspects = new HashMap<>();

        // Populate rooms map with Room values
        for (Rooms r : Rooms.values()) {
            rooms.put(r,list);
        }

        // Populate weapons map with Weapon values
        for (Weapons w : Weapons.values()) {
            weapons.put(w, list);
        }

        // Populate suspects map with Character values
        for (Characters c : Characters.values()) {
            suspects.put(c, list);
        }
    }

    public void writeCheckmark(Rooms r, Checkmark check, int column) {
        //Panic if given an invalid column entry outside range of [0, numberOfRows - 1]
        assert (column >= 0 || column < numberOfRows);
        rooms.computeIfPresent(r, (key, list) -> { list.set(column, check); return list; });
    }

    public void writeCheckmark(Weapons w, Checkmark check, int column) {
        //Panic if given an invalid column entry outside range of [0, numberOfRows - 1]
        assert (column >= 0 || column < numberOfRows);
        weapons.computeIfPresent(w, (key, list) -> { list.set(column, check); return list; });
    }

    public void writeCheckmark(Characters c, Checkmark check, int column ) {
        //Panic if given an invalid column entry outside range of [0, numberOfRows - 1]
        assert (column >= 0 || column < numberOfRows);
        suspects.computeIfPresent(c, (key, list) -> { list.set(column, check); return list; });
    }

    /**
     * Cleans Notepad and recreates it to the default
     */
    public void reconstructNotepad() {

        purgeNotePad();

        list = new ArrayList<>();

        //Make row of 7 columns, with unticked checkmarks.
        for (int i = 0; i < numberOfRows; i++) {
            list.add(Checkmark.UNTICKED);
        }

        rooms    = new HashMap<>();
        weapons  = new HashMap<>();
        suspects = new HashMap<>();

        // Populate rooms map with Room values
        for (Rooms r : Rooms.values()) {
            rooms.put(r,list);
        }

        // Populate weapons map with Weapon values
        for (Weapons w : Weapons.values()) {
            weapons.put(w, list);
        }

        // Populate suspects map with Character values
        for (Characters c : Characters.values()) {
            suspects.put(c, list);
        }
    }

    public void purgeNotePad() {
        // Clear all arrays
        rooms.clear();
        weapons.clear();
        suspects.clear();
        list.clear();
    }
}
