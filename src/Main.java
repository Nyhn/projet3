public class Main {
    /**
     * main is a function that will create a console and start the game
     * @param args
     *      Args no use
     * @see Console
     */
    public static void main(String[] args) {
        Console console = new Console(4,true,10);
        console.run();
    }
}
