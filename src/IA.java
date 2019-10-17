import static java.lang.Math.random;

/**
 * <b> IA is an entity which represents a computer player</b>
 * <p>
 *     IA is characterized by :
 *     <ul>
 *         <li>A matrix of choice</li>
 *         <li>A table of combination</li>
 *         <li>A size of combination</li>
 *     </ul>
 *
 * @see Entity
 * @author Geoffrey
 * @version 1.0
 */
public class IA extends Entity{
    /**
     * maxMin[][] is a matrix of int.
     * it represents the maximum then the minimum the number of his line
     * ex: for a size combination 3 : XXX
     *     0 [9][0]
     *     1 [5][2]
     *     2 [7][1]
     * for the index 1 , the max of choice is 5 and the min is 2.
     */
    int maxMin[][];

    /**
     * Builder IA.
     * <p>
     * when building a IA object, we define the size of combination
     * and we call the builder of entity and we initialize mmaxMin
     * </p>
     *
     * @param sizeCombination
     *            the size of combination.
     *
     * @see Entity#sizeCombination
     * @see Entity#Entity(int)
     * @see IA#init()
     */
    public IA(int sizeCombination) {
        super(sizeCombination);
        init();
    }

    /**
     * defense is a function
     * who randomly create numbers in 0 and 9 for combination[]
     * @see Entity#sizeCombination
     * @see Entity#combination
     */
    @Override
    public void defense(){
        for(int i = 0; i < this.sizeCombination; i++){
            combination[i] = (int) (random() * 9);
        }
    }

    /**
     * defense is a function
     * who randomly create numbers in minimum and maximum of maxMin[][] for combination[]
     * @see Entity#sizeCombination
     * @see Entity#combination
     * @see IA#maxMin
     */
    @Override
    public void attack(){
        for(int i = 0; i < this.sizeCombination; i++){
            combination[i] = (int) (maxMin[i][1]+random() * (maxMin[i][0]-maxMin[i][1]));
        }
    }

    /**
     * init is a function to initialize maxMin
     * 0 for min , 9 for max
     * @see IA#maxMin
     * @see Entity#sizeCombination
     */
    public void init(){
        maxMin = new int[sizeCombination][2];
        for (int i=0;i<sizeCombination;i++){
            for(int j=0;j<2;j++){
                if(j==0){
                    maxMin[i][j] = 9;
                }
                if(j==1){
                    maxMin[i][j] = 0;
                }
            }
        }
    }

    /**
     * function to display the combination of IA
     * @see Entity#display()
     */
    public void display(){
        System.out.print("IA     : ");
        super.display();
    }

    /**
     * this function update maxMin matrix compared to the signs obtained
     * @param signs
     *      signs od comparator function of Console (+ or - or =)
     * @param index
     *      index is the postion in the combination chart
     * @see IA#maxMin
     * @see Entity#combination
     */
    @Override
    public void update(char signs, int index){
        if (signs == '+')
            maxMin[index][1]=combination[index]+1;
        if (signs == '-')
            maxMin[index][0]=combination[index]-1;
        if (signs == '='){
            maxMin[index][1]=combination[index];
            maxMin[index][0]=combination[index];
        }
    }
}
