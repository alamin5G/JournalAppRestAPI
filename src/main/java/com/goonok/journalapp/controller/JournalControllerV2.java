package com.goonok.journalapp.controller;

import com.goonok.journalapp.entity.JournalEntity;
import com.goonok.journalapp.entity.User;
import com.goonok.journalapp.service.JournalEntryService;
import com.goonok.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


/*
    //TODO - This controller is used to save the journal data into MongoDB
    // The calling stack = Controller => Service => Repository;
    // now setting up the http return code by ResponseEntity
 */
@RestController
@RequestMapping("/journal")
public class JournalControllerV2 {

    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalsOfUser(@PathVariable String username) {
        User user = userService.findUserByUserName(username);
        List<JournalEntity> all = user.getJournalEntityList();

        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> postMapping(@RequestBody JournalEntity journalEntity) {
        try {
            journalEntity.setPublishedDate(LocalDateTime.now());
            journalEntryService.saveJournalEntity(journalEntity);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<?> getJournalEntityById(@PathVariable ObjectId myId) {

        Optional<JournalEntity> journal = journalEntryService.findJournalEntryById(myId);
        if (journal.isPresent()) {
            return new ResponseEntity<>(journal.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{journalId}")
    public ResponseEntity<?> deleteJournalEntityByJournalId(@PathVariable ObjectId journalId) {
        journalEntryService.deleteJournalEntryById(journalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{journalId}")
    public ResponseEntity<?> updateJournalEntityByJournalId(@PathVariable ObjectId journalId, @RequestBody JournalEntity newEntry) {
        JournalEntity journal = journalEntryService.findJournalEntryById(journalId).orElse(null);
        if (journal != null) {
            newEntry.setPublishedDate(LocalDateTime.now());
            journal.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : journal.getTitle());
            journal.setJournal(newEntry.getJournal() != null && !newEntry.getJournal().isEmpty() ? newEntry.getJournal() : journal.getJournal());
            journal.setNotes(newEntry.getNotes() != null && !newEntry.getNotes().isEmpty() ? newEntry.getNotes() : journal.getNotes());
            journalEntryService.saveJournalEntity(journal);
            return new ResponseEntity<>(journal, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
