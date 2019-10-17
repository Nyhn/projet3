/**
 * <b> Entity is a representation of an element player</b>
 * <p>
 *     Entity is characterized by :
 *     <ul>
 *         <li>A board of combination</li>
 *         <li>A size of combination</li>
 *     </ul>
 *
 * @author Geoffrey
 * @version 1.0
 */
public abstract class Entity {
    /**
     * sizeCombination is the size of combination
     * ex: XXX a combination of 3
     */
    protected int sizeCombination;

    /**
     * Combination is a table of int ( between 0-9)
     */
    protected int[] combination;

    /**
     * Builder Entity
     * <p>
     *     when building a IA object, we define the size of combination
     *     and create a table combination
     * </p>
     *
     * @param sizeCombination
     *      the size of combination
     * @see Entity#combination
     * @see Entity#sizeCombination
     */
    public Entity(int sizeCombination) {
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
     * @param signs
     *      the signs to update
     * @param index
     *      the box compared
     */
    public abstract void update(char signs, int index);

    /**
     * Display the combination
     */
    public void display(){
        for(int i = 0; i < this.sizeCombination; i++){
            System.out.print(combination[i]);
        }
        System.out.println();
    }

}
