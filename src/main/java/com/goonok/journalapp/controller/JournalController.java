package com.goonok.journalapp.controller;

import com.goonok.journalapp.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private Map<Long, JournalEntity> journalEntityMap = new HashMap<>();
    private JournalEntity journalEntity;

    @GetMapping
    public List<JournalEntity> getAllJournals() {
        return new ArrayList<>(journalEntityMap.values());
    }

    @PostMapping
    public boolean postMapping(@RequestBody JournalEntity journalEntity){
        journalEntityMap.put(journalEntity.getId(),journalEntity);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntity getJournalEntityById(@PathVariable Long myId){
        return journalEntityMap.get(myId);
    }

    @DeleteMapping("/id/{journalId}")
    public JournalEntity deleteJournalEntityByJournalId(@PathVariable Long journalId){
        return journalEntityMap.remove(journalId);
    }

    @PutMapping("/id/{journalId}")
    public JournalEntity updateJournalEntityByJournalId(@PathVariable long journalId, @RequestBody JournalEntity journalEntity) {
        return journalEntityMap.put(journalId, journalEntity);
    }
}
