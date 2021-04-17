import java.util.Random;
public class Player {
	/* Current health of this Player */
    private int health = 0;

    /* Current gold of this Player */
    private int gold = 0;

    /* Damage this Player inflicts */
    private int damage = 0;

    /* Class of this Player */
    private String playerClass;

    /* Modifer to loot obtained by this Player */
    private double lootModifier = 0;

    private Random rand = new Random();;

    private void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }

    /* Hits the targeted Monster */
    public void attack(Monster target) {
        if (playerClass.equals("Thief")) { //need to know which monster has what amount of health so that the limits of the monsters health are known

        }
        else if (playerClass.equals("Warrior")) {

        }
    }

	/* Removes health from this Player
    when hit by a Monster */
    public void onHit(int damage) {
        damage = rand.nextInt((60 - 10)) + 10; // max damage is 60 and min damage is 10
        health -= damage;
        if (health <= 0) {
            System.out.println("You have died"); // need a way to end the game if the player dies
        }
    }

	/* Adds health to this Player when healed */
    public void onHeal(int health) {
        int maxHP = 0;
        if (playerClass.equals("Thief")) {
            maxHP = 80;
        }
        else if (playerClass.equals("Warrior")) {
            maxHP = 100;
        }
        maxHP -= health;
        health += rand.nextInt((maxHP - 5)) + 5; // max heal is the most hp the player can have minus the current amount of health and the min health is 5
    }

	/* Adds gold to this Player when obtained */
    public void onLoot(int gold) {
        gold += rand.nextInt((35 - 10)) + 10;
        // bonus gold if player chose the thief player class
        if (playerClass.equals("Thief")) {
            gold += 10;
        }
        if (gold > 100) {
            //need a portion that ends the game
            System.out.println("Congratulations! You have won!");
        }
    }
}
