import java.util.Random;

public class Monster {

    private final int DENEKE_HEALTH = 55;
    private final int DENEKE_DAMAGE = 5; 
    private final int GOBLIN_HEALTH = 6;
    private final int GOBLIN_DAMAGE = 10;
    private final int ZOMBIE_HEALTH = 12;
    private final int ZOMBIE_DAMAGE = 15;
    private final int ORC_HEALTH = 18;
    private final int ORC_DAMAGE = 20;
   
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
                damage = DENEKE_DAMAGE;
                break;
            case "Goblin": 
                health = GOBLIN_HEALTH;
                damage = GOBLIN_DAMAGE;
                break;
            case "Zombie": 
                health = ZOMBIE_HEALTH;
                damage = ZOMBIE_DAMAGE;
                break;
            case "Orc": 
                health = ORC_HEALTH;
                damage = ORC_DAMAGE;
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

    public String getMonsterType() {
        return monsterType;
    }
    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /* Hits the targeted Player */
    public void attack(Player target) {
        Random rand = new Random();
        
        damage = rand.nextInt(damage) + 1;
        target.setHealth(target.getHealth() - damage);
             
    }

    /* Removes health from this Monster
    when hit by a Player */
    public void onHit(int damage) {
        health -= damage;
        if(health < 0){
            health = 0; 
            System.out.printf(monsterType, "has been slain!");
        } 
    }
}
