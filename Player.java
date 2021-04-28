import java.util.Random;

public class Player {
	/* Current health of this Player */
    private int health;
    private int maxHealth;
    /* Current gold of this Player */
    private int gold;
    /* Damage this Player inflicts */
    // private int damage;
    /* Class of this Player */
    private String playerClass;
    private int positionX;
    private int positionY;
    /* Modifer to loot obtained by this Player */
    // private double lootModifier = 0;
    private Random rand = new Random();;

    public Player() {
        gold = 0;

        positionX = 0;
        positionY = 0;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }

    // public int getDamage() {
    //     return damage;
    // }
    // public void setDamage(int damage) {
    //     this.damage = damage;
    // }

    public String getPlayerClass() {
        return playerClass;
    }
    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
        if (playerClass.equals("Thief")) {
            health = 80;
            maxHealth = 80;
        } else {
            health = 100;
            maxHealth = 100;
        }
    }

    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /* Hits the targeted Monster */
    public int attack(Monster target) {
        int damage = 0;
        if (playerClass.equals("Thief")) { //need to know which monster has what amount of health so that the limits of the monsters health are known
            damage = rand.nextInt((40 - 15)) + 15;
            target.getHealth();
            health -= damage;
            target.setHealth(health);
            if (health <= 0) {
                System.out.println("the " + target.getMonsterType() + " has been slain");
            }
        }
        else if (playerClass.equals("Warrior")) {
            damage = rand.nextInt((60 - 20)) + 20;
            target.getHealth();
            health -= damage;
            target.setHealth(health);
            if (health <= 0) {
                System.out.println("the " + target.getMonsterType() + " has been slain");
            }
        }
        return damage;
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
    // public void onHeal(int health) {
    //     int maxHP = 0;
    //     if (playerClass.equals("Thief")) {
    //         maxHP = 80;
    //     }
    //     else if (playerClass.equals("Warrior")) {
    //         maxHP = 100;
    //     }
    //     maxHP -= health;
    //     health += rand.nextInt((maxHP - 5)) + 5; // max heal is the most hp the player can have minus the current amount of health and the min health is 5
    // }

	/* Add extra gold to this Player if Thief */
    public void loot(int gold) {
        // bonus gold if player chose the thief player class
        if (playerClass.equals("Thief")) {
            gold += 10;
        }
    }
}
