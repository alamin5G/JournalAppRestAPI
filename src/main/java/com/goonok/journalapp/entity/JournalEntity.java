package com.goonok.journalapp.entity;

public class JournalEntity {

    private long id;
    private String title;
    private String journal;
    private String notes;

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
