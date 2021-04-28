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
        System.out.println("=================================================\n");
        System.out.println("You are in a dungeon!!");
        System.out.println("There are monsters, bags of gold, and healing elixirs in each room.");
        System.out.println("Can you find 100 gold pieces and pay the evil professor to let you out, before the monsters kill you?\n");
        createPlayer();
        System.out.println("\n=================================================\n");
    }
    private void createPlayer() {
        String input;
        boolean exited = false;
        
        System.out.print("Select your class:\n[1] Warrior\n[2] Thief\n==> ");
        
        while (!exited) {
            input = scanner.next();
            switch (input) {
                case "1":
                    exited = true;
                    player.setPlayerClass("Warrior");
                    System.out.println("You are now a warrior!");
                    break;
                case "2":
                    exited = true;
                    player.setPlayerClass("Theif");
                    System.out.println("You are now a theif!");
                    break;
                default:
                    System.out.print("Please type [1] or [2]!\n==> ");
                    break;
            }
        }
    }
}
