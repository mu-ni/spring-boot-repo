package com.muni.test.websocket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/websocket")
public class WebSocketServer {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);		
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	private static Session session;

    @OnOpen
    public void open(Session session) {
    	WebSocketServer.session = session;
    	webSocketSet.add(this);
    	logger.info("OnOpen: onlineCount=" + webSocketSet.size());
    }

    @OnClose
    public void close(Session session) {
    	webSocketSet.remove(this);
    	logger.info("OnClose: onlineCount=" + webSocketSet.size());
    }

    @OnError
    public void onError(Throwable error) {
    	logger.info("OnError: " + error.getMessage()); 	
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
    	logger.info("OnMessage: " + message);
//    	for(DeviceWebSocketServer websocket : webSocketSet){
//    		websocket.sendMessage(message); 
//    	}    	
    }
    
    public static void sendMessage(String message){
    	logger.info("Send message: " + message);
		try {
			session.getBasicRemote().sendText(message);;
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
    }
    
}
