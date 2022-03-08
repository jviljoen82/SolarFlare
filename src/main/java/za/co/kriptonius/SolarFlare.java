package za.co.kriptonius;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import za.co.kriptonius.server.MainServer;

public class SolarFlare implements Runnable {
    protected Logger sfLog = LogManager.getLogger(SolarFlare.class);

    /**
     * Default constructor
     *
     */
    public SolarFlare() {
        try {
            MainServer apiServer = new MainServer("127.0.0.1", 8888);
        } catch (Exception sfExc) {
            sfLog.error(sfExc.getMessage(), sfExc);
        }
    }


    @Override
    public void run() {
        sfLog.info("running down here!");
    }
}
