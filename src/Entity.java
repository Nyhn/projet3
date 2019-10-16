public abstract class Entity {
    protected int sizeCombination;
    protected int[] combination;

    public Entity(int sizeCombination) {
        this.sizeCombination = sizeCombination;
        combination = new int[sizeCombination];
    }


    public abstract void defense();

    public abstract void attack();

    public abstract void update(char signs, int index);

    public void display(){
        for(int i = 0; i < this.sizeCombination; i++){
            System.out.print(combination[i]);
        }
        System.out.println();
    }

}
