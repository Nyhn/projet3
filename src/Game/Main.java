package Game;

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
        logger.info("Program launch");
        File file = new File("configuration.json");
        ConfigJSON configuration = new ConfigJSON(file);
        try {
            if(args.length == 0) {
                Console console = new Console(configuration.readSizeCombination(), configuration.readModeDev(), configuration.readNbTest());
                console.run();
            }
            else if(Boolean.parseBoolean(args[0]))
            {
                Console console = new Console(configuration.readSizeCombination(), true, configuration.readNbTest());
                console.run();
            }
            else if(!Boolean.parseBoolean(args[0]))
            {
                Console console = new Console(configuration.readSizeCombination(), false, configuration.readNbTest());
                console.run();
            }
            else
                logger.error("the argument returned is not a boolean"+args[0]);
        } catch (Exception e) {
            configuration.writeDefaultJSONConfig(file);
            logger.error("Error on File : "+file+".\n File init : DONE !\n Please restart program");
        }
        logger.info("end of the program");
    }
}
