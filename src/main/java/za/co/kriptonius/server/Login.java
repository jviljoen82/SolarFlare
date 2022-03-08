package za.co.kriptonius.server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import za.co.kriptonius.pojo.api.LoginAPIpojo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Login implements HttpHandler {
    private Logger loginLOG = LogManager.getLogger(Login.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if("POST".equals(httpExchange.getRequestMethod())) {
            try {
                loginLOG.info("logging in");
                Headers requestHeaders = httpExchange.getRequestHeaders();
                Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();
                int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
                InputStream inpStr = httpExchange.getRequestBody();
                byte[] inpData = new byte[contentLength];
                int length = inpStr.read(inpData);
                String inpString = new String(inpData);
                Gson gsonTool = new Gson();
                LoginAPIpojo loginData = gsonTool.fromJson(inpString, LoginAPIpojo.class);
                byte[] decodedPasswordBytes = Base64.getDecoder().decode(loginData.getPassword());
                String decodedPassword = new String(decodedPasswordBytes);



                httpExchange.sendResponseHeaders(200, 9);
                OutputStream outputStream = httpExchange.getResponseBody();
                outputStream.write("SUCCESS!\n".getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception loginExc) {
                loginLOG.error(loginExc.getMessage(), loginExc);
            }
        } else {
            httpExchange.sendResponseHeaders(404, 0);
            loginLOG.error("/login only has GET");
        }
    }
}
