import java.util.Random;
import java.util.Scanner;

/* Represents a Room in the Dungeon, where
encounters with Monsters and Loot occur */
public class Room {
    /* Indicates whether or not this Room
    instance has been visited already */
    private boolean visited;
    /*
        An enum with three possible values:
        0: Standard
        1: Item
        2: Monster
    */
    private RoomType type;
    /*
        An emum with three possible values:
        0: None
        1: Gold
        2: Elixr
    */
    private ItemType item;
    // Amount of gold
    private int gold;
    // The monster present in the room (if there is any)
    private Monster monster;

    public Room() {
        // A seed is passed to the contructor to avoid different instances of random returning the same values
        Random rng = new Random(48651);
        // Roll a random number between 0 and 3 (exclusive) for the room type
        int randomValue = rng.nextInt(3);
        switch(randomValue) {
            case 0:
                type = RoomType.Standard;
                break;
            case 1:
                type = RoomType.Item;
                break;
            case 2:
                type = RoomType.Monster;
                break;
        }

        if(type == RoomType.Item) {
            // Reuse the same variable to roll a number between 0 and 3 (exclusive) for the item type
            randomValue = rng.nextInt(3);
            switch(randomValue) {
                case 0:
                    item = ItemType.None;
                    break;
                case 1:
                    item = ItemType.Gold;
                    break;
                case 2:
                    item = ItemType.Elixr;
                    break;
            }
            // Roll a number between 1 and 51 (exclusive) for the amount of gold
            gold = rng.nextInt(51) + 1;
            monster = null;
        } else if(type == RoomType.Monster) {
            // Reuse the same variable to roll a number between 0 and 4 (exclusive) for the monster type
            randomValue = rng.nextInt(4);
            switch(randomValue) {
                case 0:
                    monster = new Monster("Goblin");
                    break;
                case 1:
                    monster = new Monster("Zombie");
                    break;
                case 2:
                    monster = new Monster("Orc");
                    break;
                case 3:
                    monster = new Monster("Deneke");
                    break;
            }
            gold = 0;
        }
    }

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
