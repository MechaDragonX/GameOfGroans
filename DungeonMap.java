import java.util.Scanner;

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
                if (i == player.getPositionX() && j == player.getPositionY()) {
                    if (player.getPlayerClass().equals("Warrior"))
                        System.out.print("W");
                    else
                        System.out.print("T");
                }
                else if (rooms[i][j].hasVisited())
                    System.out.print("*");
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
	public void movePlayer(MovementDirection direction) {
        switch (direction) {
            case Left:
                if (player.getPositionY() - 1 == -1)
                    throw new IndexOutOfBoundsException();
                else{
                    rooms[player.getPositionX()][player.getPositionY()].setVisited(true);
                    player.setPositionY(player.getPositionY() - 1);
                }
                break;
            case Right:
                if (player.getPositionY() + 1 == rooms.length)
                    throw new IndexOutOfBoundsException();
                else {
                    rooms[player.getPositionX()][player.getPositionY()].setVisited(true);
                    player.setPositionY(player.getPositionY() + 1);
                }
                break;
            case Up:
                if (player.getPositionX() - 1 == -1)
                    throw new IndexOutOfBoundsException();
                else {
                    rooms[player.getPositionX()][player.getPositionY()].setVisited(true);
                    player.setPositionX(player.getPositionX() - 1);
                }
                break;
            case Down:
                if (player.getPositionX() + 1 == rooms[0].length)
                    throw new IndexOutOfBoundsException();
                else {
                    rooms[player.getPositionX()][player.getPositionY()].setVisited(true);
                    player.setPositionX(player.getPositionX() + 1);
                }
                break;
        }
    }

    public void enterRoom(Scanner scanner, int x, int y) {
        rooms[x][y].enter(scanner, player);
    }
}
