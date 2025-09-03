package net.engineeringdigest.journal.App.repository;

import net.engineeringdigest.journal.App.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongoTemplate;  //to interact with mongodb

    public List<User> getUserForSA(){
        Query query=new Query();

        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,63}$"));
        query.addCriteria(Criteria.where("sentimentalAnalysis").is(true));
        
        List<User> users=mongoTemplate.find(query,User.class); //implementing ORM

        System.out.println("users"+users);
        return users;
    }
}
