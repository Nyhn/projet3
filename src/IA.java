import static java.lang.Math.random;

public class IA extends Entity{
    int maxMin[][];

    public IA(int sizeCombination) {
        super(sizeCombination);
        init();
    }

    @Override
    public void defense(){
        for(int i = 0; i < this.sizeCombination; i++){
            combination[i] = (int) (random() * 9);
        }
    }

    @Override
    public void attack(){
        for(int i = 0; i < this.sizeCombination; i++){
            combination[i] = (int) (maxMin[i][1]+random() * (maxMin[i][0]-maxMin[i][1]));
        }
    }

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

    public void display(){
        System.out.print("IA     : ");
        super.display();
    }

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
