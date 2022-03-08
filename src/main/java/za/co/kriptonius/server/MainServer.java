package za.co.kriptonius.server;

import com.sun.net.httpserver.HttpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Singleton Class for HTTPServer
 */
public class MainServer {
    protected Logger serverLog = LogManager.getLogger(MainServer.class);
    public final HttpServer SERVER;

    /**
     * Http Server constructor     *
     * @param serverIP host ip address ie: 127.0.0.1
     * @param serverPort host port
     * @throws IOException throws io exception
     */
    public  MainServer(String serverIP, int serverPort) throws IOException {
        serverLog.info("Starting API server on: " + serverIP + " PORT: " + serverPort);
        SERVER = HttpServer.create(new InetSocketAddress(serverIP, serverPort), 0);
        SERVER.createContext("/ping", new PingPong());
        SERVER.createContext("/login", new Login());
        SERVER.start();
    }

}
