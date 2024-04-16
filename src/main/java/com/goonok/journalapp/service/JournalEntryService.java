package com.goonok.journalapp.service;

import com.goonok.journalapp.entity.JournalEntity;
import com.goonok.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveJournalEntity(JournalEntity journalEntity){
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAllJournalEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findJournalEntryById(ObjectId id){

        return journalEntryRepository.findById(id);
    }

    public void deleteJournalEntryById(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
