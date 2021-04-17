mport java.util.Random;

public class Monster {
	/* Current health of this Monster */
    private int health;
    // private final int DENEKE_HEALTH = 55;
    // private final int GOBLIN_HEALTH = 6;
    // private final int ZOMBIE_HEALTH = 12;
    // private final int ORC_HEALTH = 18;
//idk if any of this is helpful but i hope it is! :) 
    public Monster(int health) {
        this.health = health;

        if (this.health < 0) {
            this.health = 0;
        }
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health) {
        if(this.health > 0){
        
        this.health = health;
        }
    }

    /* Max damage this Monster inflicts */
    private int damage;

    /* Type of this Monster */
    private String monsterType;

    /* Hits the targeted Player */
    public void attack(Player target) {

    }

    /* Removes health from this Monster
    when hit by a Player */
    public void onHit(int damage) {

    }
}
