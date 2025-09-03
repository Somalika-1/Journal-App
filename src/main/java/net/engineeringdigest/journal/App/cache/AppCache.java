package net.engineeringdigest.journal.App.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journal.App.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journal.App.repository.ConfigJournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    public ConfigJournalAppRepo configJournalAppRepo;

    public Map<String,String> APP_CACHE;

    //to load api info
    @PostConstruct
    public void init(){
        APP_CACHE=new HashMap<>();
        List<ConfigJournalAppEntity> all=configJournalAppRepo.findAll();

        for(ConfigJournalAppEntity configJournalAppEntity:all){
            APP_CACHE.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }


    }
}
