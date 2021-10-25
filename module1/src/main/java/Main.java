import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.MirrorServlet;

import javax.servlet.Servlet;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Servlet servlet = new MirrorServlet();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(servlet), "/mirror");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        try {
            server.start();
            Logger.getGlobal().info("Server started");
            server.join();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
