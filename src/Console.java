import java.util.Scanner;

public class Console {
    private  int nbTest;
    private int modeSelect=2;
    private int nbCombination;
    private int choice=0;
    private boolean modeDev;
    private boolean victory = false;

    public Console(int nbCombination, boolean modeDev, int nbTest) {
        this.nbCombination = nbCombination;
        this.modeDev = modeDev;
        this.nbTest = nbTest;
    }

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

    public void game(){
        if(choice != 1)
            modeSelect=selectMode(0);
        if(modeSelect == -1)
            return;
        IA computer = new IA(nbCombination);
        computer.init();
        Player player = new Player(nbCombination);
        IA computer1 = new IA(nbCombination);
        computer.init();
        Player player1 = new Player(nbCombination);

        victory = false;
        for (int i = 0; i < nbTest && victory == false; i++){
            System.out.println("Tour "+(i+1)+" :");
            if(modeSelect == 1)
                challenger(player,computer,i);
            else if(modeSelect ==2)
                defender(player,computer,i);
            else if(modeSelect == 3)
                duel(player, computer, player1, computer1, i);
        }
        if(victory==false){
            System.out.println("the answer was :");
            computer.display();
        }


    }

    private int selectMode(int nb) {
        System.out.println("What game mode do you want to play ? ");
        System.out.println("    1: Mode challenger ? ");
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
            System.out.println("the player win this game en "+index+" move(s)");
        }
    }

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
            System.out.println("the computer win this game en "+index+" move(s)");
        }

    }

    public void duel(Entity player1,Entity player2,Entity player3,Entity player4, int index){
        challenger(player1,player2,index);
        if(!victory)
            defender(player3,player4,index);
    }

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
