import accounts.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        AccountService service = AccountService.getInstance();

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(service)), "/signup");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(service)), "/signin");

        Server server = new Server(8080);
        server.setHandler(contextHandler);

        try {
            server.start();
            Logger.getGlobal().info("Server started");
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
