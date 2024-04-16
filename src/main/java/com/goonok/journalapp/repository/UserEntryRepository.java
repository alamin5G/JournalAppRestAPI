package com.goonok.journalapp.repository;

import com.goonok.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String username);
    void deleteByUserName(String username);


}
