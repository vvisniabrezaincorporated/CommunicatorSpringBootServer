package pl.wnb.communicator.controller.rest_controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @GetMapping("/web/login")
    public String login() {
        return "login";
    }
    @GetMapping("/login")
    public String secondLoginLink() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    @RequestMapping("/login?error")
    public String logError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
