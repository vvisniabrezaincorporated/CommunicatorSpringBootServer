package pl.wnb.communicator.controller.socket_controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import pl.wnb.communicator.model.Action;
import pl.wnb.communicator.model.ActionType;
@Component
public class WebSocketActionListener {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketActionListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {

        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            logger.info("User Disconnected : " + username);
            Action action = new Action();
            action.setType(ActionType.LEAVE);
            action.setSender(username);
            SocketController.removeUserFromList(username);
            messagingTemplate.convertAndSend("/online", action);
        }
    }
}
