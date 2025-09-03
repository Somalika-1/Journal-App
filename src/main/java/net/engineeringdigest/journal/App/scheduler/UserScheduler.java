package net.engineeringdigest.journal.App.scheduler;

import net.engineeringdigest.journal.App.Service.EmailService;
import net.engineeringdigest.journal.App.Service.SentimentAnalysisService;
import net.engineeringdigest.journal.App.cache.AppCache;
import net.engineeringdigest.journal.App.entity.JournalEntry;
import net.engineeringdigest.journal.App.entity.User;
import net.engineeringdigest.journal.App.repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepoImpl userRepoImpl;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron="0 0 9 ? * SUN *")
    public void fetchUsersAndSendSaMail(){

        List<User> users=userRepoImpl.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries=user.getJournalEntries();
            List<String> filteredList=journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());

            String entry=String.join(" ",filteredList);
            int sentimentScore = sentimentAnalysisService.getSentiment(entry);
            String sentiment = String.valueOf(sentimentScore);
            emailService.sendMail(user.getEmail(), "Sentiment for last 7 days",sentiment);
        }

    }

    @Scheduled(cron = "0 0/10 * 1/1 * ? *")
    public  void clearAppCache(){
        appCache.init();
    }
}
