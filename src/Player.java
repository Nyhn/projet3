import java.util.Scanner;

/**
 * <b> Player is an entity which represents a IRL player</b>
 * <p>
 * Player is characterized by :
 * <ul>
 * <li>A matrix of choice</li>
 * <li>A table of combination</li>
 * <li>A size of combination</li>
 *
 * </ul>
 *
 * @author Geoffrey
 * @version 1.0
 * @see Entity
 */
public class Player extends Entity {

    /**
     * Builder Player.
     * <p>
     * when building a Player object, we define the size of combination
     * and we call the builder of entity
     * </p>
     *
     * @param sizeCombination the size of combination.
     * @param name name is a name of player
     * @see Entity#sizeCombination
     * @see Entity#name
     * @see Entity#Entity(int, String)
     */
    public Player(int sizeCombination, String name) {
        super(sizeCombination, name);
    }

    /**
     * defense is a function
     * which asks the user to choose a combination of sizeCombination
     * and fill in the table of combination
     *
     * @see Entity#sizeCombination
     * @see Entity#combination
     */
    @Override
    public void defense() {
        Scanner scan = new Scanner(System.in);
        String enter;
        do {
            System.out.print("(Proposition of " + name + ")");
            enter = scan.nextLine();
        } while (enter.length() != sizeCombination);
        for (int i = 0; i < sizeCombination; i++)
            this.combination[i] = Character.digit(enter.charAt(i), 10);
    }

    /**
     * attack is the same as defense
     * so he's calling defense
     *
     * @see Player#defense()
     */
    @Override
    public void attack() {
        this.defense();
    }

    /**
     * this function is never us with the class Player
     *
     * @param signs signs to update
     * @param index the box compared
     */
    @Override
    public void updateTable(char signs, int index) {

    }

    @Override
    public void update(String signs) {

    }

    /**
     * function to display the combination of Player
     *
     * @see Entity#display()
     */
    public void display() {
        System.out.print("Player : ");
        super.display();
    }
}
