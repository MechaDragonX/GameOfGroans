import java.lang.annotation.Target;
import java.util.Random;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

/* Represents a Room in the Dungeon, where
encounters with Monsters and Loot occur */
public class Room {
    // A seed is passed to the contructor to avoid different instances of random returning the same values
    private static Random rng = new Random(48651);

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
    private ItemType itemType;
    // Amount of gold
    private int gold;
    // The monster present in the room (if there is any)
    private Monster monster;

    public Room() {
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
                    itemType = ItemType.None;
                    break;
                case 1:
                    itemType = ItemType.Gold;
                    break;
                case 2:
                    itemType = ItemType.Elixr;
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

    /* Accessor for the visited field */
    public boolean hasVisited() {
        return this.visited;
    }

	/* Handles encounter logic when a Player
	enters this Room. Includes combat resolution
	and obtaining loot. */
    public void enter(Scanner scanner, Player player) {
        System.out.println("You open a door and move through ...");
        if(visited) {
            System.out.println("You have already visited this room...");
            return;
        }

        switch(type) {
            case Item:
                pickUpItem(player);
                break;
            case Monster:
                if(monster.getHealth() == monster.getMaxHealth())
                    initiateEncouter(scanner, player);
                else
                    battle(player);
                break;
        }
    }
    private void pickUpItem(Player player) {
        if(itemType == ItemType.Gold) {
            player.setGold(player.getGold() + gold);
            System.out.printf("You find a bag of %d gold pieces!!\n", gold);
        } else {
            player.setHelth(player.getHealth() + 20);
            System.out.println("You find a healing elixir and are healed by 20 HP!!");
        }
    }
    private void initiateEncouter(Scanner scanner, Player player) {
        System.out.printf("A %s appears!!\n\n", monster.getMonsterType());
        
        int input = 0;
        boolean exited = false;
        while(!exited) {
            System.out.print("Select an action: [1] Attack\n[2] Run\n==> ");
            input = scanner.nextInt();
            if(input == 1 || input == 2)
                exited = true;
            else
                System.out.println("Please type [1] or [2]!");
        }
        
        if(input == 1) {
            battle(player);
        } else {
            // Able to run?
            int randomValue = rng.nextInt(2);
            if(randomValue == 0) {
                System.out.println("You weren't able to run away!");
                monster.attack(player);
                // Add attack message
                player.attack(monster);
                // Add attack message
            } else {
                System.out.println("You successfully ran away!");
            }
        }
    }
    private void battle(Player player) {
        // Ambush or not?
        int randomValue = rng.nextInt(2);
        if(randomValue == 0) {
            monster.attack(player);
            // Add attack message
            player.attack(monster);
            // Add attack message
        } else {
            player.attack(monster);
            // Add attack message
            monster.attack(player);
            // Add attack message
        }
    }
}
