import org.json.JSONObject;

import java.io.*;
import java.nio.charset.Charset;


public class ConfigJSON{
    String configurationJSON ="";
    JSONObject object;
    public ConfigJSON(File file) {
        try {
            this.configurationJSON = utf8FileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.object = new JSONObject(this.configurationJSON);
    }

    public int readNbTest(){
        return object.getInt("nbTest");
    }
    public int readSizeCombination(){
        return object.getInt("sizeCombination");
    }
    public boolean readModeDev(){
        return object.getBoolean("modeDev");
    }

    /** Lit l'InputStream entièrement et le charge en mémoire sous forme de String avec le charset donné.
     * Ignore les fins de ligne.
     * Ne ferme pas l'InputStream, qui doit donc être fermé par son créateur. */
    public static String inputStreamToString(InputStream is, Charset charset) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        for(String line = reader.readLine(); line != null; line = reader.readLine()) {
            builder.append(line);
        }
        return builder.toString();
    }

    /** Lit le fichier entièrement et le charge en mémoire sous forme de String avec le charset donné.
     * Ignore les fins de ligne. */
    public static String fileToString(File file, Charset charset) throws IOException {
        try(InputStream is = new FileInputStream(file)) {
            return inputStreamToString(is, charset);
        }
    }

    /** Lit le fichier utf-8 entièrement et le charge en mémoire sous forme de String.
     * Ignore les fins de ligne. */
    public static String utf8FileToString(File file) throws IOException {
        return fileToString(file, Charset.forName("utf-8"));
    }

}
