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
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerv2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    UserService userService;

    @GetMapping("{username}")
    //methods inside controller should be public so that they can be accessed by springframework
    public ResponseEntity<?> getAllJournalEntriesOfuser(@PathVariable String username) {

        User user=userService.findByUsername(username);
        List<JournalEntry> l=user.getJournalEntries();

        if(l!=null && !l.isEmpty()){
            return new ResponseEntity<>(l,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String username){

        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntrybyid(@PathVariable ObjectId myid){
        Optional<JournalEntry> myEntry=journalEntryService.findById(myid);

        if(myEntry.isPresent()){
            return new ResponseEntity<>(myEntry.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myid}")
    public ResponseEntity<?> deleteJournalEntrybyid(@PathVariable ObjectId myid,@PathVariable String username){
        journalEntryService.deleteById(myid,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{username}/{myid}")
    public ResponseEntity<JournalEntry> updateJournalEntrybyid(
            @PathVariable ObjectId myid,
            @RequestBody JournalEntry newEntry,
            @PathVariable String username){

        Optional<JournalEntry> optionalOld = journalEntryService.findById(myid);

        if (optionalOld.isEmpty()) {
            //HttpStatus enum.
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JournalEntry old = optionalOld.get();

        if(old!=null){
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent() );
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle() );
        }
        journalEntryService.saveEntry(old);
        return new ResponseEntity<>(old,HttpStatus.OK);
    }
}


