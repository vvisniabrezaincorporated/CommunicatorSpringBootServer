package pl.wnb.communicator.controller.web_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://83.0.171.108:8443", allowedHeaders = "*")
@Controller
@RequestMapping("/web/")
public class ChatController {

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

}
