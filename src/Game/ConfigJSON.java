package Game;

import org.apache.log4j.Logger;
import org.json.JSONException;
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
     * Builder of Game.ConfigJSON
     * when building a Game.ConfigJSON object, we define File of the configuration of JSON
     * and we recover the string of the file for then the transforms in JSON object
     * @param file
     *      file is the name of file of Configuration of JSON
     * @see ConfigJSON#configurationJSON
     * @see ConfigJSON#object
     */
    public ConfigJSON(File file) {
        logger.trace("instantiation of an object Game.ConfigJSON");
        logger.debug("File = "+file);
        try {
            this.configurationJSON = utf8FileToString(file);
        } catch (IOException e) {
            logger.error("the file has encountered an open problem or is non-existent");
        }
        try {
            this.object = new JSONObject(this.configurationJSON);
        } catch (JSONException e) {
            logger.error("Error of "+file);
            writeDefaultJSONConfig(file);
            try {
                this.configurationJSON = utf8FileToString(file);
            }catch (IOException f){
                logger.error("the file has encountered an open problem or is non-existent");
            }
            this.object = new JSONObject(this.configurationJSON);
        }
    }

    /**
     * writeDefaultJSONConfig recreate the file JSON with default Config
     * @param file
     *        Name of file
     */
    public void writeDefaultJSONConfig(File file){
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            writer.println("{");
            writer.println("  \"sizeCombination\" : 4,");
            writer.println("  \"nbTest\" : 5,");
            writer.println("  \"modeDev\" : false");
            writer.println("}");
            writer.close();
        } catch (FileNotFoundException e) {
            logger.error("Impossible to writer in "+file);
        }

    }
    /**
     * readNbTest retrieves NbTest of JSON Object
     * @return nbTest
     *      nbTest is a integer
     * @see ConfigJSON#object
     */
    public int readNbTest(){
        logger.trace("Input procedure readNbTest");
        logger.debug("Return nbTest = "+object.getInt("nbTest"));
        logger.trace("Output procedure readNbTest");
        return object.getInt("nbTest");
    }

    /**
     * readNbTest retrieves sizeCombination of JSON Object
     * @return sizeCombination
     *      sizeCombination is a integer
     * @see ConfigJSON#object
     */
    public int readSizeCombination(){
        logger.trace("Input procedure readSizeCombination");
        logger.debug("Return nbTest = "+object.getInt("sizeCombination"));
        logger.trace("Output procedure readSizeCombination");
        return object.getInt("sizeCombination");
    }

    /**
     * readNbTest retrieves modeDev of JSON Object
     * @return modeDev
     *      modeDev is a boolean
     * @see ConfigJSON#object
     */
    public boolean readModeDev(){
        logger.trace("Input procedure readModeDev");
        logger.debug("Return nbTest = "+object.getBoolean("modeDev"));
        logger.trace("Output procedure readModeDev");
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
