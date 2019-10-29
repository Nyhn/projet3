import org.apache.logging.*;
import  org.apache.logging.log4j.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

public class ConfigLog4j {
    private static final Logger logger = Logger.getLogger("GameServer");


    public static void tieSystemOutAndErrToLog() {
        System.setOut(new ProxyPrintStream(System.out,"logError.log"));
        System.setErr(new ProxyPrintStream(System.err,"logError.log"));
    }

    static class ProxyPrintStream extends PrintStream {
        private PrintStream fileStream = null;
        public ProxyPrintStream(PrintStream out, String FilePath) {
            super(out);
            try {
                FileOutputStream fout = new FileOutputStream(FilePath,true);
                fileStream = new PrintStream(fout);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        public void print(final String str) {
            fileStream.println(str);
        }
        public void println(final String str) {
            fileStream.println(str);
        }
    }
}
