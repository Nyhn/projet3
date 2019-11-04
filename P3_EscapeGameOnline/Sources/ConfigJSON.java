import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;


public class ConfigJSON{
    Logger logger = Logger.getLogger(ConfigJSON.class);
    /**
     * configurationJSON is the string of the contents of the file
     */
    String configurationJSON;
    /**
     * object is the representation of the string of configurationJSON in JSONObject
     */
    JSONObject object;

    /**
     * Builder of ConfigJSON
     * when building a ConfigJSON object, we define File of the configuration of JSON
     * and we recover the string of the file for then the transforms in JSON object
     * @param file
     *      file is the name of file of Configuration of JSON
     * @see ConfigJSON#configurationJSON
     * @see ConfigJSON#object
     */
    public ConfigJSON(File file) {
        logger.trace("Instanciation d'un objet ConfigJSON");
        logger.debug("File = "+file);
        try {
            this.configurationJSON = utf8FileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.object = new JSONObject(this.configurationJSON);
    }

    /**
     * readNbTest retrieves NbTest of JSON Object
     * @return nbTest
     *      nbTest is a integer
     * @see ConfigJSON#object
     */
    public int readNbTest(){
        logger.trace("Entrée méthode readNbTest");
        logger.debug("Return nbTest = "+object.getInt("nbTest"));
        logger.trace("Sortie méthode readNbTest");
        return object.getInt("nbTest");
    }

    /**
     * readNbTest retrieves sizeCombination of JSON Object
     * @return sizeCombination
     *      sizeCombination is a integer
     * @see ConfigJSON#object
     */
    public int readSizeCombination(){
        logger.trace("Entrée méthode readSizeCombination");
        logger.debug("Return nbTest = "+object.getInt("sizeCombination"));
        logger.trace("Sortie méthode readSizeCombination");
        return object.getInt("sizeCombination");
    }

    /**
     * readNbTest retrieves modeDev of JSON Object
     * @return modeDev
     *      modeDev is a boolean
     * @see ConfigJSON#object
     */
    public boolean readModeDev(){
        logger.trace("Entrée méthode readModeDev");
        logger.debug("Return nbTest = "+object.getBoolean("modeDev"));
        logger.trace("Sortie méthode readModeDev");
        return object.getBoolean("modeDev");
    }

    /**
     * Reads the InputStream fully and loads it into memory as a String with the given charset.
     * Ignore the line ends.
     * Do not close the InputStream, which must be closed by its creator.
     * @param is
     *      is = InputStream
     * @param charset
     *      Charset
     * @return builder.toString
     *      a string of InputStream
     * @throws IOException
     *      For the problem of opening the file
     */
    public static String inputStreamToString(InputStream is, Charset charset) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line);
        }
        return builder.toString();
    }

    /**
     * Read the file completely and load it into memory as a String with the given charset.
     * Ignore the line ends.
     * @param file
     *      file of Configuration JSON
     * @param charset
     *      Charset
     * @return inputStreamToString()
     *      function who return a string
     * @throws IOException
     *      For the problem of opening the file
     * @see ConfigJSON#inputStreamToString(InputStream, Charset)
     */
    public static String fileToString(File file, Charset charset) throws IOException {
        try(InputStream is = new FileInputStream(file)) {
            return inputStreamToString(is, charset);
        }
    }

    /**
     * Read the utf-8 file completely and load it into memory as a String.
     * Ignore the line ends.
     * @param file
     *      file of configuration JSON
     * @return fileToString
     *      function who return a string
     * @throws IOException
     *      For the problem of opening the file
     * @see ConfigJSON#fileToString(File, Charset)
     */
    public static String utf8FileToString(File file) throws IOException {
        return fileToString(file, Charset.forName("utf-8"));
    }

}
