package Actor;

import org.apache.log4j.Logger;

import static java.lang.Math.random;

/**
 * <b> Actor.IA is an entity which represents a computer player</b>
 * <p>
 * Actor.IA is characterized by :
 * <ul>
 * <li>A matrix of choice</li>
 * <li>A table of combination</li>
 * <li>A size of combination</li>
 * <li>A name</li>
 * </ul>
 *
 * @author Geoffrey
 * @version 1.0
 * @see Entity
 */
public class IA extends Entity {
    /**
     * maxMin[][] is a matrix of int.
     * it represents the maximum then the minimum the number of his line
     * ex: for a size combination 3 : XXX
     * 0 [9][0]
     * 1 [5][2]
     * 2 [7][1]
     * for the index 1 , the max of choice is 5 and the min is 2.
     */
    int maxMin[][];
    private static final Logger logger = Logger.getLogger(IA.class);
    /**
     * Builder Actor.IA.
     * <p>
     * when building a Actor.IA object, we define the size of combination
     * and we call the builder of entity and we initialize mmaxMin
     * </p>
     *
     * @param sizeCombination the size of combination.
     * @param name name is a name of player
     * @see Entity#sizeCombination
     * @see Entity#name
     * @see Entity#Entity(int, String)
     * @see IA#init()
     */
    public IA(int sizeCombination, String name) {
        super(sizeCombination, name);
        logger.trace("instantiation of an object Actor.IA");
        logger.debug("Actor.IA(sizeCombination = "+sizeCombination+" and name = "+name+")");
        init();
    }

    /**
     * defense is a function
     * who randomly create numbers in 0 and 9 for combination[]
     *
     * @see Entity#sizeCombination
     * @see Entity#combination
     */
    @Override
    public void defense() {
        logger.trace("Input procedure defense");
        for (int i = 0; i < this.sizeCombination; i++) {
            combination[i] = (int) (random() * 9);
        }
        logger.trace("Output procedure defense");
    }

    /**
     * defense is a function
     * who randomly create numbers in minimum and maximum of maxMin[][] for combination[]
     *
     * @see Entity#sizeCombination
     * @see Entity#combination
     * @see IA#maxMin
     */
    @Override
    public void attack() {
        logger.trace("Input procedure attack");
        for (int i = 0; i < this.sizeCombination; i++) {
            combination[i] = (int) (maxMin[i][1] + random() * (maxMin[i][0] - maxMin[i][1]));
        }
        logger.trace("Output procedure attack");
    }

    /**
     * init is a function to initialize maxMin
     * 0 for min , 9 for max
     *
     * @see IA#maxMin
     * @see Entity#sizeCombination
     */
    public void init() {
        logger.trace("Input procedure init");
        maxMin = new int[sizeCombination][2];
        for (int i = 0; i < sizeCombination; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    maxMin[i][j] = 9;
                }
                if (j == 1) {
                    maxMin[i][j] = 0;
                }
            }
        }
        logger.trace("Output procedure init");
    }

    /**
     * function to display the combination of Actor.IA
     *
     * @see Entity#display()
     */
    public void display() {
        logger.trace("Input procedure display");
        System.out.print("["+name+"](Combination of " + sizeCombination + ") :");
        super.display();
        logger.trace("Output procedure display");
    }

    /**
     * this function update maxMin matrix compared to the signs obtained
     *
     * @param signs signs od comparator function of Game.Console (+ or - or =)
     * @param index index is the postion in the combination chart
     * @see IA#maxMin
     * @see Entity#combination
     */
    @Override
    public void updateTable(char signs, int index) {
        logger.trace("Input procedure updateTable");
        logger.debug("updateTable(signs = "+signs+" and index = "+index+")");
        if (signs == '+')
            maxMin[index][1] = combination[index] + 1;
        if (signs == '-')
            maxMin[index][0] = combination[index] - 1;
        if (signs == '=') {
            maxMin[index][1] = combination[index];
            maxMin[index][0] = combination[index];
        }
        logger.trace("Output procedure updateTable");
    }

    /**
     * this function launch updateTable for each row of signs
     *
     * @param signs signs is a string of signs( ex : +-+++=-)
     */
    @Override
    public void update(String signs) {
        logger.trace("Input procedure update");
        logger.debug("update(signs = \""+signs+"\")");
        for (int i = 0; i < sizeCombination; i++)
            updateTable(signs.charAt(i), i);
        logger.trace("Output procedure update");
    }
}

