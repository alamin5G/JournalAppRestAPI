package com.goonok.journalapp.controller;

import com.goonok.journalapp.entity.JournalEntity;
import com.goonok.journalapp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
    //TODO - This controller is used to save the journal data into MongoDB
    // The calling stack = Controller => Service => Repository;
 */
@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;


    @GetMapping
    public List<JournalEntity> getAllJournals() {

        return journalEntryService.getAllJournalEntries();
    }

    @PostMapping
    public JournalEntity postMapping(@RequestBody JournalEntity journalEntity){
        journalEntity.setPublishedDate(LocalDateTime.now());
        journalEntryService.saveJournalEntity(journalEntity);
        return journalEntity;
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getJournalEntityById(@PathVariable ObjectId myId){


        return journalEntryService.findJournalEntryById(myId).orElse(null);
    }

    @DeleteMapping("/id/{journalId}")
    public boolean deleteJournalEntityByJournalId(@PathVariable ObjectId journalId){
        journalEntryService.deleteJournalEntryById(journalId);
        return true;
    }

    @PutMapping("/id/{journalId}")
    public JournalEntity updateJournalEntityByJournalId(@PathVariable ObjectId journalId, @RequestBody JournalEntity newEntry) {
        JournalEntity journal = journalEntryService.findJournalEntryById(journalId).orElse(null);
        if (journal != null) {
            newEntry.setPublishedDate(LocalDateTime.now());
            journal.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : journal.getTitle());
            journal.setJournal(newEntry.getJournal() != null && !newEntry.getJournal().isEmpty() ? newEntry.getJournal() : journal.getJournal());
            journal.setNotes(newEntry.getNotes() != null && !newEntry.getNotes().isEmpty() ? newEntry.getNotes() : journal.getNotes());
        }
        journalEntryService.saveJournalEntity(journal);
        return journal;
        }
}
