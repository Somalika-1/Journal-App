package net.engineeringdigest.journal.App.controller;

import net.engineeringdigest.journal.App.Service.UserService;
import net.engineeringdigest.journal.App.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        System.out.println("Reached Controller");
        userService.saveEntry(user);
    }
}
