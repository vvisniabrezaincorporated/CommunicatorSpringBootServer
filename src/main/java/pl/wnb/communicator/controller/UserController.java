package pl.wnb.communicator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


import pl.wnb.communicator.model.User;
import pl.wnb.communicator.repository.UserRepository;
import pl.wnb.communicator.service.CustomUserDetailsService;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        User userExists = userRepository.findByUsername(user.getUsername());
        if (userExists != null) {

            throw new BadCredentialsException("User: " + user.getUsername() + "already exists!");
        }
        customUserDetailsService.saveUser(user);
        Map<Object, Object> model = new HashMap<>();
        model.put("message", "User registered successfully");
        return ok(model);

    }


}
