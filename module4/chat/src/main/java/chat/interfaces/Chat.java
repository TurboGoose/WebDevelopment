package chat.interfaces;

public interface Chat {
    void notifyWebSockets(String message);
    void subscribeWebSocket(ChatSocket socket);
    void unsubscribeWebSocket(ChatSocket socket);
}
