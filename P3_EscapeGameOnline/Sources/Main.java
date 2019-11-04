import org.apache.log4j.Logger;

import java.io.File;

public class Main {
    /**
     * main is a function that will create a console and start the game
     *
     * @param args Args no use
     * @see Console
     */
    private static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Lancement du programme");
        ConfigJSON configuration = new ConfigJSON(new File("configuration.json"));
        Console console = new Console(configuration.readSizeCombination(), configuration.readModeDev(), configuration.readNbTest());
        console.run();
        logger.info("Fin du programme");
    }
}
