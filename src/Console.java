import java.util.Scanner;

/**
 * <b> Console is a controller of the Game</b>
 * <p>
 * Console is characterized by :
 * <ul>
 * <li>A number of test</li>
 * <li>A mode of selection</li>
 * <li>A size of combination</li>
 * <li>A choice to determine in run()</li>
 * <li>A mode of development to display or not the combination of IA</li>
 * <li>A boolean victory to determine end of the game</li>
 * </ul>
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
     * <ul>
     * <li>1: Mode challenger</li>
     * <li>2: Mode defender</li>
     * <li>3: Mode duel</li>
     * </ul>
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
     * <ul>
     * <li>0: Stop the game</li>
     * <li>1: Restart with the same mode</li>
     * <li>2: Restart and go to mode of selection</li>
     * </ul>
     * </p>
     */
    private int choice = 0;
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
     * @param nbCombination the size of combination.
     * @param modeDev       the mode of development.
     * @param nbTest        the number of test
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
     * run create the game, and request a selectEndMenu
     * loop if choose is different from 0
     *
     * @see Console#choice
     * @see Console#CreateGame()
     */
    public void run() {
        do {
            CreateGame();
            selectEndMenu();
        } while (choice != 0);
    }

    /**
     * SelectEndMenu request a choice
     *  <ul>
     *  <li>0 : Stop the game.</li>
     *  <li>1 : Restart with the same mode.</li>
     *  <li>2 : Change the mode</li>
     *  </ul>
     *
     * @see Console#choice
     * @see Console#displayEndMenu()
     */
    public void selectEndMenu() {
        int statue;
        Scanner scan = new Scanner(System.in);
        displayEndMenu();
        statue = scan.nextInt();
        if (statue >= 0 && statue < 3)
            choice = statue;
        else
            choice = 0;
    }

    /**
     * Display the menu of the end of game
     */
    public void displayEndMenu() {
        System.out.println("What do you want ?");
        System.out.println("    0 : Stop the game.");
        System.out.println("    1 : Restart with the same mode.");
        System.out.println("    2 : Change the mode");
    }

    /**
     * createGame checked if user select restart with the same mode
     * initializes victory to false
     * and request isVictory()
     *
     * @see Console#choice
     * @see Console#modeSelect
     * @see Console#nbCombination
     * @see Console#victory
     * @see Console#game(Entity, Entity, Entity, Entity)
     * @see Console#isVictory(Entity)
     */
    public void CreateGame() {
        if (choice != 1)
            modeSelect = selectMode(0);
        if (modeSelect == -1)
            return;
        victory = false;
        IA computerDefense = new IA(nbCombination, "computerDefense");
        computerDefense.init();
        Player playerAttack = new Player(nbCombination, "playerAttack");
        IA computerAttack = new IA(nbCombination, "computerAttack");
        computerDefense.init();
        Player playerDefense = new Player(nbCombination, "playerDefense");

        game(playerAttack, computerDefense, playerDefense, computerAttack);
        isVictory(computerDefense);
    }

    /**
     * isVictory display combination of Entity if mode select equals 1 and victory is false
     *
     * @param computerDefense ComputerDefense is an Entity to be displayed
     */
    public void isVictory(Entity computerDefense) {
        if (victory == false && modeSelect == 1) {
            System.out.println("the answer was :");
            computerDefense.display();
        }
    }

    /**
     * Game loops the selected game mode by the number of test
     * or stop is victory is true
     *
     * @param playerAttack    it's an Entity realizing the attack of challenger
     * @param computerDefense it's an Entity realizing the defense of challenger
     * @param playerDefense   it's an Entity realizing the defense of defender
     * @param computerAttack  it's an Entity realizing the attack of defender
     * @see Console#nbTest
     * @see Console#victory
     * @see Console#modeSelect
     * @see Console#challenger(Entity, Entity, int)
     * @see Console#defender(Entity, Entity, int)
     * @see Console#duel(Entity, Entity, Entity, Entity, int)
     */
    public void game(Entity playerAttack, Entity computerDefense, Entity playerDefense, Entity computerAttack) {
        for (int i = 0; i < nbTest && victory == false; i++) {
            System.out.println("Tour " + (i + 1) + " :");
            if (modeSelect == 1)
                challenger(playerAttack, computerDefense, i);
            else if (modeSelect == 2)
                defender(playerDefense, computerAttack, i);
            else if (modeSelect == 3)
                duel(playerAttack, computerDefense, playerDefense, computerAttack, i);
        }
    }

    /**
     * SelectMode permit to asks the type of game
     * <p>
     * <ul>
     * <li>1: Mode challenger</li>
     * <li>2: Mode defender</li>
     * <li>3: Mode duel</li>
     * </ul>
     * </p>
     *
     * @param nb number of loop before to return -1
     * @return A integer to determine choice of modeSelect
     * @see Console#modeSelect
     */
    private int selectMode(int nb) {
        displaySelectMode();
        Scanner scan = new Scanner(System.in);
        int enter = scan.nextInt();
        if (enter > 0 && enter < 4)
            return enter;
        else if (nb < 5)
            return selectMode(nb++);
        else
            return -1;
    }

    /**
     * Display the menu of mode selection
     */
    public void displaySelectMode() {
        System.out.println("What game mode do you want to play ? ");
        System.out.println("    1: Mode challenger ");
        System.out.println("    2: Mode defender ");
        System.out.println("    3: Mode duel ");
    }

    /**
     * Challenger is a function to launch defense of player2
     * then launch attack of player 1
     * and launch a comparator of sign
     * Display a list of signs
     * And launch isEqual
     *
     * @param player1 player1 is the attacker  (often it's the player IRL)
     * @param player2 player2 is the defense (often it's the IA)
     * @param index index is a turn of game
     * @see Console#modeDev
     * @see Console#comparatorSigns(Entity, Entity)
     * @see Entity
     */
    public void challenger(Entity player1, Entity player2, int index) {
        String result = "";
        if (index == 0) {
            player2.defense();
            if (modeDev)
                player2.display();
        }
        player1.attack();
        result = comparatorSigns(player1, player2);
        System.out.println(result);
        isEqual(result, player1.getName(), index);
    }

    /**
     * isEqual passes victory to true and display victory if result equals = for each row
     *
     * @param result result is a string of sign
     * @param entity entity is a name od entity attacker
     * @param turn   turn is a number of turns
     */
    public void isEqual(String result, String entity, int turn) {
        for (int i = 0; i < this.nbCombination; i++) {
            if (result.charAt(i) != '=') {
                victory = false;
                return;
            }
        }
        victory = true;
        System.out.println("the " + entity + " win this game in " + (turn + 1) + " move(s)");
    }

    /**
     * comparatorSigns launch comparator on each row to compares the 2 combination
     *
     * @param player1 player1 is a entity to be compared
     * @param player2 player2 is a entity reference
     * @return a string of sign
     * @see Console#nbCombination
     * @see Console#comparator(int, int)
     * @see Entity#combination
     * @see Entity
     */
    public String comparatorSigns(Entity player1, Entity player2) {
        char signs = ' ';
        String result = "";
        for (int i = 0; i < nbCombination; i++) {
            signs = comparator(player2.combination[i], player1.combination[i]);
            result += signs;
        }
        return result;
    }

    /**
     * Defender is a function to launch defense of player1
     * then launch attack of player 2
     * and launch a comparator of sign
     * launch an update of player2
     * Display a list of signs
     * And launch isEqual
     *
     * @param player1 player1 is the defense  (often it's the player IRL)
     * @param player2 player2 is the attacker (often it's the IA)
     * @param index   index represent the number of iteration not to raise twice the defense
     * @see Console#comparatorSigns(Entity, Entity)
     * @see IA#update(String)
     * @see Entity
     */
    public void defender(Entity player1, Entity player2, int index) {
        String result = "";
        if (index == 0)
            player1.defense();
        player2.attack();
        player2.display();
        result = comparatorSigns(player2, player1);
        player2.update(result);

        System.out.println(result);
        isEqual(result,player2.getName(), index);
    }

    /**
     * duel launches a function challenger with player 1 and 2
     * then if there is no winner launches defender
     *
     * @param player1 player1 is the attacker to challenger (often it's the player IRL)
     * @param player2 player2 is the defense to challenger (often it's the IA)
     * @param player3 player2 is the defense to defender (often it's the player IRL)
     * @param player4 player4 is the attacker to defender (often it's the IA)
     * @param index   index represent the number of iteration not to raise twice the defense
     * @see Console#victory
     * @see Console#challenger(Entity, Entity, int)
     * @see Console#defender(Entity, Entity, int)
     */
    public void duel(Entity player1, Entity player2, Entity player3, Entity player4, int index) {
        challenger(player1, player2, index);
        if (!victory)
            defender(player3, player4, index);
    }

    /**
     * comparator is a simple function to compare num1 with num2
     *
     * @param num1 num1 is a number to be compared
     * @param num2 num2 is a number reference
     * @return a char with a sign (+ or - or =)
     */
    public char comparator(int num1, int num2) {
        if (num1 > num2) {
            return '+';
        } else if (num2 > num1) {
            return '-';
        } else {
            return '=';
        }
    }
}
