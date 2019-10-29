import java.io.File;

public class Main {
    /**
     * main is a function that will create a console and start the game
     *
     * @param args Args no use
     * @see Console
     */
    public static void main(String[] args) {
        ConfigLog4j out = new ConfigLog4j();
        ConfigJSON configuration = new ConfigJSON(new File("configuration.json"));
        Console console = new Console(configuration.readSizeCombination(), configuration.readModeDev(), configuration.readNbTest());


        console.run();
    }
}
