import java.util.Random;
import java.util.Scanner;

/* Represents a Room in the Dungeon, where
encounters with Monsters and Loot occur */
public class Room {
    /* Indicates whether or not this Room
    instance has been visited already */
    private boolean visited;

	/* Handles encounter logic when a Player
	enters this Room. Includes combat resolution
	and obtaining loot. */
    public void enter(Player player) {

    }

    /* Accessor for the visited field */
    public boolean hasVisited() {
        return this.visited;
    }
}
