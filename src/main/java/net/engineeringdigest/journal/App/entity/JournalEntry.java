package net.engineeringdigest.journal.App.entity;

import lombok.Getter;
import lombok.Setter;
import net.engineeringdigest.journal.App.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

//ORM
@Getter
@Setter
@Document("journal_entries")
public class JournalEntry {

    @Id
    private ObjectId id;
    private LocalDateTime date;
    private String title;
    private String content;
    private Sentiment sentiment;
}
