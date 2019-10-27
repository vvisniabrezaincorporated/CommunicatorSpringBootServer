package pl.wnb.communicator.controller.web_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.wnb.communicator.model.User;

@Controller
public class RegisterController {
    @GetMapping("register/new")
    public String check(User user) {
        return "register";
    }
}
