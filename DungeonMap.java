/* Contains the Rooms in the dungeon 
and logic for Player movement */
public class DungeonMap {
	/* Rooms in the dungeon */
    private Room[][] rooms;

    /* Reference to the Player in the dungeon */
    private Player player;

    /* Initializes the rooms and shared Player reference */
    public DungeonMap(int rows, int columns, Player player){
        rooms = new Room[rows][columns];
        this.player = player;
        initialize();
    }

    private void initialize() {
        for(int i = 0; i < rooms.length; i++) {
            for(int j = 0; j < rooms[0].length; j++) {
                rooms[i][j] = new Room();
            }
        }
    }
    /* Displays the dungeon's rooms, walls,
    and player's current location */
    public void print() {
        printHorizontalWall();
        System.out.println();
        for (int i = 0; i < rooms.length; i++) {
            System.out.print("|");
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j].hasVisited())
                    System.out.print("*");
                else if (i == player.getPositionX() && j == player.getPositionY()) {
                    if (player.getPlayerClass() == "Warrior")
                        System.out.print("W");
                    else
                        System.out.print("T");
                }
                else
                    System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }
        printHorizontalWall();
        System.out.println();

        System.out.printf("GP = %d\nHP = %d", player.getGold(), player.getHealth());
    }
    private void printHorizontalWall() {
        System.out.print("+");
        for (int i = 0; i < rooms[0].length; i++)
            System.out.print("-");
        System.out.print("+");
    }
	
	//TODO: method(s) to move player
}
