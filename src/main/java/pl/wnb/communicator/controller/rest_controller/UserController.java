package pl.wnb.communicator.controller.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import pl.wnb.communicator.model.Response;
import pl.wnb.communicator.model.User;
import pl.wnb.communicator.repository.UserRepository;
import pl.wnb.communicator.repository.CustomUserDetailsService;

import static org.springframework.http.ResponseEntity.ok;


import java.io.IOException;
import java.security.Principal;
import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @PostMapping("/register/new")
    public synchronized ResponseEntity register(@RequestBody User user) {
        String responseMessage;
        int status;

        if (userRepository.findByUsername(user.getUsername()) != null) {
            responseMessage = "Given username is already taken!";
            status=422;
        } else if (userRepository.findByEmail(user.getEmail()) != null){
            responseMessage = "Given e-mail is already taken!";
            status=422;
        } else {
            customUserDetailsService.saveUser(user);
            responseMessage = "ok";
            status=200;
        }

        Response res = new Response.Builder()
                .addContentType("application/json")
                .addCharEncoding("UTF-8")
                .addIsError(false)
                .addMsg(responseMessage)
                .addStatus(status)
                .build();

        return ok(res);

    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }


    @PostMapping(value = {"/web/user/{username}", "/android/user/{username}"})
    public synchronized ResponseEntity addOrChangeKey(@PathVariable("username") String username, Principal principal, @RequestBody byte[] publicKey) {

        Map<Object, Object> model = new HashMap<>();

        User testUser = userRepository.findByUsername(principal.getName());

        if (username.equals(testUser.getUsername())) {

            userRepository.setKey(publicKey, testUser.getUser_id());
            model.put("message", "Key added successfully!");
            return ok(model);
        }

        model.put("message", "You can't add Public Key to another user!");
        return ok(model);
    }


    @PostMapping(value = {"/web/email/{username}", "/android/email/{username}"})
    public synchronized ResponseEntity addOrChangeKeyEmail(@PathVariable("username") String username, Principal principal, @RequestBody String publicKeyEmail) {

        Map<Object, Object> model = new HashMap<>();

        User testUser = userRepository.findByUsername(principal.getName());

        if (username.equals(testUser.getUsername())) {

            userRepository.setKeyEmail(publicKeyEmail, testUser.getUser_id());
            model.put("message", "Key email added successfully!");
            return ok(model);
        }

        model.put("message", "You can't add Public Key email to another user!");
        return ok(model);
    }


    @GetMapping(value = {"/web/user/{username}", "/android/user/{username}"})
    public synchronized ResponseEntity getKey(@PathVariable("username") String username) throws IOException, ClassNotFoundException {
        User user = userRepository.findByUsername(username);
        return ok(user.getPublicKey());
    }

    @GetMapping(value = {"/web/email/{username}", "/android/email/{username}"})
    public synchronized ResponseEntity getKeyEmail(@PathVariable("username") String username) throws IOException, ClassNotFoundException {
        User user = userRepository.findByUsername(username);
        return ok(user.getKeyEmail());
    }


}
