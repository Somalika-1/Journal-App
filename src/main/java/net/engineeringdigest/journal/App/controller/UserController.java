package net.engineeringdigest.journal.App.controller;

import net.engineeringdigest.journal.App.Service.JournalEntryService;
import net.engineeringdigest.journal.App.Service.UserService;
import net.engineeringdigest.journal.App.entity.JournalEntry;
import net.engineeringdigest.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;



    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username){
        User userindb=userService.findByUsername(username);

        if(userindb!=null){
            userindb.setUsername(user.getUsername());
            userindb.setPassword(user.getPassword());
            userService.saveEntry(userindb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}


