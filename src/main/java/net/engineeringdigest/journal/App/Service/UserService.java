package net.engineeringdigest.journal.App.Service;

import net.engineeringdigest.journal.App.entity.JournalEntry;
import net.engineeringdigest.journal.App.entity.User;
import net.engineeringdigest.journal.App.repository.JournalEntryRepo;
import net.engineeringdigest.journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordencoder;

    public void saveEntry(User user){

        System.out.println("Reached Service");
        try{
            user.setPassword(passwordencoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepo.save(user);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

   /* public void saveNewUser(User user){

        userRepo.save(user);
    }*/

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepo.findById(id);

    }

    public  void deleteById(ObjectId id){
        userRepo.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepo.findByusername(username);

    }
}
//Controller calls Service that calls Repository
