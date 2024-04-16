package com.goonok.journalapp.entity;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntity {

    @Id
    private ObjectId id;
    private String title;
    private String journal;
    private String notes;

    private LocalDateTime publishedDate;

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "JournalEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", journal='" + journal + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
