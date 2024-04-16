package com.goonok.journalapp.repository;

import com.goonok.journalapp.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId> {



}
