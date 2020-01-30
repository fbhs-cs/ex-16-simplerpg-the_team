public class Character {

    private static String CLASS_NAME = "Character";

    private String name;
    private String weakness;
    private int speed;
    private int hp;
    private int xp;
  
    public Character(String name, String weakness, int speed) {
        this.name = name;
        this.weakness = weakness;
        this.speed = speed;
        this.xp = 0;
        this.hp = getMaxHP();
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

    public void setLevel(int lvl) {
        xp = lvl * lvl;
        hp = getMaxHP();
    }

    public int getLevel() {
        if(xp == 0)
            return 1;
        return (int)Math.sqrt(xp);
    }

    public int getMaxHP() {
        return getLevel() * 10;
    }

    public String toString() {
        return String.format("%s: %s  HP: %d  Level: %d",CLASS_NAME, name, hp, getLevel());

    }
    
    public boolean isAlive() {
        return hp > 0;
    }

    public void chooseAction(Character target) {
        System.out.println(name + " hugs " + target.getName());
        target.takeDmg(5);
    }

    public void takeDmg(int dmg) {
        hp -= dmg;
    }

    public void addXP(int xpAmt) {
        int currLvl = getLevel();
        xp += xpAmt;
        if (getLevel() > currLvl) {
            System.out.println(name + " leveled up!");
            hp = getMaxHP();
        }
    }

    public int getXP() {
        return xp;
    }
}