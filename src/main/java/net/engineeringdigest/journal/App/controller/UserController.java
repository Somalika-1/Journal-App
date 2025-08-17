package net.engineeringdigest.journal.App.controller;

import net.engineeringdigest.journal.App.Service.UserService;
import net.engineeringdigest.journal.App.Service.WeatherService;
import net.engineeringdigest.journal.App.api.response.WeatherResponse;
import net.engineeringdigest.journal.App.entity.User;
import net.engineeringdigest.journal.App.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userindb=userService.findByUsername(username);
        userindb.setUsername(user.getUsername());
        userindb.setPassword(user.getPassword());
        userService.saveNewUser(userindb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByusername(authentication.getName());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse=weatherService.getWeather("Mumbai");
        String greeting="";

        if(weatherResponse!=null){
            greeting=", weather feels like" + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }



}


