package servlets;

import resources.ResourceServer;
import resources.TestResource;
import sax.XmlReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourceServlet extends HttpServlet {
    public static final String URL = "/resources";
    private final ResourceServer resourceServer;

    public ResourceServlet(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resourcePath = req.getParameter("path");
        TestResource resource = (TestResource) new XmlReader().read(resourcePath);
        resourceServer.setResource(resource);
    }
}
