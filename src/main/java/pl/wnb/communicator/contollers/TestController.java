package pl.wnb.communicator.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wnb.communicator.model.User;
import pl.wnb.communicator.repository.UserRepository;


@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/status/check")
    public String status()
    {
        return "working";
    }
    @GetMapping("/status/check2")
    public String status2()
    {
        return "working2";
    }
    @GetMapping("/users/getall")
    public User getAllUsers() {
        return userRepository.findByUsername("Admin");
    }
}
