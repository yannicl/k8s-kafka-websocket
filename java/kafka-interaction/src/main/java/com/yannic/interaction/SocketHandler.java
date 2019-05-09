package com.yannic.interaction;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class SocketHandler extends TextWebSocketHandler {

    static ConcurrentLinkedQueue<WebSocketSession> sessions = new ConcurrentLinkedQueue<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message)
            throws IOException {

        if (message.getPayload().startsWith("Time:")) {
            long time = Long.valueOf(message.getPayload().substring(5));
            System.out.println("Full trip back time:" + (System.currentTimeMillis() - time) + "ms");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Websocket opened");
        sessions.add(session);
    }

    public void forwardTransactions(String transaction) {
        System.out.println("Found " + sessions.size() + " sessions");
        List<WebSocketSession> toDeleteList = new ArrayList<>();
        for(WebSocketSession webSocketSession : sessions) {
            try {
                webSocketSession.sendMessage(new TextMessage(transaction));
                System.out.println("Message forwarded: " + transaction);
            } catch (Exception e) {
                toDeleteList.add(webSocketSession);
            }
        }
        sessions.removeAll(toDeleteList);
    }
}
