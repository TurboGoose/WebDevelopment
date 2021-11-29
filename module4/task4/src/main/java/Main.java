import mirror.WebSocketChatServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        Server server = new Server(8080);
        server.setHandler(context);

        try {
            server.start();
            Logger.getGlobal().info("Server started");
            server.join();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}

