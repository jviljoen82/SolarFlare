package za.co.kriptonius.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.OutputStream;

public class PingPong implements HttpHandler {

    private Logger pingLog = LogManager.getLogger(PingPong.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if("GET".equals(httpExchange.getRequestMethod())) {
            OutputStream outputStream = httpExchange.getResponseBody();

            String htmlBuilder = "PONG";

            // encode HTML content
            // String htmlResponse = StringEscapeUtils.escapeHtml4(htmlBuilder);

            // this line is a must
            httpExchange.sendResponseHeaders(200, htmlBuilder.length());
            outputStream.write(htmlBuilder.getBytes());
            outputStream.flush();
            pingLog.info("PING PONG!");
        } else {
            httpExchange.sendResponseHeaders(404, 0);
            pingLog.error("/ping only has GET");
        }
    }
}
