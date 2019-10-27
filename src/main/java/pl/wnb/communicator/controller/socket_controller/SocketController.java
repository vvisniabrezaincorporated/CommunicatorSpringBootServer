package pl.wnb.communicator.controller.socket_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.wnb.communicator.model.Action;
import pl.wnb.communicator.model.Answer;
import pl.wnb.communicator.model.Message;

import java.util.ArrayList;


@CrossOrigin(origins = "https://83.0.171.108:8443", allowedHeaders = "*")
@Controller
public class SocketController {

    private static ArrayList<String> usersOnline = new ArrayList<>();

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chat")
    public void chat(Message message) {

        Answer answer = new Answer(message.getMessage(), message.getFromUserID());
        simpMessagingTemplate.convertAndSendToUser(String.valueOf(message.getUserID()), "/msg", answer);
    }


    @MessageMapping("/app/chat.addUser")
    @SendTo("/online")
    public synchronized Action addUser(@Payload Action action,
                          SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", action.getSender());
        if (usersOnline.contains(action.getSender())){
            return action;
        }else {
            usersOnline.add(action.getSender());
            return action;
        }
    }
    public synchronized static String removeUserFromList(String user){
        usersOnline.remove(user);
        return "Removed " + user + " from online list";
    }

    @RequestMapping(value = "/android/online", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> loggedUsersAndroid() {
        return usersOnline;
    }

    @RequestMapping(value = "/web/online", method = RequestMethod.GET)
    @ResponseBody
    public String loggedUsersWeb() {
        return usersOnline.toString();
    }


}
