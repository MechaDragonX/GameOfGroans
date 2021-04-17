import java.util.Random;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Or;

public class Monster {

    private final int DENEKE_HEALTH = 55;
    private final int GOBLIN_HEALTH = 6;
    private final int ZOMBIE_HEALTH = 12;
    private final int ORC_HEALTH = 18;

	/* Current health of this Monster */
    private int health;

    /* Max damage this Monster inflicts */
    private int damage;

    /* Type of this Monster */
    private String monsterType;
    

    public Monster(String monsterType) {
        this.monsterType = monsterType;
        switch (this.monsterType) {
            case "Deneke": 
                health = DENEKE_HEALTH;
                break;
            case "Goblin":
                health = GOBLIN_HEALTH;
                break;
            case "Zombie":
                health = ZOMBIE_HEALTH;
                break;
            case "Orc":
                health = ORC_HEALTH;
                break;
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

    /* Hits the targeted Player */
    public void attack(Player target) {

    }

    /* Removes health from this Monster
    when hit by a Player */
    public void onHit(int damage) {

    }
}
