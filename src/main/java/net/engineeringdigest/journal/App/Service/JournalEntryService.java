package net.engineeringdigest.journal.App.Service;

import net.engineeringdigest.journal.App.entity.JournalEntry;
import net.engineeringdigest.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            //retreiving user from db (with encoded password)
            User user=userService.findByUsername(username);
            JournalEntry saved=journalEntryRepo.save(journalentry);
            user.getJournalEntries().add(saved);
            //calling 2nd save method
            userService.saveUser(user);
        }
        catch (Exception e){

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

    @Transactional
    public boolean deleteById(ObjectId id, String username){
        boolean removed;
        try {
            User user=userService.findByUsername(username);
            removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while saving te Entry",e);
        }
        return removed;
    }

}
//Controller calls Service that calls Repository
