package chat;

import chat.interfaces.Chat;
import chat.interfaces.ChatSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatService implements Chat {
    private final Set<ChatSocket> subscribedSockets;

    public ChatService() {
        this.subscribedSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void notifyWebSockets(String message) {
        for (ChatSocket socket : subscribedSockets) {
            socket.notify(message);
        }
    }

    @Override
    public void subscribeWebSocket(ChatSocket socket) {
        subscribedSockets.add(socket);
    }

    @Override
    public void unsubscribeWebSocket(ChatSocket socket) {
        subscribedSockets.remove(socket);
    }
}
