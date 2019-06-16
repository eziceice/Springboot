package com.example.springboot.core.other.websocket;

import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@ServerEndpoint("/ws")
@Getter
public class WevSocketServiceImpl {

    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static CopyOnWriteArraySet<WevSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();

    private Session session;

    /**
     * Method that will be invoked when connection start
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("New Connection In " + getOnlineCount());
        try {
            sendMessage("New Connection!!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Method that will be invoked when connection end
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("One connection has been closed! " + getOnlineCount());
    }

    /**
     * Method that will be invoked when a message is received
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("Message from client: " + message);

        for (WevSocketServiceImpl impl: webSocketSet)
        {
            try {
                String userName = impl.getSession().getUserPrincipal().getName();
                System.out.println(userName);
                impl.sendMessage(message);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error)
    {
        System.out.println("Error!");
        error.printStackTrace();
    }

    private void sendMessage(String message) throws IOException {
        getSession().getBasicRemote().sendText(message);
    }

    private static void addOnlineCount()
    {
        onlineCount.incrementAndGet();
    }

    private static void subOnlineCount()
    {
        onlineCount.decrementAndGet();
    }

    private static int getOnlineCount()
    {
        return onlineCount.get();
    }
}