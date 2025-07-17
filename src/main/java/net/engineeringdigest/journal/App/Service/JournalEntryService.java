package net.engineeringdigest.journal.App.Service;

import net.engineeringdigest.journal.App.entity.JournalEntry;
import net.engineeringdigest.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.engineeringdigest.journal.App.repository.JournalEntryRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    //to achieve atomicity
    @Transactional
    public void saveEntry(JournalEntry journalentry, String username){
        try{
            User user=userService.findByUsername(username);
            JournalEntry saved=journalEntryRepo.save(journalentry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured");
        }

    }

    public void saveEntry(JournalEntry journalentry){
        journalEntryRepo.save(journalentry);

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);

    }

    public  void deleteById(ObjectId id, String username){
        User user=userService.findByUsername(username);
        journalEntryRepo.deleteById(id);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);

    }
}
//Controller calls Service that calls Repository
