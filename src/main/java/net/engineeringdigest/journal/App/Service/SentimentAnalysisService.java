package net.engineeringdigest.journal.App.Service;

import org.springframework.stereotype.Service;

@Service
public class SentimentAnalysisService {

    public int getSentiment(String text){
        return 1;
        /* -1:-ve, 0:neutral, 1:postive */
    }
}
