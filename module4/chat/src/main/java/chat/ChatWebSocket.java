package chat;

import chat.interfaces.Chat;
import chat.interfaces.ChatSocket;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ChatWebSocket implements ChatSocket {
    private final Chat chat;
    private Session session;

    public ChatWebSocket(Chat chat) {
        this.chat = chat;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        chat.subscribeWebSocket(this);
        this.session = session;
    }

    @OnWebSocketMessage
    public void onMessage(String data) {
        chat.notifyWebSockets(data);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        chat.unsubscribeWebSocket(this);
    }

    @Override
    public void notify(String message) {
        try {
            session.getRemote().sendString(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
