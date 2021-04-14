
public class Player {
	/* Current health of this Player */
    private int health;

    /* Current gold of this Player */
    private int gold;

    /* Damage this Player inflicts */
    private int damage;

    /* Class of this Player */
    private String playerClass;

    /* Modifer to loot obtained by this Player */
    private double lootModifier;

    /* Hits the targeted Monster */
    public void attack(Monster target) {

    }

	/* Removes health from this Player
    when hit by a Monster */
    public void onHit(int damage) {

    }

	/* Adds health to this Player when healed */
    public void onHeal(int health) {

    }

	/* Adds gold to this Player when obtained */
    public void onLoot(int gold) {

    }
}
