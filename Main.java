public class Main {
	/* Entry point of the application */
    public static void main(String[] args) {
        if (args.length > 2) {
            System.out.println("You can either provide no arguments, or provide exactly 2 arguments!");
            System.out.println("USAGE: java <application name> OR java <application name> <number of rows> <number of columns>");
            System.out.println("If no arguments are passed, then a defualt size dungeon will be created, which is 10x10.");
            return;
        }

        DungeonGame game;
        int rows = 10;
        int columns = 10;

        if (args.length != 0) {
            if ((tryParseInt(args[0]) == null || tryParseInt(args[1]) == null)) {
                System.out.println("You must type a number!");
                return;
            } if (!(tryParseInt(args[0]) == null && tryParseInt(args[1]) == null)) {
                if (tryParseInt(args[0]) >= 1 || tryParseInt(args[1]) >= 1) {
                    rows = Integer.parseInt(args[0]);
                    columns = Integer.parseInt(args[1]);
                } else {
                    System.out.println("A dungeon can't have no rooms! You must type a number greater than or equal to zero!");
                    return;
                }
            }
        }

        game = new DungeonGame(rows, columns);
        game.play();
    }

    private static Integer tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
