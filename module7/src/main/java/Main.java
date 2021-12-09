import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class Main {
    static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        logger.info("Server started");
        for (int i = 0; i < 10; i++) {
            new MultiSocket();
        }
    }
}
