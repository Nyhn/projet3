import java.util.Scanner;

/**
 * <b> Console is a controller of the Game</b>
 * <p>
 *     Console is characterized by :
 *     <ul>
 *         <li>A number of test</li>
 *         <li>A mode of selection</li>
 *         <li>A size of combination</li>
 *         <li>A choice to determine in run()</li>
 *         <li>A mode of development to display or not the combination of IA</li>
 *         <li>A boolean victory to determine end of the game</li>
 *     </ul>
 *
 * @author Geoffrey
 * @version 1.0
 */
public class Console {
    /**
     * number of test. this nbTest can't be change
     */
    private final int nbTest;
    /**
     * mode of selection. modeSelect can take as value
     * <p>
     *     <ul>
     *         <li>1: Mode challenger</li>
     *         <li>2: Mode defender</li>
     *         <li>3: Mode duel</li>
     *     </ul>
     * </p>
     */
    private int modeSelect;
    /**
     * number of combination. this nbCombination is the size of combination
     */
    private final int nbCombination;
    /**
     * choice of action. choice can take as value
     * <p>
     *     <ul>
     *         <li>0: Stop the game</li>
     *         <li>1: Restart with the same mode</li>
     *         <li>2: Restart and go to mode of selection</li>
     *     </ul>
     * </p>
     */
    private int choice=0;
    /**
     * Mode development. it's a boolean, true to activate the display of combination, else false
     */
    private boolean modeDev;
    /**
     * Victory. it's a boolean, true to signal a solution, else false
     */
    private boolean victory = false;

    /**
     * Builder Console.
     * <p>
     * when building a Console object, we define the size of combination
     * the number of test and if the mode of development is activate
     * </p>
     *
     * @param nbCombination
     *            the size of combination.
     * @param modeDev
     *            the mode of development.
     * @param nbTest
     *            the number of test
     *
     * @see Console#nbCombination
     * @see Console#modeDev
     * @see Console#nbTest
     */
    public Console(int nbCombination, boolean modeDev, int nbTest) {
        this.nbCombination = nbCombination;
        this.modeDev = modeDev;
        this.nbTest = nbTest;
    }

    /**
     * run the game, and after request a choice to the user
     * and loop as long as not quit
     *
     * @see Console#choice
     * @see Console#game()
     */
    public void run(){
        int statue;
        do{
            game();
            Scanner scan = new Scanner(System.in);
            System.out.println("What do you want ?");
            System.out.println("    0 : Stop the game.");
            System.out.println("    1 : Restart with the same mode.");
            System.out.println("    2 : Change the mode");
            statue = scan.nextInt();
            if(statue >= 0 && statue < 3)
                choice = statue;
            else
                choice = 0;
        }while(choice != 0);
    }

    /**
     * game checked if user select restart with the same mode
     * else asks the type of game
     * initializes victory to false
     * and loop the game selected by the number of trials
     * if the solution is not found, solution is display
     *
     * @see Console#choice
     * @see Console#modeSelect
     * @see Console#nbCombination
     * @see Console#victory
     * @see Console#nbTest
     * @see Console#challenger(Entity, Entity, int)
     * @see Console#defender(Entity, Entity, int)
     * @see Console#duel(Entity, Entity, Entity, Entity, int)
     */
    public void game(){
        if(choice != 1)
            modeSelect=selectMode(0);
        if(modeSelect == -1)
            return;
        IA computerDefense = new IA(nbCombination,"computerDefense");
        computerDefense.init();
        Player playerAttack = new Player(nbCombination,"playerAttack");
        IA computerAttack = new IA(nbCombination,"computerAttack");
        computerDefense.init();
        Player playerDefense = new Player(nbCombination,"playerDefense");

        victory = false;
        for (int i = 0; i < nbTest && victory == false; i++){
            System.out.println("Tour "+(i+1)+" :");
            if(modeSelect == 1)
                challenger(playerAttack,computerDefense,i);
            else if(modeSelect ==2)
                defender(playerAttack,computerDefense,i);
            else if(modeSelect == 3)
                duel(playerAttack, computerDefense, playerDefense, computerAttack, i);
        }
        if(victory==false){
            System.out.println("the answer was :");
            computerDefense.display();
        }


    }

