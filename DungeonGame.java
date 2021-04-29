import java.util.Scanner;

public class DungeonGame {
    private static Scanner scanner = new Scanner(System.in);

    /* Reference to the DungeonMap the Player is in */
    private DungeonMap map;

    /* Reference to the Player in the dungeon */
    private Player player;

    /* Initializes the size of the dungeon */
	public DungeonGame(int rows, int columns) {
        player = new Player();
		map = new DungeonMap(rows, columns, player);
	}
	
	/* Main loop of the game, which handles
	non-combat related user input. Continues
	until the Player either wins or loses. */
    public void play() {
        // Pregame text
        System.out.println("=================================================\n");
        System.out.println("You are in a dungeon!!");
        System.out.println("There are monsters, bags of gold, and healing elixirs in each room.");
        System.out.println("Can you find 100 gold pieces and pay the evil professor to let you out, before the monsters kill you?\n");
        // Choose class and set up the player
        createPlayer();
        System.out.println("\n=================================================\n");

        // Is the player dead? Did they win?
        boolean gameEnd = false;
        // Is a battle still in progress? If so, battling will resume on next turn
        boolean inBattle = false;
        // While the game hasn't ended yet...
        while (!gameEnd) {
            // Print the map and status information
            map.print();

            // If the player is not in a battle...
            if (!inBattle)
                // Try moving
                inBattle = pollMovement();
            else
                // Otherwise, continue battle
                inBattle = battle();

            // Is the player dead? If so, end the game.
            if (player.getHealth() <= 0) {
                System.out.println("Game Over!!");
                gameEnd = true;
            // Did the player collect all gold necessary to win? If so, end the game.
            } else if (player.getGold() >= 100) {
                System.out.println("You've collected 100 gold piece!!\nYou win!!");
                gameEnd = true;
            }

            // Add pauses to allow the player to read all messags before moving on the next turn.
            System.out.print("\nType anything to continue ==> ");
            scanner.next();

            // Separator
            System.out.println("\n=================================================\n");
        }
    }

    private void createPlayer() {
        String input;
        boolean exited = false;
        
        System.out.print("Select your class:\n[1] Warrior\n[2] Thief\n==> ");
        while (!exited) {
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    exited = true;
                    player.setPlayerClass("Warrior");
                    System.out.println("You are now a warrior!");
                    break;
                case "2":
                    exited = true;
                    player.setPlayerClass("Thief");
                    System.out.println("You are now a thief!");
                    break;
                default:
                    System.out.print("Please type [1] or [2]!\n==> ");
                    break;
            }
        }
    }
    // Return if still in battle
    private boolean pollMovement() {
        boolean exited = false;
        boolean inBattle = false;
        String input;

        System.out.print("\n\nSelect a door: [W] up, [S] down, [A] left, [D] right ==> ");
        while (!exited) {
            input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "w":
                    exited = true;
                    inBattle = move(MovementDirection.Up);
                    break;
                case "s":
                    exited = true;
                    inBattle = move(MovementDirection.Down);
                    break;
                case "a":
                    exited = true;
                    inBattle = move(MovementDirection.Left);
                    break;
                case "d":
                    exited = true;
                    inBattle = move(MovementDirection.Right);
                    break;
            }
            if (!exited)
                System.out.print("Please type [W], [S], [A], or [D]!\n==> ");
        }

        return inBattle;
    }
    // Returns if in battle or not
    private boolean move(MovementDirection direction) {
        try {
            map.movePlayer(direction);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nYou can’t move through a wall!");
        }

        return map.enterRoom(scanner, player.getPositionX(), player.getPositionY());
    }
    // Returns if in battle or not
    private boolean battle() {
        System.out.print("\n\nYou are in battle!\nType anything to continue ==> ");
        scanner.next();
        System.out.println("\n");
        return map.enterRoom(scanner, player.getPositionX(), player.getPositionY());
    }
}
