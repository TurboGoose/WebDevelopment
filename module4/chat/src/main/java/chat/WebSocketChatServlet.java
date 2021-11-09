package chat;

import chat.interfaces.Chat;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    private static final int LOGOUT_TIME_MS = 10 * 60 * 1000;
    private final Chat chat;

    public WebSocketChatServlet() {
        this.chat = new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME_MS);
        webSocketServletFactory.setCreator((req, res) -> new ChatWebSocket(chat));
    }
}
