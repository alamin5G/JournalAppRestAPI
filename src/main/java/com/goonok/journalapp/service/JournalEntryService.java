package com.goonok.journalapp.service;

import com.goonok.journalapp.entity.JournalEntity;
import com.goonok.journalapp.entity.User;
import com.goonok.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveJournalEntity(JournalEntity journalEntity, String username){
        User user = userService.findUserByUserName(username);
        JournalEntity save = journalEntryRepository.save(journalEntity);
        user.getJournalEntityList().add(save);
        userService.saveUser(user);
    }

    public void saveJournalEntity(JournalEntity journalEntity){
        journalEntryRepository.save(journalEntity);
    }


    public List<JournalEntity> getAllJournalEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findJournalEntryById(ObjectId id){

        return journalEntryRepository.findById(id);
    }

    public void deleteJournalEntryById(String username, ObjectId id){
        User user = userService.findUserByUserName(username);
        user.getJournalEntityList().removeIf( x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }
}
