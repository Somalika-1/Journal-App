package net.engineeringdigest.journal.App.repository;

import net.engineeringdigest.journal.App.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalAppEntity, ObjectId> {
}
