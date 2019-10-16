import java.util.Scanner;

public class Player extends Entity{

    public Player(int sizeCombination) {
        super(sizeCombination);
    }

    @Override
    public void defense(){
        Scanner scan = new Scanner(System.in);
        String enter;
        int number;
        do {
            System.out.println("Choix d'un nombre à " + sizeCombination + " chiffres");
            enter = scan.nextLine();
            System.out.println(enter);
        }while (enter.length() != sizeCombination);
        for(int i =0; i < sizeCombination;i++)
                this.combination[i]= Character.digit(enter.charAt(i),10);
    }

    @Override
    public void attack(){
        this.defense();
    }

    @Override
    public void update(char signs, int index) {

    }

    public void display(){
        System.out.print("Player : ");
        super.display();
    }
}
