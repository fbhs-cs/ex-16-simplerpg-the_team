import java.util.Scanner;

public class Game {

    static Character player;

    // change these to match weapon/spell types
    static final String[] WEAKNESSES = {"Weakness #1", "Weakness #2", "Weakness #3"};

    
    
    public static void main(String[] args) {
        int roundNum = 0;
        Scanner kbd = new Scanner(System.in);
        makeCharacter(kbd);
        while(player.isAlive()) {
            roundNum++;
            System.out.println("Round #" + roundNum);
            playRound();
            System.out.println("Press ENTER to continue");
            kbd.nextLine();
        }
        System.out.println("YOU DIED.");


    }

    
    /** 
     * Makes a Character based on user input
     * @param in Scanner used for input
     */
    public static void makeCharacter(Scanner in) {
        System.out.print("What will your character's name be?\n> ");
        String name = in.nextLine();

        int classChoice;
        while (true) {
            try {
                System.out.println("What class would you like to play?  Here are your options:");
                System.out.println("1. Class #1"); // change this
                System.out.println("2. Class #2"); // change this
                System.out.println("3. Class #3"); // change this
                System.out.print("> ");
                classChoice = Integer.parseInt(in.nextLine());
                if(classChoice < 1 || classChoice > 3) {
                    System.out.println("You must type in 1, 2, or 3!");
                    continue;
                }
                break;

            } catch (Exception e) {
                System.out.println("You must type in 1, 2, or 3!");
            }
        }

        int weaknessChoice;
        while (true) {
            try {
                System.out.println("What weakness will your character have?  Here are your options:");
                for(int i = 0; i < WEAKNESSES.length; i++) {
                    System.out.printf("%d. %s\n",i+1,WEAKNESSES[i]);
                }
                System.out.print("> ");
                weaknessChoice = Integer.parseInt(in.nextLine());
                if(weaknessChoice < 1 || weaknessChoice > WEAKNESSES.length) {
                    System.out.println("Invalid choice!");
                }
                break;

            } catch (Exception e) {
                System.out.println("You must enter a number!");
            }
        }

        String weakness = WEAKNESSES[weaknessChoice-1];

        if(classChoice == 1)
            player = new Character(name,weakness,10); // This needs to be updated for each class
        else if(classChoice == 2)
            player = new Character(name, weakness,20); // update for the class
        else if(classChoice == 3)
            player = new Character(name, weakness,5); // update for the class

        
    }

    
    /** 
     * Generates an enemy at the same level as this.player
     * @return Character the generated enemy
     */
    public static Character makeEnemy() {
        int randWeakness = (int)(Math.random()*WEAKNESSES.length);

        // make speed at least 5, and at most player's speed + 4
        int randSpeed = (int)(Math.random()*player.getSpeed() + 5);

        double rand = Math.random();
        if(rand < 0.3) {
            return new Character("Enemy1",WEAKNESSES[randWeakness],randSpeed); // change to enemy class constructor
        } else if (rand < 0.6) {
            return new Character("Enemy2",WEAKNESSES[randWeakness],randSpeed); // change to enemy class constructor
        } else {
            return new Character("Enemy3",WEAKNESSES[randWeakness],randSpeed); // change to enemy class constructor
        }
    }

    public static void playRound() {
        Character enemy = makeEnemy();
        enemy.setLevel(player.getLevel()-(int)Math.random()*2); // enemy will be same level or 1 level lower
        //System.out.println(player.getName() + " is fighting " + enemy.getName());

        while(player.isAlive() && enemy.isAlive()) {
            System.out.println(player + " vs. " + enemy);
            if(player.getSpeed() > enemy.getSpeed()) {
                player.chooseAction(enemy);
                if(enemy.isAlive()) {
                    enemy.chooseAction(player);
                }
            } else {
                enemy.chooseAction(player);
                if(player.isAlive()) {
                    player.chooseAction(enemy);
                }
            }
        }

        if(player.isAlive()) {
            System.out.println(player.getName() + " vanquished " + enemy.getName());
            player.addXP(enemy.getLevel()*4); // this isn't quite right yet
        }

    }

}