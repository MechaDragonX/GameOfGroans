import java.util.Random;
import java.util.Scanner;

/* Represents a Room in the Dungeon, where
encounters with Monsters and Loot occur */
public class Room {
    // A seed is passed to the constructor to avoid different instances of random returning the same values
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
        An enum with three possible values:
        0: None
        1: Gold
        2: Elixir
    */
    private ItemType itemType;
    // Amount of gold
    private int gold;
    // The monster present in the room (if there is any)
    private Monster monster;

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Room() {
        // Roll a random number between 0 and 3 (exclusive) for the room type
        int randomValue = rng.nextInt(3);
        switch (randomValue) {
            case 0:
                type = RoomType.Standard;
                itemType = ItemType.None;
                monster = null;
                break;
            case 1:
                type = RoomType.Item;
                monster = null;
                break;
            case 2:
                type = RoomType.Monster;
                gold = 0;
                break;
        }

        if (type == RoomType.Item) {
            // Reuse the same variable to roll a number between 1 and 3 (exclusive) for the item type
            randomValue = rng.nextInt(2) + 1;
            switch (randomValue) {
                case 1:
                    itemType = ItemType.Gold;
                    // Roll a number between 1 and 51 (exclusive) for the amount of gold
                    gold = rng.nextInt(51) + 1;
                    break;
                case 2:
                    itemType = ItemType.Elixr;
                    gold = 0;
                    break;
            }
        } else if (type == RoomType.Monster) {
            // Reuse the same variable to roll a number between 0 and 4 (exclusive) for the monster type
            randomValue = rng.nextInt(4);
            switch (randomValue) {
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
        }
    }

    /* Accessor for the visited field */
    public boolean hasVisited() {
        return this.visited;
    }

	/* Handles encounter logic when a Player
	enters this Room. Includes combat resolution
	and obtaining loot. */
    // Return if still in battle or not
    public boolean enter(Scanner scanner, Player player) {
        // Only print if the player is stil in battle
        if (monster == null || (monster.getHealth() == monster.getMaxHealth()))
            System.out.println("You open a door and move through ...");

        // Only print if the room has been previously visited
        if (visited) {
            System.out.println("You have already visited this room...");
            return false;
        }

        switch (type) {
            case Item:
                pickUpItem(player);
                break;
            case Monster:
                 if(monster.getHealth() == monster.getMaxHealth())
                     return initiateEncounter(scanner, player);
                 else
                    return battle(player);
        }

        return false;
    }
    private void pickUpItem(Player player) {
        if (itemType == ItemType.Gold) {
            player.setGold(player.getGold() + gold);
            System.out.printf("You find a bag of %d gold pieces!!\n", gold);
        } else {
            if(player.getHealth() == player.getMaxHealth())
                System.out.println("You find a healing elixir, but you have max HP...");
            else {
                player.setHealth(player.getHealth() + 20);
                System.out.println("You find a healing elixir and are healed by 20 HP!!");
            }
        }
    }
    // Return if still in battle or not
    private boolean initiateEncounter(Scanner scanner, Player player) {
        System.out.printf("A %s appears!!\n\n", monster.getMonsterType());
        
        int input = 0;
        boolean exited = false;
        while (!exited) {
            System.out.print("Select an action: [1] Attack, [2] Run ==> ");
            input = scanner.nextInt();
            if(input == 1 || input == 2)
                exited = true;
            else
                System.out.println("Please type [1] or [2]!");
        }
        
        if (input == 1)
            return battle(player);

        System.out.println("You try to run ...");
        // Able to run safely?
        int randomValue = rng.nextInt(2);
        if(randomValue == 0)
            System.out.printf("The %s attacks and hits you for %d damage as you escape!\n", monster.getMonsterType(), monster.attack(player));
        else
            System.out.println("You successfully ran away!");
        return false;
    }
    // Return if still in battle or not
    private boolean battle(Player player) {
        // Ambush or not?
        int randomValue = rng.nextInt(2);
        if (randomValue == 0) {
            System.out.printf("The %s attacks and hits you for %d damage!\n", monster.getMonsterType(), monster.attack(player));
            // Cam you counterattack?
            if (player.getHealth() >= 0)
                System.out.printf("You attack and hit the %s for %d damage.\n", monster.getMonsterType(), player.attack(monster));
        } else {
            System.out.printf("You attack and hit the %s for %d damage.\n", monster.getMonsterType(), player.attack(monster));
            // Can it counterattack?
            if(monster.getHealth() >= 0)
                System.out.printf("The %s attacks and hits you for %d damage!\n", monster.getMonsterType(), monster.attack(player));
        }

        // Did someone die? Otherwise, the battle continues.
        if (player.getHealth() <= 0) {
            System.out.println("You died!");
            return false;
        } else if (monster.getHealth() <= 0) {
            System.out.printf("The %s dies!\n", monster.getMonsterType());
            return false;
        }
        else
            return true;
    }
}