    /**
     * SelectMode permit to asks the type of game
     * <p>
     *     <ul>
     *         <li>1: Mode challenger</li>
     *         <li>2: Mode defender</li>
     *         <li>3: Mode duel</li>
     *     </ul>
     * </p>
     * @param nb
     *      number of loop before to return -1
     * @return A integer to determine choice of modeSelect
     * @see Console#modeSelect
     */
    private int selectMode(int nb) {
        System.out.println("What game mode do you want to play ? ");
        System.out.println("    1: Mode challenger ");
        System.out.println("    2: Mode defender ");
        System.out.println("    3: Mode duel ");
        Scanner scan = new Scanner(System.in);
        int enter = scan.nextInt();
        if(enter>0 && enter <4)
            return enter;
        else if(nb < 5)
            return selectMode(nb++);
        else
            return -1;
    }

    /**
     * Challenger is a function to launch defense of player2
     * then launch attack of player 1
     * this function compares the 2 combination
     * Display a list of signs
     * if the result is equal to ==== then victory is update to true
     * @param player1
     *      player1 is the attacker  (often it's the player IRL)
     * @param player2
     *      player2 is the defense (often it's the IA)
     * @param index
     *      index represent the number of iteration not to raise twice the defense
     * @see Console#modeDev
     * @see Console#nbCombination
     * @see Console#victory
     * @see Console#comparator(int, int)
     * @see Entity#combination
     */
    public void challenger(Entity player1,Entity player2,int index){
        char signs=' ';
        String result="";
        if(index ==0){
            player2.defense();
            if(modeDev)
                player2.display();
        }
        player1.attack();
        for(int i = 0; i < nbCombination; i++){
            signs = comparator(player2.combination[i],player1.combination[i]);
            result += signs;
        }
        System.out.println(result);
        if(result.equals("====")){
            victory = true;
            System.out.println("the player win this game in "+(index+1)+" move(s)");
        }
    }

    /**
     * Defender is a function to launch defense of player1
     * then launch attack of player 2
     * display the combination of attack
     * this function compares the 2 combination
     * and launch an update of matrix maxMin of player2
     * Display a list of signs
     * if the result is equal to ==== then victory is update to true
     * @param player1
     *      player1 is the defense  (often it's the player IRL)
     * @param player2
     *      player2 is the attacker (often it's the IA)
     * @param index
     *      index represent the number of iteration not to raise twice the defense
     * @see Console#nbCombination
     * @see Console#comparator(int, int)
     * @see Console#victory
     * @see IA#update(char, int)
     * @see Entity#combination
     */
    public void defender(Entity player1,Entity player2,int index){
        char signs=' ';
        String result="";
        if(index ==0)
            player1.defense();
        player2.attack();
        player2.display();
        for(int i = 0; i < nbCombination; i++){
            signs = comparator(player1.combination[i],player2.combination[i]);
            if(player2 instanceof IA){
                player2.update(signs,i);
            }
            result += signs;
        }
        System.out.println(result);
        if(result.equals("====")){
            victory = true;
            System.out.println("the computer win this game en "+(index+1)+" move(s)");
        }

    }

    /**
     * duel launches a function challenger with player 1 and 2
     * then if there is no winner launches defender
     * @param player1
     *       player1 is the attacker to challenger (often it's the player IRL)
     * @param player2
     *       player2 is the defense to challenger (often it's the IA)
     * @param player3
     *      player2 is the defense to defender (often it's the player IRL)
     * @param player4
     *      player4 is the attacker to defender (often it's the IA)
     * @param index
     *      index represent the number of iteration not to raise twice the defense
     * @see Console#victory
     * @see Console#challenger(Entity, Entity, int)
     * @see Console#defender(Entity, Entity, int)
     */
    public void duel(Entity player1,Entity player2,Entity player3,Entity player4, int index){
        challenger(player1,player2,index);
        if(!victory)
            defender(player3,player4,index);
    }

    /**
     * comparator is a simple function to compare num1 with num2
     *
     * @param num1
     *      num1 is a number to be compared
     * @param num2
     *      num2 is a number reference
     * @return a char with a sign (+ or - or =)
     */
    public char comparator(int num1, int num2){
        if(num1>num2){
            return '+';
        }
        else if(num2>num1){
            return '-';
        }
        else{
            return '=';
        }
    }
}
