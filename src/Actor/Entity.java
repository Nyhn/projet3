package Actor;

import org.apache.log4j.Logger;

/**
 * <b> Actor.Entity is a representation of an element player</b>
 * <p>
 * Actor.Entity is characterized by :
 * <ul>
 * <li>A board of combination</li>
 * <li>A size of combination</li>
 * <li>A name</li>
 * </ul>
 *
 * @author Geoffrey
 * @version 1.0
 */
public abstract class Entity {
    /**
     * Name is an ID unique
     */
    protected String name;
    /**
     * sizeCombination is the size of combination
     * ex: XXX a combination of 3
     */
    protected int sizeCombination;

    /**
     * Combination is a table of int ( between 0-9)
     */
    public int[] combination;
    private static final Logger logger = Logger.getLogger(Entity.class);
    /**
     * Builder Actor.Entity
     * <p>
     * when building a Actor.IA object, we define the size of combination
     * and create a table combination
     * </p>
     *
     * @param sizeCombination the size of combination
     * @param name name is a name of player
     * @see Entity#combination
     * @see Entity#sizeCombination
     */
    public Entity(int sizeCombination, String name) {
        logger.trace("instantiation of an object Actor.Entity");
        logger.debug("sizeCombination = "+sizeCombination+" , name = "+name);
        this.name = name;
        this.sizeCombination = sizeCombination;
        combination = new int[sizeCombination];
    }

    /**
     * abstract function to redefine
     */
    public abstract void defense();

    /**
     * abstract function to redefine
     */
    public abstract void attack();

    /**
     * abstract function to redefine
     *
     * @param sign the signs to update
     * @param index the box compared
     */
    public abstract void updateTable(char sign, int index);

    public abstract void update(String signs);

    /**
     * Display the combination
     */
    public void display() {
        logger.trace("Input procedure display");
        for (int i = 0; i < this.sizeCombination; i++) {
            System.out.print(combination[i]);
        }
        System.out.println();
        logger.trace("Output procedure display");
    }

    /**
     * Getter of Name
     * @return Name
     *      name of class Actor.Entity
     * @see Entity#name
     */
    public String getName() {
        return name;
    }
}
